package com.unifil.agendapaf.view.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.unifil.agendapaf.SceneManager;
import com.unifil.agendapaf.dao.JPA;
import com.unifil.agendapaf.model.Contato;
import com.unifil.agendapaf.model.Empresa;
import com.unifil.agendapaf.model.Endereco;
import com.unifil.agendapaf.model.LaudoComplementar;
import com.unifil.agendapaf.model.Telefone;
import com.unifil.agendapaf.model.Usuario;
import com.unifil.agendapaf.model.aux.ParametroDocx;
import com.unifil.agendapaf.model.laudo.AplicacoesEspeciaisType;
import com.unifil.agendapaf.model.laudo.AprovacaoRelatorioType;
import com.unifil.agendapaf.model.laudo.ArquivoExecutavelComFuncaoType;
import com.unifil.agendapaf.model.laudo.ArquivoExecutavelPrincipalType;
import com.unifil.agendapaf.model.laudo.ArquivoExecutavelSemFuncaoType;
import com.unifil.agendapaf.model.laudo.ArquivoExecutavelType;
import com.unifil.agendapaf.model.laudo.ArquivoOutroType;
import com.unifil.agendapaf.model.laudo.ArquivoRelacaoExecutaveisType;
import com.unifil.agendapaf.model.laudo.ArquivosExecutaveisComFuncaoType;
import com.unifil.agendapaf.model.laudo.ArquivosExecutaveisSemFuncaoType;
import com.unifil.agendapaf.model.laudo.ArquivosExecutaveisType;
import com.unifil.agendapaf.model.laudo.ArquivosOutrosType;
import com.unifil.agendapaf.model.laudo.CaracteristicasPafType;
import com.unifil.agendapaf.model.laudo.ContatoType;
import com.unifil.agendapaf.model.laudo.DesenvolvedoraType;
import com.unifil.agendapaf.model.laudo.EcfAnaliseFuncionalType;
import com.unifil.agendapaf.model.laudo.EmissaoType;
import com.unifil.agendapaf.model.laudo.EmpresaDesenvolvedoraType;
import com.unifil.agendapaf.model.laudo.EnderecoType;
import com.unifil.agendapaf.model.laudo.EnvelopeSegurancaType;
import com.unifil.agendapaf.model.laudo.ExecucaoTestesType;
import com.unifil.agendapaf.model.laudo.FormaImpressaoType;
import com.unifil.agendapaf.model.laudo.IdentificacaoPafType;
import com.unifil.agendapaf.model.laudo.LaudoType;
import com.unifil.agendapaf.model.laudo.MarcaModeloType;
import com.unifil.agendapaf.model.laudo.MarcasModelosCompativeisType;
import com.unifil.agendapaf.model.laudo.MeioGeracaoArquivoSintegraEfdType;
import com.unifil.agendapaf.model.laudo.MensagemType;
import com.unifil.agendapaf.model.laudo.NaoConformidadeType;
import com.unifil.agendapaf.model.laudo.NaoConformidadesType;
import com.unifil.agendapaf.model.laudo.OtcType;
import com.unifil.agendapaf.model.laudo.PerfisRequisitosType;
import com.unifil.agendapaf.model.laudo.PeriodoAnaliseType;
import com.unifil.agendapaf.model.laudo.RequisitosExecutadosType;
import com.unifil.agendapaf.model.laudo.RoteiroAnaliseType;
import com.unifil.agendapaf.model.laudo.SistemaGestaoType;
import com.unifil.agendapaf.model.laudo.SistemaPedNfeType;
import com.unifil.agendapaf.model.laudo.SistemaPedType;
import com.unifil.agendapaf.model.laudo.SistemasGestaoType;
import com.unifil.agendapaf.model.laudo.SistemasPedNfeType;
import com.unifil.agendapaf.model.laudo.SistemasPedType;
import com.unifil.agendapaf.model.laudo.TratamentoInterrupcaoType;
import com.unifil.agendapaf.service.ContatoService;
import com.unifil.agendapaf.service.EnderecoService;
import com.unifil.agendapaf.service.TelefoneService;
import com.unifil.agendapaf.statics.StaticLista;
import com.unifil.agendapaf.util.GerarDocx;
import com.unifil.agendapaf.util.Json;
import com.unifil.agendapaf.util.MaskFieldUtil;
import com.unifil.agendapaf.util.UtilConverter;
import com.unifil.agendapaf.util.UtilDialog;
import com.unifil.agendapaf.util.UtilFile;
import com.unifil.agendapaf.util.UtilTexto;
import com.unifil.agendapaf.view.util.enums.EnumCaminho;
import com.unifil.agendapaf.view.util.enums.EnumMensagem;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.text.DateFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.HashMap;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Separator;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.controlsfx.control.CheckComboBox;
import org.controlsfx.dialog.Wizard;
import org.controlsfx.dialog.Wizard.LinearFlow;
import org.controlsfx.dialog.WizardPane;

/**
 * FXML Controller class
 *
 * @author danielmorita
 */
public class LaudoController {

    /**
     * Initializes the controller class.
     */
    @FXML
    public void initialize() {

        MaskFieldUtil.removeAllSimbolsExceptCaracterAndNumber(oTxtIE);
        MaskFieldUtil.removeAllSimbolsExceptCaracterAndNumber(dTxtIE);
        MaskFieldUtil.removeAllSimbolsExceptNumber(oTxtCNPJ);
        MaskFieldUtil.removeAllSimbolsExceptNumber(dTxtCNPJ);
        MaskFieldUtil.removeAllSimbolsExceptNumber(dTxtCPF);
        MaskFieldUtil.removeAllSimbolsExceptNumber(dTxtTelefone);
        MaskFieldUtil.removeAllSimbolsExceptNumber(oTxtCEP);
        MaskFieldUtil.removeAllSimbolsExceptNumber(dTxtCEP);
        MaskFieldUtil.removeAllSimbolsExceptNumber(sgTxtCNPJ);
        MaskFieldUtil.removeAllSimbolsExceptNumber(spTxtCNPJ);
        MaskFieldUtil.removeAllSimbolsExceptNumber(speTxtCNPJ);
        MaskFieldUtil.numericField(dTxtNumero);
        MaskFieldUtil.removeAllSimbolsExceptNumber(txtRg);
        MaskFieldUtil.removeAllSimbolsExceptNumber(txtCelular);
        MaskFieldUtil.removeAllSimbolsExceptNumber(txtFax);

        enableEditAllTable();

        txtNumeroLaudo.setOnKeyReleased(eventHabdle -> {
            txtNLaudo.setText(txtNumeroLaudo.getText());
        });

        cbResponsavelEnsaio.setItems(StaticLista.getListaGlobalUsuario());

        mensagem = new MensagemType();
        utilXml = new UtilFile();
        utilXml.listarArquivos(new File(EnumCaminho.ModeloDocxs.getCaminho()));
        files = utilXml.getDocs();

        paneCheckBox1.getChildren().add(cb1);
        paneCheckBox1.getChildren().add(cbSelecionarTodos);
        paneCheckBox2.getChildren().add(cb2);
        paneCheckBox2.getChildren().add(cbSelecionarTodos2);
        preencherCbMarca();
        carregarDiretorioXML();
    }

    @FXML
    private Tab sp;
    @FXML
    private Tab spe;
    @FXML
    private Tab ecfU;
    @FXML
    private Tab relacaoE;
    @FXML
    private ResourceBundle resources;
    @FXML
    private Button iBtnRemove;
    @FXML
    private URL location;
    @FXML
    private DatePicker oDtInicio;
    @FXML
    private CheckBox ckEspeciais1;
    @FXML
    private TextField fTxtLocal;
    @FXML
    private TextField iTxtMarca;
    @FXML
    private CheckBox ckEspeciais3;
    @FXML
    private CheckBox ckEspeciais2;
    @FXML
    private CheckBox ckEspeciais5;
    @FXML
    private CheckBox ckEspeciais4;
    @FXML
    private TextField sgTxtCNPJ;
    @FXML
    private CheckBox ckEspeciais7;
    @FXML
    private Button rBtnAdd;
    @FXML
    private Button rBtnRemove;
    @FXML
    private CheckBox ckEspeciais6;
    @FXML
    private CheckBox ckEspeciais9;
    @FXML
    private CheckBox ckEspeciais8;
    @FXML
    private TextField oTxtNumero;
    @FXML
    private TextField feTxtNome;
    @FXML
    private TextField feTxtNome2;
    @FXML
    private TextField oTxtIE;
    @FXML
    private TextField feTxtCPF;
    @FXML
    private TextField feTxtCPF2;
    @FXML
    private TextField oTxtComplemento;
    @FXML
    private TableView<ArquivoExecutavelType> sgTvTabela;
    @FXML
    private Button speBtnAdd;
    @FXML
    private TextField vTxtVersaoER;
    @FXML
    private TextField vTxtMes;
    @FXML
    private CheckBox ckForma1;
    @FXML
    private TextField oTxtVersaoER;
    @FXML
    private CheckBox ckForma2;
    @FXML
    private CheckBox ckForma3;
    @FXML
    private CheckBox ckForma4;
    @FXML
    private CheckBox ckProprio;
    @FXML
    private TextField vTxtAno;
    @FXML
    private TextField txtNumeroLaudo;
    @FXML
    private CheckBox ckTerceirizado;
    @FXML
    private CheckBox ckInterrupcao1;
    @FXML
    private CheckBox ckInterrupcao2;
    @FXML
    private CheckBox ckInterrupcao3;
    @FXML
    private TextField oTxtLogradouro;
    @FXML
    private CheckBox ckEmite2;
    @FXML
    private CheckBox ckEmite1;
    @FXML
    private TextField speTxtNomeSistema;
    @FXML
    private CheckBox ckGeracao3;
    @FXML
    private CheckBox ckGeracao2;
    @FXML
    private CheckBox ckGeracao1;
    @FXML
    private CheckBox fCkDeclaracao;
    @FXML
    private ComboBox<String> eCbMarca;
    @FXML
    private TableView<ArquivoExecutavelComFuncaoType> spTvTabela;
    @FXML
    private TextField iTxtMD5Outro;
    @FXML
    private TextField iTxtNomeComercial;
    @FXML
    private TableView<MarcaModeloType> eTvTabela;
    @FXML
    private Button btnSalvar;
    @FXML
    private TextField dTxtCEP;
    @FXML
    private CheckBox ckForma5;
    @FXML
    private CheckBox ckForma6;
    @FXML
    private CheckBox ckForma7;
    @FXML
    private TextField dTxtLogradouro;
    @FXML
    private CheckBox ckTipo2;
    @FXML
    private TextField dTxtBairro;
    @FXML
    private TextArea fTxtArea;
    @FXML
    private Button btnLimpar;
    @FXML
    private Button eBtnAdd;
    @FXML
    private ComboBox<String> rCbMarca;
    @FXML
    private TextField dTxtNumero;
    @FXML
    private TextField dTxtNome;
    @FXML
    private TextField dTxtRazaoSocial;
    @FXML
    private CheckBox ckComercializavel;
    @FXML
    private TextField oTxtCidade;
    @FXML
    private TextField iTxtNumero;
    @FXML
    private Button spBtnAdd;
    @FXML
    private TableView<ArquivoExecutavelComFuncaoType> speTvTabela;
    @FXML
    private TableView<MarcaModeloType> tTvTabela;
    @FXML
    private TextField iTxtVersao;
    @FXML
    private TableView<ArquivoExecutavelSemFuncaoType> iTvTabela;
    @FXML
    private CheckBox ckIntegracao2;
    @FXML
    private TextField dTxtUF;
    @FXML
    private CheckBox ckIntegracao1;
    @FXML
    private CheckBox ckIntegracao4;
    @FXML
    private CheckBox ckIntegracao3;
    @FXML
    private CheckBox ckTipo1;
    @FXML
    private CheckBox ckTipo3;
    @FXML
    private VBox main;
    @FXML
    private Button nBtnAdd;
    @FXML
    private TextField oTxtBairro;
    @FXML
    private TextField oTxtUF;
    @FXML
    private TextField dTxtComplemento;
    @FXML
    private DatePicker fDt;
    @FXML
    private Label lblAgenda;
    @FXML
    private TextField dTxtCPF;
    @FXML
    private DatePicker oDtFinal;
    @FXML
    private TextField dTxtResponsavelTestes;
    @FXML
    private TextField iTxtPrincipalExec;
    @FXML
    private TextField iTxtModelo;
    @FXML
    private HBox otxtLogradouro;
    @FXML
    private TextField oTxtRazaoSocial;
    @FXML
    private TextField iTxtRelacaoExec;
    @FXML
    private TextField spTxtEmpresaDesenvolvedora;
    @FXML
    private TextField vTxtVersaoRoteiro;
    @FXML
    private TextField iTxtOutroArq;
    @FXML
    private Button iBtnAdd;
    @FXML
    private ComboBox<String> cbBD;
    @FXML
    private TableView<NaoConformidadeType> nTvTabela;
    @FXML
    private TextField oTxtCNPJ;
    @FXML
    private ComboBox<String> cbSO;
    @FXML
    private TextField speTxtCNPJ;
    @FXML
    private TextField dTxtTelefone;
    @FXML
    private TextField speTxtEmpresaDesenvolvedora;
    @FXML
    private TextField dTxtCNPJ;
    @FXML
    private TextField spTxtNomeSistema;
    @FXML
    private TextField iTxtMD5Relacao;
    @FXML
    private ComboBox<String> cbLinguagem;
    @FXML
    private Button sgBtnAdd;
    @FXML
    private CheckBox ckEspeciais12;
    @FXML
    private CheckBox ckEspeciais11;
    @FXML
    private CheckBox ckEspeciais10;
    @FXML
    private TextField feTxtCargo;
    @FXML
    private TextField feTxtCargo2;
    @FXML
    private TextField dTxtEmail;
    @FXML
    private TextField iTxtMD5Principal;
    @FXML
    private TextField spTxtCNPJ;
    @FXML
    private CheckBox ckEspeciais15;
    @FXML
    private CheckBox ckEspeciais14;
    @FXML
    private CheckBox ckEspeciais13;
    @FXML
    private TextField dTxtIE;
    @FXML
    private CheckBox iCkA;
    @FXML
    private CheckBox iCkB;
    @FXML
    private CheckBox iCkC;
    @FXML
    private CheckBox iCkD;
    @FXML
    private CheckBox iCkE;
    @FXML
    private CheckBox iCkF;
    @FXML
    private CheckBox iCkG;
    @FXML
    private CheckBox iCkH;
    @FXML
    private CheckBox iCkI;
    @FXML
    private CheckBox iCkJ;
    @FXML
    private CheckBox iCkR;
    @FXML
    private CheckBox iCkS;
    @FXML
    private CheckBox iCkT;
    @FXML
    private CheckBox iCkU;
    @FXML
    private CheckBox iCkV;
    @FXML
    private CheckBox iCkW;
    @FXML
    private CheckBox iCkX;
    @FXML
    private CheckBox iCkY;
    @FXML
    private CheckBox iCkZ;
    @FXML
    private TextField oTxtCEP;
    @FXML
    private TextField sgTxtNomeSistema;
    @FXML
    private TextField sgTxtRazaoSocial;
    @FXML
    private TextField dTxtCidade;
    @FXML
    private ComboBox cbEmpresa;
    @FXML
    private ListView lvLaudo;
    @FXML
    private Button btnCarregar;
    @FXML
    private Tab tabDesenvolvedora;
    @FXML
    private Tab tabComplementar;
    @FXML
    private TabPane tpPrincipal;
    @FXML
    private HBox paneCheckBox1;
    @FXML
    private HBox paneCheckBox2;
    //iTvTabela
    @FXML
    private TableColumn<ArquivoExecutavelSemFuncaoType, String> iTcNome;
    @FXML
    private TableColumn<ArquivoExecutavelSemFuncaoType, String> iTcMd5;
    //sgTvTabela
    @FXML
    private TableColumn<ArquivoExecutavelType, String> tcSGNome;
    @FXML
    private TableColumn<ArquivoExecutavelType, String> tcSGMD5;
    @FXML
    private TableColumn<ArquivoExecutavelType, String> tcSGRequisitos;

    //spTvTabela
    @FXML
    private TableColumn<ArquivoExecutavelComFuncaoType, String> tcSpNome;
    @FXML
    private TableColumn<ArquivoExecutavelComFuncaoType, String> tcSpMD5;
    @FXML
    private TableColumn<ArquivoExecutavelComFuncaoType, String> tcSpFuncao;

//    speTvTabela
    @FXML
    private TableColumn<ArquivoExecutavelComFuncaoType, String> tcSpeNome;
    @FXML
    private TableColumn<ArquivoExecutavelComFuncaoType, String> tcSpeMD5;
    @FXML
    private TableColumn<ArquivoExecutavelComFuncaoType, String> tcSpeFuncao;

//    nTvTabela
    @FXML
    private TableColumn<NaoConformidadeType, String> tcRequisito;
    @FXML
    private TableColumn<NaoConformidadeType, String> tcItem;
    @FXML
    private TableColumn<NaoConformidadeType, String> tcDescricao;
    @FXML
    private ToggleButton tBtnGerarDocs;

    @FXML
    private Text txtTopEmpresa;
    @FXML
    private Button btnBuscarEmpresa;
    //Informações complementares
    @FXML
    private ComboBox<Usuario> cbResponsavelEnsaio;
    @FXML
    private TextField txtNomeFantasia;
    @FXML
    private TextField txtIm;
    @FXML
    private TextField txtCelular;
    @FXML
    private TextField txtFax;
    @FXML
    private TextField txtBytes;
    @FXML
    private TextField txtRg;
    @FXML
    private CheckBox ckbGerenciadorBD;
    @FXML
    private TextField txtRipmedPrincipal;
    @FXML
    private TextField txtRipmedRelacao;
    @FXML
    private Text txtNLaudo;
    @FXML
    private CheckBox cbValidarLaudo;

    private CheckComboBox<String> cb1 = new CheckComboBox();
    private CheckComboBox<String> cb2 = new CheckComboBox();
    private CheckBox cbSelecionarTodos = new CheckBox("Selecionar Todos");
    private CheckBox cbSelecionarTodos2 = new CheckBox("Selecionar Todos");
    private Stage stage;
    private MensagemType mensagem;
    private UtilFile utilXml;
    private MarcasModelosCompativeisType mct;
    private ObservableList<String> files;
    private LaudoComplementar laudoComplementar;
    private String diretorioDoc;

    private void carregarDiretorioXML() {
        utilXml.setEmpresas(FXCollections.observableArrayList());
        utilXml.listarDireitorio(new File(utilXml.getDiretorioInicial()));
        cbEmpresa.setItems(utilXml.getEmpresas());
    }

//    public static void main(String[] args) {
//        launch(args);
//    }
    private void showLinearWizard() {
        // define pages to show
        Wizard wizard = new Wizard();
        wizard.setTitle("Linear Wizard");
        // --- page 1
        int row = 0;
        GridPane page1Grid = new GridPane();
        page1Grid.setVgap(10);
        page1Grid.setHgap(10);
        page1Grid.add(new Label("Número do Laudo:"), 0, row);
        TextField txFirstName = createTextField("laudo");
        page1Grid.add(txFirstName, 1, row++);

        final WizardPane page1 = new WizardPane() {
            @Override
            public void onExitingPage(Wizard wizard) {
                mensagem.setNumero(txFirstName.getText());
            }
        };
        page1.setHeaderText("Informe o número do laudo");
        page1.setContent(page1Grid);
        // --- page 2
        CheckBox checkBox = new CheckBox("Tem NF-e?");
        checkBox.setId("nfe");
        WizardPane page2 = new WizardPane();
        page2.setHeaderText("Empresa tem NF-e?");
        page2.setContent(checkBox);
        wizard.setFlow(new LinearFlow(page1, page2));
        wizard.showAndWait().ifPresent(result -> {
            if (result == ButtonType.FINISH) {
                System.out.println("Wizard finished, settings: " + wizard.getSettings());
                if (checkBox.isSelected()) {
                    mensagem.setEmiteNfe(true);
                } else {
                    mensagem.setEmiteNfe(false);
                }
            }
        });
    }

    private TextField createTextField(String id) {
        TextField textField = new TextField();
        textField.setId(id);
        GridPane.setHgrow(textField, Priority.ALWAYS);
        return textField;
    }

    private Button createButton(String id, String name) {
        Button btn = new Button(name);
        btn.setId(id);
        GridPane.setHgrow(btn, Priority.ALWAYS);
        return btn;
    }

    @FXML
    void actionBtnSalvar(ActionEvent event) {
        try {
            System.out.println("- Iniciar metodo actionBtnSalvar");
            main.setDisable(true);
            if (!validarCampos()) {
                mensagem.setNumero(txtNumeroLaudo.getText());
                if (ckEmite1.isSelected()) {
                    mensagem.setEmiteNfe(true);
                } else {
                    mensagem.setEmiteNfe(false);
                }

                //-----------------------Desenvolvedora---------------------------------
                DesenvolvedoraType desenvolvedora = new DesenvolvedoraType();
                desenvolvedora.setRazaoSocial(dTxtRazaoSocial.getText());
                desenvolvedora.setCnpj(dTxtCNPJ.getText());
                desenvolvedora.setIe(dTxtIE.getText());
                EnderecoType endereco = new EnderecoType();
                endereco.setLogradouro(dTxtLogradouro.getText());
                endereco.setNumero(dTxtNumero.getText());
                endereco.setComplemento(dTxtComplemento.getText());
                endereco.setBairro(dTxtBairro.getText());
                endereco.setMunicipio(dTxtCidade.getText());
                endereco.setUf(dTxtUF.getText());
                endereco.setCep(dTxtCEP.getText());
                desenvolvedora.setEndereco(endereco);
                ContatoType contato = new ContatoType();
                contato.setNome(dTxtNome.getText());
                contato.setCpf(dTxtCPF.getText());
                contato.setTelefone(dTxtTelefone.getText());
                contato.setEmail(dTxtEmail.getText());
                desenvolvedora.setContato(contato);
                desenvolvedora.setResponsavelAcompanhamentoTestes(dTxtResponsavelTestes.getText());
                mensagem.setDesenvolvedora(desenvolvedora);

                //------------------------------OTC-------------------------------------
                OtcType otc = new OtcType();
                otc.setRazaoSocial(oTxtRazaoSocial.getText());
                otc.setCnpj(oTxtCNPJ.getText());
                otc.setIe(oTxtIE.getText());
                endereco = new EnderecoType();
                endereco.setLogradouro(oTxtLogradouro.getText());
                endereco.setNumero(oTxtNumero.getText());
                endereco.setComplemento(oTxtComplemento.getText());
                endereco.setBairro(oTxtBairro.getText());
                endereco.setMunicipio(oTxtCidade.getText());
                endereco.setUf(oTxtUF.getText());
                endereco.setCep(oTxtCEP.getText());
                otc.setEndereco(endereco);
                PeriodoAnaliseType peridoAnalise = new PeriodoAnaliseType();
                if (oDtInicio.getValue() != null) {
                    peridoAnalise.setDataInicio(UtilConverter.convertDateForXMLGregorianCalendar(oDtInicio));
                }
                if (oDtFinal.getValue() != null) {
                    peridoAnalise.setDataFim(UtilConverter.convertDateForXMLGregorianCalendar(oDtFinal));
                }
                otc.setPeriodoAnalise(peridoAnalise);
                otc.setVersaoEspecificacaoRequisitos(oTxtVersaoER.getText());
                mensagem.setOtc(otc);

                //---------------------------Identificação------------------------------
                IdentificacaoPafType identificacao = new IdentificacaoPafType();
                identificacao.setNomeComercial(iTxtNomeComercial.getText());
                identificacao.setVersao(iTxtVersao.getText());
                ArquivoExecutavelPrincipalType arquivoExecutavelPrincipal = new ArquivoExecutavelPrincipalType();
                arquivoExecutavelPrincipal.setNome(iTxtPrincipalExec.getText());
                arquivoExecutavelPrincipal.setMd5(iTxtMD5Principal.getText());
                identificacao.setArquivoExecutavelPrincipal(arquivoExecutavelPrincipal);
                ArquivoRelacaoExecutaveisType arquivoRelacaoExecutaveis = new ArquivoRelacaoExecutaveisType();
                arquivoRelacaoExecutaveis.setNome(iTxtRelacaoExec.getText());
                arquivoRelacaoExecutaveis.setMd5(iTxtMD5Relacao.getText());
                identificacao.setArquivoRelacaoExecutaveis(arquivoRelacaoExecutaveis);
                ArquivosExecutaveisSemFuncaoType arquivosExecutaveisSemFuncao = new ArquivosExecutaveisSemFuncaoType();
                arquivosExecutaveisSemFuncao.getArquivoExecutavel().addAll(iTvTabela.getItems());
                identificacao.setArquivosExecutaveis(arquivosExecutaveisSemFuncao);
                ArquivoOutroType ao = new ArquivoOutroType();
                ao.setNome(iTxtOutroArq.getText());
                ao.setMd5(iTxtMD5Outro.getText());
                ArquivosOutrosType arquivosOutros = new ArquivosOutrosType();
                arquivosOutros.getArquivoOutro().add(ao);
                identificacao.setArquivosOutros(arquivosOutros);
                EnvelopeSegurancaType est = new EnvelopeSegurancaType();
                est.setMarca(iTxtMarca.getText());
                est.setModelo(iTxtModelo.getText());
                est.setNumero(iTxtNumero.getText());
                identificacao.setEnvelopeSeguranca(est);
                PerfisRequisitosType pqt = new PerfisRequisitosType();
                if (iCkV.isSelected()) {
                    pqt.getPerfilRequisito().add("V");
                }
                if (iCkW.isSelected()) {
                    pqt.getPerfilRequisito().add("W");
                }
                if (iCkY.isSelected()) {
                    pqt.getPerfilRequisito().add("Y");
                }
                if (iCkZ.isSelected()) {
                    pqt.getPerfilRequisito().add("Z");
                }
                if (iCkA.isSelected()) {
                    pqt.getPerfilRequisito().add("A");
                }
                if (iCkB.isSelected()) {
                    pqt.getPerfilRequisito().add("B");
                }
                if (iCkC.isSelected()) {
                    pqt.getPerfilRequisito().add("C");
                }
                if (iCkD.isSelected()) {
                    pqt.getPerfilRequisito().add("D");
                }
                if (iCkE.isSelected()) {
                    pqt.getPerfilRequisito().add("E");
                }
                if (iCkF.isSelected()) {
                    pqt.getPerfilRequisito().add("F");
                }
                if (iCkG.isSelected()) {
                    pqt.getPerfilRequisito().add("G");
                }
                if (iCkH.isSelected()) {
                    pqt.getPerfilRequisito().add("H");
                }
                if (iCkI.isSelected()) {
                    pqt.getPerfilRequisito().add("I");
                }
                if (iCkJ.isSelected()) {
                    pqt.getPerfilRequisito().add("J");
                }
                if (iCkR.isSelected()) {
                    pqt.getPerfilRequisito().add("R");
                }
                if (iCkS.isSelected()) {
                    pqt.getPerfilRequisito().add("S");
                }
                if (iCkT.isSelected()) {
                    pqt.getPerfilRequisito().add("T");
                }
                if (iCkU.isSelected()) {
                    pqt.getPerfilRequisito().add("U");
                }
                if (iCkX.isSelected()) {
                    pqt.getPerfilRequisito().add("X");
                }

                identificacao.setPerfisRequisitos(pqt);
                mensagem.setIdentificacaoPaf(identificacao);

                //---------------------------Caracteristica-----------------------------
                CaracteristicasPafType caracteristicas = new CaracteristicasPafType();
                MeioGeracaoArquivoSintegraEfdType maset = new MeioGeracaoArquivoSintegraEfdType();
                if (ckGeracao1.isSelected()) {
                    maset.getModo().add(ckGeracao1.getText());
                } else if (ckGeracao2.isSelected()) {
                    maset.getModo().add(ckGeracao2.getText());
                } else if (ckGeracao3.isSelected()) {
                    maset.getModo().add(ckGeracao3.getText());
                }
                caracteristicas.setMeioGeracaoArquivoSintegraEfd(maset);

                TratamentoInterrupcaoType tit = new TratamentoInterrupcaoType();
                if (ckInterrupcao1.isSelected()) {
                    tit.getModo().add(ckInterrupcao1.getText());
                }
                if (ckInterrupcao2.isSelected()) {
                    tit.getModo().add(ckInterrupcao2.getText());
                }
                if (ckInterrupcao3.isSelected()) {
                    tit.getModo().add(ckInterrupcao3.getText());
                }
                caracteristicas.setTratamentoInterrupcao(tit);

                FormaImpressaoType fit = new FormaImpressaoType();
                if (ckForma1.isSelected()) {
                    fit.getModo().add(ckForma1.getText());
                }
                if (ckForma2.isSelected()) {
                    fit.getModo().add(ckForma2.getText());
                }
                if (ckForma3.isSelected()) {
                    fit.getModo().add(ckForma3.getText());
                }
                if (ckForma4.isSelected()) {
                    fit.getModo().add(ckForma4.getText());
                }
                if (ckForma5.isSelected()) {
                    fit.getModo().add(ckForma5.getText());
                }
                if (ckForma6.isSelected()) {
                    fit.getModo().add(ckForma6.getText());
                }
                if (ckForma7.isSelected()) {
                    fit.getModo().add(ckForma7.getText());
                }
                caracteristicas.setFormaImpressao(fit);

                if (ckIntegracao1.isSelected()) {
                    caracteristicas.setIntegracaoPaf(ckIntegracao1.getText());
                } else if (ckIntegracao2.isSelected()) {
                    caracteristicas.setIntegracaoPaf(ckIntegracao2.getText());
                } else if (ckIntegracao3.isSelected()) {
                    caracteristicas.setIntegracaoPaf(ckIntegracao3.getText());
                } else if (ckIntegracao4.isSelected()) {
                    caracteristicas.setIntegracaoPaf(ckIntegracao4.getText());
                }

                AplicacoesEspeciaisType aet = new AplicacoesEspeciaisType();
                if (ckEspeciais1.isSelected()) {
                    aet.getAplicacaoEspecial().add(ckEspeciais1.getText());
                }
                if (ckEspeciais2.isSelected()) {
                    aet.getAplicacaoEspecial().add(ckEspeciais2.getText());
                }
                if (ckEspeciais3.isSelected()) {
                    aet.getAplicacaoEspecial().add(ckEspeciais3.getText());
                }
                if (ckEspeciais4.isSelected()) {
                    aet.getAplicacaoEspecial().add(ckEspeciais4.getText());
                }
                if (ckEspeciais5.isSelected()) {
                    aet.getAplicacaoEspecial().add(ckEspeciais5.getText());
                }
                if (ckEspeciais6.isSelected()) {
                    aet.getAplicacaoEspecial().add(ckEspeciais6.getText());
                }
                if (ckEspeciais7.isSelected()) {
                    aet.getAplicacaoEspecial().add(ckEspeciais7.getText());
                }
                if (ckEspeciais8.isSelected()) {
                    aet.getAplicacaoEspecial().add(ckEspeciais8.getText());
                }
                if (ckEspeciais9.isSelected()) {
                    aet.getAplicacaoEspecial().add(ckEspeciais9.getText());
                }
                if (ckEspeciais10.isSelected()) {
                    aet.getAplicacaoEspecial().add(ckEspeciais10.getText());
                }
                if (ckEspeciais11.isSelected()) {
                    aet.getAplicacaoEspecial().add(ckEspeciais11.getText());
                }
                if (ckEspeciais12.isSelected()) {
                    aet.getAplicacaoEspecial().add(ckEspeciais12.getText());
                }
                if (ckEspeciais13.isSelected()) {
                    aet.getAplicacaoEspecial().add(ckEspeciais13.getText());
                }
                if (ckEspeciais14.isSelected()) {
                    aet.getAplicacaoEspecial().add(ckEspeciais14.getText());
                }
                if (ckEspeciais15.isSelected()) {
                    aet.getAplicacaoEspecial().add(ckEspeciais15.getText());
                }
                caracteristicas.setAplicacoesEspeciais(aet);
                if (cbLinguagem.getValue() == null) {
                    cbLinguagem.getSelectionModel().selectFirst();
                    caracteristicas.setLinguagemProgramacao(cbLinguagem.getValue());
                } else {
                    caracteristicas.setLinguagemProgramacao(cbLinguagem.getValue());
                }
                if (cbSO.getValue() == null) {
                    cbSO.getSelectionModel().selectFirst();
                    caracteristicas.setSistemaOperacional(cbSO.getValue());
                } else {
                    caracteristicas.setSistemaOperacional(cbSO.getValue());
                }
                if (cbBD.getValue() == null) {
                    cbBD.getSelectionModel().selectFirst();
                    caracteristicas.getGerenciadorBancoDados().add(cbBD.getValue());
                } else {
                    caracteristicas.getGerenciadorBancoDados().add(cbBD.getValue());
                }
                if (ckComercializavel.isSelected()) {
                    caracteristicas.setTipoDesenvolvimento(ckComercializavel.getText());
                } else if (ckProprio.isSelected()) {
                    caracteristicas.setTipoDesenvolvimento(ckProprio.getText());
                } else if (ckTerceirizado.isSelected()) {
                    caracteristicas.setTipoDesenvolvimento(ckTerceirizado.getText());
                }
                if (ckTipo1.isSelected()) {
                    caracteristicas.setTipoFuncionamento(ckTipo1.getText());
                } else if (ckTipo2.isSelected()) {
                    caracteristicas.setTipoFuncionamento(ckTipo2.getText());
                } else if (ckTipo3.isSelected()) {
                    caracteristicas.setTipoFuncionamento(ckTipo3.getText());
                }

                caracteristicas.setMeioGeracaoArquivoSintegraEfd(maset);
                mensagem.setCaracteristicasPaf(caracteristicas);

                //---------------------------Sistema Gestao-----------------------------
                SistemaGestaoType sgt = new SistemaGestaoType();
                if (sgTxtNomeSistema.getText().equals("")) {
                    sgTxtNomeSistema.setText("-");
                }
                sgt.setNome(sgTxtNomeSistema.getText());
                EmpresaDesenvolvedoraType edt = new EmpresaDesenvolvedoraType();
                if (sgTxtCNPJ.getText().equals("")) {
                    sgTxtCNPJ.setText("00000000000000");
                }
                edt.setCnpj(sgTxtCNPJ.getText());
                if (sgTxtRazaoSocial.getText().equals("")) {
                    sgTxtRazaoSocial.setText("-");
                }
                edt.setRazaoSocial(sgTxtRazaoSocial.getText());
                sgt.setEmpresaDesenvolvedora(edt);

                ArquivosExecutaveisType aet2 = new ArquivosExecutaveisType();
                if (sgTvTabela.getItems().size() == 0) {
                    ArquivoExecutavelType a = new ArquivoExecutavelType();
                    a.setMd5("00000000000000000000000000000000");
                    a.setNome("-");
                    RequisitosExecutadosType r = new RequisitosExecutadosType();
                    r.getRequisitoExecutado().add("-");
                    a.setRequisitosExecutados(r);
                    sgTvTabela.getItems().add(a);
                }
                aet2.getArquivoExecutavel().addAll(sgTvTabela.getItems());
                sgt.setArquivosExecutaveis(aet2);
                SistemasGestaoType sgts = new SistemasGestaoType();
                sgts.getSistemaGestao().add(sgt);
                mensagem.setSistemasGestao(sgts);

                //---------------------------Sistema PED--------------------------------
                SistemaPedType spt = new SistemaPedType();
                if (spTxtNomeSistema.getText().equals("")) {
                    spTxtNomeSistema.setText("-");
                }
                spt.setNome(spTxtNomeSistema.getText());
                EmpresaDesenvolvedoraType edt2 = new EmpresaDesenvolvedoraType();
                if (spTxtEmpresaDesenvolvedora.getText().equals("")) {
                    spTxtEmpresaDesenvolvedora.setText("-");
                }
                edt2.setRazaoSocial(spTxtEmpresaDesenvolvedora.getText());
                if (spTxtCNPJ.getText().equals("")) {
                    spTxtCNPJ.setText("00000000000000");
                }
                edt2.setCnpj(spTxtCNPJ.getText());
                spt.setEmpresaDesenvolvedora(edt2);

                ArquivosExecutaveisComFuncaoType aesft = new ArquivosExecutaveisComFuncaoType();
                if (spTvTabela.getItems().size() == 0) {
                    ArquivoExecutavelComFuncaoType a = new ArquivoExecutavelComFuncaoType();
                    a.setFuncao("-");
                    a.setMd5("00000000000000000000000000000000");
                    a.setNome("-");
                    spTvTabela.getItems().add(a);
                }
                aesft.getArquivoExecutavel().addAll(spTvTabela.getItems());
                spt.setArquivosExecutaveis(aesft);
                SistemasPedType spts = new SistemasPedType();
                spts.getSistemaPed().add(spt);
                mensagem.setSistemasPed(spts);

                //------------------------Sistema PED NF-e------------------------------
                SistemaPedNfeType spft = new SistemaPedNfeType();
                if (speTxtNomeSistema.getText().equals("")) {
                    speTxtNomeSistema.setText("-");
                }
                spft.setNome(speTxtNomeSistema.getText());
                EmpresaDesenvolvedoraType edt3 = new EmpresaDesenvolvedoraType();
                if (speTxtCNPJ.getText().equals("")) {
                    speTxtCNPJ.setText("00000000000000");
                }
                edt3.setCnpj(speTxtCNPJ.getText());
                if (speTxtEmpresaDesenvolvedora.getText().equals("")) {
                    speTxtEmpresaDesenvolvedora.setText("-");
                }
                edt3.setRazaoSocial(speTxtEmpresaDesenvolvedora.getText());
                spft.setEmpresaDesenvolvedora(edt3);

                ArquivosExecutaveisComFuncaoType aesft2 = new ArquivosExecutaveisComFuncaoType();
                if (speTvTabela.getItems().size() == 0) {
                    ArquivoExecutavelComFuncaoType a = new ArquivoExecutavelComFuncaoType();
                    a.setFuncao("-");
                    a.setMd5("00000000000000000000000000000000");
                    a.setNome("-");
                    speTvTabela.getItems().add(a);
                }
                aesft2.getArquivoExecutavel().addAll(speTvTabela.getItems());
                spft.setArquivosExecutaveis(aesft2);
                SistemasPedNfeType spnts = new SistemasPedNfeType();
                spnts.getSistemaPedNfe().add(spft);
                mensagem.setSistemasPedNfe(spnts);

                //8. Identifica��o dos Equipamentos ECF Utilizados para a An�lise Funcional :
                EcfAnaliseFuncionalType eaft = new EcfAnaliseFuncionalType();
                eaft.getMarcaModelo().addAll(eTvTabela.getItems());
                mensagem.setEcfAnaliseFuncional(eaft);

                //9. Rela��o de marcas e modelos de equipamentos ECF compat�veis com o PAF-ECF:
                MarcasModelosCompativeisType mmct = new MarcasModelosCompativeisType();
                mmct.getMarcaModelo().addAll(tTvTabela.getItems());
                mensagem.setMarcasModelosCompativeis(mmct);
//-------------------------------------------------------------------------------
                mensagem.setVersaoErPaf(vTxtVersaoER.getText());
                RoteiroAnaliseType rat = new RoteiroAnaliseType();
                rat.setAno(vTxtAno.getText());
                rat.setMes(vTxtMes.getText());
                rat.setVersaoRoteiro(vTxtVersaoRoteiro.getText());
                mensagem.setRoteiroAnalise(rat);

                NaoConformidadesType nct = new NaoConformidadesType();
                nct.getNaoConformidade().addAll(nTvTabela.getItems());
                mensagem.setNaoConformidades(nct);

                mensagem.setComentarioOtc(fTxtArea.getText());
                mensagem.setDeclaracao(fCkDeclaracao.isSelected());

                EmissaoType et = new EmissaoType();
                if (fDt.getValue() != null) {
                    et.setData(UtilConverter.convertDateForXMLGregorianCalendar(fDt));
                }
                et.setLocal(fTxtLocal.getText());
                mensagem.setEmissao(et);

                ExecucaoTestesType ett = new ExecucaoTestesType();
                ett.setNome(feTxtNome.getText());
                ett.setCargo(feTxtCargo.getText());
                ett.setCpf(feTxtCPF.getText());
                mensagem.setExecucaoTestes(ett);

                AprovacaoRelatorioType art = new AprovacaoRelatorioType();
                art.setNome(feTxtNome2.getText());
                art.setCargo(feTxtCargo2.getText());
                art.setCpf(feTxtCPF2.getText());
                mensagem.setAprovacaoRelatorio(art);
                LaudoType lt = new LaudoType();
                lt.setMensagem(mensagem);
                lt.setVersao("1.0");
                diretorioDoc = utilXml.criaDiretorio(mensagem.getDesenvolvedora().getRazaoSocial());
                addDocx();
                File criarArquivo = new File(utilXml.getDiretorioInicial() + mensagem.getDesenvolvedora().getRazaoSocial() + "/" + mensagem.getNumero() + ".xml");
                if (criarArquivo.exists()) {
                    Optional<ButtonType> result = UtilDialog.criarDialogConfirmacao(EnumMensagem.Pergunta.getTitulo(), EnumMensagem.Pergunta.getSubTitulo(), "Esté arquivo já existe; " + mensagem.getNumero() + ".xml");
                    if (result.get() == ButtonType.OK) {
                        finalizarAoSalvar(criarArquivo, lt);
                    }
                } else {
                    finalizarAoSalvar(criarArquivo, lt);
                }
            }
            main.setDisable(false);
            System.out.println("- Finalizar metodo actionBtnSalvar");
        } catch (Exception e) {
            e.printStackTrace();
            main.setDisable(false);
            UtilDialog.criarDialogException(EnumMensagem.Padrao.getTitulo(), "Erro!", "Preencher todos os campos", e, "Obs:");
        }
    }

    private void addDocx() {
        if (tBtnGerarDocs.isSelected()) {
            Json json = new Json();
            Gson gson = new Gson();
            Type type = new TypeToken<HashMap<String, String>>() {
            }.getType();
            GerarDocx gerarDoc = new GerarDocx();
//            System.out.println("DOCUMENTOS JSON CONVERT " + json.lerArquivoJSON(EnumCaminho.DocumentosDocxs.getCaminho()));
            for (ParametroDocx pr : json.lerArquivoJSON(EnumCaminho.DocumentosDocxs.getCaminho())) {
                pr.setParametros(setHashMap(pr.getDocumento(), gson.fromJson(pr.getParametros(), type)));
//                System.out.println("NEW PARAMETRO " + pr.getParametros());
                if (pr.getDocumento().equals("ANEXO BANCO DE DADOS MODELO") || pr.getDocumento().equals("LAUDO PAF-ECF-F MODELO 2015")) {
                    gerarDoc.gerarDocx(diretorioDoc, pr, true, txtNomeFantasia.getText());
                } else {

                    gerarDoc.gerarDocx(diretorioDoc, pr, false, txtNomeFantasia.getText());
                }
            }

        }
    }

    public String setHashMap(String documento, HashMap<String, String> hmap) {
        Gson gson = new Gson();
        Optional<ButtonType> resp;
        for (String key : hmap.keySet()) {
            switch (key) {
                case "txtNomeFantasia":
                    hmap.replace(key, txtNomeFantasia.getText());
                    break;
                case "txtFax":
                    hmap.replace(key, txtFax.getText());
                    break;
                case "txtCelular":
                    hmap.replace(key, txtCelular.getText());
                    break;
                case "txtIm":
                    hmap.replace(key, txtIm.getText());
                    break;
                case "txtRg":
                    hmap.replace(key, txtRg.getText());
                    break;
                case "txtTamanhoBytes":
                    hmap.replace(key, txtBytes.getText());
                    break;
                case "txtRipmedRelacao":
                    hmap.replace(key, txtRipmedRelacao.getText());
                    break;
                case "txtRipmedEmpresa":
                    hmap.replace(key, txtRipmedPrincipal.getText());
                    break;
                case "laudo":
                    hmap.replace(key, txtNumeroLaudo.getText());
                    break;
                case "txtRazaoSocial":
                    hmap.replace(key, dTxtRazaoSocial.getText());
                    break;
                case "txtEndereco":
                    hmap.replace(key, dTxtLogradouro.getText() + ", " + dTxtNumero.getText());
                    break;
                case "txtBairro":
                    hmap.replace(key, dTxtBairro.getText());
                    break;
                case "txtCidade":
                    hmap.replace(key, dTxtCidade.getText());
                    break;
                case "txtUf":
                    hmap.replace(key, dTxtUF.getText());
                    break;
                case "txtCep":
                    hmap.replace(key, UtilTexto.formatarMascaraCep(dTxtCEP.getText()));
                    break;
                case "txtTelefone":
                    hmap.replace(key, UtilTexto.formatarMascaraTelefone(dTxtTelefone.getText()));
                    break;
                case "txtCnpj":
                    hmap.replace(key, UtilTexto.formatarMascaraCnpj(dTxtCNPJ.getText()));
                    break;
                case "txtIe":
                    hmap.replace(key, dTxtIE.getText());
                    break;
                case "txtContato":
                    hmap.replace(key, dTxtNome.getText());
                    break;
                case "txtCpfContato":
                    hmap.replace(key, UtilTexto.formatarMascaraCpf(dTxtCPF.getText()));
                    break;
                case "txtEmail":
                    hmap.replace(key, dTxtEmail.getText());
                    break;
                case "txtResponsavel":
                    hmap.replace(key, dTxtResponsavelTestes.getText());
                    break;
                case "txtNomeHomologador":
                    hmap.replace(key, cbResponsavelEnsaio.getValue().getNome());
                    break;
                case "txtDataInicio":
                    hmap.replace(key, oDtInicio.getValue().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)));
                    break;
                case "txtDataFinal":
                    hmap.replace(key, oDtFinal.getValue().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)));
                    break;
                case "txtNomeComercial":
                    hmap.replace(key, iTxtNomeComercial.getText());
                    break;
                case "txtVersao":
                    hmap.replace(key, iTxtVersao.getText());
                    break;
                case "txtDataVersao":
                    hmap.replace(key, oDtFinal.getValue().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)));
                    break;
                case "txtPrincipalExec":
                    hmap.replace(key, iTxtPrincipalExec.getText());
                    break;
                case "txtMd5Principal":
                    hmap.replace(key, iTxtMD5Principal.getText());
                    break;
                case "txtMd5NomeArquivoEmpresa":
                    hmap.replace(key, iTxtMD5Relacao.getText() + " *" + iTxtRelacaoExec.getText());
                    break;
                case "txtRelacaoMd5Executaveis":
                    iTvTabela.getItems();
                    String relacao = "";
                    int qtdEspaco = 40;
                    String espaco = "";
                    for (ArquivoExecutavelSemFuncaoType item : iTvTabela.getItems()) {
                        if (item.getNome().length() < qtdEspaco) {
                            for (int i = 0; i <= qtdEspaco; i++) {
                                espaco += " ";
                            }
                        }
                        relacao += item.getNome() + espaco + item.getMd5() + ",";
                        espaco = "";
                    }
                    hmap.replace(key, relacao);
                    break;
                case "txtMd5NomeRelacao":
                    hmap.replace(key, iTxtMD5Outro.getText() + " *" + iTxtOutroArq.getText());
                    break;
                case "perfila":
                    hmap.replace(key, iCkA.isSelected() + "");
                    break;
                case "perfilb":
                    hmap.replace(key, iCkB.isSelected() + "");
                    break;
                case "perfilc":
                    hmap.replace(key, iCkC.isSelected() + "");
                    break;
                case "perfild":
                    hmap.replace(key, iCkD.isSelected() + "");
                    break;
                case "perfile":
                    hmap.replace(key, iCkE.isSelected() + "");
                    break;
                case "perfilf":
                    hmap.replace(key, iCkF.isSelected() + "");
                    break;
                case "perfilg":
                    hmap.replace(key, iCkG.isSelected() + "");
                    break;
                case "perfilh":
                    hmap.replace(key, iCkH.isSelected() + "");
                    break;
                case "perfili":
                    hmap.replace(key, iCkI.isSelected() + "");
                    break;
                case "perfilj":
                    hmap.replace(key, iCkJ.isSelected() + "");
                    break;
                case "perfilr":
                    hmap.replace(key, iCkR.isSelected() + "");
                    break;
                case "perfils":
                    hmap.replace(key, iCkS.isSelected() + "");
                    break;
                case "perfilt":
                    hmap.replace(key, iCkT.isSelected() + "");
                    break;
                case "perfilu":
                    hmap.replace(key, iCkU.isSelected() + "");
                    break;
                case "perfilv":
                    hmap.replace(key, iCkV.isSelected() + "");
                    break;
                case "perfilw":
                    hmap.replace(key, iCkW.isSelected() + "");
                    break;
                case "perfilx":
                    hmap.replace(key, iCkX.isSelected() + "");
                    break;
                case "perfily":
                    hmap.replace(key, iCkY.isSelected() + "");
                    break;
                case "perfilz":
                    hmap.replace(key, iCkZ.isSelected() + "");
                    break;
                case "txtEnvelope":
                    hmap.replace(key, iTxtNumero.getText());
                    break;
                case "txtLinguagemProgramacao":
                    hmap.replace(key, cbLinguagem.getValue());
                    break;
                case "txtSo":
                    hmap.replace(key, cbSO.getValue());
                    break;
                case "txtBd":
                    hmap.replace(key, cbBD.getValue());
                    break;

                case "ckbComercializavel":
                    hmap.replace(key, ckComercializavel.isSelected() + "");
                    break;
                case "ckbExclusivoProprio":
                    hmap.replace(key, ckProprio.isSelected() + "");
                    break;
                case "ckbExclusivoTerce":
                    hmap.replace(key, ckTerceirizado.isSelected() + "");
                    break;
                case "ckbConcomitante":
                    hmap.replace(key, ckForma1.isSelected() + "");
                    break;
                case "ckbNaoConcomitanteDV":
                    hmap.replace(key, ckForma2.isSelected() + "");
                    break;
                case "ckbNaoConcomitantePr":
                    hmap.replace(key, ckForma3.isSelected() + "");
                    break;
                case "ckbNaoConcomitanteCC":
                    hmap.replace(key, ckForma4.isSelected() + "");
                    break;
                case "ckbDavSemImp":
                    hmap.replace(key, ckForma5.isSelected() + "");
                    break;
                case "ckbDavImpNaoFiscal":
                    hmap.replace(key, ckForma6.isSelected() + "");
                    break;
                case "ckbDavEcf":
                    hmap.replace(key, ckForma7.isSelected() + "");
                    break;
                case "ckbStandAlone":
                    hmap.replace(key, ckTipo1.isSelected() + "");
                    break;
                case "ckbEmRede":
                    hmap.replace(key, ckTipo2.isSelected() + "");
                    break;
                case "ckbParametrizavel":
                    hmap.replace(key, ckTipo3.isSelected() + "");
                    break;
                case "ckbPeloPaf":
                    hmap.replace(key, ckGeracao1.isSelected() + "");
                    break;
                case "ckbPeloRetaguarda":
                    hmap.replace(key, ckGeracao2.isSelected() + "");
                    break;
                case "ckbPeloSisPed":
                    hmap.replace(key, ckGeracao3.isSelected() + "");
                    break;
                case "ckbNfeSim":
                    if (ckEmite1.isSelected()) {
                        hmap.replace(key, "true");
                    } else {
                        hmap.replace(key, "false");
                    }
                    break;
                case "ckbNfeNao":
                    if (ckEmite1.isSelected()) {
                        hmap.replace(key, "false");
                    } else {
                        hmap.replace(key, "true");
                    }
                    break;
                case "ckbNfceSim":
                    if (ckEmite2.isSelected()) {
                        hmap.replace(key, "true");
                    } else {
                        hmap.replace(key, "false");
                    }
                    break;
                case "ckbNfceNao":
                    if (ckEmite2.isSelected()) {
                        hmap.replace(key, "true");
                    } else {
                        hmap.replace(key, "false");
                    }
                    break;
                case "ckbRecuDados":
                    hmap.replace(key, ckInterrupcao1.isSelected() + "");
                    break;
                case "ckbCancelAutoma":
                    hmap.replace(key, ckInterrupcao2.isSelected() + "");
                    break;
                case "ckbBloqueFunc":
                    hmap.replace(key, ckInterrupcao3.isSelected() + "");
                    break;
                case "ckbComRetaguarda":
                    hmap.replace(key, ckIntegracao1.isSelected() + "");
                    break;
                case "ckbComSisPed":
                    hmap.replace(key, ckIntegracao2.isSelected() + "");
                    break;
                case "ckbComAmbos":
                    hmap.replace(key, ckIntegracao3.isSelected() + "");
                    break;
                case "ckbNaoIntegrado":
                    hmap.replace(key, ckIntegracao4.isSelected() + "");
                    break;
                case "ckbPostoComBomb":
                    hmap.replace(key, ckEspeciais1.isSelected() + "");
                    break;
                case "ckbPostoSemBomb":
                    hmap.replace(key, ckEspeciais2.isSelected() + "");
                    break;
                case "ckbOficinaComDavOs":
                    hmap.replace(key, ckEspeciais3.isSelected() + "");
                    break;
                case "ckbOficinaComCC":
                    hmap.replace(key, ckEspeciais4.isSelected() + "");
                    break;
                case "ckbRestaEcfRestaCom":
                    hmap.replace(key, ckEspeciais5.isSelected() + "");
                    break;
                case "ckbRestaEcfRestaSem":
                    hmap.replace(key, ckEspeciais7.isSelected() + "");
                    break;
                case "ckbRestaEcfNormalCom":
                    hmap.replace(key, ckEspeciais6.isSelected() + "");
                    break;
                case "ckbRestaEcfNormalSem":
                    hmap.replace(key, ckEspeciais8.isSelected() + "");
                    break;
                case "ckbFarmacia":
                    hmap.replace(key, ckEspeciais9.isSelected() + "");
                    break;
                case "ckbTransporte":
                    hmap.replace(key, ckEspeciais10.isSelected() + "");
                    break;
                case "ckbPedagio":
                    hmap.replace(key, ckEspeciais11.isSelected() + "");
                    break;
                case "ckbEstacionamento":
                    hmap.replace(key, ckEspeciais12.isSelected() + "");
                    break;
                case "ckbCinema":
                    hmap.replace(key, ckEspeciais13.isSelected() + "");
                    break;
                case "ckbDemaisAtividades":
                    hmap.replace(key, ckEspeciais14.isSelected() + "");
                    break;
                case "ckbSimplesNacional":
                    hmap.replace(key, ckEspeciais15.isSelected() + "");
                    break;

                case "txtSGRazaoSocialCnpj":
                    String contem = sgTxtRazaoSocial.getText() + ", CNPJ: " + UtilTexto.formatarMascaraCnpj(sgTxtCNPJ.getText());
                    hmap.replace(key, contem);
                    break;
                case "txtSGNomeSistema":
                    hmap.replace(key, sgTxtNomeSistema.getText());
                    break;
                case "txtSGRequisitoExecutado":
                    String execs = "";
                    for (ArquivoExecutavelType item : sgTvTabela.getItems()) {
                        execs += item.getRequisitosExecutados();
                    }
                    hmap.replace(key, execs);
                    break;
                case "txtSGNomeArquivoMd5":
                    String temp = "";
                    for (ArquivoExecutavelType item : sgTvTabela.getItems()) {
                        temp += item.getNome() + "                              " + item.getMd5() + "\n";
                    }
                    hmap.replace(key, temp);
                    break;

                case "txtSPRazaoSocialCnpj":
                    String contem2 = spTxtEmpresaDesenvolvedora.getText() + ", CNPJ: " + UtilTexto.formatarMascaraCnpj(spTxtCNPJ.getText());
                    hmap.replace(key, contem2);
                    break;
                case "txtSPNomeSistema":
                    hmap.replace(key, spTxtNomeSistema.getText());
                    break;
                case "txtSPNomeArquivoMd5":
                    String sp = "";
                    for (ArquivoExecutavelComFuncaoType item : spTvTabela.getItems()) {
                        sp += item.getNome() + "                              " + item.getMd5();
                    }
                    hmap.replace(key, sp);
                    break;
                case "txtSPFuncao":
                    String spF = "";
                    for (ArquivoExecutavelComFuncaoType item : spTvTabela.getItems()) {
                        spF += item.getFuncao();
                    }
                    hmap.replace(key, spF);
                    break;

                case "txtSNRazaoSocialCnpj":
                    String contem3 = speTxtEmpresaDesenvolvedora.getText() + ", CNPJ: " + UtilTexto.formatarMascaraCnpj(speTxtCNPJ.getText());
                    hmap.replace(key, contem3);
                    break;
                case "txtSNNomeSistema":
                    hmap.replace(key, speTxtNomeSistema.getText());
                    break;
                case "txtSNNomeArquivoMd5":
                    String sn = "";
                    for (ArquivoExecutavelComFuncaoType item : speTvTabela.getItems()) {
                        sn += item.getNome() + "                              " + item.getMd5();
                    }
                    hmap.replace(key, sn);
                    break;

                case "txtNaoConformidadeRequisito":
                    String naoConformidade = "";
                    for (NaoConformidadeType item : nTvTabela.getItems()) {
                        naoConformidade += "Requisito " + item.getRequisito() + " item " + item.getItem() + "," + item.getDescricao() + ",";
                    }
                    hmap.replace(key, naoConformidade);
                    break;

                case "txtEcfMarca":
                    String ecfMarca = "";
                    for (MarcaModeloType item : eTvTabela.getItems()) {
                        ecfMarca += item.getMarca() + " ";
                    }
                    hmap.replace(key, ecfMarca);
                    break;
                case "txtEcfModelo":
                    String ecfModelo = "";
                    for (MarcaModeloType item : eTvTabela.getItems()) {
                        ecfModelo += item.getModelo() + " ";
                    }
                    hmap.replace(key, ecfModelo);
                    break;

                case "txtRelacaoEcf":
                    String relacaoEcf = "";
                    for (MarcaModeloType item : tTvTabela.getItems()) {
                        relacaoEcf += item.getMarca() + "," + item.getModelo() + ",";
                    }
                    hmap.replace(key, relacaoEcf);
                    break;

                case "ckbConstatada":
                    if (nTvTabela.getItems().size() > 1) {
                        hmap.replace(key, "true");
                    } else {
                        hmap.replace(key, "false");
                    }
                    break;
                case "ckbNaoConstatada":
                    if (nTvTabela.getItems().size() > 1) {
                        hmap.replace(key, "false");
                    } else {
                        hmap.replace(key, "true");
                    }
                    break;

                case "txtObservacaoOTC":
                    hmap.replace(key, fTxtArea.getText());
                    break;

                case "txtNomeAplicativo":
                    hmap.replace(key, sgTxtNomeSistema.getText());
                    break;
                case "txtDataGeracao":
                    hmap.replace(key, DateFormat.getDateInstance(DateFormat.MEDIUM).format(UtilConverter.converterLocalDateToUtilDate(fDt.getValue())));
                    break;
                case "txtMd5Relacao":
                    hmap.replace(key, iTxtMD5Outro.getText());
                    break;
                case "txtNomeArquivoEmpresa":
                    hmap.replace(key, iTxtRelacaoExec.getText());
                    break;
                case "txtMd5Empresa":
                    hmap.replace(key, iTxtMD5Relacao.getText());
                    break;
                case "txtNome":
                    hmap.replace(key, dTxtNome.getText());
                    break;
                case "txtCpf":
                    hmap.replace(key, UtilTexto.formatarMascaraCpf(dTxtCPF.getText()));
                    break;
                case "txtData":
                    hmap.replace(key, DateFormat.getDateInstance(DateFormat.LONG).format(UtilConverter.converterLocalDateToUtilDate(fDt.getValue())));
                    break;
                case "ckbComRegras":
                    if (ckbGerenciadorBD.isSelected()) {
                        hmap.replace(key, "true");
                        hmap.replace("ckbGerenciadorBD", "false");
                    } else {
                        hmap.replace(key, "false");
                        hmap.replace("ckbGerenciadorBD", "true");
                    }
                    break;
                case "txtCargoTecnico":
                    hmap.replace(key, feTxtCargo.getText());
                    break;
                case "txtTecnicoCpf":
                    hmap.replace(key, feTxtNome.getText() + " CPF: " + UtilTexto.formatarMascaraCpf(feTxtCPF.getText()));
                    break;
                case "txtCargoCoordenador":
                    hmap.replace(key, feTxtCargo2.getText());
                    break;
                case "txtCoordenadorCpf":
                    hmap.replace(key, feTxtNome2.getText() + " CPF: " + UtilTexto.formatarMascaraCpf(feTxtCPF2.getText()));
                    break;
            }
        }
        return gson.toJson(hmap);
    }

    private void finalizarAoSalvar(File criarArquivo, LaudoType lt) {
        System.out.println("Salvando arquivo XML");
        utilXml.salvarArquivo(criarArquivo, utilXml.marshal(lt));
        if (cbValidarLaudo.isSelected()) {
            if (utilXml.validarXMLSchema("xml/laudo.xsd", criarArquivo, true)) {
                salvarXMLComplementar();
                UtilDialog.criarDialogInfomation(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), "Arquivo XML validado com sucesso");
            } else {
                System.out.println("Deletou a pasta???!!!! " + utilXml.deletarDiretorio(mensagem.getDesenvolvedora().getRazaoSocial()));
            }
        } else {
            salvarXMLComplementar();
        }
    }

    private void salvarXMLComplementar() {
        carregarDiretorioXML();
//            System.out.println(utilXml.marshal(lt));
        laudoComplementar.setBairro(dTxtBairro.getText());
        laudoComplementar.setCelular(txtCelular.getText());
        laudoComplementar.setCep(dTxtCEP.getText());
        laudoComplementar.setCidade(dTxtCidade.getText());
        laudoComplementar.setCnpj(dTxtCNPJ.getText());
        laudoComplementar.setComplemento(dTxtComplemento.getText());
        laudoComplementar.setCpf(dTxtCPF.getText());
        laudoComplementar.setEmail(dTxtEmail.getText());
        laudoComplementar.setFax(txtFax.getText());
        laudoComplementar.setIe(dTxtIE.getText());
        laudoComplementar.setIm(txtIm.getText());
        laudoComplementar.setLogradouro(dTxtLogradouro.getText());
        laudoComplementar.setNomeContato(dTxtNome.getText());
        laudoComplementar.setNomeFantasia(txtNomeFantasia.getText());
        laudoComplementar.setNumero(dTxtNumero.getText());
        laudoComplementar.setRazaoSocial(dTxtRazaoSocial.getText());
        if (cbResponsavelEnsaio.getValue() == null) {
            cbResponsavelEnsaio.getSelectionModel().selectFirst();
            laudoComplementar.setResponsavelEnsaio(cbResponsavelEnsaio.getValue().getNome());
        } else {
            laudoComplementar.setResponsavelEnsaio(cbResponsavelEnsaio.getValue().getNome());
        }
        laudoComplementar.setResponsavelTeste(dTxtResponsavelTestes.getText());
        laudoComplementar.setTelefone(dTxtTelefone.getText());
        laudoComplementar.setRg(txtRg.getText());
        laudoComplementar.setUf(dTxtUF.getText());
        laudoComplementar.setPossuiSGDB(ckbGerenciadorBD.isSelected());
        laudoComplementar.setBytesExePrincipal(txtBytes.getText());
        laudoComplementar.setRipmedExePrincipal(txtRipmedPrincipal.getText());
        laudoComplementar.setRipmedTxtRelacao(txtRipmedRelacao.getText());
        File criarLaudoComplementar = new File(utilXml.getDiretorioInicial() + mensagem.getDesenvolvedora().getRazaoSocial() + "/" + mensagem.getNumero() + "_complementar.xml");
//        System.out.println("utilXml.marshal(laudoComplementar " + utilXml.marshal(laudoComplementar));
        utilXml.salvarArquivo(criarLaudoComplementar, utilXml.marshal(laudoComplementar));
        actionBtnLimpar(null);
    }

    @FXML
    private void actionBtnLimpar(ActionEvent event) {
        System.out.println("- Iniciar metodo de actionBtnLimpar");
        txtTopEmpresa.setText("Empresa?");
        mensagem = new MensagemType();

//        oTxtVersaoER.setText("02.02");
//        oTxtLogradouro.setText("Av. Juscelino Kubischeck");
//        oTxtCidade.setText("Londrina");
//        oTxtBairro.setText("Centro");
//        oTxtUF.setText("PR");
//        oTxtRazaoSocial.setText("IFL - Instituto Filadélfia de Londrina");
//        oTxtCNPJ.setText("78624202000100");
//        oTxtCEP.setText("10900000");
        txtNumeroLaudo.setText("");
        vTxtVersaoER.setText("");
        vTxtMes.setText("");
        vTxtAno.setText("");
        vTxtVersaoRoteiro.setText("");
        iTxtMD5Outro.setText("");
        iTxtNomeComercial.setText("");
        iTxtNumero.setText("");
//        iTxtMarca.setText("");
//        iTxtModelo.setText("");
        iTxtVersao.setText("");
        iTxtPrincipalExec.setText("");
        iTxtRelacaoExec.setText("");
        iTxtOutroArq.setText("");
        iTxtMD5Relacao.setText("");
        iTxtMD5Principal.setText("");
        dTxtCEP.setText("");
        dTxtLogradouro.setText("");
        dTxtBairro.setText("");
        dTxtNumero.setText("");
        dTxtNome.setText("");
        dTxtRazaoSocial.setText("");
        dTxtUF.setText("");
        dTxtComplemento.setText("");
        dTxtCPF.setText("");
        dTxtResponsavelTestes.setText("");
        dTxtTelefone.setText("");
        dTxtCNPJ.setText("");
        dTxtIE.setText("");
        dTxtCidade.setText("");
        dTxtEmail.setText("");
        fTxtLocal.setText("");
        fTxtArea.setText("");
        speTxtNomeSistema.setText("");
        speTxtCNPJ.setText("");
        speTxtEmpresaDesenvolvedora.setText("");
        spTxtNomeSistema.setText("");
        spTxtCNPJ.setText("");
        sgTxtNomeSistema.setText("");
        sgTxtCNPJ.setText("");
        sgTxtRazaoSocial.setText("");
        spTxtEmpresaDesenvolvedora.setText("");

        fDt.setValue(null);
        oDtFinal.setValue(null);
        oDtInicio.setValue(null);

        spTvTabela.getItems().clear();
        speTvTabela.getItems().clear();
        sgTvTabela.getItems().clear();
        eTvTabela.getItems().clear();
        tTvTabela.getItems().clear();
        iTvTabela.getItems().clear();
        nTvTabela.getItems().clear();
        lvLaudo.getItems().clear();

        fCkDeclaracao.setSelected(false);
        eCbMarca.getSelectionModel().clearSelection();
        rCbMarca.getSelectionModel().clearSelection();
        cbBD.getSelectionModel().clearSelection();
        cbSO.getSelectionModel().clearSelection();
        cbLinguagem.getSelectionModel().clearSelection();
        cbEmpresa.getSelectionModel().clearSelection();
        cb1.getCheckModel().clearChecks();
        cb2.getCheckModel().clearChecks();

        ckEspeciais1.setSelected(false);
        ckEspeciais2.setSelected(false);
        ckEspeciais3.setSelected(false);
        ckEspeciais4.setSelected(false);
        ckEspeciais5.setSelected(false);
        ckEspeciais6.setSelected(false);
        ckEspeciais7.setSelected(false);
        ckEspeciais8.setSelected(false);
        ckEspeciais9.setSelected(false);
        ckForma1.setSelected(false);
        ckForma2.setSelected(false);
        ckForma3.setSelected(false);
        ckForma4.setSelected(false);
        ckForma5.setSelected(false);
        ckForma6.setSelected(false);
        ckForma7.setSelected(false);
        ckProprio.setSelected(false);
        ckTerceirizado.setSelected(false);
        ckInterrupcao1.setSelected(false);
        ckInterrupcao2.setSelected(false);
        ckInterrupcao3.setSelected(false);
        ckEmite1.setSelected(false);
        ckEmite2.setSelected(false);
        ckGeracao1.setSelected(false);
        ckGeracao2.setSelected(false);
        ckGeracao3.setSelected(false);
        ckComercializavel.setSelected(false);
        ckIntegracao1.setSelected(false);
        ckIntegracao2.setSelected(false);
        ckIntegracao3.setSelected(false);
        ckIntegracao4.setSelected(false);
        ckTipo1.setSelected(false);
        ckTipo2.setSelected(false);
        ckTipo3.setSelected(false);
        ckEspeciais12.setSelected(false);
        ckEspeciais11.setSelected(false);
        ckEspeciais10.setSelected(false);
        ckEspeciais15.setSelected(false);
        ckEspeciais14.setSelected(false);
        ckEspeciais13.setSelected(false);

        iCkA.setSelected(false);
        iCkB.setSelected(false);
        iCkC.setSelected(false);
        iCkD.setSelected(false);
        iCkE.setSelected(false);
        iCkF.setSelected(false);
        iCkG.setSelected(false);
        iCkH.setSelected(false);
        iCkI.setSelected(false);
        iCkJ.setSelected(false);
        iCkR.setSelected(false);
        iCkS.setSelected(false);
        iCkT.setSelected(false);
        iCkU.setSelected(false);
        iCkX.setSelected(false);
        iCkV.setSelected(false);
        iCkW.setSelected(false);
        iCkY.setSelected(false);
        iCkZ.setSelected(false);

        cbResponsavelEnsaio.getSelectionModel().clearSelection();
        txtNomeFantasia.setText("");
        txtIm.setText("");
        txtRg.setText("");
        txtCelular.setText("");
        txtFax.setText("");
        ckbGerenciadorBD.setSelected(false);
        txtBytes.setText("");
        txtRipmedPrincipal.setText("");
        txtRipmedRelacao.setText("");
        System.out.println("- Finalizar metodo de actionBtnLimpar");
    }

    private void preenchimento(LaudoType laudo, LaudoComplementar laudoComplementar) {
        System.out.println("- Iniciar metodo de preenchimento");
        actionBtnLimpar(null);
        MensagemType m = laudo.getMensagem();
        txtNumeroLaudo.setText(m.getNumero());
        dTxtRazaoSocial.setText(m.getDesenvolvedora().getRazaoSocial());
        dTxtCNPJ.setText(m.getDesenvolvedora().getCnpj());
        dTxtIE.setText(m.getDesenvolvedora().getIe());
        dTxtResponsavelTestes.setText(m.getDesenvolvedora().getResponsavelAcompanhamentoTestes());
        dTxtLogradouro.setText(m.getDesenvolvedora().getEndereco().getLogradouro());
        dTxtNumero.setText(m.getDesenvolvedora().getEndereco().getNumero());
        dTxtComplemento.setText(m.getDesenvolvedora().getEndereco().getComplemento());
        dTxtBairro.setText(m.getDesenvolvedora().getEndereco().getBairro());
        dTxtCEP.setText(m.getDesenvolvedora().getEndereco().getCep());
        dTxtUF.setText(m.getDesenvolvedora().getEndereco().getUf());
        dTxtCidade.setText(m.getDesenvolvedora().getEndereco().getMunicipio());
        dTxtNome.setText(m.getDesenvolvedora().getContato().getNome());
        dTxtCPF.setText(m.getDesenvolvedora().getContato().getCpf());
        dTxtTelefone.setText(m.getDesenvolvedora().getContato().getTelefone());
        dTxtEmail.setText(m.getDesenvolvedora().getContato().getEmail());

        oTxtRazaoSocial.setText(m.getOtc().getRazaoSocial());
        oTxtCNPJ.setText(m.getOtc().getCnpj());
        oTxtIE.setText(m.getOtc().getIe());
        oTxtVersaoER.setText(m.getOtc().getVersaoEspecificacaoRequisitos());
        oTxtLogradouro.setText(m.getOtc().getEndereco().getLogradouro());
        oTxtNumero.setText(m.getOtc().getEndereco().getNumero());
        oTxtComplemento.setText(m.getOtc().getEndereco().getComplemento());
        oTxtBairro.setText(m.getOtc().getEndereco().getBairro());
        oTxtCEP.setText(m.getOtc().getEndereco().getCep());
        oTxtUF.setText(m.getOtc().getEndereco().getUf());
        oTxtCidade.setText(m.getOtc().getEndereco().getMunicipio());
        if (m.getOtc().getPeriodoAnalise().getDataInicio() != null) {
            oDtInicio.setValue(UtilConverter.convertXMLGregorianCalendarForDate(m.getOtc().getPeriodoAnalise().getDataInicio()));
        }
        if (m.getOtc().getPeriodoAnalise().getDataFim() != null) {
            oDtFinal.setValue(UtilConverter.convertXMLGregorianCalendarForDate(m.getOtc().getPeriodoAnalise().getDataFim()));
        }

        iTxtNomeComercial.setText(m.getIdentificacaoPaf().getNomeComercial());
        iTxtVersao.setText(m.getIdentificacaoPaf().getVersao());
        iTxtPrincipalExec.setText(m.getIdentificacaoPaf().getArquivoExecutavelPrincipal().getNome());
        iTxtMD5Principal.setText(m.getIdentificacaoPaf().getArquivoExecutavelPrincipal().getMd5());
        iTxtRelacaoExec.setText(m.getIdentificacaoPaf().getArquivoRelacaoExecutaveis().getNome());
        iTxtMD5Relacao.setText(m.getIdentificacaoPaf().getArquivoRelacaoExecutaveis().getMd5());
        iTxtOutroArq.setText(m.getIdentificacaoPaf().getArquivosOutros().getArquivoOutro().get(0).getNome());
        iTxtMD5Outro.setText(m.getIdentificacaoPaf().getArquivosOutros().getArquivoOutro().get(0).getMd5());
        iTxtMarca.setText(m.getIdentificacaoPaf().getEnvelopeSeguranca().getMarca());
        iTxtModelo.setText(m.getIdentificacaoPaf().getEnvelopeSeguranca().getModelo());
        iTxtNumero.setText(m.getIdentificacaoPaf().getEnvelopeSeguranca().getNumero());
        for (String s : m.getIdentificacaoPaf().getPerfisRequisitos().getPerfilRequisito()) {
            if (s.equals("W")) {
                iCkW.setSelected(true);
            }
            if (s.equals("V")) {
                iCkV.setSelected(true);
            }
            if (s.equals("Y")) {
                iCkY.setSelected(true);
            }
            if (s.equals("Z")) {
                iCkZ.setSelected(true);
            }
            if (s.equals("A")) {
                iCkA.setSelected(true);
            }
            if (s.equals("B")) {
                iCkB.setSelected(true);
            }
            if (s.equals("C")) {
                iCkC.setSelected(true);
            }
            if (s.equals("D")) {
                iCkD.setSelected(true);
            }
            if (s.equals("E")) {
                iCkE.setSelected(true);
            }
            if (s.equals("F")) {
                iCkF.setSelected(true);
            }
            if (s.equals("G")) {
                iCkG.setSelected(true);
            }
            if (s.equals("H")) {
                iCkH.setSelected(true);
            }
            if (s.equals("I")) {
                iCkI.setSelected(true);
            }
            if (s.equals("J")) {
                iCkJ.setSelected(true);
            }
            if (s.equals("R")) {
                iCkR.setSelected(true);
            }
            if (s.equals("S")) {
                iCkS.setSelected(true);
            }
            if (s.equals("T")) {
                iCkT.setSelected(true);
            }
            if (s.equals("X")) {
                iCkX.setSelected(true);
            }
            if (s.equals("U")) {
                iCkU.setSelected(true);
            }
        }
        ObservableList<ArquivoExecutavelSemFuncaoType> listaExecutaveis = FXCollections.observableArrayList();
        for (ArquivoExecutavelSemFuncaoType a : m.getIdentificacaoPaf().getArquivosExecutaveis().getArquivoExecutavel()) {
            listaExecutaveis.add(a);
        }
        iTvTabela.setItems(listaExecutaveis);

        cbLinguagem.getSelectionModel().selectFirst();
        cbSO.getSelectionModel().selectFirst();
        cbBD.getSelectionModel().selectFirst();
        if (m.getCaracteristicasPaf().getLinguagemProgramacao() != null) {
            while (true) {
                if (!m.getCaracteristicasPaf().getLinguagemProgramacao().equals(cbLinguagem.getSelectionModel().getSelectedItem())) {
                    cbLinguagem.getSelectionModel().selectNext();
                }
                if (m.getCaracteristicasPaf().getLinguagemProgramacao().equals(cbLinguagem.getSelectionModel().getSelectedItem())) {
                    break;
                }
            }
        }

        if (m.getCaracteristicasPaf().getSistemaOperacional() != null) {
            while (true) {
                if (!m.getCaracteristicasPaf().getSistemaOperacional().equals(cbSO.getSelectionModel().getSelectedItem())) {
                    cbSO.getSelectionModel().selectNext();
                }
                if (m.getCaracteristicasPaf().getSistemaOperacional().equals(cbSO.getSelectionModel().getSelectedItem())) {
                    break;
                }
            }
        }

        if (m.getCaracteristicasPaf().getGerenciadorBancoDados().get(0) != null && cbBD.getValue() != null) {
            while (true) {
                if (!m.getCaracteristicasPaf().getGerenciadorBancoDados().get(0).equals(cbBD.getValue())) {
                    cbBD.getSelectionModel().selectNext();
                }
                if (m.getCaracteristicasPaf().getGerenciadorBancoDados().get(0).equals(cbBD.getValue())) {
                    break;
                }
            }
        }

        if (ckComercializavel.getText().equals(m.getCaracteristicasPaf().getTipoDesenvolvimento())) {
            ckComercializavel.setSelected(true);
        } else {
            ckComercializavel.setSelected(false);
        }
        if (ckProprio.getText().equals(m.getCaracteristicasPaf().getTipoDesenvolvimento())) {
            ckProprio.setSelected(true);
        } else {
            ckProprio.setSelected(false);
        }
        if (ckTerceirizado.getText().equals(m.getCaracteristicasPaf().getTipoDesenvolvimento())) {
            ckTerceirizado.setSelected(true);
        } else {
            ckTerceirizado.setSelected(false);
        }

        for (String s : m.getCaracteristicasPaf().getFormaImpressao().getModo()) {
            if (ckForma1.getText().equals(s)) {
                ckForma1.setSelected(true);
            }
            if (ckForma2.getText().equals(s)) {
                ckForma2.setSelected(true);
            }
            if (ckForma3.getText().equals(s)) {
                ckForma3.setSelected(true);
            }
            if (ckForma4.getText().equals(s)) {
                ckForma4.setSelected(true);
            }
            if (ckForma5.getText().equals(s)) {
                ckForma5.setSelected(true);
            }
            if (ckForma6.getText().equals(s)) {
                ckForma6.setSelected(true);
            }
            if (ckForma7.getText().equals(s)) {
                ckForma7.setSelected(true);
            }

        }

        if (ckTipo1.getText().equals(m.getCaracteristicasPaf().getTipoFuncionamento())) {
            ckTipo1.setSelected(true);
        } else if (ckTipo2.getText().equals(m.getCaracteristicasPaf().getTipoFuncionamento())) {
            ckTipo2.setSelected(true);
        } else if (ckTipo3.getText().equals(m.getCaracteristicasPaf().getTipoFuncionamento())) {
            ckTipo3.setSelected(true);
        }

        for (String s : m.getCaracteristicasPaf().getMeioGeracaoArquivoSintegraEfd().getModo()) {
            if (ckGeracao1.getText().equals(s)) {
                ckGeracao1.setSelected(true);
            }
            if (ckGeracao2.getText().equals(s)) {
                ckGeracao2.setSelected(true);
            }
            if (ckGeracao3.getText().equals(s)) {
                ckGeracao3.setSelected(true);
            }
        }
        if (m.isEmiteNfe()) {
            ckEmite1.setSelected(true);
        } else {
            ckEmite1.setSelected(false);
        }

        for (String s : m.getCaracteristicasPaf().getTratamentoInterrupcao().getModo()) {
            if (ckInterrupcao1.getText().equals(s)) {
                ckInterrupcao1.setSelected(true);
            }
            if (ckInterrupcao2.getText().equals(s)) {
                ckInterrupcao2.setSelected(true);
            }
            if (ckInterrupcao3.getText().equals(s)) {
                ckInterrupcao3.setSelected(true);
            }
        }
        if (ckIntegracao1.getText().equals(m.getCaracteristicasPaf().getIntegracaoPaf())) {
            ckIntegracao1.setSelected(true);
        }
        if (ckIntegracao2.getText().equals(m.getCaracteristicasPaf().getIntegracaoPaf())) {
            ckIntegracao2.setSelected(true);
        }
        if (ckIntegracao3.getText().equals(m.getCaracteristicasPaf().getIntegracaoPaf())) {
            ckIntegracao3.setSelected(true);
        }
        if (ckIntegracao4.getText().equals(m.getCaracteristicasPaf().getIntegracaoPaf())) {
            ckIntegracao4.setSelected(true);
        }

        for (String s : m.getCaracteristicasPaf().getAplicacoesEspeciais().getAplicacaoEspecial()) {
            if (ckEspeciais1.getText().equals(s)) {
                ckEspeciais1.setSelected(true);
            } else if (ckEspeciais2.getText().equals(s)) {
                ckEspeciais2.setSelected(true);
            } else if (ckEspeciais3.getText().equals(s)) {
                ckEspeciais3.setSelected(true);
            } else if (ckEspeciais4.getText().equals(s)) {
                ckEspeciais4.setSelected(true);
            } else if (ckEspeciais5.getText().equals(s)) {
                ckEspeciais5.setSelected(true);
            } else if (ckEspeciais6.getText().equals(s)) {
                ckEspeciais6.setSelected(true);
            } else if (ckEspeciais7.getText().equals(s)) {
                ckEspeciais7.setSelected(true);
            } else if (ckEspeciais8.getText().equals(s)) {
                ckEspeciais8.setSelected(true);
            } else if (ckEspeciais9.getText().equals(s)) {
                ckEspeciais9.setSelected(true);
            } else if (ckEspeciais10.getText().equals(s)) {
                ckEspeciais10.setSelected(true);
            } else if (ckEspeciais11.getText().equals(s)) {
                ckEspeciais11.setSelected(true);
            } else if (ckEspeciais12.getText().equals(s)) {
                ckEspeciais12.setSelected(true);
            } else if (ckEspeciais13.getText().equals(s)) {
                ckEspeciais13.setSelected(true);
            } else if (ckEspeciais14.getText().equals(s)) {
                ckEspeciais14.setSelected(true);
            } else if (ckEspeciais15.getText().equals(s)) {
                ckEspeciais15.setSelected(true);
            }
        }

        sgTxtRazaoSocial.setText(m.getSistemasGestao().getSistemaGestao().get(0).getEmpresaDesenvolvedora().getRazaoSocial());
        sgTxtCNPJ.setText(m.getSistemasGestao().getSistemaGestao().get(0).getEmpresaDesenvolvedora().getCnpj());
        sgTxtNomeSistema.setText(m.getSistemasGestao().getSistemaGestao().get(0).getNome());

        ObservableList<ArquivoExecutavelType> li = FXCollections.observableArrayList();
        for (ArquivoExecutavelType a : m.getSistemasGestao().getSistemaGestao().get(0).getArquivosExecutaveis().getArquivoExecutavel()) {
            li.add(a);
        }
        sgTvTabela.setItems(li);

        spTxtEmpresaDesenvolvedora.setText(m.getSistemasPed().getSistemaPed().get(0).getEmpresaDesenvolvedora().getRazaoSocial());
        spTxtCNPJ.setText(m.getSistemasPed().getSistemaPed().get(0).getEmpresaDesenvolvedora().getCnpj());
        spTxtNomeSistema.setText(m.getSistemasPed().getSistemaPed().get(0).getNome());
        ObservableList<ArquivoExecutavelComFuncaoType> li2 = FXCollections.observableArrayList();
        for (ArquivoExecutavelComFuncaoType a : m.getSistemasPed().getSistemaPed().get(0).getArquivosExecutaveis().getArquivoExecutavel()) {
            li2.add(a);
        }
        spTvTabela.setItems(li2);

        speTxtEmpresaDesenvolvedora.setText(m.getSistemasPedNfe().getSistemaPedNfe().get(0).getEmpresaDesenvolvedora().getRazaoSocial());
        speTxtCNPJ.setText(m.getSistemasPedNfe().getSistemaPedNfe().get(0).getEmpresaDesenvolvedora().getCnpj());
        speTxtNomeSistema.setText(m.getSistemasPedNfe().getSistemaPedNfe().get(0).getNome());
        ObservableList<ArquivoExecutavelComFuncaoType> li3 = FXCollections.observableArrayList();
        for (ArquivoExecutavelComFuncaoType a : m.getSistemasPedNfe().getSistemaPedNfe().get(0).getArquivosExecutaveis().getArquivoExecutavel()) {
            li3.add(a);
        }
        speTvTabela.setItems(li3);

        ObservableList<MarcaModeloType> lmmt = FXCollections.observableArrayList();
        for (MarcaModeloType marcaModeloType : m.getEcfAnaliseFuncional().getMarcaModelo()) {
            lmmt.add(marcaModeloType);
        }
        eTvTabela.setItems(lmmt);

        ObservableList<MarcaModeloType> lmmt2 = FXCollections.observableArrayList();
        for (MarcaModeloType marcaModeloType : m.getMarcasModelosCompativeis().getMarcaModelo()) {
            lmmt2.add(marcaModeloType);
        }
        tTvTabela.setItems(lmmt2);

        vTxtVersaoER.setText(m.getVersaoErPaf());
        vTxtVersaoRoteiro.setText(m.getRoteiroAnalise().getVersaoRoteiro());
        vTxtMes.setText(m.getRoteiroAnalise().getMes());
        vTxtAno.setText(m.getRoteiroAnalise().getAno());

        ObservableList<NaoConformidadeType> lnct = FXCollections.observableArrayList();
        for (NaoConformidadeType nct : m.getNaoConformidades().getNaoConformidade()) {
            lnct.add(nct);
        }
        nTvTabela.setItems(lnct);

        fTxtArea.setText(m.getComentarioOtc());

        if (m.isDeclaracao()) {
            fCkDeclaracao.setSelected(true);
        } else {
            fCkDeclaracao.setSelected(false);
        }

        fTxtLocal.setText(m.getEmissao().getLocal());
        if (m.getEmissao().getData() != null) {
            fDt.setValue(UtilConverter.convertXMLGregorianCalendarForDate(m.getEmissao().getData()));
        }
        feTxtNome.setText(m.getExecucaoTestes().getNome());
        feTxtCPF.setText(m.getExecucaoTestes().getCpf());
        feTxtCargo.setText(m.getExecucaoTestes().getCargo());
        feTxtNome2.setText(m.getAprovacaoRelatorio().getNome());
        feTxtCPF2.setText(m.getAprovacaoRelatorio().getCpf());
        feTxtCargo2.setText(m.getAprovacaoRelatorio().getCargo());

        if (laudoComplementar != null) {
            for (Usuario item : cbResponsavelEnsaio.getItems()) {
                if (item.getNome().equals(laudoComplementar.getResponsavelEnsaio())) {
                    cbResponsavelEnsaio.getSelectionModel().select(item);
                    break;
                }
            }
            txtNomeFantasia.setText(laudoComplementar.getNomeFantasia());
            txtRg.setText(laudoComplementar.getRg());
            txtCelular.setText(laudoComplementar.getCelular());
            txtFax.setText(laudoComplementar.getFax());
            ckbGerenciadorBD.setSelected(laudoComplementar.getPossuiSGDB());
            txtBytes.setText(laudoComplementar.getBytesExePrincipal());
            txtRipmedPrincipal.setText(laudoComplementar.getRipmedExePrincipal());
            txtRipmedRelacao.setText(laudoComplementar.getRipmedTxtRelacao());
            txtIm.setText(laudoComplementar.getIm());
        }

        txtNLaudo.setText(txtNumeroLaudo.getText());
        System.out.println("- Finalizar metodo de preenchimento");
    }

    @FXML
    public void actionBtnIAdd() {
        showWizardAddArqExecut();
    }
    private boolean buscouArquivo = false;

    private void showWizardAddArqExecut() {
        ObservableList<ArquivoExecutavelSemFuncaoType> listaExecutaveis = FXCollections.observableArrayList();
        // define pages to show
        Wizard wizard = new Wizard();
        wizard.setTitle("Adicionar nomes de arquivos executaveis");
        // --- page 1
        int row = 0;
        GridPane page1Grid = new GridPane();
        page1Grid.setVgap(10);
        page1Grid.setHgap(10);
        page1Grid.add(new Label("Arq. Executavel:"), 0, 0);
        page1Grid.add(new Label("MD5:"), 0, 1);
        page1Grid.add(new Separator(), 0, 2, 2, 2);
        page1Grid.add(new Label("Escolher arquivo: "), 0, 4);
        TextField txArq = createTextField("arq");
        TextField txMd5 = createTextField("md5");
        Button btnChoose = createButton("fileChoose", "Buscar");
        page1Grid.add(txArq, 1, row++);
        page1Grid.add(txMd5, 1, row++);
        page1Grid.add(btnChoose, 1, 4);

        btnChoose.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Busca arquivo MD5");
                File file = fileChooser.showOpenDialog(stage);
//                System.out.println("CAMINHO: " + file.getAbsoluteFile());
                BufferedReader buf = null;
                try {
                    buf = new BufferedReader(new FileReader(file));
                    String line = "";
                    while ((line = buf.readLine()) != null) {
                        if (line.contains("N3")) {
                            buscouArquivo = true;
//                            System.out.println("LINE - " + line);
//                            System.out.println("DLLs - " + line.substring(2, 51).replace(" ", ""));
//                            System.out.println("MD5 - " + line.substring(52, 84).replace(" ", ""));

                            ArquivoExecutavelSemFuncaoType aesft = new ArquivoExecutavelSemFuncaoType();
                            aesft.setNome(line.substring(2, 51).replace(" ", ""));
                            aesft.setMd5(line.substring(52, 84).replace(" ", ""));
                            listaExecutaveis.add(aesft);
                        }
                    }
                    if (buscouArquivo == false) {
                        UtilDialog.criarDialogWarning(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.LaudoWarningArquivoInvalido.getMensagem());

                    }

                } catch (FileNotFoundException ex) {
                    Logger.getLogger(LaudoController.class
                            .getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(LaudoController.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        final WizardPane page1 = new WizardPane() {
            @Override
            public void onExitingPage(Wizard wizard) {
            }
        };
        page1.setHeaderText("Informe o nome do arquivo");
        page1.setContent(page1Grid);

        wizard.setFlow(new LinearFlow(page1));
        wizard.showAndWait().ifPresent(result -> {
            if (result == ButtonType.FINISH) {
                if (buscouArquivo) {
                    iTvTabela.setItems(listaExecutaveis);
                    buscouArquivo = false;
                } else {
                    ObservableList<ArquivoExecutavelSemFuncaoType> listaExecutaveis2 = iTvTabela.getItems();
                    ArquivoExecutavelSemFuncaoType aesft = new ArquivoExecutavelSemFuncaoType();
                    aesft.setNome(txArq.getText());
                    aesft.setMd5(txMd5.getText());
                    listaExecutaveis2.add(aesft);
                    iTvTabela.setItems(listaExecutaveis2);
                }
                System.out.println("Wizard finished, settings: " + wizard.getSettings());
            }
        });
    }

    @FXML
    public void actionBtnIRemove() {
        if (iTvTabela.getSelectionModel().getSelectedItem() != null) {
            ObservableList<ArquivoExecutavelSemFuncaoType> listaExecutaveis = iTvTabela.getItems();
            listaExecutaveis.remove(iTvTabela.getSelectionModel().getSelectedItem());
            iTvTabela.setItems(listaExecutaveis);
        }
    }

    @FXML
    private void actionBtnSGAdd() {
        Wizard wizard = new Wizard();
        wizard.setTitle("Adicionar nomes de arquivos executaveis");
        // --- page 1
        int row = 0;
        GridPane page1Grid = new GridPane();
        page1Grid.setVgap(10);
        page1Grid.setHgap(10);
        page1Grid.add(new Label("Nome:"), 0, row);
        page1Grid.add(new Label("MD5:"), 0, (row + 1));
        page1Grid.add(new Label("Requisitos:"), 0, (row + 2));
        TextField txArq = createTextField("nome");
        TextField txMd5 = createTextField("md5");
        TextField txRequisito = createTextField("requisitos");
        page1Grid.add(txArq, 1, row++);
        page1Grid.add(txMd5, 1, row++);
        page1Grid.add(txRequisito, 1, row++);

        final WizardPane page1 = new WizardPane() {
            @Override
            public void onExitingPage(Wizard wizard) {
            }
        };
        page1.setHeaderText("Informe!!");
        page1.setContent(page1Grid);

        wizard.setFlow(new LinearFlow(page1));
        wizard.showAndWait().ifPresent(result -> {
            if (result == ButtonType.FINISH) {
                ObservableList<ArquivoExecutavelType> li = sgTvTabela.getItems();
                ArquivoExecutavelType aet = new ArquivoExecutavelType();
                aet.setNome(txArq.getText());
                aet.setMd5(txMd5.getText());
                RequisitosExecutadosType ret = new RequisitosExecutadosType();
                ret.getRequisitoExecutado().add(txRequisito.getText());
                aet.setRequisitosExecutados(ret);
                li.add(aet);
                sgTvTabela.setItems(li);
                System.out.println("Wizard finished, settings: " + wizard.getSettings());
            }
        });
    }

    @FXML
    private void actionBtnSGRemove() {
        if (sgTvTabela.getSelectionModel().getSelectedItem() != null) {
            ObservableList<ArquivoExecutavelType> li = sgTvTabela.getItems();
            li.remove(sgTvTabela.getSelectionModel().getSelectedItem());
            sgTvTabela.setItems(li);
        }
    }

    @FXML
    private void actionBtnSPAdd() {
        createWizardForSP();
    }

    private void createWizardForSP() {
        Wizard wizard = new Wizard();
        wizard.setTitle("Adicionar nomes de arquivos executaveis");
        // --- page 1
        int row = 0;
        GridPane page1Grid = new GridPane();
        page1Grid.setVgap(10);
        page1Grid.setHgap(10);
        page1Grid.add(new Label("Nome:"), 0, row);
        page1Grid.add(new Label("MD5:"), 0, (row + 1));
        page1Grid.add(new Label("Função:"), 0, (row + 2));
        TextField txArq = createTextField("nome");
        TextField txMd5 = createTextField("md5");
        TextField txFuncao = createTextField("funcao");
        page1Grid.add(txArq, 1, row++);
        page1Grid.add(txMd5, 1, row++);
        page1Grid.add(txFuncao, 1, row++);

        final WizardPane page1 = new WizardPane() {
            @Override
            public void onExitingPage(Wizard wizard) {
            }
        };
        page1.setHeaderText("Informe!!");
        page1.setContent(page1Grid);

        wizard.setFlow(new LinearFlow(page1));
        wizard.showAndWait().ifPresent(result -> {
            if (result == ButtonType.FINISH) {
                ObservableList<ArquivoExecutavelComFuncaoType> li = null;
                ArquivoExecutavelComFuncaoType aecft = new ArquivoExecutavelComFuncaoType();
                aecft.setNome(txArq.getText());
                aecft.setMd5(txMd5.getText());
                aecft.setFuncao(txFuncao.getText());
                if (sp.isSelected()) {
                    li = spTvTabela.getItems();
                    li.add(aecft);
                    spTvTabela.setItems(li);
                } else if (spe.isSelected()) {
                    li = speTvTabela.getItems();
                    li.add(aecft);
                    speTvTabela.setItems(li);
                }
            }
        });
    }

    @FXML
    private void actionBtnSPRemove() {
        if (spTvTabela.getSelectionModel().getSelectedItem() != null) {
            ObservableList<ArquivoExecutavelComFuncaoType> li = spTvTabela.getItems();
            li.remove(spTvTabela.getSelectionModel().getSelectedItem());
            spTvTabela.setItems(li);
        }
    }

    @FXML
    private void actionBtnSPEAdd() {
        createWizardForSP();
    }

    @FXML
    private void actionBtnSPERemove() {
        if (speTvTabela.getSelectionModel().getSelectedItem() != null) {
            ObservableList<ArquivoExecutavelComFuncaoType> li = speTvTabela.getItems();
            li.remove(speTvTabela.getSelectionModel().getSelectedItem());
            speTvTabela.setItems(li);
        }
    }

    @FXML
    private void actionCbEMarca() {
        cb1.getItems().clear();
        cb2.getItems().clear();
        ObservableList<String> liModelo = FXCollections.observableArrayList();
        if (mct != null) {
            for (MarcaModeloType mmt : mct.getMarcaModelo()) {
                if (ecfU.isSelected()) {
                    if (mmt.getMarca().equals(eCbMarca.getValue().toString())) {
                        if (!liModelo.contains(mmt.getModelo())) {
                            liModelo.add(mmt.getModelo());
                        }
                    }
                } else if (relacaoE.isSelected()) {
                    if (mmt.getMarca().equals(rCbMarca.getValue().toString())) {
                        if (!liModelo.contains(mmt.getModelo())) {
                            liModelo.add(mmt.getModelo());
                        }
                    }
                }
            }
        }
        if (ecfU.isSelected()) {
            cb1.getCheckModel().clearChecks();
            cb1.getItems().setAll(liModelo);
        } else if (relacaoE.isSelected()) {
            cb2.getCheckModel().clearChecks();
            cb2.getItems().setAll(liModelo);
        }
    }

    private void preencherCbMarca() {
        mct = utilXml.getMarcaEModeloECF();
        ObservableList<String> liMarca = FXCollections.observableArrayList();
        for (MarcaModeloType mmt : mct.getMarcaModelo()) {
            if (!liMarca.contains(mmt.getMarca())) {
                liMarca.add(mmt.getMarca());
            }
        }
        eCbMarca.setItems(liMarca);
        rCbMarca.setItems(liMarca);
    }

    @FXML
    private void actionBtnERemove() {
        ObservableList<MarcaModeloType> li = null;
        if (ecfU.isSelected()) {
            if (eTvTabela.getItems().size() > 0) {
                if (cbSelecionarTodos.isSelected()) {
                    eTvTabela.getItems().clear();
                } else {
                    if (eTvTabela.getSelectionModel().getSelectedItem() != null) {
                        li = eTvTabela.getItems();
                        li.remove(eTvTabela.getSelectionModel().getSelectedItem());
                        eTvTabela.setItems(li);
                    } else {
                        UtilDialog.criarDialogWarning(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.LaudoInformeLinhaNaoSelecionada.getMensagem());
                    }
                }
            } else {
                UtilDialog.criarDialogWarning(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.LaudoInformeTabelaVazia.getMensagem());
            }
        } else if (relacaoE.isSelected()) {
            if (tTvTabela.getItems().size() > 0) {
                if (cbSelecionarTodos2.isSelected()) {
                    tTvTabela.getItems().clear();
                } else {
                    if (tTvTabela.getSelectionModel().getSelectedItem() != null) {
                        li = tTvTabela.getItems();
                        li.remove(tTvTabela.getSelectionModel().getSelectedItem());
                        tTvTabela.setItems(li);
                    } else {
                        UtilDialog.criarDialogWarning(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.LaudoInformeLinhaNaoSelecionada.getMensagem());
                    }
                }
            } else {
                UtilDialog.criarDialogWarning(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.LaudoInformeTabelaVazia.getMensagem());
            }
        }
    }

    private boolean existe = false;
    private String msgExiste;

    @FXML
    private void actionBtnEAdd() {
        ObservableList<MarcaModeloType> lmmt = null;
        MarcaModeloType mmt = null;
        if (ecfU.isSelected()) {
            if (eCbMarca.getSelectionModel().getSelectedItem() != null && !cb1.getCheckModel().getCheckedItems().isEmpty() || cbSelecionarTodos.isSelected()) {
                lmmt = eTvTabela.getItems();
                if (cbSelecionarTodos.isSelected()) {
                    for (String s : cb1.getItems()) {
                        existe = false;
                        lmmt.stream().forEach((string) -> {
                            if (string.getModelo().equals(s)) {//TODO
                                existe = true;
                            }
                        });
                        if (!existe) {
                            mmt = new MarcaModeloType();
                            mmt.setMarca(eCbMarca.getValue().toString());
                            mmt.setModelo(s);
                            lmmt.add(mmt);
                        }
                    }
                } else {
                    for (String s : cb1.getCheckModel().getCheckedItems()) {
                        existe = false;
                        lmmt.stream().forEach((string) -> {
                            if (string.getModelo().equals(s)) {//TODO
//                                System.out.println("EXISTE " + string.getModelo());
//                                System.out.println("SS -> " + s);
                                existe = true;
                            }
                        });
                        if (!existe) {
                            mmt = new MarcaModeloType();
                            mmt.setMarca(eCbMarca.getValue().toString());
                            mmt.setModelo(s);
                            lmmt.add(mmt);
                        } else {
                            UtilDialog.criarDialogWarning(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), s + " -> já existe");
                        }
                    }
                }
                cb1.getCheckModel().clearChecks();
                eTvTabela.setItems(lmmt);
            } else {
                UtilDialog.criarDialogWarning(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.LaudoInformeMarcaModelo.getMensagem());
            }
        } else if (relacaoE.isSelected()) {
            if (rCbMarca.getSelectionModel().getSelectedItem() != null && !cb2.getCheckModel().getCheckedItems().isEmpty() || cbSelecionarTodos2.isSelected()) {
                lmmt = tTvTabela.getItems();
                if (cbSelecionarTodos2.isSelected()) {
                    for (String s : cb2.getItems()) {
                        existe = false;
                        lmmt.stream().forEach((string) -> {
                            if (string.getModelo().equals(s)) {//TODO
                                existe = true;
                            }
                        });
                        if (!existe) {
                            mmt = new MarcaModeloType();
                            mmt.setMarca(rCbMarca.getValue().toString());
                            mmt.setModelo(s);
                            lmmt.add(mmt);
                        }
                    }
                } else {
                    for (String s : cb2.getCheckModel().getCheckedItems()) {
                        existe = false;
                        lmmt.stream().forEach((string) -> {
                            if (string.getModelo().equals(s)) {//TODO
                                existe = true;
                            }
                        });
                        if (!existe) {
                            mmt = new MarcaModeloType();
                            mmt.setMarca(rCbMarca.getValue().toString());
                            mmt.setModelo(s);
                            lmmt.add(mmt);
                        } else {
                            UtilDialog.criarDialogWarning(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), s + " -> já existe");
                        }
                    }

                }
                cb2.getCheckModel().clearChecks();
                tTvTabela.setItems(lmmt);
            } else {
                UtilDialog.criarDialogWarning(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.LaudoInformeMarcaModelo.getMensagem());
            }
        }
    }

    @FXML
    private void actionBtnNAdd() {
        Wizard wizard = new Wizard();
        wizard.setTitle("Informe!!!!");
        // --- page 1
        int row = 0;
        GridPane page1Grid = new GridPane();
        page1Grid.setVgap(10);
        page1Grid.setHgap(10);
        page1Grid.add(new Label("Requisito:"), 0, row);
        page1Grid.add(new Label("Item:"), 0, (row + 1));
        page1Grid.add(new Label("Descrição:"), 0, (row + 2));
        TextField txRequisito = createTextField("requisito");
        TextField txItem = createTextField("item");
        TextField txDescricao = createTextField("descricao");
        page1Grid.add(txRequisito, 1, row++);
        page1Grid.add(txItem, 1, row++);
        page1Grid.add(txDescricao, 1, row++);

        final WizardPane page1 = new WizardPane() {
            @Override
            public void onExitingPage(Wizard wizard) {
            }
        };
        page1.setHeaderText("Informe!!");
        page1.setContent(page1Grid);

        wizard.setFlow(new LinearFlow(page1));
        wizard.showAndWait().ifPresent(result -> {
            if (result == ButtonType.FINISH) {
                ObservableList<NaoConformidadeType> listNcts = nTvTabela.getItems();
                NaoConformidadeType nct = new NaoConformidadeType();
                nct.setRequisito(txRequisito.getText());
                nct.setItem(txItem.getText());
                nct.setDescricao(txDescricao.getText());
                listNcts.add(nct);
                nTvTabela.setItems(listNcts);
            }
        });
    }

    @FXML
    private void actionBtnNRemove() {
        if (nTvTabela.getSelectionModel().getSelectedItem() != null) {
            ObservableList<NaoConformidadeType> listNcts = nTvTabela.getItems();
            listNcts.remove(nTvTabela.getSelectionModel().getSelectedItem());
            nTvTabela.setItems(listNcts);
        }
    }

    @FXML
    private void actionCbEmpresa() {
        if (cbEmpresa.getSelectionModel().getSelectedItem() != null) {
            utilXml.setLaudos(FXCollections.observableArrayList());
            utilXml.listarArquivos(new File(utilXml.getDiretorioInicial() + cbEmpresa.getSelectionModel().getSelectedItem().toString()));
            lvLaudo.setItems(utilXml.getLaudos());
        }
    }

    @FXML
    private void actionBtnCarregar() {
        System.out.println("- Iniciar metodo actionBtnCarregar");

        if (lvLaudo.getSelectionModel().getSelectedItem() != null) {
            LaudoType l = (LaudoType) utilXml.unmarshalFromFile(LaudoType.class, utilXml.getDiretorioInicial() + cbEmpresa.getSelectionModel().getSelectedItem().toString() + "/" + lvLaudo.getSelectionModel().getSelectedItem().toString());
            String comp = lvLaudo.getSelectionModel().getSelectedItem().toString().substring(0, lvLaudo.getSelectionModel().getSelectedItem().toString().indexOf("."));
            comp += "_complementar.xml";
            laudoComplementar = (LaudoComplementar) utilXml.unmarshalFromFile(LaudoComplementar.class, utilXml.getDiretorioInicial() + cbEmpresa.getSelectionModel().getSelectedItem().toString() + "/" + comp);
//            System.out.println(
//                    "Laudo a ser carregado " + l);
            preenchimento(l, laudoComplementar);
            tpPrincipal.getSelectionModel()
                    .select(tabComplementar);
            txtTopEmpresa.setText(dTxtRazaoSocial.getText());
        } else if (lvLaudo.getSelectionModel().getSelectedItems().size() == 1) {
            LaudoType l = (LaudoType) utilXml.unmarshalFromFile(LaudoType.class, utilXml.getDiretorioInicial() + cbEmpresa.getSelectionModel().getSelectedItem().toString() + "/" + lvLaudo.getSelectionModel().getSelectedItems().get(0));
            String comp = lvLaudo.getSelectionModel().getSelectedItem().toString().substring(0, lvLaudo.getSelectionModel().getSelectedItem().toString().indexOf("."));
            comp += "_complementar.xml";
            laudoComplementar = (LaudoComplementar) utilXml.unmarshalFromFile(LaudoComplementar.class, utilXml.getDiretorioInicial() + cbEmpresa.getSelectionModel().getSelectedItem().toString() + "/" + comp);
//            System.out.println(
//                    "Laudo a ser carregado " + l);
            preenchimento(l, laudoComplementar);

            tpPrincipal.getSelectionModel()
                    .select(tabComplementar);
            txtTopEmpresa.setText(dTxtRazaoSocial.getText());
        }
        System.out.println("- Finalizar metodo actionBtnCarregar");
    }

    @FXML
    private void actionBtnAbrir() {
        String fi = utilXml.getDiretorioInicial() + cbEmpresa.getSelectionModel().getSelectedItem().toString() + "/" + lvLaudo.getSelectionModel().getSelectedItem().toString();
        try {
            java.awt.Desktop.getDesktop().open(new File(fi));

        } catch (IOException ex) {
            Logger.getLogger(LaudoController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void enableEditAllTable() {
        iTcNome.setCellFactory(TextFieldTableCell.<ArquivoExecutavelSemFuncaoType>forTableColumn());
        iTcNome.setOnEditCommit(
                new EventHandler<CellEditEvent<ArquivoExecutavelSemFuncaoType, String>>() {
                    @Override
                    public void handle(CellEditEvent<ArquivoExecutavelSemFuncaoType, String> t) {
                        ((ArquivoExecutavelSemFuncaoType) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setNome(t.getNewValue());
                    }
                }
        );
        iTcMd5.setCellFactory(TextFieldTableCell.<ArquivoExecutavelSemFuncaoType>forTableColumn());
        iTcMd5.setOnEditCommit(
                new EventHandler<CellEditEvent<ArquivoExecutavelSemFuncaoType, String>>() {
                    @Override
                    public void handle(CellEditEvent<ArquivoExecutavelSemFuncaoType, String> t) {
                        ((ArquivoExecutavelSemFuncaoType) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setMd5(t.getNewValue());
                    }
                }
        );

        tcSGNome.setCellFactory(TextFieldTableCell.<ArquivoExecutavelType>forTableColumn());
        tcSGNome.setOnEditCommit(
                new EventHandler<CellEditEvent<ArquivoExecutavelType, String>>() {
                    @Override
                    public void handle(CellEditEvent<ArquivoExecutavelType, String> t) {
                        ((ArquivoExecutavelType) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setNome(t.getNewValue());
                    }
                }
        );

        tcSGMD5.setCellFactory(TextFieldTableCell.<ArquivoExecutavelType>forTableColumn());
        tcSGMD5.setOnEditCommit(
                new EventHandler<CellEditEvent<ArquivoExecutavelType, String>>() {
                    @Override
                    public void handle(CellEditEvent<ArquivoExecutavelType, String> t) {
                        ((ArquivoExecutavelType) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setMd5(t.getNewValue());
                    }
                }
        );

        tcSGRequisitos.setCellFactory(TextFieldTableCell.<ArquivoExecutavelType>forTableColumn());
        tcSGRequisitos.setOnEditCommit(
                new EventHandler<CellEditEvent<ArquivoExecutavelType, String>>() {
                    @Override
                    public void handle(CellEditEvent<ArquivoExecutavelType, String> t) {
                        RequisitosExecutadosType qet = new RequisitosExecutadosType();
                        qet.getRequisitoExecutado().add(t.getNewValue());
                        ((ArquivoExecutavelType) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setRequisitosExecutados(qet);
                    }
                }
        );

        tcSpNome.setCellFactory(TextFieldTableCell.<ArquivoExecutavelComFuncaoType>forTableColumn());
        tcSpNome.setOnEditCommit(
                new EventHandler<CellEditEvent<ArquivoExecutavelComFuncaoType, String>>() {
                    @Override
                    public void handle(CellEditEvent<ArquivoExecutavelComFuncaoType, String> t) {
                        ((ArquivoExecutavelComFuncaoType) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setNome(t.getNewValue());
                    }
                }
        );

        tcSpMD5.setCellFactory(TextFieldTableCell.<ArquivoExecutavelComFuncaoType>forTableColumn());
        tcSpMD5.setOnEditCommit(
                new EventHandler<CellEditEvent<ArquivoExecutavelComFuncaoType, String>>() {
                    @Override
                    public void handle(CellEditEvent<ArquivoExecutavelComFuncaoType, String> t) {
                        ((ArquivoExecutavelComFuncaoType) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setMd5(t.getNewValue());
                    }
                }
        );

        tcSpFuncao.setCellFactory(TextFieldTableCell.<ArquivoExecutavelComFuncaoType>forTableColumn());
        tcSpFuncao.setOnEditCommit(
                new EventHandler<CellEditEvent<ArquivoExecutavelComFuncaoType, String>>() {
                    @Override
                    public void handle(CellEditEvent<ArquivoExecutavelComFuncaoType, String> t) {
                        ((ArquivoExecutavelComFuncaoType) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setFuncao(t.getNewValue());
                    }
                }
        );

        tcSpeNome.setCellFactory(TextFieldTableCell.<ArquivoExecutavelComFuncaoType>forTableColumn());
        tcSpeNome.setOnEditCommit(
                new EventHandler<CellEditEvent<ArquivoExecutavelComFuncaoType, String>>() {
                    @Override
                    public void handle(CellEditEvent<ArquivoExecutavelComFuncaoType, String> t) {
                        ((ArquivoExecutavelComFuncaoType) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setNome(t.getNewValue());
                    }
                }
        );

        tcSpeMD5.setCellFactory(TextFieldTableCell.<ArquivoExecutavelComFuncaoType>forTableColumn());
        tcSpeMD5.setOnEditCommit(
                new EventHandler<CellEditEvent<ArquivoExecutavelComFuncaoType, String>>() {
                    @Override
                    public void handle(CellEditEvent<ArquivoExecutavelComFuncaoType, String> t) {
                        ((ArquivoExecutavelComFuncaoType) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setMd5(t.getNewValue());
                    }
                }
        );

        tcSpeFuncao.setCellFactory(TextFieldTableCell.<ArquivoExecutavelComFuncaoType>forTableColumn());
        tcSpeFuncao.setOnEditCommit(
                new EventHandler<CellEditEvent<ArquivoExecutavelComFuncaoType, String>>() {
                    @Override
                    public void handle(CellEditEvent<ArquivoExecutavelComFuncaoType, String> t) {
                        ((ArquivoExecutavelComFuncaoType) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setFuncao(t.getNewValue());
                    }
                }
        );

        tcRequisito.setCellFactory(TextFieldTableCell.<NaoConformidadeType>forTableColumn());
        tcRequisito.setOnEditCommit(
                new EventHandler<CellEditEvent<NaoConformidadeType, String>>() {
                    @Override
                    public void handle(CellEditEvent<NaoConformidadeType, String> t) {
                        ((NaoConformidadeType) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setRequisito(t.getNewValue());
                    }
                }
        );

        tcItem.setCellFactory(TextFieldTableCell.<NaoConformidadeType>forTableColumn());
        tcItem.setOnEditCommit(
                new EventHandler<CellEditEvent<NaoConformidadeType, String>>() {
                    @Override
                    public void handle(CellEditEvent<NaoConformidadeType, String> t) {
                        ((NaoConformidadeType) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setItem(t.getNewValue());
                    }
                }
        );

        tcDescricao.setCellFactory(TextFieldTableCell.<NaoConformidadeType>forTableColumn());
        tcDescricao.setOnEditCommit(
                new EventHandler<CellEditEvent<NaoConformidadeType, String>>() {
                    @Override
                    public void handle(CellEditEvent<NaoConformidadeType, String> t) {
                        ((NaoConformidadeType) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setDescricao(t.getNewValue());
                    }
                }
        );
    }

    @FXML
    public void onActionGerarDocs() {
        if (tBtnGerarDocs.isSelected()) {
            SceneManager.getInstance().showEscolherDocs();
        }
    }

    @FXML
    private void actionBtnBuscarEmpresa() {
        UtilDialog.criarDialogWarning(EnumMensagem.Aviso.getTitulo(), EnumMensagem.Aviso.getSubTitulo(), "Campos relacionado a empresa será substituido ao identificar");
        SceneManager.getInstance().showTabelaEmpresa(false, false, false, false, false, false, true);
    }

    public void preencherComEmpresaIdentificada(Empresa empresa) {
        EnderecoService es = new EnderecoService();
        Endereco endereco = null;
        for (Endereco e : es.findByIdEmpresa(empresa)) {
            if (e.selecionadoBoolean()) {
                endereco = e;
                break;
            }
        }
        JPA.em(false).close();
        ContatoService cs = new ContatoService();
        Contato contato = null;
        for (Contato c : cs.findByIdEmpresa(empresa)) {
            if (c.selecionadoBoolean()) {
                contato = c;
                break;
            }
        }
        JPA.em(false).close();
        TelefoneService ts = new TelefoneService();
        Telefone tel = null;
        for (Telefone t : ts.findByIdEmpresa(empresa)) {
            if (t.selecionadoBoolean()) {
                tel = t;
                break;
            }
        }
        JPA.em(false).close();

        sgTxtRazaoSocial.setText(empresa.getDescricao());
        sgTxtCNPJ.setText(UtilTexto.removeAllSimbolsExceptNumber(empresa.getCnpj()));
        spTxtEmpresaDesenvolvedora.setText(empresa.getDescricao());
        spTxtCNPJ.setText(UtilTexto.removeAllSimbolsExceptNumber(empresa.getCnpj()));
        speTxtEmpresaDesenvolvedora.setText(empresa.getDescricao());
        speTxtCNPJ.setText(UtilTexto.removeAllSimbolsExceptNumber(empresa.getCnpj()));

        txtTopEmpresa.setText(empresa.getDescricao());
        laudoComplementar = new LaudoComplementar();
        laudoComplementar.setIdEmpresa(empresa.getId());
        txtNomeFantasia.setText(empresa.getNomeFantasia());
        txtIm.setText(empresa.getInscricaoMunicipal());
        dTxtRazaoSocial.setText(empresa.getDescricao());
        dTxtCNPJ.setText(UtilTexto.removeAllSimbolsExceptNumber(empresa.getCnpj()));
        dTxtIE.setText(empresa.getInscricaoEstadual());

        txtCelular.setText(UtilTexto.removeAllSimbolsExceptNumber(tel.getCelular()));
        txtFax.setText(UtilTexto.removeAllSimbolsExceptNumber(tel.getFax()));
        dTxtTelefone.setText(UtilTexto.removeAllSimbolsExceptNumber(tel.getFixo()));

        dTxtLogradouro.setText(endereco.getLogradouro());
        dTxtNumero.setText(endereco.getNumero());
        dTxtComplemento.setText(endereco.getComplemento());
        dTxtBairro.setText(endereco.getBairro());
        dTxtCEP.setText(UtilTexto.removeAllSimbolsExceptNumber(endereco.getCep()));
        dTxtUF.setText(endereco.getIdCidade().getIdEstado().getUf());
        dTxtCidade.setText(endereco.getIdCidade().getNome());

        dTxtResponsavelTestes.setText(contato.getResponsavelTeste());
        dTxtNome.setText(contato.getNome());
        dTxtCPF.setText(UtilTexto.removeAllSimbolsExceptNumber(contato.getCpf()));
        dTxtEmail.setText(contato.getEmail());
        txtRg.setText(UtilTexto.removeAllSimbolsExceptNumber(contato.getRg()));

    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setMain(VBox main) {
        this.main = main;
    }

    public void setFiles(ObservableList<String> files) {
        this.files = files;
    }

    private boolean validarCampos() {
        String preencher = "";
        boolean erro = false;
        if (tBtnGerarDocs.isSelected()) {
            if (cbResponsavelEnsaio.getValue() == null) {
                preencher += "Selecionar Usuário\n";
                erro = true;
            }
            if (txtNomeFantasia.getText().equals("")) {
                preencher += "Preencher nome fantasia\n";
                erro = true;
            }
            if (txtIm.getText().equals("")) {
                preencher += "Preencher Inscrição municipal\n";
                erro = true;
            }
            if (txtBytes.getText().equals("")) {
                preencher += "Preencher tamanho em bytes do exe principal da empresa\n";
                erro = true;
            }

            if (txtRipmedPrincipal.getText().equals("")) {
                preencher += "Preencher o RIPMED do exec principal\n";
                erro = true;
            }

            if (txtRipmedRelacao.getText().equals("")) {
                preencher += "Preencher o RIPMED do txt relação\n";
                erro = true;
            }
            if (laudoComplementar == null) {
                preencher += "Identificar empresa\n";
                erro = true;
            }
        }
        if (txtNumeroLaudo.getText().equals("")) {
            preencher += "Preencher número do laudo\n";
            erro = true;
        }
        if (!preencher.equals("")) {
            UtilDialog.criarDialogWarning(EnumMensagem.Padrao.getTitulo(), preencher, "");
        }
        return erro;

    }

    public static class ProgressForm {

        private final Stage dialogStage;
        private final ProgressBar pb = new ProgressBar();
        private final ProgressIndicator pin = new ProgressIndicator();

        public ProgressForm() {
            dialogStage = new Stage();
            dialogStage.initStyle(StageStyle.UTILITY);
            dialogStage.setResizable(false);
            dialogStage.initModality(Modality.APPLICATION_MODAL);

            // PROGRESS BAR
            final Label label = new Label();
            label.setText("alerto");

            pb.setProgress(-1F);
            pin.setProgress(-1F);

            final HBox hb = new HBox();
            hb.setSpacing(5);
            hb.setAlignment(Pos.CENTER);
            hb.getChildren().addAll(pb, pin);

            Scene scene = new Scene(hb);
            dialogStage.setScene(scene);
        }

        public void activateProgressBar(final Task<?> task) {
            pb.progressProperty().bind(task.progressProperty());
            pin.progressProperty().bind(task.progressProperty());
            dialogStage.show();
        }

        public Stage getDialogStage() {
            return dialogStage;
        }
    }

}
