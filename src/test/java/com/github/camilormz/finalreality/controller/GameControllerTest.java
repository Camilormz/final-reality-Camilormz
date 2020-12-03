package com.github.camilormz.finalreality.controller;

import com.github.camilormz.finalreality.model.character.CharacterDomain;
import com.github.camilormz.finalreality.model.character.Enemy;
import com.github.camilormz.finalreality.model.character.ICharacter;
import com.github.camilormz.finalreality.model.character.player.CharacterClass;
import com.github.camilormz.finalreality.model.character.player.IPlayerCharacter;
import com.github.camilormz.finalreality.model.character.player.characterclass.*;
import com.github.camilormz.finalreality.model.weapon.IWeapon;
import com.github.camilormz.finalreality.model.weapon.WeaponType;
import com.github.camilormz.finalreality.model.weapon.types.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class that holds everything related to the controller
 *
 * @author Camilo Ramírez Canales
 */
public class GameControllerTest {

    private GameController controller;
    private BlockingQueue<ICharacter> turns;

    // Constant Strings for character names
    private final String BLACK_MAGE_NAME = "Nix";
    private final String ENGINEER_NAME = "Tesla";
    private final String KNIGHT_NAME = "Steel";
    private final String THIEF_NAME = "Bonnie";
    private final String WHITE_MAGE_NAME = "Lux";

    private final String AXE_NAME = "Wood Slayer";
    private final String BOW_NAME = "The Trebuchet";
    private final String KNIFE_NAME = "Stealthy";
    private final String STAFF_NAME = "Shadowmaker";
    private final String SWORD_NAME = "Infidel Redentor";

    private final String ENEMY_NAME = "Kronos";
    private final String ENEMY_NAME_2 = "Goblin";
    private final String ENEMY_NAME_3 = "Venom";

    // Characters generated directly by model constructors
    private BlackMage modelBlackMage;
    private Engineer modelEngineer;
    private Knight modelKnight;
    private Thief modelThief;
    private WhiteMage modelWhiteMage;

    private Enemy modelEnemy;

    private Axe modelAxe;
    private Bow modelBow;
    private Knife modelKnife;
    private Staff modelStaff;
    private Sword modelSword;

    // Characters generated by the controller
    private BlackMage controllerBlackMage;
    private Engineer controllerEngineer;
    private Knight controllerKnight;
    private Thief controllerThief;
    private WhiteMage controllerWhiteMage;

    private Enemy controllerEnemy;
    private Enemy controllerEnemy2;
    private Enemy controllerEnemy3;

    private Axe controllerAxe;
    private Bow controllerBow;
    private Knife controllerKnife;
    private Staff controllerStaff;
    private Sword controllerSword;

    // Default values for test characters
    private final int DEFAULT_HEALTH_POINTS = 100;
    private final int DEFAULT_DEFENSE = 2;
    private final int DEFAULT_ENEMY_WEIGHT = 10;
    private final int DEFAULT_WEAPON_WEIGHT = 10;
    private final int DEFAULT_ENEMY_DAMAGE = 10;
    private final int DEFAULT_WEAPON_DAMAGE = 10;
    private final int DEFAULT_MAGIC_DAMAGE = 10;

    /**
     * Initializes every variable necessary to do the tests, e.g., characters, weapons,
     * the controller, the turns queue among others
     */
    @BeforeEach
    void setUp() {
        controller = new GameController();
        turns = new LinkedBlockingQueue<>();

        modelBlackMage = new BlackMage(BLACK_MAGE_NAME, DEFAULT_HEALTH_POINTS,
                                       DEFAULT_DEFENSE, turns);
        modelEngineer = new Engineer(ENGINEER_NAME, DEFAULT_HEALTH_POINTS, DEFAULT_DEFENSE, turns);
        modelKnight = new Knight(KNIGHT_NAME, DEFAULT_HEALTH_POINTS, DEFAULT_DEFENSE, turns);
        modelThief = new Thief(THIEF_NAME, DEFAULT_HEALTH_POINTS, DEFAULT_DEFENSE, turns);
        modelWhiteMage = new WhiteMage(WHITE_MAGE_NAME, DEFAULT_HEALTH_POINTS,
                                       DEFAULT_DEFENSE, turns);

        modelEnemy = new Enemy(ENEMY_NAME, DEFAULT_ENEMY_WEIGHT, DEFAULT_HEALTH_POINTS,
                               DEFAULT_DEFENSE, DEFAULT_ENEMY_DAMAGE, turns);

        modelAxe = new Axe(AXE_NAME, DEFAULT_WEAPON_DAMAGE, DEFAULT_WEAPON_WEIGHT);
        modelBow = new Bow(BOW_NAME, DEFAULT_WEAPON_DAMAGE, DEFAULT_ENEMY_WEIGHT);
        modelKnife = new Knife(KNIFE_NAME, DEFAULT_WEAPON_DAMAGE, DEFAULT_WEAPON_WEIGHT);
        modelStaff = new Staff(STAFF_NAME, DEFAULT_WEAPON_DAMAGE,
                               DEFAULT_WEAPON_WEIGHT, DEFAULT_MAGIC_DAMAGE);
        modelSword = new Sword(SWORD_NAME, DEFAULT_WEAPON_DAMAGE, DEFAULT_WEAPON_WEIGHT);

        controllerBlackMage = controller.createBlackMage(BLACK_MAGE_NAME,
                                                         DEFAULT_HEALTH_POINTS, DEFAULT_DEFENSE);
        controllerEngineer = controller.createEngineer(ENGINEER_NAME,
                                                       DEFAULT_HEALTH_POINTS, DEFAULT_DEFENSE);
        controllerKnight = controller.createKnight(KNIGHT_NAME,
                                                   DEFAULT_HEALTH_POINTS, DEFAULT_DEFENSE);
        controllerThief = controller.createThief(THIEF_NAME,
                                                 DEFAULT_HEALTH_POINTS, DEFAULT_DEFENSE);
        controllerWhiteMage = controller.createWhiteMage(WHITE_MAGE_NAME,
                                                         DEFAULT_HEALTH_POINTS, DEFAULT_DEFENSE);

        controllerEnemy = controller.createEnemy(ENEMY_NAME, DEFAULT_ENEMY_WEIGHT,
                                                 DEFAULT_HEALTH_POINTS, DEFAULT_DEFENSE,
                                                 DEFAULT_ENEMY_DAMAGE);
        controllerEnemy2 = controller.createEnemy(ENEMY_NAME_2, DEFAULT_ENEMY_WEIGHT,
                                                  DEFAULT_HEALTH_POINTS, DEFAULT_DEFENSE,
                                                  DEFAULT_ENEMY_DAMAGE);
        controllerEnemy3 = controller.createEnemy(ENEMY_NAME_3, DEFAULT_ENEMY_WEIGHT,
                                                  DEFAULT_HEALTH_POINTS, DEFAULT_DEFENSE,
                                                  DEFAULT_ENEMY_DAMAGE);

        controllerAxe = controller.createAxe(AXE_NAME,
                                             DEFAULT_WEAPON_DAMAGE, DEFAULT_WEAPON_WEIGHT);
        controllerBow = controller.createBow(BOW_NAME,
                                             DEFAULT_WEAPON_DAMAGE, DEFAULT_WEAPON_WEIGHT);
        controllerKnife = controller.createKnife(KNIFE_NAME,
                                               DEFAULT_WEAPON_DAMAGE, DEFAULT_WEAPON_WEIGHT);
        controllerStaff = controller.createStaff(STAFF_NAME, DEFAULT_WEAPON_DAMAGE,
                                                 DEFAULT_WEAPON_WEIGHT, DEFAULT_MAGIC_DAMAGE);
        controllerSword = controller.createSword(SWORD_NAME,
                                                 DEFAULT_WEAPON_DAMAGE, DEFAULT_WEAPON_WEIGHT);
    }

    /**
     * Tests that the controller can create the model elements (playable characters, enemies and
     * weapons) as exactly as their respective constructor does
     */
    @Test
    void controllerCreationTest() {
        assertEquals(controllerBlackMage, modelBlackMage);
        assertEquals(controllerEngineer, modelEngineer);
        assertEquals(controllerKnight, modelKnight);
        assertEquals(controllerThief, modelThief);
        assertEquals(controllerWhiteMage, modelWhiteMage);

        assertEquals(controllerEnemy, modelEnemy);

        assertEquals(controllerAxe, modelAxe);
        assertEquals(controllerBow, modelBow);
        assertEquals(controllerKnife, modelKnife);
        assertEquals(controllerStaff, modelStaff);
        assertEquals(controllerSword, modelSword);
    }

    /**
     * Tests that the controller getters works as expected
     */
    @Test
    void attributeGetterTest() {
        assertEquals(controller.getCharacterName(controllerWhiteMage), WHITE_MAGE_NAME);
        assertEquals(controller.getCharacterHealthPoints(controllerWhiteMage),
                     DEFAULT_HEALTH_POINTS);
        assertEquals(controller.getCharacterDefense(controllerWhiteMage), DEFAULT_DEFENSE);
        assertEquals(controller.getCharacterDomain(controllerWhiteMage), CharacterDomain.PLAYABLE);

        assertEquals(controller.getPlayableCharacterClass(controllerWhiteMage),
                     CharacterClass.WHITE_MAGE);
        assertNull(controller.getPlayableCharacterEquippedWeapon(controllerWhiteMage));

        assertEquals(controller.getEnemyWeight(controllerEnemy), DEFAULT_ENEMY_WEIGHT);
        assertEquals(controller.getEnemyDamage(controllerEnemy), DEFAULT_ENEMY_DAMAGE);

        assertEquals(controller.getWeaponName(controllerKnife), KNIFE_NAME);
        assertEquals(controller.getWeaponDamage(controllerKnife), DEFAULT_WEAPON_DAMAGE);
        assertEquals(controller.getWeaponWeight(controllerKnife), DEFAULT_WEAPON_WEIGHT);
        assertEquals(controller.getWeaponType(controllerKnife), WeaponType.KNIFE);
        assertNull(controller.getWeaponHolder(controllerKnife));

        assertEquals(controller.getMagicDamage(controllerStaff), DEFAULT_MAGIC_DAMAGE);
    }

    /**
     * Test for playable character assignation to the player to play
     */
    @Test
    void playerAssignationTest() {
        Set<IPlayerCharacter> playerAssignedCharacters =
                controller.getPlayerAssignedCharacters();
        assertEquals(playerAssignedCharacters.size(), 0);
        controller.assignToPlayer(controllerKnight);
        assertEquals(playerAssignedCharacters.size(), 1);
        controller.assignToPlayer(controllerBlackMage);
        controller.assignToPlayer(modelBlackMage);
        assertEquals(playerAssignedCharacters.size(), 2);
        assertTrue(playerAssignedCharacters.contains(controllerKnight));
        assertTrue(playerAssignedCharacters.contains(controllerBlackMage));
        assertFalse(playerAssignedCharacters.contains(controllerEngineer));
        controller.removeFromPlayer(controllerKnight);
        controller.removeFromPlayer(controllerEngineer);
        assertTrue(playerAssignedCharacters.contains(controllerBlackMage));
        assertFalse(playerAssignedCharacters.contains(controllerKnight));
        assertFalse(playerAssignedCharacters.contains(controllerEngineer));
        assertEquals(playerAssignedCharacters.size(), 1);
    }

    /**
     * Test for enemies assignation to the CPU (enemy player)
     */
    @Test
    void enemyAssignationTest() {
        Set<Enemy> cpuEnemiesAssigned = controller.getEnemiesAssigned();
        assertEquals(cpuEnemiesAssigned.size(), 0);
        controller.assignEnemy(controllerEnemy);
        assertEquals(cpuEnemiesAssigned.size(), 1);
        controller.assignEnemy(controllerEnemy2);
        controller.assignEnemy(modelEnemy);
        assertEquals(cpuEnemiesAssigned.size(), 2);
        assertTrue(cpuEnemiesAssigned.contains(controllerEnemy));
        assertTrue(cpuEnemiesAssigned.contains(controllerEnemy2));
        assertFalse(cpuEnemiesAssigned.contains(controllerEnemy3));
        controller.removeAssignedEnemy(controllerEnemy);
        controller.removeAssignedEnemy(controllerEnemy3);
        assertTrue(cpuEnemiesAssigned.contains(controllerEnemy2));
        assertFalse(cpuEnemiesAssigned.contains(controllerEnemy));
        assertFalse(cpuEnemiesAssigned.contains(controllerEnemy3));
        assertEquals(cpuEnemiesAssigned.size(), 1);
    }

    /**
     * Test for inventory assignation (weapons)
     */
    @Test void inventoryTest() {
        Set<IWeapon> inventory = controller.getInventory();
        assertEquals(inventory.size(), 0);
        controller.assignToInventory(controllerSword);
        assertEquals(inventory.size(), 1);
        controller.assignToInventory(controllerStaff);
        controller.assignToInventory(modelSword);
        assertEquals(inventory.size(), 2);
        assertTrue(inventory.contains(controllerSword));
        assertTrue(inventory.contains(controllerStaff));
        assertFalse(inventory.contains(controllerAxe));
        controller.removeFromInventory(controllerSword);
        controller.removeFromInventory(controllerAxe);
        assertTrue(inventory.contains(controllerStaff));
        assertFalse(inventory.contains(controllerSword));
        assertFalse(inventory.contains(controllerAxe));
        assertEquals(inventory.size(), 1);
    }
}
