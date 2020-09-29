package com.github.camilormz.finalreality.model.character.player.characterclass;

import com.github.camilormz.finalreality.model.character.ICharacter;
import com.github.camilormz.finalreality.model.character.player.AbstractPlayerCharacter;
import com.github.camilormz.finalreality.model.character.player.CharacterClass;
import com.github.camilormz.finalreality.model.character.player.IMagicalCharacter;
import com.github.camilormz.finalreality.model.weapon.IWeapon;
import com.github.camilormz.finalreality.model.weapon.WeaponType;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.BlockingQueue;

/**
 * A class that holds the methods and information exclusive to Black Mages
 *
 * @author Camilo Ramírez Canales.
 */
public class BlackMage extends AbstractPlayerCharacter implements IMagicalCharacter {
    /**
     * Creates a new black mage.
     *
     * @param name
     *     the black mage's name
     * @param turnsQueue
     *     the queue with the characters waiting for their turn
     */
    public BlackMage(@NotNull String name, @NotNull BlockingQueue<ICharacter> turnsQueue) {
        super(name, turnsQueue, CharacterClass.BLACK_MAGE);
    }

    @Override
    public void equip(IWeapon weapon) {
        WeaponType weaponType = weapon.getType();
        if (weaponType == WeaponType.KNIFE || weaponType == WeaponType.STAFF) {
            this.equippedWeapon = weapon;
        }
    }
}
