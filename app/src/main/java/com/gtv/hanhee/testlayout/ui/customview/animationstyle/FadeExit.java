package com.gtv.hanhee.testlayout.ui.customview.animationstyle;

import android.animation.ObjectAnimator;
import android.view.View;

import com.gtv.hanhee.testlayout.ui.customview.FlipVerticalSwingEnterDialog.BaseAnimatorSet;


public class FadeExit extends BaseAnimatorSet {

	public FadeExit() {
		duration = 200;
	}
	@Override
	public void setAnimation(View view) {
		animatorSet.playTogether(//
				ObjectAnimator.ofFloat(view, "alpha", 1, 0).setDuration(duration));
	}
}
