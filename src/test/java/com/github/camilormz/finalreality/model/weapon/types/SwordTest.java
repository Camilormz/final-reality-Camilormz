package com.github.camilormz.finalreality.model.weapon.types;

import com.github.camilormz.finalreality.model.weapon.AbstractWeaponTest;
import javafx.scene.input.SwipeEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SwordTest extends AbstractWeaponTest {

    private final String SWORD_NAME = "Infidel Redentor";
    private final String SWORD_ALT_NAME = "Sacred Steel";

    private Sword sword;
    private Sword swordAltName;
    private Sword swordAltDamage;
    private Sword swordAltWeight;
    private Axe notSwordAltType;

    @BeforeEach
    protected void subClassSetUp() {
        sword = new Sword(SWORD_NAME, 10, 10);
        swordAltName = new Sword(SWORD_ALT_NAME, 10, 10);
        swordAltDamage = new Sword(SWORD_NAME, 20, 10);
        swordAltWeight = new Sword(SWORD_NAME, 10, 20);
        notSwordAltType = new Axe(SWORD_NAME, 10, 10);
    }
    @Override
    @Test
    protected void subClassConstructorTest() {
        this.constructionTest(sword, new Sword(SWORD_NAME, 10, 10), swordAltName,
                              swordAltDamage, swordAltWeight, notSwordAltType);
    }
}
