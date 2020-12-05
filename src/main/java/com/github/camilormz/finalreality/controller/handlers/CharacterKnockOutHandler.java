package com.github.camilormz.finalreality.controller.handlers;

import com.github.camilormz.finalreality.controller.GameController;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Handler class got the event of character knocking out
 *
 * @author Camilo Ramírez Canales
 */
public class CharacterKnockOutHandler implements PropertyChangeListener {

    private final GameController controller;

    /**
     * Handler class constructor, it has associated the game controller
     */
    public CharacterKnockOutHandler(GameController controller) {
        this.controller = controller;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        controller.onCharacterKnockOut();
    }
}
