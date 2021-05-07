package cna.apps.hangman.adapters.proposal;

import java.util.Map;
import java.util.Optional;

public enum Errors {
  GAME_OVER(4001, Map.of(MessageKey.WON_MESSAGE_KEY, "The game is over! You won the game :)",
      MessageKey.LOST_MESSAGE_KEY, "The game is over! You lost the game :("));

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

  public String message(MessageKey messageKey) {
    return Optional.of(messages.get(messageKey)).orElse(DEFAULT_MESSAGE);
  }

  public static enum MessageKey {
    WON_MESSAGE_KEY, LOST_MESSAGE_KEY;
  }

}
