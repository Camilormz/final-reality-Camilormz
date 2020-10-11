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

  /**
   * Creates a new enemy.
   *
   * @param name
   *     the enemy's name
   * @param weight
   *     the enemy's weight, variable relevant for time enqueuing
   * @param turnsQueue
   *     the queue with the characters waiting for their turn
   */
  public Enemy(@NotNull final String name,
               final int weight,
               @NotNull final BlockingQueue<ICharacter> turnsQueue) {
    super(turnsQueue, name, CharacterDomain.ENEMY);
    this.weight = weight;
  }

  /**
   * Returns the weight of this enemy.
   */
  public int getWeight() {
    return weight;
  }

  protected int getTurnWeight() {
    return this.getWeight();
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
    return getWeight() == enemy.getWeight();
  }

  @Override
  public int hashCode() {
    return Objects.hash(getName(), getWeight());
  }
}
