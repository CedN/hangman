package cna.apps.hangman.domain;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import cna.apps.hangman.provider.WordList;

public class HangmanGameShould {
  
  @Test
  void choose_word() {
    HangmanGame game = new HangmanGame(new WordList("hangman"));
    assertNotNull(game.chooseWord());
  }  

}
