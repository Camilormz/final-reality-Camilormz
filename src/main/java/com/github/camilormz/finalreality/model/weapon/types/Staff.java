package com.github.camilormz.finalreality.model.weapon.types;

import com.github.camilormz.finalreality.model.weapon.AbstractWeapon;
import com.github.camilormz.finalreality.model.weapon.IMagicalWeapon;
import com.github.camilormz.finalreality.model.weapon.WeaponType;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * A class that holds all the information of a staff.
 *
 * @author Camilo Ramírez Canales.
 */
public class Staff extends AbstractWeapon implements IMagicalWeapon {

    private final int magicDamage;

    /**
     * Creates a staff.
     *
     * @param name
     *     the staff's name
     * @param damage
     *     the damage done by the staff used as a melee weapon
     * @param weight
     *     the weight of the staff
     * @param magicDamage
     *     the magical damage (spell power) of the staff
     */
    public Staff(@NotNull String name, final int damage, final int weight, final int magicDamage) {
        super(name, damage, weight, WeaponType.STAFF);
        this.magicDamage = magicDamage;
    }

    @Override
    public int getMagicDamage() {
        return this.magicDamage;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof IMagicalWeapon)) {
            return false;
        }
        final IMagicalWeapon weapon = (IMagicalWeapon) o;
        return this.getName().equals(weapon.getName()) &&
               this.getDamage() == weapon.getDamage() &&
               this.getWeight() == weapon.getWeight() &&
               this.getMagicDamage() == weapon.getMagicDamage();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getName(), this.getDamage(), this.getWeight(), this.getType(),
                            this.getMagicDamage());
    }
}
