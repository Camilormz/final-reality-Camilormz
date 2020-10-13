package com.github.camilormz.finalreality.model.weapon;

/**
 * This represents a weapon of the game of any type.
 *
 * @author Camilo Ramírez Canales.
 */
public interface IWeapon {
    /**
     * Gets the weapons name
     */
    String getName();

    /**
     * Gets the weapon damage
     */
    int getDamage();

    /**
     * Gets the weapon weight
     */
    int getWeight();

    /**
     * Gets the weapon type
     */
    WeaponType getType();
}
