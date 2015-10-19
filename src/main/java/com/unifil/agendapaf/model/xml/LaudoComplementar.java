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
@XmlRootElement(name = "LaudoComplementar")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LaudoComplementar", propOrder = {
    "idEmpresa",
    "possuiSGDB",
    "empresa",
    "endereco",
    "contato",
    "responsavelEnsaio",
    "bytesExePrincipal",
    "ripmedExePrincipal",
    "ripmedTxtRelacao"})
public class LaudoComplementar {

    @XmlElement(name = "IdEmpresa")
    private Long idEmpresa;
    @XmlElement(name = "Empresa")
    private Empresa empresa;
    @XmlElement(name = "Endereco")
    private Endereco endereco;
    @XmlElement(name = "Contato")
    private Contato contato;
    @XmlElement(name = "ResponsavelEnsaio")
    private String responsavelEnsaio;
    @XmlElement(name = "PossuiSGDB", required = true)
    private Boolean possuiSGDB;
    @XmlElement(name = "BytesExePrincipal", required = true)
    private String bytesExePrincipal;
    @XmlElement(name = "RipmedExePrincipal", required = true)
    private String ripmedExePrincipal;
    @XmlElement(name = "RipmedTxtRelacao", required = true)
    private String ripmedTxtRelacao;

    public LaudoComplementar() {
    }

    public LaudoComplementar(Long idEmpresa, Empresa empresa, Endereco endereco, Contato contato, String responsavelEnsaio, Boolean possuiSGDB, String bytesExePrincipal, String ripmedExePrincipal, String ripmedTxtRelacao) {
        this.idEmpresa = idEmpresa;
        this.empresa = empresa;
        this.endereco = endereco;
        this.contato = contato;
        this.responsavelEnsaio = responsavelEnsaio;
        this.possuiSGDB = possuiSGDB;
        this.bytesExePrincipal = bytesExePrincipal;
        this.ripmedExePrincipal = ripmedExePrincipal;
        this.ripmedTxtRelacao = ripmedTxtRelacao;
    }

    public Long getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Long idEmpresa) {
        this.idEmpresa = idEmpresa;
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

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

    public String getResponsavelEnsaio() {
        return responsavelEnsaio;
    }

    public void setResponsavelEnsaio(String responsavelEnsaio) {
        this.responsavelEnsaio = responsavelEnsaio;
    }

    public Boolean getPossuiSGDB() {
        return possuiSGDB;
    }

    public void setPossuiSGDB(Boolean possuiSGDB) {
        this.possuiSGDB = possuiSGDB;
    }

    public String getBytesExePrincipal() {
        return bytesExePrincipal;
    }

    public void setBytesExePrincipal(String bytesExePrincipal) {
        this.bytesExePrincipal = bytesExePrincipal;
    }

    public String getRipmedExePrincipal() {
        return ripmedExePrincipal;
    }

    public void setRipmedExePrincipal(String ripmedExePrincipal) {
        this.ripmedExePrincipal = ripmedExePrincipal;
    }

    public String getRipmedTxtRelacao() {
        return ripmedTxtRelacao;
    }

    public void setRipmedTxtRelacao(String ripmedTxtRelacao) {
        this.ripmedTxtRelacao = ripmedTxtRelacao;
    }

}
