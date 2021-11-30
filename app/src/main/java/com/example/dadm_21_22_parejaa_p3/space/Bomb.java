package com.example.dadm_21_22_parejaa_p3.space;

import android.os.Debug;

import com.example.dadm_21_22_parejaa_p3.R;
import com.example.dadm_21_22_parejaa_p3.engine.GameEngine;
import com.example.dadm_21_22_parejaa_p3.engine.ScreenGameObject;
import com.example.dadm_21_22_parejaa_p3.engine.Sprite;
import com.example.dadm_21_22_parejaa_p3.sound.GameEvent;

import java.util.ArrayList;
import java.util.List;

public class Bomb extends Sprite {

    private double speedFactor;

    private Hunter parent;

    public Bomb(GameEngine gameEngine){
        super(gameEngine, R.drawable.bomb_one);

        speedFactor = (gameEngine.pixelFactor * -300d / 1000d);
    }

    @Override
    public void startGame() {}

    @Override
    public void onUpdate(long elapsedMillis, GameEngine gameEngine) {
        positionY -= speedFactor * elapsedMillis;
        if (positionY < -height) {
            gameEngine.removeGameObject(this);
            // And return it to the pool
            parent.releaseBullet(this);
        }
    }


    public void init(Hunter parentHunter, double initPositionX, double initPositionY) {
        positionX = initPositionX - width/2;
        positionY = initPositionY - height/2;
        parent = parentHunter;
    }

    private void removeObject(GameEngine gameEngine) {
        gameEngine.removeGameObject(this);
        // And return it to the pool
        parent.releaseBullet(this);
    }

    @Override
    public void onCollision(GameEngine gameEngine, ScreenGameObject otherObject) {
        if (otherObject instanceof Bullet) {
            // Remove both from the game (and return them to their pools)
            removeObject(gameEngine);
            // Add some score
        }else if (otherObject instanceof SpaceShipPlayer) {
            // Remove both from the game (and return them to their pools)
            removeObject(gameEngine);
            // Add some score
        }
    }
}
