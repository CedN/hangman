package cna.apps.hangman.domain.usecases;

import cna.apps.hangman.domain.entities.HangmanGame;
import cna.apps.hangman.domain.ports.proposal.LetterProposalOutputBoundary;

@FunctionalInterface
interface PresenterCall {
  public void apply(LetterProposalOutputBoundary presenter, HangmanGame hangmanGame, String message);
}