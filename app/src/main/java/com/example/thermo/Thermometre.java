package com.example.thermo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class Thermometre extends View {
    private float temp;

    public Thermometre(Context context) {
        super(context);
        // Initial temp
        this.temp = 0.0f;
    }

    public Thermometre(Context context, float temp) {
        super(context);
        this.temp = temp;
    }

    public void setTemp(float temp) {
        this.temp = temp;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Dimensions of the view
        float radius = getWidth() / 10f;
        float strokeWidth = getWidth() / 50f;
        float margin = radius + strokeWidth;

        // Calculate the color depending of temperature
        float tempNormalized = Math.max(-20, Math.min(temp, 40));
        float tempRatio = (tempNormalized + 20) / 60;
        int red = (int) (255 * tempRatio);
        int blue = 255 - red;
        int green = (int) (128 - Math.abs(tempRatio - 0.5) * 256);

//        Use the right color and adjust line's width
        @SuppressLint("DrawAllocation") Paint p = new Paint();
        p.setColor(Color.rgb(red, green, blue));
        p.setStrokeWidth(strokeWidth);

        // Adjust position of the depending of temperature
        float heightRatio = tempRatio;
        float yStart = (getHeight() - margin) * (1 - heightRatio) + margin;
        float yEnd = getHeight() - margin;
        float centerX = getWidth() / 2.0f;

        canvas.drawLine(centerX, yStart, centerX, yEnd, p);
        canvas.drawCircle(centerX, getHeight() - margin, radius, p);
    }
}
