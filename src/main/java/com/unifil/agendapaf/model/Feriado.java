package com.unifil.agendapaf.model;

import com.unifil.agendapaf.converter.ConverterLocalDate;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.time.LocalDate;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Access(AccessType.PROPERTY)
@Table(name = "feriado")
@NamedQueries({
    @NamedQuery(name = "Feriado.findAll", query = "SELECT f FROM Feriado f"),
    @NamedQuery(name = "Feriado.findByID", query = "SELECT f FROM Feriado f where f.id = :id")})
public class Feriado implements Externalizable {
//id, feriado, data, codigoInterno

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

    private StringProperty feriado = new SimpleStringProperty(this, "feriado");

    public String getFeriado() {
        return feriado.get();
    }

    public void setFeriado(String feriado) {
        this.feriado.set(feriado);
    }

    public StringProperty feriadoProperty() {
        return feriado;
    }

    @Convert(converter = ConverterLocalDate.class)
    private ObjectProperty<LocalDate> data = new SimpleObjectProperty<>();

    public LocalDate getData() {
        return data.get();
    }

    public void setData(LocalDate data) {
        this.data.set(data);
    }

    public ObjectProperty<LocalDate> dataProperty() {
        return data;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeLong(getId());
        out.writeObject(getFeriado());
        out.writeObject(getData());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        setId(in.readLong());
        setFeriado((String) in.readObject());
        setData((LocalDate) in.readObject());
    }

    public void validate() {
//        if (idEmpresa.get() == null) {
//            throw new IllegalArgumentException("IdEmpresa cannot be null");
//        }
    }
}
