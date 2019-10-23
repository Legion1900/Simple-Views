package com.legion1900.simpleviews.views.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

public class CustomReverseLayout extends ViewGroup {
    public CustomReverseLayout(Context context) {
        super(context);
    }

    public CustomReverseLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomReverseLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /*
     * Should return true for scrollable ViewGroup.
     * This is done to prevent triggering click event when user tries to scroll.
     * */
    @Override
    public boolean shouldDelayChildPressedState() {
        /*
         * As this implementation doesn't support scrolling there is no need in extra delay.
         * */
        return false;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int count = getChildCount();
        int height = 0;
        int width = 0;
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() == View.GONE) continue;
            measureChildWithMargins(
                    child,
                    widthMeasureSpec,
                    0,
                    heightMeasureSpec,
                    0
            );
            width = Math.max(width, child.getMeasuredWidth());
            height += child.getMeasuredHeight();
        }
        width += getPaddingStart() + getPaddingEnd();
        height += getPaddingTop() + getPaddingBottom();
        width = resolveSize(width, widthMeasureSpec);
        height = resolveSize(height, heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    //    TODO: see if there is call to it after addView finishes!
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        Log.d("Test", "top=" + top);
        int currentTop = getPaddingTop();
        int count = getChildCount();
        for (int i = count - 1; i >= 0; i--) {
            View child = getChildAt(i);
            if (child.getVisibility() == View.GONE) continue;
            MarginLayoutParams params = (MarginLayoutParams) child.getLayoutParams();

        }
    }

    /*
     * Returns a new set of layout parameters based on the supplied attributes set.
     * */
    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    /*
     * This one is called when LayoutParams of parent for this view
     * does not pass checkLayoutParams.
     * */
    @Override
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
        return new MarginLayoutParams(p);
    }

    /*
     * Used by addView when a view argument has no layout parameters.
     * */
    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT
        );
    }
}
