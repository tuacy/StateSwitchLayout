package com.tuacy.stateswitchlayout.multimodule;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.tuacy.stateswitch.StateLayout;
import com.tuacy.stateswitchlayout.R;

public class MultiModuleStateActivity extends AppCompatActivity {

	public static void startUp(Context context) {
		context.startActivity(new Intent(context, MultiModuleStateActivity.class));
	}

	private StateLayout mStateLayout0;
	private StateLayout mStateLayout1;
	private StateLayout mStateLayout2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_multi_module_state);
		initView();
	}

	private void initView() {

		mStateLayout0 = findViewById(R.id.layout_state_0);
		mStateLayout1 = findViewById(R.id.layout_state_1);
		mStateLayout2 = findViewById(R.id.layout_state_2);
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
				mStateLayout0.switchState(StateLayout.State.CONTENT);
				mStateLayout1.switchState(StateLayout.State.CONTENT);
				mStateLayout2.switchState(StateLayout.State.CONTENT);
				break;
			case R.id.menu_state_ing:
				mStateLayout0.switchState(StateLayout.State.ING, getString(R.string.module_0_loading));
				mStateLayout1.switchState(StateLayout.State.ING, getString(R.string.module_1_loading));
				mStateLayout2.switchState(StateLayout.State.ING, getString(R.string.module_2_loading));
				break;
			case R.id.menu_state_empty:
				mStateLayout0.switchState(StateLayout.State.EMPTY, getString(R.string.module_0_empty));
				mStateLayout1.switchState(StateLayout.State.EMPTY, getString(R.string.module_1_empty));
				mStateLayout2.switchState(StateLayout.State.EMPTY, getString(R.string.module_2_empty));
				break;
			case R.id.menu_state_error:
				mStateLayout0.switchState(StateLayout.State.ERROR, getString(R.string.module_0_error));
				mStateLayout1.switchState(StateLayout.State.ERROR, getString(R.string.module_1_error));
				mStateLayout2.switchState(StateLayout.State.ERROR, getString(R.string.module_2_error));
				break;
			default:
				break;
		}
		return super.onOptionsItemSelected(item);
	}
}
