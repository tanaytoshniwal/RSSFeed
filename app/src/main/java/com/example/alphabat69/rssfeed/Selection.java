package com.example.alphabat69.rssfeed;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Selection extends AppCompatActivity {
    private RecyclerView view;
    private List<Card> list;
    private CardAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);
        view = findViewById(R.id.view);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
        view.setLayoutManager(layoutManager);
        view.addItemDecoration(new DividerItemDecoration(Selection.this,LinearLayoutManager.VERTICAL));
        view.setItemAnimator(new DefaultItemAnimator());
        Card c[] = new Card[9];
        c[0] = new Card("Top Stories", "http://abcnews.go.com/abcnews/topstories", "top.txt");
        c[1] = new Card("World Headlines", "http://abcnews.go.com/abcnews/internationalheadlines", "world.txt");
        c[2] = new Card("US Headlines", "http://abcnews.go.com/abcnews/usheadlines", "us.txt");
        c[3] = new Card("Politics Headlines", "http://abcnews.go.com/abcnews/politicsheadlines", "politics.txt");
        c[4] = new Card("Money Headlines", "http://abcnews.go.com/abcnews/moneyheadlines", "money.txt");
        c[5] = new Card("Health Headlines", "http://abcnews.go.com/abcnews/healthheadlines", "health.txt");
        c[6] = new Card("Travel Headlines", "http://abcnews.go.com/abcnews/travelheadlines", "travel.txt");
        c[7] = new Card("Entertainment Headlines", "http://abcnews.go.com/abcnews/entertainmentheadlines", "entertainment.txt");
        c[8] = new Card("Sports Headlines", "http://abcnews.go.com/abcnews/sportsheadlines", "sports.txt");
        list = new ArrayList<Card>();
        for(int i=0;i<9;i++)
            list.add(c[i]);
        adapter = new CardAdapter(Selection.this, list);
        view.setAdapter(adapter);
    }
}
