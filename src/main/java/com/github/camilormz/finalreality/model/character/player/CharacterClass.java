package com.github.camilormz.finalreality.model.character.player;

/**
 * Enumeration of the classes a player character may have.
 *
 * @author Ignacio Slater Muñoz.
 * @author Camilo Ramírez Canales
 */
public enum CharacterClass {
  KNIGHT("Knight"), ENGINEER("Engineer"), THIEF("Thief"),
  BLACK_MAGE("Black mage"), WHITE_MAGE("White Mage");

  private final String field_description;

  /**
   * Enum constructor, requires a string description
   */
  CharacterClass(String description)  {
    this.field_description = description;
  }

  /**
   * Returns the string description of the enum
   */
  public String getDescription() {
    return this.field_description;
  }
}
