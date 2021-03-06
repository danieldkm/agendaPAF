package com.unifil.agendapaf.view.controller;

import com.unifil.agendapaf.SceneManager;
import com.unifil.agendapaf.model.xml.LaudoFerramenta;
import com.unifil.agendapaf.util.MaskFieldUtil;
import com.unifil.agendapaf.util.mensagem.Dialogos;
import com.unifil.agendapaf.util.UtilFile;
import com.unifil.agendapaf.view.util.enums.EnumMensagem;
import java.io.File;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author danielmorita
 */
public class FerramentaLaudoController {

    /**
     * Initializes the controller class.
     */
    @FXML
    public void initialize() {
        //setar o titledPane que irá iniciar/exibir
        accordion.setExpandedPane(titledPane);

        if (utilXml == null) {
            utilXml = new UtilFile();
        }
        if (laudoFerramenta != null) {
            preencherLaudoFerramenta();
        } else {
            try {
                laudoFerramenta = LaudoFerramenta.getInstance();
                laudoFerramenta = (LaudoFerramenta) utilXml.unmarshalFromFile(LaudoFerramenta.class, "xml/LaudoFerramenta.xml");
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("java.io.FileNotFoundException: xml/LaudoFerramenta.xml (No such file or directory)");
            }
            preencherLaudoFerramenta();
        }

        MaskFieldUtil.removeAllSimbolsExceptCaracterAndNumber(txtIE, 30);
        MaskFieldUtil.removeAllSimbolsExceptNumber(txtCNPJ, 20);
        MaskFieldUtil.removeAllSimbolsExceptNumber(txtETCPF, 20);
        MaskFieldUtil.removeAllSimbolsExceptNumber(txtARCPF, 20);
        MaskFieldUtil.numericField(txtNumero);
    }

    @FXML
    private Label lblAgenda;

    @FXML
    private TextField txtRazaoSocial;

    @FXML
    private TextField txtCNPJ;

    @FXML
    private TextField txtIE;

    @FXML
    private TextField txtLogradouro;

    @FXML
    private TextField txtNumero;

    @FXML
    private TextField txtComplemento;

    @FXML
    private TextField txtBairro;

    @FXML
    private TextField txtCEP;

    @FXML
    private TextField txtUF;

    @FXML
    private TextField txtCidade;

    @FXML
    private TextField txtETNome;

    @FXML
    private TextField txtETCPF;

    @FXML
    private TextField txtETCargo;

    @FXML
    private TextField txtARNome;

    @FXML
    private TextField txtARCPF;

    @FXML
    private TextField txtARCargo;

    @FXML
    private TextField txtVersaoER;

    @FXML
    private Button btnSalvar;

    @FXML
    private Button btnCancelar;

    @FXML
    private VBox main;

    @FXML
    private Accordion accordion;

    @FXML
    private TitledPane titledPane;

    private Stage stage;
    private UtilFile utilXml;
    private LaudoFerramenta laudoFerramenta;

    @FXML
    void onActionBtnCacelar(ActionEvent event) {
        stage.close();
    }

    @FXML
    void onActionBtnSalvar(ActionEvent event) {
        Dialogos d = new Dialogos(stage);
        Optional<ButtonType> r = d.confirmacao(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.CertezaSalvar.getMensagem());
        if (r.get() == ButtonType.OK) {
            File criarLaudoComplementar = new File("xml/LaudoFerramenta.xml");
            utilXml.salvarArquivo(criarLaudoComplementar, utilXml.marshal(laudoFerramenta));
            LaudoController lc = SceneManager.getInstance().getLaudoController();
            lc.bindComponenetsWithLaudoFerramenta(laudoFerramenta);
            stage.close();
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setMain(VBox vBox) {
        this.main = vBox;
    }

    private void preencherLaudoFerramenta() {
        txtARCPF.textProperty().bindBidirectional(laudoFerramenta.getAprovadorRelatorio().cpfProperty());
        txtARCargo.textProperty().bindBidirectional(laudoFerramenta.getAprovadorRelatorio().cargoProperty());
        txtARNome.textProperty().bindBidirectional(laudoFerramenta.getAprovadorRelatorio().nomeProperty());
        txtETCPF.textProperty().bindBidirectional(laudoFerramenta.getExecutorTestes().cpfProperty());
        txtETCargo.textProperty().bindBidirectional(laudoFerramenta.getExecutorTestes().cargoProperty());
        txtETNome.textProperty().bindBidirectional(laudoFerramenta.getExecutorTestes().nomeProperty());
        txtBairro.textProperty().bindBidirectional(laudoFerramenta.getEndereco().bairroProperty());
        txtCEP.textProperty().bindBidirectional(laudoFerramenta.getEndereco().cepProperty());
        txtCidade.textProperty().bindBidirectional(laudoFerramenta.getEndereco().getIdCidade().nomeProperty());
        txtUF.textProperty().bindBidirectional(laudoFerramenta.getEndereco().getIdCidade().ufProperty());
        txtComplemento.textProperty().bindBidirectional(laudoFerramenta.getEndereco().complementoProperty());
        txtLogradouro.textProperty().bindBidirectional(laudoFerramenta.getEndereco().logradouroProperty());
        txtNumero.textProperty().bindBidirectional(laudoFerramenta.getEndereco().numeroProperty());
        txtCNPJ.textProperty().bindBidirectional(laudoFerramenta.getEmpresa().cnpjProperty());
        txtRazaoSocial.textProperty().bindBidirectional(laudoFerramenta.getEmpresa().descricaoProperty());
        txtIE.textProperty().bindBidirectional(laudoFerramenta.getEmpresa().inscricaoEstadualProperty());
        txtVersaoER.textProperty().bindBidirectional(laudoFerramenta.versaoERProperty());
    }

}
