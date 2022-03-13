package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.MyGdxGame;

import java.util.BitSet;
import java.util.Random;

/**
 * This class decides the position of the helicopter. Moves it around.
 */

public class Helicopter {
    private Vector3 position, velocity;
    private Texture heli1Left, heli2Left, heli3Left, heli4Left, heli1Right, heli2Right, heli3Right, heli4Right;
    private Array<Texture> heliArrayLeft = new Array<Texture>();
    private Array<Texture> heliArrayRight = new Array<Texture>();
    private Animation heliAnimation;
    private Rectangle heliBounds;

    private boolean goingRight, goingUp;

    public Helicopter(int x, int y, Boolean startRight) {
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0); // Only 0 since we start with it not moving.
        heli1Left = new Texture("heli1Left.png");
        heli2Left = new Texture("heli2Left.png");
        heli3Left = new Texture("heli3Left.png");
        heli4Left = new Texture("heli4Left.png");
        heli1Right = new Texture("heli1Right.png");
        heli2Right = new Texture("heli2Right.png");
        heli3Right = new Texture("heli3Right.png");
        heli4Right = new Texture("heli4Right.png");
        heliArrayLeft.add(heli1Left, heli2Left, heli3Left, heli4Left);
        heliArrayRight.add(heli1Right, heli2Right, heli3Right, heli4Right);
        if(startRight){
            heliAnimation = new Animation(heliArrayRight, 0.1f);
            goingRight = true;
        } else {
            heliAnimation = new Animation(heliArrayLeft, 0.1f);
            goingRight = false;
        }
        goingUp = true;
        heliBounds = new Rectangle(position.x, position.y, getTexture().getWidth(), getTexture().getHeight());
    }

    // Update will send the delta time to our helicopter class and allow it to do the math that will reset it's position in out game world.
    public void update(float dt) {
        heliAnimation.update(dt);
        Random rand = new Random();
        int xRandom = rand.nextInt(300);
        int yRandom = rand.nextInt(300);

        // If helicopter collides with walls
        // I realise now that I could just have changed the necessary x or y value instead of keeping track with goingUp og goingRight
        if(position.x <= 0 && goingUp) {
            position.x = 0;
            goingRight = true;
            heliAnimation.setCurrentArray(heliArrayRight);
            velocity.set(xRandom,yRandom,0);
        } if(position.x <= 0 && !goingUp) {
            position.x = 0;
            goingRight = true;
            heliAnimation.setCurrentArray(heliArrayRight);
            velocity.set(xRandom,-yRandom,0);
        } if((position.x >= MyGdxGame.WIDTH - getTexture().getWidth()) && goingUp) {
            position.x = MyGdxGame.WIDTH - getTexture().getWidth();
            goingRight = false;
            heliAnimation.setCurrentArray(heliArrayLeft);
            velocity.set(-xRandom, yRandom,0);
        } if((position.x >= MyGdxGame.WIDTH - getTexture().getWidth()) && !goingUp) {
            position.x = MyGdxGame.WIDTH - getTexture().getWidth();
            goingRight = false;
            heliAnimation.setCurrentArray(heliArrayLeft);
            velocity.set(-xRandom,-yRandom,0);
        } if(position.y <= 100 && goingRight) {
            position.y = 100;
            velocity.set(xRandom,yRandom,0);
            goingUp = true;
        } if(position.y <= 100 && !goingRight) {
            position.y = 100;
            velocity.set(-xRandom,yRandom,0);
            goingUp = true;
        } if((position.y >= MyGdxGame.HEIGHT - getTexture().getHeight()) && goingRight) {
            position.y = MyGdxGame.HEIGHT - getTexture().getHeight();
            velocity.set(xRandom,-yRandom,0);
            goingUp = false;
        } if((position.y >= MyGdxGame.HEIGHT - getTexture().getHeight()) && !goingRight) {
            position.y = MyGdxGame.HEIGHT - getTexture().getHeight();
            velocity.set(-xRandom,-yRandom,0);
            goingUp = false;
        }


        velocity.scl(dt);
        position.add(velocity.x, velocity.y, 0);
        velocity.scl(1/dt);
        heliBounds.setPosition(position.x, position.y);
    }

    public Rectangle getHeliBounds() {
        return heliBounds;
    }

    public Vector3 getPosition() {
        return position;
    }

    public Vector3 getVelocity(){
        return velocity;
    }

    public Texture getTexture() {
        return heliAnimation.getFrame();
    }

}
