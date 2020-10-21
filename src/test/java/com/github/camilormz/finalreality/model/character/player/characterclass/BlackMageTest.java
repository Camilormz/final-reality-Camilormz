package com.github.camilormz.finalreality.model.character.player.characterclass;

import com.github.camilormz.finalreality.model.character.CharacterDomain;
import com.github.camilormz.finalreality.model.character.player.AbstractPlayerCharacterTest;
import com.github.camilormz.finalreality.model.weapon.types.Staff;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class that holds everything related to black mages
 *
 * @author Camilo Ramírez Canales
 */
public class BlackMageTest extends AbstractPlayerCharacterTest {

    private final String BLACK_MAGE_NAME = "Nix";
    private final String BLACK_MAGE_ALT_NAME = "Chaos";

    private BlackMage blackMage;
    private BlackMage blackMageAltName;
    private BlackMage unarmedBlackMage;
    private WhiteMage notBlackMageAltClass;

    private BlackMage combatBlackMage;
    private BlackMage anotherCombatBlackMage;
    private Staff combatStaff;
    private Staff anotherStaff;

    @Override
    @BeforeEach
    protected void subClassSetUp() {
        blackMage = new BlackMage(BLACK_MAGE_NAME, turns);
        blackMageAltName = new BlackMage(BLACK_MAGE_ALT_NAME, turns);
        unarmedBlackMage = new BlackMage(BLACK_MAGE_NAME, turns);
        notBlackMageAltClass = new WhiteMage(BLACK_MAGE_NAME, turns);

        combatBlackMage = new BlackMage(BLACK_MAGE_NAME, turns);
        anotherCombatBlackMage = new BlackMage(BLACK_MAGE_ALT_NAME, turns);
        combatStaff = new Staff(STAFF_NAME, 10, 10, 10);
        anotherStaff = new Staff(STAFF_NAME, 10, 10, 10);
        combatBlackMage.tryToEquip(combatStaff);
        anotherCombatBlackMage.tryToEquip(anotherStaff);
    }
    @Override
    @Test
    protected void subClassConstructorTest() {
        this.constructionTest(blackMage, new BlackMage(BLACK_MAGE_NAME, turns),
                              blackMageAltName, notBlackMageAltClass);
    }
    @Override
    @Test
    protected void subClassWeaponTest() {
        this.weaponEquipmentTest(blackMage, unarmedBlackMage,
                                 testStaff, anotherStaff, combatStaff, testBow);
    }
    @Override
    @Test
    protected void subClassWaitTurnTest() {
        this.waitTurnTest(blackMage, 0, waitTurnTestErrorMargin);
        blackMage.tryToEquip(testStaff);
        long expectedTime = testStaff.getWeight()/10;
        this.waitTurnTest(blackMage, expectedTime, waitTurnTestErrorMargin);
    }
    @Override
    @Test
    protected void subClassCharacterDomainTest() {
        this.getCharacterDomainTest(blackMage, CharacterDomain.PLAYABLE);
    }
    @Override
    @Test
    protected void subClassCombatTest() {
        this.subClassCombatTestExecution(combatBlackMage, anotherCombatBlackMage);
    }
    @Override
    @Test
    protected void subClassWeaponDroppingTest() {
        this.deathWeaponDropTest(blackMage, testStaff);
    }
}
