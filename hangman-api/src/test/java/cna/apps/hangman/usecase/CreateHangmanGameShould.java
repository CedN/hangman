package cna.apps.hangman.usecase;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import cna.apps.hangman.provider.WordList;

public class CreateHangmanGameShould {
  
  @Test
  void start_new_game() {
    CreateHangmanGame hangmanGame = new CreateHangmanGame(new WordList("hangman"));
    assertNotNull(hangmanGame.startNewGame());
  }

}
