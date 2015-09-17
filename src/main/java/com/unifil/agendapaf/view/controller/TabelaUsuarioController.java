package com.unifil.agendapaf.view.controller;

import com.unifil.agendapaf.SceneManager;
import com.unifil.agendapaf.model.Usuario;
import com.unifil.agendapaf.statics.StaticLista;
import com.unifil.agendapaf.util.UtilDialog;
import com.unifil.agendapaf.view.util.enums.EnumMensagem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class TabelaUsuarioController {

    @FXML
    public void initialize() {
        try {
            popularTabela();

        } catch (Exception e) {
            e.printStackTrace();
            UtilDialog.criarDialogException(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), "Erro ao inicializar tabela usuario", e, "Exception:");
        }
    }

    private ObservableList<Usuario> listaTbECF = FXCollections.observableArrayList();

    @FXML
    private TableView<Usuario> tbUsuario;
    @FXML
    private TextField txtBuscar;
    @FXML
    private BorderPane mainTbUsuario;

    private Stage stage;

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
        tbUsuario.getItems().setAll(StaticLista.getListaGlobalUsuario());
    }

    @FXML
    private void onMouseClickedTabelaUsuario(MouseEvent event) {
        if (event.getClickCount() == 2) {
            SceneManager.getInstance().setUsuarioEncontrada(tbUsuario.getSelectionModel().getSelectedItem());
            SceneManager.getInstance().showUsuario();
            stage.close();
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setMainTbUsuario(BorderPane mainTbUsuario) {
        this.mainTbUsuario = mainTbUsuario;
    }

}
