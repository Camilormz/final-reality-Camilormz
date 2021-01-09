package com.github.camilormz.finalreality.controller.phases.detailedphases;

import com.github.camilormz.finalreality.controller.phases.Phase;
import com.github.camilormz.finalreality.model.character.CharacterDomain;
import com.github.camilormz.finalreality.model.character.ICharacter;
import com.github.camilormz.finalreality.model.character.player.IPlayerCharacter;
import com.github.camilormz.finalreality.model.weapon.IWeapon;

/**
 * Class that represents the state at the equipment phase of the game
 *
 * @author Camilo Ramírez Canales.
 */
public class EquipmentPhase extends Phase {

    @Override
    public void equipTurnCharacter(IWeapon weapon) {
        this.verifyCapabilityToEquip();
        IPlayerCharacter character = (IPlayerCharacter) controller.getCurrentTurnCharacter();
        this.controller.tryToEquipWeapon(character, weapon);
    }

    @Override
    public void unEquipTurnCharacter() {
        this.verifyCapabilityToEquip();
        IPlayerCharacter character = (IPlayerCharacter) controller.getCurrentTurnCharacter();
        this.controller.unEquipWeapon(character);
    }

    @Override
    public void finishEquipmentProcedure() {
        this.changePhase(new AttackPhase());
    }

    @Override
    public boolean isAtEquipmentPhase() {
        return true;
    }

    /*
     * Verifies the capability to equip of the current character
     * Currently it checks if its an enemy or a playable character
     */
    private void verifyCapabilityToEquip() {
        ICharacter currentCharacter = controller.getCurrentTurnCharacter();
        // Code section not tested as this code is supposed to be unreachable, but is he for
        // robustness
        if (currentCharacter.getCharacterDomain() == CharacterDomain.ENEMY) {
            this.changePhase(new AttackPhase());
        }
    }
}
