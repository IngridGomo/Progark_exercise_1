package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class Animation {
    private Array<Texture> currentArray;
    float showtime;
    private float currentFrameTime;  // the time the animation has been in the current frame
    private int currentFrame; // the current frame we are in
    //private int frameCount; // the number of frames in our animation
    //private Array<TextureRegion> frames;
    //private float maxFrameTime;

    /**
     * Constructor for when animation frames come separately, gathered in an array.
     * heliArray = array with all the frames
     * showTime = how long a frame is gonna show
     */
    public Animation(Array<Texture> heliArrayStart, float showTime){
        this.currentArray = heliArrayStart;
        this.showtime = showTime;
    }

    public void update(float dt){  // dt is time between render cycles
        currentFrameTime += dt;
        if(currentFrameTime > showtime){
            currentFrame++;
            currentFrameTime = 0;
        }
        if(currentFrame >= currentArray.size){
            currentFrame = 0;
        }
    }

    public Texture getFrame(){
        return currentArray.get(currentFrame);
    }

    public void setCurrentArray(Array<Texture> currentArray) {
        this.currentArray = currentArray;
    }

    /**
     * Constructor for when animation frames come in a sheet (and mine dont, so I comment this out).
     * region = all of the frame combined in one image
     * frameCount = number of frames in our textureRegion
     * cycleTime = how long it's gonna take to cycle through the entire animation
     */
   /* public Animation(TextureRegion region, int frameCount, float cycleTime){
        frames = new Array<TextureRegion>();
        int frameWidth = region.getRegionWidth() / frameCount;
        for(int i=0; i < frameCount; i++){ // this loop splits the sprite sheet
            frames.add(new TextureRegion(region, i * frameWidth, 0, frameWidth, region.getRegionHeight()));
        }
        this.frameCount = frameCount;
        maxFrameTime = cycleTime / frameCount;
        frame = 0;
    }*/
}
