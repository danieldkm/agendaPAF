package com.unifil.agendapaf.util;

import com.unifil.agendapaf.dao.JPA;
import com.unifil.agendapaf.model.aux.Anual;
import com.unifil.agendapaf.view.controller.RelatorioController;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.ButtonType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JExcelApiExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
//JRDataSource dataSource

public class GerarRelatorios {

    private UtilDialog dialog;

    public GerarRelatorios() {
        if (dialog == null) {
            dialog = new UtilDialog();
        }
    }

    public GerarRelatorios(UtilDialog dialog) {
        this.dialog = dialog;
    }

//    private Map<String, Object> parametros = new HashMap<String, Object>(); // para receber o dados determinado pelo usu�rio que ir� filtrar conforme essa informa��o
//    private String caminhoAbsoluto = "relatorios";
//    private String caminhoAbsoluto = "src/relatorios";
    /**
     * Gera o relat�rio conforme as v�riaveis passadas por parametro
     *
     * @param stage o FileChooser precisa de um stage para abrir a janela
     * @param jasperFile diretorio do arquivo
     * @param parametros parametros que compoem os arquivos jasper
     * @param extensao tipo de extensão que foi escolhido pelo usuário
     * @param isAnual caso o relatorio for do tipo anual gera o grafico
     * @param listaAnual para compor o grafico
     */
    public void gerarRelatorio(Stage stage, String jasperFile, Map<String, Object> parametros,/*String agrupadoPor, Object dados,*/ String extensao/*, String tipo*/, boolean isAnual, List<Anual> listaAnual) {

//        String jasperFile = getJasperFile(tipo, agrupadoPor, dados);
        System.out.println("jasperFile " + jasperFile);
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save");
            File file = fileChooser.showSaveDialog(stage);
//            org.apache.log4j.BasicConfigurator.configure();
//            JasperReport compile = JasperCompileManager.compileReport(jasperFile);
            String print = null;
            if (isAnual) {
//                JRDataSource dataSource = new JRBeanCollectionDataSource(RelatorioController.listaAnual);
//                print = JasperFillManager.fillReport(
//                        compile, parametros, dataSource);
                JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(listaAnual, false); // false para n�o ler espa�os em branco
                print = JasperFillManager.fillReportToFile(
                        jasperFile, parametros, dataSource);
            } else {
//                parametros.put(JRJpaQueryExecuterFactory.PARAMETER_JPA_ENTITY_MANAGER, JPA.em());
                print = JasperFillManager.fillReportToFile(
                        jasperFile, parametros, JPA.getConnection());
//                JPA.em(false).close();

            }
            if (extensao.equals("PDF")) {
//                System.out.println("Caminho p1 " + file.getPath());
                if (!file.getPath().contains(".pdf")) {
                    String novo = file.getAbsoluteFile() + ".pdf";
                    File f = new File(novo);
                    file = f;
                }
                JasperExportManager.exportReportToPdfFile(print,
                        file.getAbsolutePath());
            } else if (extensao.equals("Excel")) {
                //Renomear o arquivo
                if (!file.getPath().contains(".xls")) {
                    String novo = file.getAbsoluteFile() + ".xls";
                    File f = new File(novo);
                    file = f;
                }
//                System.out.println("Caminho p2 " + file.getPath());
                JExcelApiExporter exporter = new JExcelApiExporter();
                exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");
                exporter.setParameter(JRExporterParameter.OUTPUT_FILE, file);
                exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
                exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
                exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,
                        file);
                exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
                exporter.exportReport();
            }
            dialog.criarDialogInfomation("Informação", "Informação do sistema", "Gerado com sucesso!!!");
            Desktop desk = Desktop.getDesktop();
            desk.open(file);
            if (isAnual) {
                Optional<ButtonType> result = dialog.criarDialogConfirmacao("Informação", "Informação do sistema", "Deseja gerar o Gráfico?!");
                if (result.get() == ButtonType.OK) {
                    gerarSubRelatorioAnual(stage, "relatorios/RelatorioFinanceiroAnualSubReport.jrxml", parametros, listaAnual);
                }
                RelatorioController.isAnual = false;
            }
        } catch (Exception e) {
            dialog.criarDialogException("Informação", "Informação do sistema", "Erro ao gerar!!!", e, "Exception");
            e.printStackTrace();
        }
    }

    /**
     * Gera o relatorio conforme as variaveis passadas por parametro
     *
     * @param stage o FileChooser precisa de um stage para abrir a janela
     * @param caminhoAbsoluto diretorio do arquivo
     * @param parametros parametros que compoem os arquivos jasper
     * @param listaAnual para compor o grafico
     */
    private void gerarSubRelatorioAnual(Stage stage, String caminhoAbsoluto, Map<String, Object> parametros, List<Anual> listaAnual) {
        JRDataSource dataSource = new JRBeanCollectionDataSource(listaAnual, false);
        JasperReport compile;
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save");
            File file = fileChooser.showSaveDialog(stage);
            compile = JasperCompileManager.compileReport(caminhoAbsoluto);
            JasperPrint print = JasperFillManager.fillReport(
                    compile, parametros, dataSource);
            JasperExportManager.exportReportToPdf(print);
            if (!file.getPath().contains(".pdf")) {
                String novo = file.getAbsoluteFile() + ".pdf";
                File f = new File(novo);
                file = f;
            }
//                System.out.println("Caminho p1 " + file.getPath());
            JasperExportManager.exportReportToPdf(print);
            JRPdfExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE, file);
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,
                    file);
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
            exporter.exportReport();
            dialog.criarDialogInfomation("Informação", "Informação do sistema", "Gerado com sucesso!!!");
            Desktop desk = Desktop.getDesktop();
            desk.open(file);
        } catch (JRException ex) {
            Logger.getLogger(RelatorioController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RelatorioController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

//    public void gerarRelatorio(Stage stage, String agrupadoPor, Object dados, String extensao, String tipo, JRDataSource dataSource) {
//
//        String jasperFile = getJasperFile(tipo, agrupadoPor, dados);
//        parametros.put("codAgenda", "F%");
//        parametros.put("codEmpresa", "F%");
//        parametros.put("empresa", "F%");
//        parametros.put("responsavel", "F%");
//        parametros.put("tipo", "F%");
//        parametros.put("dataInicial", "F%");
//        parametros.put("dataFinal", "F%");
//        parametros.put("statusBoleto", "F%");
//        parametros.put("statusAgenda", "F%");
//        parametros.put("dataReagendada", "F%");
//        try {
//            FileChooser fileChooser = new FileChooser();
//            fileChooser.setTitle("Save");
//            File file = fileChooser.showSaveDialog(stage);
//            JasperPrint print = JasperFillManager.fillReport(
//                    jasperFile, parametros, dataSource);
//            if (extensao.equals("PDF")) {
////                System.out.println("Caminho p1 " + file.getPath());
//                if (!file.getPath().contains(".pdf")) {
////                    String novo = file.getAbsoluteFile() + ".pdf";
//                    String novo = file.getAbsolutePath() + ".pdf";
//                    File f = new File(novo);
//                    file = f;
//                }
////                System.out.println("Caminho p1 " + file.getPath());
//                JRPdfExporter exporter = new JRPdfExporter();
//                exporter.setParameter(JRExporterParameter.OUTPUT_FILE, file);
//                exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,
//                        file);
//                exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
//                exporter.exportReport();
//            } else if (extensao.equals("Excel")) {
//                //Renomear o arquivo
//                if (!file.getPath().contains(".xls")) {
//                    String novo = file.getAbsoluteFile() + ".xls";
//                    File f = new File(novo);
//                    file = f;
//                }
////                System.out.println("Caminho p2 " + file.getPath());
//                JExcelApiExporter exporter = new JExcelApiExporter();
//                exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");
//                exporter.setParameter(JRExporterParameter.OUTPUT_FILE, file);
//                exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
//                exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
//                exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,
//                        file);
//                exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
//                exporter.exportReport();
//            }
//
//            Desktop desk = Desktop.getDesktop();
//            desk.open(file);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    /**
//     * Verifica o tipo do relat�rio se agenda, empresa, historico e empresas
//     * homologas. E escolhe o filtro caso empresa, data ou vazio, quando
//     * escolhido ser� setado o caminho do arquivo jasper (o relat�rio)
//     *
//     *
//     * @param tipo uma String para verificar o tipo do relat�rio
//     * @param agrupadoPor uma String para verificar o agrupando desejado p/
//     * gerar relat�rio
//     * @param dados � um objeto que pode ser util.Date ou Integer, que ir�o
//     * determinar no parametros o filtro dos relat�rios
//     * @return caso esncontre o tipo e o agrupadoPor retorna o caminho do
//     * arquivo jasper
//     */
//    private String getJasperFile(String tipo, String agrupadoPor, Object dados) {
//        BufferedImage gto = null;
//        try {
//            gto = ImageIO.read(getClass().getResource("/Imagem/logoPAFECFUNIFIL.png"));
//        } catch (IOException ex) {
//            Logger.getLogger(GerarRelatorios.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        parametros.put("logo", gto);
//        switch (tipo) {
//            case "Agenda":
//                switch (agrupadoPor) {
//                    case "Empresa":
//                        parametros.put("codEmpresa", dados);
//                        return caminhoAbsoluto + "/RelatorioAgendaPorEmpresa.jrxml";
//                    case "Data":
//                        parametros.put("data", dados);
//                        return caminhoAbsoluto + "/RelatorioAgendaPorData.jrxml";
//                    case "":
//                        return caminhoAbsoluto + "/RelatorioAgenda.jrxml";
//                }
//                break;
//            case "Empresa":
//                return caminhoAbsoluto + "/RelatorioEmpresa.jrxml";
//            case "Historico":
//                switch (agrupadoPor) {
//                    case "Empresa":
//                        parametros.put("codEmpresa", dados);
//                        return caminhoAbsoluto + "/RelatorioHistoricoEmpresa.jrxml";
//                    case "Data":
//                        parametros.put("data", dados);
//                        return caminhoAbsoluto + "/RelatorioHistoricoData.jrxml";
//                    case "":
//                        return caminhoAbsoluto + "/RelatorioHistorico.jasper";
//                }
//                break;
//            case "Empresas Homologadas":
//                switch (agrupadoPor) {
//                    case "Empresa":
//                        parametros.put("codEmpresa", dados);
//                        return caminhoAbsoluto + "/RelatorioEmpresasHomologadasPorEmpresa.jrxml";
//                    case "Data":
//                        parametros.put("data", dados);
//                        return caminhoAbsoluto + "/RelatorioEmpresasHomologadasPorData.jrxml";
//                    case "":
//                        System.out.println("arquivo empresas homologadas geral e agrupadoPor " + agrupadoPor);
//                        return caminhoAbsoluto + "/RelatorioEmpresasHomologadas.jrxml";
//                }
//                break;
//            case "Financeiro":
//                switch (agrupadoPor) {
//                    case "Mensal":
//                        parametros.put("dataIncial", dados);
//                        parametros.put("dataFinal", dados);
//                        return caminhoAbsoluto+ "/RelatorioFinanceiro.jrxml";
//                }
//                break;
//
//        }
//        System.out.println("caminho " + caminhoAbsoluto);
//        return "erro";
//    }
}
