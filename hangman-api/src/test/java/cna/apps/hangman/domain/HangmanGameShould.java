package cna.apps.hangman.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class HangmanGameShould {

  @Test
  void return_mask_for_hello_word_with_empty_hangman() {
    String wordToGuess = "hello";
    HangmanGame word = new HangmanGame(wordToGuess);
    assertEquals("-----", word.getMask());
    assertTrue(word.getHangMan().isEmpty());
  }

  @Test
  void return_mask_for_hangman_word_with_empty_hangman() {
    String wordToGuess = "hangman";
    HangmanGame word = new HangmanGame(wordToGuess);
    assertEquals("-------", word.getMask());
    assertTrue(word.getHangMan().isEmpty());
  }

  @Test
  void return_mask_with_letter_h_for_hangman_word_when_try_with_letter_h() throws Exception {
    String wordToGuess = "hangman";
    HangmanGame word = new HangmanGame(wordToGuess);
    word.tryLetter('h');
    assertEquals("h------", word.getMask());
  }

  @Test
  void return_mask_with_all_letter_a_for_hangman_word_when_try_with_letter_a() throws Exception {
    String wordToGuess = "hangman";
    HangmanGame word = new HangmanGame(wordToGuess);
    word.tryLetter('a');
    assertEquals("-a---a-", word.getMask());
  }

  @Test
  void let_the_mask_unchanged_when_try_letter_z_for_hangman_word_with_step_one_of_hangman() throws Exception {
    String wordToGuess = "hangman";
    HangmanGame word = new HangmanGame(wordToGuess);
    String maskedWord = word.getMask();
    word.tryLetter('z');
    assertEquals(maskedWord, word.getMask());
    assertFalse(word.getHangMan().isEmpty());
    assertEquals(1, word.getHangMan().getStep());
  }

  @Test
  void return_true_if_word_to_guess_is_hangman_and_the_proposal_is_hangman_too() throws Exception {
    String wordToGuess = "hangman";
    HangmanGame word = new HangmanGame(wordToGuess);
    String proposal = wordToGuess;
    assertTrue(word.isTheGoodWord(proposal));
  }

  @Test
  void return_false_and_hangman_at_step_one_if_word_to_guess_is_hangman_while_the_proposal_is_not_hangman() throws Exception {
    String wordToGuess = "hangman";
    HangmanGame word = new HangmanGame(wordToGuess);
    String proposal = "hello";
    assertFalse(word.isTheGoodWord(proposal));
    assertEquals(1, word.getHangMan().getStep());
  }

  @Test
  void return_true_if_word_to_guess_is_hello_and_the_proposal_is_hello_too() throws Exception {
    String wordToGuess = "hello";
    HangmanGame word = new HangmanGame(wordToGuess);
    String proposal = wordToGuess;
    assertTrue(word.isTheGoodWord(proposal));
  }

  @Test
  void throw_GameOverException_at_the_7th_letter_failure_with_hangman_at_step_7() throws Exception {
    String wordToGuess = "hangman";
    HangmanGame word = new HangmanGame(wordToGuess);
    word.tryLetter('q');
    word.tryLetter('w');
    word.tryLetter('e');
    word.tryLetter('r');
    word.tryLetter('t');
    word.tryLetter('y');
    GameOverException exception = assertThrows(GameOverException.class, () -> word.tryLetter('u'));
    assertEquals("hangman", exception.getWordToGuess());
    assertEquals(7, word.getHangMan().getStep());
  }

  @Test
  void throw_GameOverException_when_propose_wrong_word_after_6_wrong_letters() throws Exception {
    String wordToGuess = "hangman";
    HangmanGame word = new HangmanGame(wordToGuess);
    word.tryLetter('q');
    word.tryLetter('w');
    word.tryLetter('e');
    word.tryLetter('r');
    word.tryLetter('t');
    word.tryLetter('y');
    GameOverException exception = assertThrows(GameOverException.class, () -> word.isTheGoodWord("hello"));
    assertEquals("hangman", exception.getWordToGuess());
    assertEquals(7, word.getHangMan().getStep());
  }

  @Test
  void throw_GameAlreadyWonException_when_word_to_guess_has_been_discovered_and_try_a_letter() throws Exception {
    String wordToGuess = "hangman";
    HangmanGame word = new HangmanGame(wordToGuess);
    word.tryLetter('h');
    word.tryLetter('a');
    word.tryLetter('n');
    word.tryLetter('g');
    word.tryLetter('m');
    assertThrows(GameAlreadyWonException.class, () -> word.tryLetter('a'));
  }

  @Test
  void throw_GameAlreadyWonException_when_word_to_guess_has_been_discovered_and_propose_a_word() throws Exception {
    String wordToGuess = "hangman";
    HangmanGame word = new HangmanGame(wordToGuess);
    word.tryLetter('h');
    word.tryLetter('a');
    word.tryLetter('n');
    word.tryLetter('g');
    word.tryLetter('m');
    assertThrows(GameAlreadyWonException.class, () -> word.isTheGoodWord("hangman"));
  }
}
