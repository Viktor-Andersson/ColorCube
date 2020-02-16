package com.androidgame.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;

import java.util.Arrays;
import java.util.List;

import com.androidgame.chunk.BackgroundBlock;
import com.androidgame.chunk.ChunkGenerator;
import com.androidgame.player.Player;
import com.androidgame.score.Score;
import com.androidgame.score.ScoreNumber;

import static com.androidgame.chunk.ChunkReader.ROWS;
import static com.androidgame.state.StateHandler.BLOCK_SIZE;
import static com.androidgame.state.StateHandler.HEIGHT;
import static com.androidgame.state.StateHandler.WIDTH;

import static com.androidgame.score.ScoreNumber.*;

public class PlayState extends State{

    private Player player;
    private OrthographicCamera camera;
    private ChunkGenerator generator1 = new ChunkGenerator(0);
    private ChunkGenerator generator2 = new ChunkGenerator(BLOCK_SIZE * ROWS);
    private StateHandler stateHandler;
    private int distToBottom;
    private boolean play = true;
    private Score score;
    private Sound sound = Gdx.audio.newSound(Gdx.files.internal("sounds/change.mp3"));

    public static final int PLAYER_START_POSITION = BLOCK_SIZE;

    public PlayState(StateHandler stateHandler) {
        player = new Player(PLAYER_START_POSITION, 0);
        camera = new OrthographicCamera(WIDTH,HEIGHT);
        camera.translate(camera.viewportWidth / 2, camera.viewportHeight / 2);


        distToBottom = (HEIGHT - (HEIGHT / BLOCK_SIZE) * BLOCK_SIZE);
        List<ScoreNumber> numberList = Arrays.asList(ZERO,ONE,TWO,THREE,FOUR,FIVE,SIX,SEVEN,EIGHT,NINE);
        score = new Score(numberList);
        this.stateHandler = stateHandler;


    }

    @Override
    public void userInput() {
    /*
    * Checks if the right or left side of the screen has been pressed and
    * moves the player accordingly
    * */

        if (Gdx.input.justTouched()) {

           final int touchX = Gdx.input.getX();

            if (touchX > WIDTH/2 && player.getXPos() <= BLOCK_SIZE) {
                player.moveRight();
            } else if (touchX <= WIDTH/2 && player.getXPos() >= BLOCK_SIZE) {
                player.moveLeft();
            }

        }
    }


    @Override
    public void update() {

        if (collisionChunk() && play) {
            collisionChunk();
            userInput();
            player.update();

            //Moves a chunk when it can no longer bee seen by the player to be on
            //top of the on currently displayed chunk. Then loads in a new random chunk
            if (player.getYPos() > generator1.getY() + distToBottom + BLOCK_SIZE * ROWS) {

                generator1.setY((BLOCK_SIZE * ROWS + generator2.getY()));
                generator1.randomChunk();
            }
            if (player.getYPos() > generator2.getY() + distToBottom + BLOCK_SIZE * ROWS) {
                generator2.setY((BLOCK_SIZE * ROWS + generator1.getY()));
                generator2.randomChunk();

            }
        } else {
            play = false;

            //Update high score if the current score is greater
            Preferences prefs = Gdx.app.getPreferences("game preferences");
            int highScore = prefs.getInteger("highscore");

            if (ChunkGenerator.chunkCalculator > highScore) {
                prefs.putInteger("highscore", ChunkGenerator.chunkCalculator - 2);
                prefs.flush();
            }

            //Waits for input from the player to load the PauseState
            if (Gdx.input.justTouched()) {

                ChunkGenerator.chunkCalculator = 0;
                System.out.println(stateHandler);
                System.out.println(stateHandler);

                stateHandler.setCurrentState(new PauseState(stateHandler));
            }

        }


    }

    public boolean collisionChunk() {
        /*
         * Checks which generator the player is colliding with and returns the current colliding block
         */

        BackgroundBlock b1;
        BackgroundBlock b2;

        final int playerY = player.getYPos();
        final int playerX = player.getXPos();

        if (playerY + BLOCK_SIZE > generator1.getY() &&
                playerY +BLOCK_SIZE < generator1.getY() + BLOCK_SIZE * ROWS) {

            b1 = generator1.getBackground(playerX, player.getYPos()+BLOCK_SIZE/2);
            b2 = generator1.getBackground(playerX, player.getYPos()+BLOCK_SIZE);


        } else {

            b1 = generator2.getBackground(playerX, playerY +BLOCK_SIZE);
            b2 = generator2.getBackground(playerX, playerY +BLOCK_SIZE / 2);

        }

        return collisionBackground(b1) && collisionBackground(b2);

    }

    private boolean collisionBackground(BackgroundBlock b) {
        /*
        * Returns false if the player collides with a color that does not match its own
         */

        if (b != BackgroundBlock.WHITE && b != null) {

            if (b.changeColor() != null && b.playerColor != player.getPlayerColor()) {
                score.setCurrentScore(score.getCurrentScore() + 1);
                player.setColor(b.playerColor);
                sound.play(1.0f);
            }

            return b.playerColor == player.getPlayerColor();

        }
        return true;
    }

    @Override
    public void render(Batch batch) {

        Gdx.gl.glClearColor(255 / 255f, 255 / 255f, 255 / 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        generator1.render(batch);
        generator2.render(batch);
        score.showScore(batch,player.getYPos());
        batch.draw(player.getPlayerColor().texture, player.getXPos(), player.getYPos(),BLOCK_SIZE, BLOCK_SIZE);

        //Fix the center of camera 2BLOCKS above the player
        camera.position.y = player.getYPos() + BLOCK_SIZE * 2;
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.end();
    }

    public void dispose() {



    }
}
