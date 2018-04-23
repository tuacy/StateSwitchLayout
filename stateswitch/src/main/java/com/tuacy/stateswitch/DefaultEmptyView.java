package com.tuacy.stateswitch;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DefaultEmptyView extends LinearLayout implements StateEmptyInterface {

	private TextView        mTextHint;

	public DefaultEmptyView(Context context) {
		this(context, null);
	}

	public DefaultEmptyView(Context context, @Nullable AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public DefaultEmptyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView(context);
	}

	private void initView(Context context) {
		LayoutInflater.from(context).inflate(R.layout.view_default_empty, this, true);
		mTextHint = findViewById(R.id.text_empty_hint);
	}

	@NonNull
	@Override
	public View getView() {
		return this;
	}

	@Override
	public void setPromptMessage(String msg) {
		mTextHint.setText(msg);
	}

	@Override
	public void setOnRetryListener(OnRetryListener listener) {
	}

	@Override
	public void visible(boolean visible) {

	}
}
