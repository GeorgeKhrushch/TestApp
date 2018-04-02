package com.test.khrushch.testapplication.ui.presenters;

import android.support.v4.app.Fragment;

public interface ActivityPresenter {

    void openMainFragment();
    void replaceFragment(Fragment fragment, String newTag);

    void addFragment(Fragment fragment, Fragment current, String newTag);

    void closeFragment(Fragment fragment, String previousFragmentTag);

    void scheduleUpdateService() throws Exception;

    void cancelUpdateService() throws Exception;

    void clearNotifications();
}
