package com.test.khrushch.testapplication.ui.views;

import com.test.khrushch.testapplication.structure.CityInfo;
import com.test.khrushch.testapplication.ui.presenters.CloseFragment;

import java.util.List;

public interface FragmentViewSelectCity extends FragmentViewBase {

    void setInfoToAdapter(List<CityInfo> cityInfoList, CloseFragment closeFragment);
}
