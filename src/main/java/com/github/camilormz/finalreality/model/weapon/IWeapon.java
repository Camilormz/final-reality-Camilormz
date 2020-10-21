package com.github.camilormz.finalreality.model.weapon;

import com.github.camilormz.finalreality.model.character.player.IPlayerCharacter;

/**
 * This represents a weapon of the game of any type.
 *
 * @author Camilo Ramírez Canales.
 */
public interface IWeapon {
    /**
     * Gets the weapons name.
     */
    String getName();

    /**
     * Gets the weapon damage.
     */
    int getDamage();

    /**
     * Gets the weapon weight.
     */
    int getWeight();

    /**
     * Gets the weapon type.
     */
    WeaponType getType();

    /**
     * Gets the weapon holder, null if there is no holder. The holder is the character who has this
     * weapon equipped.
     */
    IPlayerCharacter getHolder();

    /**
     * Returns if the weapon is available for equipping.
     */
    boolean isAvailable();

    /**
     * Sets the weapon status as held by the IPlayerCharacter
     */
    void beHeld(IPlayerCharacter playerCharacter);

    /**
     * Sets the weapon status as no held by any IPlayerCharacter
     */
    void beUnHeld();
}
