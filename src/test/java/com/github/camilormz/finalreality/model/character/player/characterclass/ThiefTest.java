package com.github.camilormz.finalreality.model.character.player.characterclass;

import com.github.camilormz.finalreality.model.character.CharacterDomain;
import com.github.camilormz.finalreality.model.character.player.AbstractPlayerCharacterTest;
import com.github.camilormz.finalreality.model.weapon.types.Staff;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class that holds everything related to thieves
 *
 * @author Camilo Ramírez Canales
 */
public class ThiefTest extends AbstractPlayerCharacterTest {

    private final String THIEF_NAME = "Bonnie";
    private final String THIEF_ALT_NAME = "Clyde";

    private Thief thief;
    private Thief thiefAltName;
    private Thief unarmedThief;
    private BlackMage notThiefAltClass;

    private Thief combatThief;
    private Thief anotherCombatThief;
    private Staff combatStaff;
    private Staff anotherStaff;

    @Override
    @BeforeEach
    protected void subClassSetUp() {
        thief = new Thief(THIEF_NAME, 100, turns);
        thiefAltName = new Thief(THIEF_ALT_NAME, 100, turns);
        unarmedThief = new Thief(THIEF_NAME, 100, turns);
        notThiefAltClass = new BlackMage(THIEF_NAME, 100, turns);

        combatThief = new Thief(THIEF_NAME, 100, turns);
        anotherCombatThief = new Thief(THIEF_NAME, 100, turns);
        combatStaff = new Staff(STAFF_NAME, 10, 10, 10);
        anotherStaff = new Staff(STAFF_NAME, 10, 10, 10);
        combatThief.tryToEquip(combatStaff);
        anotherCombatThief .tryToEquip(anotherStaff);
    }
    @Override
    @Test
    protected void subClassConstructorTest() {
        this.constructionTest(thief, new Thief(THIEF_ALT_NAME, 100, turns),
                              thiefAltName, notThiefAltClass);
    }
    @Override
    @Test
    protected void subClassWeaponTest() {
        this.weaponEquipmentTest(thief, unarmedThief,
                                 testSword, anotherTestSword, combatStaff, testAxe);
    }
    @Override
    @Test
    protected void subClassWaitTurnTest() {
        this.waitTurnTest(thief, 0, waitTurnTestErrorMargin);
        thief.tryToEquip(testSword);
        long expectedTime = testSword.getWeight()/10;
        this.waitTurnTest(thief, expectedTime, waitTurnTestErrorMargin);
    }
    @Override
    @Test
    protected void subClassCharacterDomainTest() {
        this.getCharacterDomainTest(thief, CharacterDomain.PLAYABLE);
    }
    @Override
    @Test
    protected void subClassCombatTest() {
        this.subClassCombatTestExecution(combatThief, anotherCombatThief);
    }
    @Override
    @Test
    protected void subClassWeaponDroppingTest() {
        this.deathWeaponDropTest(thief, testStaff);
    }
}
