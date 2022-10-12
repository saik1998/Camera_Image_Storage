package com.firstapp.camera_image_storage;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton camera;
    private static final int pic_id = 123;


    AlertDialog alertDialog;
    RecyclerView recyclerView;
    ImageView imageView;
    ImageAdapter imageAdapter;
    ArrayList<ImageModel> imageModelArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        camera = findViewById(R.id.camera_id);
        recyclerView = findViewById(R.id.recycler_view);
        imageView = findViewById(R.id.imgeview);


//
//        // adding layout manager to our recycler view.
//


//        imageAdapter = new ImageAdapter(this, imageModelArrayList);
//        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setAdapter(imageAdapter);



        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                askCameraPermission();

            }
        });

    }


    private void askCameraPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, pic_id);
        } else {
            openCamera();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == pic_id) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openCamera();

            } else {
                Toast.makeText(this, "Camera permission to Required", Toast.LENGTH_SHORT).show();

            }
        }

    }

    private void openCamera() {
        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(camera, pic_id);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == pic_id && resultCode == RESULT_OK) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            View root = getLayoutInflater().inflate(R.layout.custom_dialog, null);
            builder.setView(root);
            builder.setCancelable(false);

            Button cancel = root.findViewById(R.id.button_cancel);
            Button save = root.findViewById(R.id.continue_button);


            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openCamera();
                    Toast.makeText(MainActivity.this, "cancel", Toast.LENGTH_SHORT).show();


                }
            });

            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Bitmap bitmap= (Bitmap) data.getExtras().get("data");
                    imageView.setImageBitmap(bitmap);

                    saveData();


//                    imageModelArrayList.add(new ImageModel(imageView));
//                    imageAdapter = new ImageAdapter(getApplicationContext(), imageModelArrayList);
//                    recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
//                    recyclerView.setHasFixedSize(true);





//
//                    // setting layout manager to our recycler view.
//                     recyclerView.setLayoutManager(manager);
//
//                    // setting adapter to our recycler view.
//                    recyclerView.setAdapter(imageAdapter);



//                    imageModelArrayList.add(new ImageModel(imageView));



                    alertDialog.dismiss();
                    Toast.makeText(MainActivity.this, "continue", Toast.LENGTH_SHORT).show();


//                    imageModelArrayList.add(new ImageModel());
//                    // notifying adapter when new data added.
//                    imageAdapter.notifyItemInserted(imageModelArrayList.size());

                }
            });
            alertDialog = builder.create();
            alertDialog.show();


        }
    }

    private void saveData() {

        // method for saving the data in array list.
        // creating a variable for storing data in
        // shared preferences.
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);

        // creating a variable for editor to
        // store data in shared preferences.
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // creating a new variable for gson.
        Gson gson = new Gson();

        // getting data from gson and storing it in a string.
        String json = gson.toJson(imageModelArrayList);
//        imageModelArrayList.add(new ImageModel(imageView));
//        imageAdapter = new ImageAdapter(getApplicationContext(), imageModelArrayList);
//        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
//        recyclerView.setHasFixedSize(true);

        // below line is to save data in shared
        // prefs in the form of string.
        editor.putString("courses", json);
        // below line is to apply changes
        // and save data in shared prefs.

        editor.apply();

        // after saving data we are displaying a toast message.
//        Toast.makeText(this, "Saved Array List to Shared preferences. ", Toast.LENGTH_SHORT).show();



    }


}





