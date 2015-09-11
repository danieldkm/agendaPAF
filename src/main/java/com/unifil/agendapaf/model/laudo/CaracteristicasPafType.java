package com.unifil.agendapaf.model.laudo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CaracteristicasPafType", propOrder = {
    "linguagemProgramacao",
    "sistemaOperacional",
    "gerenciadorBancoDados",
    "tipoDesenvolvimento",
    "tipoFuncionamento",
    "meioGeracaoArquivoSintegraEfd",
    "integracaoPaf",
    "formaImpressao",
    "tratamentoInterrupcao",
    "aplicacoesEspeciais"
})
public class CaracteristicasPafType {

    @XmlElement(name = "LinguagemProgramacao", required = true)
    protected String linguagemProgramacao;
    @XmlElement(name = "SistemaOperacional", required = true)
    protected String sistemaOperacional;
    @XmlElement(name = "GerenciadorBancoDados", required = true)
    protected List<String> gerenciadorBancoDados;
    @XmlElement(name = "TipoDesenvolvimento", required = true)
    protected String tipoDesenvolvimento;
    @XmlElement(name = "TipoFuncionamento", required = true)
    protected String tipoFuncionamento;
    @XmlElement(name = "MeioGeracaoArquivoSintegraEfd", required = true)
    protected MeioGeracaoArquivoSintegraEfdType meioGeracaoArquivoSintegraEfd;
    @XmlElement(name = "IntegracaoPaf", required = true)
    protected String integracaoPaf;
    @XmlElement(name = "FormaImpressao", required = true)
    protected FormaImpressaoType formaImpressao;
    @XmlElement(name = "TratamentoInterrupcao", required = true)
    protected TratamentoInterrupcaoType tratamentoInterrupcao;
    @XmlElement(name = "AplicacoesEspeciais", required = true)
    protected AplicacoesEspeciaisType aplicacoesEspeciais;

    /**
     * Gets the value of the linguagemProgramacao property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLinguagemProgramacao() {
        return linguagemProgramacao;
    }

    /**
     * Sets the value of the linguagemProgramacao property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLinguagemProgramacao(String value) {
        this.linguagemProgramacao = value;
    }

    /**
     * Gets the value of the sistemaOperacional property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSistemaOperacional() {
        return sistemaOperacional;
    }

    /**
     * Sets the value of the sistemaOperacional property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSistemaOperacional(String value) {
        this.sistemaOperacional = value;
    }

    /**
     * Gets the value of the gerenciadorBancoDados property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the gerenciadorBancoDados property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGerenciadorBancoDados().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * @return gerenciadorBancoDados
     */
    public List<String> getGerenciadorBancoDados() {
        if (gerenciadorBancoDados == null) {
            gerenciadorBancoDados = new ArrayList<String>();
        }
        return this.gerenciadorBancoDados;
    }

    /**
     * Gets the value of the tipoDesenvolvimento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoDesenvolvimento() {
        return tipoDesenvolvimento;
    }

    /**
     * Sets the value of the tipoDesenvolvimento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoDesenvolvimento(String value) {
        this.tipoDesenvolvimento = value;
    }

    /**
     * Gets the value of the tipoFuncionamento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoFuncionamento() {
        return tipoFuncionamento;
    }

    /**
     * Sets the value of the tipoFuncionamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoFuncionamento(String value) {
        this.tipoFuncionamento = value;
    }

    /**
     * Gets the value of the meioGeracaoArquivoSintegraEfd property.
     * 
     * @return
     *     possible object is
     *     {@link MeioGeracaoArquivoSintegraEfdType }
     *     
     */
    public MeioGeracaoArquivoSintegraEfdType getMeioGeracaoArquivoSintegraEfd() {
        return meioGeracaoArquivoSintegraEfd;
    }

    /**
     * Sets the value of the meioGeracaoArquivoSintegraEfd property.
     * 
     * @param value
     *     allowed object is
     *     {@link MeioGeracaoArquivoSintegraEfdType }
     *     
     */
    public void setMeioGeracaoArquivoSintegraEfd(MeioGeracaoArquivoSintegraEfdType value) {
        this.meioGeracaoArquivoSintegraEfd = value;
    }

    /**
     * Gets the value of the integracaoPaf property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIntegracaoPaf() {
        return integracaoPaf;
    }

    /**
     * Sets the value of the integracaoPaf property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIntegracaoPaf(String value) {
        this.integracaoPaf = value;
    }

    /**
     * Gets the value of the formaImpressao property.
     * 
     * @return
     *     possible object is
     *     {@link FormaImpressaoType }
     *     
     */
    public FormaImpressaoType getFormaImpressao() {
        return formaImpressao;
    }

    /**
     * Sets the value of the formaImpressao property.
     * 
     * @param value
     *     allowed object is
     *     {@link FormaImpressaoType }
     *     
     */
    public void setFormaImpressao(FormaImpressaoType value) {
        this.formaImpressao = value;
    }

    /**
     * Gets the value of the tratamentoInterrupcao property.
     * 
     * @return
     *     possible object is
     *     {@link TratamentoInterrupcaoType }
     *     
     */
    public TratamentoInterrupcaoType getTratamentoInterrupcao() {
        return tratamentoInterrupcao;
    }

    /**
     * Sets the value of the tratamentoInterrupcao property.
     * 
     * @param value
     *     allowed object is
     *     {@link TratamentoInterrupcaoType }
     *     
     */
    public void setTratamentoInterrupcao(TratamentoInterrupcaoType value) {
        this.tratamentoInterrupcao = value;
    }

    /**
     * Gets the value of the aplicacoesEspeciais property.
     * 
     * @return
     *     possible object is
     *     {@link AplicacoesEspeciaisType }
     *     
     */
    public AplicacoesEspeciaisType getAplicacoesEspeciais() {
        return aplicacoesEspeciais;
    }

    /**
     * Sets the value of the aplicacoesEspeciais property.
     * 
     * @param value
     *     allowed object is
     *     {@link AplicacoesEspeciaisType }
     *     
     */
    public void setAplicacoesEspeciais(AplicacoesEspeciaisType value) {
        this.aplicacoesEspeciais = value;
    }

}
