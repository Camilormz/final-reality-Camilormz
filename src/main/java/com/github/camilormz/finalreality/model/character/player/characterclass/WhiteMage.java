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
 * A class that holds the methods and information exclusive to White Mages
 *
 * @author Camilo Ramírez Canales.
 */
public class WhiteMage extends AbstractPlayerCharacter implements IMagicalCharacter {
    /**
     * Creates a new white mage.
     *
     * @param name
     *     the white mage's name
     * @param turnsQueue
     *     the queue with the characters waiting for their turn
     */
    public WhiteMage(@NotNull final String name,
                     int healthPoints,
                     @NotNull final BlockingQueue<ICharacter> turnsQueue) {
        super(name,
              healthPoints,
              turnsQueue,
              CharacterClass.WHITE_MAGE,
              EnumSet.of(WeaponType.STAFF));
    }
}
