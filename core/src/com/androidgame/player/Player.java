package com.androidgame.player;

import com.badlogic.gdx.Gdx;

import static com.androidgame.state.StateHandler.BLOCK_SIZE;

public class Player {

    private final static int SPEED = 15;

    private int xPos;
    private int yPos;

    private PlayerColor color;


    public Player(int x, int y){
        color = PlayerColor.PLAYER_BLUE;
       this.xPos = x;
       this.yPos = y;

    }

    public void update(){
        yPos += SPEED * (1- Gdx.graphics.getRawDeltaTime());
    }

    public PlayerColor getPlayerColor (){
        return color;
    }

    public void setColor(PlayerColor color) {
        this.color = color;
    }

    public int getYPos() {
        return yPos;
    }
    public int getXPos() { return xPos; }

    public void moveRight(){xPos += BLOCK_SIZE;}

    public void moveLeft(){xPos -= BLOCK_SIZE;}
}
