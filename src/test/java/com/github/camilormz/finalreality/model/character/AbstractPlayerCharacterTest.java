package com.github.camilormz.finalreality.model.character;

import com.github.camilormz.finalreality.model.character.player.AbstractPlayerCharacter;
import com.github.camilormz.finalreality.model.character.player.characterclass.WhiteMage;
import com.github.camilormz.finalreality.model.weapon.IWeapon;
import com.github.camilormz.finalreality.model.weapon.types.*;
import javafx.scene.input.SwipeEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Abstract class that holds the tests for all the playable character subclasses
 *
 * @author Camilo Ramírez Canales
 */
public abstract class AbstractPlayerCharacterTest {

    private final String ENEMY_NAME = "Kronos";
    private final String AXE_NAME = "Wood Slayer";
    private final String BOW_NAME = "The Trebuchet";
    private final String KNIFE_NAME = "Stealthy";
    private final String STAFF_NAME = "Shadowmaker";
    private final String SWORD_NAME = "Infidel Redentor";

    protected BlockingQueue<ICharacter> turns;
    protected Axe testAxe;
    protected Bow testBow;
    protected Knife testKnife;
    protected Staff testStaff;
    protected Sword testSword;
    private Enemy testEnemy;

    /**
     * Executes a setup for every test involving Playable Characters
     */
    @BeforeEach
    void setUp() {
        turns = new LinkedBlockingQueue<>();
        testEnemy = new Enemy(ENEMY_NAME, 10, turns);
        testAxe = new Axe(AXE_NAME, 10, 10);
        testBow = new Bow(BOW_NAME, 10, 10);
        testKnife = new Knife(KNIFE_NAME, 10, 10);
        testStaff = new Staff(STAFF_NAME, 10, 10, 10);
        testSword = new Sword(SWORD_NAME, 10, 10);
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
}
