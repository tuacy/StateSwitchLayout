package com.tuacy.stateswitchlayout.defualt;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.tuacy.stateswitch.StateLayout;
import com.tuacy.stateswitchlayout.R;

public class DefaultStateActivity extends AppCompatActivity {

	public static void startUp(Context context) {
		context.startActivity(new Intent(context, DefaultStateActivity.class));
	}

	private StateLayout mStateLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_defualt_state);
		initView();
	}

	private void initView() {
		mStateLayout = findViewById(R.id.layout_state);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_state, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.menu_state_content:
				mStateLayout.switchState(StateLayout.State.CONTENT);
				break;
			case R.id.menu_state_ing:
				mStateLayout.switchState(StateLayout.State.ING);
				break;
			case R.id.menu_state_empty:
				mStateLayout.switchState(StateLayout.State.EMPTY);
				break;
			case R.id.menu_state_error:
				mStateLayout.switchState(StateLayout.State.ERROR);
				break;
			default:
				break;
		}
		return super.onOptionsItemSelected(item);
	}
}
