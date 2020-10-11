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

  protected final BlockingQueue<ICharacter> turnsQueue;
  protected final String name;
  private final CharacterDomain characterDomain;
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
  protected AbstractCharacter(@NotNull BlockingQueue<ICharacter> turnsQueue,
                              @NotNull String name,
                              @NotNull CharacterDomain characterDomain) {
    this.turnsQueue = turnsQueue;
    this.name = name;
    this.characterDomain = characterDomain;
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
}
