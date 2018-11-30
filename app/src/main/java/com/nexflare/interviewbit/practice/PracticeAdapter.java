package com.nexflare.interviewbit.practice;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nexflare.interviewbit.R;
import com.nexflare.interviewbit.practice.model.PracticeObject;
import com.nexflare.interviewbit.topic.TopicActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by nexflare on 30/11/18.
 */

public class PracticeAdapter extends RecyclerView.Adapter<PracticeAdapter.PracticeViewHolder> {
    private Context context;

    private ArrayList<PracticeObject> list;

    public PracticeAdapter(Context context, ArrayList<PracticeObject> list) {
        this.context = context;
        this.list = list;
    }

    public class PracticeViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        LinearLayout linearLayout;
        TextView title;

        public PracticeViewHolder(View itemView) {
            super(itemView);
            this.image = (ImageView) itemView.findViewById(R.id.image_title);
            this.title = (TextView) itemView.findViewById(R.id.title);
            this.linearLayout = (LinearLayout) itemView.findViewById(R.id.linearLayout);
        }
    }


    @NonNull
    public PracticeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PracticeViewHolder(LayoutInflater.from(this.context).inflate(R.layout.layout_tracks, parent, false));
    }

    public void onBindViewHolder(@NonNull PracticeViewHolder holder, final int position) {
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                PracticeAdapter.this.context.startActivity(new Intent(context, TopicActivity.class).putExtra("url",list.get(position).getNextUrl()));
            }
        });

        holder.title.setText(list.get(position).getTitle());

        Picasso.with(context).load(list.get(position).getIconUrl()).into(holder.image);
    }

    public int getItemCount() {
        return list.size();
    }
}
