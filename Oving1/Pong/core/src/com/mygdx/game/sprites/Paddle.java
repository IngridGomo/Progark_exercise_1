package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.MyGdxGame;

import org.w3c.dom.css.Rect;

public class Paddle extends Sprite {
    private Texture paddle;
    private Rectangle bounds;

    public Paddle(int xStart, int yStart) {
        super(xStart, yStart);
        paddle = new Texture("paddle.png");
        bounds = new Rectangle(position.x, position.y, getTexture().getWidth(), getTexture().getHeight());
    }

    @Override
    public void update(float dt) {
        if(position.y <= 0) {
            position.y = 0;
        } if(position.y >= MyGdxGame.HEIGHT - getTexture().getHeight()) {
            position.y = MyGdxGame.HEIGHT - getTexture().getHeight();
        }
        velocity.scl(dt);
        position.add(velocity.x, velocity.y, 0);
        velocity.scl(1/dt);
        bounds.setPosition(position.x, position.y);
    }

    @Override
    public Texture getTexture() {
        return paddle;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void changeDirection(String direction) {
        if(direction.equals("up")) {
            if(this.velocity.y < 0) {
                velocity.y = 0; }
            velocity.add(0,20,0);
        } if (direction.equals("down")) {
            if (velocity.y > 0) {
                velocity.y = 0; }
            velocity.add(0, -20, 0);
        }
    }
}
