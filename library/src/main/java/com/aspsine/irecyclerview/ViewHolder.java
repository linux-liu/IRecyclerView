package com.bcjm.eshop.views.recycleview;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by Finley on 2017/8/17.
 */

public class ViewHolder extends IViewHolder {

    private SparseArray<View> mViews;


    public ViewHolder(View itemView,ViewGroup parent) {
        super(itemView);
        mViews=new SparseArray<>();

    }


    public static ViewHolder get(Context context,ViewGroup parent,@LayoutRes int layoutId){
        View itemView= LayoutInflater.from(context).inflate(layoutId,parent,false);
        ViewHolder viewHolder=new ViewHolder(itemView,parent);
        return viewHolder;
    }


    /**
     * 通过viewId找到View
     * @param viewId
     * @param <T>
     * @return
     */
    public <T extends View> T getView(int viewId){
       View view= mViews.get(viewId);
        if(view==null){
           view= itemView.findViewById(viewId);
            mViews.put(viewId,view);
        }
        return (T) view;
    }


    /**
     * 设置textView 文本
     * @param viewId
     * @param text
     * @return
     */
    public ViewHolder setText(int viewId,String text){
        TextView tv=getView(viewId);
        tv.setText(text);

        return this;
    }

    /**
     * 设置文本颜色
     * @param viewId
     * @param resid
     * @return
     */

    public ViewHolder setTextColor(int viewId,int resid){
        TextView textView=getView(viewId);
        textView.setTextColor(itemView.getResources().getColor(resid));
        return this;
    }



    /**
     * 设置ImageView图片
     * @param viewId
     * @param url
     * @return
     */
    public ViewHolder setImage(int viewId,String url){
        ImageView imageView=getView(viewId);
        Glide.with(itemView.getContext()).load(url).asBitmap().into(imageView);

       return  this;
    }




}
