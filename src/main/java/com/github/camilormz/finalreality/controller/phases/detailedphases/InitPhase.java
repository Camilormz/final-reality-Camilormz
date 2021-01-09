package com.github.camilormz.finalreality.controller.phases.detailedphases;

import com.github.camilormz.finalreality.controller.phases.Phase;
import com.github.camilormz.finalreality.model.character.Enemy;
import com.github.camilormz.finalreality.model.character.player.IPlayerCharacter;
import com.github.camilormz.finalreality.model.weapon.IWeapon;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Class that represents the state at the initial phase of the game
 *
 * @author Camilo Ramírez Canales.
 */
public class InitPhase extends Phase {

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
    private final String ENEMY_NAME_4 = "X";
    private final String ENEMY_NAME_5 = "Ultra Lord";

    // Default values for characters and weapons
    private final int DEFAULT_HEALTH_POINTS = 100;
    private final int DEFAULT_DEFENSE = 2;
    private final int DEFAULT_ENEMY_WEIGHT = 10;
    private final int DEFAULT_WEAPON_WEIGHT = 10;
    private final int DEFAULT_ENEMY_DAMAGE = 10;
    private final int DEFAULT_WEAPON_DAMAGE = 10;
    private final int DEFAULT_MAGIC_DAMAGE = 10;

    // Character, enemies and weapon list ready for assignment
    private LinkedList<Enemy> enemyList = new LinkedList<>();
    private LinkedList<IPlayerCharacter> pcList = new LinkedList<>();
    private LinkedList<IWeapon> weaponList = new LinkedList<>();

    private LinkedList<String> enemyNames = new LinkedList<>(Arrays.asList(
            ENEMY_NAME, ENEMY_NAME_2, ENEMY_NAME_3, ENEMY_NAME_4, ENEMY_NAME_5));

    @Override
    public void initController() {
        this.listsSetUp();
        this.initEnemies();
        this.initPlayer();
        this.initInventory();
        this.changePhase(new PickingPhase());
    }

    /**
     * Does the initial setup of the list of weapons, playable characters and enemies
     */
    private void listsSetUp() {
        for (String enemyName : enemyNames) {
            enemyList.add(controller.createEnemy(enemyName, DEFAULT_ENEMY_WEIGHT,
                    DEFAULT_HEALTH_POINTS, DEFAULT_DEFENSE,
                    DEFAULT_ENEMY_DAMAGE));
        }
        pcList.add(controller.createBlackMage(BLACK_MAGE_NAME, DEFAULT_HEALTH_POINTS,
                DEFAULT_DEFENSE));
        pcList.add(controller.createEngineer(ENGINEER_NAME, DEFAULT_HEALTH_POINTS,
                DEFAULT_DEFENSE));
        pcList.add(controller.createKnight(KNIGHT_NAME, DEFAULT_HEALTH_POINTS, DEFAULT_DEFENSE));
        pcList.add(controller.createThief(THIEF_NAME, DEFAULT_HEALTH_POINTS, DEFAULT_DEFENSE));
        pcList.add(controller.createWhiteMage(WHITE_MAGE_NAME, DEFAULT_HEALTH_POINTS,
                DEFAULT_DEFENSE));
        weaponList.add(controller.createKnife(KNIFE_NAME, DEFAULT_WEAPON_DAMAGE,
                DEFAULT_WEAPON_WEIGHT));
        weaponList.add(controller.createAxe(AXE_NAME, DEFAULT_WEAPON_DAMAGE,
                DEFAULT_WEAPON_WEIGHT));
        weaponList.add(controller.createSword(SWORD_NAME, DEFAULT_WEAPON_DAMAGE,
                DEFAULT_WEAPON_WEIGHT));
        weaponList.add(controller.createBow(BOW_NAME, DEFAULT_WEAPON_DAMAGE,
                DEFAULT_WEAPON_WEIGHT));
        weaponList.add(controller.createStaff(STAFF_NAME, DEFAULT_WEAPON_DAMAGE,
                DEFAULT_WEAPON_WEIGHT, DEFAULT_MAGIC_DAMAGE));
    }

    /**
     * Initializes the assignation of enemies
     */
    private void initEnemies() {
        int maxEnemies = controller.getMaxEnemies();
        int amountEnemies = ThreadLocalRandom.current().nextInt(1, maxEnemies+1);
        for (int i=0; i<amountEnemies; i++) {
            Enemy enemy = enemyList.get(i);
            controller.assignEnemy(enemy);
            controller.waitEnqueueForTurn(enemy);

        }
    }

    /**
     * Initializes the assignation of playable characters
     */
    private void initPlayer() {
        int amountPlayerCharacters = controller.getAmountPlayableCharacters();
        for (int i=0; i<amountPlayerCharacters; i++) {
            IPlayerCharacter character = pcList.get(i);
            controller.assignToPlayer(character);
            controller.waitEnqueueForTurn(character);
        }
    }

    /**
     * Initializes the inventory
     */
    private void initInventory() {
        for (IWeapon weapon : weaponList) {
            controller.assignToInventory(weapon);
        }
    }

    @Override
    public boolean isAtInitPhase() {
        return true;
    }
}
