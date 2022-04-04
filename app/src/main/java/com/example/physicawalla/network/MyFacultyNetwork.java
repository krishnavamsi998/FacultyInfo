package com.example.physicawalla.network;

import android.os.Looper;
import android.util.JsonReader;
import android.util.Log;
import android.os.Handler;

import com.example.physicawalla.models.Faculty;
import com.example.physicawalla.viewmodels.MyViewModel;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


import javax.net.ssl.HttpsURLConnection;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyFacultyNetwork {

    List<Faculty> mFacultyList;
    ExecutorService mExecutorService;


    MutableLiveData<List<Faculty>> mListMutableLiveData;
    Handler mHandler;
    public MyFacultyNetwork(){
        mFacultyList = new ArrayList<>();
        mListMutableLiveData = new MutableLiveData<>();
        mExecutorService = Executors.newSingleThreadExecutor();
        mHandler = new Handler(Looper.getMainLooper());
        updateFacultyList();

    }


    public MutableLiveData<List<Faculty>> getListMutableLiveData(){
        return mListMutableLiveData;
    }
    public List<Faculty> getFacultyList(){
        return mFacultyList;
    }

    public void updateFacultyList(){
        mExecutorService.execute(()->{
            try {
                URL url = new URL("https://my-json-server.typicode.com/easygautam/data/users");
                HttpsURLConnection connection = (HttpsURLConnection)url.openConnection();
                connection.setRequestMethod("GET");

                if(connection.getResponseCode() ==200){
                    InputStream inputStream = connection.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    JsonReader jsonReader = new JsonReader(inputStreamReader);
                    jsonReader.beginArray();

                    while(jsonReader.hasNext()){
                        mFacultyList.add(parseJsonObject(jsonReader));
                    }
                    mHandler.post(()->{
                       mListMutableLiveData.setValue(mFacultyList);
                    });

                }
                else{
                    Log.d("network","Unable to connect");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public Faculty parseJsonObject(JsonReader jsonReader) throws IOException{
        jsonReader.beginObject();

        jsonReader.skipValue();
        int id = jsonReader.nextInt();

        jsonReader.skipValue();
        String name = jsonReader.nextString();

        jsonReader.skipValue();
        jsonReader.beginArray();
        List<String> subjects = new ArrayList<>();
        while(jsonReader.hasNext()){
            subjects.add(jsonReader.nextString());
        }
        jsonReader.endArray();


        jsonReader.skipValue();

        jsonReader.beginArray();
        List<String> qualifications = new ArrayList<>();
        while(jsonReader.hasNext()){
            qualifications.add(jsonReader.nextString());
        }
        jsonReader.endArray();


        jsonReader.skipValue();
        String url = jsonReader.nextString();

        jsonReader.endObject();

        return new Faculty(id,name,subjects,qualifications,url);
    }
}
