package com.unifil.agendapaf.model;

import com.unifil.agendapaf.converter.ConverterLocalDate;
import com.unifil.agendapaf.util.UtilConverter;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.time.LocalDate;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
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
@Table(name = "historico")
@NamedQueries({
    @NamedQuery(name = "Historico.findAll", query = "SELECT h FROM Historico h"),
    @NamedQuery(name = "Historico.findByID", query = "SELECT h FROM Historico h where h.id = :id")})
public class Historico implements Externalizable {

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

    private ObjectProperty<Agenda> idAgenda = new SimpleObjectProperty<>();

    @ManyToOne(optional = false)
    @JoinColumn(name = "idAgenda", referencedColumnName = "id")
    public Agenda getIdAgenda() {
        return idAgenda.get();
    }

    public void setIdAgenda(Agenda idAgenda) {
        this.idAgenda.set(idAgenda);
    }

    public ObjectProperty<Agenda> idAgendaProperty() {
        return idAgenda;
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

    private StringProperty motivo = new SimpleStringProperty(this, "motivo");

    public String getMotivo() {
        return motivo.get();
    }

    public void setMotivo(String motivo) {
        this.motivo.set(motivo);
    }

    public StringProperty motivoProperty() {
        return motivo;
    }

    private StringProperty status = new SimpleStringProperty(this, "status");

    public String getStatus() {
        return status.get();
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public StringProperty statusProperty() {
        return status;
    }

    @Convert(converter = ConverterLocalDate.class)
    private ObjectProperty<LocalDate> dataAlteracao = new SimpleObjectProperty<>();

    public LocalDate getDataAlteracao() {
        return dataAlteracao.get();
    }

    public void setDataAlteracao(LocalDate dataAlteracao) {
        this.dataAlteracao.set(dataAlteracao);
    }

    public ObjectProperty<LocalDate> dataAlteracaoProperty() {
        return dataAlteracao;
    }

    private ObjectProperty<Usuario> idUsuario = new SimpleObjectProperty<>();

    @ManyToOne(optional = false)
    @JoinColumn(name = "idUsuario", referencedColumnName = "id")
    public Usuario getIdUsuario() {
        return idUsuario.get();
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario.set(idUsuario);
    }

    public ObjectProperty<Usuario> idUsuarioProperty() {
        return idUsuario;
    }

    @Convert(converter = ConverterLocalDate.class)
    private ObjectProperty<LocalDate> dataReagendada = new SimpleObjectProperty<>();

    public LocalDate getDataReagendada() {
        return dataReagendada.get();
    }

    public void setDataReagendada(LocalDate dataReagendada) {
        this.dataReagendada.set(dataReagendada);
    }

    public ObjectProperty<LocalDate> dataReagendadaProperty() {
        return dataReagendada;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeLong(getId());
        out.writeObject(getIdAgenda());
        out.writeObject(getIdEmpresa());
        out.writeObject(getDataInicial());
        out.writeObject(getDataFinal());
        out.writeObject(getMotivo());
        out.writeObject(getStatus());
        out.writeObject(getDataAlteracao());
        out.writeObject(getIdUsuario());
        out.writeObject(getDataReagendada());
    }
//id, idAgenda, idEmpresa, dataInicial, dataFinal, motivo, status, dataAlteracao, idUsuario, codigoInterno

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        setId(in.readLong());
        setIdAgenda((Agenda) in.readObject());
        setIdEmpresa((Empresa) in.readObject());
        setDataInicial((LocalDate) in.readObject());
        setDataFinal((LocalDate) in.readObject());
        setMotivo((String) in.readObject());
        setStatus((String) in.readObject());
        setDataAlteracao((LocalDate) in.readObject());
        setIdUsuario((Usuario) in.readObject());
        setDataReagendada((LocalDate) in.readObject());
    }

    public void validate() {
//        if (idEmpresa.get() == null) {
//            throw new IllegalArgumentException("IdEmpresa cannot be null");
//        }
    }

    @Override
    public String toString() {
        return (id.get() + " " + idAgenda.get().getStatusAgenda() + " " + idEmpresa.get().getDescricao() + " " + UtilConverter.converterDataToFormat(UtilConverter.converterLocalDateToUtilDate(dataInicial.get()), "dd-MM-yyyy") + " " + UtilConverter.converterDataToFormat(UtilConverter.converterLocalDateToUtilDate(dataFinal.get()), "dd-MM-yyyy") + " " + motivo.get() + " " + status.get() + " " + dataAlteracao.get() + " " + idUsuario.get().getNome() + " " + dataReagendada.get());
    }

}
