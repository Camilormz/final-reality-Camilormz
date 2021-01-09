package com.github.camilormz.finalreality.controller.phases;

import com.github.camilormz.finalreality.controller.GameController;
import com.github.camilormz.finalreality.controller.phases.detailedphases.InitPhase;
import com.github.camilormz.finalreality.model.weapon.IWeapon;

/**
 * Class that represent a non-concrete (nor abstract) phase, te phases are equivalent to the states
 * of an State Pattern, any concrete phase inherits from this one , the phases are used to sustain
 * the flow of the game
 *
 * @author Camilo Ramírez Canales.
 */
public class Phase {

    public GameController controller;

    /**
     * Sets the controller associated with the phase
     */
    public void setController(GameController controller) {
        this.controller = controller;
    }

    /**
     * Changes the phase of the associated controller
     */
    protected void changePhase(Phase phase) {
        controller.setPhase(phase);
    }

    /**
     * Initializes the controller assigning the enemies and players specified in InitPhase
     * @see InitPhase
     */
    public void initController() {}

    /**
     * Starts the turn of the character in the head of the queue
     */
    public void turnStart() {}

    /**
     * Equips the character of the current turn with a weapon
     */
    public void equipTurnCharacter(IWeapon weapon) {}

    /**
     * Un-equips any weapon that the character could have
     */
    public void unEquipTurnCharacter() {}

    /**
     * Finish the equipment procedure, i.e., the equipment phase for the character of the current
     * turn
     */
    public void finishEquipmentProcedure() {}

    /**
     * Given the list index of the adversary, this method executes an attack from the character of
     * the current turn
     */
    public boolean executeAttack(int adversarialIndex) {
        return false;
    }

    /**
     * Ends the turn of the character in the head of the queue
     */
    public void turnEnd() {}

    /**
     * Starts the picking phase
     */
    public void startPickingPhase() {}

    /**
     * Returns true if the current phase is InitPhase
     */
    public boolean isAtInitPhase() {
        return false;
    }

    /**
     * Returns true if the current phase is PickingPhase
     */
    public boolean isAtPickingPhase() {
        return false;
    }

    /**
     * Returns true if the current phase is EquipmentPhase
     */
    public boolean isAtEquipmentPhase() {
        return false;
    }

    /**
     * Returns true if the current phase is AttackPhase
     */
    public boolean isAtAttackPhase() {
        return false;
    }

    /**
     * Returns true if the current phase is EnqueuingPhase
     */
    public boolean isAtEnqueuingPhase() {
        return false;
    }
}
