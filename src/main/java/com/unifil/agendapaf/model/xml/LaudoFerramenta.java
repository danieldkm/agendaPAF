package com.unifil.agendapaf.model.xml;

import com.unifil.agendapaf.model.Cidade;
import com.unifil.agendapaf.model.Empresa;
import com.unifil.agendapaf.model.Endereco;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author danielmorita
 */
@XmlRootElement(name = "LaudoFerramenta")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class LaudoFerramenta {

    private ObjectProperty<Empresa> empresa;

    private ObjectProperty<Endereco> endereco;

    private ObjectProperty<ContatoPAF> executorTestes;

    private ObjectProperty<ContatoPAF> aprovadorRelatorio;

    private StringProperty versaoER;

    private static LaudoFerramenta instance;

    public LaudoFerramenta() {
        empresa = new SimpleObjectProperty<>();
        Empresa e = new Empresa();
        e.setCnpj("");
        e.setInscricaoEstadual("");
        e.setInscricaoMunicipal("");
        e.setNomeFantasia("");
        e.setDescricao("");
        empresa.set(e);

        endereco = new SimpleObjectProperty<>();
        Endereco en = new Endereco();
        en.setBairro("");
        en.setCep("");
        Cidade c = new Cidade();
        c.setNome("");
        c.setUf("");
        en.setIdCidade(c);
        en.setComplemento("");
        en.setLogradouro("");
        en.setNumero("");
        endereco.set(en);

        executorTestes = new SimpleObjectProperty<>();
        ContatoPAF et = new ContatoPAF();
        et.setCargo("");
        et.setCpf("");
        et.setNome("");
        et.setRg("");
        executorTestes.set(et);

        aprovadorRelatorio = new SimpleObjectProperty<>();
        ContatoPAF ar = new ContatoPAF();
        ar.setCargo("");
        ar.setCpf("");
        ar.setNome("");
        ar.setRg("");
        aprovadorRelatorio.set(ar);

        versaoER = new SimpleStringProperty();
        versaoER.set("");
    }

    public static LaudoFerramenta getInstance() {
        if (instance == null) {
            instance = new LaudoFerramenta();
        }
        return instance;
    }

    @XmlElement(name = "Empresa")
    public Empresa getEmpresa() {
        return empresa.get();
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa.set(empresa);
    }

    public ObjectProperty<Empresa> empresaProperty() {
        return empresa;
    }

    @XmlElement(name = "Endereco")
    public Endereco getEndereco() {
        return endereco.get();
    }

    public void setEndereco(Endereco endereco) {
        this.endereco.set(endereco);
    }

    public ObjectProperty<Endereco> enderecoProperty() {
        return endereco;
    }

    @XmlElement(name = "ExecucaoDosTestes")
    public ContatoPAF getExecutorTestes() {
        return executorTestes.get();
    }

    public void setExecutorTestes(ContatoPAF executorTestes) {
        this.executorTestes.set(executorTestes);
    }

    public ObjectProperty<ContatoPAF> executorTestesProperty() {
        return executorTestes;
    }

    @XmlElement(name = "AprovacaoDoRelatorio")
    public ContatoPAF getAprovadorRelatorio() {
        return aprovadorRelatorio.get();
    }

    public void setAprovadorRelatorio(ContatoPAF aprovadorRelatorio) {
        this.aprovadorRelatorio.set(aprovadorRelatorio);
    }

    public ObjectProperty<ContatoPAF> aprovadorRelatorioProperty() {
        return aprovadorRelatorio;
    }

    @XmlElement(name = "VersaoER")
    public String getVersaoER() {
        return versaoER.get();
    }

    public void setVersaoER(String versaoER) {
        this.versaoER.set(versaoER);
    }

    public StringProperty versaoERProperty() {
        return versaoER;
    }

}
