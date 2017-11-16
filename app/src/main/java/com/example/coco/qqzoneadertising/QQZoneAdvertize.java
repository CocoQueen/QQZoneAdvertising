package com.example.coco.qqzoneadertising;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by coco on 2017/11/16.
 */

public class QQZoneAdvertize extends View {
    private Paint paint;
    private int screenHeight;
    private Canvas topCanVas;
    private int height;
    private int width;
    private float r = 0;
    private RectF rectF;
    private Bitmap bottom;
    private Bitmap topBg;
    private Bitmap top;
    private float offsetX = 100;
    private float offsetY = 100;
    private int[] images = {R.drawable.lyibottom, R.drawable.lyftop};

    public QQZoneAdvertize(Context context) {
        super(context);
        init();
    }

    public QQZoneAdvertize(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        getWidthAndHeight();
        paint = new Paint();
        paint.setAlpha(0);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(0);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        topCanVas.drawBitmap(top, null, rectF, null);
        canvas.drawBitmap(bottom, null, rectF, null);
        canvas.drawBitmap(topBg, null, rectF, null);
        topCanVas.drawCircle(width - offsetX, height - offsetY, r, paint);


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
        rectF = new RectF(0, 0, width, height);
        bottom = ((BitmapDrawable) getResources().getDrawable(images[0])).getBitmap();
        top = ((BitmapDrawable) getResources().getDrawable(images[1])).getBitmap();
        topBg = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        topCanVas = new Canvas(topBg);
    }

    public void getWidthAndHeight() {
        Resources resources = this.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        screenHeight = dm.heightPixels;
    }

    private void getLocation() {
        int[] location = new int[2];
        this.getLocationOnScreen(location);
        int y = location[1];
        int height = y + getHeight();

        if (y > 0 && screenHeight >= height) {
            r = (float) ((screenHeight - height) * 1.5);
            topCanVas.drawCircle(width - offsetX, this.height - offsetY, r, paint);
        } else {
            if (r < width) {
                r = 0;
            }

        }
        invalidate();
    }

    public void setmRv(ViewGroup parent) {
        if (!(parent instanceof RecyclerView)) {
            throw new RuntimeException("必须是android.support.v7.widget.RecyclerView");
        }
        ((RecyclerView) parent).addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                getLocation();
            }
        });
    }

    public void setImages(int[] images) {
        bottom = ((BitmapDrawable) getResources().getDrawable(images[0])).getBitmap();
        top = ((BitmapDrawable) getResources().getDrawable(images[1])).getBitmap();
    }
}

