package com.test.khrushch.testapplication.ui.views;

import android.support.v4.app.Fragment;
import android.view.View;

public interface FragmentViewBase {
    Fragment getFragment();
    void makeToast(int stringId);
    void log(String message);
    View getMainView();

}
