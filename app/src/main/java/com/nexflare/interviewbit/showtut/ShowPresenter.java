package com.nexflare.interviewbit.showtut;

import android.content.Context;
import android.widget.Toast;

import com.nexflare.interviewbit.base.BasePresenter;
import com.nexflare.interviewbit.practice.model.PracticeObject;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by nexflare on 30/11/18.
 */

public class ShowPresenter<V extends ShowMvpView> extends BasePresenter<V>
        implements ShowMvpPresenter<V> {


    @Inject
    public ShowPresenter() {
        super();

    }


    @Override
    public void onViewInitialized() {

    }

    @Override
    public void onCardExhausted() {

        getMvpView().showLoading();
        if(getMvpView().isNetworkConnected())
            getMvpView().crawlPracticeList();
        else{
            getMvpView().showMessage("Network Error");
            getMvpView().hideLoading();
        }

    }

    @Override
    public void onDataCrawled(String list) {

        getMvpView().hideLoading();
        getMvpView().showInformationOnPage(list);

    }
}
