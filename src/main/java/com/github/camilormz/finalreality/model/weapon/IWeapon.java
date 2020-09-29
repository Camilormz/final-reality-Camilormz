package com.github.camilormz.finalreality.model.weapon;

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
