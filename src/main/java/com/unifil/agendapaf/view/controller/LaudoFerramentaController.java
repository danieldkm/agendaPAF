package com.unifil.agendapaf.view.controller;

import com.unifil.agendapaf.model.xml.Empresa;
import com.unifil.agendapaf.model.xml.Endereco;
import com.unifil.agendapaf.model.xml.LaudoFerramenta;
import com.unifil.agendapaf.model.xml.Pessoa;
import com.unifil.agendapaf.util.MaskFieldUtil;
import com.unifil.agendapaf.util.UtilDialog;
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
public class LaudoFerramentaController {

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
        try {
            laudoFerramenta = (LaudoFerramenta) utilXml.unmarshalFromFile(LaudoFerramenta.class, utilXml.getDiretorioInicial() + "LaudoFerramenta.xml");
        } catch (Exception e) {
//            e.printStackTrace();
            System.err.println("java.io.FileNotFoundException: xml/LaudoFerramenta.xml (No such file or directory)");
        }
        if (laudoFerramenta != null) {
            preencherLaudoFerramenta();
        }

        MaskFieldUtil.removeAllSimbolsExceptCaracterAndNumber(txtIE);
        MaskFieldUtil.removeAllSimbolsExceptNumber(txtCNPJ);
        MaskFieldUtil.removeAllSimbolsExceptNumber(txtETCPF);
        MaskFieldUtil.removeAllSimbolsExceptNumber(txtARCPF);
        MaskFieldUtil.numericField(txtNumero);
    }

    @FXML
    void onActionBtnCacelar(ActionEvent event) {
        stage.close();
    }

    @FXML
    void onActionBtnSalvar(ActionEvent event) {
        Optional<ButtonType> r = UtilDialog.criarDialogConfirmacao(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.CertezaSalvar.getMensagem());
        if (r.get() == ButtonType.OK) {
            if (laudoFerramenta == null) {
                laudoFerramenta = new LaudoFerramenta();
            }
            Pessoa ar = new Pessoa();
            ar.setCpf(txtARCPF.getText());
            ar.setCargo(txtARCargo.getText());
            ar.setNome(txtARNome.getText());
            laudoFerramenta.setAprovadorRelatorio(ar);

            Pessoa et = new Pessoa();
            et.setCpf(txtETCPF.getText());
            et.setCargo(txtETCargo.getText());
            et.setNome(txtETNome.getText());
            laudoFerramenta.setExecutorTestes(et);

            Endereco endereco = new Endereco();
            endereco.setBairro(txtBairro.getText());
            endereco.setCep(txtCEP.getText());
            endereco.setCidade(txtCidade.getText());
            endereco.setUf(txtUF.getText());
            endereco.setComplemento(txtComplemento.getText());
            endereco.setLogradouro(txtLogradouro.getText());
            endereco.setNumero(txtNumero.getText());
            laudoFerramenta.setEndereco(endereco);

            Empresa empresa = new Empresa();
            empresa.setCnpj(txtCNPJ.getText());
            empresa.setRazaoSocial(txtRazaoSocial.getText());
            empresa.setIe(txtIE.getText());
            empresa.setVersaoER(txtVersaoER.getText());
            laudoFerramenta.setEmpresa(empresa);

            File criarLaudoComplementar = new File(utilXml.getDiretorioInicial() + "LaudoFerramenta.xml");
            utilXml.salvarArquivo(criarLaudoComplementar, utilXml.marshal(laudoFerramenta));
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
        txtARCPF.setText(laudoFerramenta.getAprovadorRelatorio().getCpf());
        txtARCargo.setText(laudoFerramenta.getAprovadorRelatorio().getCargo());
        txtARNome.setText(laudoFerramenta.getAprovadorRelatorio().getNome());
        txtETCPF.setText(laudoFerramenta.getExecutorTestes().getCpf());
        txtETCargo.setText(laudoFerramenta.getExecutorTestes().getCargo());
        txtETNome.setText(laudoFerramenta.getExecutorTestes().getNome());
        txtBairro.setText(laudoFerramenta.getEndereco().getBairro());
        txtCEP.setText(laudoFerramenta.getEndereco().getCep());
        txtCidade.setText(laudoFerramenta.getEndereco().getCidade());
        txtUF.setText(laudoFerramenta.getEndereco().getUf());
        txtComplemento.setText(laudoFerramenta.getEndereco().getComplemento());
        txtLogradouro.setText(laudoFerramenta.getEndereco().getLogradouro());
        txtNumero.setText(laudoFerramenta.getEndereco().getNumero());
        txtCNPJ.setText(laudoFerramenta.getEmpresa().getCnpj());
        txtRazaoSocial.setText(laudoFerramenta.getEmpresa().getRazaoSocial());
        txtIE.setText(laudoFerramenta.getEmpresa().getIe());
        txtVersaoER.setText(laudoFerramenta.getEmpresa().getVersaoER());
    }
}
