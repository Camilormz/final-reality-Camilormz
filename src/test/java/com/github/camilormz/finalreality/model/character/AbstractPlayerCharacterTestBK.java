package com.github.camilormz.finalreality.model.character;

import com.github.camilormz.finalreality.model.character.player.CharacterClass;
import com.github.camilormz.finalreality.model.character.player.AbstractPlayerCharacter;
import java.util.Map;

// TODO: The design of this test IS totally broken and a new one must be re-done ASAP

/**
 * Set of tests for the {@code GameCharacter} class.
 *
 * @author Ignacio Slater Muñoz.
 * @author Camilo Ramírez Canales.
 * @see AbstractPlayerCharacter
 */
class AbstractPlayerCharacterTestBK extends AbstractCharacterTestBK {

  private static final String BLACK_MAGE_NAME = "Vivi";
  private static final String KNIGHT_NAME = "Adelbert";
  private static final String WHITE_MAGE_NAME = "Eiko";
  private static final String ENGINEER_NAME = "Cid";
  private static final String THIEF_NAME = "Zidane";
  private Map<CharacterClass, String> characterNames;

}
