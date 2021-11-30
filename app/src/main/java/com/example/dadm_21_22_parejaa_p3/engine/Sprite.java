package com.example.dadm_21_22_parejaa_p3.engine;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

public abstract class Sprite extends ScreenGameObject {

    protected double rotation;

    protected double pixelFactor;

    private Bitmap bitmap;

    private final Matrix matrix = new Matrix();

    private Paint pX = new Paint();

    private GameEngine theGameEngine;

    protected Sprite (GameEngine gameEngine, int drawableRes) {
        this.theGameEngine = gameEngine;

        Resources r = gameEngine.getContext().getResources();
        Drawable spriteDrawable = r.getDrawable(drawableRes);

        this.pixelFactor = gameEngine.pixelFactor;

        this.height = (int) (spriteDrawable.getIntrinsicHeight() * this.pixelFactor);
        this.width = (int) (spriteDrawable.getIntrinsicWidth() * this.pixelFactor);

        this.bitmap = ((BitmapDrawable) spriteDrawable).getBitmap();

        radius = Math.max(height, width)/2;
    }

    @Override
    public void onDraw(Canvas canvas) {
        if (positionX > canvas.getWidth()
                || positionY > canvas.getHeight()
                || positionX < - width
                || positionY < - height) {
            return;
        }
        matrix.reset();
        matrix.postScale((float) pixelFactor, (float) pixelFactor);
        matrix.postTranslate((float) positionX, (float) positionY);
        matrix.postRotate((float) rotation, (float) (positionX + width/2), (float) (positionY + height/2));
        //pX.setColor(Color.YELLOW);
        //canvas.drawRect(mBoundingRect.left, mBoundingRect.top, mBoundingRect.right, mBoundingRect.bottom, pX);
        canvas.drawBitmap(bitmap, matrix, null);
    }

    public void setBitmap(int drawableRes) {
        Resources r = theGameEngine.getContext().getResources();
        Drawable spriteDrawable = r.getDrawable(drawableRes);
        this.bitmap = ((BitmapDrawable) spriteDrawable).getBitmap();
    }
}
