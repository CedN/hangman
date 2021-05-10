package cna.apps.hangman.domain.ports.proposal;

import java.util.UUID;

import cna.apps.hangman.domain.usecases.UnknownGameException;

public interface ProposeLetterInputBoundary {

	void tryLetter(UUID gameId, char c) throws UnknownGameException;

}
