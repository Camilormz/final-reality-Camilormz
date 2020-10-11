package com.github.camilormz.finalreality.model.character;

import com.github.camilormz.finalreality.model.character.player.AbstractPlayerCharacter;
import com.github.camilormz.finalreality.model.character.player.characterclass.*;
import com.github.camilormz.finalreality.model.weapon.IWeapon;
import com.github.camilormz.finalreality.model.weapon.types.Axe;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Abstract class that holds the tests for all the playable character subclasses
 *
 * @author Camilo Ramírez Canales
 */
public abstract class AbstractPlayerCharacterTest {

    protected BlockingQueue<ICharacter> turns;
    private IWeapon testWeapon;

    /**
     * Executes a setup for every test involving Playable Characters
     */
    @BeforeEach
    void setUp() {
        turns = new LinkedBlockingQueue<>();
        // blackMage = new BlackMage("Nix", turns);
        // engineer = new Engineer("Tesla", turns);
        // knight = new Knight("Steel", turns);
        // thief = new Thief("Bonnie", turns);
        // whiteMage = new WhiteMage("Lux", turns);
        testWeapon = new Axe("Wood Slayer", 10, 10);
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
        assertNotEquals(standard, testWeapon);
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
}
