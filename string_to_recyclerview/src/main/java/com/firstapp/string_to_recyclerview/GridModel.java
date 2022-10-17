package com.firstapp.string_to_recyclerview;

import android.graphics.Bitmap;
import android.net.Uri;

public class GridModel {
    private Bitmap image;
//    public ModelClass(String courseName) {
//        this.courseName = courseName;
//    }

    // creating getter and setter methods.


    public GridModel(Bitmap image) {
        this.image = image;
    }

    public GridModel(Uri bitmap) {
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
