package com.firstapp.camera_image_storage;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
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
    ArrayList<Bitmap> imageModelArrayList=new ArrayList<>();
    Button load;
    Bitmap bitmap;
    ImageModel imageModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        camera = findViewById(R.id.camera_id);
        recyclerView = findViewById(R.id.recycler_view);
        imageView = findViewById(R.id.imgeview);

        imageModelArrayList = new ArrayList<>();
        imageModel = new ImageModel(this);

        load=findViewById(R.id.load);


        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
//                imageModelArrayList.add(new ImageModel(bitmap));
                imageAdapter=new ImageAdapter(getApplicationContext(),imageModelArrayList);
                recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),3));
                recyclerView.setHasFixedSize(true);
                recyclerView.setAdapter(imageAdapter);

            }
        });
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
//debug
                    bitmap= (Bitmap) data.getExtras().get("data");
                    imageView.setImageBitmap(bitmap);
                    imageModel.setImage(bitmap);
                    imageModelArrayList.add(bitmap);




//                    saveData();


                    alertDialog.dismiss();
                    Toast.makeText(MainActivity.this, "continue", Toast.LENGTH_SHORT).show();



                }
            });
            alertDialog = builder.create();
            alertDialog.show();


        }
    }

//    private void addimagebitmap(Bitmap bitmap) {
//            ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
//            bitmap.compress(Bitmap.CompressFormat.PNG,100,outputStream);
//            byte[] b=outputStream.toByteArray();
//            String temp= Base64.encodeToString(b,Base64.DEFAULT);
//
//
//            imageModelArrayList.add(new ImageModel(temp));
//        }




    private void saveData() {
        // shared preferences.
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();

        // getting data from gson and storing it in a string.
        String json = gson.toJson(imageModelArrayList);
        editor.putString("courses", json);
        editor.apply();
    }


}





