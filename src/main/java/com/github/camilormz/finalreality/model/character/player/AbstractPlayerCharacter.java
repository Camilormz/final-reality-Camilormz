package com.github.camilormz.finalreality.model.character.player;

import com.github.camilormz.finalreality.model.character.AbstractCharacter;
import com.github.camilormz.finalreality.model.character.CharacterDomain;
import com.github.camilormz.finalreality.model.character.ICharacter;

import java.util.EnumSet;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;

import com.github.camilormz.finalreality.model.weapon.IWeapon;
import com.github.camilormz.finalreality.model.weapon.WeaponType;
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

  private IWeapon equippedWeapon;
  private final CharacterClass characterClass;
  private final EnumSet<WeaponType> allowedWeapons;

  /**
   * Creates a new (abstract) playable character.
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
                                 @NotNull final CharacterClass characterClass,
                                 @NotNull final EnumSet<WeaponType> allowedWeapons) {
    super(turnsQueue, name, CharacterDomain.PLAYABLE);
    this.equippedWeapon = null;
    this.characterClass = characterClass;
    this.allowedWeapons = allowedWeapons;
  }

  public void equip(IWeapon weapon) {
    WeaponType weaponType = weapon.getType();
    if (this.allowedWeapons.contains(weaponType)) {
      this.equippedWeapon = weapon;
    }
  }

  public IWeapon getEquippedWeapon() {
    return this.equippedWeapon;
  }

  public CharacterClass getCharacterClass() {
    return this.characterClass;
  }

  @Override
  protected int getTurnWeight() {
    IWeapon weapon = this.getEquippedWeapon();
    if (weapon == null) {
      return 0;
    }
    return weapon.getWeight();
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

  @Override
  public int hashCode() {
    return Objects.hash(getName(), getCharacterClass());
  }
}
