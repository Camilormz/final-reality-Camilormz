package com.github.camilormz.finalreality.model.character.characterclass;

import com.github.camilormz.finalreality.model.character.AbstractPlayerCharacterTest;
import com.github.camilormz.finalreality.model.character.player.characterclass.Engineer;
import com.github.camilormz.finalreality.model.character.player.characterclass.Knight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class that holds everything relative to engineers
 */
public class EngineerTest extends AbstractPlayerCharacterTest {

    private Engineer engineer;
    private Engineer engineerAltName;
    private Knight notEngineerAltClass;

    @Override
    @BeforeEach
    protected void subClassSetUp() {
        engineer = new Engineer("Tesla", turns);
        engineerAltName = new Engineer("Conagher", turns);
        notEngineerAltClass = new Knight("Tesla", turns);
    }
    @Override
    @Test
    protected void subClassConstructorTest() {
        this.constructionTest(engineer, new Engineer("Tesla", turns),
                              engineerAltName, notEngineerAltClass);
    }
}
