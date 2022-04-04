package com.example.physicawalla;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.physicawalla.adapters.MyAdapter;
import com.example.physicawalla.models.Faculty;
import com.example.physicawalla.viewmodels.MyViewModel;


import java.util.ArrayList;
import java.util.List;



public class RecyclerFragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    public RecyclerFragment() {
        // Required empty public constructor
    }


    public static RecyclerFragment newInstance(String param1, String param2) {
        RecyclerFragment fragment = new RecyclerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    ProgressBar mProgressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_recycler, container, false);

        mProgressBar = view.findViewById(R.id.progress_circular);
        List<Faculty> list = new ArrayList<>();
        MyAdapter myAdapter = new MyAdapter(list);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(myAdapter);

        MyViewModel myViewModel = new ViewModelProvider(this).get(MyViewModel.class);
        LiveData<List<Faculty>> ld = myViewModel.getFacultyLiveData();
        ld.observe(getViewLifecycleOwner(), faculties -> {
            mProgressBar.setVisibility(View.GONE);
            myAdapter.setFacultyList(faculties);
        });


        if(ld.getValue() == null || ld.getValue().isEmpty()){
            mProgressBar.setVisibility(View.VISIBLE);
        }


        return view;
    }



}