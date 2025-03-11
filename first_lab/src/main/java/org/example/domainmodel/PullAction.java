package org.example.domainmodel;

public class PullAction extends Action {
    private final Character character;

    public PullAction(Character character, String object) {
        this.character = character;
    }

    @Override
    public void execute() {
        character.setPulled(true);
    }
}