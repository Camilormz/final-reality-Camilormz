package com.github.camilormz.finalreality.model.character.characterclass;

import com.github.camilormz.finalreality.model.character.AbstractPlayerCharacterTest;
import com.github.camilormz.finalreality.model.character.player.characterclass.BlackMage;
import com.github.camilormz.finalreality.model.character.player.characterclass.WhiteMage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class that holds everything related to black mages
 *
 * @author Camilo Ramírez Canales
 */
public class BlackMageTest extends AbstractPlayerCharacterTest {

    private BlackMage blackMage;
    private BlackMage blackMageAltName;
    private WhiteMage notBlackMageAltClass;

    @Override
    @BeforeEach
    protected void subClassSetUp() {
        blackMage = new BlackMage("Nix", turns);
        blackMageAltName = new BlackMage("Chaos", turns);
        notBlackMageAltClass = new WhiteMage("Nix", turns);
    }
    @Override
    @Test
    protected void subClassConstructorTest() {
        this.constructionTest(blackMage, new BlackMage("Nix", turns),
                              blackMageAltName, notBlackMageAltClass);
    }
}
