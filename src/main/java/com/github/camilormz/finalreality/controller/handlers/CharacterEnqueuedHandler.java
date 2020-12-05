package com.github.camilormz.finalreality.controller.handlers;

import com.github.camilormz.finalreality.controller.GameController;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Handler class for the event of character enqueuing in the turns queue
 *
 * @author Camilo Ramírez Canales
 */
public class CharacterEnqueuedHandler implements PropertyChangeListener {

    private final GameController controller;

    /**
     * Handler class constructor, it has associated the game controller
     */
    public CharacterEnqueuedHandler(GameController controller) {
        this.controller = controller;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        controller.onQueueEnqueuing();
    }

}
