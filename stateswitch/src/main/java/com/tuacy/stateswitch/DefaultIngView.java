package com.tuacy.stateswitch;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DefaultIngView extends LinearLayout implements StateIngInterface {

	private ImageView mImageView;
	private Animation mLoadingAnim;
	private TextView  mTextHint;

	public DefaultIngView(Context context) {
		this(context, null);
	}

	public DefaultIngView(Context context, @Nullable AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public DefaultIngView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView(context);
	}

	private void initView(Context context) {
		LayoutInflater.from(context).inflate(R.layout.view_default_ing, this, true);
		mImageView = findViewById(R.id.image_loading);
		mLoadingAnim = AnimationUtils.loadAnimation(getContext(), R.anim.loading);
		mTextHint = findViewById(R.id.text_loading_hit);
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
	public void visible(boolean visible) {
		if (visible) {
			mImageView.startAnimation(mLoadingAnim);
		} else {
			mImageView.clearAnimation();
		}
	}

}
