package com.github.camilormz.finalreality.controller;

import com.github.camilormz.finalreality.controller.handlers.CharacterEnqueuedHandler;
import com.github.camilormz.finalreality.controller.handlers.CharacterKnockOutHandler;
import com.github.camilormz.finalreality.controller.phases.Phase;
import com.github.camilormz.finalreality.controller.phases.detailedphases.InitPhase;
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
import java.util.LinkedList;
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
 *  3. Game phase related methods (state machines)
 *  4. Game state methods (inventory, assigned characters to player and CPU)
 *  5. Combat availability and winner checks
 *  6. Element creator methods
 *  7. Getters
 *  8. GUI auxiliary methods
 *
 * A game controller is equal to another when they share the same references to all its instance
 * variables (except handlers; these variables are turnsQueue, inventory, enemiesAssignedToCPU,
 * playerAssignedCharacters, winner and currentTurnCharacter). As this class has only its default
 * constructor and the instance variables are created at construction (not passed as parameters) it
 * is impossible for different instances of GameController have the same reference for their
 * instance variables.
 *
 * The previous paragraph justifies that there is not necessity to override equals and hashCode of
 * the Object class as that implementation of these methods fits perfectly for the equivalence of
 * GameControllers as stated in the previous paragraph. Anyways there exists a test fot the correct
 * GameController initialization
 *
 * @author Camilo Ramírez Canales.
 */
public class GameController {

    private final BlockingQueue<ICharacter> turnsQueue;
    private final LinkedList<IPlayerCharacter> playerAssignedCharacters;
    private final LinkedList<Enemy> enemiesAssignedToCPU;
    private final LinkedList<IWeapon> inventory;

    private final PropertyChangeListener characterEnqueuedHandler =
            new CharacterEnqueuedHandler(this);
    private final PropertyChangeListener characterKnockOutHandler =
            new CharacterKnockOutHandler(this);

    private String winner;
    public Phase gamePhase;
    private ICharacter currentTurnCharacter;

    private final int AMOUNT_PLAYABLE_CHARACTERS = 5;
    private final int MAX_ENEMIES = 5;

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
        playerAssignedCharacters = new LinkedList<>();
        enemiesAssignedToCPU = new LinkedList<>();
        inventory = new LinkedList<>();

        currentTurnCharacter = null;
        winner = WINNER_NOBODY;

        this.setPhase(new InitPhase());
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
        if (currentTurnCharacter == null) {
            turnStart();
        }
    }

    /**
     * Performs an action on character knocking out, currently it checks for any winner
     */
    public void onCharacterKnockOut() {
        updateWinner();
        if (getWinner().equals(WINNER_PLAYER)) {
            onPlayerWinning();
        } else if (getWinner().equals(WINNER_CPU)) {
            onPlayerLosing();
        }
    }

    // ========================================================================================= //
    //                                                                                           //
    // --------------------------- 2. Section for turns related methods ------------------------ //
    //                                                                                           //
    // ========================================================================================= //

    /**
     * Starts the next turn for wait turn queue head character
     */
    public void turnStart() {
        currentTurnCharacter = peekWaitTurnQueueHead();
        this.gamePhase.turnStart();
        onCharacterTurnStart(currentTurnCharacter);
    }

    /**
     * Ends the character turn
     */
    public void turnEnd() {
        if (currentTurnCharacter != null) {
            onCharacterTurnEnd(currentTurnCharacter);
            this.gamePhase.turnEnd();
            currentTurnCharacter = null;
            removeWaitTurnQueueHead();
            this.startPickingPhase();
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
    public void waitEnqueueForTurn(@NotNull ICharacter character) {
        character.waitTurn();
    }

    // ========================================================================================= //
    //                                                                                           //
    // --------------------------- 3. Section for phase related methods ------------------------ //
    //                                                                                           //
    // ========================================================================================= //

    /**
     * Sets the phase of the controller
     */
    public void setPhase(Phase phase) {
        this.gamePhase = phase;
        this.gamePhase.setController(this);
    }

    /**
     * Initializes the controller assigning the enemies and players specified in InitPhase
     * @see InitPhase
     */
    public void initController() {
        this.gamePhase.initController();
    }

    /**
     * Equips the character of the current turn with a weapon
     */
    public void equipTurnCharacter(IWeapon weapon) {
        this.gamePhase.equipTurnCharacter(weapon);
    }

    /**
     * Un-equips any weapon that the character could have
     */
    public void unEquipTurnCharacter() {
        this.gamePhase.unEquipTurnCharacter();
    }

    /**
     * Finish the equipment procedure, i.e., the equipment phase for the character of the current
     * turn
     */
    public void finishEquipmentProcedure() {
        this.gamePhase.finishEquipmentProcedure();
    }

    /**
     * Given the list index of the adversary, this method executes an attack from the character of
     * the current turn
     */
    public boolean executeAttack(int adversarialIndex) {
        return this.gamePhase.executeAttack(adversarialIndex);
    }

    /**
     * Starts the picking phase
     */
    public void startPickingPhase() {
       this.gamePhase.startPickingPhase();
    }

    /**
     * Returns true if the current phase is InitPhase
     */
    public boolean isAtInitPhase() {
        return this.gamePhase.isAtInitPhase();
    }

    /**
     * Returns true if the current phase is PickingPhase
     */
    public boolean isAtPickingPhase() {
        return this.gamePhase.isAtPickingPhase();
    }

    /**
     * Returns true if the current phase is EquipmentPhase
     */
    public boolean isAtEquipmentPhase() {
        return this.gamePhase.isAtEquipmentPhase();
    }

    /**
     * Returns true if the current phase is AttackPhase
     */
    public boolean isAtAttackPhase() {
        return this.gamePhase.isAtAttackPhase();
    }

    /**
     * Returns true if the current phase is EnqueuingPhase
     */
    public boolean isAtEnqueuingPhase() {
        return this.gamePhase.isAtEnqueuingPhase();
    }

    // ========================================================================================= //
    //                                                                                           //
    // --------------------------- 4. Section for game state methods --------------------------- //
    //                                                                                           //
    // ========================================================================================= //

    /**
     * Returns the set that contains the playable characters assigned to the player
     */
    public LinkedList<IPlayerCharacter> getPlayerAssignedCharacters() {
        return this.playerAssignedCharacters;
    }

    /**
     * Assigns a character to the player
     */
    public void assignToPlayer(@NotNull IPlayerCharacter character) {
        LinkedList<IPlayerCharacter> assignedToPlayerList = this.getPlayerAssignedCharacters();
        if (!assignedToPlayerList.contains(character)) {
            assignedToPlayerList.add(character);
        }
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
    public LinkedList<Enemy> getEnemiesAssigned() {
        return this.enemiesAssignedToCPU;
    }

    /**
     * Assigns an enemy to the CPU for using in combat
     */
    public void assignEnemy(@NotNull Enemy enemy) {
        LinkedList<Enemy> assignedEnemiesList = this.getEnemiesAssigned();
        if (!assignedEnemiesList.contains(enemy)) {
            assignedEnemiesList.add(enemy);
        }
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
    public LinkedList<IWeapon> getInventory() {
        return this.inventory;
    }

    /**
     * Assigns the weapon to the inventory
     */
    public void assignToInventory(IWeapon weapon) {
        LinkedList<IWeapon> currentInventory = this.getInventory();
        if (!currentInventory.contains(weapon)) {
            currentInventory.add(weapon);
        }
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

    /**
     * Gets the amount of playable characters fixed for this controller
     */
    public int getAmountPlayableCharacters() {
        return this.AMOUNT_PLAYABLE_CHARACTERS;
    }

    /**
     * Gets the maximum possible amount of enemies allowed in this controller
     */
    public int getMaxEnemies() {
        return this.MAX_ENEMIES;
    }

    // ========================================================================================= //
    //                                                                                           //
    // ------------------ 5. Section for combat availability and winner checks ----------------- //
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
    public boolean isAnyAvailableForCombat(LinkedList<? extends ICharacter> assignedSet) {
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
    public int countAvailableForCombat(LinkedList<? extends ICharacter> assignedSet) {
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
    // --------------------------- 6. Section for creator methods ------------------------------ //
    //                                                                                           //
    // ========================================================================================= //

    /**
     * Creates a black mage calling its model constructor
     * @see BlackMage
     */
    public BlackMage createBlackMage(@NotNull String name, int healthPoints, final int defense) {
        BlackMage blackMage = new BlackMage(name, healthPoints, defense, turnsQueue);
        blackMage.addEnqueuingListener(characterEnqueuedHandler);
        blackMage.addKnockOutListener(characterKnockOutHandler);
        return blackMage;
    }

    /**
     * Creates an engineer calling its model constructor
     * @see Engineer
     */
    public Engineer createEngineer(@NotNull String name, int healthPoints, final int defense) {
        Engineer engineer = new Engineer(name, healthPoints, defense, turnsQueue);
        engineer.addEnqueuingListener(characterEnqueuedHandler);
        engineer.addKnockOutListener(characterKnockOutHandler);
        return engineer;
    }

    /**
     * Creates a knight calling its model constructor
     * @see Knight
     */
    public Knight createKnight(@NotNull String name, int healthPoints, final int defense) {
        Knight knight = new Knight(name, healthPoints, defense, turnsQueue);
        knight.addEnqueuingListener(characterEnqueuedHandler);
        knight.addKnockOutListener(characterKnockOutHandler);
        return knight;
    }

    /**
     * Creates a thief calling its model constructor
     * @see Thief
     */
    public Thief createThief(@NotNull String name, int healthPoints, final int defense) {
        Thief thief = new Thief(name, healthPoints, defense, turnsQueue);
        thief.addEnqueuingListener(characterEnqueuedHandler);
        thief.addKnockOutListener(characterKnockOutHandler);
        return thief;
    }

    /**
     * Creates a white mage calling its model constructor
     * @see WhiteMage
     */
    public WhiteMage createWhiteMage(@NotNull String name, int healthPoints, final int defense) {
        WhiteMage whiteMage = new WhiteMage(name, healthPoints, defense, turnsQueue);
        whiteMage.addEnqueuingListener(characterEnqueuedHandler);
        whiteMage.addKnockOutListener(characterKnockOutHandler);
        return whiteMage;
    }

    /**
     * Creates an enemy calling its model constructor
     * @see Enemy
     */
    public Enemy createEnemy(@NotNull String name, final int weight, int healthPoints,
                             final int defense, final int damage) {
        Enemy enemy = new Enemy(name, weight, healthPoints, defense, damage, turnsQueue);
        enemy.addEnqueuingListener(characterEnqueuedHandler);
        enemy.addKnockOutListener(characterKnockOutHandler);
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
    // ------------------------------ 7. Section for getters ----------------------------------- //
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

    /**
     * Check if the current turn character is equipped or not
     */
    public boolean isCurrentTurnCharacterEquipped() {
        ICharacter currentCharacter = this.getCurrentTurnCharacter();
        if (currentCharacter.getCharacterDomain() == CharacterDomain.PLAYABLE) {
            IPlayerCharacter character = (IPlayerCharacter) currentCharacter;
            return character.getEquippedWeapon() != null;
        } else {
            return false;
        }
    }

    /**
     * Returns true if it's the turn of any character
     */
    public boolean isAnyCharacterInTurn() {
        return this.getCurrentTurnCharacter() != null;
    }

    /**
     * Try to equip the weapon on the given index to the current turn character
     */
    public void tryToEquipCurrentByIndex(int index) {
        this.equipTurnCharacter(this.getInventory().get(index));
    }

    /**
     * Returns if the current turn character is a playable one
     */
    public boolean isCurrentCharacterEnemy() {
        ICharacter currentCharacter = this.getCurrentTurnCharacter();
        if (currentCharacter != null) {
            return this.getCurrentTurnCharacter().getCharacterDomain() == CharacterDomain.ENEMY;
        } else {
            return false;
        }
    }

    // ========================================================================================= //
    //                                                                                           //
    // ----------------------- 8. Section for GUI auxiliary methods ---------------------------- //
    //                                                                                           //
    // ========================================================================================= //

    private String getHolderString(IPlayerCharacter character) {
        if (character == null) {
            return "No holder";
        }
        return String.format("Holder: %s \"%s\"",
                this.getPlayableCharacterClass(character).getDescription(),
                this.getCharacterName(character));
    }

    private String getEquippedString(IWeapon weapon) {
        if (weapon == null) {
            return "No equipped weapon";
        }
        return String.format("Weapon: %s \"%s\"",
                this.getWeaponType(weapon).getDescription(), this.getWeaponName(weapon));
    }

    private String getEnemyString(@NotNull Enemy enemy) {
        return String.format("Enemy \"%s\": HP %d, Defense %d, Weight %d, Damage %d",
                this.getCharacterName(enemy), this.getCharacterHealthPoints(enemy),
                this.getCharacterDefense(enemy), this.getEnemyWeight(enemy),
                this.getEnemyDamage(enemy));
    }

    private String getPCString(IPlayerCharacter character) {
        return String.format("%s: \"%s\": HP %d, Defense %d (%s)",
                this.getPlayableCharacterClass(character).getDescription(),
                this.getCharacterName(character), this.getCharacterHealthPoints(character),
                this.getCharacterDefense(character),
                this.getEquippedString(this.getPlayableCharacterEquippedWeapon(character)));
    }

    private String getWeaponString(IWeapon weapon) {
        return String.format("%s: \"%s\": Weight %d, Damage %d (%s)",
                this.getWeaponType(weapon).getDescription(), this.getWeaponName(weapon),
                this.getWeaponWeight(weapon), this.getWeaponDamage(weapon),
                this.getHolderString(this.getWeaponHolder(weapon)));
    }

    /**
     * Returns the string that describes the character in the argument
     */
    public String getCharacterString(@NotNull ICharacter character) {
        if (character.getCharacterDomain() == CharacterDomain.PLAYABLE) {
            return this.getPCString((IPlayerCharacter) character);
        } else {
            return this.getEnemyString((Enemy) character);
        }
    }

    /**
     * Returns the string that describes the current turn character
     */
    public String getCurrentTurnCharacterString() {
        ICharacter currentCharacter = this.getCurrentTurnCharacter();
        if (currentCharacter == null) {
            return "Waiting for characters recovering... (no active turn)";
        } else {
            return this.getCharacterString(currentCharacter);
        }
    }

    /**
     * Returns a string list describing the playable characters
     */
    public LinkedList<String> getAssignedCharacterString() {
        LinkedList<String> assignedCharacterString = new LinkedList<>();
        for (IPlayerCharacter character : this.getPlayerAssignedCharacters()) {
            assignedCharacterString.add(this.getPCString(character));
        }
        return assignedCharacterString;
    }

    /**
     * Returns a string list describing the assigned enemies
     */
    public LinkedList<String> getEnemiesString() {
        LinkedList<String> assignedEnemiesString = new LinkedList<>();
        for (Enemy enemy : this.getEnemiesAssigned()) {
            assignedEnemiesString.add(this.getEnemyString(enemy));
        }
        return assignedEnemiesString;
    }

    /**
     * Returns a string list describing the inventory
     */
    public LinkedList<String> getInventoryString() {
        LinkedList<String> inventoryString = new LinkedList<>();
        for (IWeapon weapon : this.getInventory()) {
            inventoryString.add(this.getWeaponString(weapon));
        }
        return inventoryString;
    }
}
