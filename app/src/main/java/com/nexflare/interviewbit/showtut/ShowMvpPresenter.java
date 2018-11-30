package com.nexflare.interviewbit.showtut;

import com.nexflare.interviewbit.base.MvpPresenter;
import com.nexflare.interviewbit.practice.model.PracticeObject;

import java.util.ArrayList;

/**
 * Created by nexflare on 30/11/18.
 */

public interface ShowMvpPresenter<V extends ShowMvpView> extends MvpPresenter<V> {


    void onViewInitialized();

    void onCardExhausted();

    void onDataCrawled(String html);

}
