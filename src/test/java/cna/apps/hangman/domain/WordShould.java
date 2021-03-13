package cna.apps.hangman.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class WordShould {

  @Test
  void return_mask_for_hello_word_with_empty_hangman() {
    String wordToGuess = "hello";
    Word word = new Word(wordToGuess);
    assertEquals("-----", word.getMask());
    assertTrue(word.getHangMan().isEmpty());
  }

  @Test
  void return_mask_for_hangman_word_with_empty_hangman() {
    String wordToGuess = "hangman";
    Word word = new Word(wordToGuess);
    assertEquals("-------", word.getMask());
    assertTrue(word.getHangMan().isEmpty());
  }

  @Test
  void return_mask_with_letter_h_for_hangman_word_when_try_with_letter_h() throws GameOverException {
    String wordToGuess = "hangman";
    Word word = new Word(wordToGuess);
    word.tryLetter('h');
    assertEquals("h------", word.getMask());
  }

  @Test
  void return_mask_with_all_letter_a_for_hangman_word_when_try_with_letter_a() throws GameOverException {
    String wordToGuess = "hangman";
    Word word = new Word(wordToGuess);
    word.tryLetter('a');
    assertEquals("-a---a-", word.getMask());
  }

  @Test
  void let_the_mask_unchanged_when_try_letter_z_for_hangman_word_with_step_one_of_hangman() throws GameOverException {
    String wordToGuess = "hangman";
    Word word = new Word(wordToGuess);
    String maskedWord = word.getMask();
    word.tryLetter('z');
    assertEquals(maskedWord, word.getMask());
    assertEquals(1, word.getHangMan().getStep());
  }

  @Test
  void return_true_if_word_to_guess_is_hangman_and_the_proposal_is_hangman_too() throws GameOverException {
    String wordToGuess = "hangman";
    Word word = new Word(wordToGuess);
    String proposal = wordToGuess;
    assertTrue(word.isTheGoodWord(proposal));
  }

  @Test
  void return_false_and_hangman_at_step_one_if_word_to_guess_is_hangman_while_the_proposal_is_not_hangman() throws GameOverException {
    String wordToGuess = "hangman";
    Word word = new Word(wordToGuess);
    String proposal = "not hangman";
    assertFalse(word.isTheGoodWord(proposal));
    assertEquals(1, word.getHangMan().getStep());
  }

  @Test
  void return_true_if_word_to_guess_is_hello_and_the_proposal_is_hello_too() throws GameOverException {
    String wordToGuess = "hello";
    Word word = new Word(wordToGuess);
    String proposal = wordToGuess;
    assertTrue(word.isTheGoodWord(proposal));
  }

  @Test
  void throw_game_over_exception_at_the_7th_letter_failure_with_hangman_at_step_7() throws GameOverException {
    String wordToGuess = "hangman";
    Word word = new Word(wordToGuess);
    word.tryLetter('q');
    word.tryLetter('w');
    word.tryLetter('e');
    word.tryLetter('r');
    word.tryLetter('t');
    word.tryLetter('y');
    assertThrows(GameOverException.class, () -> word.tryLetter('u'));
    assertEquals(7, word.getHangMan().getStep());
  }
}
