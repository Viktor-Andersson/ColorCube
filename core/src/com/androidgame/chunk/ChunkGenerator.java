package com.androidgame.chunk;
/**
 *  Reads random chunks and draws them on the screen
 *
 * */

import com.badlogic.gdx.graphics.g2d.Batch;
import java.util.Random;
import static com.androidgame.chunk.ChunkReader.NUMBER_OF_CHUNKS;
import static com.androidgame.chunk.ChunkReader.ROWS;
import static com.androidgame.state.StateHandler.BLOCK_SIZE;

public class ChunkGenerator {

    public static int chunkCalculator = 0;
    private  int y;
    private ChunkReader chunk;
    private Random rand = new Random();
    private BackgroundBlock[][] currentChunk;


    public ChunkGenerator(int y){
        this.y = y;
        chunk = new ChunkReader();
        randomChunk();

    }

    public void randomChunk(){

        if (chunkCalculator == 0){
            currentChunk = chunk.getScenario(0);
        }
        else{
            //Generates a new random chunk
            int n = rand.nextInt(NUMBER_OF_CHUNKS - 1) + 1;
            currentChunk = chunk.getScenario(n);
        }

        chunkCalculator += 1;

    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void render(Batch batch){
        /*
        *  Draws the current chunk on the screen
        * */

        for(int i = 0; i < ROWS; i++) {

            if(currentChunk[i][0] != BackgroundBlock.WHITE && currentChunk[i][0] != null){
                batch.draw((currentChunk[i][0].background),0,
                        BLOCK_SIZE*((ROWS-1)-i) + y, BLOCK_SIZE, BLOCK_SIZE);}
            if(currentChunk[i][1] != BackgroundBlock.WHITE && currentChunk[i][1] != null){
                batch.draw((currentChunk[i][1].background), BLOCK_SIZE,
                        BLOCK_SIZE*((ROWS-1)-i) + y, BLOCK_SIZE,BLOCK_SIZE);}

            if(currentChunk[i][2] != BackgroundBlock.WHITE && currentChunk[i][2] != null) {
                batch.draw((currentChunk[i][2].background), BLOCK_SIZE * 2,
                        BLOCK_SIZE*((ROWS-1)-i) + y, BLOCK_SIZE, BLOCK_SIZE);
            }
        }
    }

    public BackgroundBlock getBackground(int backgroundPosX, int backgroundPosY){
        /*
        * Calculates what type of BackgroundBlock that exists at the coordinates(backgroundPosX,backgroundPosY) 
        * */

        int yPos = (backgroundPosY - y)/ BLOCK_SIZE;

        //yPos can not be negative
        if (yPos < 0 ){
            yPos *= -1;
        }
        if (yPos >= ROWS){
            yPos = (ROWS-1);
        }

        return currentChunk[(ROWS-1) - yPos][backgroundPosX/ BLOCK_SIZE];
    }

    public void dispose(){

    }

}
