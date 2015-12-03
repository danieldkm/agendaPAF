package com.unifil.agendapaf.model.email;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author danielmorita
 */
@XmlRootElement(name = "FerramentaEmail")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FerramentaEmail", propOrder = {
    "hostName",
    "portaSMTP",
    "email",
    "senha",
    "caminhoImg"
})
public class FerramentaEmail {

    @XmlElement(name = "HostName", required = true)
    private String hostName;
    @XmlElement(name = "PortaSMTP", required = true)
    private int portaSMTP;
    @XmlElement(name = "Email", required = true)
    private String email;
    @XmlElement(name = "Senha", required = true)
    private String senha;
    @XmlElement(name = "CaminhoImg")
    private String caminhoImg;

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public int getPortaSMTP() {
        return portaSMTP;
    }

    public void setPortaSMTP(int portaSMTP) {
        this.portaSMTP = portaSMTP;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCaminhoImg() {
        return caminhoImg;
    }

    public void setCaminhoImg(String caminhoImg) {
        this.caminhoImg = caminhoImg;
    }

    @Override
    public String toString() {
        return "FerramentaEmail{" + "hostName=" + hostName + ", portaSMTP=" + portaSMTP + ", email=" + email + ", senha=" + senha + ", caminhoImg=" + caminhoImg + '}';
    }

}
