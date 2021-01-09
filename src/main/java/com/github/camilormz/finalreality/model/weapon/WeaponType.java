package com.github.camilormz.finalreality.model.weapon;

/**
 * Enumeration of all the weapon types.
 *
 * @author Ignacio Slater Muñoz.
 */
public enum WeaponType {
  SWORD("Sword"), AXE("Axe"), KNIFE("Knife"),
  STAFF("Staff"), BOW("Bow");

  private final String field_description;

  /**
   * Enum constructor, requires a string description
   */
  WeaponType(String description)  {
    this.field_description = description;
  }

  /**
   * Returns the string description of the enum
   */
  public String getDescription() {
    return this.field_description;
  }
}
