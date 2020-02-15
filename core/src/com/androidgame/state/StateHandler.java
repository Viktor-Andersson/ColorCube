package com.androidgame.state;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class StateHandler extends ApplicationAdapter {
    private SpriteBatch batch;

    private State currentState;

    public static int WIDTH, HEIGHT, BLOCK_SIZE;

    @Override
    public void create() {
        WIDTH = Gdx.graphics.getWidth();
        HEIGHT = Gdx.graphics.getHeight();
        BLOCK_SIZE = WIDTH / 3;
        currentState = new PauseState(this);


        batch = new SpriteBatch();

    }


    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        currentState.update();
        currentState.render(batch);
    }

    @Override
    public void dispose() {
        currentState.dispose();
    }

    public void setCurrentState(State currentState) {
       this.currentState = currentState;
    }
}
