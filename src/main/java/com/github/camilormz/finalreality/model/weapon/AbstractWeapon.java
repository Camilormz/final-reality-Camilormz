package com.github.camilormz.finalreality.model.weapon;

import com.github.camilormz.finalreality.model.character.player.IPlayerCharacter;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * A class that holds all the information of a weapon.
 *
 * @author Ignacio Slater Muñoz.
 * @author Camilo Ramírez Canales.
 */
public abstract class AbstractWeapon implements IWeapon {

  private final String name;
  private final int damage;
  private final int weight;
  private final WeaponType type;
  private IPlayerCharacter holder;

  /**
   * Creates a weapon.
   *
   * @param name
   *     the weapon's name
   * @param damage
   *     the damage the weapon is allowed to do
   * @param weight
   *     the weight of the weapon
   * @param type
   *     the type of the weapon
   *     @see WeaponType
   */
  public AbstractWeapon(@NotNull final String name, final int damage, final int weight,
                        @NotNull final WeaponType type) {
    this.name = name;
    this.damage = damage;
    this.weight = weight;
    this.type = type;
    this.holder = null;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public int getDamage() {
    return damage;
  }

  @Override
  public int getWeight() {
    return weight;
  }

  @Override
  public WeaponType getType() {
    return type;
  }

  @Override
  public IPlayerCharacter getHolder() {
    return this.holder;
  }

  @Override
  public boolean isAvailable() {
    return this.holder == null;
  }

  @Override
  public void beHeld(@NotNull IPlayerCharacter playerCharacter) {
    this.holder = playerCharacter;
    assert playerCharacter.getEquippedWeapon() == this;
  }

  @Override
  public void beUnHeld() {
    this.holder = null;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof IWeapon)) {
      return false;
    }
    final IWeapon weapon = (IWeapon) o;
    return this.getName().equals(weapon.getName()) &&
           this.getDamage() == weapon.getDamage() &&
           this.getWeight() == weapon.getWeight() &&
           this.getType() == weapon.getType();
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.getName(), this.getDamage(), this.getWeight(), this.getType());
  }
}
