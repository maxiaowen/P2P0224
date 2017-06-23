package com.atguigu.p2p0224.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.atguigu.p2p0224.utils.UIUtils;

/**
 * Created by Administrator on 2017/6/23.
 */

public class ProgressView extends View {

    private int width;
    private int height;
    private int strokeWidth = UIUtils.dp2px(10);
    private Paint paint;
    private int sweepAngle = 0;


    public ProgressView(Context context) {
        super(context);
        init();
    }

    public ProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getWidth();
        height = getHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
         /*
        * 第一 画出三个部分 1 画圆 2 画弧 3 画文字
        *
        * */

        //画圆
        paint.setStrokeWidth(strokeWidth);
        paint.setColor(Color.BLACK);
        int cx = width / 2;
        int cy = height / 2;
        int radius = cx - strokeWidth / 2;
        canvas.drawCircle(cx, cy, radius, paint);


        //花弧
        paint.setColor(Color.RED);
        RectF rectF = new RectF();
        rectF.set(strokeWidth / 2, strokeWidth / 2, width - strokeWidth / 2, height - strokeWidth / 2);
        canvas.drawArc(rectF, 0, sweepAngle, false, paint);


        //画文字
        paint.setStrokeWidth(0); //设置画笔的宽度
        String str = sweepAngle + "%";
        paint.setTextSize(30);//设置文字的大小
        Rect rect = new Rect();
        paint.getTextBounds(str, 0, str.length(), rect);//获取文字的宽高
        int textWidth = rect.width(); //文字的宽
        int textHeight = rect.height();//文字的高
        float x = width / 2 - textWidth / 2; //左下顶点的x坐标
        float y = height / 2 + textHeight / 2;//左下顶点的y坐标
        canvas.drawText(str, x, y, paint);

    }

    public void setSweepAngle(int sweepAngle) {
        final ValueAnimator animator = ValueAnimator.ofInt(0, sweepAngle);
        animator.setDuration(5000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                ProgressView.this.sweepAngle = (int) animation.getAnimatedValue();
                invalidate();
            }
        });

        animator.start();
    }
}
