package com.unifil.agendapaf.view.controller;

import com.unifil.agendapaf.SceneManager;
import com.unifil.agendapaf.model.Financeiro;
import com.unifil.agendapaf.model.Usuario;
import com.unifil.agendapaf.statics.StaticLista;
import com.unifil.agendapaf.util.UtilConverter;
import com.unifil.agendapaf.util.mensagem.Dialogos;
import com.unifil.agendapaf.util.mensagem.Mensagem;
import com.unifil.agendapaf.view.util.enums.EnumMensagem;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
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
            mensagem = new Mensagem(stage);
            popularTabela();
            tbcDataCadastro.setCellFactory(column -> {
                return new TableCell<Usuario, LocalDate>() {
                    @Override
                    protected void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null || empty) {
                            setText(null);
                            setStyle("");
                        } else {
                            // Format date.
                            setText(UtilConverter.converterDataToFormat(UtilConverter.converterLocalDateToUtilDate(item), "dd/MM/yyyy"));
                        }
                    }
                };
            });
        } catch (Exception e) {
            e.printStackTrace();
            mensagem.erro(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), "Erro ao inicializar tabela usuario", e);
        }
    }

    private ObservableList<Usuario> listaTbECF = FXCollections.observableArrayList();

    @FXML
    private TableView<Usuario> tbUsuario;
    @FXML
    private TableColumn tbcDataCadastro;
    @FXML
    private TextField txtBuscar;
    @FXML
    private BorderPane mainTbUsuario;

    private Stage stage;
    private Mensagem mensagem;

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
