package com.github.camilormz.finalreality.model.character;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import org.jetbrains.annotations.NotNull;

/**
 * A class that holds all the information of a single enemy of the game.
 *
 * @author Ignacio Slater Muñoz
 * @author Camilo Ramírez Canales
 */
public class Enemy extends AbstractCharacter {

  private final int weight;
  private final int damage;

  /**
   * Creates a new enemy.
   *
   * @param name
   *     the enemy's name
   * @param weight
   *     the enemy's weight, variable relevant for time enqueuing
   * @param healthPoints
   *     the enemy's health points
   * @param damage
   *     the damage the enemy is able to do in a single attack
   * @param turnsQueue
   *     the queue with the characters waiting for their turn
   */
  public Enemy(@NotNull final String name,
               final int weight,
               int healthPoints,
               final int damage,
               @NotNull final BlockingQueue<ICharacter> turnsQueue) {
    super(turnsQueue, name, healthPoints, CharacterDomain.ENEMY);
    this.weight = weight;
    this.damage = damage;
  }

  /**
   * Returns the weight of this enemy.
   */
  public int getWeight() {
    return weight;
  }

  @Override
  protected int getTurnWeight() {
    return this.getWeight();
  }

  @Override
  public int getDamagePoints() {
    return this.damage;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Enemy)) {
      return false;
    }
    final Enemy enemy = (Enemy) o;
    return this.getName().equals(enemy.getName()) &&
           this.getWeight() == enemy.getWeight() &&
           this.getDamagePoints() == enemy.getDamagePoints();
  }

  @Override
  public int hashCode() {
    return Objects.hash(getName(), getWeight());
  }
}
