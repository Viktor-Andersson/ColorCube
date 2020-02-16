package com.androidgame.chunk;

/**
 * A chunk is a piece of the game map with a 9x3 grid filled with BackgroundBlocks.
 *
 * Each chunk can be loaded from a chunk.data file and has the following format:
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
 * To add a chunk simply create a new chunk.data file and save it in  android/assets/data
 * name the file chunk(NextNumberThatDoesNotExist).data
 * */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ChunkReader {
    //Numbers of rows in a com.androidgame.chunk
    public static final int ROWS = 9;

    private static final int LEFT_BLOCK = 0;
    private static final int MIDDLE_BLOCK = 1;
    private static final int RIGHT_BLOCK = 2;

    private static final int GRID_WIDTH = 3;
    private static final int GRID_HEIGHT = ROWS;

    private static final String PATH = "data/";
    public static final int NUMBER_OF_CHUNKS = 26;

    private BackgroundBlock[][][] chunkArray = new BackgroundBlock[NUMBER_OF_CHUNKS][GRID_HEIGHT][GRID_WIDTH];

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

            BufferedReader reader = chunkFile.reader(100);
            int lineCounter = 0;
            String line;

            //Reads the file line by line and stops when there is nothing more to read
            while ((line = reader.readLine()) != null) {
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
            System.out.println("File " + chunkName + " could not be found at path: " + PATH + " ." +e);

        } catch (IOException e) {
            System.out.println("Something went wrong when reading the file: " + e);
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
