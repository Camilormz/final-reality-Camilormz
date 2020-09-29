package com.github.camilormz.finalreality.model.character;

import com.github.camilormz.finalreality.model.character.player.CharacterClass;
import com.github.camilormz.finalreality.model.character.player.PlayerCharacter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// TODO: The design of this test is probably broken and must be reviewed and fixed ASAP

class EnemyTest extends AbstractCharacterTest {

  private static final String ENEMY_NAME = "Goblin";

  @BeforeEach
  void setUp() {
    basicSetUp();
    testCharacters.add(new Enemy(ENEMY_NAME, 10, turns));
  }

  @Test
  void constructorTest() {
    checkConstruction(new Enemy(ENEMY_NAME, 10, turns),
        testCharacters.get(0),
        new Enemy(ENEMY_NAME, 11, turns),
        new PlayerCharacter(ENEMY_NAME, turns, CharacterClass.THIEF));
  }
}