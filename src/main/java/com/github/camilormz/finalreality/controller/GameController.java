package com.github.camilormz.finalreality.controller;

import com.github.camilormz.finalreality.controller.handlers.CharacterEnqueuedHandler;
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

import java.beans.PropertyChangeListener;
import java.util.HashSet;
import java.util.Set;
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
 *  1. Triggered methods (executed by observers)
 *  2. Turns related methods
 *  3. Game state methods (inventory, assigned characters to player and CPU)
 *  4. Combat availability and winner checks
 *  5. Element creator methods
 *  6. Getters
 *
 * @author Camilo Ramírez Canales.
 */
public class GameController {

    private final BlockingQueue<ICharacter> turnsQueue;
    private final Set<IPlayerCharacter> playerAssignedCharacters;
    private final Set<Enemy> enemiesAssignedToCPU;
    private final Set<IWeapon> inventory;

    private final PropertyChangeListener characterEnqueuedHandler =
            new CharacterEnqueuedHandler(this);

    private String winner;
    private ICharacter currentTurnCharacter;

    public static final String WINNER_NOBODY = "Nobody";
    public static final String WINNER_PLAYER = "Player";
    public static final String WINNER_CPU = "CPU";
    public static final String WINNER_TIE = "Tie";

    /**
     * Game Controller constructor, it creates a new set of empty instance variables for the turns
     * queue, the player assigned characters, the CPU assigned enemies and inventory
     */
    public GameController() {
        turnsQueue = new LinkedBlockingQueue<>();
        playerAssignedCharacters = new HashSet<>();
        enemiesAssignedToCPU = new HashSet<>();
        inventory = new HashSet<>();

        currentTurnCharacter = null;
        winner = WINNER_NOBODY;
    }

    // ========================================================================================= //
    //                                                                                           //
    // ------------------ 1. Section for methods executed by observers ------------------------- //
    //                                                                                           //
    // ========================================================================================= //

    /**
     * Performs a routine on player winning, currently it does no action
     */
    private void onPlayerWinning() {}

    /**
     * Performs a routine on player loosing, currently it does no action
     */
    private void onPlayerLosing() {}

    /**
     * Performs a routine on player tie, currently it's not possible to reach this state in the
     * game according to its dynamics, it does no action
     */
    private void onPlayerTying() {}

    /**
     * Performs a routine on character's turn start, currently it does no action
     */
    private void onCharacterTurnStart(ICharacter character) {}

    /**
     * Performs a routine on character's turn end, currently it does no action
     */
    private void onCharacterTurnEnd(ICharacter character) {}

    /**
     * Performs an action on queue update, currently it starts the next character turn
     */
    public void onQueueEnqueuing() {
        ICharacter nextCharacter = peekWaitTurnQueueHead();
        if (nextCharacter != null && currentTurnCharacter == null) {
            turnStart();
        }
    }

    /**
     * Performs an action on character knocking out, currently it checks for any winner
     */
    private void onCharacterKnockOut() {
        updateWinner();
        switch (getWinner()) {
            case WINNER_PLAYER:
                onPlayerWinning();
                break;
            case WINNER_CPU:
                onPlayerLosing();
                break;
            case WINNER_TIE:
                onPlayerTying();
                break;
        }
    }

    // ========================================================================================= //
    //                                                                                           //
    // --------------------------- 2. Section for turns related methods --- -------------------- //
    //                                                                                           //
    // ========================================================================================= //

    /**
     * Starts the next turn for wait turn queue head
     */
    public void turnStart() {
        currentTurnCharacter = peekWaitTurnQueueHead();
        onCharacterTurnStart(currentTurnCharacter);
    }

    /**
     * Ends the character turn
     */
    public void turnEnd() {
        if (currentTurnCharacter != null) {
            onCharacterTurnEnd(currentTurnCharacter);
            currentTurnCharacter = null;
            removeWaitTurnQueueHead();
        }
    }

    /**
     * Returns the character whose turn is currently happening
     */
    public ICharacter getCurrentTurnCharacter() {
        return this.currentTurnCharacter;
    }

    /**
     * Checks if the turns queue is empty or not
     */
    public boolean isTurnsQueueEmpty() {
        return turnsQueue.isEmpty();
    }

    /**
     * Returns the head of the turns queue
     */
    public ICharacter peekWaitTurnQueueHead() {
        return turnsQueue.peek();
    }

    /**
     * Removes the head of the turns queue
     */
    private void removeWaitTurnQueueHead() {
        turnsQueue.poll();
    }

    /**
     * Starts the timer of the character according to its turn weight to enqueue it on the turns
     * queue, for more details on the turn weight
     * @see com.github.camilormz.finalreality.model.character.AbstractCharacter
     */
    public void waitEnqueueForTurn(ICharacter character) {
        character.waitTurn();
    }

    // ========================================================================================= //
    //                                                                                           //
    // --------------------------- 3. Section for game state methods --------------------------- //
    //                                                                                           //
    // ========================================================================================= //


    /**
     * Returns the set that contains the playable characters assigned to the player
     */
    public Set<IPlayerCharacter> getPlayerAssignedCharacters() {
        return this.playerAssignedCharacters;
    }

    /**
     * Assigns a character to the player
     */
    public void assignToPlayer(@NotNull IPlayerCharacter character) {
        this.getPlayerAssignedCharacters().add(character);
    }

    /**
     * Removes a character from the players assigned characters set
     */
    public void removeFromPlayer(@NotNull IPlayerCharacter character) {
        this.getPlayerAssignedCharacters().remove(character);
    }

    /**
     * Returns the set that contains the enemies assigned to the CPU for using in the game
     */
    public Set<Enemy> getEnemiesAssigned() {
        return this.enemiesAssignedToCPU;
    }

    /**
     * Assigns an enemy to the CPU for using in combat
     */
    public void assignEnemy(@NotNull Enemy enemy) {
        this.getEnemiesAssigned().add(enemy);
    }

    /**
     * Removes an enemy to the CPU assigned enemies set
     */
    public void removeAssignedEnemy(@NotNull Enemy enemy) {
        this.getEnemiesAssigned().remove(enemy);
    }

    /**
     * Returns the game inventory
     */
    public Set<IWeapon> getInventory() {
        return this.inventory;
    }

    /**
     * Assigns the weapon to the inventory
     */
    public void assignToInventory(IWeapon weapon) {
        this.getInventory().add(weapon);
    }

    /**
     * Removes the weapon from the inventory
     */
    public void removeFromInventory(IWeapon weapon) {
        this.getInventory().remove(weapon);
    }


    /**
     * Tries to equip a playable character with a weapon, it's not possible if the weapon type is
     * not compatible with the character class or if the weapon is already equipped, this is done
     * by calling the model methods, returns True if the equipment is successfully, False if not
     */
    public boolean tryToEquipWeapon(IPlayerCharacter character, IWeapon weapon) {
        character.tryToEquip(weapon);
        return weapon.equals(character.getEquippedWeapon());
    }

    /**
     * Un-equips the equipped weapon of the character if it had already one
     */
    public void unEquipWeapon(IPlayerCharacter character) {
        character.unEquip();
    }

    /**
     * Performs an attack between two characters using model methods
     */
    public void performAttack(ICharacter attacker, ICharacter adversary) {
        attacker.attack(adversary);
    }

    // ========================================================================================= //
    //                                                                                           //
    // ------------------ 4. Section for combat availability and winner checks ----------------- //
    //                                                                                           //
    // ========================================================================================= //

    /**
     * Checks if the character is available for combat
     */
    public boolean checkCombatAvailability(ICharacter character) {
        return character.isAvailableForCombat();
    }

    /**
     * Checks if there is any character available for combat in the given set
     */
    public boolean isAnyAvailableForCombat(Set<? extends ICharacter> assignedSet) {
        for (ICharacter character : assignedSet) {
            if (checkCombatAvailability(character)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Counts the amount of characters in the set that are available for combat
     */
    public int countAvailableForCombat(Set<? extends ICharacter> assignedSet) {
        int counter = 0;
        for (ICharacter character : assignedSet) {
            if (checkCombatAvailability(character)) {
                counter++;
            }
        }
        return counter;
    }

    /**
     * Checks the current winner and updates the associated instance value
     */
    public void updateWinner() {
        boolean isAnyPlayerCharacterAvailable =
                isAnyAvailableForCombat(this.playerAssignedCharacters);
        boolean isAnyEnemyAvailable = isAnyAvailableForCombat(this.enemiesAssignedToCPU);
        if (isAnyPlayerCharacterAvailable) {
            if (isAnyEnemyAvailable) {
                this.winner = WINNER_NOBODY;
            } else {
                this.winner = WINNER_PLAYER;
            }
        } else {
            if (isAnyEnemyAvailable) {
                this.winner = WINNER_CPU;
            } else {
                this.winner = WINNER_TIE;
            }
        }
    }

    /**
     * Gets the current checked winner
     * It is highly recommendable to call checkWinner() before calling this method
     */
    public String getWinner() {
        return this.winner;
    }

    // ========================================================================================= //
    //                                                                                           //
    // --------------------------- 5. Section for creator methods ------------------------------ //
    //                                                                                           //
    // ========================================================================================= //

    /**
     * Creates a black mage calling its model constructor
     * @see BlackMage
     */
    public BlackMage createBlackMage(@NotNull String name, int healthPoints, final int defense) {
        BlackMage blackMage = new BlackMage(name, healthPoints, defense, turnsQueue);
        blackMage.addListener(characterEnqueuedHandler);
        return blackMage;
    }

    /**
     * Creates an engineer calling its model constructor
     * @see Engineer
     */
    public Engineer createEngineer(@NotNull String name, int healthPoints, final int defense) {
        Engineer engineer = new Engineer(name, healthPoints, defense, turnsQueue);
        engineer.addListener(characterEnqueuedHandler);
        return engineer;
    }

    /**
     * Creates a knight calling its model constructor
     * @see Knight
     */
    public Knight createKnight(@NotNull String name, int healthPoints, final int defense) {
        Knight knight = new Knight(name, healthPoints, defense, turnsQueue);
        knight.addListener(characterEnqueuedHandler);
        return knight;
    }

    /**
     * Creates a thief calling its model constructor
     * @see Thief
     */
    public Thief createThief(@NotNull String name, int healthPoints, final int defense) {
        Thief thief = new Thief(name, healthPoints, defense, turnsQueue);
        thief.addListener(characterEnqueuedHandler);
        return thief;
    }

    /**
     * Creates a white mage calling its model constructor
     * @see WhiteMage
     */
    public WhiteMage createWhiteMage(@NotNull String name, int healthPoints, final int defense) {
        WhiteMage whiteMage = new WhiteMage(name, healthPoints, defense, turnsQueue);
        whiteMage.addListener(characterEnqueuedHandler);
        return whiteMage;
    }

    /**
     * Creates an enemy calling its model constructor
     * @see Enemy
     */
    public Enemy createEnemy(@NotNull String name, final int weight, int healthPoints,
                             final int defense, final int damage) {
        Enemy enemy = new Enemy(name, weight, healthPoints, defense, damage, turnsQueue);
        enemy.addListener(characterEnqueuedHandler);
        return enemy;
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

    // ========================================================================================= //
    //                                                                                           //
    // ------------------------------ 6. Section for getters ----------------------------------- //
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
