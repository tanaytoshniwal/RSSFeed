package com.example.alphabat69.rssfeed;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AlphaBAT69 on 16-02-2018.
 */

public class Data implements Serializable {
    private ArrayList<String> l1,l2,l3,l4;
    public Data(ArrayList<String> l1, ArrayList<String> l2, ArrayList<String> l3, ArrayList<String> l4){
        this.l1=l1;
        this.l2=l2;
        this.l3=l3;
        this.l4=l4;
    }
    public ArrayList<String> get1(){
        return l1;
    }
    public ArrayList<String> get2(){
        return l2;
    }
    public ArrayList<String> get3(){
        return l3;
    }
    public ArrayList<String> get4(){
        return l4;
    }
}
