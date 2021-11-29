package com.example.dadm_21_22_parejaa_p3.space;

import android.graphics.Canvas;

import com.example.dadm_21_22_parejaa_p3.R;
import com.example.dadm_21_22_parejaa_p3.engine.GameEngine;
import com.example.dadm_21_22_parejaa_p3.engine.ScreenGameObject;
import com.example.dadm_21_22_parejaa_p3.engine.Sprite;
import com.example.dadm_21_22_parejaa_p3.sound.GameEvent;

import java.util.ArrayList;
import java.util.List;

public class Hunter extends Sprite {

    private final GameController gameController;
    private static final int INITIAL_BULLET_POOL_AMOUNT = 1;
    List<Bomb> bombs = new ArrayList<Bomb>();

    private double speed;
    private double speedX;
    private double speedY;
    private double rotationSpeed;

    private int points = 20;

    public Hunter(GameController gameController, GameEngine gameEngine) {
        super(gameEngine, R.drawable.hunter_a);
        this.speed = 200d * pixelFactor/1000d;
        this.gameController = gameController;
    }

    public void init(GameEngine gameEngine) {
        // They initialize in a [-30, 30] degrees angle
        //double angle = gameEngine.random.nextDouble()*Math.PI/3d-Math.PI/6d;
        //speedX = speed * Math.sin(angle);
        speedY = speed * 0.75;
        // Asteroids initialize in the central 50% of the screen horizontally
        positionX = gameEngine.random.nextInt(((int)(gameEngine.width*0.85) - (int)(gameEngine.width*0.15)) + 1) + (int)(gameEngine.width*0.15);//(gameEngine.width)+gameEngine.width/4;
        // They initialize outside of the screen vertically
        positionY = -height;
        //rotationSpeed = angle*(180d / Math.PI)/250d; // They rotate 4 times their ange in a second.
        //rotation = gameEngine.random.nextInt(360);
        initBombPool(gameEngine);
    }

    @Override
    public void startGame() {
    }

    public void removeObject(GameEngine gameEngine) {
        // Return to the pool
        gameEngine.removeGameObject(this);
        gameController.backToPool(this);
    }

    public int getPoints(){
        return points;
    }

    private void initBombPool(GameEngine gameEngine) {
        for (int i=0; i<INITIAL_BULLET_POOL_AMOUNT; i++) {
            bombs.add(new Bomb(gameEngine));
        }
    }

    private Bomb getBomb() {
        if (bombs.isEmpty()) {
            return null;
        }
        return bombs.remove(0);
    }

    void releaseBullet(Bomb bomb) {
        bombs.add(bomb);
    }

    @Override
    public void onUpdate(long elapsedMillis, GameEngine gameEngine) {
        //positionX += speedX * elapsedMillis;
        positionY += speedY * elapsedMillis;
        /*
        rotation += rotationSpeed * elapsedMillis;
        if (rotation > 360) {
            rotation = 0;
        }
        else if (rotation < 0) {
            rotation = 360;
        }*/
        // Check of the sprite goes out of the screen and return it to the pool if so
        if (positionY > gameEngine.height) {
            // Return to the pool
            gameEngine.removeGameObject(this);
            gameController.backToPool(this);
        }
        checkBombing(elapsedMillis, gameEngine);
    }

    private void checkBombing(long elapsedMillis, GameEngine gameEngine) {
        Bomb bomb = getBomb();
        if (bomb == null) {
            return;
        }
        bomb.init(this, positionX + width/2, positionY);
        gameEngine.addGameObject(bomb);
        //timeSinceLastFire = 0;
        //gameEngine.onGameEvent(GameEvent.LaserFired);

    }

    @Override
    public void onCollision(GameEngine gameEngine, ScreenGameObject otherObject) {

    }

}

