package com.unifil.agendapaf.model.xml;

import com.unifil.agendapaf.model.Empresa;
import com.unifil.agendapaf.model.Telefone;
import com.unifil.agendapaf.model.Usuario;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
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
@XmlRootElement(name = "LaudoComplementar")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class LaudoComplementar {

    private ObjectProperty<Empresa> empresa;

    private ObjectProperty<ContatoComplementar> contato;

    private ObjectProperty<Usuario> responsavelEnsaio;

    private BooleanProperty possuiSGDB;

    private StringProperty bytesExePrincipal;

    private StringProperty ripmedExePrincipal;

    private StringProperty ripmedTxtRelacao;

    private static LaudoComplementar instance;

    public LaudoComplementar() {
        empresa = new SimpleObjectProperty<>();
        Empresa e = new Empresa();
        e.setInscricaoMunicipal("");
        e.setNomeFantasia("");
        empresa.set(e);

        contato = new SimpleObjectProperty<>();
        ContatoComplementar con = new ContatoComplementar();
        con.setRg("");
        Telefone tel = new Telefone();
        tel.setCelular("");
        tel.setFax("");
        con.setTelefone(tel);
        contato.set(con);

        responsavelEnsaio = new SimpleObjectProperty<>();
        Usuario u = new Usuario();
        u.setNome("");
        responsavelEnsaio.set(u);

        possuiSGDB = new SimpleBooleanProperty();
        possuiSGDB.set(false);

        bytesExePrincipal = new SimpleStringProperty();
        bytesExePrincipal.set("");

        ripmedExePrincipal = new SimpleStringProperty();
        ripmedExePrincipal.set("");

        ripmedTxtRelacao = new SimpleStringProperty();
        ripmedTxtRelacao.set("");

    }

    public static LaudoComplementar getInstance() {
        if (instance == null) {
            instance = new LaudoComplementar();
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

    @XmlElement(name = "Contato")
    public ContatoComplementar getContato() {
        return contato.get();
    }

    public void setContato(ContatoComplementar contato) {
        this.contato.set(contato);
    }

    public ObjectProperty<ContatoComplementar> contatoProperty() {
        return contato;
    }

    @XmlElement(name = "ResponsavelEnsaio")
    public Usuario getResponsavelEnsaio() {
        return responsavelEnsaio.get();
    }

    public void setResponsavelEnsaio(Usuario responsavelEnsaio) {
        this.responsavelEnsaio.set(responsavelEnsaio);
    }

    public ObjectProperty<Usuario> responsavelEnsaioProperty() {
        return responsavelEnsaio;
    }

    @XmlElement(name = "PossuiSGDB", required = true)
    public Boolean getPossuiSGDB() {
        return possuiSGDB.get();
    }

    public void setPossuiSGDB(Boolean possuiSGDB) {
        this.possuiSGDB.set(possuiSGDB);
    }

    public BooleanProperty possuiSGDBProperty() {
        return possuiSGDB;
    }

    @XmlElement(name = "BytesExePrincipal", required = true)
    public String getBytesExePrincipal() {
        return bytesExePrincipal.get();
    }

    public void setBytesExePrincipal(String bytesExePrincipal) {
        this.bytesExePrincipal.set(bytesExePrincipal);
    }

    public StringProperty bytesExePrincipalProperty() {
        return bytesExePrincipal;
    }

    @XmlElement(name = "RipmedExePrincipal", required = true)
    public String getRipmedExePrincipal() {
        return ripmedExePrincipal.get();
    }

    public void setRipmedExePrincipal(String ripmedExePrincipal) {
        this.ripmedExePrincipal.set(ripmedExePrincipal);
    }

    public StringProperty ripmedExePrincipalProperty() {
        return ripmedExePrincipal;
    }

    @XmlElement(name = "RipmedTxtRelacao", required = true)
    public String getRipmedTxtRelacao() {
        return ripmedTxtRelacao.get();
    }

    public void setRipmedTxtRelacao(String ripmedTxtRelacao) {
        this.ripmedTxtRelacao.set(ripmedTxtRelacao);
    }

    public StringProperty ripmedTxtRelacaoProperty() {
        return ripmedTxtRelacao;
    }

    @Override
    public String toString() {
        return "LaudoComplementar{" + "nomeFantasia=" + empresa.get().getNomeFantasia()
                + ", IM=" + empresa.get().getInscricaoMunicipal()
                + ", RG=" + contato.get().getRg()
                + ", CELULAR=" + contato.get().getTelefone().getCelular()
                + ", FAX=" + contato.get().getTelefone().getFax()
                + ", RESPENSAIO=" + responsavelEnsaio.get().getNome()
                + ", bytesExePrincipal=" + bytesExePrincipal.get() + ", ripmedExePrincipal=" + ripmedExePrincipal.get() + ", ripmedTxtRelacao=" + ripmedTxtRelacao.get() + '}';
    }

}
