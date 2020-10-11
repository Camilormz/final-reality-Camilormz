package com.github.camilormz.finalreality.model.character.characterclass;

import com.github.camilormz.finalreality.model.character.AbstractPlayerCharacterTest;
import com.github.camilormz.finalreality.model.character.player.characterclass.BlackMage;
import com.github.camilormz.finalreality.model.character.player.characterclass.Thief;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class that holds everything related to thieves
 *
 * @author Camilo Ramírez Canales
 */
public class ThiefTest extends AbstractPlayerCharacterTest {

    private Thief thief;
    private Thief thiefAltName;
    private BlackMage notThiefAltClass;

    @Override
    @BeforeEach
    protected void subClassSetUp() {
        thief = new Thief("Bonnie", turns);
        thiefAltName = new Thief("Clyde", turns);
        notThiefAltClass = new BlackMage("Bonnie", turns);
    }
    @Override
    @Test
    protected void subClassConstructorTest() {
        this.constructionTest(thief, new Thief("Bonnie", turns),
                              thiefAltName, notThiefAltClass);
    }
}
