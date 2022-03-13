package com.mygdx.game.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public abstract class State {
    protected OrthographicCamera cam;
    protected Vector3 mouse;
    // gsm manages the state of our game. Vi har ulike game states; eks. play state, pause state som legges oppå det. Så i stedet for å update og endre playstate, så updater vi og rendrer nå pause-state. Når brukeren trykker play igjen så fjerner vi den staten og den aktive staten blir play-state. Så dette funker bra med en stack.
    protected GameStateManager gsm;

    protected State(GameStateManager gsm){
        this.gsm = gsm;
        cam = new OrthographicCamera();
        mouse = new Vector3();
    }

    protected abstract void handleInput();
    // update does all the calculations in order for the render method to draw pictures. Update method includes things like jumping the character up on the screen, how many pixels doing the calculations for gravity to bring it back down. Or to adjust the pipes going left and right, where the ground needs to be and stuff like that.
    public abstract void update(float dt); // dt = time between one frame rendered and the next frame rendered
    // render draws things on the screen. No calculations. Just takes objects of our game world and draws them to the screen.
    public abstract void render(SpriteBatch sb); // SpriteBatch = a container for everything that we need to render to the screen. All of our textures and stuff like that. And it renders everything to our screen in ine big blob.
    // need this to dispose of things, as to not use more memory than necessary.
    public abstract void dispose();
}
