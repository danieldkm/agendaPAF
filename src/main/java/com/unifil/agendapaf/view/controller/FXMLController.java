package com.unifil.agendapaf.view.controller;

import com.unifil.agendapaf.model.Agenda;
import com.unifil.agendapaf.model.Empresa;
import com.unifil.agendapaf.model.Feriado;
import com.unifil.agendapaf.model.Financeiro;
import com.unifil.agendapaf.model.Usuario;
import com.unifil.agendapaf.controller.Controller;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.sql.Date;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class FXMLController extends Application implements Initializable {

    @Override
    public void start(Stage primaryStage) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    //AGENDA CONTROLLER-INICIO--------------------------------------------------
//    private static Agenda agendaEncontrada;
//
//    public static Agenda getAgendaEncontrada() {
//        return agendaEncontrada;
//    }
//
//    public static void setAgendaEncontrada(Agenda agendaEncontrada) {
//        FXMLController.agendaEncontrada = agendaEncontrada;
//    }
//
//    //AGENDA CONTROLLER-FIM-----------------------------------------------------
//    //TABELA DE AGENDA CONTROLLER-INICIO----------------------------------------
////    java.util.Date dataSelecionada;
//    private static boolean isUpdate = false;
//
//    public static boolean isUpdate() {
//        return isUpdate;
//    }
//
//    public static void setIsUpdate(boolean isUpdate) {
//        FXMLController.isUpdate = isUpdate;
//    }
//
//    //TABELA DE AGENDA CONTROLLER-FIM-------------------------------------------
//    //MOTIVO CONTROLLER-INICIO--------------------------------------------------
//    private static String txtMotivoReagendamento;
//
//    public static String getTxtMotivoReagendamento() {
//        return txtMotivoReagendamento;
//    }
//
//    public static void setTxtMotivoReagendamento(String txtMotivoReagendamento) {
//        FXMLController.txtMotivoReagendamento = txtMotivoReagendamento;
//    }
//
//    //MOTIVO CONTROLLER-FIM-----------------------------------------------------
//    //FINANCEIRO CONTROLLER-INICIO----------------------------------------------
//    private static boolean isTabelaEmpresaToFinanceiro = false;
//    private static java.sql.Date dtInicial, dtFinal;
//    private static Financeiro financeiroEncontrado;
////    private static Empresa financeiroEmpresa;
////    private static Agenda financeiroAgenda;
//
//    public static boolean isTabelaEmpresaToFinanceiro() {
//        return isTabelaEmpresaToFinanceiro;
//    }
//
//    public static void setIsTabelaEmpresaToFinanceiro(boolean isTabelaEmpresaToFinanceiro) {
//        FXMLController.isTabelaEmpresaToFinanceiro = isTabelaEmpresaToFinanceiro;
//    }
//
//    public static Date getDtInicial() {
//        return dtInicial;
//    }
//
//    public static void setDtInicial(Date dtInicial) {
//        FXMLController.dtInicial = dtInicial;
//    }
//
//    public static Date getDtFinal() {
//        return dtFinal;
//    }
//
//    public static void setDtFinal(Date dtFinal) {
//        FXMLController.dtFinal = dtFinal;
//    }
//
//    public static Financeiro getFinanceiroEncontrado() {
//        return financeiroEncontrado;
//    }
//
//    public static void setFinanceiroEncontrado(Financeiro financeiroEncontrado) {
//        FXMLController.financeiroEncontrado = financeiroEncontrado;
//    }
//
////    public static Empresa getFinanceiroEmpresa() {
////        return financeiroEmpresa;
////    }
////
////    public static void setFinanceiroEmpresa(Empresa financeiroEmpresa) {
////        FXMLController.financeiroEmpresa = financeiroEmpresa;
////    }
////
////    public static Agenda getFinanceiroAgenda() {
////        return financeiroAgenda;
////    }
////
////    public static void setFinanceiroAgenda(Agenda financeiroAgenda) {
////        FXMLController.financeiroAgenda = financeiroAgenda;
////    }
//    //FINANCEIRO CONTROLLER-FIM-------------------------------------------------
//    //FERIADO CONTROLLER-INICIO-------------------------------------------------
//    private static Feriado feriadoEncontrado;
//
//    public static Feriado getFeriadoEncontrado() {
//        return feriadoEncontrado;
//    }
//
//    public static void setFeriadoEncontrado(Feriado feriadoEncontrado) {
//        FXMLController.feriadoEncontrado = feriadoEncontrado;
//    }
//
//    //FERIADO CONTROLLER-FIM----------------------------------------------------
//    //USUARIO CONTROLLER-INICIO-------------------------------------------------
//    private static Usuario usuarioEncontrado;
//
//    public static Usuario getUsuarioEncontrado() {
//        return usuarioEncontrado;
//    }
//
//    public static void setUsuarioEncontrado(Usuario usuarioEncontrado) {
//        FXMLController.usuarioEncontrado = usuarioEncontrado;
//    }
//    //USUARIO CONTROLLER-FIM----------------------------------------------------
//
//    //TABELA DE EMPRESA-INICIO--------------------------------------------------------
//    private static boolean isTabelaAgenda = false;
//    private static boolean isAgenda = false;
//    private static boolean isEmpresa = false;
//    private static boolean isTabelaHistorico = false;
//    private static boolean isConsulta = false;
//    private static boolean isTabelaEmpresaHomologada = false;
//    private static boolean isRelatorio = false;
//    private static String txtVisualizadorMotivo;
//
//    public static String getTxtVisualizadorMotivo() {
//        return txtVisualizadorMotivo;
//    }
//
//    public static void setTxtVisualizadorMotivo(String txtVisualizadorMotivo) {
//        FXMLController.txtVisualizadorMotivo = txtVisualizadorMotivo;
//    }
//
//    public static boolean isTabelaAgenda() {
//        return isTabelaAgenda;
//    }
//
//    public static void setIsTabelaAgenda(boolean isTabelaAgenda) {
//        FXMLController.isTabelaAgenda = isTabelaAgenda;
//    }
//
//    public static boolean isAgenda() {
//        return isAgenda;
//    }
//
//    public static void setIsAgenda(boolean isAgenda) {
//        FXMLController.isAgenda = isAgenda;
//    }
//
//    public static boolean isEmpresa() {
//        return isEmpresa;
//    }
//
//    public static void setIsEmpresa(boolean isEmpresa) {
//        FXMLController.isEmpresa = isEmpresa;
//    }
//
//    public static boolean isTabelaHistorico() {
//        return isTabelaHistorico;
//    }
//
//    public static void setIsTabelaHistorico(boolean isTabelaHistorico) {
//        FXMLController.isTabelaHistorico = isTabelaHistorico;
//    }
//
//    public static boolean isConsulta() {
//        return isConsulta;
//    }
//
//    public static void setIsConsulta(boolean isConsulta) {
//        FXMLController.isConsulta = isConsulta;
//    }
//
//    public static boolean isTabelaEmpresaHomologada() {
//        return isTabelaEmpresaHomologada;
//    }
//
//    public static void setIsTabelaEmpresaHomologada(boolean isTabelaEmpresaHomologada) {
//        FXMLController.isTabelaEmpresaHomologada = isTabelaEmpresaHomologada;
//    }
//
//    public static boolean isRelatorio() {
//        return isRelatorio;
//    }
//
//    public static void setIsRelatorio(boolean isRelatorio) {
//        FXMLController.isRelatorio = isRelatorio;
//    }
//    //TABELA DE EMPRESA-FIM-----------------------------------------------------
//
//    //EMPRESA-INICIO------------------------------------------------------------
//    private static Empresa empresaEncontrada;
//    private static boolean isTabelaEmpresa;
//
//    public static Empresa getEmpresaEncontrada() {
//        return empresaEncontrada;
//    }
//
//    public static void setEmpresaEncontrada(Empresa empresaEncontrada) {
//        FXMLController.empresaEncontrada = empresaEncontrada;
//    }
//
//    public static boolean isTabelaEmpresa() {
//        return isTabelaEmpresa;
//    }
//
//    public static void setIsTabelaEmpresa(boolean isTabelaEmpresa) {
//        FXMLController.isTabelaEmpresa = isTabelaEmpresa;
//    }
//
//    //EMPRESA-FIM---------------------------------------------------------------
//    //FERRAMENTA-INICIO---------------------------------------------------------
//    private static boolean isServidor = false;
//    private static boolean isLocal = false;
//    private static boolean isSelectedLocal = false;
//
//    public static boolean isServidor() {
//        return isServidor;
//    }
//
//    public static void setIsServidor(boolean isServidor) {
//        FXMLController.isServidor = isServidor;
//    }
//
//    public static boolean isLocal() {
//        return isLocal;
//    }
//
//    public static void setIsLocal(boolean isLocal) {
//        FXMLController.isLocal = isLocal;
//    }
//
//    public static boolean isSelectedLocal() {
//        return isSelectedLocal;
//    }
//
//    public static void setIsSelectedLocal(boolean isSelectedLocal) {
//        FXMLController.isSelectedLocal = isSelectedLocal;
//    }
//
//    //FERRAMENTA-FIM------------------------------------------------------------
//    //POPUP-INICIO--------------------------------------------------------------
//    private static double x;
//    private static double y;
//
//    public static double getX() {
//        return x;
//    }
//
//    public static void setX(double x) {
//        FXMLController.x = x;
//    }
//
//    public static double getY() {
//        return y;
//    }
//
//    public static void setY(double y) {
//        FXMLController.y = y;
//    }
//
//    //POPUP-FIM-----------------------------------------------------------------
//    //GERAL-INICIO--------------------------------------------------------------
//
////    protected static boolean salvo = false;
////
////    @FXML
////    protected static Button btnSalvar;
////    @FXML
////    protected static Button btnCancelar;
////    @FXML
////    protected static Button btnAlterar;
////    @FXML
////    protected static Button btnBuscar;
//    private static boolean isReagendamento = false;
//    private static boolean isCancelamento = false;
//    private static Usuario usuarioLogado;
//
//    @Override
//    public void start(Stage stage) throws Exception {
////        stage.getIcons().add(Controller.icoPAF);
//    }
//
////    public void criarPopUp() {
////        try {
////            popUp.getItems().addAll(
////                    MenuItemBuilder.create()
////                    .text("Agendar")
////                    //                .graphic(createIcon(...))
////                    .onAction(new EventHandler() {
////                        @Override
////                        public void handle(Event t) {
////                            setIsCancelamento(false);
////                            setIsReagendamento(false);
////                            setIsUpdate(false);
////                            runAnotherApp(AgendarController.class);
////                        }
////                    })
////                    .
////                    build(),
////                    SeparatorMenuItemBuilder.create().build(),
////                    MenuItemBuilder.create()
////                    .text("Reagendar")
////                    .onAction(new EventHandler() {
////                        @Override
////                        public void handle(Event t) {
////                            setIsReagendamento(true);
////                            setIsCancelamento(false);
////                            setIsUpdate(false);
////                            runAnotherApp(TabelaAgendaController.class);
////
//////                            TabelaAgendaController.data.setSelectedDate(AgendarController.dataSelecionada);
//////                            
//////                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//////                            TabelaAgendaController.getCbComboBox().getSelectionModel().selectLast();
//////                            Dao<Agenda> dao = new Dao(Agenda.class);
//////                            
//////                            TabelaAgendaController.listaAgendas.clear();
//////                            TabelaAgendaController.listaAgendas = dao.findByDate(sdf.format(AgendarController.dataSelecionada));
//////                            TabelaAgendaController.tvAgenda.getItems().setAll(TabelaAgendaController.listaAgendas);
////                        }
////                    })
////                    .build(),
////                    /*.graphic(createIcon())*/
////                    SeparatorMenuItemBuilder.create().build(),
////                    MenuItemBuilder.create()
////                    .text("Atualizar Agenda")
////                    .onAction(new EventHandler() {
////
////                        @Override
////                        public void handle(Event t) {
////                            Dao<Agenda> dao = new Dao(Agenda.class);
////                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
////
////                            ObservableList<Agenda> listaAgendas = dao.findByDate(sdf.format(converterLocalDateToUtilDate(Controller.getDataSelecionada())));
////                            if (listaAgendas.size() == 0) {
//////                                Dialogs.showInformationDialog(null, "N�o existe agendamentos nesta data, \npara ser atualizado", "aaaa");
////                                criarDialogInfomation("Informa��o", "Informa��o do sistema", "N�o existe agendamentos nesta data, \\npara ser atualizado");
////                            } else {
////                                setIsReagendamento(false);
////                                setIsCancelamento(false);
////                                setIsUpdate(true);
////                                runAnotherApp(TabelaAgendaController.class);
////
//////                                TabelaAgendaController.data.setSelectedDate(AgendarController.dataSelecionada);
//////                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//////                                TabelaAgendaController.getCbComboBox().getSelectionModel().selectLast();
////////                                Dao<Agenda> dao = new Dao(Agenda.class);
//////                                TabelaAgendaController.listaAgendas.clear();
//////                                TabelaAgendaController.listaAgendas = dao.findByDate(sdf.format(AgendarController.dataSelecionada));
//////                                TabelaAgendaController.tvAgenda.getItems().setAll(TabelaAgendaController.listaAgendas);
//////                                setIsReagendamento(false);
//////                                setIsCancelamento(false);
////                            }
////                        }
////
////                    }).build(),
////                    SeparatorMenuItemBuilder.create().build(),
////                    MenuItemBuilder.create()
////                    .text("Cancelar Agendamento")
////                    .onAction(new EventHandler() {
////                        @Override
////                        public void handle(Event t) {
////                            Dao<Agenda> dao = new Dao(Agenda.class);
////                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//////                            ObservableList<Agenda> listaAgendas = dao.findByDate(sdf.format(AgendarController.dataSelecionada));
//////                            if (listaAgendas.size() == 0) {
//////                                Dialogs.showInformationDialog(null, "N�o existe agendamentos nesta data, \npara ser atualizado", "aaaa");
//////                            } else {
//////                                boolean cancelado = false;
//////                                for (Agenda agenda : listaAgendas) {
//////                                    if (agenda.getStatusAgenda().equals("Cancelado") || agenda.getStatusAgenda().equals("Concluido") || agenda.getStatusAgenda().equals("Nao compareceu")) {
//////                                        cancelado = true;
//////                                    } else {
//////                                        cancelado = false;
//////                                    }
//////                                }
//////                                if (!cancelado) {
//////                                    runAnotherApp(TabelaAgendaController.class);
//////                                    TabelaAgendaController.data.setSelectedDate(AgendarController.dataSelecionada);
////////                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//////                                    TabelaAgendaController.getCbComboBox().getSelectionModel().selectLast();
////////                                Dao<Agenda> dao = new Dao(Agenda.class);
//////                                    TabelaAgendaController.listaAgendas.clear();
//////                                    TabelaAgendaController.listaAgendas = dao.findByDate(sdf.format(AgendarController.dataSelecionada));
//////                                    TabelaAgendaController.tvAgenda.getItems().setAll(TabelaAgendaController.listaAgendas);
//////                                    setIsCancelamento(true);
//////                                    setIsReagendamento(false);
//////                                }
//////                            }
////                        }
////                    })
////                    .build()
////            );
////        } catch (Exception e) {
////            e.printStackTrace();
////            System.out.println("Erro!! Classe: Controller.class  Metodo: criarPopUp");
////        }
////    }
////    private ImageView createIcon(URL iconURL) {
////        return ImageViewBuilder.create()
////                .image(new Image(iconURL.toString()))
////                .build();
////    }
////    public static String converterEncoding(String t) {
////        byte[] bytes = null;
////        try {
////            bytes = t.getBytes("UTF-8");
////            return new String(bytes, "UTF-8");
////        } catch (UnsupportedEncodingException ex) {
////            System.out.println("Erro ao converter encoding");
////            ex.printStackTrace();
////        }
////        return null;
////    }
//    public static boolean isReagendamento() {
//        return isReagendamento;
//    }
//
//    public static void setIsReagendamento(boolean isReagendamento) {
//        FXMLController.isReagendamento = isReagendamento;
//    }
//
//    public static boolean isCancelamento() {
//        return isCancelamento;
//    }
//
//    public static void setIsCancelamento(boolean isCancelamento) {
//        FXMLController.isCancelamento = isCancelamento;
//    }
//
//    /**
//     * Retorna o caminho onde a aplica��o est� sendo executada
//     *
//     * @return caminho da aplica��o
//     */
//    public String getApplicationPath() {
//        String url = getClass().getResource(getClass().getSimpleName() + ".class").getPath();
//        File dir = new File(url).getParentFile();
//        String path = null;
//
//        if (dir.getPath().contains(".jar")) {
//            path = findJarParentPath(dir);
//        } else {
//            path = dir.getPath();
//        }
////        Dialogs.showInformationDialog(null, "caminho " + path);
//
//        try {
//            return URLDecoder.decode(path, "UTF-8");
//        } catch (UnsupportedEncodingException e) {
//            return path.replace("%20", " ");
//        }
//    }
//
//    /**
//     * retorna o caminho absoluto do arquivo jar do projeto
//     *
//     * @param jarFile
//     * @return
//     */
//    private String findJarParentPath(File jarFile) {
//        while (jarFile.getPath().contains(".jar")) {
//            jarFile = jarFile.getParentFile();
//        }
//
//        return jarFile.getPath().substring(6);
//    }
//
//    /**
//     * Valida��o Text Field
//     */
//    @Deprecated
//    public static void validate(Object control) {
//        if (control instanceof TextField) {
//            TextField t = (TextField) control;
//            t.setStyle("-fx-text-fill:yellow;   "
//                    + "-fx-prompt-text-fill:gray;  "
//                    + "-fx-highlight-text-fill:black ;  "
//                    + "-fx-highlight-fill: gray;  "
//                    + "-fx-background-color: red;");
//        } else if (control instanceof ComboBox) {
//            ComboBox c = (ComboBox) control;
//            c.setStyle("-fx-text-fill:yellow;   "
//                    + "-fx-prompt-text-fill:gray;  "
//                    + "-fx-highlight-text-fill:black ;  "
//                    + "-fx-highlight-fill: gray;  "
//                    + "-fx-background-color: red;");
//        }
//    }
//
////    public void executarConteudoScript(ObservableList<Script> listaScript, Connection connetion, boolean servidor) {
//////        this.listaScript = listScript;
////        Connection conexao = connetion;
////        Statement sta = null;
////        boolean erro = false;
////        try {
////            sta = conexao.createStatement();
////            String sql;
////            for (Script script : listaScript) {
////                System.out.println("SCRIPT " + script.getData());
////                sta.executeUpdate(script.getData());
////                if (servidor) {
////                    sql = "insert into script (data) values (\"" + script.getData() + "\");";
////                    System.out.println("SCRIPT on server " + sql);
////                    sta.executeUpdate(sql);
////                }
////            }
////            sta.close();
////        } catch (SQLException ex) {
////            Logger.getLogger(LoginController.class
////                    .getName()).log(Level.SEVERE, null, ex);
////            erro = true;
////        }
////        if (!erro) {
////            erro = false;
////            try {
////                conexao = ConexaoLocal.getInstance();
////                sta = conexao.createStatement();
////                System.out.println("DROP TABLE SCRIPT e criar de novo");
////                sta.executeUpdate("drop table if exists `script`");
////                sta.executeUpdate("CREATE TABLE `script` ( `cod` int(11) NOT NULL AUTO_INCREMENT, "
////                        + "`data` varchar(30000) DEFAULT NULL, "
////                        + "PRIMARY KEY (`cod`) ) "
////                        + "ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;");
////                sta.close();
////            } catch (Exception e) {
////                e.printStackTrace();
////                erro = true;
////            }
////            if (!erro) {
////                criarDialogInfomation("Informa��o", "Informa��o do sistema", "BD local foi sincronizado com sucesso");
////            } else {
////                criarDialogInfomation("Informa��o", "Informa��o do sistema", "ERRO: BD local n�o foi sincronizado");
////            }
////        }
////    }
//
////    protected ObservableList<Script> selectScript(Connection con) {
////        PreparedStatement s = null;
////        ObservableList<Script> a = FXCollections.observableArrayList();
////        try {
////            s = con.prepareStatement("select * from script");
////            ResultSet r = s.executeQuery();
////            Script script = null;
////            while (r.next()) {
////                script = new Script();
////                script.setCod(r.getInt("cod"));
////                script.setData(r.getString("data"));
////                a.add(script);
////            }
////            r.close();
//////            Statement sta = con.createStatement();
//////            sta.execute("DROP TABLE IF EXISTS `script`;");
//////            sta.execute("CREATE TABLE `script` (`cod` int(11) NOT NULL AUTO_INCREMENT,`data` varchar(30000) DEFAULT NULL,PRIMARY KEY (`cod`)) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;");
//////            sta.close();
////
////        } catch (SQLException ex) {
////            Logger.getLogger(LoginController.class
////                    .getName()).log(Level.SEVERE, null, ex);
////        } finally {
////            try {
////                s.close();
//////                con.close();
////            } catch (SQLException ex) {
////                Logger.getLogger(LoginController.class
////                        .getName()).log(Level.SEVERE, null, ex);
////            }
////        }
////        return a;
////    }
//
//    public void initialize(URL url, ResourceBundle rb) {
//    }
//
//    public static Usuario getUsuarioLogado() {
//        return usuarioLogado;
//    }
//
//    public static void setUsuarioLogado(Usuario usuarioLogado) {
//        FXMLController.usuarioLogado = usuarioLogado;
//    }
//
//    private static String removeInvalidCaracterOnMask(String txt) {
//        String txtAux = txt;
//        if (txtAux.contains(" ")) {
//            txtAux = txtAux.replace(" ", "");
//        }
//        if (txtAux.contains("-")) {
//            txtAux = txtAux.replace("-", "");
//        }
//        if (txtAux.contains("(")) {
//            txtAux = txtAux.replace("(", "");
//        }
//        if (txtAux.contains(")")) {
//            txtAux = txtAux.replace(")", "");
//        }
//
//        return txtAux;
//    }
//
//    /**
//     * Adds a static mask to the specified text field.
//     *
//     * @param tf the text field.
//     * @param mask the mask to apply. Example of usage: addMask(txtDate, " / /
//     * ");
//     */
//    public static void addMask(final TextField tf, final String mask) {
//        tf.setText(mask);
//        addTextLimiter(tf, mask.length());
//
//        tf.textProperty().addListener(new ChangeListener<String>() {
//            @Override
//            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
//                String txt = removeInvalidCaracterOnMask(tf.getText());
//                String value = stripMask(txt, mask);
//                tf.setText(merge(value, mask));
//            }
//        });
//
//        tf.setOnKeyPressed(new EventHandler<KeyEvent>() {
//            @Override
//            public void handle(final KeyEvent e) {
//                int caretPosition = tf.getCaretPosition();
//                if (caretPosition < mask.length() - 1 && mask.charAt(caretPosition) != ' ' && e.getCode() != KeyCode.BACK_SPACE && e.getCode() != KeyCode.LEFT) {
//                    tf.positionCaret(caretPosition + 1);
//                }
//            }
//        });
//    }
//
//    static String merge(final String value, final String mask) {
//        final StringBuilder sb = new StringBuilder(mask);
//        int k = 0;
//        for (int i = 0; i < mask.length(); i++) {
//            if (mask.charAt(i) == ' ' && k < value.length()) {
//                sb.setCharAt(i, value.charAt(k));
//                k++;
//            }
//        }
//        return sb.toString();
//    }
//
//    static String stripMask(String text, final String mask) {
//        final Set<String> maskChars = new HashSet<>();
//        for (int i = 0; i < mask.length(); i++) {
//            char c = mask.charAt(i);
//            if (c != ' ') {
//                maskChars.add(String.valueOf(c));
//            }
//        }
//        for (String c : maskChars) {
//            text = text.replace(c, "");
//        }
//        return text;
//    }
//
//    public static void addTextLimiter(final TextField tf, final int maxLength) {
//        tf.textProperty().addListener(new ChangeListener<String>() {
//            @Override
//            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
//                if (tf.getText().length() > maxLength) {
//                    String s = tf.getText().substring(0, maxLength);
//                    tf.setText(s);
//                }
//            }
//        });
//    }
}
