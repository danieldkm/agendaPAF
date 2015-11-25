package com.unifil.agendapaf.util.mensagem;

import java.util.Random;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import org.controlsfx.control.GridCell;
import org.controlsfx.control.GridView;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.cell.ColorGridCell;

/**
 *
 * @author danielmorita
 */
public class Notificacao implements ImplMensagem {

    private CheckBox mostarTitulo;
    private CheckBox mostrarBotaoFechar;
    private CheckBox estiloDark;
    private CheckBox proprietario;
    private Slider fadeDelaySlider;

    private Pos posicao = Pos.TOP_RIGHT;

    private Stage stage;

    public Notificacao(Stage stage) {
        this.stage = stage;
        fadeDelaySlider = new Slider(1, 20, 5);
        fadeDelaySlider.setShowTickMarks(true);
        fadeDelaySlider.setMaxWidth(Double.MAX_VALUE);
        mostarTitulo = new CheckBox();
        mostarTitulo.setSelected(true);

        mostrarBotaoFechar = new CheckBox();
        mostrarBotaoFechar.setSelected(false);

        estiloDark = new CheckBox();
        estiloDark.setSelected(false);

        proprietario = new CheckBox();
        proprietario.setSelected(false);
    }

    private int count = 0;
    protected String tipoImagem = "";
//    private static final Image SMALL_GRAPHIC
//            = new Image(Notificacao.class.getResource("controlsfx-logo.png").toExternalForm());

    private void notificacao(String titulo, String cabecalho, String corpo, Exception ex, Pos pos) {
        String text = corpo;

        Node graphic = null;
        switch (tipoImagem) {
            default:
            case "Sem imagem":
            case "Aviso":
            case "Informacao":
            case "Confirm graphic":
            case "Erro":
                break;
            case "Custom graphic":
//                graphic = new ImageView(SMALL_GRAPHIC);
                break;
            case "Total-replacement graphic":
                text = null;
                graphic = buildTotalReplacementGraphic();
                break;
        }

        Notifications notificationBuilder = Notifications.create()
                .title(mostarTitulo.isSelected() ? titulo : "")
                .text(text)
                .graphic(graphic)
                .hideAfter(Duration.seconds(fadeDelaySlider.getValue()))
                .position(pos)
                .onAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent arg0) {
//                        System.out.println("Notification clicked on!");
                        if (tipoImagem.equals("Erro")) {
                            Dialogos d = new Dialogos(stage);
                            d.excecao(cabecalho, ex);
                        }
                    }
                });

        if (proprietario.isSelected()) {
            notificationBuilder.owner(stage);
        }

        if (!mostrarBotaoFechar.isSelected()) {
            notificationBuilder.hideCloseButton();
        }

        if (estiloDark.isSelected()) {
            notificationBuilder.darkStyle();
        }

        switch (tipoImagem) {
            case "Aviso":
                notificationBuilder.showWarning();
                break;
            case "Informacao":
                System.out.println("INFORMACAO");
                notificationBuilder.showInformation();
                break;
            case "Confirm graphic":
                notificationBuilder.showConfirm();
                break;
            case "Erro":
                notificationBuilder.showError();
                break;
            default:
                notificationBuilder.show();
        }
    }

    private Node buildTotalReplacementGraphic() {
        final ObservableList<Color> list = FXCollections.<Color>observableArrayList();

        GridView<Color> colorGrid = new GridView<>(list);
        colorGrid.setPrefSize(300, 300);
        colorGrid.setMaxSize(300, 300);

        colorGrid.setCellFactory(new Callback<GridView<Color>, GridCell<Color>>() {
            @Override
            public GridCell<Color> call(GridView<Color> arg0) {
                return new ColorGridCell();
            }
        });
        Random r = new Random(System.currentTimeMillis());
        for (int i = 0; i < 500; i++) {
            list.add(new Color(r.nextDouble(), r.nextDouble(), r.nextDouble(), 1.0));
        }
        return colorGrid;
    }

    @Override
    public void erro(String titulo, String cabecalho, String corpo, Exception ex) {
        tipoImagem = "Erro";
        notificacao(titulo, cabecalho, corpo, ex, posicao);
    }

    @Override
    public void informacao(String titulo, String cabecalho, String corpo) {
        tipoImagem = "Informacao";
        notificacao(titulo, cabecalho, corpo, null, posicao);
    }

    @Override
    public void aviso(String titulo, String cabecalho, String corpo) {
        tipoImagem = "Aviso";
        notificacao(titulo, cabecalho, corpo, null, posicao);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    

}
