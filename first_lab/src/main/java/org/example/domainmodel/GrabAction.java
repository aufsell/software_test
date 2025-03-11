package org.example.domainmodel;

public class GrabAction extends Action {
    private final Character character;

    public GrabAction(Character character, String object) {
        this.character = character;
    }

    @Override
    public void execute() {
        character.setGrabbed(true);
    }
}