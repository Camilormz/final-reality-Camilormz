package com.github.camilormz.finalreality.model.weapon;

import com.github.camilormz.finalreality.model.character.AbstractCharacter;
import com.github.camilormz.finalreality.model.character.ICharacter;
import com.github.camilormz.finalreality.model.character.player.characterclass.BlackMage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Abstract class that contain tests suitable for all weapons
 *
 * @author Camilo Ramírez Canales
 */
public abstract class AbstractWeaponTest {

    private final String CHARACTER_TEST_NAME = "Nix";

    protected BlockingQueue<ICharacter> turns;
    private AbstractCharacter testCharacter;

    /**
     * Setup for the tests
     */
    @BeforeEach
    void setUp() {
        turns = new LinkedBlockingQueue<>();
        testCharacter = new BlackMage(CHARACTER_TEST_NAME, turns);
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
     * Executes the setup for every subclass test
     */
    @BeforeEach
    protected abstract void subClassSetUp();

    /**
     * Executes the construction test for the subclasses
     */
    @Test
    protected abstract void subClassConstructorTest();
}
