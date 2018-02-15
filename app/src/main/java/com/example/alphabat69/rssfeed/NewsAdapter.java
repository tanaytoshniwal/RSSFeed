package com.example.alphabat69.rssfeed;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

/**
 * Created by AlphaBAT69 on 15-02-2018.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder>{
    private List<News> newsList;
    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView title,link,pub;
        public EditText description;
        public  ViewHolder(View view){
            super(view);
            title=view.findViewById(R.id.title);
            description=view.findViewById(R.id.description);
            link=view.findViewById(R.id.link);
            pub=view.findViewById(R.id.pub);
        }
    }
    public NewsAdapter(List<News> newsList){
        this.newsList=newsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.newslayout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final News news = newsList.get(position);
        holder.title.setText(news.getTitle());
        final ViewHolder desc = holder;
        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(desc.description.getVisibility()==View.GONE)
                    desc.description.setVisibility(View.VISIBLE);
                else
                    desc.description.setVisibility(View.GONE);
            }
        });
        holder.description.setText(news.getDescription());
        holder.link.setText(news.getLink());
        holder.link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(news.getLink()));
                v.getContext().startActivity(i);
            }
        });
        holder.pub.setText(news.getPub());
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }
}
