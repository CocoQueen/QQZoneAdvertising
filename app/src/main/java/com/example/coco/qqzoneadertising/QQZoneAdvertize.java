package com.example.coco.qqzoneadertising;

import android.annotation.SuppressLint;
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
    private Paint paint;//初始化画笔
    private int screenHeight;//屏幕高度
    private Canvas topCanvas;//初始化顶层画布
    private int height;
    private int width;
    private float r = 0;
    private RectF rectF;//矩形的绘制
    private Bitmap bottom;//底层广告图
    private Bitmap top;//顶层广告图
    private Bitmap topBg;//顶层画布
    private float offsetX = 100;
    private float offsetY = 100;
    private int[] images = {R.drawable.lyftop, R.drawable.lyibottom};//存放两张广告图的数组

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
        //画笔的一些设置
        paint = new Paint();
        paint.setAlpha(0);
        //设置画笔的绘图模式   两层重叠模式
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(0);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
    }

    @Override
    @SuppressLint("DrawAllocation")
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //在画布上绘图
        topCanvas.drawBitmap(top, null, rectF, null);
        //绘制底层广告
        canvas.drawBitmap(bottom, null, rectF, null);
        //绘制顶层广告
        canvas.drawBitmap(topBg, null, rectF, null);
        topCanvas.drawCircle(width - offsetX, height - offsetY, r, paint);
    }

    public void setmRv(ViewGroup parent) {
        if (!(parent instanceof RecyclerView)){
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

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //获取宽高
        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
        rectF = new RectF(0, 0, width, height);
        bottom = ((BitmapDrawable) getResources().getDrawable(images[0])).getBitmap();
        top = ((BitmapDrawable) getResources().getDrawable(images[1])).getBitmap();
        topBg = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        //进行绘图
        topCanvas = new Canvas(topBg);
    }

    private void getWidthAndHeight() {//获取宽高
        Resources resources = this.getResources();//获取资源
        //获取DisplayMetrics对象，再获取屏幕的参数
        DisplayMetrics dm = resources.getDisplayMetrics();
        screenHeight = dm.heightPixels;
    }


    private void getLocation() {
        int[] location = new int[2];
        //获取view在屏幕上的坐标
        this.getLocationOnScreen(location);
        int y = location[1];
        //？？
        int height = y + getHeight();//广告图底部距离屏幕顶部的高度
        if (y > 0 && screenHeight >= height) {//？？？
            r = (float) ((screenHeight - height) * 1.5);
            topCanvas.drawCircle(width - offsetX, this.height - offsetY, r, paint);
        } else {//？？？
            if (r < width)
                r = 0;
        }
        invalidate();//重绘
    }
}

