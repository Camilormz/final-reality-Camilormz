package com.github.camilormz.finalreality.model.character.characterclass;

import com.github.camilormz.finalreality.model.character.AbstractPlayerCharacterTest;
import com.github.camilormz.finalreality.model.character.player.characterclass.BlackMage;
import com.github.camilormz.finalreality.model.character.player.characterclass.WhiteMage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class that holds everything related to white mages
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
}
