package com.unifil.agendapaf.exemplos;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.paint.Color;

/**
 *
 * @author danielmorita
 */
public class TesteProperty {

    public static void main(String[] args) {
//        IntegerProperty num1 = new SimpleIntegerProperty(1);
//        IntegerProperty num2 = new SimpleIntegerProperty(2);
//        NumberBinding sum = num1.add(num2);
//        System.out.println(sum.getValue());
//        num1.set(2);
//        System.out.println(sum.getValue());
//        
//        
//        StringProperty sp1 = new SimpleStringProperty("a");
//        StringProperty sp2 = new SimpleStringProperty("b");
//        
////        StringBinding sumSp = sp1.;
////        sp1.set(sp2.);

        ObjectProperty<Lighting> root = new SimpleObjectProperty<Lighting>();
        final ObjectBinding<Color> colorBinding = Bindings.select(root, "light", "color");
        colorBinding.addListener(new ChangeListener<Color>() {
            @Override
            public void changed(ObservableValue<? extends Color> observableValue, Color oldValue, Color newValue) {
                System.out.println(oldValue + "new = " + newValue);
            }
        });
        Light firstLight = new Light.Point();
        firstLight.setColor(Color.BLACK);

        Light secondLight = new Light.Point();
        secondLight.setColor(Color.WHITE);

        Lighting firstLighting = new Lighting();
        firstLighting.setLight(firstLight);

        root.set(firstLighting);
        firstLighting.setLight(firstLight);
        firstLight.setColor(Color.RED);
    }

}
