package org.liutz.bluetooth;

import android.support.v7.app.ActionBarActivity;
import android.bluetooth.BluetoothAdapter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;

public class MainActivity extends ActionBarActivity {
	Button b;
	View mView = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mView = this.findViewById(R.id.tv);
	}

	int i = -200;
	int j = 2;

	public void onButton(View view) {
		if (j > 0) {
			slideview(mView, 0, i);
			j--;

		} else {
			slideview(mView, 0, -i);
			j++;
		}
	}

	public void slideview(final View view, final float p1, final float p2) {
		TranslateAnimation animation = new TranslateAnimation(0, 0, p1, p2);
		animation.setInterpolator(new OvershootInterpolator());
		animation.setDuration(400);
		animation.setStartOffset(0);
		animation.setAnimationListener(new Animation.AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {

			}

			@Override
			public void onAnimationRepeat(Animation animation) {
			}

			@Override
			public void onAnimationEnd(Animation animation) {
				int left = view.getLeft();
				int top = view.getTop() + (int) (p2 - p1);
				int width = view.getWidth();
				int height = view.getHeight();
				view.clearAnimation();
				view.layout(left, top, left + width, top + height);
			}
		});
		view.startAnimation(animation);
	}

	public boolean supportBluetooth() {
		BluetoothAdapter mBluetoothAdapter = BluetoothAdapter
				.getDefaultAdapter();
		if (mBluetoothAdapter == null) {
			// Device does not support Bluetooth
			return false;
		}
		return true;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
