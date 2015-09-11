package com.unifil.agendapaf.model;

import com.unifil.agendapaf.converter.ConverterLocalDate;
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
//id, idEmpresa, dataHomologada, dataAviso, email, visualizado, codigoInterno

@Entity
@Access(AccessType.PROPERTY)
@Table(name = "empresasHomologadas")
@NamedQueries({
    @NamedQuery(name = "EmpresasHomologadas.findAll", query = "SELECT eh FROM EmpresasHomologadas eh"),
    @NamedQuery(name = "EmpresasHomologadas.findByID", query = "SELECT eh FROM EmpresasHomologadas eh where eh.id = :id")})
public class EmpresasHomologadas implements Externalizable {

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

    @Convert(converter = ConverterLocalDate.class)
    private ObjectProperty<LocalDate> dataHomologada = new SimpleObjectProperty<>();

    public LocalDate getDataHomologada() {
        return dataHomologada.get();
    }

    public void setDataHomologada(LocalDate dataHomologada) {
        this.dataHomologada.set(dataHomologada);
    }

    public ObjectProperty<LocalDate> dataHomologadaProperty() {
        return dataHomologada;
    }

    @Convert(converter = ConverterLocalDate.class)
    private ObjectProperty<LocalDate> dataAviso = new SimpleObjectProperty<>();

    public LocalDate getDataAviso() {
        return dataAviso.get();
    }

    public void setDataAviso(LocalDate dataAviso) {
        this.dataAviso.set(dataAviso);
    }

    public ObjectProperty<LocalDate> dataAvisoProperty() {
        return dataAviso;
    }

    private StringProperty email = new SimpleStringProperty(this, "email");

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public StringProperty emailProperty() {
        return email;
    }

    private StringProperty visualizado = new SimpleStringProperty(this, "visualizado");

    public String getVisualizado() {
        return visualizado.get();
    }

    public void setVisualizado(String visualizado) {
        this.visualizado.set(visualizado);
    }

    public StringProperty visualizadoProperty() {
        return visualizado;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeLong(getId());
        out.writeObject(getIdEmpresa());
        out.writeObject(getDataHomologada());
        out.writeObject(getDataAviso());
        out.writeObject(getEmail());
        out.writeObject(getVisualizado());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        setId(in.readLong());
        setIdEmpresa((Empresa) in.readObject());
        setDataHomologada((LocalDate) in.readObject());
        setDataAviso((LocalDate) in.readObject());
        setEmail((String) in.readObject());
        setVisualizado((String) in.readObject());
    }

    public void validate() {
//        if (idEmpresa.get() == null) {
//            throw new IllegalArgumentException("IdEmpresa cannot be null");
//        }
    }

    @Override
    public String toString() {
        return id.get() + " " + idEmpresa.get().getDescricao() + " " + dataHomologada.get() + " " + dataAviso.get() + " " 
                + email.get() + " " + visualizado.get();
    }

}
