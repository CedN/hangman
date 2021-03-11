package cna.apps.hangman.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
}
