package com.unifil.agendapaf;

//import com.sun.javafx.scene.control.behavior.BehaviorBase;
//import com.sun.javafx.scene.control.skin.SkinBase;
import com.unifil.agendapaf.dao.JPA;
import com.unifil.agendapaf.model.Agenda;
import com.unifil.agendapaf.model.Feriado;
import com.unifil.agendapaf.model.Historico;
import com.unifil.agendapaf.service.AgendaService;
import com.unifil.agendapaf.statics.StaticLista;
import com.unifil.agendapaf.util.Util;
import com.unifil.agendapaf.util.UtilConverter;
import com.unifil.agendapaf.view.util.enums.EnumServico;
import com.unifil.agendapaf.view.util.enums.EnumStatus;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SkinBase;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

//public class DateChooserSkin extends SkinBase<DateChooser, BehaviorBase<DateChooser>> {
public class DateChooserSkin extends SkinBase<DateChooser> {
//

    private final Date date;
    private final Label month;
    private final BorderPane content;
    final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMMMM yyyy");
    private static Button monthBack;
    private static Button monthForward;

    private static BorderPane principalId;
    private static TextArea txtArea;
    private Boolean semestral;

    private static class CalendarCell extends StackPane {

        private final Date date;

        public CalendarCell(Date day, String text) {
            this.date = day;
            Label label = new Label(text);
            getChildren().add(label);
        }

        public Date getDate() {
            return date;
        }
    }

    public DateChooserSkin(DateChooser dateChooser) {
//        super(dateChooser, new BehaviorBase<DateChooser>(dateChooser));
        super(dateChooser);
        semestral = dateChooser.getSemestral();
        principalId = dateChooser.getPrincipalId();
        txtArea = dateChooser.getTxtArea();
        // this date is the selected date
        date = dateChooser.getDate();
        final DatePickerPane calendarPane = new DatePickerPane(date);

        month = new Label(simpleDateFormat.format(calendarPane.getShownMonth()));
        HBox hbox = new HBox(10);

        if (!semestral) {
            // create the navigation Buttons
            Button yearBack = new Button("<<");
            yearBack.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    calendarPane.forward(-12);

                }
            });
            monthBack = new Button("<");
            monthBack.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    calendarPane.forward(-1);
                }
            });
            monthForward = new Button(">");
            monthForward.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    calendarPane.forward(1);
                }
            });
//        monthForward.arm();
//        monthForward.fire();
            Button yearForward = new Button(">>");
            yearForward.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    calendarPane.forward(12);
                }
            });

            // center the label and make it grab all free space
            HBox.setHgrow(month, Priority.ALWAYS);
            month.setMaxWidth(Double.MAX_VALUE);
            month.setAlignment(Pos.CENTER);
            hbox.getChildren().addAll(yearBack, monthBack, month, monthForward, yearForward);
        } else {
            hbox.getChildren().add(month);
        }

        // use a BorderPane to Layout the view
        content = new BorderPane();
        getChildren().add(content);
        content.setTop(hbox);
        content.setCenter(calendarPane);
    }

    /**
     *
     * @author eppleton
     */
    class DatePickerPane extends Region {

        private final Date selectedDate;
        private final Calendar cal;
        private CalendarCell selectedDayCell;
        // this is used to format the day cells
        private final SimpleDateFormat sdf = new SimpleDateFormat("d");
        // empty cell header of weak-of-year row
        private final CalendarCell woyCell = new CalendarCell(new Date(), "");

        private int rows, columns;//default

        public DatePickerPane(Date date) {
//            setPrefSize(300, 300);
            autosize();
            if (semestral) {
                woyCell.getStyleClass().add("week-of-year-cell2");
            } else {
                woyCell.getStyleClass().add("week-of-year-cell");
            }
            setPadding(new Insets(5, 0, 5, 0));
            this.columns = 7;
//            this.columns = 6;
            this.rows = 5;

            // use a copy of Date, because it's mutable
            // we'll helperDate it through the month
            cal = Calendar.getInstance();
            Date helperDate = new Date(date.getTime());
            cal.setTime(helperDate);

            // the selectedDate is the date we will change, when a date is picked
            selectedDate = date;
            refresh();
        }

        /**
         * Move forward the specified number of Months, move backward by using
         * negative numbers
         *
         * @param i
         */
        public void forward(int i) {

            cal.add(Calendar.MONTH, i);
            month.setText(simpleDateFormat.format(cal.getTime()));
            refresh();
        }

        private void refresh() {
            super.getChildren().clear();
            this.rows = 5; // most of the time 5 rows are ok
            // save a copy to reset the date after our loop
            Date copy = new Date(cal.getTime().getTime());

            // empty cell header of weak-of-year row
            super.getChildren().add(woyCell);

            // Display a styleable row of localized weekday symbols 
            DateFormatSymbols symbols = new DateFormatSymbols();
            String[] dayNames = symbols.getShortWeekdays();

            // @TODO use static constants to access weekdays, I suspect we 
            // get problems with localization otherwise ( Day 1 = Sunday/ Monday in
            // different timezones
            for (int i = 1; i < 8; i++) { // array starts with an empty field
//                System.out.println("dayNames[i] " + dayNames[i]);
                CalendarCell calendarCell = new CalendarCell(cal.getTime(), dayNames[i]);
                if (semestral) {
                    calendarCell.getStyleClass().add("weekday-cell2");
                } else {
                    calendarCell.getStyleClass().add("weekday-cell");
                }
                super.getChildren().add(calendarCell);
            }

            // find out which month we're displaying
            cal.set(Calendar.DAY_OF_MONTH, 1);
            final int month = cal.get(Calendar.MONTH);

            int weekday = cal.get(Calendar.DAY_OF_WEEK);

            int ultimo = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

//            
//            System.out.println("Dia da semana " + weekday);
//            System.out.println("Ultimo dia do mes " + ultimo);
            //verifica se o 1 dia do mes inicia a semana na sexta ou sabado
            // e ainda se o mes atual � acima de 30 dias
            // caso verdadeiro aumenta a quantidade de linhas do calendario
            if (weekday >= 5 && ultimo >= 30) {
                rows = 6;
            }

            // if the first day is a sunday we need to rewind 7 days otherwise the 
            // code below would only start with the second week. There might be 
            // better ways of doing this...
//            if (weekday != Calendar.SUNDAY) {
//                // it might be possible, that we need to add a row at the end as well...
//                Calendar check = Calendar.getInstance();
//                check.setTime(new Date(cal.getTime().getTime()));
//                int lastDate = check.getActualMaximum(Calendar.DATE);
//                check.set(Calendar.DATE, lastDate);
//                if ((lastDate + weekday) > 36) {
//                    rows = 6;
//                }
//                cal.add(Calendar.DATE, -7);
//            }
            cal.set(Calendar.DAY_OF_WEEK, 1);

            // used to identify and style the cell with the selected date;
            Calendar testSelected = Calendar.getInstance();
            testSelected.setTime(selectedDate);
            ObservableList<Agenda> listaAgendas = StaticLista.getListaGlobalAgenda();

            for (int i = 0; i < (rows); i++) {

//                 first column shows the week of year
                CalendarCell calendarCell = new CalendarCell(cal.getTime(), "" + cal.get(Calendar.WEEK_OF_YEAR));
                if (semestral) {
                    calendarCell.getStyleClass().add("week-of-year-cell2");
                } else {
                    calendarCell.getStyleClass().add("week-of-year-cell");
                }
                super.getChildren().add(calendarCell);

                // loop through current week
                for (int j = 0; j < columns; j++) {
                    String formatted = sdf.format(cal.getTime());
                    final CalendarCell dayCell = new CalendarCell(cal.getTime(), formatted);
                    if (semestral) {
                        dayCell.getStyleClass().add("calendar-cell2");
                    } else {
                        dayCell.getStyleClass().add("calendar-cell");
                    }
                    if (cal.get(Calendar.MONTH) != month) {
                        if (semestral) {
                            dayCell.getStyleClass().add("calendar-cell-inactive2");
                        } else {

                            dayCell.getStyleClass().add("calendar-cell-inactive");
                        }

                    } else {
                        if (isSameDay(testSelected, cal)) {
                            if (semestral) {
                                dayCell.getStyleClass().add("calendar-cell-selected2");
                            } else {

                                dayCell.getStyleClass().add("calendar-cell-selected");
                            }
                            selectedDayCell = dayCell;
                        }
                        if (isToday(cal)) {
                            if (semestral) {
                                dayCell.getStyleClass().add("calendar-cell-today2");
                            } else {
                                dayCell.getStyleClass().add("calendar-cell-today");
                            }
                        }
                    }
                    //modifica a cor da celula do dia da semana de sabado e domingo - inicio
                    Calendar c = Calendar.getInstance();
                    c.setTime(dayCell.getDate());
                    int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
                    if (dayOfWeek == Calendar.SUNDAY || dayOfWeek == Calendar.SATURDAY) {
                        if (semestral) {
                            dayCell.getStyleClass().remove("calendar-cell2");
                            dayCell.getStyleClass().add("calendar-cell-sabado-e-domingo2");
                        } else {
                            dayCell.getStyleClass().remove("calendar-cell");
                            dayCell.getStyleClass().add("calendar-cell-sabado-e-domingo");
                        }
                    }
                    //modifica a cor da celula do dia da semana de sabado e domingo - fim
                    for (Agenda agenda : listaAgendas) {
                        // nesse codigo deve comparar a data da lista tanto 
                        // inicial quanto final com dayCell caso for igual 
                        // pintar a celula, com isso despendi menos tempo de 
                        // execu��o caso entre v�rias vezes 
                        // desnecessariamente no metodo pintarCelulas.
                        if (agenda.getDataInicial() != null) {
                            Calendar ca3 = new GregorianCalendar();
                            ca3.setTime(UtilConverter.converterLocalDateToUtilDate(agenda.getDataInicial()));
                            ca3.set(Calendar.HOUR_OF_DAY, 0);
                            ca3.set(Calendar.MINUTE, 0);
                            ca3.set(Calendar.SECOND, 0);
                            ca3.set(Calendar.MILLISECOND, 0);

                            Calendar ca4 = new GregorianCalendar();
                            ca4.setTime(UtilConverter.converterLocalDateToUtilDate(agenda.getDataFinal()));
                            ca4.set(Calendar.HOUR_OF_DAY, 0);
                            ca4.set(Calendar.MINUTE, 0);
                            ca4.set(Calendar.SECOND, 0);
                            ca4.set(Calendar.MILLISECOND, 0);

                            Calendar ca1 = new GregorianCalendar();
                            ca1.setTime(dayCell.getDate());
                            ca1.set(Calendar.HOUR_OF_DAY, 0);
                            ca1.set(Calendar.MINUTE, 0);
                            ca1.set(Calendar.SECOND, 0);
                            ca1.set(Calendar.MILLISECOND, 0);
                            if (ca1.equals(ca3) || ca1.equals(ca4)) {
                                pintarCelulas(dayCell, agenda);
                            }
                        }
                    }
                    pintarFeriados(dayCell);
                    dayCell.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent t) {
                            if (selectedDayCell != null) {
                                if (semestral) {
                                    selectedDayCell.getStyleClass().add("calendar-cell2");
                                    selectedDayCell.getStyleClass().remove("calendar-cell-selected2");
                                    dayCell.getStyleClass().add("calendar-cell-selected2");
                                    dayCell.getStyleClass().remove("calendar-cell2");
                                } else {
                                    selectedDayCell.getStyleClass().add("calendar-cell");
                                    selectedDayCell.getStyleClass().remove("calendar-cell-selected");
                                    dayCell.getStyleClass().add("calendar-cell-selected");
                                    dayCell.getStyleClass().remove("calendar-cell");
                                }
                                selectedDate.setTime(dayCell.getDate().getTime());
                                selectedDayCell = dayCell;
                                Calendar checkMonth = Calendar.getInstance();
                                checkMonth.setTime(dayCell.getDate());

                                if (checkMonth.get(Calendar.MONTH) != month) {
                                    forward(checkMonth.get(Calendar.MONTH) - month);
                                }
                            }
                        }
                    });

                    dayCell.setOnMousePressed(new EventHandler<MouseEvent>() {

                        @Override
                        public void handle(MouseEvent t) {
                            if (!semestral) {
                                if (t.isPrimaryButtonDown()) {
                                    SceneManager.getInstance().getPopUp().getPopUp().hide();
                                    selectedDate.setTime(dayCell.getDate().getTime());
                                    if (clickOnCell) {
                                        clickOnCell = false;
                                    } else {
                                        clickOnCell = true;
                                    }
                                } else if (t.isSecondaryButtonDown()) {
                                    clickOnCell = false;
//                                    StaticCalendar.setDataSelecionada(dayCell.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                                    SceneManager.getInstance().setDataSelecionada(dayCell.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                                    if (contemFeriado(dayCell)) {
                                        txtArea.setText("Não é possivel agendar nos feriados");
                                    } else if (!diaSemana.format(dayCell.getDate()).equals("Sábado") && !diaSemana.format(dayCell.getDate()).equals("Domingo")) {
                                        SceneManager.getInstance().getPopUp().getPopUp().show(principalId, t.getScreenX(), t.getScreenY());
                                    } else {
                                        txtArea.setText("Não é possivel agendar nos finais de semana e/ou feriados");
                                    }
                                }
                            }
                        }
                    });

                    // grow the hovered cell in size  
                    dayCell.setOnMouseEntered(new EventHandler<MouseEvent>() {

                        @Override
                        public void handle(MouseEvent e) {
                            dayCell.setScaleX(1.1);
                            dayCell.setScaleY(1.1);
                            if (!clickOnCell) {
                                if (!diaSemana.format(dayCell.getDate()).equals("Sábado") && !diaSemana.format(dayCell.getDate()).equals("Domingo")) {
                                    preencherTxtArea(dayCell.getDate());
                                    if (contemFeriado(dayCell)) {
                                        txtArea.setText(feriado);
                                    }
                                }
                            }
                        }
                    });

                    dayCell.setOnMouseExited(new EventHandler<MouseEvent>() {

                        @Override
                        public void handle(MouseEvent e) {
                            dayCell.setScaleX(1);
                            dayCell.setScaleY(1);
                            if (!clickOnCell) {
                                txtArea.setText("");
                            }
                        }
                    });

                    super.getChildren().add(dayCell);
                    cal.add(Calendar.DATE, 1);  // number of days to add

                }
            }
            cal.setTime(copy);
        }

        SimpleDateFormat diaSemana = new SimpleDateFormat("EEEE");

        /**
         * Overriden, don't add Children directly
         *
         * @return unmodifieable List
         */
        @Override
        protected ObservableList<Node> getChildren() {
            return FXCollections.unmodifiableObservableList(super.getChildren());
        }

        /**
         * get the current month our calendar displays. Should always give you
         * the correct one, even if some days of other mnths are also displayed
         *
         * @return
         */
        public Date getShownMonth() {
            return cal.getTime();
        }

        @Override
        protected void layoutChildren() {
            ObservableList<Node> children = getChildren();
            double width = getWidth();
            double height = getHeight();

            double cellWidth = (width / (columns + 1));
            double cellHeight = height / (rows + 1);

            for (int i = 0; i < (rows + 1); i++) {
                for (int j = 0; j < (columns + 1); j++) {
                    if (children.size() <= ((i * (columns + 1)) + j)) {
                        break;
                    }
                    Node get = children.get((i * (columns + 1)) + j);
                    layoutInArea(get, j * cellWidth, i * cellHeight, cellWidth, cellHeight, 0.0d, HPos.LEFT, VPos.TOP);
                }
            }
        }
    }
    // utility methods

    private static boolean isSameDay(Calendar cal1, Calendar cal2) {
        if (cal1 == null || cal2 == null) {
            throw new IllegalArgumentException("The dates must not be null");
        }
        return (cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA)
                && cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
                && cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR));
    }

    private static boolean isToday(Calendar cal) {
        return isSameDay(cal, Calendar.getInstance());
    }

//    //esse metodo serve para setar a data na tela de agenda
//    private static void setDataAgendaController(Date selectedDate) {
//        AgendarController.dtInicial = new eu.schudt.javafx.controls.calendar.DatePicker();
//        AgendarController.dtInicial.getStylesheets().add("/styles/DatePicker.css");
//        AgendarController.dtInicial.getCalendarView().todayButtonTextProperty().set("Today");
//        AgendarController.dtInicial.getCalendarView().setShowWeeks(false);
//        AgendarController.dtFinal = new eu.schudt.javafx.controls.calendar.DatePicker();
//        AgendarController.dtFinal.getStylesheets().add("/styles/DatePicker.css");
//        AgendarController.dtInicial.setSelectedDate(selectedDate);
//        Calendar cal = new GregorianCalendar();
//        cal.setTime(selectedDate);
//        cal.add(Calendar.DATE, 1);
//        AgendarController.dtFinal.setSelectedDate(cal.getTime());
//    }
    /**
     * escolhe o stilo a ser utilizado para mudar a cor da celula com agenda
     * marcada.
     *
     */
    private void escolherStatus(CalendarCell dayCell, ObservableList<Agenda> listaAgenda) {
        String statusAtual = "";
        for (Agenda agenda1 : listaAgenda) {
            statusAtual += agenda1.getStatusAgenda() + " ";
        }
        if (statusAtual.contains(EnumStatus.DataAgendada.getStatus())) {
            if (semestral) {
//                dayCell.getStyleClass().clear();
                dayCell.getStyleClass().add("calendar-cell-data-agendada2");
            } else {
//                dayCell.getStyleClass().clear();
                dayCell.getStyleClass().add("calendar-cell-data-agendada");
            }
        } else if (statusAtual.contains(EnumStatus.Pendente.getStatus())) {
            if (semestral) {
//                dayCell.getStyleClass().clear();
                dayCell.getStyleClass().add("calendar-cell-pendente2");
            } else {
//                dayCell.getStyleClass().clear();
                dayCell.getStyleClass().add("calendar-cell-pendente");
            }
        } else if (statusAtual.contains(Util.removerAcentuacaoServico(EnumStatus.NaoCompareceu.getStatus()))) {
            if (semestral) {
//                dayCell.getStyleClass().clear();
                dayCell.getStyleClass().add("calendar-cell-nao-comparaceu2");
            } else {
//                dayCell.getStyleClass().clear();
                dayCell.getStyleClass().add("calendar-cell-nao-comparaceu");
            }
        } else if (statusAtual.contains(Util.removerAcentuacaoServico(EnumStatus.Concluido.getStatus()))) {
            if (semestral) {
//                dayCell.getStyleClass().clear();
                dayCell.getStyleClass().add("calendar-cell-concluido2");
            } else {
//                dayCell.getStyleClass().clear();
                dayCell.getStyleClass().add("calendar-cell-concluido");
            }
        } else if (statusAtual.contains(EnumStatus.Cancelado.getStatus())) {
            if (semestral) {
                dayCell.getStyleClass().clear();
                dayCell.getStyleClass().add("calendar-cell-cancelado2");
            } else {
                dayCell.getStyleClass().clear();
                dayCell.getStyleClass().add("calendar-cell-cancelado");
            }
        }
        statusAtual = null;
    }

    private void pintarCelulas(CalendarCell dayCell, Agenda agenda) {
        // se agenda.getDataInicial2() ou qualquer um relacionada a data e dia da semana estiver vazio lan�a um axce��o--TODO
        try {
//            Dao<Agenda> agendasByDate = new Dao(Agenda.class);
//            ObservableList<Agenda> listaAgenda = FXCollections.observableArrayList();
            ObservableList<Agenda> listaAgenda = null;
            if (agenda.getDataInicial() != null) {
                AgendaService as = new AgendaService();
//                listaAgenda = StaticDao.getAgendaService().findByDate(agenda.getDataInicial());
                listaAgenda = as.findByDate(agenda.getDataInicial());
                JPA.em(false).close();
            }
            if (listaAgenda != null) {
                if (agenda.getDataInicial() != null) {
                    if (agenda.getTipo().equals(Util.removerAcentuacaoServico(EnumServico.Avaliacao.getServico()))
                            || agenda.getTipo().equals(Util.removerAcentuacaoServico(EnumServico.AvaliacaoIntinerante.getServico()))) {
                        Calendar ca2 = new GregorianCalendar();
                        ca2.setTime(UtilConverter.converterLocalDateToUtilDate(agenda.getDataInicial()));
                        ca2.set(Calendar.HOUR_OF_DAY, 0);
                        ca2.set(Calendar.MINUTE, 0);
                        ca2.set(Calendar.SECOND, 0);
                        ca2.set(Calendar.MILLISECOND, 0);

                        Calendar ca3 = new GregorianCalendar();
                        ca3.setTime(UtilConverter.converterLocalDateToUtilDate(agenda.getDataFinal()));
                        ca3.set(Calendar.HOUR_OF_DAY, 0);
                        ca3.set(Calendar.MINUTE, 0);
                        ca3.set(Calendar.SECOND, 0);
                        ca3.set(Calendar.MILLISECOND, 0);

                        Calendar ca1 = new GregorianCalendar();
                        ca1.setTime(dayCell.getDate());
                        ca1.set(Calendar.HOUR_OF_DAY, 0);
                        ca1.set(Calendar.MINUTE, 0);
                        ca1.set(Calendar.SECOND, 0);
                        ca1.set(Calendar.MILLISECOND, 0);
                        if (ca1.equals(ca2)) {
                            escolherStatus(dayCell, listaAgenda);
                        }
                        if (ca1.equals(ca3)) {
                            escolherStatus(dayCell, listaAgenda);
                        }
                    } else if (agenda.getTipo().equals(EnumServico.HoraAdicional.getServico())
                            || agenda.getTipo().equals(Util.removerAcentuacaoServico(EnumServico.PreAvaliacao.getServico()))
                            || agenda.getTipo().equals(Util.removerAcentuacaoServico(EnumServico.PreAvaliacaoIntinerante.getServico()))
                            || agenda.getTipo().equals(Util.removerAcentuacaoServico(EnumServico.PreAvaliacaoRemoto.getServico()))) {
                        Calendar ca2 = new GregorianCalendar();
                        ca2.setTime(UtilConverter.converterLocalDateToUtilDate(agenda.getDataFinal()));
                        ca2.set(Calendar.HOUR_OF_DAY, 0);
                        ca2.set(Calendar.MINUTE, 0);
                        ca2.set(Calendar.SECOND, 0);
                        ca2.set(Calendar.MILLISECOND, 0);

                        Calendar ca1 = new GregorianCalendar();
                        ca1.setTime(dayCell.getDate());
                        ca1.set(Calendar.HOUR_OF_DAY, 0);
                        ca1.set(Calendar.MINUTE, 0);
                        ca1.set(Calendar.SECOND, 0);
                        ca1.set(Calendar.MILLISECOND, 0);

                        if (ca1.equals(ca2)) {
                            escolherStatus(dayCell, listaAgenda);
                        }
                    }
                }
            }
        } catch (NullPointerException e) {
            System.err.println("Campos data e dia da semana vazio!!");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private Calendar ca1 = new GregorianCalendar();
    private Calendar ca2 = new GregorianCalendar();
    private Calendar ca3 = new GregorianCalendar();

    private String textoHistorico = "Histórico";
    private boolean clickOnCell = false;

    private void preencherTxtArea(Date d) {
        try {
            textoHistorico = "Histórico\n";

            ObservableList<Agenda> listaAgendas = StaticLista.getListaGlobalAgenda();
            for (Agenda agenda : listaAgendas) {
                if (agenda.getDataInicial() != null) {
                    ca3.setTime(UtilConverter.converterLocalDateToUtilDate(agenda.getDataInicial()));
                    ca3.set(Calendar.HOUR_OF_DAY, 0);
                    ca3.set(Calendar.MINUTE, 0);
                    ca3.set(Calendar.SECOND, 0);
                    ca3.set(Calendar.MILLISECOND, 0);

                    ca2.setTime(UtilConverter.converterLocalDateToUtilDate(agenda.getDataFinal()));
                    ca2.set(Calendar.HOUR_OF_DAY, 0);
                    ca2.set(Calendar.MINUTE, 0);
                    ca2.set(Calendar.SECOND, 0);
                    ca2.set(Calendar.MILLISECOND, 0);

                    ca1.setTime(d);
                    ca1.set(Calendar.HOUR_OF_DAY, 0);
                    ca1.set(Calendar.MINUTE, 0);
                    ca1.set(Calendar.SECOND, 0);
                    ca1.set(Calendar.MILLISECOND, 0);
//                    ObservableList<Empresa> listaEmpresas = Controller.getEmpresas();
//                    ObservableList<Empresa> listaEmpresas = Conf.getEmpresas();
                    if (ca1.equals(ca2) || ca1.equals(ca3)) {
                        ObservableList<Historico> his = StaticLista.getListaGlobalHistorico();
                        ArrayList<Long> codAgendas = new ArrayList();
                        String txtAux = "";
                        boolean codExiste = false;
                        for (Historico historico : his) {
                            if (historico.getIdAgenda().getId().equals(agenda.getId())) {
                                if (historico.getStatus().equals("Reagendada")) {
                                    txtAux += "\nREAGENDAMENTO!";
                                    txtAux += "\nData agendamento: " + UtilConverter.converterDataToFormat(UtilConverter.converterLocalDateToUtilDate(historico.getDataInicial()), "dd/MM/yyyy");
                                    txtAux += "\nMotivo: " + historico.getMotivo();
                                    txtAux += "\nData da alteração: " + UtilConverter.converterDataToFormat(UtilConverter.converterLocalDateToUtilDate(historico.getDataAlteracao()), "dd/MM/yyyy");
                                    txtAux += "\n----------------------------\n";
                                }
                                for (Long i : codAgendas) {
                                    if (i.equals(historico.getIdAgenda().getId())) {
                                        codExiste = true;
                                    }
                                }
                                if (!codExiste) {
                                    codAgendas.add(historico.getIdAgenda().getId());
                                    if (!historico.getStatus().equals("Reagendada")) {
                                        txtAux += "\nEmpresa: " + historico.getIdEmpresa().getDescricao();
                                        txtAux += "\nData: " + UtilConverter.converterDataToFormat(UtilConverter.converterLocalDateToUtilDate(historico.getDataInicial()), "dd/MM/yyyy");
                                        txtAux += "\nServiço: " + Util.porAcentuacaoServico(historico.getIdAgenda().getTipo());
                                        txtAux += "\nStatus Agenda: " + Util.porAcentuacaoServico(historico.getIdAgenda().getStatusAgenda());
                                        txtAux += "\nStatus Boleto: " + Util.porAcentuacaoServico(agenda.getStatusBoleto());
                                        if (!agenda.getStatusBoleto().equals("Nao enviado")) {
                                            if (agenda.getDataVencimentoBoleto() != null) {
                                                txtAux += "\nDt envio Boleto: " + UtilConverter.converterDataToFormat(UtilConverter.converterLocalDateToUtilDate(agenda.getDataVencimentoBoleto()), "dd/MM/yyyy");
                                            }
                                        }
                                        txtAux += "\nUsuário: " + historico.getIdUsuario().getNome();
                                        txtAux += "\n----------------------------\n";
                                    }
                                } else {
                                    codExiste = false;
                                }
                            }
                        }
                        textoHistorico += txtAux;
                    }
                }
                if (!clickOnCell) {
                    txtArea.setText(textoHistorico);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void pintarFeriados(CalendarCell dayCell) {

        cale1.setTime(dayCell.getDate());
        cale1.set(Calendar.HOUR_OF_DAY, 0);
        cale1.set(Calendar.MINUTE, 0);
        cale1.set(Calendar.SECOND, 0);
        cale1.set(Calendar.MILLISECOND, 0);
        cale1.set(Calendar.YEAR, 0);

        for (Feriado feriado : StaticLista.getListaGlobalFeriado()) {
//            System.out.println("data sql convertido para util " + new java.util.Date(feriado.getData()).getTime());
            cale2.setTime(UtilConverter.converterLocalDateToUtilDate(feriado.getData()));

            cale2.set(Calendar.HOUR_OF_DAY,
                    0);
            cale2.set(Calendar.MINUTE,
                    0);
            cale2.set(Calendar.SECOND,
                    0);
            cale2.set(Calendar.MILLISECOND,
                    0);
            cale2.set(Calendar.YEAR,
                    0);
            if (cale1.equals(cale2)) {
                if (semestral) {
                    dayCell.getStyleClass().clear();
                    dayCell.getStyleClass().add("calendar-cell-feriado2");
                } else {
                    dayCell.getStyleClass().clear();
                    dayCell.getStyleClass().add("calendar-cell-feriado");
                }
            }
        }
    }

    private Calendar cale1 = new GregorianCalendar();
    private Calendar cale2 = new GregorianCalendar();
    private String feriado = "";

    private boolean contemFeriado(CalendarCell dayCell) {
        cale1.setTime(dayCell.getDate());
        cale1.set(Calendar.HOUR_OF_DAY, 0);
        cale1.set(Calendar.MINUTE, 0);
        cale1.set(Calendar.SECOND, 0);
        cale1.set(Calendar.MILLISECOND, 0);
        cale1.set(Calendar.YEAR, 0);

        for (Feriado f : StaticLista.getListaGlobalFeriado()) {
            cale2.setTime(UtilConverter.converterLocalDateToUtilDate(f.getData()));

            cale2.set(Calendar.HOUR_OF_DAY,
                    0);
            cale2.set(Calendar.MINUTE,
                    0);
            cale2.set(Calendar.SECOND,
                    0);
            cale2.set(Calendar.MILLISECOND,
                    0);
            cale2.set(Calendar.YEAR,
                    0);
            if (cale1.equals(cale2)) {
                feriado = f.getFeriado();
                return true;
            }
        }
        return false;
    }

    public static Button getMonthBack() {
        return monthBack;
    }

    public static Button getMonthForward() {
        return monthForward;
    }
}
