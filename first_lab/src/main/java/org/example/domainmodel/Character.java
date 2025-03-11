package org.example.domainmodel;

public class Character {
    private boolean grabbed = false;
    private boolean pulled = false;
    private boolean triedToOpen = false;
    private boolean hypnotized = false;

    public Character(String name) {
    }

    public boolean isGrabbed() {
        return grabbed;
    }

    public void setGrabbed(boolean grabbed) {
        this.grabbed = grabbed;
    }

    public boolean isPulled() {
        return pulled;
    }

    public void setPulled(boolean pulled) {
        this.pulled = pulled;
    }

    public boolean isTriedToOpen() {
        return triedToOpen;
    }

    public void setTriedToOpen(boolean triedToOpen) {
        this.triedToOpen = triedToOpen;
    }

    public boolean isHypnotized() {
        return hypnotized;
    }

    public void setHypnotized(boolean hypnotized) {
        this.hypnotized = hypnotized;
    }
}
