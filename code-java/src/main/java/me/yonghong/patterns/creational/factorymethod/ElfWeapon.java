package me.yonghong.patterns.creational.factorymethod;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author yonghongwang#163.com
 * @since 2021/8/6
 */
@RequiredArgsConstructor
@Getter
public class ElfWeapon implements Weapon {

  private final WeaponType weaponType;

  @Override
  public String toString() {
    return "an elven " + weaponType;
  }
}
