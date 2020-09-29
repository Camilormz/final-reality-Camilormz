package com.github.camilormz.finalreality.model.character.player;

import com.github.camilormz.finalreality.model.character.ICharacter;
import com.github.camilormz.finalreality.model.weapon.Weapon;

/**
 * This represents a playable character (character controlled by the player) from the game.
 *
 * @author Camilo Ramírez Canales.
 */
public interface IPlayerCharacter extends ICharacter {

    /**
     * Equips a weapon to the character.
     */
    void equip(Weapon weapon);

    /**
     * Returns this character's equipped weapon.
     */
    Weapon getEquippedWeapon();

    /**
     * Returns this character's class.
     * @see CharacterClass
     */
    CharacterClass getCharacterClass();
}
