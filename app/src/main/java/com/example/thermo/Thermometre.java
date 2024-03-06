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
        float radius = getWidth() / 12f;
        float strokeWidth = getWidth() / 50f;
        float margin = radius * 2;

        // Adjusted for 0-100 range
        float tempNormalized = Math.max(0, Math.min(temp, 100));
        float tempRatio = tempNormalized / 100;
        int red = (int) (255 * tempRatio);
        int blue = 255 - red;
        int green = (int) (128 - Math.abs(tempRatio - 0.5) * 256);

        Paint p = new Paint();
        p.setColor(Color.rgb(red, green, blue));
        p.setStrokeWidth(strokeWidth);

        // Adjust the text size for the numbers
        Paint textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(radius * 1.2f);

        // Position of the thermometer elements based on temperature
        float heightRatio = tempRatio;
        float yStart = (getHeight() - margin) * (1 - heightRatio) + margin / 2;
        float yEnd = getHeight() - margin / 2;
        float centerX = getWidth() / 2.0f;

        // Draw the thermometer's mercury line and bulb
        canvas.drawLine(centerX, yStart, centerX, yEnd, p);
        canvas.drawCircle(centerX, getHeight() - margin / 2, radius, p);

        // Draw markings and numbers
        Paint linePaint = new Paint();
        linePaint.setColor(Color.BLACK);
        linePaint.setStrokeWidth(strokeWidth / 2);

        for (int i = 0; i <= 10; i++) {
            float yMark = (getHeight() - margin) * (1 - i / 10f) + margin / 2;
            canvas.drawLine(centerX - radius * 1.5f, yMark, centerX - radius * 2.5f, yMark, linePaint);
            canvas.drawLine(centerX + radius * 1.5f, yMark, centerX + radius * 2.5f, yMark, linePaint);
            canvas.drawText(String.valueOf(i * 10), centerX - radius * 5f, yMark + radius / 4, textPaint);
        }
    }
}
