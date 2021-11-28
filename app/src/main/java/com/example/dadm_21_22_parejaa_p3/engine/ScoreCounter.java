package com.example.dadm_21_22_parejaa_p3.engine;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;


public class ScoreCounter extends GameObject{
    private final float textWidth;
    private final float textHeight;

    private Paint paint;
    private long totalMillis;
    private int draws;
    private float framesPerSecond;

    private String scoreText = "";
    private String healthText = "";

    public ScoreCounter(GameEngine gameEngine) {

        paint = new Paint();
        paint.setTextAlign(Paint.Align.CENTER);
        textHeight = (float) (25 * gameEngine.pixelFactor);
        textWidth = (float) (50 * gameEngine.pixelFactor);
        paint.setTextSize(textHeight / 2);
    }

    @Override
    public void startGame() {
        totalMillis = 0;
    }

    @Override
    public void onUpdate(long elapsedMillis, GameEngine gameEngine) {
        totalMillis += elapsedMillis;
        if (totalMillis > 1000) {
            framesPerSecond = draws * 1000 / totalMillis;
            scoreText = gameEngine.score + " pts";
            healthText = "Health: " + gameEngine.health;
            totalMillis = 0;
            draws = 0;
        }
    }

    @Override
    public void onDraw(Canvas canvas) {
        paint.setColor(Color.BLACK);
        canvas.drawRect((int) (canvas.getWidth() - textWidth), (int) (canvas.getHeight() - textHeight), canvas.getWidth(), canvas.getHeight(), paint);
        canvas.drawRect(0, (int) (canvas.getHeight() - textHeight), textWidth, canvas.getHeight(), paint);
        paint.setColor(Color.WHITE);
        canvas.drawText(scoreText, (int) (canvas.getWidth() - textWidth / 2), (int) (canvas.getHeight() - textHeight / 2), paint);
        canvas.drawText(healthText, textWidth / 2, (int) (canvas.getHeight() - textHeight / 2), paint);
        //canvas.drawText(scoreText, textWidth / 2, (int) (canvas.getHeight() - textHeight / 2), paint);
        draws++;
    }
}
