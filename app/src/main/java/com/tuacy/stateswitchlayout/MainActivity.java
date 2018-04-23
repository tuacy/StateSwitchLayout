package com.tuacy.stateswitchlayout;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.tuacy.stateswitchlayout.customer.CustomerStateActivity;
import com.tuacy.stateswitchlayout.defualt.DefaultStateActivity;
import com.tuacy.stateswitchlayout.multimodule.MultiModuleStateActivity;

public class MainActivity extends AppCompatActivity {

	private Context mContext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mContext = this;
		findViewById(R.id.button_default_state).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				DefaultStateActivity.startUp(mContext);
			}
		});

		findViewById(R.id.button_customer_state).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				CustomerStateActivity.startUp(mContext);
			}
		});

		findViewById(R.id.button_multi_module_state).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				MultiModuleStateActivity.startUp(mContext);
			}
		});
	}


}
