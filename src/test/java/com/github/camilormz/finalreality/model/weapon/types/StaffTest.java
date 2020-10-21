package com.github.camilormz.finalreality.model.weapon.types;

import com.github.camilormz.finalreality.model.character.player.characterclass.Knight;
import com.github.camilormz.finalreality.model.character.player.characterclass.WhiteMage;
import com.github.camilormz.finalreality.model.weapon.AbstractWeaponTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class that holds everything related to staffs
 *
 * @author Camilo Ramírez Canales
 */
public class StaffTest extends AbstractWeaponTest {

    private final String STAFF_NAME = "Shadowmaker";
    private final String STAFF_ALT_NAME = "Oblivion";

    private Staff staff;
    private Staff staffAltName;
    private Staff staffAltDamage;
    private Staff staffAltWeight;
    private Staff staffAltMagicDamage;

    private WhiteMage validHolder;
    private WhiteMage secondValidHolder;
    private Knight unValidHolder;

    @Override
    @BeforeEach
    protected void subClassSetUp() {
        staff = new Staff(STAFF_NAME, 10, 10, 10);
        staffAltName = new Staff(STAFF_ALT_NAME, 10, 10, 10);
        staffAltDamage = new Staff(STAFF_NAME, 20, 10, 10);
        staffAltWeight = new Staff(STAFF_NAME, 10, 20, 10);
        staffAltMagicDamage = new Staff(STAFF_NAME, 10, 10, 20);

        validHolder = new WhiteMage(CHARACTER_TEST_NAME, 100, 2, turns);
        secondValidHolder = new WhiteMage(CHARACTER_TEST_NAME, 100, 2, turns);
        unValidHolder = new Knight(CHARACTER_TEST_NAME, 100, 2, turns);
    }
    @Override
    @Test
    protected void subClassConstructorTest() {
        this.magicalConstructionTest(staff,
                                     new Staff(STAFF_NAME, 10, 10, 10),
                                     staffAltName, staffAltDamage, staffAltWeight,
                                     staffAltMagicDamage);
    }
    @Override
    @Test
    protected void subClassEquipmentTest() {
        this.equipmentTest(staff, validHolder, secondValidHolder, unValidHolder);
    }
}
