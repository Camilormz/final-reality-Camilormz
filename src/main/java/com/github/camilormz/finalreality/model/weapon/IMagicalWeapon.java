package com.github.camilormz.finalreality.model.weapon;

/**
 * This represents a magical weapon of the game, it can be of any magic type.
 *
 * @author Camilo Ramírez Canales.
 */
public interface IMagicalWeapon extends IWeapon {
    /**
     * Gets the weapon magic damage
     */
    int getMagicDamage();
}
