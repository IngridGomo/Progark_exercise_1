package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.states.GameStateManager;
import com.mygdx.game.states.MenuState;

public class MyGdxGame extends ApplicationAdapter {
	public static final int WIDTH = 480;
	public static final int HEIGHT = 800;

	public static final String TITLE = "Helicopter";
	private GameStateManager gsm;
	private SpriteBatch batch; //skal bare ha én av denne.

	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm = new GameStateManager();
		gsm.push(new MenuState(gsm));
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		gsm.update(Gdx.graphics.getDeltaTime()); //argumentet her gir oss delta time dt
		gsm.render(batch);
	}

	@Override
	public void dispose () {
		batch.dispose();
	}
}