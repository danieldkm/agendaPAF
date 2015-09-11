package com.unifil.agendapaf.model.laudo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name = "Mensagem")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MensagemType", propOrder = {
    "numero",
    "emiteNfe",
    "desenvolvedora",
    "otc",
    "identificacaoPaf",
    "caracteristicasPaf",
    "sistemasGestao",
    "sistemasPed",
    "sistemasPedNfe",
    "ecfAnaliseFuncional",
    "marcasModelosCompativeis",
    "versaoErPaf",
    "roteiroAnalise",
    "naoConformidades",
    "comentarioOtc",
    "declaracao",
    "emissao",
    "execucaoTestes",
    "aprovacaoRelatorio"
})
public class MensagemType {

    @XmlElement(name = "Numero", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String numero;
    @XmlElement(name = "EmiteNfe")
    protected boolean emiteNfe;
    @XmlElement(name = "Desenvolvedora", required = true)
    protected DesenvolvedoraType desenvolvedora;
    @XmlElement(name = "Otc", required = true)
    protected OtcType otc;
    @XmlElement(name = "IdentificacaoPaf", required = true)
    protected IdentificacaoPafType identificacaoPaf;
    @XmlElement(name = "CaracteristicasPaf", required = true)
    protected CaracteristicasPafType caracteristicasPaf;
    @XmlElement(name = "SistemasGestao", required = true)
    protected SistemasGestaoType sistemasGestao;
    @XmlElement(name = "SistemasPed", required = true)
    protected SistemasPedType sistemasPed;
    @XmlElement(name = "SistemasPedNfe", required = true)
    protected SistemasPedNfeType sistemasPedNfe;
    @XmlElement(name = "EcfAnaliseFuncional", required = true)
    protected EcfAnaliseFuncionalType ecfAnaliseFuncional;
    @XmlElement(name = "MarcasModelosCompativeis", required = true)
    protected MarcasModelosCompativeisType marcasModelosCompativeis;
    @XmlElement(name = "VersaoErPaf", required = true)
    protected String versaoErPaf;
    @XmlElement(name = "RoteiroAnalise", required = true)
    protected RoteiroAnaliseType roteiroAnalise;
    @XmlElement(name = "NaoConformidades", required = true)
    protected NaoConformidadesType naoConformidades;
    @XmlElement(name = "ComentarioOtc", required = true)
    protected String comentarioOtc;
    @XmlElement(name = "Declaracao")
    protected boolean declaracao;
    @XmlElement(name = "Emissao", required = true)
    protected EmissaoType emissao;
    @XmlElement(name = "ExecucaoTestes", required = true)
    protected ExecucaoTestesType execucaoTestes;
    @XmlElement(name = "AprovacaoRelatorio", required = true)
    protected AprovacaoRelatorioType aprovacaoRelatorio;

    /**
     * Gets the value of the numero property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getNumero() {
        return numero;
    }

    /**
     * Sets the value of the numero property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setNumero(String value) {
        this.numero = value;
    }

    /**
     * Gets the value of the emiteNfe property.
     *
     * @return emiteNfe
     */
    public boolean isEmiteNfe() {
        return emiteNfe;
    }

    /**
     * Sets the value of the emiteNfe property.
     *
     * @param value seta para true/false a emissão de NF-e
     */
    public void setEmiteNfe(boolean value) {
        this.emiteNfe = value;
    }

    /**
     * Gets the value of the desenvolvedora property.
     *
     * @return possible object is {@link DesenvolvedoraType }
     *
     */
    public DesenvolvedoraType getDesenvolvedora() {
        return desenvolvedora;
    }

    /**
     * Sets the value of the desenvolvedora property.
     *
     * @param value allowed object is {@link DesenvolvedoraType }
     *
     */
    public void setDesenvolvedora(DesenvolvedoraType value) {
        this.desenvolvedora = value;
    }

    /**
     * Gets the value of the otc property.
     *
     * @return possible object is {@link OtcType }
     *
     */
    public OtcType getOtc() {
        return otc;
    }

    /**
     * Sets the value of the otc property.
     *
     * @param value allowed object is {@link OtcType }
     *
     */
    public void setOtc(OtcType value) {
        this.otc = value;
    }

    /**
     * Gets the value of the identificacaoPaf property.
     *
     * @return possible object is {@link IdentificacaoPafType }
     *
     */
    public IdentificacaoPafType getIdentificacaoPaf() {
        return identificacaoPaf;
    }

    /**
     * Sets the value of the identificacaoPaf property.
     *
     * @param value allowed object is {@link IdentificacaoPafType }
     *
     */
    public void setIdentificacaoPaf(IdentificacaoPafType value) {
        this.identificacaoPaf = value;
    }

    /**
     * Gets the value of the caracteristicasPaf property.
     *
     * @return possible object is {@link CaracteristicasPafType }
     *
     */
    public CaracteristicasPafType getCaracteristicasPaf() {
        return caracteristicasPaf;
    }

    /**
     * Sets the value of the caracteristicasPaf property.
     *
     * @param value allowed object is {@link CaracteristicasPafType }
     *
     */
    public void setCaracteristicasPaf(CaracteristicasPafType value) {
        this.caracteristicasPaf = value;
    }

    /**
     * Gets the value of the sistemasGestao property.
     *
     * @return possible object is {@link SistemasGestaoType }
     *
     */
    public SistemasGestaoType getSistemasGestao() {
        return sistemasGestao;
    }

    /**
     * Sets the value of the sistemasGestao property.
     *
     * @param value allowed object is {@link SistemasGestaoType }
     *
     */
    public void setSistemasGestao(SistemasGestaoType value) {
        this.sistemasGestao = value;
    }

    /**
     * Gets the value of the sistemasPed property.
     *
     * @return possible object is {@link SistemasPedType }
     *
     */
    public SistemasPedType getSistemasPed() {
        return sistemasPed;
    }

    /**
     * Sets the value of the sistemasPed property.
     *
     * @param value allowed object is {@link SistemasPedType }
     *
     */
    public void setSistemasPed(SistemasPedType value) {
        this.sistemasPed = value;
    }

    /**
     * Gets the value of the sistemasPedNfe property.
     *
     * @return possible object is {@link SistemasPedNfeType }
     *
     */
    public SistemasPedNfeType getSistemasPedNfe() {
        return sistemasPedNfe;
    }

    /**
     * Sets the value of the sistemasPedNfe property.
     *
     * @param value allowed object is {@link SistemasPedNfeType }
     *
     */
    public void setSistemasPedNfe(SistemasPedNfeType value) {
        this.sistemasPedNfe = value;
    }

    /**
     * Gets the value of the ecfAnaliseFuncional property.
     *
     * @return possible object is {@link EcfAnaliseFuncionalType }
     *
     */
    public EcfAnaliseFuncionalType getEcfAnaliseFuncional() {
        return ecfAnaliseFuncional;
    }

    /**
     * Sets the value of the ecfAnaliseFuncional property.
     *
     * @param value allowed object is {@link EcfAnaliseFuncionalType }
     *
     */
    public void setEcfAnaliseFuncional(EcfAnaliseFuncionalType value) {
        this.ecfAnaliseFuncional = value;
    }

    /**
     * Gets the value of the marcasModelosCompativeis property.
     *
     * @return possible object is {@link MarcasModelosCompativeisType }
     *
     */
    public MarcasModelosCompativeisType getMarcasModelosCompativeis() {
        return marcasModelosCompativeis;
    }

    /**
     * Sets the value of the marcasModelosCompativeis property.
     *
     * @param value allowed object is {@link MarcasModelosCompativeisType }
     *
     */
    public void setMarcasModelosCompativeis(MarcasModelosCompativeisType value) {
        this.marcasModelosCompativeis = value;
    }

    /**
     * Gets the value of the versaoErPaf property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getVersaoErPaf() {
        return versaoErPaf;
    }

    /**
     * Sets the value of the versaoErPaf property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setVersaoErPaf(String value) {
        this.versaoErPaf = value;
    }

    /**
     * Gets the value of the roteiroAnalise property.
     *
     * @return possible object is {@link RoteiroAnaliseType }
     *
     */
    public RoteiroAnaliseType getRoteiroAnalise() {
        return roteiroAnalise;
    }

    /**
     * Sets the value of the roteiroAnalise property.
     *
     * @param value allowed object is {@link RoteiroAnaliseType }
     *
     */
    public void setRoteiroAnalise(RoteiroAnaliseType value) {
        this.roteiroAnalise = value;
    }

    /**
     * Gets the value of the naoConformidades property.
     *
     * @return possible object is {@link NaoConformidadesType }
     *
     */
    public NaoConformidadesType getNaoConformidades() {
        return naoConformidades;
    }

    /**
     * Sets the value of the naoConformidades property.
     *
     * @param value allowed object is {@link NaoConformidadesType }
     *
     */
    public void setNaoConformidades(NaoConformidadesType value) {
        this.naoConformidades = value;
    }

    /**
     * Gets the value of the comentarioOtc property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getComentarioOtc() {
        return comentarioOtc;
    }

    /**
     * Sets the value of the comentarioOtc property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setComentarioOtc(String value) {
        this.comentarioOtc = value;
    }

    /**
     * Gets the value of the declaracao property.
     *
     * @return declaracao
     */
    public boolean isDeclaracao() {
        return declaracao;
    }

    /**
     * Sets the value of the declaracao property.
     *
     * @param value seta a existencia da declaracao
     */
    public void setDeclaracao(boolean value) {
        this.declaracao = value;
    }

    /**
     * Gets the value of the emissao property.
     *
     * @return possible object is {@link EmissaoType }
     *
     */
    public EmissaoType getEmissao() {
        return emissao;
    }

    /**
     * Sets the value of the emissao property.
     *
     * @param value allowed object is {@link EmissaoType }
     *
     */
    public void setEmissao(EmissaoType value) {
        this.emissao = value;
    }

    /**
     * Gets the value of the execucaoTestes property.
     *
     * @return possible object is {@link ExecucaoTestesType }
     *
     */
    public ExecucaoTestesType getExecucaoTestes() {
        return execucaoTestes;
    }

    /**
     * Sets the value of the execucaoTestes property.
     *
     * @param value allowed object is {@link ExecucaoTestesType }
     *
     */
    public void setExecucaoTestes(ExecucaoTestesType value) {
        this.execucaoTestes = value;
    }

    /**
     * Gets the value of the aprovacaoRelatorio property.
     *
     * @return possible object is {@link AprovacaoRelatorioType }
     *
     */
    public AprovacaoRelatorioType getAprovacaoRelatorio() {
        return aprovacaoRelatorio;
    }

    /**
     * Sets the value of the aprovacaoRelatorio property.
     *
     * @param value allowed object is {@link AprovacaoRelatorioType }
     *
     */
    public void setAprovacaoRelatorio(AprovacaoRelatorioType value) {
        this.aprovacaoRelatorio = value;
    }

    @Override
    public String toString() {
        return "MensagemType{" + "numero=" + numero + ", emiteNfe=" + emiteNfe + ", desenvolvedora=" + desenvolvedora + ", otc=" + otc + ", identificacaoPaf=" + identificacaoPaf + ", caracteristicasPaf=" + caracteristicasPaf + ", sistemasGestao=" + sistemasGestao + ", sistemasPed=" + sistemasPed + ", sistemasPedNfe=" + sistemasPedNfe + ", ecfAnaliseFuncional=" + ecfAnaliseFuncional + ", marcasModelosCompativeis=" + marcasModelosCompativeis + ", versaoErPaf=" + versaoErPaf + ", roteiroAnalise=" + roteiroAnalise + ", naoConformidades=" + naoConformidades + ", comentarioOtc=" + comentarioOtc + ", declaracao=" + declaracao + ", emissao=" + emissao + ", execucaoTestes=" + execucaoTestes + ", aprovacaoRelatorio=" + aprovacaoRelatorio + '}';
    }

}
