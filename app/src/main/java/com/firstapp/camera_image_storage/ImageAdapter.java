package com.firstapp.camera_image_storage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.ArrayList;


public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.MyViewModel> {
    Context context;
    ArrayList<Bitmap> imageModelArrayList=new ArrayList<>();

    ImageModel imageModel;

    AlertDialog alertDialog;


    public ImageAdapter(Context context, ArrayList<Bitmap> imageModelArrayList) {
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

//        ImageModel imageModel=imageModelArrayList.get(position);

        holder.imageView.setImageBitmap(imageModelArrayList.get(position));




        //  holder.courseNameTV.setText(modal.getCourseName());
//            holder.courseNameTV.setImageResource(modal.getImage());

//        Glide.with(context)
//                .load(new File(String.valueOf(imageModel.getImage())))
//                .into(holder.imageView);

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
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    AlertDialog.Builder builder=new AlertDialog.Builder(itemView.getContext());
                    View view=LayoutInflater.from(context).inflate(R.layout.imge_dialog,null);
                    ImageView dialogImage=view.findViewById(R.id.long_click_Image);

                    //set image in dialog box
                    dialogImage.setImageBitmap(imageModelArrayList.get(getAdapterPosition()));

                    builder.setView(view);
                    alertDialog=builder.create();
                    alertDialog.show();












                }
            });



        }
    }
}
