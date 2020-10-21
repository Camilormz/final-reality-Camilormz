package com.github.camilormz.finalreality.model.weapon.types;

import com.github.camilormz.finalreality.model.character.player.characterclass.Knight;
import com.github.camilormz.finalreality.model.character.player.characterclass.WhiteMage;
import com.github.camilormz.finalreality.model.weapon.AbstractWeaponTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class that holds everything related to axes
 *
 * @author Camilo Ramírez Canales
 */
public class AxeTest extends AbstractWeaponTest {

    private final String AXE_NAME = "Wood Slayer";
    private final String AXE_ALT_NAME = "Wood Chopper";

    private Axe axe;
    private Axe axeAltName;
    private Axe axeAltDamage;
    private Axe axeAltWeight;
    private Sword notAxeAltType;

    private Knight validHolder;
    private Knight secondValidHolder;
    private WhiteMage unValidHolder;

    @Override
    @BeforeEach
    protected void subClassSetUp() {
        axe = new Axe(AXE_NAME, 10, 10);
        axeAltName = new Axe(AXE_ALT_NAME, 10, 10);
        axeAltDamage = new Axe(AXE_NAME, 20, 10);
        axeAltWeight = new Axe(AXE_NAME, 10, 20);
        notAxeAltType = new Sword(AXE_NAME, 10, 10);

        validHolder = new Knight(CHARACTER_TEST_NAME, 100, 2, turns);
        secondValidHolder = new Knight(CHARACTER_TEST_NAME, 100, 2, turns);
        unValidHolder = new WhiteMage(CHARACTER_TEST_NAME, 100, 2, turns);
    }
    @Override
    @Test
    protected void subClassConstructorTest() {
        this.constructionTest(axe, new Axe(AXE_NAME, 10, 10), axeAltName,
                              axeAltDamage, axeAltWeight, notAxeAltType);
    }
    @Override
    @Test
    protected void subClassEquipmentTest() {
        this.equipmentTest(axe, validHolder, secondValidHolder, unValidHolder);
    }
}
