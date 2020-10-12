package com.github.camilormz.finalreality.model.character;

import com.github.camilormz.finalreality.model.character.player.AbstractPlayerCharacter;
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
    private AbstractPlayerCharacter notEnemy;

    /**
     * SetUp for the tests
     */
    @BeforeEach
    void setUpEnemies() {
        enemy = new Enemy(ENEMY_NAME, 10, turns);
        enemySame = new Enemy(ENEMY_NAME, 10, turns);
        enemyAltName = new Enemy(ENEMY_ALT_NAME, 10, turns);
        enemyAltWeight = new Enemy(ENEMY_NAME, 20, turns);
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
}
