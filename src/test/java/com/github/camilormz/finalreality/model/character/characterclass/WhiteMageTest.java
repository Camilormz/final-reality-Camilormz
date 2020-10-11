package com.github.camilormz.finalreality.model.character.characterclass;

import com.github.camilormz.finalreality.model.character.AbstractPlayerCharacterTest;
import com.github.camilormz.finalreality.model.character.player.characterclass.BlackMage;
import com.github.camilormz.finalreality.model.character.player.characterclass.WhiteMage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class that holds everything related to white mages
 */
public class WhiteMageTest extends AbstractPlayerCharacterTest {

    private WhiteMage whiteMage;
    private WhiteMage whiteMageAltName;
    private BlackMage notWhiteMageAltClass;

    @Override
    @BeforeEach
    protected void subClassSetUp() {
        whiteMage = new WhiteMage("Lux", turns);
        whiteMageAltName = new WhiteMage("Abel", turns);
        notWhiteMageAltClass = new BlackMage("Lux", turns);
    }
    @Override
    @Test
    protected void subClassConstructorTest() {
        this.constructionTest(whiteMage, new WhiteMage("Lux", turns),
                              whiteMageAltName, notWhiteMageAltClass);
    }
}
