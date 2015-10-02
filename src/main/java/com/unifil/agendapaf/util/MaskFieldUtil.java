package com.unifil.agendapaf.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import static javafx.scene.input.KeyCode.F1;
import static javafx.scene.input.KeyCode.F10;
import static javafx.scene.input.KeyCode.F11;
import static javafx.scene.input.KeyCode.F12;
import static javafx.scene.input.KeyCode.F2;
import static javafx.scene.input.KeyCode.F3;
import static javafx.scene.input.KeyCode.F4;
import static javafx.scene.input.KeyCode.F5;
import static javafx.scene.input.KeyCode.F6;
import static javafx.scene.input.KeyCode.F7;
import static javafx.scene.input.KeyCode.F8;
import static javafx.scene.input.KeyCode.F9;
import javafx.scene.input.KeyEvent;

public abstract class MaskFieldUtil {

    private static List<KeyCode> ignoreKeyCodes;

    static {
        ignoreKeyCodes = new ArrayList<>();
        Collections.addAll(ignoreKeyCodes, new KeyCode[]{F1, F2, F3, F4, F5, F6, F7, F8, F9, F10, F11, F12});
    }

    public static void ignoreKeys(final TextField textField) {
        textField.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (ignoreKeyCodes.contains(keyEvent.getCode())) {
                    keyEvent.consume();
                }
            }
        });
    }

    /**
     * Monta a mascara para Data (dd/MM/yyyy).
     *
     * @param textField TextField
     */
    public static void dateField(final TextField textField) {
        maxField(textField, 10);

        textField.lengthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (newValue.intValue() < 11) {
                    String value = textField.getText();
                    value = value.replaceAll("[^0-9]", "");
                    value = value.replaceFirst("(\\d{2})(\\d)", "$1/$2");
                    value = value.replaceFirst("(\\d{2})\\/(\\d{2})(\\d)", "$1/$2/$3");
                    textField.setText(value);
                    positionCaret(textField);
                }
            }
        });
    }

    /**
     * Campo que aceita somente numericos.
     *
     * @param textField TextField
     */
    public static void numericField(final TextField textField) {
        textField.lengthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (newValue.intValue() > oldValue.intValue()) {
                    char ch = textField.getText().charAt(oldValue.intValue());
                    if (!(ch >= '0' && ch <= '9')) {
                        textField.setText(textField.getText().substring(0, textField.getText().length() - 1));
                    }
                }
            }
        });
    }

    /**
     * Monta a mascara para Moeda.
     *
     * @param textField TextField
     */
    public static void monetaryField(final TextField textField) {
        textField.setAlignment(Pos.CENTER_RIGHT);
        textField.lengthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                String value = textField.getText();
                value = value.replaceAll("[^0-9]", "");
                value = value.replaceAll("([0-9]{1})([0-9]{14})$", "$1.$2");
                value = value.replaceAll("([0-9]{1})([0-9]{11})$", "$1.$2");
                value = value.replaceAll("([0-9]{1})([0-9]{8})$", "$1.$2");
                value = value.replaceAll("([0-9]{1})([0-9]{5})$", "$1.$2");
                value = value.replaceAll("([0-9]{1})([0-9]{2})$", "$1,$2");
                textField.setText(value);
                positionCaret(textField);

                textField.textProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                        if (newValue.length() > 17) {
                            textField.setText(oldValue);
                        }
                    }
                });
            }
        });

        textField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean fieldChange) {
                if (!fieldChange) {
                    final int length = textField.getText().length();
                    if (length > 0 && length < 3) {
                        textField.setText(textField.getText() + "00");
                    }
                }
            }
        });
    }

    /**
     * Monta as mascara para CPF/CNPJ. A mascara eh exibida somente apos o campo
     * perder o foco.
     *
     * @param textField TextField
     */
    @Deprecated
    public static void cpfCnpjField(final TextField textField) {

        textField.focusedProperty().addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean fieldChange) {
                String value = textField.getText();
                if (!fieldChange) {
                    if (textField.getText().length() == 11) {
                        value = value.replaceAll("[^0-9]", "");
                        value = value.replaceFirst("([0-9]{3})([0-9]{3})([0-9]{3})([0-9]{2})$", "$1.$2.$3-$4");
                    }
                    if (textField.getText().length() == 14) {
                        value = value.replaceAll("[^0-9]", "");
                        value = value.replaceFirst("([0-9]{2})([0-9]{3})([0-9]{3})([0-9]{4})([0-9]{2})$", "$1.$2.$3/$4-$5");
                    }
                }
                textField.setText(value);
                if (textField.getText() != value) {
                    textField.setText("");
                    textField.insertText(0, value);
                }

            }
        });

        maxField(textField, 18);
    }

    /**
     * Monta a mascara para os campos CNPJ.
     *
     * @param textField TextField
     */
    public static void cnpjField(final TextField textField) {
        maxField(textField, 18);
        textField.lengthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                String value = textField.getText();
                value = value.replaceAll("[^0-9]", "");
                value = value.replaceFirst("(\\d{2})(\\d)", "$1.$2");
                value = value.replaceFirst("(\\d{2})\\.(\\d{3})(\\d)", "$1.$2.$3");
                value = value.replaceFirst("\\.(\\d{3})(\\d)", ".$1/$2");
                value = value.replaceFirst("(\\d{4})(\\d)", "$1-$2");
                textField.setText(value);
                positionCaret(textField);
            }
        });
    }

    /**
     * Monta a mascara para os campos CPF.
     *
     * @param textField TextField
     */
    public static void cpfField(final TextField textField) {
        maxField(textField, 14);

        textField.lengthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
//                06.028.222/0001-07
//                999.999.999-99
                String value = textField.getText();
                value = value.replaceAll("[^0-9]", "");
                value = value.replaceFirst("(\\d{3})(\\d)", "$1.$2");
                value = value.replaceFirst("(\\d{2})\\.(\\d{3})(\\d)", "$1.$2.$3");
                value = value.replaceFirst("\\.(\\d{3})(\\d)", ".$1-$2");
                textField.setText(value);
                positionCaret(textField);
            }
        });
    }

    /**
     * Monta a mascara para os campos Horario.
     *
     * @param textField TextField
     */
    public static void horarioField(final TextField textField) {
        maxField(textField, 5);

        textField.lengthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                String value = textField.getText();
                value = value.replaceAll("[^0-9]", "");
                value = value.replaceFirst("(\\d{2})(\\d)", "$1:$2");

                textField.setText(value);
                positionCaret(textField);
            }
        });

    }

    /**
     * Monta a mascara para os campos Telefone.
     *
     * @param textField TextField
     */
    public static void telefoneField(final TextField textField) {
        maxField(textField, 14);

        textField.lengthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                //(99)9999-9999
                //(99)99999-9999
                String value = textField.getText();
                value = value.replaceAll("[^0-9]", "");
                if (value.length() > 10) {
                    value = value.replaceFirst("(\\d{2})(\\d)", "($1)$2");
                    value = value.replaceFirst("(\\d{5})(\\d)", "$1-$2");
                } else {
                    value = value.replaceFirst("(\\d{2})(\\d)", "($1)$2");
                    value = value.replaceFirst("(\\d{4})(\\d)", "$1-$2");
                }

                textField.setText(value);
                positionCaret(textField);
            }
        });
    }

    /**
     * Monta a mascara para os campos RG.
     *
     * @param textField TextField
     */
    public static void rgField(final TextField textField) {
        maxField(textField, 12);
        textField.lengthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                //88.888.888-8
                String value = textField.getText();
                value = value.replaceAll("[^0-9]", "");
                value = value.replaceFirst("(\\d{2})(\\d)", "$1.$2");
                value = value.replaceFirst("(\\d{2})\\.(\\d{3})(\\d)", "$1.$2.$3");
                value = value.replaceFirst("\\.(\\d{3})(\\d)", ".$1-$2");
                textField.setText(value);
                positionCaret(textField);
            }
        });
    }

    /**
     * Monta a mascara para os campos CEP.
     *
     * @param textField TextField
     */
    public static void cepField(final TextField textField) {
        maxField(textField, 10);
        textField.lengthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                //99.999-999
                String value = textField.getText();
                value = value.replaceAll("[^0-9]", "");
                value = value.replaceFirst("(\\d{2})(\\d)", "$1.$2");
                value = value.replaceFirst("(\\d{2})\\.(\\d{3})(\\d)", "$1.$2-$3");
                textField.setText(value);
                positionCaret(textField);
            }
        });
    }

//                  06.028.222/0001-07
//         value = value.replaceAll("[^0-9]", "");
//                value = value.replaceFirst("(\\d{2})(\\d)", "$1.$2");
//                value = value.replaceFirst("(\\d{2})\\.(\\d{3})(\\d)", "$1.$2.$3");
//                value = value.replaceFirst("\\.(\\d{3})(\\d)", ".$1/$2");
//                value = value.replaceFirst("(\\d{4})(\\d)", "$1-$2");
    /**
     * Monta a mascara para os campos Email.
     *
     * @param textField TextField
     */
    public static void mailField(final TextField textField) {
        maxField(textField, 50);

        textField.lengthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                String value = textField.getText();
                value = value.replaceAll("^[a-z]&& @", "");
//                for (int i = 0; i < value.length(); i++) {
//                    if (value.charAt(i)=='@') {
//                       value=value.replaceAll("^a-Z","" );
//                    }                    
//                }                

                textField.setText(value);
                positionCaret(textField);
            }
        });

    }

    /**
     * Remover todos os simbolos que não seja o número.
     *
     * @param textField TextField
     */
    public static void removeAllSimbolsExceptNumber(final TextField textField) {
        textField.focusedProperty().addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean fieldChange) {
                String value = textField.getText();
                String newValue = "";
                if (!fieldChange) {
                    Pattern p = Pattern.compile("[0-9]+");
                    Matcher m = p.matcher(value);

                    while (m.find()) {
                        newValue += m.group();
                    }
                    textField.setText(newValue);
                    positionCaret(textField);
                }
            }
        });
    }

    /**
     * Remover todos os simbolos que não seja o caracter.
     *
     * @param textField TextField
     */
    public static void removeAllSimbolsExceptCaracter(final TextField textField) {
        textField.focusedProperty().addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean fieldChange) {
                String value = textField.getText();
                String newValue = "";
                if (!fieldChange) {
                    Pattern p = Pattern.compile("[a-zA-Zà-úÀ-Ú]+");
                    Matcher m = p.matcher(value);

                    while (m.find()) {
                        System.out.println("m.group() " + m.group());
                        newValue += m.group();
                    }
                    textField.setText(newValue);
                    positionCaret(textField);
                }
            }
        });
    }

    /**
     * Remover todos os simbolos que não seja o caracter e número.
     *
     * @param textField TextField
     */
    public static void removeAllSimbolsExceptCaracterAndNumber(final TextField textField) {
        textField.focusedProperty().addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean fieldChange) {
                String value = textField.getText();
                String newValue = "";
                if (!fieldChange) {
                    Pattern p = Pattern.compile("[a-zA-Zà-úÀ-Ú0-9]+");
                    Matcher m = p.matcher(value);
                    while (m.find()) {
                        newValue += m.group();
                    }
                    textField.setText(newValue);
                    positionCaret(textField);
                }
            }
        });
    }

    /**
     * Devido ao incremento dos caracteres das mascaras eh necessario que o
     * cursor sempre se posicione no final da string.
     *
     * @param textField TextField
     */
    private static void positionCaret(final TextField textField) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                // Posiciona o cursor sempre a direita.
                textField.positionCaret(textField.getText().length());
            }
        });
    }

    /**
     * @param textField TextField.
     * @param length Tamanho do campo.
     */
    private static void maxField(final TextField textField, final Integer length) {
        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if (newValue != null && oldValue != null) {
                    if (newValue.length() > length) {
                        textField.setText(oldValue);
                    }
                }
            }
        });
    }

}
