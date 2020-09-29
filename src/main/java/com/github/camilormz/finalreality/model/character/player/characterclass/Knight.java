package com.github.camilormz.finalreality.model.character.player.characterclass;

import com.github.camilormz.finalreality.model.character.ICharacter;
import com.github.camilormz.finalreality.model.character.player.CharacterClass;
import com.github.camilormz.finalreality.model.character.player.AbstractPlayerCharacter;
import com.github.camilormz.finalreality.model.weapon.IWeapon;
import com.github.camilormz.finalreality.model.weapon.WeaponType;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.BlockingQueue;

/**
 * A class that holds the methods and information exclusive to Knights
 *
 * @author Camilo Ramírez Canales.
 */
public class Knight extends AbstractPlayerCharacter {
    /**
     * Creates a new knight.
     *
     * @param name
     *     the knight's name
     * @param turnsQueue
     *     the queue with the characters waiting for their turn
     */
    public Knight(@NotNull String name, @NotNull BlockingQueue<ICharacter> turnsQueue) {
        super(name, turnsQueue, CharacterClass.KNIGHT);
    }

    @Override
    public void equip(IWeapon weapon) {
        WeaponType weaponType = weapon.getType();
        if (weaponType == WeaponType.SWORD
         || weaponType == WeaponType.AXE
         || weaponType == WeaponType.KNIFE) {
            this.equippedWeapon = weapon;
        }
    }
}
