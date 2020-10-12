package com.github.camilormz.finalreality.model.character.player.characterclass;

import com.github.camilormz.finalreality.model.character.CharacterDomain;
import com.github.camilormz.finalreality.model.character.player.AbstractPlayerCharacterTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class that holds everything related to knights
 *
 * @author Camilo Ramírez Canales.
 */
public class KnightTest extends AbstractPlayerCharacterTest {

    private final String KNIGHT_NAME = "Steel";
    private final String KNIGHT_ALT_NAME = "Iron";

    private Knight knight;
    private Knight knightAltName;
    private Knight unarmedKnight;
    private Engineer notKnightAltClass;

    @Override
    @BeforeEach
    protected void subClassSetUp() {
        knight = new Knight(KNIGHT_NAME, turns);
        knightAltName = new Knight(KNIGHT_ALT_NAME, turns);
        unarmedKnight = new Knight(KNIGHT_NAME, turns);
        notKnightAltClass = new Engineer(KNIGHT_NAME, turns);
    }
    @Override
    @Test
    protected void subClassConstructorTest() {
        this.constructionTest(knight, new Knight(KNIGHT_NAME, turns),
                              knightAltName, notKnightAltClass);
    }
    @Override
    @Test
    protected void subClassWeaponTest() {
        this.weaponEquipmentTest(knight, unarmedKnight, testSword, testBow);
    }
    @Override
    @Test
    protected void subClassWaitTurnTest() {
        this.waitTurnTest(knight, 0, waitTurnTestErrorMargin);
        knight.equip(testSword);
        long expectedTime = testSword.getWeight()/10;
        this.waitTurnTest(knight, expectedTime, waitTurnTestErrorMargin);
    }
    @Override
    @Test
    protected void subClassCharacterDomainTest() {
        this.getCharacterDomainTest(knight, CharacterDomain.PLAYABLE);
    }
}
