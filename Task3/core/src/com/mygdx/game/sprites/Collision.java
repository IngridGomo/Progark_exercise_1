package com.mygdx.game.sprites;

import com.badlogic.gdx.math.Rectangle;

/*
    * A class for detecting collision and directing collided helicopters in opposite direction
    * Due to lack of time, the class is "hardcoded" to accept only 3 helicopters, and is not scalable.
    * */
public class Collision {
    private Helicopter heli1;
    private Helicopter heli2;
    private Helicopter heli3;

    public Collision(Helicopter helicopter1, Helicopter helicopter2, Helicopter helicopter3) {
        this.heli1 = helicopter1;
        this.heli2 = helicopter2;
        this.heli3 = helicopter3;
    }

    public boolean collides(Helicopter heli1, Helicopter heli2) {
        return heli1.getHeliBounds().overlaps(heli2.getHeliBounds());
    }

    public void bounceOff(Helicopter upperOrRight, Helicopter lowerOrLeft, Boolean y) {
        if(y) {
            upperOrRight.getVelocity().y = Math.abs(upperOrRight.getVelocity().y);
            lowerOrLeft.getVelocity().y = - Math.abs(lowerOrLeft.getVelocity().y);
        } else {
            upperOrRight.getVelocity().x = Math.abs(upperOrRight.getVelocity().x);
        }   lowerOrLeft.getVelocity().x = - Math.abs(lowerOrLeft.getVelocity().x);
    }

    public void update(float dt){
        // Crashing on top of each other:
        if(collides(heli1, heli2) && (heli1.getPosition().y > heli2.getPosition().y)) {
            bounceOff(heli1, heli2, true);
        } if(collides(heli1, heli2) && (heli1.getPosition().y < heli2.getPosition().y)) {
            bounceOff(heli2, heli1, true);
        } if(collides(heli1, heli3) && (heli1.getPosition().y > heli3.getPosition().y)) {
            bounceOff(heli1, heli3, true);
        } if(collides(heli1, heli3) && (heli1.getPosition().y < heli3.getPosition().y)) {
            bounceOff(heli3, heli1, true);
        } if(collides(heli2, heli3) && (heli2.getPosition().y > heli3.getPosition().y)) {
            bounceOff(heli2, heli3, true);
        } if(collides(heli2, heli3) && (heli2.getPosition().y < heli3.getPosition().y)) {
            bounceOff(heli3, heli2, true);
        }
        // Crashing in each others sides:
        if(collides(heli1, heli2) && (heli1.getPosition().x > heli2.getPosition().x)) {
            bounceOff(heli1, heli2, false);
        } if(collides(heli1, heli2) && (heli1.getPosition().x < heli2.getPosition().x)) {
            bounceOff(heli2, heli1, false);
        } if(collides(heli1, heli3) && (heli1.getPosition().x > heli3.getPosition().x)) {
            bounceOff(heli1, heli3, false);
        } if(collides(heli1, heli3) && (heli1.getPosition().x < heli3.getPosition().x)) {
            bounceOff(heli3, heli1, false);
        } if(collides(heli2, heli3) && (heli2.getPosition().x > heli3.getPosition().x)) {
            bounceOff(heli2, heli3, false);
        } if(collides(heli2, heli3) && (heli2.getPosition().x < heli3.getPosition().x)) {
            bounceOff(heli3, heli2, false);
        }
    }
}
