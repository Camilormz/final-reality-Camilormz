package com.github.camilormz.finalreality.model.character.player.characterclass;

import com.github.camilormz.finalreality.model.character.ICharacter;
import com.github.camilormz.finalreality.model.character.player.AbstractPlayerCharacter;
import com.github.camilormz.finalreality.model.character.player.CharacterClass;
import com.github.camilormz.finalreality.model.weapon.IWeapon;
import com.github.camilormz.finalreality.model.weapon.WeaponType;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.BlockingQueue;

/**
 * A class that holds the methods and information exclusive to Thief characters
 *
 * @author Camilo Ramírez Canales.
 */
public class Thief extends AbstractPlayerCharacter {
    /**
     * Creates a new thief.
     *
     * @param name
     *     the thief's name
     * @param turnsQueue
     *     the queue with the characters waiting for their turn
     */
    public Thief(@NotNull String name, @NotNull BlockingQueue<ICharacter> turnsQueue) {
        super(name, turnsQueue, CharacterClass.THIEF);
    }

    @Override
    public void equip(IWeapon weapon) {
        WeaponType weaponType = weapon.getType();
        if (weaponType == WeaponType.SWORD
         || weaponType == WeaponType.STAFF
         || weaponType == WeaponType.BOW) {
            this.equippedWeapon = weapon;
        }
    }
}
