package com.firstapp.camera_image_storage;

import android.widget.ImageView;

public class ImageModel {
    int image;

    public ImageModel(int image) {
        this.image = image;
    }

    public ImageModel(ImageView imageView) {
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }


}
