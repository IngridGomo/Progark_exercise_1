package com.mygdx.game.states;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.sprites.Helicopter;

public class PlayState extends State {
    private Helicopter helicopter;
    private Texture background;
    private SpriteBatch emptyBatch;

    protected PlayState(GameStateManager gsm) {
        super(gsm);
        helicopter = helicopter.getInstance();
        background = new Texture("background.png");
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.isKeyPressed(Input.Keys.DPAD_UP)) {
            helicopter.changeDirection("up");
        } if(Gdx.input.isKeyPressed(Input.Keys.DPAD_DOWN)) {
            helicopter.changeDirection("down");
        } if(Gdx.input.isKeyPressed(Input.Keys.DPAD_RIGHT)) {
            helicopter.changeDirection("right");
        } if(Gdx.input.isKeyPressed(Input.Keys.DPAD_LEFT)) {
            helicopter.changeDirection("left");
        }
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
        helicopter.getFont().setColor(253, 232, 52, 1);
        helicopter.getFont().draw(sb, helicopter.getCoordinatePrint(), 15, MyGdxGame.HEIGHT - 15);
        sb.end();
    }

    @Override
    public void dispose() {
        helicopter.getFont().dispose();
    }
}
