package com.github.camilormz.finalreality.model.weapon;

import com.github.camilormz.finalreality.model.character.AbstractCharacter;
import com.github.camilormz.finalreality.model.character.ICharacter;
import com.github.camilormz.finalreality.model.character.player.AbstractPlayerCharacter;
import com.github.camilormz.finalreality.model.character.player.characterclass.BlackMage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Abstract class that contain tests suitable for all weapons
 *
 * @author Camilo Ramírez Canales
 */
public abstract class AbstractWeaponTest {

    protected final String CHARACTER_TEST_NAME = "Nix";

    protected BlockingQueue<ICharacter> turns;
    private AbstractCharacter testCharacter;

    /**
     * Setup for the tests
     */
    @BeforeEach
    void setUp() {
        turns = new LinkedBlockingQueue<>();
        testCharacter = new BlackMage(CHARACTER_TEST_NAME, 100, 2, turns);
    }
    /**
     * General test of construction for all non magical weapons
     */
    protected void constructionTest(AbstractWeapon standard,
                                    AbstractWeapon same,
                                    AbstractWeapon anotherName,
                                    AbstractWeapon anotherDamage,
                                    AbstractWeapon anotherWeight,
                                    AbstractWeapon anotherType) {
        assertEquals(standard, standard);
        assertEquals(standard, same);
        assertEquals(standard.hashCode(), same.hashCode());
        assertNotEquals(standard, anotherName);
        assertNotEquals(standard, anotherDamage);
        assertNotEquals(standard, anotherWeight);
        assertNotEquals(standard, anotherType);
        assertNotEquals(standard, testCharacter);
    }
    /**
     * General test of construction for all magical weapons
     */
    protected void magicalConstructionTest(AbstractWeapon standard,
                                           AbstractWeapon same,
                                           AbstractWeapon anotherName,
                                           AbstractWeapon anotherDamage,
                                           AbstractWeapon anotherWeight,
                                           AbstractWeapon anotherMagicDamage) {
        assertEquals(standard, standard);
        assertEquals(standard, same);
        assertEquals(standard.hashCode(), same.hashCode());
        assertNotEquals(standard, anotherName);
        assertNotEquals(standard, anotherDamage);
        assertNotEquals(standard, anotherWeight);
        assertNotEquals(standard, anotherMagicDamage);
        assertNotEquals(standard, testCharacter);
    }
    /**
     * General test for equipment states
     */
    protected void equipmentTest(AbstractWeapon weapon,
                                 AbstractPlayerCharacter validHolder,
                                 AbstractPlayerCharacter secondValidHolder,
                                 AbstractPlayerCharacter unValidHolder) {
        // Checks that the weapon inits available
        assertTrue(weapon.isAvailable());
        assertNull(weapon.getHolder());
        // Checks that an invalid class can't equip the weapon
        unValidHolder.tryToEquip(weapon);
        assertTrue(weapon.isAvailable());
        assertNull(weapon.getHolder());
        // Checks that a valid class character can successfully equip the weapon
        validHolder.tryToEquip(weapon);
        assertFalse(weapon.isAvailable());
        assertEquals(weapon.getHolder(), validHolder);
        // Checks that a second character can't equip an already equipped weapon
        secondValidHolder.tryToEquip(weapon);
        assertEquals(weapon.getHolder(), validHolder);
        // Checks the correct un-equipment of the weapon
        validHolder.unEquip();
        assertTrue(weapon.isAvailable());
        assertNull(weapon.getHolder());
        // Checks that the second character can actually equip the previous un-equipped weapon
        secondValidHolder.tryToEquip(weapon);
        assertFalse(weapon.isAvailable());
        assertEquals(weapon.getHolder(), secondValidHolder);
        // Checks that the weapon cannot equip itself to a character
        weapon.beHeld(validHolder);
        assertNull(validHolder.getEquippedWeapon());
        assertEquals(secondValidHolder.getEquippedWeapon(), weapon);
        assertEquals(weapon.getHolder(), secondValidHolder);
        // Checks that a weapon cannot un-equip itself
        weapon.beUnHeld();
        assertEquals(secondValidHolder.getEquippedWeapon(), weapon);
        assertEquals(weapon.getHolder(), secondValidHolder);
        // Checks that there is no change if a weapon tries to un-equip itself having no holder
        secondValidHolder.unEquip();
        assertNull(weapon.getHolder());
        weapon.beUnHeld();
        assertNull(weapon.getHolder());
    }

    /**
     * Executes the setup for every subclass test
     */
    @BeforeEach
    protected abstract void subClassSetUp();

    /**
     * Executes the construction test for the subclasses
     */
    @Test
    protected abstract void subClassConstructorTest();

    /**
     * Executes the equipment test for the subclasses
     */
    @Test
    protected abstract void subClassEquipmentTest();
}
