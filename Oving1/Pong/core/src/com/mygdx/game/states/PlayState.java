package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.sprites.Ball;
import com.mygdx.game.sprites.Helicopter;
import com.mygdx.game.sprites.Paddle;
import com.mygdx.game.sprites.Score;

public class PlayState extends State {
    private Texture background;
    private Ball ball;
    private Paddle paddle1;
    private Paddle paddle2;
    private Score score;

    protected PlayState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("background.png");
        paddle1 = new Paddle(20, MyGdxGame.HEIGHT/2 - 25); // Hard coded in -49 to center the paddle. Did not prioritize time to find another way to do it.
        paddle2 = new Paddle(MyGdxGame.WIDTH-20, MyGdxGame.HEIGHT/2 - 25);
        score = new Score(paddle1, paddle2);
        ball = new Ball(paddle1, paddle2, score);
;    }

    @Override
    protected void handleInput() {
        if(Gdx.input.isKeyPressed(Input.Keys.Q)) {
            paddle1.changeDirection("up");
        } if(Gdx.input.isKeyPressed(Input.Keys.A)) {
            paddle1.changeDirection("down");
        } if(Gdx.input.isKeyPressed(Input.Keys.DPAD_UP)) {
            paddle2.changeDirection("up");
        } if(Gdx.input.isKeyPressed(Input.Keys.DPAD_DOWN)) {
            paddle2.changeDirection("down");
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        ball.update(dt);
        paddle1.update(dt);
        paddle2.update(dt);
        score.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background, 0, 0, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
        sb.draw(ball.getTexture(), ball.getPosition().x, ball.getPosition().y);
        sb.draw(paddle1.getTexture(), paddle1.getPosition().x - paddle1.getTexture().getWidth(), paddle1.getPosition().y);
        sb.draw(paddle2.getTexture(), paddle2.getPosition().x, paddle2.getPosition().y);
        score.getFont().setColor(253, 232, 52, 1);
        score.getFont().draw(sb, score.getScoreText(), MyGdxGame.WIDTH/2 - 42, MyGdxGame.HEIGHT - 15);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
    }
}
