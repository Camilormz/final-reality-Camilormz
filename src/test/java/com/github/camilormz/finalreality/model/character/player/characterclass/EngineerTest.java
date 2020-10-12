package com.github.camilormz.finalreality.model.character.player.characterclass;

import com.github.camilormz.finalreality.model.character.CharacterDomain;
import com.github.camilormz.finalreality.model.character.player.AbstractPlayerCharacterTest;
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
    @Override
    @Test
    protected void subClassWaitTurnTest() {
        this.waitTurnTest(engineer, 0, waitTurnTestErrorMargin);
        engineer.equip(testBow);
        long expectedTime = testBow.getWeight()/10;
        this.waitTurnTest(engineer, expectedTime, waitTurnTestErrorMargin);
    }
    @Override
    @Test
    protected void subClassCharacterDomainTest() {
        this.getCharacterDomainTest(engineer, CharacterDomain.PLAYABLE);
    }
}
