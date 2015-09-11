package com.unifil.agendapaf.statics;

import javafx.scene.control.ContextMenu;

/**
 *
 * @author danielmorita
 */
public class StaticPopUp {

    private static ContextMenu popUp;

    public static ContextMenu getPopUp() {
        return popUp;
    }

    public static void setPopUp(ContextMenu popUp) {
        StaticPopUp.popUp = popUp;
    }

}
