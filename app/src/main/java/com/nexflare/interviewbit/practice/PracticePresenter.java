package com.nexflare.interviewbit.practice;

import android.content.Context;
import android.widget.Toast;

import com.nexflare.interviewbit.base.BasePresenter;
import com.nexflare.interviewbit.practice.model.PracticeObject;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by nexflare on 30/11/18.
 */

public class PracticePresenter<V extends PracticeMvpView> extends BasePresenter<V>
        implements PracticeMvpPresenter<V>{

    Context context;

    @Inject
    public PracticePresenter(Context context) {
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
    public void onDataCrawled(ArrayList<PracticeObject> list) {


        getMvpView().showListOfItems(list);
        getMvpView().hideLoading();

    }
}
