package com.github.camilormz.finalreality.model.weapon.types;

import com.github.camilormz.finalreality.model.weapon.AbstractWeapon;
import com.github.camilormz.finalreality.model.weapon.WeaponType;
import org.jetbrains.annotations.NotNull;

/**
 * A class that holds all the information of an axe.
 *
 * @author Camilo Ramírez Canales.
 */
public class Axe extends AbstractWeapon {
    /**
     * Creates aa sword with a name, a base damage and weight
     */
    public Axe(@NotNull String name, final int damage, final int weight) {
        super(name, damage, weight, WeaponType.AXE);
    }
}
