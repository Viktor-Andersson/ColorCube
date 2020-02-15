package com.androidgame.chunk;

/**
 * A com.androidgame.chunk is a piece of the game map with a 9x3 grid filled with BackgroundBlocks.
 *
 * Each com.androidgame.chunk can be loaded from a com.androidgame.chunk.data file and has the following format:
 *
 * BackgroundBlock|BackgroundBlock|BackgroundBlock|
 * BackgroundBlock|BackgroundBlock|BackgroundBlock|
 * BackgroundBlock|BackgroundBlock|BackgroundBlock|
 * BackgroundBlock|BackgroundBlock|BackgroundBlock|
 * BackgroundBlock|BackgroundBlock|BackgroundBlock|
 * BackgroundBlock|BackgroundBlock|BackgroundBlock|
 * BackgroundBlock|BackgroundBlock|BackgroundBlock|
 * BackgroundBlock|BackgroundBlock|BackgroundBlock|
 * BackgroundBlock|BackgroundBlock|BackgroundBlock|
 *
 * To add a com.androidgame.chunk simply create a new com.androidgame.chunk.data file and save it in  android/assets/data
 * name the file com.androidgame.chunk(NextNumberThatDoesNotExist).data
 * */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;

public class ChunkReader {
    //Numbers of rows in a com.androidgame.chunk
    public static int ROWS = 9;

    private static int LEFT_BLOCK = 0;
    private static int MIDDLE_BLOCK = 1;
    private static int RIGHT_BLOCK = 2;

    private static int GRID_WIDTH = 3;
    private static int GRID_HEIGHT = ROWS;

    private static final String PATH = "data/";
    public static final int NUMBER_OF_CHUNKS = 26;

    BackgroundBlock[][][] chunkArray = new BackgroundBlock[NUMBER_OF_CHUNKS][GRID_HEIGHT][GRID_WIDTH];

    public ChunkReader(){

        for (int i = 0; i < NUMBER_OF_CHUNKS; i++){
            chunkArray[i] = readChunk(i+1);
        }

    }

    private BackgroundBlock[][] readChunk(int i){

        System.out.println("CHUNK:! " + i);

        final String chunkName = "chunk" + i + ".data";
        FileHandle chunkFile = Gdx.files.internal(PATH +chunkName);

        BackgroundBlock[][] chunkGrid = new BackgroundBlock[GRID_HEIGHT][GRID_WIDTH];

        try {

            Reader reader = chunkFile.reader(100);
            int lineCounter = 0;
            String line = "";

            //Reads the file line by line and stops when there is nothing more to read
            while ((line = ((BufferedReader) reader).readLine()) != null) {
                String[] data = line.split("\\|");

                chunkGrid[lineCounter][LEFT_BLOCK] = BackgroundBlock.valueOf(data[LEFT_BLOCK]);
                chunkGrid[lineCounter][MIDDLE_BLOCK] = BackgroundBlock.valueOf(data[MIDDLE_BLOCK]);
                chunkGrid[lineCounter][RIGHT_BLOCK] = BackgroundBlock.valueOf(data[RIGHT_BLOCK]);

                System.out.println(  chunkGrid[lineCounter][LEFT_BLOCK]);
                System.out.println(  chunkGrid[lineCounter][MIDDLE_BLOCK]);
                System.out.println(  chunkGrid[lineCounter][RIGHT_BLOCK]);

                lineCounter++;

            }

        } catch (FileNotFoundException e) {
            System.out.println("File " + chunkName + " could not be found at path: " + PATH );
        } catch (IOException e) {
            System.out.println("Something went wrong when reading the file: " + e.toString());
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        return chunkGrid;
    }


    public BackgroundBlock[][] getScenario(int index){

        return getChunkArray()[index];
    }

    private BackgroundBlock[][][] getChunkArray(){
        return chunkArray;
    }

}
