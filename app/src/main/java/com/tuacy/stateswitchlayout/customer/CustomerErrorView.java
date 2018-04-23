package com.tuacy.stateswitchlayout.customer;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.opensource.svgaplayer.SVGAImageView;
import com.tuacy.stateswitch.StateErrorInterface;
import com.tuacy.stateswitchlayout.R;

public class CustomerErrorView extends LinearLayout implements StateErrorInterface {

	private SVGAImageView   mSVGError;
	private TextView        mTextRefresh;
	private OnRetryListener mRetryListener;

	public CustomerErrorView(@NonNull Context context) {
		this(context, null);
	}

	public CustomerErrorView(@NonNull Context context, @Nullable AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public CustomerErrorView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView(context);
	}

	private void initView(Context context) {
		LayoutInflater.from(context).inflate(R.layout.layout_customer_error, this, true);
		mSVGError = findViewById(R.id.svg_status_exception);
		mTextRefresh = findViewById(R.id.text_status_layout_exception_refresh);
		mTextRefresh.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (mRetryListener != null) {
					mRetryListener.onRetry();
				}
			}
		});
	}

	@NonNull
	@Override
	public View getView() {
		return this;
	}

	@Override
	public void setPromptMessage(String msg) {
		mTextRefresh.setText(msg);
	}

	@Override
	public void setOnRetryListener(OnRetryListener listener) {
		mRetryListener = listener;
	}

	@Override
	public void visible(boolean visible) {
		if (visible) {
			mSVGError.startAnimation();
		} else {
			mSVGError.pauseAnimation();
		}
	}
}
