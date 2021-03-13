package cna.apps.hangman.domain;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import cna.apps.hangman.provider.WordList;

public class GameShould {
  
  @Test
  void choose_word() {
    Game game = new Game(new WordList("hangman"));
    assertNotNull(game.chooseWord());
  }  

}
