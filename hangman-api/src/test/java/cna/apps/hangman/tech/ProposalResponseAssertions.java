package cna.apps.hangman.tech;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cna.apps.hangman.api.ProposalResponse;
import cna.apps.hangman.api.ProposalResponse.GameStateEnum;

public class ProposalResponseAssertions {

  public static void assertProposalResponse(ProposalResponse proposalResponse, String expectedMask, int expectedHangmanStep,
      GameStateEnum expectedGameState, String expectedMessage) {
    assertEquals(expectedGameState, proposalResponse.getGameState());
    assertEquals(expectedHangmanStep, proposalResponse.getHangmanStep());
    assertEquals(expectedMask, proposalResponse.getMaskedWord());
    assertEquals(expectedMessage, proposalResponse.getMessage());
  }
  
}
