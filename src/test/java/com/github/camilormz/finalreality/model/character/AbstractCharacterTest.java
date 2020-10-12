package com.github.camilormz.finalreality.model.character;

import com.github.camilormz.finalreality.model.character.player.characterclass.Knight;
import com.github.camilormz.finalreality.model.weapon.types.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Abstract class that holds the tests available for any character of the game
 *
 * @author Camilo Ramírez Canales
 */
public abstract class AbstractCharacterTest {

    protected final String ENEMY_TEST_NAME = "Kronos";
    protected final String PLAYABLE_TEST_NAME = "Steel";
    private final String AXE_NAME = "Wood Slayer";
    private final String BOW_NAME = "The Trebuchet";
    private final String KNIFE_NAME = "Stealthy";
    private final String STAFF_NAME = "Shadowmaker";
    private final String SWORD_NAME = "Infidel Redentor";

    protected BlockingQueue<ICharacter> turns;
    protected Axe testAxe;
    protected Bow testBow;
    protected Knife testKnife;
    protected Staff testStaff;
    protected Sword testSword;
    protected Enemy testEnemy;
    protected Knight testPlayable;

    protected final float waitTurnTestErrorMargin = 10;
    private final long epsilonWaitTurnTest = 50;  // milliseconds to wait at 0 expected time

    /**
     * Executes a setup for every test involving characters
     */
    @BeforeEach
    void setUp() {
        turns = new LinkedBlockingQueue<>();
        testEnemy = new Enemy(ENEMY_TEST_NAME, 10, turns);
        testPlayable = new Knight(PLAYABLE_TEST_NAME, turns);
        testAxe = new Axe(AXE_NAME, 10, 10);
        testBow = new Bow(BOW_NAME, 10, 10);
        testKnife = new Knife(KNIFE_NAME, 10, 10);
        testStaff = new Staff(STAFF_NAME, 10, 10, 10);
        testSword = new Sword(SWORD_NAME, 10, 10);
    }

    /**
     * Test for enqueuing time according to character turn-weight
     */
    protected void waitTurnTest(AbstractCharacter character, long expectedTime,
                                float percentageErrorMargin) {
        assertTrue(turns.isEmpty());
        long intervalStartWaitTime = (long)(1000*expectedTime*(100-percentageErrorMargin)/100.0);
        long intervalToEndWaitTime = (long)(1000*expectedTime*(2*percentageErrorMargin)/100.0);
        character.waitTurn();
        if (expectedTime == 0) {
            try {
                Thread.sleep(epsilonWaitTurnTest);
                assertEquals(1, turns.size());
                assertEquals(turns.take(), character);
                assertEquals(0, turns.size());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            try {
                Thread.sleep(intervalStartWaitTime);
                assertEquals(0, turns.size());
                Thread.sleep(intervalToEndWaitTime);
                assertEquals(1, turns.size());
                assertEquals(turns.take(), character);
                assertEquals(0, turns.size());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * Test for character domain
     */
    protected void getCharacterDomainTest(AbstractCharacter character, CharacterDomain expectedDomain) {
        assertEquals(expectedDomain, character.getCharacterDomain());
    }
    /**
     * Abstract method to execute tests for character domain
     */
    @Test
    protected abstract void subClassCharacterDomainTest();
    /**
     * Abstract method for the subclasses to execute wait turn test
     */
    @Test
    protected abstract void subClassWaitTurnTest();
}
