package com.github.camilormz.finalreality.controller.phases.detailedphases;

import com.github.camilormz.finalreality.controller.phases.Phase;
import com.github.camilormz.finalreality.model.character.CharacterDomain;
import com.github.camilormz.finalreality.model.character.ICharacter;

/**
 * Class that represents the state at the picking phase of the game
 *
 * @author Camilo Ramírez Canales.
 */
public class PickingPhase extends Phase {

    @Override
    public void turnStart() {
        ICharacter currentCharacter = controller.getCurrentTurnCharacter();
        if (currentCharacter.getCharacterDomain() == CharacterDomain.PLAYABLE) {
            this.changePhase(new EquipmentPhase());
        } else {
            this.changePhase(new AttackPhase());
        }
    }

    @Override
    public boolean isAtPickingPhase() {
        return true;
    }
}
