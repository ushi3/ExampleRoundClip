package com.ushi.example.roundclip;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.FrameLayout;


public class RoundClipFrameLayout extends FrameLayout {

    private final Path mPath = new Path();

    private final RectF mRect = new RectF();

    private int mCornerRadius;

    public RoundClipFrameLayout(Context context) {
        this(context, null);
    }

    public RoundClipFrameLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundClipFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.RoundClipFrameLayout, defStyleAttr, 0);
        mCornerRadius = ta.getDimensionPixelSize(R.styleable.RoundClipFrameLayout_cornerRadius, 0);

        ta.recycle();
    }

    public void setCornerRadius(int radiusPx) {
        if (mCornerRadius != radiusPx) {
            mCornerRadius = radiusPx;
            rebuildPath();
            invalidate();
        }
    }

    private void rebuildPath() {
        mPath.reset();
        mPath.addRoundRect(mRect, mCornerRadius, mCornerRadius, Path.Direction.CW);
        mPath.close();
    }

    @Override
    protected void onSizeChanged(int width, int height, int oldWidth, int oldHeight) {
        super.onSizeChanged(width, height, oldWidth, oldHeight);

        mRect.set(0, 0, width, height);
        rebuildPath();
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        int save = canvas.save();
        canvas.clipPath(mPath);
        super.dispatchDraw(canvas);
        canvas.restoreToCount(save);
    }
}
