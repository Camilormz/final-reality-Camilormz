package com.github.camilormz.finalreality.model.weapon.types;

import com.github.camilormz.finalreality.model.weapon.AbstractWeapon;
import com.github.camilormz.finalreality.model.weapon.WeaponType;
import org.jetbrains.annotations.NotNull;

/**
 * A class that holds all the information of a knife.
 *
 * @author Camilo Ramírez Canales.
 */
public class Knife extends AbstractWeapon {
    /**
     * Creates a knife with a name, a base damage and weight
     */
    public Knife(@NotNull String name, final int damage, final int weight) {
        super(name, damage, weight, WeaponType.KNIFE);
    }
}
