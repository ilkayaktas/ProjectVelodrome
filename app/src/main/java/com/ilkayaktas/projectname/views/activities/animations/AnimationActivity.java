package com.ilkayaktas.projectname.views.activities.animations;

import android.animation.*;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.*;
import android.widget.Button;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.bartoszlipinski.viewpropertyobjectanimator.ViewPropertyObjectAnimator;
import com.ilkayaktas.projectname.R;
import com.ilkayaktas.projectname.views.activities.base.BaseActivity;

import javax.inject.Inject;

/**
 * Created by ilkay on 02/08/2017.
 */

public class AnimationActivity extends BaseActivity implements AnimationMvpView {
	
	@Inject
	AnimationMvpPresenter<AnimationMvpView> mPresenter;

	@BindView(R.id.button) Button button;
	@BindView(R.id.button2) Button button2;
	@BindView(R.id.button3) Button button3;
	@BindView(R.id.button4) Button button4;
	@BindView(R.id.button5) Button button5;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getActivityComponent().inject(this);
		
		setUnBinder(ButterKnife.bind(this));
		
		// Attach presenter
		mPresenter.onAttach(AnimationActivity.this);

	}

	@Override
	protected int getActivityLayout() {
		return R.layout.activity_animation;
	}

	@Override
	protected void onDestroy() {
		mPresenter.onDetach();
		super.onDestroy();
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			
			finish();
			
			return true;
		} else {
			return super.onKeyDown(keyCode, event);
		}
	}

	@OnClick(R.id.button)
	public void onButtonClicked(View v){
		ObjectAnimator oa = ObjectAnimator.ofInt(button,"width", 600);
		oa.setDuration(2000);
		oa.setInterpolator(new AccelerateDecelerateInterpolator());
		oa.start();
	}

	@OnClick(R.id.button2)
	public void onButton2Clicked(View v){
		ObjectAnimator oa = ObjectAnimator.ofInt(button2,"width", 600);
		oa.setDuration(1000);
		oa.setInterpolator(new AccelerateDecelerateInterpolator());

		ObjectAnimator oa1 = ObjectAnimator.ofInt(button2,"width", 400);
		oa1.setDuration(1000);
		oa1.setInterpolator(new AccelerateInterpolator());

		ObjectAnimator oa2 = ObjectAnimator.ofInt(button2,"height", 400);
		oa2.setDuration(3000);
		oa2.setInterpolator(new BounceInterpolator());


		AnimatorSet animator = new AnimatorSet();
		animator.play(oa).before(oa1);
		animator.play(oa1).with(oa2);
		animator.play(oa2);
		animator.start();

		oa.addListener(new Animator.AnimatorListener() {
			@Override
			public void onAnimationStart(Animator animator) {

			}

			@Override
			public void onAnimationEnd(Animator animator) {
				Toast.makeText(AnimationActivity.this, "Bitti", Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onAnimationCancel(Animator animator) {

			}

			@Override
			public void onAnimationRepeat(Animator animator) {

			}
		});
	}


	@OnClick(R.id.button3)
	public void onButton3Clicked(View v){
			button3.animate().
					setInterpolator(new AnticipateOvershootInterpolator())
					.setDuration(800)
				.x(300)
				.rotation(30f)
				.translationX(40f);
	}


	@OnClick(R.id.button4)
	public void onButton4Clicked(View v){
		/*AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(this,
				R.animator.property_animator);
		set.setTarget(v);
		set.start();*/
		ViewPropertyObjectAnimator
				.animate(v)
				.withLayer()
				.alpha(0f)
				.scaleX(0f)
				.scaleY(0f)
				.scrollX(0)
				.scrollY(0)
				.height(0)
				.topPaddingBy(0)
				.setDuration(1000)
				.setInterpolator(new AnticipateInterpolator())
				.addListener(new Animator.AnimatorListener() {
					@Override
					public void onAnimationStart(Animator animation) {
						
					}

					@Override
					public void onAnimationEnd(Animator animation) {
						v.setVisibility(View.GONE);
					}

					@Override
					public void onAnimationCancel(Animator animation) {

					}

					@Override
					public void onAnimationRepeat(Animator animation) {

					}
				})
				.get()
				.start();
	}

	@OnClick(R.id.button5)
	public void onButton5Clicked(View v){
		ValueAnimator xmlAnimator = (ValueAnimator) AnimatorInflater.loadAnimator(this,
				R.animator.animator);
		xmlAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator updatedAnimation) {
				float animatedValue = (float)updatedAnimation.getAnimatedValue();
				v.setTranslationX(animatedValue);
			}
		});

		xmlAnimator.start();
	}
}
