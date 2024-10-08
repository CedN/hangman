/*
Errors.java
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

import static cna.apps.hangman.adapters.proposal.Errors.MessageKey.DEFAULT_MESSAGE_KEY;

import java.util.Map;
import java.util.Optional;

public enum Errors {
  GAME_OVER(4001, Map.of(MessageKey.WON_MESSAGE_KEY, "The game is over! You won the game :)",
      MessageKey.LOST_MESSAGE_KEY, "The game is over! You lost the game :(")),
  UNKNOWN_GAME(4002, Map.of(DEFAULT_MESSAGE_KEY, "The game ID does not match any game"));

  /**
   * Message par défaut.
   */
  private static final String DEFAULT_MESSAGE = "No information!";
  private final int code;
  private Map<MessageKey, String> messages;

  Errors(int code, Map<MessageKey, String> messages) {
    this.code = code;
    this.messages = messages;
  }

  public int code() {
    return code;
  }

  public String message() {
    return message(DEFAULT_MESSAGE_KEY);
  }

  public String message(MessageKey messageKey) {
    return Optional.of(messages.get(messageKey)).orElse(DEFAULT_MESSAGE);
  }

  public static enum MessageKey {
    WON_MESSAGE_KEY, LOST_MESSAGE_KEY, DEFAULT_MESSAGE_KEY;
  }

}
