package com.example.coco.qqzoneadertising;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by coco on 2017/11/16.
 */

class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private int ITEM = 1;
    private int ADVERTISING = 2;
    private ViewGroup parent;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.parent = parent;
        View view = null;
        if (viewType == ITEM) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, null, false);
        } else if (viewType == ADVERTISING) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.advertising, null, false);

        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (getItemViewType(position) == ADVERTISING) {
            ((QQZoneAdvertize) holder.view).setmRv(parent);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position == 3 ? ADVERTISING : ITEM;
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView.findViewById(R.id.mQQ);
        }
    }
}
