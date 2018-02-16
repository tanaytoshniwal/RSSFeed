package com.example.alphabat69.rssfeed;

/**
 * Created by AlphaBAT69 on 16-02-2018.
 */

public class Card {
    private String title;
    private String link;
    private String file;
    public Card(String title, String link, String file){
        this.title = title;
        this.link = link;
        this.file = file;
    }
    //getter
    public String getTitle(){return this.title;}
    public String getLink(){return this.link;}
    public String getFile(){return this.file;}
    //setter
    public void setTitle(String title){this.title = title;}
    public void setLink(String link){this.title = link;}
    public void setFile(String file){this.file = file;}
}
