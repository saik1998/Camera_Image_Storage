package com.firstapp.string_to_recyclerview;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  {

    private ImageView courseNameEdt;
    private Button addBtn, saveBtn;
    private RecyclerView courseRV;

    RecyclerView recyclerView;

    private AlertDialog alertDialog;

    private static final int pic_id = 123;


    // variable for our adapter class and array list
    private GridAdapter adapter;
    ArrayList<GridModel> courseModalArrayList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        // initializing our variables.
        courseNameEdt = findViewById(R.id.imgeview);
        addBtn = findViewById(R.id.idBtnAdd);
        saveBtn = findViewById(R.id.idBtnSave);
        courseRV = findViewById(R.id.idRVCourses);

        courseNameEdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //camera open
                openCamera();

            }
        });


        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //data loaded in recycler view
                loadData();

                buildRecyclerView();
//
            }
        });
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });
    }



    private void openCamera() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 7);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 7 && resultCode == RESULT_OK && data!=null) {


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
//
//




                    Bitmap bitmap= (Bitmap) data.getExtras().get("data");
                    courseNameEdt.setImageBitmap(bitmap);
//                    courseModalArrayList.set(1,new GridModel(bitmap));

                    courseModalArrayList.add(new GridModel(bitmap));





//                    GridLayoutManager manager = new GridLayoutManager(this,2);
//                    courseRV.setHasFixedSize(true);
//
//                    // setting layout manager to our recycler view.
//                    courseRV.setLayoutManager(manager);
//
//                    // setting adapter to our recycler view.
//                    courseRV.setAdapter(adapter);
//
////                    buildRecyclerView();






                    saveData();



                    alertDialog.dismiss();
                    Toast.makeText(MainActivity.this, "continue", Toast.LENGTH_SHORT).show();



                }
            });
            alertDialog = builder.create();
            alertDialog.show();


        }
    }


    private void buildRecyclerView() {
        // initializing our adapter class.

        adapter = new GridAdapter(courseModalArrayList, MainActivity.this);
        // adding layout manager to our recycler view.
        GridLayoutManager manager = new GridLayoutManager(this,2);
        courseRV.setHasFixedSize(true);

        // setting layout manager to our recycler view.
        courseRV.setLayoutManager(manager);

        // setting adapter to our recycler view.
        courseRV.setAdapter(adapter);
    }

    private void loadData() {
        // method to load arraylist from shared prefs
        // initializing our shared prefs with name as
        // shared preferences.
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);

        // creating a variable for gson.
        Gson gson = new Gson();

        // below line is to get to string present from our
        // shared prefs if not present setting it as null.
        String json = sharedPreferences.getString("courses", null);

        // below line is to get the type of our array list.
        Type type = new TypeToken<ArrayList<GridModel>>() {}.getType();

        // in below line we are getting data from gson
        // and saving it to our array list
        courseModalArrayList = gson.fromJson(json, type);
        // checking below if the array list is empty or not
        if (courseModalArrayList == null)
        {
            // if the array list is empty
            // creating a new array list.
            courseModalArrayList = new ArrayList<>();
        }
    }
    @SuppressLint("NewApi")
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
        String json = gson.toJson(courseModalArrayList);
//        courseModalArrayList.add(new GridModel(courseNameEdt));
        // below line is to save data in shared
        // prefs in the form of string.
        editor.putString("courses", json);
        // below line is to apply changes
        // and save data in shared prefs.
        editor.apply();
        // after saving data we are displaying a toast message.
        Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
    }







}

