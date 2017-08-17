package com.bcjm.eshop.views.recycleview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * 通用RecycleView适配器
 * Created by 刘信 on 2017/8/17.
 */

public abstract class CommonAdapter<T> extends RecyclerView.Adapter<ViewHolder> {

    protected List<T> mDatas;

    protected OnItemClickListener<T> mOnItemClickListener;

    public CommonAdapter() {
        mDatas = new ArrayList<>();
    }


    public void setOnItemClickListener(OnItemClickListener<T> listener) {
        this.mOnItemClickListener = listener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        final ViewHolder viewHolder = ViewHolder.get(parent.getContext(), parent, getLayoutId(viewType));

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int position = viewHolder.getIAdapterPosition();
                final T good = mDatas.get(position);
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(position, good, v);
                }
            }
        });
        return viewHolder;
    }


    /**
     * 设置数据
     *
     * @param isRefresh
     * @param data
     */
    public void setList(boolean isRefresh, List<T> data) {
        if (isRefresh) {
            mDatas.clear();
        }
        append(data);
    }

    public void setList(List<T> data){

        append(data);
    }

    /**
     * 添加数据
     *
     * @param commentBeans
     */
    private void append(List<T> commentBeans) {
        int positionStart = mDatas.size();
        int itemCount = commentBeans.size();
        mDatas.addAll(commentBeans);
        if (positionStart > 0 && itemCount > 0) {
            notifyItemRangeInserted(positionStart, itemCount);
        } else {
            notifyDataSetChanged();
        }
    }

    /**
     * 清除数据
     */
    public void clear() {
        mDatas.clear();
        notifyDataSetChanged();
    }


    /**
     * 获取单个ViewType的id
     *
     * @return
     */
    public abstract int getLayoutId();


    @Override
    public int getItemViewType(int position) {

        return getItemViewType(position, mDatas.get(position));
    }


    /**
     * 如果有多个ViewTypes时需重新这个方法
     *
     * @param position
     * @param t
     * @return
     */
    public int getItemViewType(int position, T t) {
        return 0;
    }

    /**
     * 如果有多个ViewTypes时需要重新这个方法
     *
     * @param viewType
     * @return
     */
    public int getLayoutId(int viewType) {

        return getLayoutId();
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        convertView(holder, position);
    }

    public abstract void convertView(ViewHolder holder, int position);

    @Override
    public int getItemCount() {
        return mDatas.size();
    }
}
