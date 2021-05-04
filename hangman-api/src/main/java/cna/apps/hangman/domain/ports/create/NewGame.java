package cna.apps.hangman.domain.ports.create;

import java.util.UUID;

public class NewGame {

  private final UUID id;
  private final String mask;

  public NewGame(UUID id, String mask) {
    this.id = id;
    this.mask = mask;
  }

	public UUID getId() {
		return id;
	}

  public String getMask() {
    return mask;
  }

}
