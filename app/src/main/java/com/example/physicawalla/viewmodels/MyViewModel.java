package com.example.physicawalla.viewmodels;

import com.example.physicawalla.models.Faculty;
import com.example.physicawalla.network.MyFacultyNetwork;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel {
    private final MutableLiveData<List<Faculty>> mFacultyLiveData;
    private final MyFacultyNetwork mMyFacultyNetwork;
    public MyViewModel(){
        mMyFacultyNetwork = new MyFacultyNetwork();
        mFacultyLiveData = mMyFacultyNetwork.getListMutableLiveData();
    }

    public LiveData<List<Faculty>> getFacultyLiveData(){
        return mFacultyLiveData;
    }
}
