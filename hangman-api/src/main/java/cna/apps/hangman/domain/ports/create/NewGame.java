package cna.apps.hangman.domain.ports.create;

public class NewGame {

  private final String id;
  private final String mask;

  public NewGame(String id, String mask) {
    this.id = id;
    this.mask = mask;
  }

	public String getId() {
		return id;
	}

  public Object getMask() {
    return mask;
  }

}
