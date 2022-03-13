package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.MyGdxGame;

/*
* This game ended up being a bit spaghetti code. And this Ball-class now kinda serves as a "controller"/bindeledd, since it
* now is not only a ball sprite, but takes in the paddles and score as well. I should probably have
* made a new class for controlling teh state, but I was unsure if this should happen in GameStateManager,
* PlayState or in a new class. And most importantly, I did not have time to think about it or fix it.
* */

public class Ball extends Sprite {
    private Texture ball;
    private Score score;
    private Rectangle bounds;
    private Paddle paddle1;
    private Paddle paddle2;
    private static int START_X = MyGdxGame.WIDTH/2 - 6; // Hard coded in -6 to center the ball. Did not prioritize time to find another way to do it.
    private static int START_Y = MyGdxGame.HEIGHT/2 - 6;


    public Ball(Paddle paddle1, Paddle paddle2, Score score) {
        super(START_X, START_Y);
        this.ball = new Texture("ball.png");
        this.score = score;
        this.paddle1 = paddle1;
        this.paddle2 = paddle2;
        bounds = new Rectangle(position.x, position.y, getTexture().getWidth(), getTexture().getHeight());
        int randomDirectionX = (int) Math.floor(Math.random()*(2)); // Random number between 0 and 1
        int randomDirectionY = (int) Math.floor(Math.random()*(4-1+1)+1); // Random number between 1 and 4 (i think. Haven't checked if 0 is still in or not.
        // Decide start x-direction
        if(randomDirectionX == 0) {
            velocity.add(-400, 0, 0);
        } else {
            velocity.add(400, 0, 0);
        }
        // Decide start y-direction
        if(randomDirectionY <= 3) {
            velocity.add(0, randomDirectionY*20, 0);
        } else {
            velocity.add(0, -randomDirectionY*20, 0);
        }
    }

    @Override
    public void update(float dt) {
        // Hits wall:
        if(position.x <= 0) {
            score.addPoint(2);
            restartBall();
        } if(position.x >= MyGdxGame.WIDTH - ball.getWidth()) {
            score.addPoint(1);
            restartBall();
        }
        // Hits the ground:
        if(position.y <= 0) {
            position.y = 0;
            velocity.y = -velocity.y;
        }
        // Hits the roof:
        if(position.y >= MyGdxGame.HEIGHT - getTexture().getHeight()) {
            position.y = MyGdxGame.HEIGHT - getTexture().getHeight();
            velocity.y = -velocity.y;
        }
        // Hits paddle:
        if(hitsPaddle(paddle1.getBounds()) || hitsPaddle(paddle2.getBounds())) {
            velocity.x = -velocity.x;
        }

        velocity.scl(dt);
        position.add(velocity.x, velocity.y, 0);
        velocity.scl(1/dt);
        bounds.setPosition(position.x, position.y);
    }

    @Override
    public Texture getTexture() {
        return ball;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public Boolean hitsPaddle(Rectangle paddleBounds) {
        return getBounds().overlaps(paddleBounds);
    }

    public void restartBall() {
        position.x = START_X;
        position.y = START_Y;
        velocity.set(0,0,0);
        int randomDirectionX = (int) Math.floor(Math.random()*(2)); // Random number between 0 and 1
        int randomDirectionY = (int) Math.floor(Math.random()*(4-1+1)+1); // Random number between 1 and 4 (i think. Haven't checked if 0 is still in or not.
        // Decide start x-direction
        if(randomDirectionX == 0) {
            velocity.add(-400, 0, 0);
        } else {
            velocity.add(400, 0, 0);
        }
        // Decide start y-direction
        if(randomDirectionY <= 3) {
            velocity.add(0, randomDirectionY*20, 0);
        } else {
            velocity.add(0, -randomDirectionY*20, 0);
        }
    }
}

// Ok, så det jeg burde gjøre er å nare ha ball som egen klasse. Og når noen taper så oppretter man bare en ny ball (og den gamle kan vel disposes - husk å legg inn det uansett).
// Så ball burde ikke ha noe med paddle å gjøre. Siden hvis man då oppretter ny ball så lager man jo også ny padler - og det vil vi ikke. Og så kan sikkert padlene ha scoren knyttet til seg.
// Men spørsmålet blir da hvordan man skal vite om en ball treffer en paddle