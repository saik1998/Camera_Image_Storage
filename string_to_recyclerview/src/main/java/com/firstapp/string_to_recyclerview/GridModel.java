package com.firstapp.string_to_recyclerview;

import android.widget.ImageView;

public class GridModel {
    private String courseName;
    private int image;

    public GridModel(ImageView courseNameEdt) {
    }


//    public ModelClass(String courseName) {
//        this.courseName = courseName;
//    }

    // creating getter and setter methods.
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public GridModel(int image) {
        this.image = image;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
