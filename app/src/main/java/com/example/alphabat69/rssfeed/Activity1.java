package com.example.alphabat69.rssfeed;

import android.app.ProgressDialog;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Activity1 extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<News> newsList = new ArrayList<>();
    private NewsAdapter newsAdapter;
    private SwipeRefreshLayout swiperefresh;
    public String TAG = "Error Occured:";
    private ArrayList<String> titles;
    private ArrayList<String> links;
    private ArrayList<String> descriptions;
    private ArrayList<String> pubs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_1);

        swiperefresh = findViewById(R.id.swiperefresh);
        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                newsList.clear();
                titles.clear();
                links.clear();
                descriptions.clear();
                pubs.clear();
                if(isConnected()) {
                    new ProcessInBackground().execute();
                    newsAdapter = new NewsAdapter(newsList);
                    recyclerView.setAdapter(newsAdapter);
                }
                else {
                    Toast.makeText(Activity1.this, "Not Connected", Toast.LENGTH_SHORT).show();
                    swiperefresh.setRefreshing(false);
                }
            }
        });

        titles = new ArrayList<>();
        links = new ArrayList<>();
        descriptions = new ArrayList<>();
        pubs = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        Toast.makeText(this, "Tap the Title to view Description", Toast.LENGTH_LONG).show();
        try{
            FileInputStream fis = openFileInput("object.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Data temp = (Data) ois.readObject();
            titles=temp.get1();
            links=temp.get2();
            descriptions=temp.get3();
            pubs=temp.get4();
            for(int i=0;i<titles.size();i++){
                News news = new News(titles.get(i), descriptions.get(i), links.get(i), pubs.get(i));
                newsList.add(news);
            }
            newsAdapter = new NewsAdapter(newsList);
            recyclerView.setAdapter(newsAdapter);
        }
        catch (Exception e){
            Log.e(TAG, e.getMessage());
        }
        if(isConnected()) {
            new ProcessInBackground().execute();
        }
        newsAdapter = new NewsAdapter(newsList);
        recyclerView.setAdapter(newsAdapter);
    }

    public boolean isConnected(){
        ConnectivityManager connec = (ConnectivityManager)getSystemService(getBaseContext().CONNECTIVITY_SERVICE);
        if ( connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED ||
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED ) {
            return true;

        } else if (
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED ||
                        connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED  ) {
            return false;
        }
        return false;
    }

    public InputStream getInputStream(URL url){
        try{
            return url.openConnection().getInputStream();
        }
        catch (IOException e){
            return null;
        }
    }
    public class ProcessInBackground extends AsyncTask<Integer, Void, Integer>
    {
        ProgressDialog progressDialog = new ProgressDialog(Activity1.this);
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setMessage("Loading...");
            progressDialog.show();
        }
        @Override
        protected Integer doInBackground(Integer... params) {
            try{
                URL url = new URL("http://abcnews.go.com/abcnews/topstories");
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                factory.setNamespaceAware(false);
                XmlPullParser xmlPullParser = factory.newPullParser();
                xmlPullParser.setInput(getInputStream(url), "UTF_8");
                boolean insideItemTag = false;
                int eventType = xmlPullParser.getEventType();
                while (eventType != XmlPullParser.END_DOCUMENT){
                    if (eventType == XmlPullParser.START_TAG){
                        if (xmlPullParser.getName().equalsIgnoreCase("item")){
                            insideItemTag = true;
                        }
                        else if (xmlPullParser.getName().equalsIgnoreCase("title") && insideItemTag){
                            String str = xmlPullParser.nextText();
                            if(str != null)
                                titles.add(str);
                        }
                        else if(xmlPullParser.getName().equalsIgnoreCase("description") && insideItemTag){
                            String str = xmlPullParser.nextText();
                            if(str != null)
                                descriptions.add(str);
                        }
                        else if (xmlPullParser.getName().equalsIgnoreCase("link") && insideItemTag){
                            String str = xmlPullParser.nextText();
                            if(str != null)
                                links.add(str);
                        }
                        else if(xmlPullParser.getName().equalsIgnoreCase("pubdate") && insideItemTag){
                            String str = xmlPullParser.nextText();
                            if(str != null)
                                pubs.add(str);
                        }
                    }
                    else if (eventType == XmlPullParser.END_TAG && xmlPullParser.getName().equalsIgnoreCase("item")){
                        insideItemTag = false;
                    }
                    eventType = xmlPullParser.next();
                }
                for(int i=0;i<titles.size();i++){
                    News news = new News(titles.get(i), descriptions.get(i), links.get(i), pubs.get(i));
                    newsList.add(news);
                }
            }
            catch (Exception e){
                Log.e(TAG, e.getMessage());
            }
            return 1;
        }

        @Override
        protected void onPostExecute(Integer s) {
            super.onPostExecute(s);
            try{
                FileOutputStream fos = openFileOutput("object.txt", MODE_APPEND);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                Data data = new Data(titles, links, descriptions, pubs);
                oos.writeObject(data);
                oos.close();
                fos.close();
            }
            catch (Exception e){
                Toast.makeText(Activity1.this, e.getMessage(), Toast.LENGTH_LONG).show();
            }
            newsAdapter = new NewsAdapter(newsList);
            recyclerView.setAdapter(newsAdapter);
            progressDialog.dismiss();
            swiperefresh.setRefreshing(false);
        }
    }
}