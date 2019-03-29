package com.itfitness.pullview.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @ProjectName: PullView
 * @Package: com.itfitness.pullview.widget
 * @ClassName: PullView
 * @Description: java类作用描述 ：
 * @Author: 作者名：lml
 * @CreateDate: 2019/3/28 15:13
 * @UpdateUser: 更新者：
 * @UpdateDate: 2019/3/28 15:13
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */

public class PullView extends View {
    private Paint mPaint;
    private float mCircleRadiu;
    private float mCircleX,mCircleY;
    public static final float MAX_HEIGHT = 300;
    private float mProgress = 0;
    public PullView(Context context) {
        super(context);
        init();
    }

    public PullView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PullView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.RED);
        mPaint.setDither(true);//防抖动
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureWidth(widthMeasureSpec),measureHeight(heightMeasureSpec));
    }

    /**
     * 测量宽度
     * @param widthMeasureSpec
     * @return
     */
    private int measureWidth(int widthMeasureSpec){
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int size = MeasureSpec.getSize(widthMeasureSpec);
        int minWidth = (int) (mCircleRadiu*2+getPaddingLeft()+getPaddingRight());
        switch (mode){
            case MeasureSpec.EXACTLY:
//                精确模式
               return size;
            case MeasureSpec.AT_MOST:
//                最大模式
                return Math.min(minWidth,size);
            case MeasureSpec.UNSPECIFIED:
//                未知模式
                return minWidth;
            default:
                return minWidth;
        }

    }
    /**
     * 测量宽度
     * @param heightMeasureSpec
     * @return
     */
    private int measureHeight(int heightMeasureSpec){
        int mode = MeasureSpec.getMode(heightMeasureSpec);
        int size = MeasureSpec.getSize(heightMeasureSpec);
        int minHeight = (int) (mCircleRadiu * 2 * mProgress + getPaddingTop() + getPaddingBottom());
        switch (mode){
            case MeasureSpec.EXACTLY:
//                精确模式
                return size;
            case MeasureSpec.AT_MOST:
//                最大模式
                return Math.min(minHeight,size);
            case MeasureSpec.UNSPECIFIED:
//                未知模式
                return minHeight;
            default:
                return minHeight;
        }

    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mCircleX = w/2;
        mCircleY = h/2;
        mCircleRadiu = w/2*0.5f;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画圆
        canvas.drawCircle(mCircleX,mCircleY,mCircleRadiu,mPaint);
    }

    public void setProgress(float progress) {
        mProgress = progress;
        requestLayout();//请求重新测量
    }
}
