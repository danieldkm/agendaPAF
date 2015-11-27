package com.unifil.agendapaf.model.aux;

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
    "senha"
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

    @Override
    public String toString() {
        return "FerramentaEmail{" + "hostName=" + hostName + ", portaSMTP=" + portaSMTP + ", email=" + email + ", senha=" + senha + '}';
    }
}
