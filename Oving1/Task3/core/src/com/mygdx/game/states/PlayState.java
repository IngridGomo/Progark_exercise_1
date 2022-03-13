package com.mygdx.game.states;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.sprites.Collision;
import com.mygdx.game.sprites.Helicopter;

public class PlayState extends State {
    private Helicopter helicopter1;
    private Helicopter helicopter2;
    private Helicopter helicopter3;
    private Texture background;
    private Collision collision;

    protected PlayState(GameStateManager gsm) {
        super(gsm);
        helicopter1 = new Helicopter(50, 100, true);
        helicopter2 = new Helicopter(200, 100, false);
        helicopter3 = new Helicopter(370, 100, true);
        background = new Texture("background.png");
        collision = new Collision(helicopter1, helicopter2, helicopter3);
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {
        handleInput();
        helicopter1.update(dt);
        helicopter2.update(dt);
        helicopter3.update(dt);
        collision.update(dt);
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background, 0, 0, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
        sb.draw(helicopter1.getTexture(), helicopter1.getPosition().x, helicopter1.getPosition().y);
        sb.draw(helicopter2.getTexture(), helicopter2.getPosition().x, helicopter2.getPosition().y);
        sb.draw(helicopter3.getTexture(), helicopter3.getPosition().x, helicopter3.getPosition().y);
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
