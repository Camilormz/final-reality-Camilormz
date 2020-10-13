package com.github.camilormz.finalreality.model.character.player.characterclass;

import com.github.camilormz.finalreality.model.character.CharacterDomain;
import com.github.camilormz.finalreality.model.character.player.AbstractPlayerCharacterTest;
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

    @Override
    @BeforeEach
    protected void subClassSetUp() {
        whiteMage = new WhiteMage(WHITE_MAGE_NAME, turns);
        whiteMageAltName = new WhiteMage(WHITE_MAGE_ALT_NAME, turns);
        unarmedWhiteMage = new WhiteMage(WHITE_MAGE_NAME, turns);
        notWhiteMageAltClass = new BlackMage(WHITE_MAGE_NAME, turns);
    }
    @Override
    @Test
    protected void subClassConstructorTest() {
        this.constructionTest(whiteMage, new WhiteMage(WHITE_MAGE_NAME, turns),
                              whiteMageAltName, notWhiteMageAltClass);
    }
    @Override
    @Test
    protected void subClassWeaponTest() {
        this.weaponEquipmentTest(whiteMage, unarmedWhiteMage, testStaff, testKnife);
    }
    @Override
    @Test
    protected void subClassWaitTurnTest() {
        this.waitTurnTest(whiteMage, 0, waitTurnTestErrorMargin);
        whiteMage.equip(testStaff);
        long expectedTime = testStaff.getWeight()/10;
        this.waitTurnTest(whiteMage, expectedTime, waitTurnTestErrorMargin);
    }
    @Override
    @Test
    protected void subClassCharacterDomainTest() {
        this.getCharacterDomainTest(whiteMage, CharacterDomain.PLAYABLE);
    }
}
