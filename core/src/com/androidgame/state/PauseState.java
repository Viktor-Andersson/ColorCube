package com.androidgame.state;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;


import java.util.Arrays;
import java.util.List;

import com.androidgame.chunk.BackgroundBlock;
import com.androidgame.score.Score;
import com.androidgame.score.ScoreNumber;

import static com.androidgame.state.StateHandler.BLOCK_SIZE;
import static com.androidgame.state.StateHandler.HEIGHT;
import static com.androidgame.state.StateHandler.WIDTH;
import static com.androidgame.score.ScoreNumber.*;

public class PauseState extends State {

    private StateHandler stateHandler;
    private Sprite spriteText;
    private Sprite playButton;

    private Score hScore;
    private OrthographicCamera camera;

    public PauseState(StateHandler stateHandler){

        this.stateHandler = stateHandler;

        Texture text = new Texture("images/menu/menu-text.png");
        spriteText = new Sprite(text);

        //Places menu text in center
        final int textWidth = BLOCK_SIZE/2* 7;
        spriteText.setSize(textWidth, BLOCK_SIZE/4 * 7);
        spriteText.setPosition((BLOCK_SIZE*1.5f) - textWidth/2, BLOCK_SIZE/2 * 5);


        Texture play = new Texture("images/menu/button-play.png");
        playButton = new Sprite(play);

        //Places playbutton in center
        final int playButtonSize = BLOCK_SIZE/100 * 140;

        playButton.setSize(playButtonSize, playButtonSize );
        playButton.setPosition((BLOCK_SIZE*1.5f) -playButtonSize/2, BLOCK_SIZE/3 * 5);

        //Set the camera to focus on the middle of the screen
        camera = new OrthographicCamera(WIDTH, HEIGHT);
        camera.translate(camera.viewportWidth/2,camera.viewportHeight/2);

        List<ScoreNumber> numberList = Arrays.asList(MZERO,MONE,MTWO,MTHREE,MFOUR,MFIVE,MSIX,MSEVEN,MEIGHT,MNINE);
        hScore = new Score(numberList);
    }


    @Override
    public void userInput() {
        /*
        *  Checks if the player presses the play button
        * */

        if(Gdx.input.justTouched()){
           final int y  = (Gdx.input.getY() - HEIGHT)*-1;
           final int x  = Gdx.input.getX();

            if((y > playButton.getY() && y < playButton.getY() + playButton.getHeight() &&
                    (x > playButton.getX()) && x < playButton.getX()+playButton.getWidth())){

                //Dispose all textures before starting new game session
                dispose();
                stateHandler.setCurrentState(new PlayState(stateHandler));
            }


        }
    }

    @Override
    public void update() {
        userInput();
    }

    @Override
    public void render(Batch batch) {

        Gdx.gl.glClearColor(255/255f, 255/255f, 255/255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        spriteText.draw(batch);
        playButton.draw(batch);

        //Draws 3 blocks at the top of the screen and 3 at the bottom
        batch.draw(BackgroundBlock.BLUE.background,0, HEIGHT-BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
        batch.draw(BackgroundBlock.RED.background,BLOCK_SIZE,HEIGHT-BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
        batch.draw(BackgroundBlock.YELLOW.background,BLOCK_SIZE*2,HEIGHT -BLOCK_SIZE,BLOCK_SIZE,BLOCK_SIZE);
        batch.draw(BackgroundBlock.BLUE.background,BLOCK_SIZE*2,0,BLOCK_SIZE,BLOCK_SIZE);
        batch.draw(BackgroundBlock.RED.background,BLOCK_SIZE,0,BLOCK_SIZE,BLOCK_SIZE);
        batch.draw(BackgroundBlock.YELLOW.background,0,0, BLOCK_SIZE,BLOCK_SIZE);

        hScore.showHighScore(batch);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.end();

    }

    @Override
    public void dispose() {
        spriteText.getTexture().dispose();
        playButton.getTexture().dispose();

    }


}
