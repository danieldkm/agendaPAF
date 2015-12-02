package com.unifil.agendapaf.view.controller;

import com.unifil.agendapaf.MainApp;
import com.unifil.agendapaf.model.Contato;
import com.unifil.agendapaf.model.Empresa;
import com.unifil.agendapaf.model.Usuario;
import com.unifil.agendapaf.model.aux.FerramentaEmail;
import com.unifil.agendapaf.statics.StaticLista;
import com.unifil.agendapaf.util.UtilEmail;
import com.unifil.agendapaf.util.UtilFile;
import com.unifil.agendapaf.util.mensagem.Mensagem;
import com.unifil.agendapaf.view.util.enums.EnumCaminho;
import com.unifil.agendapaf.view.util.enums.EnumMensagem;
import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javassist.bytecode.annotation.EnumMemberValue;
import org.controlsfx.control.PopOver;
import org.controlsfx.control.action.Action;
import org.controlsfx.control.action.ActionUtils;

/**
 * FXML Controller class
 *
 * @author danielmorita
 */
public class EmailController extends Application {

    /**
     * Initializes the controller class.
     */
    @FXML
    public void initialize() {
        //Menu
        principal.getChildren().add(0, ActionUtils.createToolBar(actions, ActionUtils.ActionTextBehavior.SHOW));

        mensagem = new Mensagem(stage);
        hideHtmlEditor();
        masterArrowSize = new SimpleDoubleProperty(0);
        masterArrowIndent = new SimpleDoubleProperty(0);
        masterCornerRadius = new SimpleDoubleProperty(0);
        masterArrowLocation = new SimpleObjectProperty<>(PopOver.ArrowLocation.TOP_CENTER);
        masterHeaderAlwaysVisible = new SimpleBooleanProperty(false);
        listaContato = FXCollections.observableArrayList();
        addAllListaContato();

        listaGrupo = FXCollections.observableArrayList();
        listaGrupo.add("Todos");
        listaGrupo.add("Empresa");
        listaGrupo.add("Usuário");

        UtilFile utilFile = new UtilFile();
        ferramentaEmail = (FerramentaEmail) utilFile.unmarshalFromFile(FerramentaEmail.class, "FerramentaEmail.xml");
        txtDe.setText(ferramentaEmail.getEmail());
        if (ferramentaEmail.getCaminhoImg() != null) {
            if (!ferramentaEmail.getCaminhoImg().equals("")) {
                htmlEditor.setHtmlText(
                        "<br>"
                        + "<br>"
                        + "<br>"
                        + "<img src=\""
                        + "file:" + ferramentaEmail.getCaminhoImg()
                        //                            + getClass().getResource("/image/iconePAFECF.png")
                        + "\"  >"
                //                            + "\" width=\"32\" height=\"32\" >"
                );
            }
        }

        utilEmail = new UtilEmail();
    }

    private void addAllListaContato() {
        for (Empresa e : StaticLista.getListaGlobalEmpresa()) {
            listaContato.add(e);
        }

        for (Usuario u : StaticLista.getListaGlobalUsuario()) {
            listaContato.add(u);
        }
    }

    private void addEmpresasListaContato() {
        for (Empresa e : StaticLista.getListaGlobalEmpresa()) {
            listaContato.add(e);
        }
    }

    private void addUsuariosListaContato() {
        for (Usuario u : StaticLista.getListaGlobalUsuario()) {
            listaContato.add(u);
        }
    }

    @FXML
    private VBox principal;
    @FXML
    private HTMLEditor htmlEditor;
    @FXML
    private TextField txtPara;
    @FXML
    private TextField txtDe;
    @FXML
    private TextField txtAssunto;
    @FXML
    private ImageView imgAddContato;

    private static Stage stage;
    private Collection<? extends Action> actions = Arrays.asList(
            new DummyAction("", imgEnviar, "enviar"),
            new DummyAction("", imgAnexo, "anexo"),
            new DummyAction("   A   ", null, "format"),
            new DummyAction("", imgAddImg, "img")
    //            new ActionGroup("Group 1", image, new DummyAction("Action 1.1", image),
    //                    new DummyAction("Action 1.2")),
    //            new ActionGroup("Group 2", image, new DummyAction("Action 2.1"),
    //                    ActionUtils.ACTION_SEPARATOR,
    //                    new ActionGroup("Action 2.2", new DummyAction("Action 2.2.1"),
    //                            new DummyAction("Action 2.2.2")),
    //                    new DummyAction("Action 2.3")),
    //            ActionUtils.ACTION_SEPARATOR,
    //            new DummyAction("Action 3", image),
    //            new ActionGroup("Group 4", image, new DummyAction("Action 4.1", image),
    //                    new DummyAction("Action 4.2"))
    );

//    private static final ImageView imgFormatacao = new ImageView(new Image("/image/formatacao.png"));
    private static final ImageView imgEnviar = new ImageView(new Image("/image/enviar.png"));
    private static final ImageView imgAnexo = new ImageView(new Image("image/anexo.png"));
    private static final ImageView imgAddImg = new ImageView(new Image("/image/addImg.png"));

    private FerramentaEmail ferramentaEmail;
    private UtilEmail utilEmail;
    private static PopOver popOver;
    private DoubleProperty masterArrowSize;
    private DoubleProperty masterArrowIndent;
    private DoubleProperty masterCornerRadius;
    private ObjectProperty<PopOver.ArrowLocation> masterArrowLocation;
    private BooleanProperty masterHeaderAlwaysVisible;
    private double targetX;
    private double targetY;
    private CheckBox autoPosition;
    private ObservableList<String> listaGrupo;
    private ObservableList<Object> listaContato;
    private Mensagem mensagem;

    @FXML
    private void onMouseEnteredImgAddContato() {
        imgAddContato.setVisible(true);
    }

    @FXML
    private void onMouseExitedImgAddContato() {
        imgAddContato.setVisible(false);
    }

    @FXML
    void onMouseClickedImgAddContato(MouseEvent evt) {
        if (popOver != null && popOver.isShowing()) {
            popOver.hide(Duration.ZERO);
        }

        popOver = createPopOver();
//        System.out.println("(popOver.getWidth() / 2) " + popOver.getContentNode().prefWidth(-1));

        targetX = imgAddContato.getLayoutX() + stage.getX() - (popOver.getContentNode().prefWidth(-1) / 2);
        targetY = imgAddContato.getLayoutY() + stage.getY() + 78;

        autoPosition = new CheckBox();
        autoPosition.setSelected(false);

        if (autoPosition.isSelected()) {
            popOver.show(stage);
        } else {
            popOver.show(stage, targetX, targetY);
        }
    }

    private PopOver createPopOver() {
        PopOver popOver = new PopOver();
        popOver.setDetachable(true);
        popOver.setDetached(false);
        popOver.arrowSizeProperty().bind(masterArrowSize);
        popOver.arrowIndentProperty().bind(masterArrowIndent);
        popOver.arrowLocationProperty().bind(masterArrowLocation);
        popOver.cornerRadiusProperty().bind(masterCornerRadius);
        popOver.headerAlwaysVisibleProperty().bind(masterHeaderAlwaysVisible);

//        Effect effect = 
        DropShadow ds = new DropShadow();
        ds.setOffsetY(3.0);
        ds.setOffsetX(3.0);
        ds.setColor(Color.GRAY);
        popOver.setContentNode(construirPopOver());
        popOver.setTitle("Adicionar contato");
//        popOver.getContentNode().setEffect(ds);
        return popOver;
    }

    private Group construirPopOver() {
        Group g = new Group();
        TextField txtBuscar = new TextField("");
        Insets i = new Insets(0, 0, 0, 0);

        VBox vbPrincipal = new VBox();
        vbPrincipal.getChildren().add(txtBuscar);

        HBox hbParte2 = new HBox();
        VBox vbListarPor = new VBox(5);
        vbListarPor.setPadding(i);
        ScrollPane sp1 = new ScrollPane();
        vbListarPor.setStyle("-fx-background-color: lightgray;");
//                + "-fx-border-color: gray;");
        ListView lv = new ListView();
        lv.setFocusTraversable(false);
        lv.setPrefWidth(100);
        lv.getStylesheets().add(this.getClass().getResource("/styles/listaGrupo.css").toExternalForm());
        lv.setItems(listaGrupo);
        lv.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                txtBuscar.setText("");
                listaContato.clear();
                if (lv.getSelectionModel().getSelectedItem() == null) {
                    addAllListaContato();
                } else {
                    if (lv.getSelectionModel().getSelectedItem().toString().equals("Todos")) {
                        addAllListaContato();
                    } else if (lv.getSelectionModel().getSelectedItem().toString().equals("Empresa")) {
                        addEmpresasListaContato();
                    } else if (lv.getSelectionModel().getSelectedItem().toString().equals("Usuário")) {
                        addUsuariosListaContato();
                    }
                }
            }
        });
        vbListarPor.getChildren().addAll(lv);
//        vbListarPor.setPrefSize(100, 30);
        sp1.setContent(vbListarPor);

        VBox vbListarContatos = new VBox(5);
        vbListarContatos.setPadding(i);
        ScrollPane sp2 = new ScrollPane();
        vbListarContatos.setStyle("-fx-background-color: transparent");

        ListView lv2 = new ListView();
        lv2.setFocusTraversable(false);
        lv2.setPrefWidth(200);
        lv2.setBorder(Border.EMPTY);
        lv2.getStylesheets().add(this.getClass().getResource("/styles/listaContato.css").toExternalForm());
        lv2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (lv2.getSelectionModel().getSelectedItem() instanceof Usuario) {
                    Usuario u = (Usuario) lv2.getSelectionModel().getSelectedItem();
                    txtPara.setText(txtPara.getText() + u.getEmail() + "; ");
                } else if (lv2.getSelectionModel().getSelectedItem() instanceof Empresa) {
                    Empresa e = (Empresa) lv2.getSelectionModel().getSelectedItem();
                    for (Contato c : StaticLista.getListaGlobalContato()) {
                        if (c.getIdEmpresa().getId().equals(e.getId())) {
                            txtPara.setText(txtPara.getText() + c.getEmail() + "; ");
                        }
                    }
                }
            }
        });
        lv2.setItems(listaContato);

        txtBuscar.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
//                event.getText().toLowerCase()
                ObservableList<Object> novaLista = FXCollections.observableArrayList();
                if ((txtBuscar.getText().toLowerCase() + event.getText().toLowerCase()).equals("")) {
                    listaContato.clear();
                    if (lv.getSelectionModel().getSelectedItem() == null) {
                        addAllListaContato();
                    } else {
                        if (lv.getSelectionModel().getSelectedItem().toString().equals("Todos")) {
                            addAllListaContato();
                        } else if (lv.getSelectionModel().getSelectedItem().toString().equals("Empresa")) {
                            addEmpresasListaContato();
                        } else if (lv.getSelectionModel().getSelectedItem().toString().equals("Usuário")) {
                            addUsuariosListaContato();
                        } else {
                            addAllListaContato();
                        }
                    }
                } else {
                    for (Object o : lv2.getItems()) {
                        if (o instanceof Usuario) {
                            Usuario u = (Usuario) o;
                            if (u.getNome().toLowerCase().contains(txtBuscar.getText().toLowerCase() + event.getText().toLowerCase())) {
                                novaLista.add(o);
                            }
                        } else if (o instanceof Empresa) {
                            Empresa e = (Empresa) o;
                            if (e.getDescricao().toLowerCase().contains(txtBuscar.getText().toLowerCase() + event.getText().toLowerCase())) {
                                novaLista.add(o);
                            }
                        }
                    }
                    listaContato.clear();
                    listaContato = novaLista;
                }
                lv2.setItems(listaContato);
            }
        });

        vbListarContatos.getChildren().add(lv2);
        sp2.setPrefHeight(300);
        vbListarContatos.setPrefHeight(300);
        sp2.setContent(vbListarContatos);

        hbParte2.getChildren().add(vbListarPor);
        hbParte2.getChildren().add(vbListarContatos);
        vbPrincipal.getChildren().add(hbParte2);

        g.getChildren().add(vbPrincipal);
        return g;
    }

    private void hideHtmlEditor() {
        htmlEditor.lookup(".top-toolbar").setManaged(false);
        htmlEditor.lookup(".top-toolbar").setVisible(false);

        htmlEditor.lookup(".bottom-toolbar").setManaged(false);
        htmlEditor.lookup(".bottom-toolbar").setVisible(false);
    }

    private void showHtmlEditor() {
        htmlEditor.lookup(".top-toolbar").setManaged(true);
        htmlEditor.lookup(".top-toolbar").setVisible(true);

        htmlEditor.lookup(".bottom-toolbar").setManaged(true);
        htmlEditor.lookup(".bottom-toolbar").setVisible(true);
    }

    private boolean isHideHtmlEditor() {
        if (htmlEditor.lookup(".bottom-toolbar").isManaged()) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        FXMLLoader rootLoader = new FXMLLoader();
        rootLoader.setLocation(MainApp.class.getResource(EnumCaminho.Email.getCaminho()));
        VBox layout = (VBox) rootLoader.load();
//        stage.initStyle(StageStyle.UNDECORATED);
        principal = layout;
//        layout.getChildren().add(ActionUtils.createToolBar(null, ActionUtils.ActionTextBehavior.SHOW));
//        stage.setResizable(false);
        Scene scene = new Scene(layout);
//        scene.getStylesheets().add(this.getClass().getResource("/styles/list.css").toExternalForm());
        stage.setTitle("TITULO");
        stage.setScene(scene);
        stage.show();
        stage.toFront();
//        initialize();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setPrincipal(VBox layout) {
        principal = layout;
    }

    class DummyAction extends Action {

//        private UtilEmail utilEmail;
        private String id;

        public DummyAction(String name, Node image, String id) {
            super(name);
            setGraphic(image);
            setStyle("-fx-font: 18px Arial");
            setId(id);
//            setEventHandler(ae -> String.format("Action '%s' is executed", getText()));
            if (id.equals("format")) {
                setEventHandler(ae -> {
                    if (isHideHtmlEditor()) {
                        hideHtmlEditor();
                    } else {
                        showHtmlEditor();
                    }
                });
            } else if (id.equals("anexo")) {
                setEventHandler(ae -> {
                    FileChooser fileChooser = new FileChooser();
                    File file = fileChooser.showOpenDialog(stage);
                    utilEmail.anexar(file.getAbsolutePath());
//                    System.out.println("file " + file.getAbsolutePath());
                    mensagem.informacao(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), "Arquivo anexado! " + file.getName());
                });
            } else if (id.equals("enviar")) {
                setEventHandler(ae -> {
                    try {

                        ObservableList<Object> contatos = FXCollections.observableArrayList();
                        for (String split : txtPara.getText().split(";")) {
                            contatos.add(split.trim());
//                        System.out.println("split " + split.trim());
                        }
                        utilEmail.enviarEmail(ferramentaEmail, htmlEditor.getHtmlText(), contatos, txtAssunto.getText());
                        mensagem.informacao(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), "E-mail enviado com sucesso!");
                        stage.close();
                    } catch (Exception e) {
                        mensagem.erro(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), "Erro ao tentar enviar e-mail", e);
                        e.printStackTrace();
                    }
                });
            } else if (id.equals("img")) {
                setEventHandler(ae -> {
                    try {

                        FileChooser fileChooser = new FileChooser();
                        File file = fileChooser.showOpenDialog(stage);
//                    htmlEditor.setHtmlText(
//                            "<img src=\""
//                            + "file:" + file.getAbsolutePath()
//                            //                            + getClass().getResource("/image/iconePAFECF.png")
//                            + "\"  >"
//                    //                            + "\" width=\"32\" height=\"32\" >"
//                    );
                        WebView webView = (WebView) htmlEditor.lookup("WebView");
                        WebEngine engine = webView.getEngine();
                        String jsCodeInsertHtml = "function insertHtmlAtCursor(html) {\n"
                                + "    var range, node;\n"
                                + "    if (window.getSelection && window.getSelection().getRangeAt) {\n"
                                + "        range = window.getSelection().getRangeAt(0);\n"
                                + "        node = range.createContextualFragment(html);\n"
                                + "        range.insertNode(node);\n"
                                + "    } else if (document.selection && document.selection.createRange) {\n"
                                + "        document.selection.createRange().pasteHTML(html);\n"
                                + "    }\n"
                                + "}insertHtmlAtCursor('####html####')";
                        String imagem
                                = "<img src=\""
                                + "file:" + file.getAbsolutePath()
                                //                            + getClass().getResource("/image/iconePAFECF.png")
                                + "\"  >";
                        engine.executeScript(jsCodeInsertHtml.
                                replace("####html####",
                                        escapeJavaStyleString(imagem, true, true)));
                        mensagem.informacao(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), "Imagem add " + file.getName());
                    } catch (Exception e) {
                        mensagem.erro(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), "Imagem não add", e);
                        e.printStackTrace();
                    }
                });
            }
        }

        private String hex(int i) {
            return Integer.toHexString(i);
        }

        private String escapeJavaStyleString(String str, boolean escapeSingleQuote, boolean escapeForwardSlash) {
            StringBuilder out = new StringBuilder("");
            if (str == null) {
                return null;
            }
            int sz;
            sz = str.length();
            for (int i = 0; i < sz; i++) {
                char ch = str.charAt(i);

                // handle unicode
                if (ch > 0xfff) {
                    out.append("\\u").append(hex(ch));
                } else if (ch > 0xff) {
                    out.append("\\u0").append(hex(ch));
                } else if (ch > 0x7f) {
                    out.append("\\u00").append(hex(ch));
                } else if (ch < 32) {
                    switch (ch) {
                        case '\b':
                            out.append('\\');
                            out.append('b');
                            break;
                        case '\n':
                            out.append('\\');
                            out.append('n');
                            break;
                        case '\t':
                            out.append('\\');
                            out.append('t');
                            break;
                        case '\f':
                            out.append('\\');
                            out.append('f');
                            break;
                        case '\r':
                            out.append('\\');
                            out.append('r');
                            break;
                        default:
                            if (ch > 0xf) {
                                out.append("\\u00").append(hex(ch));
                            } else {
                                out.append("\\u000").append(hex(ch));
                            }
                            break;
                    }
                } else {
                    switch (ch) {
                        case '\'':
                            if (escapeSingleQuote) {
                                out.append('\\');
                            }
                            out.append('\'');
                            break;
                        case '"':
                            out.append('\\');
                            out.append('"');
                            break;
                        case '\\':
                            out.append('\\');
                            out.append('\\');
                            break;
                        case '/':
                            if (escapeForwardSlash) {
                                out.append('\\');
                            }
                            out.append('/');
                            break;
                        default:
                            out.append(ch);
                            break;
                    }
                }
            }
            return out.toString();
        }

        public DummyAction(String name) {
            super(name);
            setEventHandler(ae -> System.out.println("awdwd" + name));
        }

        @Override
        public String toString() {
            return getText();
        }

        private void onActionToolBar() {
            utilEmail.enviaEmailSimples(null, "Mensagem", "Assunto");
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

    }
}
