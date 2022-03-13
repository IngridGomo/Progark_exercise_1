package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.states.GameStateManager;
import com.mygdx.game.states.MenuState;

public class MyGdxGame extends ApplicationAdapter {
	public static final int WIDTH = 800;
	public static final int HEIGHT = 500;
	public static final String TITLE = "Pong";

	private GameStateManager gsm;
	private SpriteBatch batch; // should only have one of this

	public void create () {
		batch = new SpriteBatch();
		gsm = new GameStateManager();
		gsm.push(new MenuState(gsm));
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		gsm.update(Gdx.graphics.getDeltaTime()); // this argument gives us the delta time (dt)
		gsm.render(batch);
	}

	@Override
	public void dispose () {
		batch.dispose();
	}
}