package com.firstapp.camera_image_storage;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

public class ImageModel {
   Bitmap image;
   Context context;

    public ImageModel(Context context) {
        this.context = context;
    }

//    public ImageModel(Bitmap imageView) {
//        this.image=imageView;
//
//    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
