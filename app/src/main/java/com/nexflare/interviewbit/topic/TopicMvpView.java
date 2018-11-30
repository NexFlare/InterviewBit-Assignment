package com.nexflare.interviewbit.topic;

import com.nexflare.interviewbit.base.MvpView;
import com.nexflare.interviewbit.practice.model.PracticeObject;
import com.nexflare.interviewbit.topic.model.TopicObject;

import java.util.ArrayList;

/**
 * Created by nexflare on 30/11/18.
 */

public interface TopicMvpView extends MvpView {

   void crawlPracticeList();

   void showListOfItems(ArrayList<TopicObject> list);
}