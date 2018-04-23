package com.tuacy.stateswitchlayout.customer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tuacy.stateswitch.StateEmptyInterface;
import com.tuacy.stateswitch.StateErrorInterface;
import com.tuacy.stateswitch.StateLayout;
import com.tuacy.stateswitchlayout.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.tuacy.stateswitch.StateLayout.State.CONTENT;
import static com.tuacy.stateswitch.StateLayout.State.EMPTY;
import static com.tuacy.stateswitch.StateLayout.State.ERROR;

public class CustomerStateActivity extends AppCompatActivity {

	public static void startUp(Context context) {
		context.startActivity(new Intent(context, CustomerStateActivity.class));
	}


	private StateLayout        mStateLayout;
	private SmartRefreshLayout mSmartRefreshLayout;
	private AdapterItem        mAdapter;
	private Handler            mMainHandler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_customer_state);
		mMainHandler = new Handler(Looper.getMainLooper());
		initView();
		initEvent();
		initData();
	}

	private void initView() {
		mStateLayout = findViewById(R.id.layout_state);
		mStateLayout.setEmptyStateView(new CustomerEmptyView(this));
		mStateLayout.setIngStateView(new CustomerIngView(this));
		mStateLayout.setErrorStateView(new CustomerErrorView(this));
		mSmartRefreshLayout = mStateLayout.getContentView().findViewById(R.id.refresh_customer);
		RecyclerView recyclerView = mStateLayout.getContentView().findViewById(R.id.recycler_customer);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		recyclerView.setAdapter(mAdapter = new AdapterItem());
	}

	private void initEvent() {

		mStateLayout.setEmptyStateRetryListener(new StateEmptyInterface.OnRetryListener() {
			@Override
			public void onRetry() {
				mStateLayout.switchState(CONTENT);
				mSmartRefreshLayout.autoRefresh();
			}
		});

		mStateLayout.setErrorStateRetryListener(new StateErrorInterface.OnRetryListener() {
			@Override
			public void onRetry() {
				mStateLayout.switchState(CONTENT);
				mSmartRefreshLayout.autoRefresh();
			}
		});

		mSmartRefreshLayout.setEnableLoadMore(false);
		mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
			@Override
			public void onRefresh(@NonNull RefreshLayout refreshLayout) {
				mStateLayout.switchState(CONTENT);
				mMainHandler.postDelayed(new Runnable() {
					@Override
					public void run() {
						mSmartRefreshLayout.finishRefresh();
						Random random = new Random();
						int randNum = Math.abs(random.nextInt()) % 3;
						if (randNum == 0) {
							//模拟获取数据失败
							mStateLayout.switchState(EMPTY);
						} else if (randNum == 1) {
							//模拟数据为空
							mStateLayout.switchState(ERROR);
						} else {
							//模拟获取数据成功
							mAdapter.setData(obtainDataList());
						}

					}
				}, 500);
			}
		});
	}

	private void initData() {
		mAdapter.setData(obtainDataList());
	}

	private List<String> obtainDataList() {
		List<String> dataList = new ArrayList<>();
		for (int index = 0; index < 20; index++) {
			dataList.add("item " + index);
		}
		return dataList;
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
				mStateLayout.switchState(CONTENT);
				break;
			case R.id.menu_state_ing:
				mStateLayout.switchState(StateLayout.State.ING);
				break;
			case R.id.menu_state_empty:
				mStateLayout.switchState(EMPTY);
				break;
			case R.id.menu_state_error:
				mStateLayout.switchState(ERROR);
				break;
			default:
				break;
		}
		return super.onOptionsItemSelected(item);
	}
}
