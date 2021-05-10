package cna.apps.hangman.domain.ports.proposal;

import cna.apps.hangman.domain.entities.WordToGuess;

public record LostGame(String message, WordToGuess wordToGuess, int hangmanStep, String mask) {
}
