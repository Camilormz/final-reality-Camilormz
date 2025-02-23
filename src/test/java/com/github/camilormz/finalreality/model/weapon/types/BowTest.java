package com.github.camilormz.finalreality.model.weapon.types;

import com.github.camilormz.finalreality.model.character.player.characterclass.Engineer;
import com.github.camilormz.finalreality.model.character.player.characterclass.WhiteMage;
import com.github.camilormz.finalreality.model.weapon.AbstractWeaponTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class that holds everything related to bows
 *
 * @author Camilo Ramírez Canales
 */
public class BowTest extends AbstractWeaponTest {

    private final String BOW_NAME = "The Trebuchet";
    private final String BOW_ALT_NAME = "Long Bow";

    private Bow bow;
    private Bow bowAltName;
    private Bow bowAltDamage;
    private Bow bowAltWeight;
    private Knife notBowAltType;

    private Engineer validHolder;
    private Engineer secondValidHolder;
    private WhiteMage unValidHolder;

    @Override
    @BeforeEach
    protected void subClassSetUp() {
        bow = new Bow(BOW_NAME, 10, 10);
        bowAltName = new Bow(BOW_ALT_NAME, 10, 10);
        bowAltDamage = new Bow(BOW_NAME, 20, 10);
        bowAltWeight = new Bow(BOW_NAME, 10, 20);
        notBowAltType = new Knife(BOW_NAME, 10, 10);

        validHolder = new Engineer(CHARACTER_TEST_NAME, 100, 2, turns);
        secondValidHolder = new Engineer(CHARACTER_TEST_NAME, 100, 2, turns);
        unValidHolder = new WhiteMage(CHARACTER_TEST_NAME, 100, 2, turns);
    }
    @Override
    @Test
    protected void subClassConstructorTest() {
        this.constructionTest(bow, new Bow(BOW_NAME, 10, 10), bowAltName,
                              bowAltDamage, bowAltWeight, notBowAltType);
    }
    @Override
    @Test
    protected void subClassEquipmentTest() {
        this.equipmentTest(bow, validHolder, secondValidHolder, unValidHolder);
    }
}
