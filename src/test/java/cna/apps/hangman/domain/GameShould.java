package cna.apps.hangman.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import cna.apps.hangman.provider.WordList;

public class GameShould {
  
  @Test
  void mask_the_word_hello_and_create_empty_hangman() {
    WordProvider wordProvider = new WordList("hello");
    Game game = new Game(wordProvider);
    Word word = game.getWord();
    assertEquals("-----", word.getMask());
    Hangman hangman = game.getHangman();
    assertTrue(hangman.isEmpty());
  }

  @Test
  void mask_the_word_hangman_and_create_empty_hangman() {
    WordProvider wordProvider = new WordList("hangman");
    Game game = new Game(wordProvider);
    Word word = game.getWord();
    assertEquals("-------", word.getMask());
    assertTrue(game.getHangman().isEmpty());
  }

  @Test
  void unmask_letter_a_from_the_word_hangman_and_keep_the_hangman_empty() throws BadLetterException {
    WordProvider wordProvider = new WordList("hangman");
    Game game = new Game(wordProvider);
    Word word = game.getWord();
    word.tryLetter('a');
    assertEquals("-a---a-", word.getMask());
    assertTrue(game.getHangman().isEmpty());
  }

  @Test
  void unmask_letter_a_and_m_from_the_word_hangman_and_keep_the_hangman_empty() throws BadLetterException {
    WordProvider wordProvider = new WordList("hangman");
    Game game = new Game(wordProvider);
    Word word = game.getWord();
    word.tryLetter('a');
    word.tryLetter('m');
    assertEquals("-a--ma-", word.getMask());
    assertTrue(game.getHangman().isEmpty());
  }

}
