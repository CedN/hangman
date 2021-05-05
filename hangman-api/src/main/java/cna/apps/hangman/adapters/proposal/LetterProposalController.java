package cna.apps.hangman.adapters.proposal;

import java.util.UUID;

import cna.apps.hangman.domain.ports.proposal.ProposeLetterInputBoundary;

public class LetterProposalController {

  private final ProposeLetterInputBoundary usecase;

  public LetterProposalController(ProposeLetterInputBoundary usecase) {
    this.usecase = usecase;
  }

  public void proposeLetter(String gameId, String letter) {
    usecase.tryLetter(UUID.fromString(gameId), letter.charAt(0));
  }

}
