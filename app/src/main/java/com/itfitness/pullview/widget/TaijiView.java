package com.itfitness.pullview.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @ProjectName: PullView
 * @Package: com.itfitness.pullview.widget
 * @ClassName: TaijiView
 * @Description: java类作用描述 ：
 * @Author: 作者名：lml
 * @CreateDate: 2019/3/28 16:33
 * @UpdateUser: 更新者：
 * @UpdateDate: 2019/3/28 16:33
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */

public class TaijiView extends View {
    private Paint mPaint;
    private Path mPath;
    private float mRadiu;
    private float mStrokWidth = 1;
    private float mAngle =0;
    private RectF rectF2;
    private int mColorFill = Color.BLACK;
    private int mColorStrok = Color.BLACK;
    private int mColorInnerCircle = Color.WHITE;
    public TaijiView(Context context) {
        super(context);
        init();
    }

    public TaijiView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TaijiView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        setLayerType(LAYER_TYPE_SOFTWARE,null);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setDither(true);
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(mStrokWidth);
        mPath = new Path();
    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mRadiu = Math.min(w,h)/2;
        RectF rectF = new RectF(mStrokWidth, h / 2 - mRadiu, w-mStrokWidth, h / 2 + mRadiu);
        mPath.addArc(rectF,0,180);//添加最外大圆弧形
        rectF2 = new RectF(mStrokWidth, h / 2 - mRadiu/2, w/2, h / 2 + mRadiu/2);
        mPath.arcTo(rectF2,180,180,false);//添加头小圆弧形
        RectF rectF3 = new RectF(w/2, h / 2 - mRadiu/2, w-mStrokWidth, h / 2 + mRadiu/2);
        mPath.arcTo(rectF3,180,-180,false);//添加尾小圆弧形
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.rotate(mAngle,getWidth()/2,getHeight()/2);

        mPaint.setColor(mColorStrok);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(mPath,mPaint);

        mPaint.setColor(mColorFill);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawPath(mPath,mPaint);

        mPaint.setColor(mColorInnerCircle);
        canvas.drawCircle(rectF2.centerX(),rectF2.centerY(),mRadiu/6,mPaint);
    }

    /**
     * 修改太极图填充的颜色
     * @param mColorFill
     */
    public void setmColorFill(int mColorFill) {
        this.mColorFill = mColorFill;
        invalidate();
    }

    /**
     * 修改太极图描边的颜色
     * @param mColorStrok
     */
    public void setmColorStrok(int mColorStrok) {
        this.mColorStrok = mColorStrok;
        invalidate();
    }

    /**
     * 修改太极图内部小圆的颜色
     * @param mColorInnerCircle
     */
    public void setmColorInnerCircle(int mColorInnerCircle) {
        this.mColorInnerCircle = mColorInnerCircle;
        invalidate();
    }

    /**
     * 设置太极图的角度
     * @param mAngle
     */
    public void setmAngle(float mAngle) {
        this.mAngle = mAngle;
        invalidate();
    }
}
