package com.test.khrushch.testapplication.ui.presenters;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.test.khrushch.testapplication.R;
import com.test.khrushch.testapplication.new_client.client_impl.RequestManager;
import com.test.khrushch.testapplication.ui.views.FragmentViewBase;

import io.reactivex.functions.Consumer;

public abstract class FragmentPresenterBaseImpl implements FragmentPresenterBase {

    protected FragmentViewBase view;
    protected RequestManager requestManager;
    protected ActivityPresenter activityPresenter;

    public FragmentPresenterBaseImpl(FragmentViewBase view, ActivityPresenter activityPresenter,
                                     RequestManager requestManager){
        this.view = view;
        this.activityPresenter = activityPresenter;
        this.requestManager = requestManager;
    }

    @Override
    public Consumer<? super Throwable> getDefaultErrorConsumer() {
        return t -> {
            t.printStackTrace();
            view.makeToast(R.string.server_error);
        };
    }

    @Override
    public void loadImage(ImageView imageView, String url) {
        Picasso.get()
                .load(url)
                .into(imageView);
    }
}
