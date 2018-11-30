package com.nexflare.interviewbit.base;

/**
 * Created by nexflare on 30/11/18.
 */

public interface MvpPresenter<V extends MvpView> {

    void onAttach(V mvpView);

    void onDetach();

}