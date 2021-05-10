package cna.apps.hangman.domain.usecases.create;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import cna.apps.hangman.domain.ports.create.NewGame;

public class NewGameAssertions {
  
  public static void assertNewGame(NewGame newGame, String expectedMask) {
    assertNotNull(newGame.id());
    assertEquals(expectedMask, newGame.mask());
  }
  
  public static void assertDifferentNewGames(NewGame newGame, String expectedMask, NewGame otherNewGame, String otherExpectedMask) {
    assertNewGame(newGame, expectedMask);
    assertNewGame(otherNewGame, otherExpectedMask);
    assertNotEquals(newGame.id(), otherNewGame.id());
  }
  
}
