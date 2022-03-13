package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.BitSet;
import java.util.Random;

/**
 * This class decides the position of the helicopter. Moves it around.
 */

public class Helicopter {
    private Vector3 position;
    private Vector3 velocity;

    private Texture helicopterLeft;
    private Texture helicopterRight;
    private boolean goingRight;
    // private TextureRegion heliReg; //prøvde å bruke dette til flip, men kommenterer det ut da flip ikke fungerer
    // private Sprite heliSprite;
    private float x = 2;
    private float y = 1;

    public Helicopter(int x, int y) {
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0); // Only 0 since we start with it not moving.
        helicopterRight = new Texture("helicopterRight.png");
        helicopterLeft = new Texture("helicopterLeft.png");
        //heliSprite = new Sprite(helicopter); //prøvde å bruke dette til flip, men kommenterer det ut da flip ikke fungerer
        //heliReg = new TextureRegion(helicopter);
    }

    // Update will send the delta time to our helicopter class and allow it to do the math that will reset it's position in out game world.
    public void update(float dt) {
        velocity.add(6,7,0); // jeg så ingen forskjell når jeg tok denne bort. Kan være den kun virker på gravity (flappy bird).
        velocity.scl(dt);
        Random random = new Random();
        int zeroOrOne = random.nextInt(2);
        if(position.x <= 0) {
            //heliSprite.flip(true, false);
            this.x = 1 + zeroOrOne;
            this.y = -1 - zeroOrOne;
            goingRight = true;
        } if(position.x >= 480 - getTexture().getWidth()) {
            //heliSprite.flip(true, false);
            this.x = -1;
            this.y = 1 + zeroOrOne;
            goingRight = false;
        } if(position.y >= 800 - getTexture().getHeight()) { // For some reason the desktop version don't think this is the top (though it is). The emulatpr gets it though. When in desktop, add -120 and it fits.
            this.y = -2 - zeroOrOne;
            this.x = -1;
            goingRight = false;
        } if(position.y <= 100) {
            // heliSprite.flip(true, false);
            this.x = 1 + zeroOrOne;
            this.y = 1 + zeroOrOne;
            goingRight = true;
        }
        position.add(x,y,0);

        velocity.scl(1/dt);  // jeg så ingen forskjell når jeg tok denne bort. Kan være den kun virker på gravity (flappy bird).

    }

    public Vector3 getPosition() {
        return position;
    }

    public Texture getTexture() {
        if (goingRight) {
            return helicopterRight;
        } else {
            return helicopterLeft;
        }
    }

    //public TextureRegion getHeliReg() { return heliReg; } //prøvde å bruke dette til flip, men kommenterer det ut da flip ikke fungerer

    public float getX() {
        return x;
    }
}
