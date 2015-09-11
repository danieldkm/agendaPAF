package com.unifil.agendapaf.model.laudo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Laudo")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LaudoType", propOrder = {
    "mensagem"
})
public class LaudoType {

    @XmlElement(name = "Mensagem", required = true)
    protected MensagemType mensagem;
    @XmlAttribute(name = "Versao")
    protected String versao;

    /**
     * Gets the value of the mensagem property.
     *
     * @return possible object is {@link MensagemType }
     *
     */
    public MensagemType getMensagem() {
        return mensagem;
    }

    /**
     * Sets the value of the mensagem property.
     *
     * @param value allowed object is {@link MensagemType }
     *
     */
    public void setMensagem(MensagemType value) {
        this.mensagem = value;
    }

    /**
     * Gets the value of the versao property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getVersao() {
        if (versao == null) {
            return "1.0";
        } else {
            return versao;
        }
    }

    /**
     * Sets the value of the versao property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setVersao(String value) {
        this.versao = value;
    }

    @Override
    public String toString() {
        return "LaudoType{" + "mensagem=" + mensagem + ", versao=" + versao + '}';
    }

}
