package com.github.camilormz.finalreality.model.weapon.types;

import com.github.camilormz.finalreality.model.weapon.AbstractWeapon;
import com.github.camilormz.finalreality.model.weapon.WeaponType;
import org.jetbrains.annotations.NotNull;

/**
 * A class that holds all the information of a sword.
 *
 * @author Camilo Ramírez Canales.
 */
public class Sword extends AbstractWeapon {
    /**
     * Creates a sword.
     *
     * @param name
     *     the sword's name
     * @param damage
     *     the damage done by the sword
     * @param weight
     *     the weight of the sword
     */
    public Sword(@NotNull String name, final int damage, final int weight) {
        super(name, damage, weight, WeaponType.SWORD);
    }
}
