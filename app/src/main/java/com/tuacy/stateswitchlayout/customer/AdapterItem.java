package com.tuacy.stateswitchlayout.customer;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tuacy.stateswitchlayout.R;

import java.util.List;

public class AdapterItem extends RecyclerView.Adapter<AdapterItem.ItemHolder> {

	private List<String> mData;

	public void setData(List<String> data) {
		mData = data;
	}

	@NonNull
	@Override
	public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		return new ItemHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_customer, parent, false));
	}

	@Override
	public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
		holder.mTextInfo.setText(mData.get(position));
	}

	@Override
	public int getItemCount() {
		return mData == null ? 0 : mData.size();
	}

	static class ItemHolder extends RecyclerView.ViewHolder {

		TextView mTextInfo;

		public ItemHolder(View itemView) {
			super(itemView);
			mTextInfo = itemView.findViewById(R.id.text_info);
		}
	}

}
