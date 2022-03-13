package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.states.GameStateManager;
import com.mygdx.game.states.MenuState;

public class MyGdxGame extends ApplicationAdapter {
	// Tror dette var skjermstørrelsen han satte, så den kan jeg endre selv.
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
		// Hadde opprinnelig disse her, men byttet dem ut med linja over fordi det var det han hadde. Og da funket det :D
		//batch.begin();
		//batch.end();
	}

	@Override
	public void dispose () {
		batch.dispose();
	}
}