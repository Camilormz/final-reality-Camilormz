package com.github.camilormz.finalreality.model.character.player;

import com.github.camilormz.finalreality.model.character.AbstractCharacterTest;
import com.github.camilormz.finalreality.model.weapon.IWeapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Abstract class that holds the tests for all the playable character subclasses
 *
 * @author Camilo Ramírez Canales
 */
public abstract class AbstractPlayerCharacterTest extends AbstractCharacterTest {

    /**
     * General test of construction for all playable characters
     */
    protected void constructionTest(AbstractPlayerCharacter standard,
                                    AbstractPlayerCharacter same,
                                    AbstractPlayerCharacter anotherName,
                                    AbstractPlayerCharacter anotherClass) {
        assertEquals(standard, standard);
        assertEquals(standard, same);
        assertEquals(standard.hashCode(), same.hashCode());
        assertNotEquals(standard, anotherName);
        assertNotEquals(standard, anotherClass);
        assertNotEquals(standard, testEnemy);
    }
    /**
     * General test of weapon equipment
     */
    protected void weaponEquipmentTest(AbstractPlayerCharacter character,
                                       AbstractPlayerCharacter unarmedCharacter,
                                       IWeapon validWeapon, IWeapon invalidWeapon) {
        assertNull(character.getEquippedWeapon());
        character.equip(invalidWeapon);
        assertNull(character.getEquippedWeapon());
        character.equip(validWeapon);
        assertEquals(character.getEquippedWeapon(), validWeapon);
        assertEquals(character, unarmedCharacter);
    }

    /**
     * Does the setup related to the playable character subclass
     */
    @BeforeEach
    protected abstract void subClassSetUp();

    /**
     * Executes the construction test for the subclasses
     */
    @Test
    protected abstract void subClassConstructorTest();

    /**
     * Executes the test for weapon equipment
     */
    @Test
    protected void subClassWeaponTest() {}

    @Override
    @Test
    protected abstract void subClassWaitTurnTest();

    @Override
    @Test
    protected abstract void subClassCharacterDomainTest();
}
