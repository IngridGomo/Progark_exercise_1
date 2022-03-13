package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;


public abstract class Sprite {
    protected Vector3 velocity;
    protected Vector3 position;

    public Sprite(int xStartPos, int yStartPos) {
        this.position = new Vector3(xStartPos, yStartPos, 0);
        this.velocity = new Vector3(0,0,0);
    }

    public abstract void update(float dt);

    public abstract Texture getTexture();

    public Vector3 getPosition() {
        return position;
    }
}
