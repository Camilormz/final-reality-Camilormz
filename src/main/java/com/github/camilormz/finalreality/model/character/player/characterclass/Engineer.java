package com.github.camilormz.finalreality.model.character.player.characterclass;

import com.github.camilormz.finalreality.model.character.ICharacter;
import com.github.camilormz.finalreality.model.character.player.AbstractPlayerCharacter;
import com.github.camilormz.finalreality.model.character.player.CharacterClass;
import com.github.camilormz.finalreality.model.weapon.WeaponType;
import org.jetbrains.annotations.NotNull;

import java.util.EnumSet;
import java.util.concurrent.BlockingQueue;

/**
 * A class that holds the methods and information exclusive to Engineers
 *
 * @author Camilo Ramírez Canales.
 */
public class Engineer extends AbstractPlayerCharacter {
    /**
     * Creates a new engineer.
     *
     * @param name
     *     the engineer's name
     * @param healthPoints
     *     the engineer's health points
     * @param turnsQueue
     *     the queue with the characters waiting for their turn
     */
    public Engineer(@NotNull final String name,
                    int healthPoints,
                    final int defense,
                    @NotNull final BlockingQueue<ICharacter> turnsQueue) {
        super(name,
              healthPoints,
              defense,
              turnsQueue,
              CharacterClass.ENGINEER,
              EnumSet.of(WeaponType.AXE, WeaponType.BOW));
    }
}
