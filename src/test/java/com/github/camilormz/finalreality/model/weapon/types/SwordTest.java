package com.github.camilormz.finalreality.model.weapon.types;

import com.github.camilormz.finalreality.model.character.player.characterclass.Knight;
import com.github.camilormz.finalreality.model.character.player.characterclass.WhiteMage;
import com.github.camilormz.finalreality.model.weapon.AbstractWeaponTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class that holds everything related to swords
 *
 * @author Camilo Ramírez Canales
 */
public class SwordTest extends AbstractWeaponTest {

    private final String SWORD_NAME = "Infidel Redentor";
    private final String SWORD_ALT_NAME = "Sacred Steel";

    private Sword sword;
    private Sword swordAltName;
    private Sword swordAltDamage;
    private Sword swordAltWeight;
    private Axe notSwordAltType;

    private Knight validHolder;
    private Knight secondValidHolder;
    private WhiteMage unValidHolder;

    @Override
    @BeforeEach
    protected void subClassSetUp() {
        sword = new Sword(SWORD_NAME, 10, 10);
        swordAltName = new Sword(SWORD_ALT_NAME, 10, 10);
        swordAltDamage = new Sword(SWORD_NAME, 20, 10);
        swordAltWeight = new Sword(SWORD_NAME, 10, 20);
        notSwordAltType = new Axe(SWORD_NAME, 10, 10);

        validHolder = new Knight(CHARACTER_TEST_NAME, turns);
        secondValidHolder = new Knight(CHARACTER_TEST_NAME, turns);
        unValidHolder = new WhiteMage(CHARACTER_TEST_NAME, turns);
    }
    @Override
    @Test
    protected void subClassConstructorTest() {
        this.constructionTest(sword, new Sword(SWORD_NAME, 10, 10), swordAltName,
                              swordAltDamage, swordAltWeight, notSwordAltType);
    }
    @Override
    @Test
    protected void subClassEquipmentTest() {
        this.equipmentTest(sword, validHolder, secondValidHolder, unValidHolder);
    }
}
