package com.github.camilormz.finalreality.model.character;

import org.junit.jupiter.api.BeforeEach;

// TODO: This test is broken and must be fixed ASAP

class EnemyTest extends AbstractCharacterTest {

  private static final String ENEMY_NAME = "Goblin";

  @BeforeEach
  void setUp() {
    basicSetUp();
    testCharacters.add(new Enemy(ENEMY_NAME, 10, turns));
  }
}