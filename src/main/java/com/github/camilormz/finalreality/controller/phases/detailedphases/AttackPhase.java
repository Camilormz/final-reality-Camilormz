package com.github.camilormz.finalreality.controller.phases.detailedphases;

import com.github.camilormz.finalreality.controller.phases.Phase;
import com.github.camilormz.finalreality.model.character.CharacterDomain;
import com.github.camilormz.finalreality.model.character.ICharacter;

import java.util.LinkedList;

/**
 * Class that represents the state at the attacking phase of the game
 *
 * @author Camilo Ramírez Canales.
 */
public class AttackPhase extends Phase {

    @Override
    public boolean executeAttack(int adversarialIndex) {
        ICharacter attacker = controller.getCurrentTurnCharacter();
        LinkedList<? extends ICharacter> adversarialList;
        if (attacker.getCharacterDomain() == CharacterDomain.PLAYABLE) {
            adversarialList = controller.getEnemiesAssigned();
        } else {
            adversarialList = controller.getPlayerAssignedCharacters();
        }
        ICharacter adversary = adversarialList.get(adversarialIndex);
        boolean isAdvAlive = adversary.isAvailableForCombat();
        boolean friendlyFire = attacker.getCharacterDomain() == adversary.getCharacterDomain();
        controller.performAttack(attacker, adversarialList.get(adversarialIndex));
        boolean transitionPerformed = isAdvAlive && !friendlyFire;
        if (transitionPerformed) {
            this.changePhase(new EnqueuingPhase());
        }
        return transitionPerformed;
    }

    @Override
    public boolean isAtAttackPhase() {
        return true;
    }
}
