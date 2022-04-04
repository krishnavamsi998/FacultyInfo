package com.example.physicawalla;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.os.Bundle;
import android.util.Log;


public class MainActivity extends AppCompatActivity {

    RecyclerFragment mRecyclerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Log.d("here", " hereing inginasd");
        mRecyclerFragment = (RecyclerFragment)fragmentManager.findFragmentById(R.id.fragment_container_view_tag);
        if(mRecyclerFragment == null){
            mRecyclerFragment = RecyclerFragment.newInstance(null,null);
            fragmentTransaction.add(R.id.fragment_container_view_tag,mRecyclerFragment).addToBackStack(null).commit();

        }
    }
}