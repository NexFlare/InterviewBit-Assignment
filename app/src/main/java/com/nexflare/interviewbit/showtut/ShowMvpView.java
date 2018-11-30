package com.nexflare.interviewbit.showtut;

import com.nexflare.interviewbit.base.MvpView;
import com.nexflare.interviewbit.practice.model.PracticeObject;

import java.util.ArrayList;

/**
 * Created by nexflare on 30/11/18.
 */

public interface ShowMvpView extends MvpView {

   void crawlPracticeList();

   void showInformationOnPage(String html);
}