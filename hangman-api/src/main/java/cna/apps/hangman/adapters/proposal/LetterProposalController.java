/*
LetterProposalController.java
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
package cna.apps.hangman.adapters.proposal;

import static cna.apps.hangman.adapters.proposal.Errors.UNKNOWN_GAME;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cna.apps.hangman.domain.ports.proposal.ProposeLetterInputBoundary;
import cna.apps.hangman.domain.usecases.UnknownGameException;
import cna.apps.hangman.tech.BadRequestException;

public class LetterProposalController {

  private static final Logger LOGGER = LoggerFactory.getLogger(LetterProposalController.class);

  private final ProposeLetterInputBoundary usecase;

  public LetterProposalController(ProposeLetterInputBoundary usecase) {
    this.usecase = usecase;
  }

  public void proposeLetter(String gameId, String letter) {
    LOGGER.info("Try with the letter = '{}'", letter);
    try {
      usecase.tryLetter(UUID.fromString(gameId), letter.charAt(0));
    } catch (UnknownGameException e) {
      throw new BadRequestException(UNKNOWN_GAME.code(), UNKNOWN_GAME.message());
    }
  }

}
