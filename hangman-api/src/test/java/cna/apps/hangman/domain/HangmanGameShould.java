package cna.apps.hangman.domain;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import cna.apps.hangman.provider.WordList;

public class HangmanGameShould {
  
  @Test
  void start_new_game() {
    HangmanGame hangmanGame = new HangmanGame(new WordList("hangman"));
    assertNotNull(hangmanGame.startNewGame());
  }  

}
