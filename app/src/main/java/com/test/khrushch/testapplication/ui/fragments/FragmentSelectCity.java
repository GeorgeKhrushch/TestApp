package com.test.khrushch.testapplication.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.test.khrushch.testapplication.R;
import com.test.khrushch.testapplication.structure.CityInfo;
import com.test.khrushch.testapplication.ui.custom_view.RecycleDecorations;
import com.test.khrushch.testapplication.ui.custom_view.WrapContentLinearLayoutManager;
import com.test.khrushch.testapplication.ui.adapter.CitiesAdapter;
import com.test.khrushch.testapplication.ui.presenters.CloseFragment;
import com.test.khrushch.testapplication.ui.presenters.FragmentPresenterSelectCityImpl;
import com.test.khrushch.testapplication.ui.views.FragmentViewSelectCity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentSelectCity extends FragmentModel implements FragmentViewSelectCity {

    @BindView(R.id.rvFragmentSelectCity) RecyclerView recyclerView;
    private View mainView;

    private List<CityInfo> cityInfoList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.fragment_select_city, container, false);
        ButterKnife.bind(this, mainView);
        setUpRecyclerView();
        return mainView;
    }

    private void setUpRecyclerView() {
        LinearLayoutManager linearLayoutManager = new WrapContentLinearLayoutManager(getContext());
        recyclerView.addItemDecoration(new RecycleDecorations.DividerItemDecoration(getContext()));
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    protected String getTitle() {
        return getString(R.string.title_select_city);
    }


    @Override
    public View getMainView() {
        return mainView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_empty, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void setInfoToAdapter(List<CityInfo> cityInfoList, CloseFragment closeFragment) {
        this.cityInfoList = cityInfoList;
        recyclerView.setAdapter(new CitiesAdapter(cityInfoList, closeFragment));
    }

    /*Throws  java.lang.IllegalStateException: Can not perform this action after onSaveInstanceState
    * when trying to .commit() after rotate - API 11+ bug:
    * https://stackoverflow.com/a/10261449 or similar doesn't work properly*/
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(getPresenter().getCitiesBundle(outState));
    }

    public FragmentPresenterSelectCityImpl getPresenter() {
        return (FragmentPresenterSelectCityImpl) presenter;
    }
}
