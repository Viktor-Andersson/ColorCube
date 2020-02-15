package com.androidgame.player;

import com.badlogic.gdx.graphics.Texture;

public enum PlayerColor {

    PLAYER_RED(new Texture("images/player/red_brick.png")),
    PLAYER_BLUE(new Texture("images/player/blue_brick.png")),
    PLAYER_YELLOW(new Texture("images/player/yellow_brick.png")),
    NONE(new Texture("images/player/red_brick.png"));

    final public Texture texture;

    PlayerColor(Texture texture){
        this.texture = texture;
    }
}
