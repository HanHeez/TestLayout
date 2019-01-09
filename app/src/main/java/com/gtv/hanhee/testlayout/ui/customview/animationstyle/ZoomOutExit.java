package com.gtv.hanhee.testlayout.ui.customview.animationstyle;

import android.animation.ObjectAnimator;
import android.view.View;

import com.gtv.hanhee.testlayout.ui.customview.FlipVerticalSwingEnterDialog.BaseAnimatorSet;


public class ZoomOutExit extends BaseAnimatorSet {
	public ZoomOutExit() {
		duration = 300;
	}
	@Override
	public void setAnimation(View view) {
		animatorSet.playTogether(//
				ObjectAnimator.ofFloat(view, "alpha", 1, 0, 0),//
				ObjectAnimator.ofFloat(view, "scaleX", 1, 0.3f, 0),//
				ObjectAnimator.ofFloat(view, "scaleY", 1, 0.3f, 0));//
	}
}
