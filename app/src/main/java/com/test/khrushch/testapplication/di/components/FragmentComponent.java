package com.test.khrushch.testapplication.di.components;


import com.test.khrushch.testapplication.di.modules.FragmentModule;
import com.test.khrushch.testapplication.di.scope.FragmentModelScope;
import com.test.khrushch.testapplication.ui.fragments.FragmentModel;

import dagger.Subcomponent;

@FragmentModelScope
@Subcomponent(modules = FragmentModule.class)

public interface FragmentComponent {
    void inject(FragmentModel fragmentModel);
}