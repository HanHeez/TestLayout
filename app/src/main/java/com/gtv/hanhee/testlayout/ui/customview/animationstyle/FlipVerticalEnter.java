package com.gtv.hanhee.testlayout.ui.customview.animationstyle;

import android.animation.ObjectAnimator;
import android.view.View;

import com.gtv.hanhee.testlayout.ui.customview.FlipVerticalSwingEnterDialog.BaseAnimatorSet;


public class FlipVerticalEnter extends BaseAnimatorSet {

	public FlipVerticalEnter() {
		duration = 300;
	}
	@Override
	public void setAnimation(View view) {
		animatorSet.playTogether(//
				// ObjectAnimator.ofFloat(view, "rotationX", -90, 0));
				ObjectAnimator.ofFloat(view, "rotationX", 90, 0));
	}
}
