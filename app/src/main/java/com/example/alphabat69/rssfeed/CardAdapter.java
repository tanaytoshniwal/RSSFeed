package com.example.alphabat69.rssfeed;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by AlphaBAT69 on 16-02-2018.
 */

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.MyViewHolder> {
    private Context context;
    private List<Card> cardsList;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView thumbnail;

        public MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.title);
            thumbnail = view.findViewById(R.id.thumbnail);
        }
    }

    public CardAdapter(Context context, List<Card> cardsList){
        this.context = context;
        this.cardsList = cardsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardlayout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Card card = cardsList.get(position);
        holder.title.setText(card.getTitle());
        holder.thumbnail.setImageResource(R.drawable.card);
        holder.thumbnail.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(context, Activity1.class);
                i.putExtra("link", card.getLink());
                i.putExtra("file", card.getFile());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cardsList.size();
    }
}
