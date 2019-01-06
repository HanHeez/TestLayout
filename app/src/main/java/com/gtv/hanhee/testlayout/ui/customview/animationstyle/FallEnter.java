package com.gtv.hanhee.testlayout.ui.customview.animationstyle;

import android.animation.ObjectAnimator;
import android.view.View;

import com.gtv.hanhee.testlayout.ui.customview.FlipVerticalSwingEnterDialog.BaseAnimatorSet;

public class FallEnter extends BaseAnimatorSet {

    public FallEnter() {
        duration = 300;
    }
    @Override
    public void setAnimation(View view) {
        animatorSet.playTogether(ObjectAnimator.ofFloat(view, "scaleX", 2f, 1.5f, 1f).setDuration(duration),//
                ObjectAnimator.ofFloat(view, "scaleY", 2f, 1.5f, 1f).setDuration(duration),//
                ObjectAnimator.ofFloat(view, "alpha", 0, 1f).setDuration(duration));
    }
}
