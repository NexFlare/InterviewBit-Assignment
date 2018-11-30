package com.nexflare.interviewbit.practice;

import com.nexflare.interviewbit.base.MvpView;
import com.nexflare.interviewbit.practice.model.PracticeObject;

import java.util.ArrayList;

/**
 * Created by nexflare on 30/11/18.
 */

public interface PracticeMvpView extends MvpView {

   void crawlPracticeList();

   void showListOfItems(ArrayList<PracticeObject> list);
}