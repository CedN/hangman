package cna.apps.hangman.adapters.proposal;

import java.util.UUID;

import cna.apps.hangman.domain.ports.proposal.ProposeLetterInputBoundary;
import cna.apps.hangman.domain.usecases.UnknownGameException;

public class LetterProposalController {

  private final ProposeLetterInputBoundary usecase;

  public LetterProposalController(ProposeLetterInputBoundary usecase) {
    this.usecase = usecase;
  }

  public void proposeLetter(String gameId, String letter) {
    try {
      usecase.tryLetter(UUID.fromString(gameId), letter.charAt(0));
    } catch (UnknownGameException e) {
      e.printStackTrace();
    }
  }

}
