package cna.apps.hangman.domain.ports.proposal;

import java.util.UUID;

public interface ProposeLetterInputBoundary {

	void tryLetter(UUID gameId, char c);

}
