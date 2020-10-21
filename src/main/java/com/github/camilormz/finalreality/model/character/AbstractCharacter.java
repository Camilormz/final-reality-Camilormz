package com.github.camilormz.finalreality.model.character;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
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
  private ScheduledExecutorService scheduledExecutor;

  /**
   * Creates a new (abstract) character.
   *
   * @param name
   *     the character's name
   * @param turnsQueue
   *     the queue with the characters waiting for their turn
   * @param characterDomain
   *     the domain of this character
   *     @see CharacterDomain
   */
  protected AbstractCharacter(@NotNull final BlockingQueue<ICharacter> turnsQueue,
                              @NotNull final String name,
                              int healthPoints,
                              @NotNull final CharacterDomain characterDomain) {
    this.turnsQueue = turnsQueue;
    this.name = name;
    this.characterDomain = characterDomain;
    this.healthPoints = healthPoints;
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

  // TODO: implement method
  @Override
  public int getDamage() { return 0; }

  // TODO: implement method
  @Override
  public int getDefense() { return 0; }

  // TODO: implement method
  @Override
  public boolean isAlive() { return true; }

  // TODO: implement method
  public void attack(ICharacter character) {}
}
