package com.androidgame.score;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public enum ScoreNumber {

    ZERO(new Texture(Gdx.files.internal("images/numbers/zero.png"))),
    ONE(new Texture(Gdx.files.internal("images/numbers/one.png"))),
    TWO(new Texture(Gdx.files.internal("images/numbers/two.png"))),
    THREE(new Texture(Gdx.files.internal("images/numbers/three.png"))),
    FOUR(new Texture(Gdx.files.internal("images/numbers/four.png"))),
    FIVE(new Texture(Gdx.files.internal("images/numbers/five.png"))),
    SIX(new Texture(Gdx.files.internal("images/numbers/six.png"))),
    SEVEN(new Texture(Gdx.files.internal("images/numbers/seven.png"))),
    EIGHT(new Texture(Gdx.files.internal("images/numbers/eight.png"))),
    NINE(new Texture(Gdx.files.internal("images/numbers/nine.png"))),

    //Numbers shown in menu
    MZERO(new Texture(Gdx.files.internal("images/menu/numbers/menu-zero.png"))),
    MONE(new Texture(Gdx.files.internal("images/menu/numbers/menu-one.png"))),
    MTWO(new Texture(Gdx.files.internal("images/menu/numbers/menu-two.png"))),
    MTHREE(new Texture(Gdx.files.internal("images/menu/numbers/menu-three.png"))),
    MFOUR(new Texture(Gdx.files.internal("images/menu/numbers/menu-four.png"))),
    MFIVE(new Texture(Gdx.files.internal("images/menu/numbers/menu-five.png"))),
    MSIX(new Texture(Gdx.files.internal("images/menu/numbers/menu-six.png"))),
    MSEVEN(new Texture(Gdx.files.internal("images/menu/numbers/menu-seven.png"))),
    MEIGHT(new Texture(Gdx.files.internal("images/menu/numbers/menu-eight.png"))),
    MNINE(new Texture(Gdx.files.internal("images/menu/numbers/menu-nine.png")));

    final public Texture image;

   ScoreNumber (Texture image){
        this.image = image;
    }
}
