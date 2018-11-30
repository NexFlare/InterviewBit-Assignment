package com.nexflare.interviewbit.tutorial;

import com.nexflare.interviewbit.base.MvpView;
import com.nexflare.interviewbit.practice.model.PracticeObject;
import com.nexflare.interviewbit.tutorial.model.TutorialObject;

import java.util.ArrayList;

/**
 * Created by nexflare on 30/11/18.
 */

public interface TutorialMvpView extends MvpView {

   void crawlPracticeList();

   void showListOfItems(ArrayList<TutorialObject> list);
}