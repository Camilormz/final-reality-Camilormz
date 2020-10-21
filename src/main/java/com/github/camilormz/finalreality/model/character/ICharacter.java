package com.github.camilormz.finalreality.model.character;

/**
 * This represents a character from the game.
 * A character can be controlled by the player or by the CPU (an enemy).
 *
 * @author Ignacio Slater Muñoz.
 * @author Camilo Ramírez Canales.
 */
public interface ICharacter {

  /**
   * Sets a scheduled executor to make this character (thread) wait for {@code turnWeight / 10}
   * seconds before adding the character to the queue.
   */
  void waitTurn();

  /**
   * Returns this character's name.
   */
  String getName();

  /**
   * Returns this character's domain.
   * @see CharacterDomain
   */
  CharacterDomain getCharacterDomain();

  /**
   * Returns the character Health Points (HP).
   */
  int getHealthPoints();

  /**
   * Returns the current non-magical damage the character is able to do
   */
  int getDamage();

  /**
   * Returns the character defense points
   */
  int getDefense();

  /**
   * Returns if the character is alive.
   */
  boolean isAlive();

  /**
   * Attacks another character
   * This generic method allows friendly fire implementations.
   */
  void attack(ICharacter adversary);
}
