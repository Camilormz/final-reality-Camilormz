package com.github.camilormz.finalreality.model.character.player;

import com.github.camilormz.finalreality.model.character.AbstractCharacter;
import com.github.camilormz.finalreality.model.character.CharacterDomain;
import com.github.camilormz.finalreality.model.character.Enemy;
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
   * @param healthPoints
   *     the character's health points
   * @param turnsQueue
   *     the queue with the characters waiting for their turn
   * @param characterClass
   *     the class of this character
   *     @see CharacterClass
   * @param allowedWeapons
   *     the set of weapon types that the character is allowed to equip
   *     @see WeaponType
   */
  public AbstractPlayerCharacter(@NotNull final String name,
                                 int healthPoints,
                                 final int defense,
                                 @NotNull final BlockingQueue<ICharacter> turnsQueue,
                                 @NotNull final CharacterClass characterClass,
                                 @NotNull final EnumSet<WeaponType> allowedWeapons) {
    super(turnsQueue, name, healthPoints, defense, CharacterDomain.PLAYABLE);
    this.equippedWeapon = null;
    this.characterClass = characterClass;
    this.allowedWeapons = allowedWeapons;
  }

  @Override
  public void tryToEquip(IWeapon weapon) {
    WeaponType weaponType = weapon.getType();
    IWeapon priorWeapon = this.getEquippedWeapon();
    if (this.allowedWeapons.contains(weaponType) && weapon.isAvailable() && this.isAlive()) {
      this.equippedWeapon = weapon;
      weapon.beHeld(this);
      if (priorWeapon != null) {
        priorWeapon.beUnHeld();
      }
      // TODO: make an equivalent to assert weapon.getHolder() == this;
    }
  }

  @Override
  public void unEquip() {
    if (this.getEquippedWeapon() != null) {
      IWeapon currentWeapon = this.equippedWeapon;
      this.equippedWeapon = null;
      currentWeapon.beUnHeld();
    }
  }

  @Override
  public IWeapon getEquippedWeapon() {
    return this.equippedWeapon;
  }

  @Override
  public CharacterClass getCharacterClass() {
    return this.characterClass;
  }

  @Override
  public int getDamagePoints() {
    if (this.equippedWeapon == null) {
      return 0;
    } else {
      return this.equippedWeapon.getDamage();
    }
  }

  @Override
  public void attack(ICharacter character) {
    if (this.isAlive()) {
      character.beAttackedByPlayableCharacter(this);
    }
    // TODO: Raise flag or exception to controller if a dead playable character is trying to attack
  }

  @Override
  public void beAttackedByPlayableCharacter(IPlayerCharacter playerCharacter) {
    // No action as friendly fire is not a current feature of this game
    // TODO: raise a flag or exception for the controller
  }

  @Override
  public void beAttackedByEnemy(Enemy enemy) {
    int HPLoss = enemy.getDamagePoints() - this.getDefensePoints();
    if (HPLoss > 0) {
      this.beDamaged(HPLoss);
    }
  }

  @Override
  protected void beKilled() {
    this.unEquip();
    this.setHealthPoints(0);
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
    if (!(o instanceof IPlayerCharacter)) {
      return false;
    }
    final IPlayerCharacter otherPCharacter = (IPlayerCharacter) o;
    return this.getName().equals(otherPCharacter.getName()) &&
           this.getCharacterClass() == otherPCharacter.getCharacterClass() &&
           this.getDefensePoints() == otherPCharacter.getDefensePoints();
  }

  @Override
  public int hashCode() {
    return Objects.hash(getName(), getCharacterClass(), getDefensePoints());
  }
}
