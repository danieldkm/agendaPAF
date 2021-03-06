package com.unifil.agendapaf;

import com.unifil.agendapaf.view.util.enums.EnumCaminho;
import java.util.Date;
import javafx.scene.control.Control;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;

public class DateChooser extends Control {

    private static final String DEFAULT_STYLE_CLASS = "date-chooser";
    private Date date;
    private static BorderPane principalId;
    private static TextArea txtArea;
    private Boolean semestral;

    public DateChooser(Date preset) {
//        StaticCalendar.setSemestral(true);
        semestral = Boolean.TRUE;
        getStyleClass().setAll(DEFAULT_STYLE_CLASS);
        this.date = preset;
    }

    public DateChooser() {
        this(new Date(System.currentTimeMillis()));
        semestral = Boolean.FALSE;
//        StaticCalendar.setSemestral(false);
    }

    public DateChooser(BorderPane principalId, TextArea txtArea) {
        this(new Date(System.currentTimeMillis()));
//        StaticCalendar.setSemestral(false);
        semestral = Boolean.FALSE;
        this.principalId = principalId;
        this.txtArea = txtArea;
    }

    @Override
    public String getUserAgentStylesheet() {
        return EnumCaminho.CSSCalendar.getCaminho();
    }

    public Date getDate() {
        return date;
    }

    public static BorderPane getPrincipalId() {
        return principalId;
    }

    public static void setPrincipalId(BorderPane principalId) {
        DateChooser.principalId = principalId;
    }

    public static TextArea getTxtArea() {
        return txtArea;
    }

    public static void setTxtArea(TextArea txtArea) {
        DateChooser.txtArea = txtArea;
    }

    public Boolean getSemestral() {
        return semestral;
    }

}
