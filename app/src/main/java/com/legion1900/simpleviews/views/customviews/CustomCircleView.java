package com.legion1900.simpleviews.views.customviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.legion1900.simpleviews.R;

public class CustomCircleView extends View {

    private static final int DEF_COLOR_ID = R.color.colorAccent;
    private static final int DEF_RADIUS = 100;

    /*
    * Circle properties.
    * */
    private float radius;
    private float cX;
    private float cY;
    /*
    * Drawing properties.
    * */
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
        int defColor = getResources().getColor(DEF_COLOR_ID);
        radius = array.getDimensionPixelSize(R.styleable.CustomCircleView_radius, DEF_RADIUS);
        Log.d("Test", radius + "");
        background = array.getColor(R.styleable.CustomCircleView_bg_color, defColor);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(background);
        paint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        TODO: override it properly
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        float diameter = radius * 2;
        /*
        * Desired values.
        * */
        int width = Math.round(diameter + getPaddingStart() + getPaddingEnd());
        int height = Math.round(diameter + getPaddingTop() + getPaddingBottom());
        /*
        * Resolved values.
        * */
        width = resolveSize(width, widthMeasureSpec);
        height = resolveSize(height, heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float centerX = getWidth() / 2;
        float centerY = getHeight() / 2;
        canvas.drawCircle(centerX, centerY, radius, paint);
    }


//    private float calculateCenter() {
//
//    }
//
//    private float calculateRealRadius() {
//
//    }

    public float getRadius() {
        return radius;
    }

    public int getBgColor() {
        return background;
    }

    public void setRadius(float radius) {
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
