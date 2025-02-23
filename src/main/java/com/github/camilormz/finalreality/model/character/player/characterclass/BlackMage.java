package com.github.camilormz.finalreality.model.character.player.characterclass;

import com.github.camilormz.finalreality.model.character.ICharacter;
import com.github.camilormz.finalreality.model.character.player.AbstractPlayerCharacter;
import com.github.camilormz.finalreality.model.character.player.CharacterClass;
import com.github.camilormz.finalreality.model.character.player.IMagicalCharacter;
import com.github.camilormz.finalreality.model.weapon.WeaponType;
import org.jetbrains.annotations.NotNull;

import java.util.EnumSet;
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
     * @param healthPoints
     *     the black mage's health points
     * @param turnsQueue
     *     the queue with the characters waiting for their turn
     */
    public BlackMage(@NotNull final String name,
                     int healthPoints,
                     final int defense,
                     @NotNull final BlockingQueue<ICharacter> turnsQueue) {
        super(name,
              healthPoints,
              defense,
              turnsQueue,
              CharacterClass.BLACK_MAGE,
              EnumSet.of(WeaponType.KNIFE, WeaponType.STAFF));
    }
}
