package com.mygdx.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.MyGdxGame;

import java.util.BitSet;
import java.util.Random;

import javax.swing.plaf.basic.BasicRadioButtonMenuItemUI;

/**
 * This class decides the position of the helicopter. Moves it around.
 * Implements Singleton
 */

public class Helicopter {
    private Vector3 position = new Vector3(50, 100, 0);
    private Vector3 velocity = new Vector3(0,0,0);
    private Texture helicopterLeft = new Texture("helicopterLeft.png");
    private Texture helicopterRight = new Texture("helicopterRight.png");
    private static Helicopter instance = new Helicopter(); // eagerly loads the singleton
    private boolean goingRight = true;
    private BitmapFont font = new BitmapFont();

    private Helicopter() {
        // private to prevent anyone else from instantiating
    }

    public static Helicopter getInstance() {
        return instance;
    }

    // Update will send the delta time to our helicopter class and allow it to do the math that will reset it's position in our game world.
    public void update(float dt) {
        if(position.x <= 0) {
            position.x = 0;
        } if(position.x >= MyGdxGame.WIDTH - getTexture().getWidth()) {
            position.x = MyGdxGame.WIDTH - getTexture().getWidth();
        } if(position.y <= 100) {
            position.y = 100;
        } if(position.y >= MyGdxGame.HEIGHT - getTexture().getHeight()) {
            position.y = MyGdxGame.HEIGHT - getTexture().getHeight();
        }
        velocity.scl(dt);
        position.add(velocity.x, velocity.y, 0);
        velocity.scl(1/dt);
    }

    public BitmapFont getFont() {
        return font;
    }

    public Vector3 getPosition() {
        return position;
    }

    public String getCoordinatePrint() {
        return  "(" + position.x + ", " + position.y + ")";
    }

    public Texture getTexture() {
        if (goingRight) {
            return helicopterRight;
        } else {
            return helicopterLeft;
        }
    }

    // Changes the way the helicopter is flying based on input-keys. Gets the input from PlayState.
    public void changeDirection(String direction) {
        if(direction.equals("up")) {
            if(velocity.y < 0) {  // If you want to slow down the helicopter and accelerate "correctly"/naturally, remove these if-statements. I found it a bit annoying to play without them
                velocity.y = 0; }
            velocity.add(0,5,0);
        } if (direction.equals("down")) {
            if(velocity.y > 0) {
                velocity.y = 0; }
            velocity.add(0,-5,0);
        } if (direction.equals("right")) {
            if(velocity.x < 0) {
                velocity.x = 0; }
            velocity.add(5,0,0);
            goingRight = true;
        } if(direction.equals("left")) {
            if(velocity.x > 0) {
                velocity.x = 0; }
            velocity.add(-5,0,0);
            goingRight = false;
        }
    }
}
