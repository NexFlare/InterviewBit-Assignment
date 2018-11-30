package com.nexflare.interviewbit.topic;

import android.content.Context;
import android.widget.Toast;

import com.nexflare.interviewbit.base.BasePresenter;
import com.nexflare.interviewbit.practice.model.PracticeObject;
import com.nexflare.interviewbit.topic.model.TopicObject;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by nexflare on 30/11/18.
 */

public class TopicPresenter<V extends TopicMvpView> extends BasePresenter<V>
        implements TopicMvpPresenter<V> {



    @Inject
    public TopicPresenter() {
        super();

    }


    @Override
    public void onViewInitialized() {

    }

    @Override
    public void onCardExhausted() {
        getMvpView().showLoading();
        if(getMvpView().isNetworkConnected()){
            getMvpView().crawlPracticeList();
        }
        else {
            getMvpView().showMessage("Network Error");
            getMvpView().hideLoading();
        }

    }

    @Override
    public void onDataCrawled(ArrayList<TopicObject> list) {

        getMvpView().hideLoading();
        getMvpView().showListOfItems(list);

    }
}
