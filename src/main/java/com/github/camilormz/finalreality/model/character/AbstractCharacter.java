package com.github.camilormz.finalreality.model.character;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.github.camilormz.finalreality.model.character.player.IPlayerCharacter;
import org.jetbrains.annotations.NotNull;

/**
 * An abstract class that holds the common behaviour of all the characters in the game.
 *
 * @author Ignacio Slater Muñoz.
 * @author Camilo Ramírez Canales.
 */
public abstract class AbstractCharacter implements ICharacter {

  private final BlockingQueue<ICharacter> turnsQueue;
  private final String name;
  private final CharacterDomain characterDomain;
  private int healthPoints;
  private final int defense;
  private ScheduledExecutorService scheduledExecutor;

  /**
   * Creates a new (abstract) character.
   *
   * @param name
   *     the character's name
   * @param healthPoints
   *     the character's health points
   * @param defense
   *     the character's defense points, it corresponds to its resistance to attacks
   * @param turnsQueue
   *     the queue with the characters waiting for their turn
   * @param characterDomain
   *     the domain of this character
   *     @see CharacterDomain
   */
  protected AbstractCharacter(@NotNull final BlockingQueue<ICharacter> turnsQueue,
                              @NotNull final String name,
                              int healthPoints,
                              final int defense,
                              @NotNull final CharacterDomain characterDomain) {
    this.turnsQueue = turnsQueue;
    this.name = name;
    this.characterDomain = characterDomain;
    this.healthPoints = healthPoints;
    this.defense = defense;
  }

  @Override
  public void waitTurn() {
    scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
    scheduledExecutor
            .schedule(this::addToQueue, this.getTurnWeight() / 10, TimeUnit.SECONDS);
  }

  /**
   * Adds this character to the turns queue.
   */
  private void addToQueue() {
    turnsQueue.add(this);
    scheduledExecutor.shutdown();
  }

  /**
   * Gets the associated turn weight of the character in order to compute its enqueuing speed in
   * the turns queue
   */
  protected abstract int getTurnWeight();

  @Override
  public String getName() {
    return name;
  }

  @Override
  public CharacterDomain getCharacterDomain() {
    return this.characterDomain;
  }

  @Override
  public int getHealthPoints() {
    return this.healthPoints;
  }

  @Override
  public abstract int getDamagePoints();

  @Override
  public int getDefensePoints() {
    return this.defense;
  }

  @Override
  public boolean isAlive() {
    return this.getHealthPoints() > 0;
  }

  @Override
  public abstract void attack(ICharacter character);

  @Override
  public abstract void beAttackedByPlayableCharacter(IPlayerCharacter playerCharacter);

  @Override
  public abstract void beAttackedByEnemy(Enemy enemy);

  /**
   * Manages an amount of damage done to the character
   */
  protected abstract void beDamaged(int damage);

  /**
   * Sets the character health points to a fixed value
   */
  protected void setHealthPoints(int healthPoints) {
    this.healthPoints = healthPoints;
  }

}
