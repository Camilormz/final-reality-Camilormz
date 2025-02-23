package com.github.camilormz.finalreality.model.character.player.characterclass;

import com.github.camilormz.finalreality.model.character.ICharacter;
import com.github.camilormz.finalreality.model.character.player.CharacterClass;
import com.github.camilormz.finalreality.model.character.player.AbstractPlayerCharacter;
import com.github.camilormz.finalreality.model.weapon.WeaponType;
import org.jetbrains.annotations.NotNull;

import java.util.EnumSet;
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
     * @param healthPoints
     *     the knight's health points
     * @param turnsQueue
     *     the queue with the characters waiting for their turn
     */
    public Knight(@NotNull final String name,
                  int healthPoints,
                  final int defense,
                  @NotNull final BlockingQueue<ICharacter> turnsQueue) {
        super(name,
              healthPoints,
              defense,
              turnsQueue,
              CharacterClass.KNIGHT,
              EnumSet.of(WeaponType.SWORD, WeaponType.AXE, WeaponType.KNIFE));
    }
}
