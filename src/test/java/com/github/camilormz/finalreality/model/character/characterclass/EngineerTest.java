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

    private final String ENGINEER_NAME = "Tesla";
    private final String ENGINEER_ALT_NAME = "Conagher";

    private Engineer engineer;
    private Engineer engineerAltName;
    private Engineer unarmedEngineer;
    private Knight notEngineerAltClass;

    @Override
    @BeforeEach
    protected void subClassSetUp() {
        engineer = new Engineer(ENGINEER_NAME, turns);
        engineerAltName = new Engineer(ENGINEER_ALT_NAME, turns);
        unarmedEngineer = new Engineer(ENGINEER_NAME, turns);
        notEngineerAltClass = new Knight(ENGINEER_NAME, turns);
    }
    @Override
    @Test
    protected void subClassConstructorTest() {
        this.constructionTest(engineer, new Engineer(ENGINEER_NAME, turns),
                              engineerAltName, notEngineerAltClass);
    }
    @Override
    @Test
    protected void subClassWeaponTest() {
        this.weaponEquipmentTest(engineer, unarmedEngineer, testBow, testStaff);
    }
}
