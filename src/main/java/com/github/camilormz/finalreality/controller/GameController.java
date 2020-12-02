package com.github.camilormz.finalreality.controller;

import com.github.camilormz.finalreality.model.character.Enemy;
import com.github.camilormz.finalreality.model.character.ICharacter;
import com.github.camilormz.finalreality.model.character.player.characterclass.*;
import com.github.camilormz.finalreality.model.weapon.types.*;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * A class for the game controller, this is a bridge between the user and the game model.
 * It maintains the state and cohesion of the game
 *
 * @author Camilo Ramírez Canales.
 */
public class GameController {

    BlockingQueue<ICharacter> turnsQueue;

    /**
     * Game Controller constructor, it creates a new set of empty instance variables for the turns
     * queue, the player assigned characters, the CPU assigned enemies and inventory
     */
    public GameController() {
        turnsQueue = new LinkedBlockingQueue<>();
    }

    /**
     * Creates a black mage calling its model constructor
     * @see BlackMage
     */
    public BlackMage createBlackMage(@NotNull String name, int healthPoints, final int defense) {
        return new BlackMage(name, healthPoints, defense, turnsQueue);
    }

    /**
     * Creates an engineer calling its model constructor
     * @see Engineer
     */
    public Engineer createEngineer(@NotNull String name, int healthPoints, final int defense) {
        return new Engineer(name, healthPoints, defense, turnsQueue);
    }

    /**
     * Creates a knight calling its model constructor
     * @see Knight
     */
    public Knight createKnight(@NotNull String name, int healthPoints, final int defense) {
        return new Knight(name, healthPoints, defense, turnsQueue);
    }

    /**
     * Creates a thief calling its model constructor
     * @see Thief
     */
    public Thief createThief(@NotNull String name, int healthPoints, final int defense) {
        return new Thief(name, healthPoints, defense, turnsQueue);
    }

    /**
     * Creates a white mage calling its model constructor
     * @see WhiteMage
     */
    public WhiteMage createWhiteMage(@NotNull String name, int healthPoints, final int defense) {
        return new WhiteMage(name, healthPoints, defense, turnsQueue);
    }

    /**
     * Creates an enemy calling its model constructor
     * @see Enemy
     */
    public Enemy createEnemy(@NotNull String name, final int weight, int healthPoints,
                             final int defense, final int damage) {
        return new Enemy(name, weight, healthPoints, defense, damage, turnsQueue);
    }

    /**
     * Creates an axe calling its model constructor
     * @see Axe
     */
    public Axe createAxe(@NotNull String name, final int damage, final int weight) {
        return new Axe(name, damage, weight);
    }

    /**
     * Creates a bow calling its model constructor
     * @see Bow
     */
    public Bow createBow(@NotNull String name, final int damage, final int weight) {
      return new Bow(name, damage, weight);
    }

    /**
     * Creates a knife calling its model constructor
     * @see Knife
     */
    public Knife createKnife(@NotNull String name, final int damage, final int weight) {
        return new Knife(name, damage, weight);
    }

    /**
     * Creates an staff calling its model constructor
     * @see Staff
     */
    public Staff createStaff(@NotNull String name, final int damage,
                             final int weight, final int magicDamage) {
        return new Staff(name, damage, weight, magicDamage);
    }

    /**
     * Creates an sword calling its model constructor
     * @see Sword
     */
    public Sword createSword(@NotNull String name, final int damage, final int weight) {
        return new Sword(name, damage, weight);
    }
}
