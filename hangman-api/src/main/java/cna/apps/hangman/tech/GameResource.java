package cna.apps.hangman.tech;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import cna.apps.hangman.adapters.creation.HangmanGameCreatedPresenter;
import cna.apps.hangman.adapters.creation.HangmanGameCreationController;
import cna.apps.hangman.adapters.proposal.LetterProposalController;
import cna.apps.hangman.adapters.proposal.LetterProposedPresenter;
import cna.apps.hangman.api.GameApi;
import cna.apps.hangman.api.NewGameResponse;
import cna.apps.hangman.api.ProposalResponse;

@RestController
public class GameResource implements GameApi {

  private final HangmanGameCreationController hangmanGameCreationController;
  private final HangmanGameCreatedPresenter hangmanGameCreatedPresenter;
  private final LetterProposedPresenter letterProposedPresenter;
  private final LetterProposalController letterProposalController;

  public GameResource(HangmanGameCreationController hangmanGameCreationController, HangmanGameCreatedPresenter hangmanGameCreatedPresenter, LetterProposalController letterProposalController, LetterProposedPresenter letterProposedPresenter) {
    this.hangmanGameCreationController = hangmanGameCreationController;
    this.hangmanGameCreatedPresenter = hangmanGameCreatedPresenter;
    this.letterProposalController = letterProposalController;
    this.letterProposedPresenter = letterProposedPresenter;
  }

  @Override
  public ResponseEntity<NewGameResponse> createGame() {
    hangmanGameCreationController.createNewGame();
    return ResponseEntity.ok(hangmanGameCreatedPresenter.getNewGameResponse());
  }

  @Override
  public ResponseEntity<ProposalResponse> proposeLetter(String gameId, String letter) {
    letterProposalController.proposeLetter(gameId, letter);
    return ResponseEntity.ok(letterProposedPresenter.getProposalResponse());
  }
  
}
