package com.ba.boost.reflection;

public class Bird extends Animal{

    private boolean walks;

    public Bird() {
        super("Bird");
    }


    public Bird(boolean walks) {
        super("Nice Bird");
        this.walks = walks;
    }

    public Bird(String name, boolean walks) {
        super(name);
        this.walks = walks;
    }

    private String secretMethod() {
        return null;
    }

    @Override
    public String getSounds() {
        return null;
    }

    @Override
    public void eat() {

    }

    public boolean isWalks() {
        return walks;
    }

    public void setWalks(boolean walks) {
        this.walks = walks;
    }
}
