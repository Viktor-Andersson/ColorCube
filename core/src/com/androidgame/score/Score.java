package com.androidgame.score;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.List;

import static com.androidgame.state.PlayState.PLAYER_START_POSITION;
import static com.androidgame.state.StateHandler.BLOCK_SIZE;
import static com.androidgame.state.StateHandler.HEIGHT;

public class Score {

    List<ScoreNumber> numberList;
    int currentScore = 0;

    public void setCurrentScore(int currentSCore) {
        this.currentScore = currentSCore;
    }

    public int getCurrentScore() {
        return currentScore;
    }

    public Score(List<ScoreNumber> numberList){
        this.numberList = numberList;

    }

    public List<ScoreNumber> getNumberList() {
        return numberList;
    }

    public void showScore(Batch batch, int playerPos){

        int score = currentScore;
        int one = score % 10;
        int hundred = score /100;
        int ten = (score - hundred * 100) / 10;

        int numberWidth = BLOCK_SIZE/2;
        int numberHeight = BLOCK_SIZE/2;

        int positionHeight = HEIGHT - numberHeight - PLAYER_START_POSITION;


        if (ten > 0 && hundred <= 0){

            Sprite scoreOne = new Sprite(numberList.get(one).image);
            scoreOne.setSize(numberWidth,  numberHeight );
            scoreOne.setPosition(numberWidth/2,  playerPos + positionHeight);
            scoreOne.draw(batch);

            Sprite scoreTen = new Sprite(numberList.get(ten).image);
            scoreTen.setSize(numberWidth,  numberHeight);
            scoreTen.setPosition(0,  playerPos + positionHeight);
            scoreTen.draw(batch);

        }
        else if (hundred > 0){
            Sprite scoreOne = new Sprite(numberList.get(one).image);
            scoreOne.setSize(numberWidth,  numberHeight );
            scoreOne.setPosition(numberWidth,  playerPos + positionHeight);
            scoreOne.draw(batch);

            Sprite scoreTen = new Sprite(numberList.get(ten).image);
            scoreTen.setSize(numberWidth,  numberHeight);
            scoreTen.setPosition(numberWidth/2,  playerPos + positionHeight);
            scoreTen.draw(batch);

            Sprite scoreHundred = new Sprite(numberList.get(hundred).image);
            scoreHundred.setSize(numberWidth,  numberHeight);
            scoreHundred.setPosition(0 ,  playerPos + positionHeight);
            scoreHundred.draw(batch);


        }
        else {

            Sprite scoreOne = new Sprite(numberList.get(one).image);
            scoreOne.setSize(numberWidth,  numberHeight );
            scoreOne.setPosition(0,  playerPos + positionHeight);
            scoreOne.draw(batch);


           // batch.draw(numberList.get(one).image, * 20,playerPos + BLOCK_SIZE/3 * 12);
        }
    }



    public void showHighScore(Batch batch){

        Preferences prefs = Gdx.app.getPreferences("game preferences");
        int highScore = prefs.getInteger("highscore");
        int one = highScore % 10;
        int hundred = highScore / 100;
        int ten = (highScore - hundred * 100) / 10;

        int numberWidth = BLOCK_SIZE/2;
        int numberHeight = BLOCK_SIZE/2;

        int positionHeight = BLOCK_SIZE/100 * 150;


        if (ten > 0  && hundred <= 0){

            Sprite scoreOne = new Sprite(numberList.get(ten).image);
            scoreOne.setSize(numberWidth,  numberHeight);
            scoreOne.setPosition(BLOCK_SIZE*1.5f - numberWidth/2 - numberWidth/4, positionHeight);

            Sprite scoreTen = new Sprite(numberList.get(one).image);
            scoreTen.setSize(numberWidth,  numberHeight);
            scoreTen.setPosition(BLOCK_SIZE*1.5f - numberWidth/2 + numberWidth/4,  positionHeight);

            scoreOne.draw(batch);
            scoreTen.draw(batch);

        }

        else if (hundred > 0){
            Sprite scoreOne = new Sprite(numberList.get(one).image);
            scoreOne.setSize(numberWidth,  numberHeight);
            scoreOne.setPosition(BLOCK_SIZE*1.5f ,  positionHeight);

            Sprite scoreTen = new Sprite(numberList.get(ten).image);
            scoreTen.setSize(numberWidth,  numberHeight);
            scoreTen.setPosition(BLOCK_SIZE*1.5f - numberWidth/2,  positionHeight);


            Sprite scoreHundred = new Sprite(numberList.get(hundred).image);
            scoreHundred.setSize(numberWidth,  numberHeight);
            scoreHundred.setPosition(BLOCK_SIZE*1.5f - numberWidth,  positionHeight);


            scoreOne.draw(batch);
            scoreTen.draw(batch);
            scoreHundred.draw(batch);
        }
        else {

            Sprite scoreS = new Sprite(numberList.get(highScore).image);
            scoreS.setSize(numberWidth,  numberHeight);
            scoreS.setPosition(BLOCK_SIZE*1.5f - numberWidth/2,  positionHeight);
            scoreS.draw(batch);
        }
    }
}
