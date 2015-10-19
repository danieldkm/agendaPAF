package com.unifil.agendapaf.model.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author danielmorita
 */
@XmlRootElement(name = "LaudoFerramenta")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LaudoFerramenta", propOrder = {
    "empresa",
    "endereco",
    "executorTestes",
    "aprovadorRelatorio"
})
public class LaudoFerramenta {

    @XmlElement(name = "Empresa")
    private Empresa empresa;
    @XmlElement(name = "Endereco")
    private Endereco endereco;
    @XmlElement(name = "ExecucaoDosTestes")
    private Pessoa executorTestes;
    @XmlElement(name = "AprovacaoDoRelatorio")
    private Pessoa aprovadorRelatorio;

    public LaudoFerramenta() {
    }

    public LaudoFerramenta(Empresa empresa, Endereco endereco, Pessoa executorTestes, Pessoa aprovadorRelatorio) {
        this.empresa = empresa;
        this.endereco = endereco;
        this.executorTestes = executorTestes;
        this.aprovadorRelatorio = aprovadorRelatorio;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Pessoa getExecutorTestes() {
        return executorTestes;
    }

    public void setExecutorTestes(Pessoa executorTestes) {
        this.executorTestes = executorTestes;
    }

    public Pessoa getAprovadorRelatorio() {
        return aprovadorRelatorio;
    }

    public void setAprovadorRelatorio(Pessoa aprovadorRelatorio) {
        this.aprovadorRelatorio = aprovadorRelatorio;
    }

}
