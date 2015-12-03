package com.unifil.agendapaf.util;

import com.unifil.agendapaf.util.mensagem.Dialogos;
import com.unifil.agendapaf.SceneManager;
import com.unifil.agendapaf.dao.JPA;
import com.unifil.agendapaf.model.aux.Anual;
import com.unifil.agendapaf.util.mensagem.Mensagem;
import com.unifil.agendapaf.view.controller.RelatorioController;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

    private Mensagem mensagem;

    public GerarRelatorios() {
        mensagem = new Mensagem(null);
    }

//    private Map<String, Object> parametros = new HashMap<String, Object>(); // para receber o dados determinado pelo usu�rio que ir� filtrar conforme essa informa��o
//    private String caminhoAbsoluto = "relatorios";
//    private String caminhoAbsoluto = "src/relatorios";
    /**
     * Gera o relat�rio conforme as v�riaveis passadas por parametro
     *
     * @param stage referencia para abrir a janela
     * @param jasperFile diretorio do arquivo
     * @param parametros parametros que compoem os arquivos jasper
     * @param extensao tipo de extensão que foi escolhido pelo usuário
     * @param isAnual caso o relatorio for do tipo anual gera o grafico
     * @param listaAnual para compor o grafico
     */
    public void gerarRelatorio(Stage stage, String jasperFile, Map<String, Object> parametros,/*String agrupadoPor, Object dados,*/ String extensao/*, String tipo*/, boolean isAnual, List<Anual> listaAnual) {

        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save");
            File file = fileChooser.showSaveDialog(stage);
            if (file != null) {
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
                mensagem.informacao("Informação", "Informação do sistema", "Gerado com sucesso!!!");
                Desktop desk = Desktop.getDesktop();
                desk.open(file);
                File fileSubRelatorio = null;
                if (isAnual) {
                    Dialogos d = new Dialogos(stage);
                    Optional<ButtonType> result = d.confirmacao("Informação", "Informação do sistema", "Deseja gerar o Gráfico?!");
                    if (result.get() == ButtonType.OK) {
                        fileSubRelatorio = gerarSubRelatorioAnual(stage, "relatorios/RelatorioFinanceiroAnualSubReport.jrxml", parametros, listaAnual);
                    }
                    SceneManager.getInstance().getRelatorioController().setIsAnual(false);
                }
                SceneManager.getInstance().getRelatorioController().actionBtnLimpar();
                ObservableList<File> anexos = FXCollections.observableArrayList();
                anexos.add(file);
                if (fileSubRelatorio != null) {
                    anexos.add(fileSubRelatorio);
                }
                SceneManager.getInstance().showEmail(anexos);
            }
            JPA.em(false).close();
        } catch (Exception e) {
            mensagem.erro("Informação", "Informação do sistema", "Erro ao gerar!!!", e);
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
    private File gerarSubRelatorioAnual(Stage stage, String caminhoAbsoluto, Map<String, Object> parametros, List<Anual> listaAnual) {
        JRDataSource dataSource = new JRBeanCollectionDataSource(listaAnual, false);
        JasperReport compile;
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save");
            File file = fileChooser.showSaveDialog(stage);
//            System.out.println("Caminho p1 " + file.getPath());
//            System.out.println("File " + file);
            if (file != null) {
                compile = JasperCompileManager.compileReport(caminhoAbsoluto);
                JasperPrint print = JasperFillManager.fillReport(
                        compile, parametros, dataSource);
                JasperExportManager.exportReportToPdf(print);
                if (!file.getPath().contains(".pdf")) {
                    String novo = file.getAbsoluteFile() + ".pdf";
                    File f = new File(novo);
                    file = f;
                }
                JasperExportManager.exportReportToPdf(print);
                JRPdfExporter exporter = new JRPdfExporter();
                exporter.setParameter(JRExporterParameter.OUTPUT_FILE, file);
                exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,
                        file);
                exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
                exporter.exportReport();
                mensagem.informacao("Informação", "Informação do sistema", "Gerado com sucesso!!!");
                Desktop desk = Desktop.getDesktop();
                desk.open(file);
            }
            return file;
        } catch (JRException ex) {
            Logger.getLogger(RelatorioController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RelatorioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
