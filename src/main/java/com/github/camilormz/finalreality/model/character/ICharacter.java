package com.github.camilormz.finalreality.model.character;

import com.github.camilormz.finalreality.model.character.player.IPlayerCharacter;

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
  int getDamagePoints();

  /**
   * Returns the character defense points
   */
  int getDefensePoints();

  /**
   * Returns if the character is alive.
   */
  boolean isAlive();

  /**
   * Attacks another character
   * This generic method allows friendly fire implementations.
   */
  void attack(ICharacter adversary);

  /**
   * Method that manages an attack from a playable character
   */
  void beAttackedByPlayableCharacter(IPlayerCharacter playerCharacter);

  /**
   * Method that manages an attack from an enemy character
   */
  void beAttackedByEnemy(Enemy enemy);
}
