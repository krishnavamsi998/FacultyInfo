package com.example.physicawalla.adapters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.Handler;
import android.os.Looper;

import com.example.physicawalla.R;
import com.example.physicawalla.models.Faculty;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyItemView> {

    List<Faculty> mFacultyList;
    Handler mHandler;
    ExecutorService mExecutorService;
    public MyAdapter(List<Faculty> faculties){
        mFacultyList = faculties;
        mHandler = new Handler(Looper.getMainLooper());
        mExecutorService = Executors.newFixedThreadPool(10);
    }
    @NonNull
    @Override
    public MyItemView onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view,parent,false);

        return new MyItemView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyItemView holder, int position) {
        holder.mName.setText(mFacultyList.get(position).getName());
        holder.mSubject.setText(mFacultyList.get(position).getSubject());
        holder.mQualification.setText(mFacultyList.get(position).getQualification());


        mExecutorService.execute(()->{
            URL url;
            Bitmap bitmap = null;
            try {
                //Thread.sleep(2000);
                url = new URL(mFacultyList.get(position).getUrl());
                bitmap = BitmapFactory.decodeStream(url.openStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(bitmap != null) {
                Bitmap finalBitmap = bitmap;
                mHandler.post(()-> holder.mImageView.setImageBitmap(finalBitmap));

            }
        });


    }

    @Override
    public int getItemCount() {
        return mFacultyList.size();
    }

    public void setFacultyList(List<Faculty> list){
        mFacultyList = list;
        notifyDataSetChanged();
    }

    public static class MyItemView extends RecyclerView.ViewHolder{
        TextView mName,mSubject,mQualification;
        ImageView mImageView;
        public MyItemView(@NonNull View itemView) {
            super(itemView);
            mName = itemView.findViewById(R.id.name_text_view);
            mSubject = itemView.findViewById(R.id.subject_text_view);
            mQualification = itemView.findViewById(R.id.qualification_text_view);
            mImageView = itemView.findViewById(R.id.imageView);
        }
    }
}
