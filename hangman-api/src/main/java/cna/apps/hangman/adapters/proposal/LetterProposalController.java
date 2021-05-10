package cna.apps.hangman.adapters.proposal;

import static cna.apps.hangman.adapters.proposal.Errors.UNKNOWN_GAME;

import java.util.UUID;

import cna.apps.hangman.domain.ports.proposal.ProposeLetterInputBoundary;
import cna.apps.hangman.domain.usecases.UnknownGameException;
import cna.apps.hangman.tech.BadRequestException;

public class LetterProposalController {

  private final ProposeLetterInputBoundary usecase;

  public LetterProposalController(ProposeLetterInputBoundary usecase) {
    this.usecase = usecase;
  }

  public void proposeLetter(String gameId, String letter) {
    try {
      usecase.tryLetter(UUID.fromString(gameId), letter.charAt(0));
    } catch (UnknownGameException e) {
      throw new BadRequestException(UNKNOWN_GAME.code(), UNKNOWN_GAME.message());
    }
  }

}
