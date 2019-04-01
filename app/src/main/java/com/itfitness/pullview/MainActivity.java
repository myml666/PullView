package com.itfitness.pullview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.itfitness.pullview.widget.TaijiView;

public class MainActivity extends AppCompatActivity {
    private RelativeLayout relativelayout;
    private float mPullY = 0;
    private TaijiView taijiYin;
    private TaijiView taijiYang;
    private int mTaijiWidth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        relativelayout = (RelativeLayout) findViewById(R.id.relativelayout);
        taijiYin = (TaijiView) findViewById(R.id.taijiYin);
        taijiYang = (TaijiView) findViewById(R.id.taijiYang);

        taijiYang.setmColorFill(Color.WHITE);
        taijiYang.setmColorInnerCircle(Color.BLACK);
        taijiYang.setmColorStrok(Color.BLACK);
        taijiYang.setmAngle(180);
        taijiYang.post(new Runnable() {
            @Override
            public void run() {
                mTaijiWidth = taijiYang.getMeasuredWidth();
            }
        });
        taijiYang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAnim();
            }
        });
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void startAnim(){
        //太极图旋转的动画
        ValueAnimator valueAnimatorRotation = ValueAnimator.ofFloat(0, 360 * 8+90);
        valueAnimatorRotation.setDuration(10000);
        valueAnimatorRotation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float animatedValue = (float) animation.getAnimatedValue();
                taijiYin.setRotation(animatedValue);
                taijiYang.setRotation(animatedValue);
            }
        });
        //太极图放大的动画
        ValueAnimator valueAnimatorScale = ValueAnimator.ofFloat(1,2f);
        valueAnimatorScale.setDuration(10000);
        valueAnimatorScale.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float animatedValue = (float) animation.getAnimatedValue();
                taijiYang.setScaleX(animatedValue);
                taijiYin.setScaleX(animatedValue);
                taijiYang.setScaleY(animatedValue);
                taijiYin.setScaleY(animatedValue);
            }
        });
        valueAnimatorScale.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                relativelayout.setBackgroundColor(0x00000000);
            }
        });
        //变换黑色部分的颜色
        ValueAnimator valueAnimatorColor1 = ValueAnimator.ofArgb(Color.BLACK, Color.parseColor("#CD1C19"));
        valueAnimatorColor1.setDuration(10000);
        valueAnimatorColor1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int animatedValue = (int) animation.getAnimatedValue();
                taijiYin.setmColorFill(animatedValue);
                taijiYang.setmColorInnerCircle(animatedValue);
            }
        });
        //变换白色部分的颜色
        ValueAnimator valueAnimatorColor2 = ValueAnimator.ofArgb(Color.WHITE, Color.parseColor("#FFFF88"));
        valueAnimatorColor2.setDuration(10000);
        valueAnimatorColor2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int animatedValue = (int) animation.getAnimatedValue();
                taijiYang.setmColorFill(animatedValue);
                taijiYin.setmColorInnerCircle(animatedValue);
            }
        });
        //将描边变为透明
        ValueAnimator valueAnimatorColor3 = ValueAnimator.ofArgb(Color.BLACK, Color.TRANSPARENT);
        valueAnimatorColor3.setDuration(10000);
        valueAnimatorColor3.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int animatedValue = (int) animation.getAnimatedValue();
                taijiYang.setmColorStrok(animatedValue);
                taijiYin.setmColorStrok(animatedValue);
            }
        });
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(valueAnimatorRotation,valueAnimatorScale,valueAnimatorColor1,valueAnimatorColor2,valueAnimatorColor3);
        //太极图分开的动画
        final ValueAnimator valueAnimatorTranslation = ValueAnimator.ofFloat(0,mTaijiWidth*2/4*3);
        valueAnimatorTranslation.setDuration(4000);
        valueAnimatorTranslation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float animatedValue = (float) animation.getAnimatedValue();
                taijiYang.setTranslationX(animatedValue);
                taijiYin.setTranslationX(-animatedValue);
            }
        });
        AnimatorSet animatorSet2 = new AnimatorSet();
        animatorSet2.playSequentially(animatorSet,valueAnimatorTranslation);
        animatorSet2.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                finish();
                overridePendingTransition(0,R.anim.trans_out);
            }
        });
        animatorSet2.start();
    }
}
