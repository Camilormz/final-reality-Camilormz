package com.github.camilormz.finalreality.controller;

import com.github.camilormz.finalreality.model.character.CharacterDomain;
import com.github.camilormz.finalreality.model.character.Enemy;
import com.github.camilormz.finalreality.model.character.ICharacter;
import com.github.camilormz.finalreality.model.character.player.CharacterClass;
import com.github.camilormz.finalreality.model.character.player.IPlayerCharacter;
import com.github.camilormz.finalreality.model.character.player.characterclass.*;
import com.github.camilormz.finalreality.model.weapon.IMagicalWeapon;
import com.github.camilormz.finalreality.model.weapon.IWeapon;
import com.github.camilormz.finalreality.model.weapon.WeaponType;
import com.github.camilormz.finalreality.model.weapon.types.*;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * A class for the game controller, this is a bridge between the user and the game model.
 * It maintains the state and cohesion of the game
 *
 * This class has a large amount of methods available for the player (via GUI), so it's divided in
 * "sections" in the following order:
 *
 *  0. Constructor
 *  1. Creator methods
 *  2. Getters
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

    // ========================================================================================= //
    //                                                                                           //
    // --------------------------- 1. Section for creator methods ------------------------------ //
    //                                                                                           //
    // ========================================================================================= //

    /**
     * Creates a black mage calling its model constructor
     *
     * @see BlackMage
     */
    public BlackMage createBlackMage(@NotNull String name, int healthPoints, final int defense) {
        return new BlackMage(name, healthPoints, defense, turnsQueue);
    }

    /**
     * Creates an engineer calling its model constructor
     *
     * @see Engineer
     */
    public Engineer createEngineer(@NotNull String name, int healthPoints, final int defense) {
        return new Engineer(name, healthPoints, defense, turnsQueue);
    }

    /**
     * Creates a knight calling its model constructor
     *
     * @see Knight
     */
    public Knight createKnight(@NotNull String name, int healthPoints, final int defense) {
        return new Knight(name, healthPoints, defense, turnsQueue);
    }

    /**
     * Creates a thief calling its model constructor
     *
     * @see Thief
     */
    public Thief createThief(@NotNull String name, int healthPoints, final int defense) {
        return new Thief(name, healthPoints, defense, turnsQueue);
    }

    /**
     * Creates a white mage calling its model constructor
     *
     * @see WhiteMage
     */
    public WhiteMage createWhiteMage(@NotNull String name, int healthPoints, final int defense) {
        return new WhiteMage(name, healthPoints, defense, turnsQueue);
    }

    /**
     * Creates an enemy calling its model constructor
     *
     * @see Enemy
     */
    public Enemy createEnemy(@NotNull String name, final int weight, int healthPoints,
                             final int defense, final int damage) {
        return new Enemy(name, weight, healthPoints, defense, damage, turnsQueue);
    }

    /**
     * Creates an axe calling its model constructor
     *
     * @see Axe
     */
    public Axe createAxe(@NotNull String name, final int damage, final int weight) {
        return new Axe(name, damage, weight);
    }

    /**
     * Creates a bow calling its model constructor
     *
     * @see Bow
     */
    public Bow createBow(@NotNull String name, final int damage, final int weight) {
        return new Bow(name, damage, weight);
    }

    /**
     * Creates a knife calling its model constructor
     *
     * @see Knife
     */
    public Knife createKnife(@NotNull String name, final int damage, final int weight) {
        return new Knife(name, damage, weight);
    }

    /**
     * Creates an staff calling its model constructor
     *
     * @see Staff
     */
    public Staff createStaff(@NotNull String name, final int damage,
                             final int weight, final int magicDamage) {
        return new Staff(name, damage, weight, magicDamage);
    }

    /**
     * Creates an sword calling its model constructor
     *
     * @see Sword
     */
    public Sword createSword(@NotNull String name, final int damage, final int weight) {
        return new Sword(name, damage, weight);
    }

    // ========================================================================================= //
    //                                                                                           //
    // ------------------------------ 2. Section for getters ----------------------------------- //
    //                                                                                           //
    // ========================================================================================= //

    /**
     * Gets the characters name from model methods
     */
    public String getCharacterName(@NotNull ICharacter character) {
        return character.getName();
    }

    /**
     * Gets the characters health points from model methods
     */
    public int getCharacterHealthPoints(@NotNull ICharacter character) {
        return character.getHealthPoints();
    }

    /**
     * Gets the character defense points from model methods
     */
    public int getCharacterDefense(@NotNull ICharacter character) {
        return character.getDefensePoints();
    }

    /**
     * Gets the character domain from model methods
     * @see CharacterDomain
     */
    public CharacterDomain getCharacterDomain(@NotNull ICharacter character) {
        return character.getCharacterDomain();
    }

    /**
     * Gets the playable character class from model methods
     * @see CharacterClass
     */
    public CharacterClass getPlayableCharacterClass(@NotNull IPlayerCharacter playableCharacter) {
        return playableCharacter.getCharacterClass();
    }

    /**
     * Gets the playable character equipped weapon from model methods, return null if there is no
     * equipped weapon
     */
    public IWeapon getPlayableCharacterEquippedWeapon(
            @NotNull IPlayerCharacter playableCharacter) {
        return playableCharacter.getEquippedWeapon();
    }

    /**
     * Gets the enemy character weight from model methods
     */
    public int getEnemyWeight(@NotNull Enemy enemy) {
        return enemy.getWeight();
    }

    /**
     * Gets the enemy character damage from model methods
     */
    public int getEnemyDamage(@NotNull Enemy enemy) {
        return enemy.getDamagePoints();
    }

    /**
     * Gets the weapons name from model methods
     */
    public String getWeaponName(@NotNull IWeapon weapon) {
        return weapon.getName();
    }

    /**
     * Gets the weapons damage from model methods
     */
    public int getWeaponDamage(@NotNull IWeapon weapon) {
        return weapon.getDamage();
    }

    /**
     * Gets the weapons weight from model methods
     */
    public int getWeaponWeight(@NotNull IWeapon weapon) {
        return weapon.getWeight();
    }

    /**
     * Gets the weapons type from model methods
     */
    public WeaponType getWeaponType(@NotNull IWeapon weapon) {
        return weapon.getType();
    }

    /**
     * Gets the weapons holder from model methods, returns null if no character has this weapon
     * equipped
     */
    public IPlayerCharacter getWeaponHolder(@NotNull IWeapon weapon) {
        return weapon.getHolder();
    }

    /**
     * Gets the magic damage of the weapon from model methods
     */
    public int getMagicDamage(@NotNull IMagicalWeapon weapon) {
        return weapon.getMagicDamage();
    }

}
