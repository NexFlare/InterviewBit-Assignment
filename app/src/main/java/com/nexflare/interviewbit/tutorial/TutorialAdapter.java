package com.nexflare.interviewbit.tutorial;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nexflare.interviewbit.R;
import com.nexflare.interviewbit.showtut.ShowActivity;
import com.nexflare.interviewbit.tutorial.model.TutorialObject;

import java.util.ArrayList;

/**
 * Created by nexflare on 30/11/18.
 */

public class TutorialAdapter extends RecyclerView.Adapter<TutorialAdapter.PracticeViewHolder> {
    private Context context;

    private ArrayList<TutorialObject> list;

    public TutorialAdapter(Context context, ArrayList<TutorialObject> list) {
        this.context = context;
        this.list = list;
    }

    public class PracticeViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        CardView linearLayout;
        TextView open;

        public PracticeViewHolder(View itemView) {
            super(itemView);
            this.title =  itemView.findViewById(R.id.headingTut);
            this.open = (TextView) itemView.findViewById(R.id.viewTut);
            this.linearLayout =  itemView.findViewById(R.id.cardView);
        }
    }


    @NonNull
    public PracticeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PracticeViewHolder(LayoutInflater.from(this.context).inflate(R.layout.layout_tut_ques, parent, false));
    }

    public void onBindViewHolder(@NonNull PracticeViewHolder holder, final int position) {
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                TutorialAdapter.this.context.startActivity(new Intent(context, ShowActivity.class).putExtra("url",list.get(position).getNextUrl()));
            }
        });

        holder.title.setText(list.get(position).getTitle());

        //Picasso.with(context).load(R.drawable.logo).into(holder.image);
    }

    public int getItemCount() {
        return list.size();
    }
}
