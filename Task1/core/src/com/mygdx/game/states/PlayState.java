package com.mygdx.game.states;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.sprites.Helicopter;

public class PlayState extends State {
    private Helicopter helicopter;
    private Texture background;

    protected PlayState(GameStateManager gsm) {
        super(gsm);
        helicopter = new Helicopter(50, 100);
        //cam.setToOrtho(false, MyGdxGame.WIDTH*2, MyGdxGame.HEIGHT*2); // zooming out to twice the size to make the helicopter smaller (it was too big). Edit: This made the background super small, so i changed the size of the helicopter-file instead.
        background = new Texture("background.png");
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {
        handleInput();
        helicopter.update(dt);
    }

    @Override
    public void render(SpriteBatch sb) {
        //sb.setProjectionMatrix(cam.combined); // Adjusting the sprite batch so that it knows the coordinate system we are working with in relation to out camera. Where it needs to draw things. Edit: This made the background super small, so i changed the size of the helicopter-file instead.
        sb.begin();
        //sb.draw(background, cam.position.x - (cam.viewportWidth/2), 0); // Flappy gjorde dette. Men da ble ikke hele skjermen dekket.
        sb.draw(background, 0, 0, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
        sb.draw(helicopter.getTexture(), helicopter.getPosition().x, helicopter.getPosition().y);
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
