package cna.apps.hangman.domain.usecases.proposal;

import cna.apps.hangman.domain.ports.proposal.GameOver;
import cna.apps.hangman.domain.ports.proposal.LetterProposalOutputBoundary;
import cna.apps.hangman.domain.ports.proposal.ProgressingGame;
import cna.apps.hangman.domain.ports.proposal.LostGame;
import cna.apps.hangman.domain.ports.proposal.WonGame;

public class LetterProposalPresenterSpy implements LetterProposalOutputBoundary {

	private ProgressingGame progessingGame;
	private LostGame lostGame;
	private WonGame wonGame;
	private GameOver gameOver;

	@Override
	public void gameInProgress(ProgressingGame progessingGame) {
		this.progessingGame = progessingGame;
	}

	public ProgressingGame getProgressingGame() {
		return progessingGame;
	}

	@Override
	public void lostGame(LostGame lostGame) {
		this.lostGame = lostGame;
	}

	public LostGame getLostGame() {
		return lostGame;
	}

	@Override
	public void wonGame(WonGame wonGame) {
		this.wonGame = wonGame;
	}

	public WonGame getWonGame() {
		return wonGame;
	}

	@Override
	public void gameIsOver(GameOver gameOver) {
		this.gameOver = gameOver;
	}

	public GameOver getGameOver() {
		return gameOver;
	}

}
