package com.github.camilormz.finalreality.model.character.player;

import com.github.camilormz.finalreality.model.character.AbstractCharacter;
import com.github.camilormz.finalreality.model.character.CharacterDomain;
import com.github.camilormz.finalreality.model.character.ICharacter;
import com.github.camilormz.finalreality.model.weapon.Weapon;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import org.jetbrains.annotations.NotNull;

/**
 * Abstract class that holds all the information of a single character of the game controlled by
 * a player.
 *
 * @author Ignacio Slater Muñoz.
 * @author Camilo Ramírez Canales.
 */
public abstract class AbstractPlayerCharacter extends AbstractCharacter
           implements IPlayerCharacter{

  protected Weapon equippedWeapon;
  private final CharacterClass characterClass;

  /**
   * Creates a new character.
   *
   * @param name
   *     the character's name
   * @param turnsQueue
   *     the queue with the characters waiting for their turn
   * @param characterClass
   *     the class of this character
   */
  public AbstractPlayerCharacter(@NotNull String name,
                                 @NotNull BlockingQueue<ICharacter> turnsQueue,
                                 @NotNull final CharacterClass characterClass) {
    super(turnsQueue, name, CharacterDomain.PLAYABLE);
    this.equippedWeapon = null;
    this.characterClass = characterClass;
  }

  public abstract void equip(Weapon weapon);

  public Weapon getEquippedWeapon() {
    return this.equippedWeapon;
  }

  public CharacterClass getCharacterClass() {
    return this.characterClass;
  }

  @Override
  public int getTurnWeight() {
    Weapon weapon = this.getEquippedWeapon();
    if (weapon == null) {
      return 0;
    }
    return weapon.getWeight();
  }

  @Override
  public int hashCode() {
    return Objects.hash(getCharacterClass());
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof AbstractPlayerCharacter)) {
      return false;
    }
    final AbstractPlayerCharacter that = (AbstractPlayerCharacter) o;
    return getCharacterClass() == that.getCharacterClass()
        && getName().equals(that.getName());
  }
}
