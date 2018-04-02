package com.test.khrushch.testapplication.di.components;



import com.test.khrushch.testapplication.MainActivity;
import com.test.khrushch.testapplication.di.modules.FragmentModule;
import com.test.khrushch.testapplication.di.modules.MainActivityModule;
import com.test.khrushch.testapplication.di.scope.MainActivityScope;

import dagger.Subcomponent;

@MainActivityScope
@Subcomponent(modules = MainActivityModule.class)

public interface MainActivityComponent {

    void inject(MainActivity mainActivity);
    FragmentComponent plusFragmentComponent(FragmentModule fragmentModule);
}