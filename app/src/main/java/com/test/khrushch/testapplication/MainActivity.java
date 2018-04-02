package com.test.khrushch.testapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.test.khrushch.testapplication.ui.presenters.ActivityPresenter;
import com.test.khrushch.testapplication.ui.views.MainActivityView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainActivityView {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @Inject
    ActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        diInject();
        presenter.clearNotifications();
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        if(savedInstanceState == null)
            presenter.openMainFragment();
    }

    private void diInject() {
        TestApplication.get(this).injectMainActivityComponent(this);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public FragmentManager getMainFragmentManager() {
        return getSupportFragmentManager();
    }

    @Override
    protected void onStop() {
        super.onStop();
        try {
            presenter.cancelUpdateService();
            presenter.scheduleUpdateService();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        try {
            presenter.cancelUpdateService();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
