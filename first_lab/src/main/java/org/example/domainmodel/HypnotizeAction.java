package org.example.domainmodel;

public class HypnotizeAction extends Action {
    private final Character character;

    public HypnotizeAction(Character character) {
        this.character = character;
    }

    @Override
    public void execute() {
        character.setHypnotized(true);
    }
}
