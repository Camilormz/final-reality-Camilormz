package com.github.camilormz.finalreality.model.character.player.characterclass;

import com.github.camilormz.finalreality.model.character.CharacterDomain;
import com.github.camilormz.finalreality.model.character.player.AbstractPlayerCharacterTest;
import com.github.camilormz.finalreality.model.weapon.types.Sword;
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
    private Knight knightAltDefense;
    private Knight unarmedKnight;
    private Engineer notKnightAltClass;

    private Knight combatKnight;
    private Knight anotherCombatKnight;
    private Sword combatSword;
    private Sword anotherSword;

    @Override
    @BeforeEach
    protected void subClassSetUp() {
        knight = new Knight(KNIGHT_NAME, 100, 2, turns);
        knightAltName = new Knight(KNIGHT_ALT_NAME, 100, 2, turns);
        knightAltDefense = new Knight(KNIGHT_NAME, 100, 1, turns);
        unarmedKnight = new Knight(KNIGHT_NAME, 100, 2, turns);
        notKnightAltClass = new Engineer(KNIGHT_NAME, 100, 2, turns);

        combatKnight = new Knight(KNIGHT_NAME, 100, 2, turns);
        anotherCombatKnight = new Knight(KNIGHT_ALT_NAME, 100, 2, turns);
        combatSword = new Sword(SWORD_NAME, 10, 10);
        anotherSword = new Sword(SWORD_NAME, 10, 10);
        combatKnight.tryToEquip(combatSword);
        anotherCombatKnight.tryToEquip(anotherSword);
    }
    @Override
    @Test
    protected void subClassConstructorTest() {
        this.constructionTest(knight, new Knight(KNIGHT_NAME, 100, 2, turns),
                              knightAltName, knightAltDefense, notKnightAltClass);
    }
    @Override
    @Test
    protected void subClassWeaponTest() {
        this.weaponEquipmentTest(knight, unarmedKnight,
                                 testSword, anotherTestSword, combatSword, testBow);
    }
    @Override
    @Test
    protected void subClassWaitTurnTest() {
        this.waitTurnTest(knight, 0, waitTurnTestErrorMargin);
        knight.tryToEquip(testSword);
        long expectedTime = testSword.getWeight()/10;
        this.waitTurnTest(knight, expectedTime, waitTurnTestErrorMargin);
    }
    @Override
    @Test
    protected void subClassCharacterDomainTest() {
        this.getCharacterDomainTest(knight, CharacterDomain.PLAYABLE);
    }
    @Override
    @Test
    protected void subClassCombatTest() {
        this.subClassCombatTestExecution(combatKnight, anotherCombatKnight);
    }
    @Override
    @Test
    protected void subClassWeaponDroppingTest() {
        this.knockOutWeaponDropTest(knight, testSword);
    }
}
