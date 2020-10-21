package com.github.camilormz.finalreality.model.character.player.characterclass;

import com.github.camilormz.finalreality.model.character.CharacterDomain;
import com.github.camilormz.finalreality.model.character.player.AbstractPlayerCharacterTest;
import com.github.camilormz.finalreality.model.weapon.types.Staff;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class that holds everything related to white mages
 *
 * @author Camilo Ramírez Canales.
 */
public class WhiteMageTest extends AbstractPlayerCharacterTest {

    private final String WHITE_MAGE_NAME = "Lux";
    private final String WHITE_MAGE_ALT_NAME = "Abel";

    private WhiteMage whiteMage;
    private WhiteMage whiteMageAltName;
    private WhiteMage unarmedWhiteMage;
    private BlackMage notWhiteMageAltClass;

    private WhiteMage combatWhiteMage;
    private WhiteMage anotherCombatWhiteMage;
    private Staff combatStaff;
    private Staff anotherStaff;

    @Override
    @BeforeEach
    protected void subClassSetUp() {
        whiteMage = new WhiteMage(WHITE_MAGE_NAME, 100, 2, turns);
        whiteMageAltName = new WhiteMage(WHITE_MAGE_ALT_NAME, 100, 2, turns);
        unarmedWhiteMage = new WhiteMage(WHITE_MAGE_NAME, 100, 2, turns);
        notWhiteMageAltClass = new BlackMage(WHITE_MAGE_NAME, 100, 2, turns);

        combatWhiteMage = new WhiteMage(WHITE_MAGE_NAME, 100,
                                       2, turns);
        anotherCombatWhiteMage = new WhiteMage(WHITE_MAGE_ALT_NAME, 100,
                                              2,  turns);
        combatStaff = new Staff(STAFF_NAME, 10, 10, 10);
        anotherStaff = new Staff(STAFF_NAME, 10, 10, 10);
        combatWhiteMage.tryToEquip(combatStaff);
        anotherCombatWhiteMage.tryToEquip(anotherStaff);
    }
    @Override
    @Test
    protected void subClassConstructorTest() {
        this.constructionTest(whiteMage,
                              new WhiteMage(WHITE_MAGE_NAME, 100, 2, turns),
                              whiteMageAltName, notWhiteMageAltClass);
    }
    @Override
    @Test
    protected void subClassWeaponTest() {
        this.weaponEquipmentTest(whiteMage, unarmedWhiteMage,
                                 testStaff, anotherStaff, combatStaff, testKnife);
    }
    @Override
    @Test
    protected void subClassWaitTurnTest() {
        this.waitTurnTest(whiteMage, 0, waitTurnTestErrorMargin);
        whiteMage.tryToEquip(testStaff);
        long expectedTime = testStaff.getWeight()/10;
        this.waitTurnTest(whiteMage, expectedTime, waitTurnTestErrorMargin);
    }
    @Override
    @Test
    protected void subClassCharacterDomainTest() {
        this.getCharacterDomainTest(whiteMage, CharacterDomain.PLAYABLE);
    }
    @Override
    @Test
    protected void subClassCombatTest() {
        this.subClassCombatTestExecution(combatWhiteMage, anotherCombatWhiteMage);
    }
    @Override
    @Test
    protected void subClassWeaponDroppingTest() {
        this.deathWeaponDropTest(whiteMage, testStaff);
    }
}
