package cna.apps.hangman.domain.ports.create;

import java.util.UUID;

public record NewGame(UUID id, String mask) {
}
