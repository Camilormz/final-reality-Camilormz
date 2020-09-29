package com.github.camilormz.finalreality.model.character;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import com.github.camilormz.finalreality.model.character.player.AbstractPlayerCharacter;
import com.github.camilormz.finalreality.model.weapon.Weapon;
import com.github.camilormz.finalreality.model.weapon.WeaponType;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

// TODO: This test is totally broken and must be fixed or re-done ASAP

/**
 * Abstract class containing the common tests for all the types of characters.
 *
 * @author Ignacio Slater Muñoz.
 * @author Camilo Ramírez Canales.
 * @see ICharacter
 */
public abstract class AbstractCharacterTest {

  protected BlockingQueue<ICharacter> turns;
  protected List<ICharacter> testCharacters;
  protected Weapon testWeapon;

  private void tryToEquip(ICharacter character) {
    if (character instanceof AbstractPlayerCharacter) {
      AbstractPlayerCharacter pCharacter = (AbstractPlayerCharacter) character;
      pCharacter.equip(testWeapon);
    }
  }

  protected void checkConstruction(final ICharacter expectedCharacter,
      final ICharacter testEqualCharacter,
      final ICharacter sameClassDifferentCharacter,
      final ICharacter differentClassCharacter) {
    assertEquals(expectedCharacter, testEqualCharacter);
    assertNotEquals(sameClassDifferentCharacter, testEqualCharacter);
    assertNotEquals(testEqualCharacter, differentClassCharacter);
    assertEquals(expectedCharacter.hashCode(), testEqualCharacter.hashCode());
  }

  protected void basicSetUp() {
    turns = new LinkedBlockingQueue<>();
    testWeapon = new Weapon("Test", 15, 10, WeaponType.AXE);
    testCharacters = new ArrayList<>();
  }
}
