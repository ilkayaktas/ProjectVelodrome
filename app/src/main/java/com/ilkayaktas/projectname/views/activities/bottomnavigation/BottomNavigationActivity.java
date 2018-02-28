package com.ilkayaktas.projectname.views.activities.bottomnavigation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.ilkayaktas.projectname.R;
import com.ilkayaktas.projectname.views.activities.base.BaseActivity;
import com.ilkayaktas.projectname.views.adapters.ViewPagerAdapter;
import com.ilkayaktas.projectname.views.fragments.another.AnotherFragment;

import javax.inject.Inject;

/**
 * Created by ilkay on 02/08/2017.
 */

public class BottomNavigationActivity extends BaseActivity implements BottomNavigationMvpView {
	
	@Inject
	BottomNavigationMvpPresenter<BottomNavigationMvpView> mPresenter;

	@BindView(R.id.bottom_navigation) BottomNavigationView bottomNavigationView;
	@BindView(R.id.viewpager) ViewPager viewPager;
	MenuItem prevMenuItem;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getActivityComponent().inject(this);
		
		setUnBinder(ButterKnife.bind(this));
		
		// Attach presenter
		mPresenter.onAttach(BottomNavigationActivity.this);

		initUI();
	}

	@Override
	protected int getActivityLayout() {
		return R.layout.activity_bottomnavigation;
	}

	@Override
	protected void initUI() {
		setupViewPager();
		setViewPagerListener();
		setBottomNavitionViewListener();
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

	private void setupViewPager() {
		ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
		adapter.addFragment(AnotherFragment.newInstance());
		adapter.addFragment(AnotherFragment.newInstance());
		adapter.addFragment(AnotherFragment.newInstance());
		viewPager.setAdapter(adapter);
	}

	private void setViewPagerListener(){
		viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

			}

			@Override
			public void onPageSelected(int position) {
				if (prevMenuItem != null) {
					prevMenuItem.setChecked(false);
				}
				else
				{
					bottomNavigationView.getMenu().getItem(0).setChecked(false);
				}
				Log.d("page", "onPageSelected: "+position);
				bottomNavigationView.getMenu().getItem(position).setChecked(true);
				prevMenuItem = bottomNavigationView.getMenu().getItem(position);

			}

			@Override
			public void onPageScrollStateChanged(int state) {

			}
		});

		/*  //Disable ViewPager Swipe

		viewPager.setOnTouchListener(new View.OnTouchListener()
		{
			@Override
			public boolean onTouch(View v, MotionEvent event)
			{
				return true;
			}
		});

		*/
	}

	private void setBottomNavitionViewListener(){
		bottomNavigationView.setOnNavigationItemSelectedListener(
				new BottomNavigationView.OnNavigationItemSelectedListener() {
					@Override
					public boolean onNavigationItemSelected(@NonNull MenuItem item) {
						switch (item.getItemId()) {
							case R.id.action_call:
								viewPager.setCurrentItem(0);
								break;
							case R.id.action_chat:
								viewPager.setCurrentItem(1);
								break;
							case R.id.action_contact:
								viewPager.setCurrentItem(2);
								break;
						}
						return false;
					}
				});
	}
}
