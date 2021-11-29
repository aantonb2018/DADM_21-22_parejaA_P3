package com.example.dadm_21_22_parejaa_p3.space;

import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.List;

import com.example.dadm_21_22_parejaa_p3.R;
import com.example.dadm_21_22_parejaa_p3.ScaffoldActivity;
import com.example.dadm_21_22_parejaa_p3.engine.GameEngine;
import com.example.dadm_21_22_parejaa_p3.engine.ScreenGameObject;
import com.example.dadm_21_22_parejaa_p3.engine.Sprite;
import com.example.dadm_21_22_parejaa_p3.input.InputController;
import com.example.dadm_21_22_parejaa_p3.sound.GameEvent;

public class SpaceShipPlayer extends Sprite {

    private static final int INITIAL_BULLET_POOL_AMOUNT = 6;
    private static final long TIME_BETWEEN_BULLETS = 250;
    List<Bullet> bullets = new ArrayList<Bullet>();
    private long timeSinceLastFire;

    private long lastFrameChangeTime = 0;
    private int frameLengthInMillisecond = 500;
    private int nextResourceIntegerId = 0;

    private int maxX;
    private int maxY;
    private double speedFactor;
    private int health;

    private int currentShip;

    public SpaceShipPlayer(GameEngine gameEngine){
        super(gameEngine, R.drawable.interprise_a);
        currentShip = ((ScaffoldActivity)gameEngine.mainActivity).getSelectedShip();
        switch(currentShip){
            case 0:
                nextResourceIntegerId = R.drawable.interprise_b;
                health = 3;
                speedFactor = pixelFactor * 100d / 1000d;
                break;
            case 1:
                nextResourceIntegerId = R.drawable.xfeather_b;
                health = 2;
                speedFactor = (pixelFactor * 100d / 1000d)*1.3;
                break;
            case 2:
                nextResourceIntegerId = R.drawable.apellonxiii_b;
                health = 4;
                speedFactor = (pixelFactor * 100d / 1000d)*0.4;
                break;
        }
         // We want to move at 100px per second on a 400px tall screen
        maxX = gameEngine.width - width;
        maxY = gameEngine.height - height;

        gameEngine.setHealth(health);
        initBulletPool(gameEngine);
    }

    private void initBulletPool(GameEngine gameEngine) {
        for (int i=0; i<INITIAL_BULLET_POOL_AMOUNT; i++) {
            bullets.add(new Bullet(gameEngine));
        }
    }

    private Bullet getBullet() {
        if (bullets.isEmpty()) {
            return null;
        }
        return bullets.remove(0);
    }

    void releaseBullet(Bullet bullet) {
        bullets.add(bullet);
    }


    @Override
    public void startGame() {
        positionX = maxX / 2;
        positionY = maxY * 0.9;
    }

    @Override
    public void onUpdate(long elapsedMillis, GameEngine gameEngine) {
        // Get the info from the inputController
        updatePosition(elapsedMillis, gameEngine.theInputController);
        checkFiring(elapsedMillis, gameEngine);
    }

    private void updatePosition(long elapsedMillis, InputController inputController) {
        positionX += speedFactor * inputController.horizontalFactor * elapsedMillis;
        if (positionX < 0) {
            positionX = 0;
        }
        if (positionX > maxX) {
            positionX = maxX;
        }
        /*
        positionY += speedFactor * inputController.verticalFactor * elapsedMillis;
        if (positionY < 0) {
            positionY = 0;
        }
        if (positionY > maxY) {
            positionY = maxY;
        }*/
    }

    private void checkFiring(long elapsedMillis, GameEngine gameEngine) {
        if (gameEngine.theInputController.isFiring && timeSinceLastFire > TIME_BETWEEN_BULLETS) {
            Bullet bullet = getBullet();
            if (bullet == null) {
                return;
            }
            bullet.init(this, positionX + width/2, positionY);
            gameEngine.addGameObject(bullet);
            timeSinceLastFire = 0;
            gameEngine.onGameEvent(GameEvent.LaserFired);
        }
        else {
            timeSinceLastFire += elapsedMillis;
        }
    }

    @Override
    public void onCollision(GameEngine gameEngine, ScreenGameObject otherObject) {
        if (otherObject instanceof Asteroid) {
            Asteroid a = (Asteroid) otherObject;
            a.removeObject(gameEngine);
            health--;
            gameEngine.setHealth(health);
            if(health < 1){
                gameEngine.removeGameObject(this);
            }
            //gameEngine.stopGame();
            gameEngine.onGameEvent(GameEvent.SpaceshipHit);
        }else if (otherObject instanceof Hunter) {
            Hunter h = (Hunter) otherObject;
            h.removeObject(gameEngine);
            health--;
            gameEngine.setHealth(health);
            if(health < 1){
                gameEngine.removeGameObject(this);
            }
            //gameEngine.stopGame();
            gameEngine.onGameEvent(GameEvent.SpaceshipHit);
        }else if (otherObject instanceof Bomb) {
            health--;
            gameEngine.setHealth(health);
            if(health < 1){
                gameEngine.removeGameObject(this);
            }
            //gameEngine.stopGame();
            gameEngine.onGameEvent(GameEvent.SpaceshipHit);
        }
    }

    @Override
    public void onDraw(Canvas canvas) {
        long time = System.currentTimeMillis();
            if (time > lastFrameChangeTime + frameLengthInMillisecond) {
                lastFrameChangeTime = time;
                super.setBitmap(nextResourceIntegerId);
                switch (currentShip){
                    case 0:
                        if (nextResourceIntegerId == R.drawable.interprise_a) {
                            nextResourceIntegerId = R.drawable.interprise_b;
                        } else {
                            nextResourceIntegerId = R.drawable.interprise_a;
                        }
                        break;
                    case 1:
                        if (nextResourceIntegerId == R.drawable.xfeather_a) {
                            nextResourceIntegerId = R.drawable.xfeather_b;
                        } else {
                            nextResourceIntegerId = R.drawable.xfeather_a;
                        }
                        break;
                    case 2:
                        if (nextResourceIntegerId == R.drawable.apellonxiii_a) {
                            nextResourceIntegerId = R.drawable.apellonxiii_b;
                        } else {
                            nextResourceIntegerId = R.drawable.apellonxiii_a;
                        }
                        break;
                }
            }
        super.onDraw(canvas);
    }

    public int getHealth(){
        return health;
    }
}
