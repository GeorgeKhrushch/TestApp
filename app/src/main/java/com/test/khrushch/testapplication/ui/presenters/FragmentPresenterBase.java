package com.test.khrushch.testapplication.ui.presenters;

import android.os.Bundle;
import android.widget.ImageView;

import com.test.khrushch.testapplication.ui.views.FragmentViewBase;

import io.reactivex.functions.Consumer;

public interface FragmentPresenterBase {

    void loadData(Bundle savedState);
    Consumer<? super Throwable> getDefaultErrorConsumer();
    void loadImage(ImageView imageView, String url);
    FragmentViewBase getView();
}
