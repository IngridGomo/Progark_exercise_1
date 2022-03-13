package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class Score {
    private Paddle paddle1;
    private Paddle paddle2;
    private BitmapFont font;
    private int paddle1Score = 0;
    private int paddle2Score = 0;

    public Score(Paddle paddle1, Paddle paddle2) {
        this.paddle1 = paddle1;
        this.paddle2 = paddle2;
        font = new BitmapFont();
    }

    public void update() {

    }

    public void addPoint(int paddleNo) {
        if(paddleNo == 1) {
            paddle1Score += 1;
        } else {
            paddle2Score += 1;
        }
    }

    public BitmapFont getFont() {
        return font;
    }

    public String getScoreText() {
        return paddle1Score + "                " + paddle2Score;
    }
}
