package com.nexflare.interviewbit.tutorial;

import android.content.Context;
import android.widget.Toast;

import com.nexflare.interviewbit.base.BasePresenter;
import com.nexflare.interviewbit.practice.model.PracticeObject;
import com.nexflare.interviewbit.tutorial.model.TutorialObject;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by nexflare on 30/11/18.
 */

public class TutorialPresenter<V extends TutorialMvpView> extends BasePresenter<V>
        implements TutorialMvpPresenter<V> {

    Context context;

    @Inject
    public TutorialPresenter(Context context) {
        super();
        this.context=context;
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
            Toast.makeText(context, "No Network", Toast.LENGTH_SHORT).show();
            getMvpView().hideLoading();
        }


    }

    @Override
    public void onDataCrawled(ArrayList<TutorialObject> list) {

        getMvpView().hideLoading();
        getMvpView().showListOfItems(list);

    }
}
