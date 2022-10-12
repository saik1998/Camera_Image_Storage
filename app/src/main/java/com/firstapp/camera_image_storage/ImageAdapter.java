package com.firstapp.camera_image_storage;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;


public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.MyViewModel> {
    Context context;

    ArrayList<ImageModel> imageModelArrayList;

    public ImageAdapter(Context context, ArrayList<ImageModel> imageModelArrayList) {
        this.context = context;
        this.imageModelArrayList = imageModelArrayList;
    }

    @NonNull
    @Override
    public ImageAdapter.MyViewModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_recycler,null);

        return new MyViewModel(root);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageAdapter.MyViewModel holder, int position) {
        ImageModel imageModel=imageModelArrayList.get(position);

        holder.imageView.setImageResource(imageModel.getImage());

    }

    @Override
    public int getItemCount() {
        return imageModelArrayList.size();
    }

    public class MyViewModel extends RecyclerView.ViewHolder {
        ImageView imageView;

        public MyViewModel(@NonNull View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.recycler_image);
        }
    }
}
