package com.nexflare.interviewbit.tutorial;

import com.nexflare.interviewbit.base.MvpPresenter;
import com.nexflare.interviewbit.practice.model.PracticeObject;
import com.nexflare.interviewbit.tutorial.model.TutorialObject;

import java.util.ArrayList;

/**
 * Created by nexflare on 30/11/18.
 */

public interface TutorialMvpPresenter<V extends TutorialMvpView> extends MvpPresenter<V> {


    void onViewInitialized();

    void onCardExhausted();

    void onDataCrawled(ArrayList<TutorialObject> list);

}
