package com.github.camilormz.finalreality.model.character.player;

import com.github.camilormz.finalreality.model.character.ICharacter;
import com.github.camilormz.finalreality.model.weapon.IWeapon;

/**
 * This represents a playable character (character controlled by the player) from the game.
 *
 * @author Camilo Ramírez Canales.
 */
public interface IPlayerCharacter extends ICharacter {

    /**
     * Equips a weapon to the character.
     */
    void tryToEquip(IWeapon weapon);

    /**
     * Returns this character's equipped weapon.
     */
    IWeapon getEquippedWeapon();

    /**
     * Returns this character's class.
     * @see CharacterClass
     */
    CharacterClass getCharacterClass();
}
