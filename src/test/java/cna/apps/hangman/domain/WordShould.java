package cna.apps.hangman.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class WordShould {

    @Test
    void return_mask_for_hello_word() {
        String wordToGuess = "hello";
        String mask = new Word(wordToGuess).getMask();
        assertEquals("-----", mask);
    }

    @Test
    void return_mask_for_hangman_word() {
        String wordToGuess = "hangman";
        String mask = new Word(wordToGuess).getMask();
        assertEquals("-------", mask);
    }

    @Test
    void return_mask_with_letter_h_for_hangman_word_when_try_with_letter_h() throws BadLetterException {
        String wordToGuess = "hangman";
        Word word =  new Word(wordToGuess);
        word.tryLetter('h');
        System.out.println(word.getMask());
        assertEquals("h------", word.getMask());
    }

    @Test
    void return_mask_with_all_letter_a_for_hangman_word_when_try_with_letter_a() throws BadLetterException {
        String wordToGuess = "hangman";
        Word word =  new Word(wordToGuess);
        word.tryLetter('a');
        assertEquals("-a---a-", word.getMask());
    }

    @Test
    void throw_BadLetterException_when_try_letter_z_for_hangman_letter() {
        String wordToGuess = "hangman";
        Word word =  new Word(wordToGuess);
        assertThrows(BadLetterException.class, () -> word.tryLetter('z'));
    }

    @Test
    void return_true_if_word_to_guess_is_hangman_and_the_proposal_is_hangman_too() {
        String wordToGuess = "hangman";
        Word word = new Word(wordToGuess);
        String proposal = wordToGuess;
        assertTrue(word.isTheGoodWord(proposal));
    }

    @Test
    void return_false_if_word_to_guess_is_hangman_and_the_proposal_is_not_hangman() {
        String wordToGuess = "hangman";
        Word word = new Word(wordToGuess);
        String proposal = "not hangman";
        assertFalse(word.isTheGoodWord(proposal));
    }

    @Test
    void return_true_if_word_to_guess_is_hello_and_the_proposal_is_hello_too() {
        String wordToGuess = "hello";
        Word word = new Word(wordToGuess);
        String proposal = wordToGuess;
        assertTrue(word.isTheGoodWord(proposal));
    }
}
