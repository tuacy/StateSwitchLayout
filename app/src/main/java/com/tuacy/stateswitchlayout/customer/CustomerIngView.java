package com.tuacy.stateswitchlayout.customer;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.opensource.svgaplayer.SVGAImageView;
import com.tuacy.stateswitch.StateIngInterface;
import com.tuacy.stateswitchlayout.R;
import com.tuacy.stateswitchlayout.widget.SpreadTextView;

public class CustomerIngView extends LinearLayout implements StateIngInterface {

	private SVGAImageView  mSVGIng;
	private SpreadTextView mTextLoading;

	public CustomerIngView(@NonNull Context context) {
		this(context, null);
	}

	public CustomerIngView(@NonNull Context context, @Nullable AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public CustomerIngView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView(context);
	}

	private void initView(Context context) {
		LayoutInflater.from(context).inflate(R.layout.layout_customer_ing, this, true);
		mSVGIng = findViewById(R.id.svg_status_loading);
		mTextLoading = findViewById(R.id.spread_loading_text);
	}

	@NonNull
	@Override
	public View getView() {
		return this;
	}

	@Override
	public void setPromptMessage(String msg) {
		mTextLoading.setText(msg);
	}

	@Override
	public void visible(boolean visible) {
		if (visible) {
			mSVGIng.startAnimation();
		} else {
			mSVGIng.pauseAnimation();
		}
	}
}
