package com.github.camilormz.finalreality.model.character.characterclass;

import com.github.camilormz.finalreality.model.character.AbstractPlayerCharacterTest;
import com.github.camilormz.finalreality.model.character.player.characterclass.Engineer;
import com.github.camilormz.finalreality.model.character.player.characterclass.Knight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class that holds everything related to knights
 *
 * @author Camilo Ramírez Canales.
 */
public class KnightTest extends AbstractPlayerCharacterTest {

    private Knight knight;
    private Knight knightAltName;
    private Engineer notKnightAltClass;

    @Override
    @BeforeEach
    protected void subClassSetUp() {
        knight = new Knight("Steel", turns);
        knightAltName = new Knight("Iron", turns);
        notKnightAltClass = new Engineer("Steel", turns);
    }
    @Override
    @Test
    protected void subClassConstructorTest() {
        this.constructionTest(knight, new Knight("Steel", turns),
                              knightAltName, notKnightAltClass);
    }
}
