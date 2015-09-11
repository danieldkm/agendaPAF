
package com.unifil.agendapaf.model.laudo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SistemaPedType", propOrder = {
    "nome",
    "empresaDesenvolvedora",
    "arquivosExecutaveis"
})
public class SistemaPedType {

    @XmlElement(name = "Nome", required = true)
    protected String nome;
    @XmlElement(name = "EmpresaDesenvolvedora", required = true)
    protected EmpresaDesenvolvedoraType empresaDesenvolvedora;
    @XmlElement(name = "ArquivosExecutaveis", required = true)
    protected ArquivosExecutaveisComFuncaoType arquivosExecutaveis;

    /**
     * Gets the value of the nome property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNome() {
        return nome;
    }

    /**
     * Sets the value of the nome property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNome(String value) {
        this.nome = value;
    }

    /**
     * Gets the value of the empresaDesenvolvedora property.
     * 
     * @return
     *     possible object is
     *     {@link EmpresaDesenvolvedoraType }
     *     
     */
    public EmpresaDesenvolvedoraType getEmpresaDesenvolvedora() {
        return empresaDesenvolvedora;
    }

    /**
     * Sets the value of the empresaDesenvolvedora property.
     * 
     * @param value
     *     allowed object is
     *     {@link EmpresaDesenvolvedoraType }
     *     
     */
    public void setEmpresaDesenvolvedora(EmpresaDesenvolvedoraType value) {
        this.empresaDesenvolvedora = value;
    }

    /**
     * Gets the value of the arquivosExecutaveis property.
     * 
     * @return
     *     possible object is
     *     {@link ArquivosExecutaveisComFuncaoType }
     *     
     */
    public ArquivosExecutaveisComFuncaoType getArquivosExecutaveis() {
        return arquivosExecutaveis;
    }

    /**
     * Sets the value of the arquivosExecutaveis property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArquivosExecutaveisComFuncaoType }
     *     
     */
    public void setArquivosExecutaveis(ArquivosExecutaveisComFuncaoType value) {
        this.arquivosExecutaveis = value;
    }

}
