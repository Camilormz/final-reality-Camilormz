package com.github.camilormz.finalreality.model.character;

import com.github.camilormz.finalreality.model.character.player.AbstractPlayerCharacter;
import com.github.camilormz.finalreality.model.character.player.characterclass.Knight;
import com.github.camilormz.finalreality.model.weapon.types.Sword;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Test class that holds everything related to enemies
 *
 * @author Camilo Ramírez Canales
 */
public class EnemyTest extends AbstractCharacterTest {

    private final String ENEMY_NAME = "Kronos";
    private final String ENEMY_ALT_NAME = "Goblin";

    private Enemy enemy;
    private Enemy enemySame;
    private Enemy enemyAltName;
    private Enemy enemyAltWeight;
    private Enemy enemyAltDamage;
    private Enemy enemyAltDefense;

    private Knight strongAdversary;
    private Knight weakAdversary;

    private Sword strongWeapon;
    private Sword weakWeapon;

    /**
     * SetUp for the tests
     */
    @BeforeEach
    void setUpEnemies() {
        enemy = new Enemy(ENEMY_NAME, 10,100,
                         2, 10, turns);
        enemySame = new Enemy(ENEMY_NAME, 10, 50,
                             2, 10, turns);
        enemyAltName = new Enemy(ENEMY_ALT_NAME, 10, 100,
                                2, 10, turns);
        enemyAltWeight = new Enemy(ENEMY_NAME, 20, 100,
                                  2, 10, turns);
        enemyAltDamage = new Enemy(ENEMY_NAME, 10, 100,
                                  2, 20, turns);
        enemyAltDefense = new Enemy(ENEMY_NAME, 10, 100,
                                   1, 10, turns);

        strongAdversary = new Knight(PLAYABLE_TEST_NAME, 100, 2, turns);
        weakAdversary = new Knight(PLAYABLE_TEST_NAME, 100, 2, turns);

        strongWeapon = new Sword(SWORD_NAME, 10, 10);
        weakWeapon = new Sword(SWORD_NAME, 1, 10);

        strongAdversary.tryToEquip(strongWeapon);
        weakAdversary.tryToEquip(weakWeapon);
    }

    /**
     * Enemy constructor, equals and hashCode test
     */
    @Test
    void constructionTest() {
        assertEquals(enemy, enemy);
        assertEquals(enemy, enemySame);
        assertEquals(enemy.hashCode(), enemySame.hashCode());
        assertNotEquals(enemy, enemyAltName);
        assertNotEquals(enemy, enemyAltWeight);
        assertNotEquals(enemy, enemyAltDamage);
        assertNotEquals(enemy, enemyAltDefense);
        assertNotEquals(enemy, testPlayable);
    }
    @Override
    @Test
    protected void subClassWaitTurnTest() {
        long expectedTime = enemy.getWeight()/10;
        this.waitTurnTest(enemy, expectedTime, waitTurnTestErrorMargin);
    }

    @Override
    @Test
    protected void subClassCharacterDomainTest() {
        this.getCharacterDomainTest(enemy, CharacterDomain.ENEMY);
    }
    @Override
    @Test
    protected void subClassCombatTest() {
        this.combatTest(enemy, enemySame, strongAdversary, weakAdversary,
                        100, 10, 2,
                        10, 1,
                        100, 2);
    }
}
