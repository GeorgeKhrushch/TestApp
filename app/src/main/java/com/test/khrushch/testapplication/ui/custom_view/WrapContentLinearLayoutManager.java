package com.test.khrushch.testapplication.ui.custom_view;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

public class WrapContentLinearLayoutManager extends LinearLayoutManager {

    public WrapContentLinearLayoutManager(Context context) {
        super(context, LinearLayoutManager.VERTICAL, false);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        try {
            super.onLayoutChildren(recycler, state);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("probe", "meet a IOOBE in RecyclerView");
        }
    }
}