package org.example.domainmodel;

public class TryToOpenAction extends Action {
    private final Character character;

    public TryToOpenAction(Character character, String object) {
        this.character = character;
    }

    @Override
    public void execute() {
        character.setTriedToOpen(true);
    }
}
