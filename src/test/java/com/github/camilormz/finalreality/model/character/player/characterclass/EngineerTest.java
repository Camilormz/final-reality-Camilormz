package com.github.camilormz.finalreality.model.character.player.characterclass;

import com.github.camilormz.finalreality.model.character.CharacterDomain;
import com.github.camilormz.finalreality.model.character.player.AbstractPlayerCharacterTest;
import com.github.camilormz.finalreality.model.weapon.types.Axe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class that holds everything related to engineers
 *
 * @author Camilo Ramírez Canales
 */
public class EngineerTest extends AbstractPlayerCharacterTest {

    private final String ENGINEER_NAME = "Tesla";
    private final String ENGINEER_ALT_NAME = "Conagher";

    private Engineer engineer;
    private Engineer engineerAltName;
    private Engineer unarmedEngineer;
    private Knight notEngineerAltClass;

    private Engineer combatEngineer;
    private Engineer anotherCombatEngineer;
    private Axe combatAxe;
    private Axe anotherAxe;

    @Override
    @BeforeEach
    protected void subClassSetUp() {
        engineer = new Engineer(ENGINEER_NAME, 100, turns);
        engineerAltName = new Engineer(ENGINEER_ALT_NAME, 100, turns);
        unarmedEngineer = new Engineer(ENGINEER_NAME, 100, turns);
        notEngineerAltClass = new Knight(ENGINEER_NAME, 100, turns);

        combatEngineer = new Engineer(ENGINEER_NAME, 100, turns);
        anotherCombatEngineer = new Engineer(ENGINEER_ALT_NAME, 100, turns);
        combatAxe = new Axe(AXE_NAME, 10, 10);
        anotherAxe = new Axe(AXE_NAME, 10, 10);
        combatEngineer.tryToEquip(combatAxe);
        anotherCombatEngineer.tryToEquip(anotherAxe);
    }
    @Override
    @Test
    protected void subClassConstructorTest() {
        this.constructionTest(engineer, new Engineer(ENGINEER_NAME, 100, turns),
                              engineerAltName, notEngineerAltClass);
    }
    @Override
    @Test
    protected void subClassWeaponTest() {
        this.weaponEquipmentTest(engineer, unarmedEngineer,
                                 testBow, anotherTestBow, combatAxe, testStaff);
    }
    @Override
    @Test
    protected void subClassWaitTurnTest() {
        this.waitTurnTest(engineer, 0, waitTurnTestErrorMargin);
        engineer.tryToEquip(testBow);
        long expectedTime = testBow.getWeight()/10;
        this.waitTurnTest(engineer, expectedTime, waitTurnTestErrorMargin);
    }
    @Override
    @Test
    protected void subClassCharacterDomainTest() {
        this.getCharacterDomainTest(engineer, CharacterDomain.PLAYABLE);
    }
    @Override
    @Test
    protected void subClassCombatTest() {
        this.subClassCombatTestExecution(combatEngineer, anotherCombatEngineer);
    }
    @Override
    @Test
    protected void subClassWeaponDroppingTest() {
        this.deathWeaponDropTest(engineer, testBow);
    }
}
