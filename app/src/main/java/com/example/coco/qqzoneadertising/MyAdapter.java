package com.example.coco.qqzoneadertising;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by coco on 2017/11/16.
 */

class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private int ITEM = 1;//普通的item
    private int ADVERTISING = 2;//广告位的item
    private ViewGroup parent;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.parent = parent;
        View view = null;
        //进行类型判断&&引入对应布局
        if (viewType == ITEM) {//普通item显示的布局
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        } else if (viewType == ADVERTISING) {//广告位item显示的布局（自定义的广告位布局）
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.advertize, parent, false);

        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //对广告位的item  添加滑动监听&&两张广告图进行切换
        if (getItemViewType(position) == ADVERTISING) {
            ((QQZoneAdvertize) holder.view).setmRv(parent);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 3 || position == 6)//如果position==3/6，返回广告位图片，否则返回普通的item
            return ADVERTISING;
        else
            return ITEM;
    }

    @Override
    public int getItemCount() {
        //item数量为10
        return 10;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {//普通item返回的布局
        View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView.findViewById(R.id.mQQ);
        }
    }
}
