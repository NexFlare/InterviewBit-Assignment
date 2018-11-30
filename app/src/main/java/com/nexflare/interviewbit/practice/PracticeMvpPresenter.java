package com.nexflare.interviewbit.practice;

import com.nexflare.interviewbit.base.MvpPresenter;
import com.nexflare.interviewbit.practice.model.PracticeObject;

import java.util.ArrayList;

/**
 * Created by nexflare on 30/11/18.
 */

public interface PracticeMvpPresenter<V extends PracticeMvpView> extends MvpPresenter<V> {


    void onViewInitialized();

    void onCardExhausted();

    void onDataCrawled(ArrayList<PracticeObject> list);

}
