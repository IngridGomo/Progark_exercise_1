package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.MyGdxGame;

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
    private boolean goingUp;

    public Helicopter(int x, int y) {
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0); // Only 0 since we start with it not moving.
        helicopterRight = new Texture("helicopterRight.png");
        helicopterLeft = new Texture("helicopterLeft.png");
        goingRight = true;
        goingUp = true;
    }

    // Update will send the delta time to our helicopter class and allow it to do the math that will reset it's position in out game world.
    public void update(float dt) {
        if(position.x <= 0 && goingUp) {
            position.x = 0;
            goingRight = true;
            velocity.set(100,200,0);
        } if(position.x <= 0 && !goingUp) {
            position.x = 0;
            goingRight = true;
            velocity.set(100,-200,0);
        } if((position.x >= MyGdxGame.WIDTH - getTexture().getWidth()) && goingUp) {
            position.x = MyGdxGame.WIDTH - getTexture().getWidth();
            goingRight = false;
            velocity.set(-100,200,0);
        } if((position.x >= MyGdxGame.WIDTH - getTexture().getWidth()) && !goingUp) {
            position.x = MyGdxGame.WIDTH - getTexture().getWidth();
            goingRight = false;
            velocity.set(-100,-200,0);
        } if(position.y <= 100 && goingRight) {
            position.y = 100;
            velocity.set(100,200,0);
            goingUp = true;
        } if(position.y <= 100 && !goingRight) {
            position.y = 100;
            velocity.set(-100,200,0);
            goingUp = true;
        } if((position.y >= MyGdxGame.HEIGHT - getTexture().getHeight() - 120) && goingRight) {
            position.y = MyGdxGame.HEIGHT - getTexture().getHeight() - 120;
            velocity.set(100,-200,0);
            goingUp = false;
        } if((position.y >= MyGdxGame.HEIGHT - getTexture().getHeight() - 120) && !goingRight) {
            position.y = MyGdxGame.HEIGHT - getTexture().getHeight() - 120;
            velocity.set(-100,-200,0); //kanskje jeg må endre fra .add til =
            goingUp = false;
        }
        velocity.scl(dt);
        position.add(velocity.x, velocity.y, 0);
        velocity.scl(1/dt);
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

}
