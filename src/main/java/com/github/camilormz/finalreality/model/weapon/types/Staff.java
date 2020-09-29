package com.github.camilormz.finalreality.model.weapon.types;

import com.github.camilormz.finalreality.model.weapon.AbstractWeapon;
import com.github.camilormz.finalreality.model.weapon.IMagicalWeapon;
import com.github.camilormz.finalreality.model.weapon.WeaponType;
import org.jetbrains.annotations.NotNull;

/**
 * A class that holds all the information of a staff.
 *
 * @author Camilo Ramírez Canales.
 */
public class Staff extends AbstractWeapon implements IMagicalWeapon {

    private final int magicDamage;

    /**
     * Creates a staff with a name, a base damage, a weight and a magical damage
     */
    public Staff(@NotNull String name, final int damage, final int weight, final int magicDamage) {
        super(name, damage, weight, WeaponType.STAFF);
        this.magicDamage = magicDamage;
    }

    @Override
    public int getMagicDamage() {
        return this.magicDamage;
    }
}
