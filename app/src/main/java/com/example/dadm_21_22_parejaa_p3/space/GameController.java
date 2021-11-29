package com.example.dadm_21_22_parejaa_p3.space;

import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.example.dadm_21_22_parejaa_p3.engine.GameEngine;
import com.example.dadm_21_22_parejaa_p3.engine.GameObject;

public class GameController extends GameObject {

    private static final int TIME_BETWEEN_ENEMIES = 500;
    private long currentMillis;
    private List<Asteroid> asteroidPool = new ArrayList<Asteroid>();
    private List<Hunter> hunterPool = new ArrayList<Hunter>();
    private int enemiesSpawned;
    private java.util.Random rand = new Random();

    public GameController(GameEngine gameEngine) {
        // We initialize the pool of items now
        for (int i=0; i<10; i++) {
            asteroidPool.add(new Asteroid(this, gameEngine));
            hunterPool.add(new Hunter(this, gameEngine));
        }
    }

    @Override
    public void startGame() {
        currentMillis = 0;
        enemiesSpawned = 0;
    }

    @Override
    public void onUpdate(long elapsedMillis, GameEngine gameEngine) {
        currentMillis += elapsedMillis;

        long waveTimestamp = enemiesSpawned*TIME_BETWEEN_ENEMIES;
        if (currentMillis > waveTimestamp) {
            // Spawn a new enemy
            if((rand.nextInt((100 - 0) + 1) + 0) > 50){
                Asteroid enemy = asteroidPool.remove(0);
                enemy.init(gameEngine);
                gameEngine.addGameObject(enemy);
            }else{
                Hunter enemy = hunterPool.remove(0);
                enemy.init(gameEngine);
                gameEngine.addGameObject(enemy);
            }
            enemiesSpawned++;
            return;
        }
    }

    @Override
    public void onDraw(Canvas canvas) {
        // This game object does not draw anything
    }

    public void returnToPool(Asteroid asteroid) {
        asteroidPool.add(asteroid);
    }
    public void backToPool(Hunter hunter) {
        hunterPool.add(hunter);
    }
}
