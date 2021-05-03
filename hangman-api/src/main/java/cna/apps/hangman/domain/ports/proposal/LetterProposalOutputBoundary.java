package cna.apps.hangman.domain.ports.proposal;

public interface LetterProposalOutputBoundary {

	void gameInProgress(ProgressingGame letterProposalResult);

	void lostGame(LostGame lostGame);

	void wonGame(WonGame wonGame);

	void gameIsOver(GameOver gameOver);

}
