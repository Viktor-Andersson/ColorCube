package com.androidgame.chunk;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import com.androidgame.player.PlayerColor;

public enum BackgroundBlock {

    BLUE(new Texture(Gdx.files.internal("images/background/blue.png"),true),
            PlayerColor.PLAYER_BLUE,false),

    RED(new Texture(Gdx.files.internal("images/background/red.png"),true),
            PlayerColor.PLAYER_RED,false),

    YELLOW(new Texture(Gdx.files.internal("images/background/yellow.png"),true),
            PlayerColor.PLAYER_YELLOW,false),

    WHITE(new Texture(Gdx.files.internal("images/background/yellow.png"),true),null, false),

    SPLASH_BLUE(new Texture(Gdx.files.internal("images/background/blue_splash.png"),true),
            PlayerColor.PLAYER_BLUE,true),

    SPLASH_RED(new Texture(Gdx.files.internal("images/background/red_splash.png"),true),
            PlayerColor.PLAYER_RED,true),

    SPLASH_YELLOW(new Texture(Gdx.files.internal("images/background/yellow_splash.png"),true),
            PlayerColor.PLAYER_YELLOW,true);

    final public Texture background;
    final public PlayerColor playerColor;
    final public Boolean changeable;

    BackgroundBlock(Texture background,PlayerColor playerColor,boolean changeable){
        this.background = background;
        this.playerColor = playerColor;
        this.changeable = changeable;

       // background.setFilter(Texture.TextureFilter.MipMapLinearNearest, Texture.TextureFilter.Nearest);
    }

   public PlayerColor changeColor(){
        if (changeable){
            return playerColor;
        }
        return null;
    }

}
