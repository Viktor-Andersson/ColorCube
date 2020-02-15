package com.androidgame.state;

import com.badlogic.gdx.graphics.g2d.Batch;

public abstract class State {

    protected abstract void userInput();
    public abstract void update();
    public abstract void render(Batch batch);
    public abstract void dispose();

}
