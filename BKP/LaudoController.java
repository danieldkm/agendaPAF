package com.unifil.agendapaf;

import com.unifil.agendapaf.view.controller.*;
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
import com.unifil.agendapaf.util.UtilDialog;
import com.unifil.agendapaf.util.UtilXML;
import com.unifil.agendapaf.view.util.enums.EnumCaminho;
import com.unifil.agendapaf.view.util.enums.EnumMensagem;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.controlsfx.control.CheckComboBox;
import org.controlsfx.dialog.Wizard;
import org.controlsfx.dialog.Wizard.LinearFlow;
//import org.controlsfx.dialog.Wizard.WizardPane;
import org.controlsfx.dialog.WizardPane;

/**
 * FXML Controller class
 *
 * @author danielmorita
 */
public class LaudoController extends FXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        try {//TODO habilitar apos finalizar ;
//            FXMLController.setLogs("LaudoController");
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
        iTcNome.setCellFactory(TextFieldTableCell.<ArquivoExecutavelSemFuncaoType>forTableColumn());
        iTcNome.setOnEditCommit(
                new EventHandler<CellEditEvent<ArquivoExecutavelSemFuncaoType, String>>() {
                    @Override
                    public void handle(CellEditEvent<ArquivoExecutavelSemFuncaoType, String> t) {
                        ((ArquivoExecutavelSemFuncaoType) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setNome(t.getNewValue());
                        iTvTabela = t.getTableView();
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
        mensagem = new MensagemType();
        utilXml = new UtilXML();
        paneCheckBox1.getChildren().add(cb1);
        paneCheckBox1.getChildren().add(cbSelecionarTodos);
        paneCheckBox2.getChildren().add(cb2);
        paneCheckBox2.getChildren().add(cbSelecionarTodos2);
//        LaudoType l = (LaudoType) xmlController.unmarshalFromFile(LaudoType.class, "out.xml");
//        preenchimentoInicial(l);
        preencherCbMarca();
        carregarDiretorioXML();
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            stage = primaryStage;
            main = FXMLLoader.load(LaudoController.class.getResource(EnumCaminho.Laudo.getCaminho()));
            Scene scene = new Scene(main);
            stage.setScene(scene);
            stage.setTitle("Laudo");
//            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
            stage.toFront();
//            stage.getIcons().add(Controller.icoPAF);
            stage.setOnHidden(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent t) {
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            dialog.criarDialogException(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), "Erro no start", e, "Exception:");
        }

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
    private CheckBox iCkV;
    @FXML
    private TextField oTxtCEP;
    @FXML
    private CheckBox iCkW;
    @FXML
    private TextField sgTxtNomeSistema;
    @FXML
    private CheckBox iCkY;
    @FXML
    private CheckBox iCkZ;
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
    private TabPane tpPrincipal;
    @FXML
    private HBox paneCheckBox1;
    @FXML
    private HBox paneCheckBox2;
    @FXML
    private TableColumn<ArquivoExecutavelSemFuncaoType, String> iTcNome;
    @FXML
    private TableColumn<ArquivoExecutavelSemFuncaoType, String> iTcMd5;
    private CheckComboBox<String> cb1 = new CheckComboBox();
    private CheckComboBox<String> cb2 = new CheckComboBox();
    private CheckBox cbSelecionarTodos = new CheckBox("Selecionar Todos");
    private CheckBox cbSelecionarTodos2 = new CheckBox("Selecionar Todos");
    private Stage stage;
    private MensagemType mensagem;
    private UtilXML utilXml;
    private MarcasModelosCompativeisType mct;
    private UtilDialog dialog = new UtilDialog();

    private void carregarDiretorioXML() {
        utilXml.setEmpresas(FXCollections.observableArrayList());
        utilXml.listarDireitorio(new File(utilXml.getDiretorioInicial()));
        cbEmpresa.setItems(utilXml.getEmpresas());
    }

    public static void main(String[] args) {
        launch(args);
    }

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

    @FXML
    void actionBtnSalvar(ActionEvent event) {
        try {
            System.out.println("- Iniciar metodo actionBtnSalvar");
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
            peridoAnalise.setDataInicio(utilXml.convertDateForXMLGregorianCalendar(oDtInicio));
            peridoAnalise.setDataFim(utilXml.convertDateForXMLGregorianCalendar(oDtFinal));
            otc.setPeriodoAnalise(peridoAnalise);
            otc.setVersaoEspecificacaoRequisitos(oTxtVersaoER.getText());
            mensagem.setOtc(otc);

            //---------------------------Identifica��o------------------------------
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

            caracteristicas.setLinguagemProgramacao(cbLinguagem.getSelectionModel().getSelectedItem().toString());
            caracteristicas.setSistemaOperacional(cbSO.getSelectionModel().getSelectedItem().toString());
            caracteristicas.getGerenciadorBancoDados().add(cbBD.getSelectionModel().getSelectedItem().toString());
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
            et.setData(utilXml.convertDateForXMLGregorianCalendar(fDt));
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
            utilXml.criaDiretorio(mensagem.getDesenvolvedora().getRazaoSocial());
            File criarArquivo = new File(utilXml.getDiretorioInicial() + mensagem.getDesenvolvedora().getRazaoSocial() + "/" + mensagem.getNumero() + ".xml");
            if (criarArquivo.exists()) {
                Optional<ButtonType> result = dialog.criarDialogConfirmacao(EnumMensagem.Pergunta.getTitulo(), EnumMensagem.Pergunta.getSubTitulo(), "Esté arquivo já existe; " + mensagem.getNumero() + ".xml");
                if (result.get() == ButtonType.OK) {
                    finalizarAoSalvar(criarArquivo, lt);
                }
            } else {
                finalizarAoSalvar(criarArquivo, lt);
            }
            System.out.println("- Finalizar metodo actionBtnSalvar");
        } catch (Exception e) {
            e.printStackTrace();
            dialog.criarDialogException(EnumMensagem.Padrao.getTitulo(), "Erro!", "Preencher todos os campos", e, "Obs:");
        }
    }

    private void finalizarAoSalvar(File criarArquivo, LaudoType lt) {
        System.out.println("Salvando arquivo XML");
        utilXml.salvarArquivo(criarArquivo, utilXml.marshal(lt));
        if (utilXml.validarXMLSchema("xml/laudo.xsd", criarArquivo, true)) {
            dialog.criarDialogInfomation(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), "Arquivo XML validado com sucesso");
            carregarDiretorioXML();
            System.out.println(utilXml.marshal(lt));
            actionBtnLimpar(null);
        } else {
            System.out.println("Deletou a pasta???!!!! " + utilXml.deletarDiretorio(mensagem.getDesenvolvedora().getRazaoSocial()));
        }
    }

    @FXML
    private void actionBtnLimpar(ActionEvent event) {
        System.out.println("- Iniciar metodo de actionBtnLimpar");
        mensagem = new MensagemType();
        oDtInicio.setValue(null);
        ckEspeciais1.setSelected(false);
        fTxtLocal.setText("");
        iTxtMarca.setText("");
        ckEspeciais3.setSelected(false);
        ckEspeciais2.setSelected(false);
        ckEspeciais5.setSelected(false);
        ckEspeciais4.setSelected(false);
        sgTxtCNPJ.setText("");
        ckEspeciais7.setText("");
        cb2.getCheckModel().clearChecks();
        ckEspeciais6.setSelected(false);
        ckEspeciais9.setSelected(false);
        ckEspeciais8.setSelected(false);

        feTxtNome.setText("Sandro Teixeira Pinto");
        feTxtCPF.setText("64555011953");
        feTxtCargo.setText("Técnico Responsável");

        feTxtNome2.setText("Sergio Akio Tanaka");
        feTxtCPF2.setText("73183920930");
        feTxtCargo2.setText("Coordenador");

        oTxtNumero.setText("1626");
        oTxtIE.setText("Isento");
        oTxtComplemento.setText("");
        oTxtVersaoER.setText("02.02");
        oTxtLogradouro.setText("Av. Juscelino Kubischeck");
        oTxtCidade.setText("Londrina");
        oTxtBairro.setText("Centro");
        oTxtUF.setText("PR");
        oTxtRazaoSocial.setText("IFL - Instituto Filadélfia de Londrina");
        oTxtCNPJ.setText("78624202000100");
        oTxtCEP.setText("10900000");

        sgTvTabela.setItems(null);
        vTxtVersaoER.setText("");
        vTxtMes.setText("");
        ckForma1.setSelected(false);
        ckForma2.setSelected(false);
        ckForma3.setSelected(false);
        ckForma4.setSelected(false);
        ckProprio.setSelected(false);
        vTxtAno.setText("");
        txtNumeroLaudo.setText("");
        ckTerceirizado.setSelected(false);
        ckInterrupcao1.setSelected(false);
        ckInterrupcao2.setSelected(false);
        ckInterrupcao3.setSelected(false);
        ckEmite1.setSelected(false);
        ckEmite2.setSelected(false);
        speTxtNomeSistema.setText("");
        ckGeracao1.setSelected(false);
        ckGeracao2.setSelected(false);
        ckGeracao3.setSelected(false);
        fCkDeclaracao.setSelected(false);
        eCbMarca.getSelectionModel().clearSelection();
        spTvTabela.setItems(null);
        iTxtMD5Outro.setText("");
        iTxtNomeComercial.setText("");
        eTvTabela.setItems(null);
        dTxtCEP.setText("");
        ckForma5.setSelected(false);
        ckForma6.setSelected(false);
        ckForma7.setSelected(false);
        dTxtLogradouro.setText("");
        ckTipo2.setSelected(false);
        dTxtBairro.setText("");
        fTxtArea.setText("");
        rCbMarca.getSelectionModel().clearSelection();
        dTxtNumero.setText("");
        dTxtNome.setText("");
        dTxtRazaoSocial.setText("");
        ckComercializavel.setSelected(false);

        iTxtNumero.setText("");
        speTvTabela.setItems(null);
        tTvTabela.setItems(null);
        iTxtVersao.setText("");
        iTvTabela.setItems(null);
        dTxtUF.setText("");
        ckIntegracao1.setSelected(false);
        ckIntegracao2.setSelected(false);
        ckIntegracao3.setSelected(false);
        ckIntegracao4.setSelected(false);
        ckTipo1.setSelected(false);
        ckTipo3.setSelected(false);

        dTxtComplemento.setText("");
        fDt.setValue(null);
        dTxtCPF.setText("");
        oDtFinal.setValue(null);
        dTxtResponsavelTestes.setText("");
        iTxtPrincipalExec.setText("");
        iTxtModelo.setText("");
        iTxtRelacaoExec.setText("");
        spTxtEmpresaDesenvolvedora.setText("");
        vTxtVersaoRoteiro.setText("");
        iTxtOutroArq.setText("");
        cbBD.getSelectionModel().clearSelection();
        nTvTabela.setItems(null);

        cbSO.getSelectionModel().clearSelection();
        speTxtCNPJ.setText("");
        dTxtTelefone.setText("");
        speTxtEmpresaDesenvolvedora.setText("");
        dTxtCNPJ.setText("");
        spTxtNomeSistema.setText("");
        iTxtMD5Relacao.setText("");
        cbLinguagem.getSelectionModel().clearSelection();
        ckEspeciais12.setSelected(false);
        ckEspeciais11.setSelected(false);
        ckEspeciais10.setSelected(false);
        dTxtEmail.setText("");
        cb1.getCheckModel().clearChecks();
        iTxtMD5Principal.setText("");
        spTxtCNPJ.setText("");
        ckEspeciais15.setSelected(false);
        ckEspeciais14.setSelected(false);
        ckEspeciais13.setSelected(false);
        dTxtIE.setText("");
        iCkV.setSelected(false);
        iCkW.setSelected(false);
        sgTxtNomeSistema.setText("");
        iCkY.setSelected(false);
        iCkZ.setSelected(false);
        sgTxtRazaoSocial.setText("");
        dTxtCidade.setText("");
        cbEmpresa.getSelectionModel().clearSelection();
        lvLaudo.setItems(null);
        System.out.println("- Finalizar metodo de actionBtnLimpar");
    }

    private void preenchimento(LaudoType laudo) {
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
        oDtInicio.setValue(utilXml.convertXMLGregorianCalendarForDate(m.getOtc().getPeriodoAnalise().getDataInicio()));
        oDtFinal.setValue(utilXml.convertXMLGregorianCalendarForDate(m.getOtc().getPeriodoAnalise().getDataFim()));

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
        }
        ObservableList<ArquivoExecutavelSemFuncaoType> listaExecutaveis = FXCollections.observableArrayList();
        for (ArquivoExecutavelSemFuncaoType a : m.getIdentificacaoPaf().getArquivosExecutaveis().getArquivoExecutavel()) {
            listaExecutaveis.add(a);
        }
        iTvTabela.setItems(listaExecutaveis);

        cbLinguagem.getSelectionModel().selectFirst();
        cbSO.getSelectionModel().selectFirst();
        cbBD.getSelectionModel().selectFirst();
        while (true) {
            if (!m.getCaracteristicasPaf().getLinguagemProgramacao().equals(cbLinguagem.getSelectionModel().getSelectedItem())) {
                cbLinguagem.getSelectionModel().selectNext();
            }
            if (m.getCaracteristicasPaf().getLinguagemProgramacao().equals(cbLinguagem.getSelectionModel().getSelectedItem())) {
                break;
            }
        }

        while (true) {
            if (!m.getCaracteristicasPaf().getSistemaOperacional().equals(cbSO.getSelectionModel().getSelectedItem())) {
                cbSO.getSelectionModel().selectNext();
            }
            if (m.getCaracteristicasPaf().getSistemaOperacional().equals(cbSO.getSelectionModel().getSelectedItem())) {
                break;
            }
        }

        while (true) {
            if (!m.getCaracteristicasPaf().getGerenciadorBancoDados().get(0).equals(cbBD.getSelectionModel().getSelectedItem())) {
                cbBD.getSelectionModel().selectNext();
            }
            if (m.getCaracteristicasPaf().getGerenciadorBancoDados().get(0).equals(cbBD.getSelectionModel().getSelectedItem())) {
                break;
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
        fDt.setValue(utilXml.convertXMLGregorianCalendarForDate(m.getEmissao().getData()));
        feTxtNome.setText(m.getExecucaoTestes().getNome());
        feTxtCPF.setText(m.getExecucaoTestes().getCpf());
        feTxtCargo.setText(m.getExecucaoTestes().getCargo());
        feTxtNome2.setText(m.getAprovacaoRelatorio().getNome());
        feTxtCPF2.setText(m.getAprovacaoRelatorio().getCpf());
        feTxtCargo2.setText(m.getAprovacaoRelatorio().getCargo());
        System.out.println("- Finalizar metodo de preenchimento");
    }

    @FXML
    public void actionBtnIAdd() {
        showWizardAddArqExecut();
    }

    private void showWizardAddArqExecut() {
        // define pages to show
        Wizard wizard = new Wizard();
        wizard.setTitle("Adicionar nomes de arquivos executaveis");
        // --- page 1
        int row = 0;
        GridPane page1Grid = new GridPane();
        page1Grid.setVgap(10);
        page1Grid.setHgap(10);
        page1Grid.add(new Label("Arq. Executavel:"), 0, row);
        page1Grid.add(new Label("MD5:"), 0, (row + 1));
        TextField txArq = createTextField("arq");
        TextField txMd5 = createTextField("md5");
        page1Grid.add(txArq, 1, row++);
        page1Grid.add(txMd5, 1, row++);

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
                ObservableList<ArquivoExecutavelSemFuncaoType> listaExecutaveis = iTvTabela.getItems();
                ArquivoExecutavelSemFuncaoType aesft = new ArquivoExecutavelSemFuncaoType();
                aesft.setNome(txArq.getText());
                aesft.setMd5(txMd5.getText());
                listaExecutaveis.add(aesft);
                iTvTabela.setItems(listaExecutaveis);
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
        ObservableList<String> liModelo = FXCollections.observableArrayList();
        if (mct != null) {
            for (MarcaModeloType mmt : mct.getMarcaModelo()) {
                if (ecfU.isSelected()) {
                    if (mmt.getMarca().equals(eCbMarca.getValue().toString())) {
                        liModelo.add(mmt.getModelo());
                    }
                } else if (relacaoE.isSelected()) {
                    if (mmt.getMarca().equals(rCbMarca.getValue().toString())) {
                        liModelo.add(mmt.getModelo());
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
            if (eTvTabela.getSelectionModel().getSelectedItem() != null) {
                li = eTvTabela.getItems();
                li.remove(eTvTabela.getSelectionModel().getSelectedItem());
                eTvTabela.setItems(li);
            }
        } else if (relacaoE.isSelected()) {
            if (tTvTabela.getSelectionModel().getSelectedItem() != null) {
                li = tTvTabela.getItems();
                li.remove(tTvTabela.getSelectionModel().getSelectedItem());
                tTvTabela.setItems(li);
            }
        }
    }

    @FXML
    private void actionBtnEAdd() {
        ObservableList<MarcaModeloType> lmmt = null;
        MarcaModeloType mmt = null;
        if (ecfU.isSelected()) {
            if (eCbMarca.getSelectionModel().getSelectedItem() != null && !cb1.getCheckModel().getCheckedItems().isEmpty() || cbSelecionarTodos.isSelected()) {
                lmmt = eTvTabela.getItems();
                if (cbSelecionarTodos.isSelected()) {
                    for (String s : cb1.getItems()) {
                        mmt = new MarcaModeloType();
                        mmt.setMarca(eCbMarca.getValue().toString());
                        mmt.setModelo(s);
                        lmmt.add(mmt);
                    }
                } else {
                    for (String s : cb1.getCheckModel().getCheckedItems()) {
                        mmt = new MarcaModeloType();
                        mmt.setMarca(eCbMarca.getValue().toString());
                        mmt.setModelo(s);
                        lmmt.add(mmt);
                    }

                }
                eTvTabela.setItems(lmmt);
            } else {
                dialog.criarDialogWarning(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.LaudoInformeMarcaModelo.getMensagem());
            }
        } else if (relacaoE.isSelected()) {
            if (rCbMarca.getSelectionModel().getSelectedItem() != null && !cb2.getCheckModel().getCheckedItems().isEmpty() || cbSelecionarTodos2.isSelected()) {
                lmmt = tTvTabela.getItems();
                if (cbSelecionarTodos2.isSelected()) {
                    for (String s : cb2.getItems()) {
                        mmt = new MarcaModeloType();
                        mmt.setMarca(rCbMarca.getValue().toString());
                        mmt.setModelo(s);
                        lmmt.add(mmt);
                    }
                } else {
                    for (String s : cb2.getCheckModel().getCheckedItems()) {
                        mmt = new MarcaModeloType();
                        mmt.setMarca(rCbMarca.getValue().toString());
                        mmt.setModelo(s);
                        lmmt.add(mmt);
                    }

                }
                tTvTabela.setItems(lmmt);
            } else {
                dialog.criarDialogWarning(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.LaudoInformeMarcaModelo.getMensagem());
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
            System.out.println("Laudo a ser carregado " + l);
            preenchimento(l);
            tpPrincipal.getSelectionModel().select(tabDesenvolvedora);
        } else if (lvLaudo.getSelectionModel().getSelectedItems().size() == 1) {
            LaudoType l = (LaudoType) utilXml.unmarshalFromFile(LaudoType.class, utilXml.getDiretorioInicial() + cbEmpresa.getSelectionModel().getSelectedItem().toString() + "/" + lvLaudo.getSelectionModel().getSelectedItems().get(0));
            System.out.println("Laudo a ser carregado " + l);
            preenchimento(l);
            tpPrincipal.getSelectionModel().select(tabDesenvolvedora);
        }
        System.out.println("- Finalizar metodo actionBtnCarregar");
    }

    @FXML
    private void actionBtnAbrir() {
        String fi = utilXml.getDiretorioInicial() + cbEmpresa.getSelectionModel().getSelectedItem().toString() + "/" + lvLaudo.getSelectionModel().getSelectedItem().toString();
        try {
            java.awt.Desktop.getDesktop().open(new File(fi));
        } catch (IOException ex) {
            Logger.getLogger(LaudoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
