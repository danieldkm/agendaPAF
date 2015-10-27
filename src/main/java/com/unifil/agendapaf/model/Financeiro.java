package com.unifil.agendapaf.model;

import com.unifil.agendapaf.converter.ConverterLocalDate;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.time.LocalDate;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Access(AccessType.PROPERTY)
@Table(name = "financeiro")
@NamedQueries({
    @NamedQuery(name = "Financeiro.findAll", query = "SELECT f FROM Financeiro f"),
    @NamedQuery(name = "Financeiro.findByID", query = "SELECT f FROM Financeiro f where f.id = :id")})
public class Financeiro implements Externalizable {

    private LongProperty id = new SimpleLongProperty(this, "id");

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id.get();
    }

    public void setId(Long id) {
        this.id.set(id);
    }

    public LongProperty idProperty() {
        return id;
    }

    private ObjectProperty<Empresa> idEmpresa = new SimpleObjectProperty<>();

    @ManyToOne(optional = false)
    @JoinColumn(name = "idEmpresa", referencedColumnName = "id")
    public Empresa getIdEmpresa() {
        return idEmpresa.get();
    }

    public void setIdEmpresa(Empresa idEmpresa) {
        this.idEmpresa.set(idEmpresa);
    }

    public ObjectProperty<Empresa> idEmpresaProperty() {
        return idEmpresa;
    }

    private StringProperty tipoServico = new SimpleStringProperty(this, "tipoServico");

    public String getTipoServico() {
        return tipoServico.get();
    }

    public void setTipoServico(String tipoServico) {
        this.tipoServico.set(tipoServico);
    }

    public StringProperty tipoServicoProperty() {
        return tipoServico;
    }

    private IntegerProperty horaAdicional = new SimpleIntegerProperty(this, "horaAdicional");

    public Integer getHoraAdicional() {
        return horaAdicional.get();
    }

    public void setHoraAdicional(Integer horaAdicional) {
        this.horaAdicional.set(horaAdicional);
    }

    public IntegerProperty horaAdicionalProperty() {
        return horaAdicional;
    }

    private DoubleProperty valorPago = new SimpleDoubleProperty(this, "valorPago");

    public Double getValorPago() {
        return valorPago.get();
    }

    public void setValorPago(Double valorPago) {
        this.valorPago.set(valorPago);
    }

    public DoubleProperty valorPagoProperty() {
        return valorPago;
    }

    private StringProperty numeroLaudo = new SimpleStringProperty(this, "numeroLaudo");

    public String getNumeroLaudo() {
        return numeroLaudo.get();
    }

    public void setNumeroLaudo(String numeroLaudo) {
        this.numeroLaudo.set(numeroLaudo);
    }

    public StringProperty numeroLaudoProperty() {
        return numeroLaudo;
    }

    private StringProperty categoria = new SimpleStringProperty(this, "categoria");

    public String getCategoria() {
        return categoria.get();
    }

    public void setCategoria(String categoria) {
        this.categoria.set(categoria);
    }

    public StringProperty categoriaProperty() {
        return categoria;
    }

    @Convert(converter = ConverterLocalDate.class)
    private ObjectProperty<LocalDate> dataInicial = new SimpleObjectProperty<>();

    public LocalDate getDataInicial() {
        return dataInicial.get();
    }

    public void setDataInicial(LocalDate dataInicial) {
        this.dataInicial.set(dataInicial);
    }

    public ObjectProperty<LocalDate> dataInicialProperty() {
        return dataInicial;
    }

    @Convert(converter = ConverterLocalDate.class)
    private ObjectProperty<LocalDate> dataFinal = new SimpleObjectProperty<>();

    public LocalDate getDataFinal() {
        return dataFinal.get();
    }

    public void setDataFinal(LocalDate dataFinal) {
        this.dataFinal.set(dataFinal);
    }

    public ObjectProperty<LocalDate> dataFinalProperty() {
        return dataFinal;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeLong(getId());
        out.writeObject(getIdEmpresa());
        out.writeObject(getTipoServico());
        out.writeInt(getHoraAdicional());
        out.writeDouble(getValorPago());
        out.writeObject(getNumeroLaudo());
        out.writeObject(getCategoria());
        out.writeObject(getDataInicial());
        out.writeObject(getDataFinal());
    }
//id, idEmpresa, tipoServico, horaAdicional, valorPago, numeroLaudo, categoria, dataInicial, dataFinal, codigoInterno

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        setId(in.readLong());
        setIdEmpresa((Empresa) in.readObject());
        setTipoServico((String) in.readObject());
        setHoraAdicional((Integer) in.readInt());
        setValorPago((Double) in.readDouble());
        setNumeroLaudo((String) in.readObject());
        setCategoria((String) in.readObject());
        setDataInicial((LocalDate) in.readObject());
        setDataFinal((LocalDate) in.readObject());
    }

    public void validate() {
//        if (idEmpresa.get() == null) {
//            throw new IllegalArgumentException("IdEmpresa cannot be null");
//        }
    }

    @Override
    public String toString() {
        return "Financeiro{" + "id=" + id.get() + ", idEmpresa=" + idEmpresa.get().getId() + ", tipoServico=" + tipoServico.get() + ", horaAdicional=" + horaAdicional.get() + ", valorPago=" + valorPago.get() + ", numeroLaudo=" + numeroLaudo.get() + ", categoria=" + categoria.get() + ", dataInicial=" + dataInicial.get() + ", dataFinal=" + dataFinal.get() + '}';
    }

}
