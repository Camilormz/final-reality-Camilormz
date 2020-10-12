package com.github.camilormz.finalreality.model.weapon.types;

import com.github.camilormz.finalreality.model.weapon.AbstractWeapon;
import com.github.camilormz.finalreality.model.weapon.WeaponType;
import org.jetbrains.annotations.NotNull;

/**
 * A class that holds all the information of a bow.
 *
 * @author Camilo Ramírez Canales.
 */
public class Bow extends AbstractWeapon {
    /**
     * Creates a bow.
     *
     * @param name
     *     the bow's name
     * @param damage
     *     the damage done by the bow
     * @param weight
     *     the weight of the bow
     */
    public Bow(@NotNull String name, final int damage, final int weight) {
        super(name, damage, weight, WeaponType.BOW);
    }
}
