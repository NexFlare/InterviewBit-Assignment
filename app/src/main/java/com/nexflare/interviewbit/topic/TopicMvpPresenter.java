package com.nexflare.interviewbit.topic;

import com.nexflare.interviewbit.base.MvpPresenter;
import com.nexflare.interviewbit.practice.model.PracticeObject;
import com.nexflare.interviewbit.topic.model.TopicObject;

import java.util.ArrayList;

/**
 * Created by nexflare on 30/11/18.
 */

public interface TopicMvpPresenter<V extends TopicMvpView> extends MvpPresenter<V> {


    void onViewInitialized();

    void onCardExhausted();

    void onDataCrawled(ArrayList<TopicObject> list);

}
