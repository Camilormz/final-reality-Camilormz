package com.github.camilormz.finalreality.controller.phases.detailedphases;

import com.github.camilormz.finalreality.controller.phases.Phase;
import com.github.camilormz.finalreality.model.character.ICharacter;

/**
 * Class that represents the state at the enqueuing phase of the game
 *
 * @author Camilo Ramírez Canales.
 */
public class EnqueuingPhase extends Phase {

    @Override
    public void turnEnd() {
        ICharacter turnCharacter = controller.getCurrentTurnCharacter();
        controller.waitEnqueueForTurn(turnCharacter);
    }

    @Override
    public void startPickingPhase() {
        this.changePhase(new PickingPhase());
    }

    @Override
    public boolean isAtEnqueuingPhase() {
        return true;
    }
}
