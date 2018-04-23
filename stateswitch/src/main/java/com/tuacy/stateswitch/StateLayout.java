package com.tuacy.stateswitch;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import static com.tuacy.stateswitch.StateLayout.State.CONTENT;
import static com.tuacy.stateswitch.StateLayout.State.ERROR;
import static com.tuacy.stateswitch.StateLayout.State.ING;

public class StateLayout extends FrameLayout {

	private static IngCreator   sIngCreator   = new IngCreator() {
		@NonNull
		@Override
		public StateIngInterface createStateIng(@NonNull Context context, @NonNull StateLayout layout) {
			return new DefaultIngView(context);
		}
	};
	private static EmptyCreator sEmptyCreator = new EmptyCreator() {
		@NonNull
		@Override
		public StateEmptyInterface createStateEmpty(@NonNull Context context, @NonNull StateLayout layout) {
			return new DefaultEmptyView(context);
		}
	};
	private static ErrorCreator sErrorCreator = new ErrorCreator() {
		@NonNull
		@Override
		public StateErrorInterface createStateError(@NonNull Context context, @NonNull StateLayout layout) {
			return new DefaultErrorView(context);
		}
	};

	/**
	 * 设置默认的加载中的UI
	 *
	 * @param creator 　IngCreator
	 */
	public static void setDefaultIngCreator(IngCreator creator) {
		sIngCreator = creator;
	}

	/**
	 * 设置默认数据为空的时候的UI
	 *
	 * @param creator 　EmptyCreator
	 */
	public static void setDefaultEmptyCreator(EmptyCreator creator) {
		sEmptyCreator = creator;
	}

	/**
	 * 设置默认数据获取失败时候的UI
	 *
	 * @param creator 　ErrorCreator
	 */
	public static void setDefaultErrorCreator(ErrorCreator creator) {
		sErrorCreator = creator;
	}

	public enum State {
		/**
		 * 数据内容显示
		 */
		CONTENT,
		/**
		 * 数据加载中
		 */
		ING,
		/**
		 * 数据为空
		 */
		EMPTY,
		/**
		 * 数据加载失败
		 */
		ERROR
	}

	private Context             mContext;
	private State               mState;
	private View                mContentView;
	private StateIngInterface   mStateIngView;
	private StateEmptyInterface mStateEmptyView;
	private StateErrorInterface mStateErrorView;

	public StateLayout(@NonNull Context context) {
		this(context, null);
	}

	public StateLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public StateLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		mContext = context;
		initAttribute(attrs, defStyleAttr);
		initView();
		switchState(CONTENT);
	}

	private void initAttribute(AttributeSet attrs, int defStyleAttr) {
		TypedArray typeArray = getContext().obtainStyledAttributes(attrs, R.styleable.StateLayout, defStyleAttr, 0);
		int contentLayoutId = typeArray.getResourceId(R.styleable.StateLayout_state_content_layout, -1);
		typeArray.recycle();
		if (contentLayoutId == -1) {
			throw new NullPointerException("StateSwitchLayout use should set content layout id!!!!!!");
		}
		mContentView = LayoutInflater.from(getContext()).inflate(contentLayoutId, this, false);
	}

	private void initView() {
		formatParameter();
		addView(mStateIngView.getView());
		addView(mStateEmptyView.getView());
		addView(mStateErrorView.getView());
		addView(mContentView);
	}

	public View getContentView() {
		return mContentView;
	}

	public void switchState(State state, String msg) {
		switchState(state);
		switch (state) {
			case ING:
				mStateIngView.setPromptMessage(msg);
				break;
			case EMPTY:
				mStateEmptyView.setPromptMessage(msg);
				break;
			case ERROR:
				mStateErrorView.setPromptMessage(msg);
				break;
		}
	}

	public void switchState(State state) {
		if (mState != null && mState == state) {
			return;
		}
		switch (state) {
			case CONTENT:
				mContentView.setVisibility(VISIBLE);
				mStateIngView.getView().setVisibility(GONE);
				mStateIngView.visible(false);
				mStateEmptyView.getView().setVisibility(GONE);
				mStateEmptyView.visible(false);
				mStateErrorView.getView().setVisibility(GONE);
				mStateErrorView.visible(false);
				break;
			case ING:
				mContentView.setVisibility(GONE);
				mStateIngView.getView().setVisibility(VISIBLE);
				mStateIngView.visible(true);
				mStateEmptyView.getView().setVisibility(GONE);
				mStateEmptyView.visible(false);
				mStateErrorView.getView().setVisibility(GONE);
				mStateErrorView.visible(false);
				break;
			case EMPTY:
				mContentView.setVisibility(GONE);
				mStateIngView.getView().setVisibility(GONE);
				mStateIngView.visible(false);
				mStateEmptyView.getView().setVisibility(VISIBLE);
				mStateEmptyView.visible(true);
				mStateErrorView.getView().setVisibility(GONE);
				mStateErrorView.visible(false);
				break;
			case ERROR:
				mContentView.setVisibility(GONE);
				mStateIngView.getView().setVisibility(GONE);
				mStateIngView.visible(false);
				mStateEmptyView.getView().setVisibility(GONE);
				mStateEmptyView.visible(false);
				mStateErrorView.getView().setVisibility(VISIBLE);
				mStateErrorView.visible(true);
				break;
		}
		mState = state;
	}

	public void setIngStateView(@NonNull StateIngInterface stateWrap) {
		removeView(mStateIngView.getView());
		mStateIngView = stateWrap;
		addView(mStateIngView.getView());
		if (mState != ING) {
			mStateIngView.getView().setVisibility(GONE);
			mStateIngView.visible(false);
		} else {
			mStateIngView.visible(true);
		}
	}

	public void setEmptyStateView(@NonNull StateEmptyInterface stateWrap) {
		setEmptyStateView(stateWrap, null);
	}

	public void setEmptyStateView(@NonNull StateEmptyInterface stateWrap, StateEmptyInterface.OnRetryListener retryListener) {
		removeView(mStateEmptyView.getView());
		mStateEmptyView = stateWrap;
		mStateEmptyView.setOnRetryListener(retryListener);
		addView(mStateEmptyView.getView());
		if (mState != ERROR) {
			mStateEmptyView.getView().setVisibility(GONE);
			mStateEmptyView.visible(false);
		} else {
			mStateEmptyView.visible(true);
		}
	}

	public void setEmptyStateRetryListener(@NonNull StateEmptyInterface.OnRetryListener retryListener) {
		mStateEmptyView.setOnRetryListener(retryListener);
	}

	public void setErrorStateView(@NonNull StateErrorInterface stateWrap) {
		setErrorStateView(stateWrap, null);
	}

	public void setErrorStateView(@NonNull StateErrorInterface stateWrap, StateErrorInterface.OnRetryListener retryListener) {
		removeView(mStateErrorView.getView());
		mStateErrorView = stateWrap;
		mStateErrorView.setOnRetryListener(retryListener);
		addView(mStateErrorView.getView());
		if (mState != ERROR) {
			mStateErrorView.getView().setVisibility(GONE);
			mStateErrorView.visible(false);
		} else {
			mStateErrorView.visible(true);
		}
	}

	public void setErrorStateRetryListener(StateErrorInterface.OnRetryListener retryListener) {
		mStateErrorView.setOnRetryListener(retryListener);
	}

	private void formatParameter() {

		if (mStateIngView == null) {
			mStateIngView = sIngCreator.createStateIng(mContext, this);
		}

		if (mStateEmptyView == null) {
			mStateEmptyView = sEmptyCreator.createStateEmpty(mContext, this);
		}

		if (mStateErrorView == null) {
			mStateErrorView = sErrorCreator.createStateError(mContext, this);
		}

	}
}
