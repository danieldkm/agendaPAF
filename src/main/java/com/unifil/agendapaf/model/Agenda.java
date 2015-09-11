package com.unifil.agendapaf.model;

import com.unifil.agendapaf.converter.ConverterLocalDate;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.sql.Date;
import java.time.LocalDate;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javax.persistence.Access;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.AccessType;
import javax.persistence.Convert;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@Access(AccessType.PROPERTY)
@Table(name = "agenda")
@NamedQueries({
    //    select t from JpaClass t order by t.id desc
    @NamedQuery(name = "Agenda.findAll", query = "SELECT a FROM Agenda a"),
    @NamedQuery(name = "Agenda.findByID", query = "SELECT a FROM Agenda a where a.id = :id"),
    @NamedQuery(name = "Agenda.findByDate", query = "SELECT a FROM Agenda a WHERE a.dataInicial = :data"),
    @NamedQuery(name = "Agenda.findOrderBy", query = "SELECT a FROM Agenda a ORDER BY :order"),
    @NamedQuery(name = "Agenda.findLast", query = "SELECT a FROM Agenda a ORDER BY a.id DESC")})
public class Agenda implements Externalizable {

    private static final long serialVersionUID = 1L;

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

    public void setIdEmpresa(Empresa empresa) {
        this.idEmpresa.set(empresa);
    }

    public ObjectProperty<Empresa> idEmpresaProperty() {
        return idEmpresa;
    }

    private StringProperty responsavel = new SimpleStringProperty(this, "responsavel");

    public String getResponsavel() {
        return responsavel.get();
    }

    public void setResponsavel(String responsavel) {
        this.responsavel.set(responsavel);
    }

    public StringProperty responsavelProperty() {
        return responsavel;
    }

    private StringProperty tipo = new SimpleStringProperty(this, "tipo");

    public String getTipo() {
        return tipo.get();
    }

    public void setTipo(String tipo) {
        this.tipo.set(tipo);
    }

    public StringProperty tipoProperty() {
        return tipo;
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

    private StringProperty diaSemana = new SimpleStringProperty(this, "diaSemana");

    public String getDiaSemana() {
        return diaSemana.get();
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana.set(diaSemana);
    }

    public StringProperty diaSemanaProperty() {
        return diaSemana;
    }

    private StringProperty statusBoleto = new SimpleStringProperty(this, "statusBoleto");

    public String getStatusBoleto() {
        return statusBoleto.get();
    }

    public void setStatusBoleto(String statusBoleto) {
        this.statusBoleto.set(statusBoleto);
    }

    public StringProperty statusBoletoProperty() {
        return statusBoleto;
    }

    private StringProperty statusAgenda = new SimpleStringProperty(this, "statusAgenda");

    public String getStatusAgenda() {
        return statusAgenda.get();
    }

    public void setStatusAgenda(String statusAgenda) {
        this.statusAgenda.set(statusAgenda);
    }

    public StringProperty statusAgendaProperty() {
        return statusAgenda;
    }

    @Convert(converter = ConverterLocalDate.class)
    private ObjectProperty<LocalDate> dataVencimentoBoleto = new SimpleObjectProperty<>();

    public LocalDate getDataVencimentoBoleto() {
        return dataVencimentoBoleto.get();
    }

    public void setDataVencimentoBoleto(LocalDate dataVencimentoBoleto) {
        this.dataVencimentoBoleto.set(dataVencimentoBoleto);
    }

    public ObjectProperty<LocalDate> dataVencimentoBoletoProperty() {
        return dataVencimentoBoleto;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeLong(getId());
        out.writeObject(getIdEmpresa());
        out.writeObject(getResponsavel());
        out.writeObject(getTipo());
        out.writeObject(getDataInicial());
        out.writeObject(getDataFinal());
        out.writeObject(getDiaSemana());
        out.writeObject(getStatusBoleto());
        out.writeObject(getStatusAgenda());
        out.writeObject(getDataVencimentoBoleto());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        setId(in.readLong());
        setIdEmpresa((Empresa) in.readObject());
        setResponsavel((String) in.readObject());
        setTipo((String) in.readObject());
        setDataInicial(((Date) in.readObject()).toLocalDate());
        setDataFinal(((Date) in.readObject()).toLocalDate());
        setDiaSemana((String) in.readObject());
        setStatusBoleto((String) in.readObject());
        setStatusAgenda((String) in.readObject());
        setDataVencimentoBoleto(((Date) in.readObject()).toLocalDate());
    }

    public void validate() {
//        if (idEmpresa.get() == null) {
//            throw new IllegalArgumentException("IdEmpresa cannot be null");
//        }
    }

    @Override
    public String toString() {
        return "Agenda{" + "id=" + id.get() + ", idEmpresa=" + idEmpresa.get().getDescricao() + ", responsavel=" + responsavel.get() + ", tipo=" + tipo.get() + ", dataInicial=" + dataInicial.get() + ", dataFinal=" + dataFinal.get() + ", diaSemana=" + diaSemana.get() + ", statusBoleto=" + statusBoleto.get() + ", statusAgenda=" + statusAgenda.get() + ", dataVencimentoBoleto=" + dataVencimentoBoleto.get() + '}';
    }

}
