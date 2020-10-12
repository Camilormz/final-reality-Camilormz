package com.github.camilormz.finalreality.model.weapon.types;

import com.github.camilormz.finalreality.model.weapon.AbstractWeaponTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class that holds everything related to knifes
 *
 * @author Camilo Ramírez Canales
 */
public class KnifeTest extends AbstractWeaponTest {

    private final String KNIFE_NAME = "Stealthy";
    private final String KNIFE_ALT_NAME = "Handy";

    private Knife knife;
    private Knife knifeAltName;
    private Knife knifeAltDamage;
    private Knife knifeAltWeight;
    private Bow notKnifeAltType;

    @Override
    @BeforeEach
    protected void subClassSetUp() {
        knife = new Knife(KNIFE_NAME, 10, 10);
        knifeAltName = new Knife(KNIFE_ALT_NAME, 10, 10);
        knifeAltDamage = new Knife(KNIFE_NAME, 20, 10);
        knifeAltWeight = new Knife(KNIFE_NAME, 10, 20);
        notKnifeAltType = new Bow(KNIFE_NAME, 10, 10);
    }
    @Override
    @Test
    protected void subClassConstructorTest() {
        this.constructionTest(knife, new Knife(KNIFE_NAME, 10, 10), knifeAltName,
                              knifeAltDamage, knifeAltWeight, notKnifeAltType);
    }
}
