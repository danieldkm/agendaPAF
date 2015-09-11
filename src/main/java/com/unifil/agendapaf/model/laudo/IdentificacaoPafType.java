
package com.unifil.agendapaf.model.laudo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IdentificacaoPafType", propOrder = {
    "nomeComercial",
    "versao",
    "arquivoExecutavelPrincipal",
    "arquivoRelacaoExecutaveis",
    "arquivosExecutaveis",
    "arquivosOutros",
    "envelopeSeguranca",
    "perfisRequisitos"
})
public class IdentificacaoPafType {

    @XmlElement(name = "NomeComercial", required = true)
    protected String nomeComercial;
    @XmlElement(name = "Versao", required = true)
    protected String versao;
    @XmlElement(name = "ArquivoExecutavelPrincipal", required = true)
    protected ArquivoExecutavelPrincipalType arquivoExecutavelPrincipal;
    @XmlElement(name = "ArquivoRelacaoExecutaveis", required = true)
    protected ArquivoRelacaoExecutaveisType arquivoRelacaoExecutaveis;
    @XmlElement(name = "ArquivosExecutaveis", required = true)
    protected ArquivosExecutaveisSemFuncaoType arquivosExecutaveis;
    @XmlElement(name = "ArquivosOutros", required = true)
    protected ArquivosOutrosType arquivosOutros;
    @XmlElement(name = "EnvelopeSeguranca", required = true)
    protected EnvelopeSegurancaType envelopeSeguranca;
    @XmlElement(name = "PerfisRequisitos", required = true)
    protected PerfisRequisitosType perfisRequisitos;

    /**
     * Gets the value of the nomeComercial property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomeComercial() {
        return nomeComercial;
    }

    /**
     * Sets the value of the nomeComercial property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomeComercial(String value) {
        this.nomeComercial = value;
    }

    /**
     * Gets the value of the versao property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersao() {
        return versao;
    }

    /**
     * Sets the value of the versao property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersao(String value) {
        this.versao = value;
    }

    /**
     * Gets the value of the arquivoExecutavelPrincipal property.
     * 
     * @return
     *     possible object is
     *     {@link ArquivoExecutavelPrincipalType }
     *     
     */
    public ArquivoExecutavelPrincipalType getArquivoExecutavelPrincipal() {
        return arquivoExecutavelPrincipal;
    }

    /**
     * Sets the value of the arquivoExecutavelPrincipal property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArquivoExecutavelPrincipalType }
     *     
     */
    public void setArquivoExecutavelPrincipal(ArquivoExecutavelPrincipalType value) {
        this.arquivoExecutavelPrincipal = value;
    }

    /**
     * Gets the value of the arquivoRelacaoExecutaveis property.
     * 
     * @return
     *     possible object is
     *     {@link ArquivoRelacaoExecutaveisType }
     *     
     */
    public ArquivoRelacaoExecutaveisType getArquivoRelacaoExecutaveis() {
        return arquivoRelacaoExecutaveis;
    }

    /**
     * Sets the value of the arquivoRelacaoExecutaveis property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArquivoRelacaoExecutaveisType }
     *     
     */
    public void setArquivoRelacaoExecutaveis(ArquivoRelacaoExecutaveisType value) {
        this.arquivoRelacaoExecutaveis = value;
    }

    /**
     * Gets the value of the arquivosExecutaveis property.
     * 
     * @return
     *     possible object is
     *     {@link ArquivosExecutaveisSemFuncaoType }
     *     
     */
    public ArquivosExecutaveisSemFuncaoType getArquivosExecutaveis() {
        return arquivosExecutaveis;
    }

    /**
     * Sets the value of the arquivosExecutaveis property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArquivosExecutaveisSemFuncaoType }
     *     
     */
    public void setArquivosExecutaveis(ArquivosExecutaveisSemFuncaoType value) {
        this.arquivosExecutaveis = value;
    }

    /**
     * Gets the value of the arquivosOutros property.
     * 
     * @return
     *     possible object is
     *     {@link ArquivosOutrosType }
     *     
     */
    public ArquivosOutrosType getArquivosOutros() {
        return arquivosOutros;
    }

    /**
     * Sets the value of the arquivosOutros property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArquivosOutrosType }
     *     
     */
    public void setArquivosOutros(ArquivosOutrosType value) {
        this.arquivosOutros = value;
    }

    /**
     * Gets the value of the envelopeSeguranca property.
     * 
     * @return
     *     possible object is
     *     {@link EnvelopeSegurancaType }
     *     
     */
    public EnvelopeSegurancaType getEnvelopeSeguranca() {
        return envelopeSeguranca;
    }

    /**
     * Sets the value of the envelopeSeguranca property.
     * 
     * @param value
     *     allowed object is
     *     {@link EnvelopeSegurancaType }
     *     
     */
    public void setEnvelopeSeguranca(EnvelopeSegurancaType value) {
        this.envelopeSeguranca = value;
    }

    /**
     * Gets the value of the perfisRequisitos property.
     * 
     * @return
     *     possible object is
     *     {@link PerfisRequisitosType }
     *     
     */
    public PerfisRequisitosType getPerfisRequisitos() {
        return perfisRequisitos;
    }

    /**
     * Sets the value of the perfisRequisitos property.
     * 
     * @param value
     *     allowed object is
     *     {@link PerfisRequisitosType }
     *     
     */
    public void setPerfisRequisitos(PerfisRequisitosType value) {
        this.perfisRequisitos = value;
    }

}
