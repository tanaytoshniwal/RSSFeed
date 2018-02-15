package com.example.alphabat69.rssfeed;

import android.content.Context;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * Created by AlphaBAT69 on 16-02-2018.
 */

public class SaveData {
    private static Data data;
    private static Context context;
    public SaveData(Context context){
        this.context = context;
    }
    public void store(Data data){
        this.data = data;
        try{
            FileOutputStream fos = context.openFileOutput("object.txt", Context.MODE_APPEND);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(data);
            oos.close();
            fos.close();
        }
        catch (Exception e){
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    public Data get(){
        Data ldata = null;
        try {
            FileInputStream fis = context.openFileInput("object.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            ldata = (Data) ois.readObject();
            ois.close();
            fis.close();
        }
        catch (Exception e){
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return ldata;
    }
}
