package com.github.camilormz.finalreality.model.character.player.characterclass;

import com.github.camilormz.finalreality.model.character.CharacterDomain;
import com.github.camilormz.finalreality.model.character.player.AbstractPlayerCharacterTest;
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

    @Override
    @BeforeEach
    protected void subClassSetUp() {
        thief = new Thief(THIEF_NAME, turns);
        thiefAltName = new Thief(THIEF_ALT_NAME, turns);
        unarmedThief = new Thief(THIEF_NAME, turns);
        notThiefAltClass = new BlackMage(THIEF_NAME, turns);
    }
    @Override
    @Test
    protected void subClassConstructorTest() {
        this.constructionTest(thief, new Thief(THIEF_NAME, turns),
                              thiefAltName, notThiefAltClass);
    }
    @Override
    @Test
    protected void subClassWeaponTest() {
        this.weaponEquipmentTest(thief, unarmedThief, testSword, testAxe);
    }
    @Override
    @Test
    protected void subClassWaitTurnTest() {
        this.waitTurnTest(thief, 0, waitTurnTestErrorMargin);
        thief.equip(testSword);
        long expectedTime = testSword.getWeight()/10;
        this.waitTurnTest(thief, expectedTime, waitTurnTestErrorMargin);
    }
    @Override
    @Test
    protected void subClassCharacterDomainTest() {
        this.getCharacterDomainTest(thief, CharacterDomain.PLAYABLE);
    }
}
