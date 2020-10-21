package com.github.camilormz.finalreality.model.character;

import com.github.camilormz.finalreality.model.character.player.characterclass.Knight;
import com.github.camilormz.finalreality.model.weapon.types.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Abstract class that holds the tests available for any character of the game
 *
 * @author Camilo Ramírez Canales
 */
public abstract class AbstractCharacterTest {

    protected final String ENEMY_TEST_NAME = "Kronos";
    protected final String PLAYABLE_TEST_NAME = "Steel";
    protected final String AXE_NAME = "Wood Slayer";
    protected final String BOW_NAME = "The Trebuchet";
    protected final String KNIFE_NAME = "Stealthy";
    protected final String STAFF_NAME = "Shadowmaker";
    protected final String SWORD_NAME = "Infidel Redentor";

    protected BlockingQueue<ICharacter> turns;
    protected Axe testAxe;
    protected Bow testBow;
    protected Knife testKnife;
    protected Staff testStaff;
    protected Sword testSword;
    protected Enemy testEnemy;
    protected Knight testPlayable;

    protected Axe anotherTestAxe;
    protected Bow anotherTestBow;
    protected Knife anotherTestKnife;
    protected Staff anotherTestStaff;
    protected Sword anotherTestSword;

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
        anotherTestAxe = new Axe(AXE_NAME, 10, 10);
        anotherTestBow = new Bow(BOW_NAME, 10, 10);
        anotherTestKnife = new Knife(KNIFE_NAME, 10, 10);
        anotherTestStaff = new Staff(STAFF_NAME, 10, 10, 10);
        anotherTestSword = new Sword(SWORD_NAME, 10, 10);
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
     * Test for combat dynamics
     * @param characterDamage corresponds to character damage and must be less than
     *                        strongAdversaryHP and greater to strongAdversaryDefense
     * @param strongAdversaryDamage corresponds to strongAdversary damage
     * @param weakAdversaryDamage corresponds to weakAdversary damage and must be less than
     *                            characterDefense
     */
    protected void combatTest(AbstractCharacter character,
                              AbstractCharacter sameDomainCharacter,
                              AbstractCharacter strongAdversary,
                              AbstractCharacter weakAdversary,
                              int characterHP, int characterDamage, int characterDefense,
                              int strongAdversaryDamage, int weakAdversaryDamage,
                              int strongAdversaryHP, int strongAdversaryDefense) {
        // Assert the parameters conditions
        assert weakAdversaryDamage < characterDefense;
        assert strongAdversaryDefense < characterDamage && characterDamage < strongAdversaryHP;
        // Checks that the character's and adversary's HP starts as desired
        assertEquals(character.getHealthPoints(), characterHP);
        assertEquals(strongAdversaryHP.getHealthPoints(), strongAdversaryHP);
        // Tests that an same domain character attack trial has no effect on character
        sameDomainCharacter.attack(character);
        assertEquals(character.getHealthPoints(), characterHP);
        // Tests that the weak adversary has not effect on character HP
        weakAdversary.attack(character);
        assertEquals(character.getHealthPoints(), characterHP);
        // Tests that the character can actually attack its adversary
        character.attack(strongAdversary);
        strongAdversaryHP -= characterDamage - strongAdversaryDefense;
        assertEquals(strongAdversaryHP.getHealthPoints(), strongAdversaryHP);
        // Tests loop in where the character receive attacks from the strong adversary until it dies
        while (characterHP > 0) {
            assertEquals(character.getHealthPoints(), characterHP);
            assertTrue(character.isAlive());
            strongAdversary.attack(character);
            characterHP -= strongAdversaryDamage - characterDefense;
        }
        // Tests if the character is actually dead
        assertEquals(character.getHealthPoints(), 0);
        assertFalse(character.isAlive());
        // Tests that a dead character has no effect
        character.attack(strongAdversary);
        assertEquals(strongAdversary.getHealthPoints(), strongAdversaryHP);
    }
    /**
     * Test for character domain
     */
    protected void getCharacterDomainTest(AbstractCharacter character,
                                          CharacterDomain expectedDomain) {
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
    /**
     * Abstract method for the subclasses to execute the combat test
     */
    @Test
    protected abstract void subClassCombatTest();
}
