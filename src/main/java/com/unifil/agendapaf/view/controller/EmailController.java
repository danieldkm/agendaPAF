package com.unifil.agendapaf.view.controller;

import com.unifil.agendapaf.MainApp;
import com.unifil.agendapaf.model.Contato;
import com.unifil.agendapaf.model.Empresa;
import com.unifil.agendapaf.model.Usuario;
import com.unifil.agendapaf.model.email.Email;
import com.unifil.agendapaf.model.email.Emails;
import com.unifil.agendapaf.model.email.FerramentaEmail;
import com.unifil.agendapaf.statics.StaticLista;
import com.unifil.agendapaf.util.PopUp;
import com.unifil.agendapaf.util.Util;
import com.unifil.agendapaf.util.UtilConverter;
import com.unifil.agendapaf.util.UtilEmail;
import com.unifil.agendapaf.util.UtilFile;
import com.unifil.agendapaf.util.mensagem.Mensagem;
import com.unifil.agendapaf.view.util.enums.EnumCaminho;
import com.unifil.agendapaf.view.util.enums.EnumMensagem;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.apache.commons.mail.EmailAttachment;
import org.controlsfx.control.MaskerPane;
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
        popup = new PopUp(this);
        popup.criarPopUpForEmail();
        hideHtmlEditor();
        utilFile = new UtilFile();
        ferramentaEmail = (FerramentaEmail) utilFile.unmarshalFromFile(FerramentaEmail.class, EnumCaminho.DiretorioEmail.getCaminho() + EnumCaminho.XMLFerramentaEmail.getCaminho());
        txtDe.setText(ferramentaEmail.getEmail());
        utilFile.criarDiretorio(EnumCaminho.DiretorioEmailEnviadas.getCaminho() + "/" + ferramentaEmail.getEmail());
        iniciarComAssinatura();
        idImgView = 0;
        mensagem = new Mensagem(stage);
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

        utilEmail = new UtilEmail();
//        vbCampos.getChildren().add(hbAnexoPrincipal);
        vbVerticalPrincipal.getChildren().add(0, ActionUtils.createToolBar(actions, ActionUtils.ActionTextBehavior.SHOW));
//        //Realocar os pane para add Masker
        stackPane.getChildren().add(masker);
        masker.setVisible(false);

        addEmailEnviadasListView();
        lvEmailEnviados.onMousePressedProperty().set(m -> {
            if (m.isPrimaryButtonDown()) {
                popup.getPopUp().hide();
            } else if (m.isSecondaryButtonDown()) {
                popup.getPopUp().show(principal, m.getScreenX(), m.getScreenY());
            }
        });
//        principal.getChildren().remove(vbEmail);
//        principal.getChildren().remove(vbMais);
//        hbTemp = new HBox();
//        HBox.setHgrow(vbMais, Priority.ALWAYS);
//        HBox.setHgrow(vbEmail, Priority.ALWAYS);
//        hbTemp.getChildren().addAll(vbMais, vbEmail);
//        stackPane.getChildren().addAll(hbTemp, masker);
//        masker.setVisible(false);
//        HBox.setHgrow(stackPane, Priority.ALWAYS);
//        HBox saco = new HBox();
//        saco.getChildren().add(stackPane);
//        HBox.setHgrow(saco, Priority.ALWAYS);
//        principal.getChildren().add(saco);

//        principal.getChildren().remove(vbCampos);
//        principal.getChildren().remove(htmlEditor);
//        VBox vbTemp = new VBox();
//        vbTemp.getChildren().addAll(ActionUtils.createToolBar(actions, ActionUtils.ActionTextBehavior.SHOW), vbCampos, htmlEditor);
//        sp.getChildren().addAll(vbTemp, masker);
    }

    @FXML
    private AnchorPane principal;
    @FXML
    private StackPane stackPane;
    @FXML
    private HBox hbHrozintalPrincipal;
    @FXML
    private VBox vbVerticalPrincipal;
    @FXML
    private VBox vbEmail;
    @FXML
    private VBox vbMais;
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
    @FXML
    private VBox vbCampos;
    @FXML
    private Button btnExpandir;

    private ListView<VBox> lvEmailEnviados = new ListView();
    private Email email;

    private static Stage stage;
    private Collection<? extends Action> actions = Arrays.asList(
            new DummyAction("", imgNovoEmail, "novoEmail"),
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
    private static final ImageView imgNovoEmail = new ImageView(new Image("/image/novoEmail.png"));

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
    private double tamanhoAnexo = 0;
    private ProgressIndicator pi = new ProgressIndicator(tamanhoAnexo);
    private HBox hbAnexoPrincipal = new HBox(5);
    private VBox vbAnexos = new VBox(5);
    private Label lbTamanhoTotalAnexo = new Label();
    private int idImgView;
    private MaskerPane masker = new MaskerPane();
    private UtilFile utilFile;
    private PopUp popup;

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

    @FXML
    private void onActionEmailSalvo(ActionEvent event) {
        if (hbHrozintalPrincipal.getChildren().size() == 2) {
            addEmailEnviadasListView();
        } else {
            btnExpandir.setText(">");
            hbHrozintalPrincipal.getChildren().remove(0);
//            stage.setWidth(stage.getWidth() - 150);
        }
    }

    private void iniciarComAssinatura() {
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
//        com.unifil.agendapaf.model.email.Email teste = new com.unifil.agendapaf.model.email.Email();
//        teste.setAssunto("Assunto");
//        teste.setConteudo("conteudo");
//        teste.setDestinatario("destinatario");
//        UtilFile uf = new UtilFile();
//        FerramentaEmail fe = (FerramentaEmail) uf.unmarshalFromFile(FerramentaEmail.class, EnumCaminho.DiretorioEmail.getCaminho() + EnumCaminho.XMLFerramentaEmail.getCaminho());
//        teste.setRemente(fe);
//        uf.criarDiretorio(EnumCaminho.DiretorioEmailEnviadas.getCaminho() + "/" + fe.getEmail());
//        Emails e = new Emails();
//        e.getEmails().add(teste);
//        System.out.println("" + EnumCaminho.DiretorioEmailEnviadas.getCaminho() + "/" + fe.getEmail());
//        uf.salvarArquivo(new File(EnumCaminho.DiretorioEmailEnviadas.getCaminho() + "/" + fe.getEmail()), uf.marshal(e));

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        FXMLLoader rootLoader = new FXMLLoader();
        rootLoader.setLocation(MainApp.class.getResource(EnumCaminho.FXMLEmail.getCaminho()));
        AnchorPane layout = (AnchorPane) rootLoader.load();
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

    public void setPrincipal(AnchorPane layout) {
        principal = layout;
    }

    public void setEmail(Email email) {
        if (email != null) {
            txtAssunto.setText(email.getAssunto());
            for (String anexo : email.getAnexos().getAnexos()) {
                anexar(new File(anexo));
            }
        }
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

    private void addEmailEnviadasListView() {
        btnExpandir.setText("<");
        Emails emails = (Emails) utilFile.unmarshalFromFile(Emails.class, EnumCaminho.DiretorioEmailEnviadas.getCaminho() + "/" + ferramentaEmail.getEmail() + "/" + EnumCaminho.XMLEmails.getCaminho());
        lvEmailEnviados.getItems().clear();
        //Ordenar por data
//        Collections.sort(emails.getEmails(), new Comparator<Email>() {
//            @Override
//            public int compare(Email email1, Email email2) {
//                return email2.getData().compareTo(email1.getData());
//            }
//        });
        if (emails != null) {
            Util.ordenarEmailsPor(emails, "data", "desc");
            for (Email email : emails.getEmails()) {
                VBox vbox = new VBox();
                String reme = email.getRemente().getEmail();
                Label remetente = new Label(reme.substring(0, email.getRemente().getEmail().indexOf("@")) + " - " + UtilConverter.converterDataToFormat(UtilConverter.converterLocalDateTimeToUtilDate(email.getData()), "dd/MM/yyyy"));
                Label assunto = new Label(email.getAssunto());
                assunto.setStyle("-fx-text-fill: lightgray");
                Label conteudo = new Label(removerTagsHTML(email.getConteudo()));
                conteudo.setStyle("-fx-text-fill: lightgray");
                
                vbox.getChildren().addAll(remetente, assunto, conteudo);
                vbox.setId(email.getId() + "");
                lvEmailEnviados.getItems().add(vbox);
                if (hbHrozintalPrincipal.getChildren().size() == 2) {
                    hbHrozintalPrincipal.getChildren().add(0, lvEmailEnviados);
                    hbHrozintalPrincipal.setPrefWidth(hbHrozintalPrincipal.getWidth() + 100);
                }
            }
        }
    }

    private void removerAnexoFromView() {
        if (vbAnexos.getChildren().size() > 0) {
            vbAnexos.getChildren().clear();
            vbCampos.getChildren().remove(3);
            utilEmail.getAnexos().clear();
        }
    }

    public void popUpAbrir() {
        removerAnexoFromView();
        Emails emails = (Emails) utilFile.unmarshalFromFile(Emails.class, EnumCaminho.DiretorioEmailEnviadas.getCaminho() + "/" + ferramentaEmail.getEmail() + "/" + EnumCaminho.XMLEmails.getCaminho());
        for (Email email : emails.getEmails()) {
            if (lvEmailEnviados.getSelectionModel().getSelectedItem().getId().equals(email.getId() + "")) {
                txtPara.setText(email.getDestinatario());
                txtAssunto.setText(email.getAssunto());
                txtDe.setText(email.getRemente().getEmail());
                for (String anexo : email.getAnexos().getAnexos()) {
                    anexar(new File(anexo));
                }
                try {
                    htmlEditor.setHtmlText(email.getConteudo());
                } catch (Exception e) {
                    e.printStackTrace();
                    mensagem.erro(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), "Erro ao editar o html", e);
                }
            }
        }
    }

    private void anexar(File file) {
        try {
            if (hbAnexoPrincipal.getChildren().size() == 0) {
                lbTamanhoTotalAnexo.setText(tamanhoAnexo + "MB");
                hbAnexoPrincipal.getChildren().addAll(vbAnexos, pi, lbTamanhoTotalAnexo);
            }
            if (vbCampos.getChildren().size() == 3) {
                vbCampos.getChildren().add(hbAnexoPrincipal);
            }
            //retorna o icone do arquivo selecionado contido no file
            final javax.swing.JFileChooser fc = new javax.swing.JFileChooser();
            javax.swing.Icon icon = fc.getUI().getFileView(fc).getIcon(file);

            BufferedImage bufferedImage = new BufferedImage(
                    icon.getIconWidth(),
                    icon.getIconHeight(),
                    BufferedImage.TYPE_INT_ARGB
            );
            icon.paintIcon(null, bufferedImage.getGraphics(), 0, 0);
            Image fxImage = SwingFXUtils.toFXImage(
                    bufferedImage, null
            );
            ImageView imageView = new ImageView(fxImage);

            double bytes = file.length();
            double kilobytes = (bytes / 1024);
            final double megabytes = (kilobytes / 1024);
//                double gigabytes = (megabytes / 1024);
//                double terabytes = (gigabytes / 1024);
//                double petabytes = (terabytes / 1024);
//                double exabytes = (petabytes / 1024);
//                double zettabytes = (exabytes / 1024);
//                double yottabytes = (zettabytes / 1024);
            if (megabytes > 25) {
                mensagem.aviso(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), "Arquivo não pode ser maior que 25 MB");
            } else {
                //Add icone do arquivo, nome, tamanho e uma lixeira
                HBox hbTemp = new HBox(5);
                hbTemp.getChildren().add(imageView);
                hbTemp.getChildren().add(new Label(file.getName()));
                hbTemp.getChildren().add(new Label("(" + String.format("%.2f", megabytes) + "MB)"));
//                System.out.println("filetrash "+ fileTrash.getAbsolutePath());
                Image imageTrash = new Image("/image/trash.png");
                ImageView iv = new ImageView(imageTrash);
                hbTemp.setId(idImgView + "");
                iv.setOnMouseClicked(e -> {
                    //quando clica na imagem da lixeira toma a seguinte ação;
                    //remove o HBox que contém o anexo
                    vbAnexos.getChildren().remove(hbTemp);
                    //realterar a porcentagem do progresso diminuindo 
                    //conforme o tamanho do anexo a ser removido
                    double removeProgresso = recalcularIndicadorProgresso(megabytes);
                    tamanhoAnexo += -megabytes;
                    lbTamanhoTotalAnexo.setText(String.format("%.2f", tamanhoAnexo) + "MB");
                    pi.setProgress(pi.getProgress() - removeProgresso);
//                    System.out.println("vbAnexos.getChildren().size() " + vbAnexos.getChildren().size());
                    //caso não houver nenhum anexo remover o Hbox principal do anexo
                    if (vbAnexos.getChildren().size() == 0) {
                        vbCampos.getChildren().remove(hbAnexoPrincipal);
                    }
                    utilEmail.removerAnexo(hbTemp.getId());
                });
                hbTemp.getChildren().add(iv);
                vbAnexos.getChildren().addAll(hbTemp);
                DecimalFormat formato = new DecimalFormat("#.##");
                double tempMegabytes = Double.valueOf(formato.format(megabytes).replace(",", "."));
                tamanhoAnexo += tempMegabytes;
                double progresso = recalcularIndicadorProgresso(tamanhoAnexo);
//                System.out.println("progresso " + progresso);
                lbTamanhoTotalAnexo.setText(String.format("%.2f", tamanhoAnexo) + "/25MB");
                if (progresso > 1.0) {
                    mensagem.aviso(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), "Tamanho total do anexo excede o limite de 25 MB");
                } else {
                    pi.setProgress(progresso);
                }
                utilEmail.anexar(file.getAbsolutePath(), (idImgView++) + "", file.getName());
                mensagem.informacao(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), "Arquivo anexado! " + file.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
            mensagem.erro(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), "Erro ao tentar anexar", e);
        } finally {
        }
    }

    private double recalcularIndicadorProgresso(double progresso) {
        progresso = (progresso / (25 * 100)) * 100;
        return progresso;
    }

    private String removerTagsHTML(String htmlText) {
        Pattern pattern = Pattern.compile("<[^>]*>");
        Matcher matcher = pattern.matcher(htmlText);
        final StringBuffer sb = new StringBuffer(htmlText.length());
        while (matcher.find()) {
            matcher.appendReplacement(sb, " ");
        }
        matcher.appendTail(sb);
//        System.out.println(sb.toString().trim());
        return sb.toString();
    }

    private boolean validarCampos() {
        if (txtAssunto.getText().equals("")) {
            mensagem.aviso(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), "Assunto não informado");
            return false;
        }
        if (txtPara.getText().equals("")) {
            mensagem.aviso(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), "Destinatário não informado");
            return false;
        }
        return true;
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
            if (id.equals("novoEmail")) {
                setEventHandler(ae -> {
                    txtAssunto.setText("");
                    txtPara.setText("");
                    removerAnexoFromView();
                    iniciarComAssinatura();
                });

            } else if (id.equals("format")) {
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
                    anexar(file);
                });
            } else if (id.equals("enviar")) {
                setEventHandler(ae -> {
                    try {
                        if (validarCampos()) {
                            masker.setVisible(true);
                            ObservableList<Object> contatos = FXCollections.observableArrayList();
                            for (String split : txtPara.getText().split(";")) {
                                contatos.add(split.trim());
                            }
//                        utilFile.criarDiretorio(EnumCaminho.DiretorioEmailEnviadas.getCaminho() + "/" + ferramentaEmail.getEmail());
                            com.unifil.agendapaf.model.email.Email email = new com.unifil.agendapaf.model.email.Email();
                            email.setAssunto(txtAssunto.getText());
                            email.setDestinatario(txtPara.getText());
                            email.setRemente(ferramentaEmail);
                            email.setConteudo(htmlEditor.getHtmlText());
                            email.setData(LocalDateTime.now());
                            Emails emails = (Emails) utilFile.unmarshalFromFile(Emails.class, EnumCaminho.DiretorioEmailEnviadas.getCaminho() + "/" + ferramentaEmail.getEmail() + "/" + EnumCaminho.XMLEmails.getCaminho());

                            File direc = new File(EnumCaminho.DiretorioEmailEnviadas.getCaminho() + "/" + ferramentaEmail.getEmail() + "/" + EnumCaminho.XMLEmails.getCaminho());
                            String nomePasta = email.getData().toString().replace(":", "").replace(".", "");
                            utilFile.criarDiretorio(EnumCaminho.DiretorioEmailEnviadas.getCaminho() + "/" + ferramentaEmail.getEmail() + "/" + nomePasta);

                            for (EmailAttachment anexo : utilEmail.getAnexos()) {
//                            Anexo an = new Anexo();
                                File destino = new File(EnumCaminho.DiretorioEmailEnviadas.getCaminho() + "/" + ferramentaEmail.getEmail() + "/" + nomePasta + "/" + anexo.getName());
//                            an.setCaminho(destino.getAbsolutePath());
                                email.getAnexos().getAnexos().add(destino.getAbsolutePath());
                                utilFile.copiarArquivo(new File(anexo.getPath()), destino);
                            }

                            if (emails != null) {
                                Util.ordenarEmailsPor(emails, "id", "asc");
                                email.setId(emails.getEmails().get(emails.getEmails().size() - 1).getId() + 1);
                                emails.getEmails().add(email);
                            } else {
                                email.setId(1);
                                emails = new Emails();
                                emails.getEmails().add(email);
                            }
                            utilFile.salvarArquivo(direc, utilFile.marshal(emails));

                            addEmailEnviadasListView();
                            utilEmail.enviarEmail(ferramentaEmail, htmlEditor.getHtmlText(), contatos, txtAssunto.getText());
                            stage.close();
                            mensagem.informacao(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), "E-mail enviado com sucesso!");
                        }
                    } catch (Exception e) {
                        masker.setVisible(false);
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
//            setEventHandler(ae -> System.out.println("awdwd" + name));
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
