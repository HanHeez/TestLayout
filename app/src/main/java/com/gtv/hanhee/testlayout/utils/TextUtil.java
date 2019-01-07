package com.gtv.hanhee.testlayout.utils;

import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

public class TextUtil {
    public static boolean showTip = false;
    public static void showTipViewAndDelayClose(String tip, TextView txtTip, Handler handler) {
        showTip = !showTip;
        txtTip.setText(tip);
        if (showTip) {
            txtTip.setVisibility(View.VISIBLE);
            Animation mShowAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                    Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                    1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
            mShowAction.setDuration(200);
            txtTip.startAnimation(mShowAction);
            mShowAction.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            Animation mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF,
                                    0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                                    Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                                    1.0f);
                            mHiddenAction.setDuration(200);
                            txtTip.startAnimation(mHiddenAction);
                            mHiddenAction.setAnimationListener(new Animation.AnimationListener() {
                                @Override
                                public void onAnimationStart(Animation animation) {
                                }

                                @Override
                                public void onAnimationEnd(Animation animation) {
                                    showTip = false;
                                    handler.removeCallbacksAndMessages(null);
                                    txtTip.setVisibility(View.GONE);
                                }

                                @Override
                                public void onAnimationRepeat(Animation animation) {

                                }
                            });
                        }
                    }, 2000);

                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
        } else {
            Animation mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF,
                    0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                    Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                    1.0f);
            mHiddenAction.setDuration(200);
            txtTip.startAnimation(mHiddenAction);
            mHiddenAction.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    showTip = false;
                    handler.removeCallbacksAndMessages(null);
                    txtTip.setVisibility(View.GONE);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });

        }
    }


}
