package com.zhuoren.aveditor.common;

import android.view.View;


public class Click {


    public interface OnClickListener {
        void onClick();
    }

    public interface OnViewClickListener<T> {
        void onClick(View view, T t);
    }

    public interface OnObjectClickListener<T> {
        void onObjectClick(T t);
    }

    public interface OnPositionClickListener {
        void onPositionClick(int position);
    }

    public interface OnItemClickListener<T> {
        void onItemClick(T t, int position);
    }

    public interface OnItemViewClickListener<T> {
        void onItemClick(View view, T t, int position);
    }


}
