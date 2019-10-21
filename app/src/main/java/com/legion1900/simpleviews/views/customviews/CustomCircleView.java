package com.legion1900.simpleviews.views.customviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.legion1900.simpleviews.R;

public class CustomCircleView extends View {

    private static final int DEF_COLOR_ID = R.color.colorAccent;
    private static final int DEF_RADIUS = 100;

    private int radius;
    private int background;
    private Paint paint;

    public CustomCircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.CustomCircleView,
                0,
                0
        );
        try {
            init(array);
        } finally {
            array.recycle();
        }
    }

    private void init(TypedArray array) {
        radius = array.getInt(R.styleable.CustomCircleView_radius, DEF_RADIUS);
        int defColor = getResources().getColor(DEF_COLOR_ID);
        background = array.getColor(R.styleable.CustomCircleView_bg_color, defColor);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(background);
        paint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float centerX = getX() + getWidth() / 2f;
        float centerY = getY() + getHeight() / 2f;
        canvas.drawCircle(centerX, centerY, radius, paint);
    }

//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//
//    }

    public int getRadius() {
        return radius;
    }

    public int getBgColor() {
        return background;
    }

    public void setRadius(int radius) {
        this.radius = radius;
        triggerRedraw();
    }

    public void setBackground(int background) {
        this.background = background;
        triggerRedraw();
    }

    private void triggerRedraw() {
        invalidate();
        requestLayout();
    }
}
