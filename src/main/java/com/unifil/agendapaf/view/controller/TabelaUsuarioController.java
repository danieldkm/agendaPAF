package com.unifil.agendapaf.view.controller;

import com.unifil.agendapaf.controller.Controller;
import com.unifil.agendapaf.model.Usuario;
import com.unifil.agendapaf.statics.StaticLista;
import com.unifil.agendapaf.statics.StaticObject;
import com.unifil.agendapaf.util.UtilDialog;
import com.unifil.agendapaf.view.util.enums.EnumMensagem;
import com.unifil.agendapaf.util.RunAnotherApp;
import com.unifil.agendapaf.view.util.enums.EnumCaminho;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TabelaUsuarioController extends FXMLController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            utilDialog = new UtilDialog();
            mainTbUsuario.setOnKeyReleased(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent t) {
                    if (t.getCode() == KeyCode.ESCAPE) {
                        stage.close();
                    }
                }
            });
            popularTabela();

        } catch (Exception e) {
            e.printStackTrace();
            utilDialog.criarDialogException(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), "Erro ao inicializar tabela usuario", e, "Exception:");
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            stage = primaryStage;
            mainTbUsuario = FXMLLoader.load(EmpresaController.class.getResource(EnumCaminho.TabelaUsuario.getCaminho()));
            Scene scene = new Scene(mainTbUsuario);
            stage.setScene(scene);
            stage.setTitle("Buscar Usuário");
//        stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
            stage.toFront();
//            stage.getIcons().add(Controller.icoPAF);
        } catch (Exception e) {
            e.printStackTrace();
            utilDialog.criarDialogException(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), "Erro no start tabela usuario", e, "Exception:");
        }
    }

    private ObservableList<Usuario> listaTbECF = FXCollections.observableArrayList();

    @FXML
    private TableView<Usuario> tbUsuario;
//    @FXML
//    private TableColumn<Usuario, Integer> tbcCodUsuario;
//    @FXML
//    private TableColumn<Usuario, String> tbcUsuario;
//    @FXML
//    private TableColumn<Usuario, String> tbcLogin;
//    @FXML
//    private TableColumn<Usuario, String> tbcDataCadastro;
//    @FXML
//    private TableColumn<Usuario, String> tcTipo;
    @FXML
    private TextField txtBuscar;
    @FXML
    private BorderPane mainTbUsuario;

    private static Stage stage;
    private UtilDialog utilDialog;

    @FXML
    private void onKeyPressdTxtBuscar(KeyEvent e) {
        listaTbECF.clear();
        for (Usuario usuario : StaticLista.getListaGlobalUsuario()) {
            if (usuario.getNome().toLowerCase().contains(txtBuscar.getText().toLowerCase() + e.getText().toLowerCase())) {
                listaTbECF.add(usuario);
            }
        }
        tbUsuario.getItems().setAll(listaTbECF);
    }

    private void popularTabela() {
////        tbcCodUsuario.setCellValueFactory(new PropertyValueFactory<Usuario, Integer>("codUsuario"));
////        tbcUsuario.setCellValueFactory(new PropertyValueFactory<Usuario, String>("nomeUsuario"));
////        tbcLogin.setCellValueFactory(new PropertyValueFactory<Usuario, String>("loginUsuario"));
////        tbcDataCadastro.setCellValueFactory(new PropertyValueFactory<Usuario, String>("senhaUsuario"));
////        tcTipo.setCellValueFactory(new PropertyValueFactory<Usuario, String>("tipoUsuario"));
//        listaTbECF = Controller.getUsuarios();
        tbUsuario.getItems().setAll(StaticLista.getListaGlobalUsuario());
    }

    @FXML
    private void onMouseClickedTabelaUsuario(MouseEvent event) {
        if (event.getClickCount() == 2) {
            StaticObject.setUsuarioEncontrada(tbUsuario.getSelectionModel().getSelectedItem());
            RunAnotherApp.runAnotherApp(UsuarioController.class);
            stage.close();
        }
    }

}
