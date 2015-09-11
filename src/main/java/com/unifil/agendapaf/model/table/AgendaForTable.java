package com.unifil.agendapaf.model.table;

import com.unifil.agendapaf.converter.ConverterLocalDate;
import java.time.LocalDate;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javax.persistence.Convert;

/**
 *
 * @author danielmorita
 */
public class AgendaForTable {

    private static final long serialVersionUID = 1L;

    private LongProperty id = new SimpleLongProperty(this, "id");

    public Long getId() {
        return id.get();
    }

    public void setId(Long id) {
        this.id.set(id);
    }

    public LongProperty idProperty() {
        return id;
    }

    private StringProperty empresa = new SimpleStringProperty();

    public String getEmpresa() {
        return empresa.get();
    }

    public void setIdEmpresa(String empresa) {
        this.empresa.set(empresa);
    }

    public StringProperty empresaProperty() {
        return empresa;
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

}
