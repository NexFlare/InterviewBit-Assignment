package com.nexflare.interviewbit.utility;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewTreeObserver;

/**
 * Created by nexflare on 30/11/18.
 */

public class AnimationUtility {

    public static void  animateRecyclerView(final RecyclerView recyclerView){
        recyclerView.getViewTreeObserver().addOnPreDrawListener(
                new ViewTreeObserver.OnPreDrawListener() {

                    @Override
                    public boolean onPreDraw() {
                        recyclerView.getViewTreeObserver().removeOnPreDrawListener(this);

                        for (int i = 0; i < recyclerView.getChildCount(); i++) {
                            View v = recyclerView.getChildAt(i);
                            v.setAlpha(0.0f);
                            v.animate().alpha(1.0f)
                                    .setDuration(500)
                                    .setStartDelay(i * 70)
                                    .start();
                        }

                        return true;
                    }
                });
    }
}
