package com.github.camilormz.finalreality.model.character.characterclass;

import com.github.camilormz.finalreality.model.character.AbstractPlayerCharacterTest;
import com.github.camilormz.finalreality.model.character.player.characterclass.BlackMage;
import com.github.camilormz.finalreality.model.character.player.characterclass.WhiteMage;
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

    @Override
    @BeforeEach
    protected void subClassSetUp() {
        blackMage = new BlackMage(BLACK_MAGE_NAME, turns);
        blackMageAltName = new BlackMage(BLACK_MAGE_ALT_NAME, turns);
        unarmedBlackMage = new BlackMage(BLACK_MAGE_NAME, turns);
        notBlackMageAltClass = new WhiteMage(BLACK_MAGE_NAME, turns);

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
        this.weaponEquipmentTest(blackMage, unarmedBlackMage, testStaff, testBow);
    }
}
