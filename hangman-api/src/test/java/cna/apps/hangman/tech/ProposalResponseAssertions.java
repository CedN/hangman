/*
ProposalResponseAssertions.java
Copyright 2021 @CedN

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/
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
