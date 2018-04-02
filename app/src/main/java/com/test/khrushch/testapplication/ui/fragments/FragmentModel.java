package com.test.khrushch.testapplication.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.Toast;

import com.test.khrushch.testapplication.TestApplication;
import com.test.khrushch.testapplication.ui.presenters.FragmentPresenterBase;
import com.test.khrushch.testapplication.ui.views.FragmentViewBase;

import javax.inject.Inject;

public abstract class FragmentModel extends Fragment implements FragmentViewBase {
    @Inject
    FragmentPresenterBase presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        initDi();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter.loadData(savedInstanceState);
    }

    private void initDi() {
        TestApplication.get(getContext()).injectFragmentModelComponent(this);
    }

    @Override
    public void log(String message){
        Log.d(getClass().getSimpleName(), message);
    }

    @Override
    public void onStart() {
        super.onStart();
        setTitle();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden)
            setTitle();
    }

    private void setTitle(){
        getActivity().setTitle(getTitle());
    }

    protected  String getTitle(){
        return getTag();
    }

    @Override
    public void makeToast(int stringId){
        Toast.makeText(getContext(), stringId, Toast.LENGTH_LONG).show();
    }

    @Override
    public Fragment getFragment() {
        return this;
    }
}
