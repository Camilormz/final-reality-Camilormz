package com.github.camilormz.finalreality.model.character.player;

import com.github.camilormz.finalreality.model.character.AbstractCharacterTest;
import com.github.camilormz.finalreality.model.character.Enemy;
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

    private Enemy killerEnemy;
    private Enemy strongEnemy;
    private Enemy weakEnemy;

    /**
     * SetUp enemies for combat tet
     */
    @BeforeEach
    void setUpEnemies() {
        strongEnemy = new Enemy(ENEMY_TEST_NAME, 10, turns);
        weakEnemy = new Enemy(ENEMY_TEST_NAME, 10, turns);
        killerEnemy = new Enemy(ENEMY_TEST_NAME, 10, turns);
    }
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
                                       IWeapon validAvailableWeapon,
                                       IWeapon anotherValidAvailableWeapon,
                                       IWeapon validUnavailableWeapon,
                                       IWeapon invalidWeapon) {
        // Tests that character starts with no weapon
        assertNull(character.getEquippedWeapon());
        // Tests that an invalid weapon cannot be equipped
        character.tryToEquip(invalidWeapon);
        assertNull(character.getEquippedWeapon());
        // Tests that an unavailable weapon cannot be equipped
        character.tryToEquip(validUnavailableWeapon);
        assertNull(character.getEquippedWeapon());
        // Tests that a valid weapon can be equipped
        character.tryToEquip(validAvailableWeapon);
        assertEquals(character.getEquippedWeapon(), validAvailableWeapon);
        assertEquals(character, unarmedCharacter);
        // Tests that the character can equip another valid weapon
        character.tryToEquip(anotherValidAvailableWeapon);
        assertEquals(character.getEquippedWeapon(), anotherValidAvailableWeapon);
        // Tests that a character can un-equip its weapons
        character.unEquip();
        assertNull(character.getEquippedWeapon());
        // Tests that a character can re-equip an old weapon
        character.tryToEquip(validAvailableWeapon);
        assertEquals(character.getEquippedWeapon(), validAvailableWeapon);
    }
    /**
     * Test for weapon dropping at death
     * @param character HP and defense must be enough to be killed in one attack by the enemy
     */
    protected void deathWeaponDropTest(AbstractPlayerCharacter character,
                                       IWeapon validAvailableWeapon) {
        // Tests that the character starts with no weapon and alive
        assertTrue(character.isAlive());
        assertNull(character.getEquippedWeapon());
        assertTrue(validAvailableWeapon.isAvailable());
        // Tests that the character can equip a weapon and is its holder
        character.tryToEquip(validAvailableWeapon);
        assertEquals(character.getEquippedWeapon(), validAvailableWeapon);
        assertFalse(validAvailableWeapon.isAvailable());
        assertNull(validAvailableWeapon.getHolder());
        // Kills the character
        killerEnemy.attack(character);
        assertFalse(character.isAlive());
        // Tests that the weapon of the character is dropped
        assertNull(character.getEquippedWeapon());
        assertTrue(validAvailableWeapon.isAvailable());
        assertNull(validAvailableWeapon.getHolder());
    }

    /**
     * Executes the combat test adapted to playable characters
     * @param character must have 100 HP, 10 Damage and 2 Defense
     * TODO: Modify constructors to make this test compilable
     * Issues with: character with no HP and defense, and enemies with no HP, damage and defense
     */
    protected void subClassCombatTestExecution(AbstractPlayerCharacter character,
                                      AbstractPlayerCharacter sameDomainCharacter) {
        assert character.getHealthPoints() == 100 && character.getDamage() == 10
                                                  && character.getDefense() == 2;
        this.combatTest(character, sameDomainCharacter, strongEnemy, weakEnemy,
                       100,10, 2,
                       10, 1,
                       100, 2);
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
    protected abstract void subClassWeaponTest();

    /**
     * Executes the test for weapon dropping at death
     */
    @Test
    protected abstract void subClassWeaponDroppingTest();

    @Override
    @Test
    protected abstract void subClassWaitTurnTest();

    @Override
    @Test
    protected abstract void subClassCharacterDomainTest();

    @Override
    @Test
    protected abstract void subClassCombatTest();
}
