package com.example.alphabat69.rssfeed;

/**
 * Created by AlphaBAT69 on 15-02-2018.
 */

public class News {
    private String title, description, link, pub;
    public News(){
    }
    public News(String title, String description, String link, String pub){
        this.title = title;
        this.description = description;
        this.link = link;
        this.pub = pub;
    }
    //getter
    public String getTitle(){return this.title;}
    public String getDescription(){return this.description;}
    public String getLink(){return this.link;}
    public String getPub(){return this.pub;}
    //setter
    public void setTitle(String title){this.title = title;}
    public void setDescription(String description){this.description = description;}
    public void setLink(String link){this.link = link;}
    public void setPub(String pub){this.pub = pub;}
}
