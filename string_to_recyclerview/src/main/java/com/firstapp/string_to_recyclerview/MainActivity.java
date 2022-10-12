package com.firstapp.string_to_recyclerview;

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
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  {

//    ImageView imageView;
//    Button button,btnReset;
//    GridView gridView;
//    public static final int RequestPermissionCode = 1;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        button = findViewById(R.id.btnSelectPhoto);
//        imageView = findViewById(R.id.viewImage);
//        btnReset=findViewById(R.id.btnResetPhoto);
//        gridView=findViewById(R.id.gridview);
//        EnableRuntimePermission();
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                selectImage();
//            }
//        });
//
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        return super.onCreateOptionsMenu(menu);
//
//        // Inflate the menu; this adds options to the action bar if it is present.
////        getMenuInflater().inflate(R.menu.main, menu);
////        return true;
//    }
//
//    private void selectImage() {
//
//        final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};
//        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//        builder.setTitle("Add Photo!");
//        builder.setItems(options, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int item) {
//
//                if (options[item].equals("Take Photo")) {
////                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
////                    File f = new File(android.os.Environment.getExternalStorageDirectory(), "temp.jpg");
////                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
////                    startActivityForResult(intent, 1);
//                    Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//                    startActivityForResult(intent, 7);
//                } else if (options[item].equals("Choose from Gallery")) {
//                    Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                    startActivityForResult(intent, 2);
//                } else if (options[item].equals("Cancel")) {
//                    dialogInterface.dismiss();
//                }
//
//            }
//        });
//        builder.show();
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
////        if (resultCode == RESULT_OK) {
////            if (requestCode == 1) {
////                File f = new File(Environment.getExternalStorageDirectory().toString());
////                for (File temp : f.listFiles()) {
////                    if (temp.getName().equals("temp.jpg")) {
////                        f = temp;
////                        break;
////                    }
////                }
////                try {
////                    Bitmap bitmap;
////                    BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
////                    bitmap = BitmapFactory.decodeFile(f.getAbsolutePath(),
////                            bitmapOptions);
////                    imageView.setImageBitmap(bitmap);
////                    String path = android.os.Environment
////                            .getExternalStorageDirectory()
////                            + File.separator
////                            + "Phoenix" + File.separator + "default";
////                    f.delete();
////                    OutputStream outFile = null;
////                    File file = new File(path, String.valueOf(System.currentTimeMillis()) + ".jpg");
////                    try {
////                        outFile = new FileOutputStream(file);
////                        bitmap.compress(Bitmap.CompressFormat.JPEG, 85, outFile);
////                        outFile.flush();
////                        outFile.close();
////                    } catch (FileNotFoundException e) {
////                        e.printStackTrace();
////                    } catch (IOException e) {
////                        e.printStackTrace();
////                    } catch (Exception e) {
////                        e.printStackTrace();
////                    }
////                }
////                catch (Exception e) {
////                    e.printStackTrace();
////                }
////            } else if (requestCode == 2) {
////                Uri selectedImage = data.getData();
////                String[] filePath = { MediaStore.Images.Media.DATA };
////                Cursor c = getContentResolver().query(selectedImage,filePath, null, null, null);
////                c.moveToFirst();
////                int columnIndex = c.getColumnIndex(filePath[0]);
////                String picturePath = c.getString(columnIndex);
////                c.close();
////                Bitmap thumbnail = (BitmapFactory.decodeFile(picturePath));
////                Log.w("Tag",picturePath+"");
////                imageView.setImageBitmap(thumbnail);
////            }
////        }
//
//        if (requestCode == 7 && resultCode == RESULT_OK) {
//            final CharSequence[] options = {"Are you sure you want to save this picture?", "Save","Cancel"};
//            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//            builder.setTitle("Add Photo!");
//            builder.setItems(options, new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialogInterface, int item) {
//
//                    if (options[item].equals("Take Photo")) {
////                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
////                    File f = new File(android.os.Environment.getExternalStorageDirectory(), "temp.jpg");
////                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
////                    startActivityForResult(intent, 1);
//                        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//                        startActivityForResult(intent, 7);
//                    }  else if (options[item].equals("Cancel")) {
//                        dialogInterface.dismiss();
//                    } else if (options[item].equals("Save")) {
//                        dialogInterface.dismiss();
//                    }
//
//                }
//            });
//            builder.show();
//            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
//
//            imageView.setImageBitmap(bitmap);
//
//        }
//    }
//    private void EnableRuntimePermission() {
//        if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
//                Manifest.permission.CAMERA)) {
//            Toast.makeText(MainActivity.this,"CAMERA permission allows us to Access CAMERA app",     Toast.LENGTH_LONG).show();
//        } else {
//            ActivityCompat.requestPermissions(MainActivity.this,new String[]{
//                    Manifest.permission.CAMERA}, RequestPermissionCode);
//        }
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] result) {
//        super.onRequestPermissionsResult(requestCode, permissions, result);
//
//        switch (requestCode) {
//            case RequestPermissionCode:
//                if (result.length > 0 && result[0] == PackageManager.PERMISSION_GRANTED) {
//                    Toast.makeText(MainActivity.this, "Permission Granted, Now your application can access CAMERA.", Toast.LENGTH_LONG).show();
//                } else {
//                    Toast.makeText(MainActivity.this, "Permission Canceled, Now your application cannot access CAMERA.", Toast.LENGTH_LONG).show();
//                }
//                break;
//        }
//    }
//////////////////==================================//////////////////////////////


    //        private static final int PICK_IMAGE_REQUEST = 0;
//        private final String TAG = "Main Activity";
//        private ImageView mImage;
//        private Uri mImageUri;
//        private Button choosePhoto;
//        private Button reset;
////////////////////==============/////
// creating variables for our ui components.
//private EditText courseNameEdt;
    private ImageView courseNameEdt;
    private Button addBtn, saveBtn;
    private RecyclerView courseRV;

    // variable for our adapter class and array list
    private GridAdapter adapter;
    private ArrayList<GridModel> courseModalArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//            mImage = findViewById(R.id.viewImage);
//
//            choosePhoto = (Button) findViewById(R.id.btnSelectPhoto);
//            choosePhoto.setOnClickListener(this);
//            reset = (Button) findViewById(R.id.btnResetPhoto);
//            reset.setOnClickListener(this);
//
//            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
//            String mImageUri = preferences.getString("image", null);
//
//            if (mImageUri != null) {
//                mImage.setImageURI(Uri.parse(mImageUri));
//            } else {
//                mImage.setImageResource(R.drawable.ic_baseline_camera_enhance_24);


        ////===
        // initializing our variables.
        courseNameEdt = findViewById(R.id.idEdtCourseName);
        addBtn = findViewById(R.id.idBtnAdd);
        saveBtn = findViewById(R.id.idBtnSave);
        courseRV = findViewById(R.id.idRVCourses);

        // calling method to load data
        // from shared prefs.
        loadData();
        // calling method to build
        // recycler view.
        buildRecyclerView();
        // on click listener for adding data to array list.
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // below line is use to add data to array list.
                courseModalArrayList.add(new GridModel(R.drawable.ic_baseline_camera_alt_24));
                // notifying adapter when new data added.
                adapter.notifyItemInserted(courseModalArrayList.size());
            }
        });
        // on click listener for saving data in shared preferences.
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calling method to save data in shared prefs.
                saveData();
            }
        });
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
        if (courseModalArrayList == null) {
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

        // below line is to save data in shared
        // prefs in the form of string.
        editor.putString("courses", json);
        // below line is to apply changes
        // and save data in shared prefs.

        editor.apply();

        // after saving data we are displaying a toast message.
        Toast.makeText(this, "Saved Array List to Shared preferences. ", Toast.LENGTH_SHORT).show();
    }

    public void imageCLick(View view) {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 7);
    }
}


/**
 * Select an image
 */
//        public void imageSelect() {
//            permissionsCheck();
//            Intent intent;
//            if (Build.VERSION.SDK_INT < 19) {
//                intent = new Intent(Intent.ACTION_GET_CONTENT);
//            } else {
//                intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
//                intent.addCategory(Intent.CATEGORY_OPENABLE);
//            }
//            intent.setType("image/*");
//            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
//        }

//        public void permissionsCheck() {
//            if (ContextCompat.checkSelfPermission(this,
//                    Manifest.permission.READ_EXTERNAL_STORAGE)
//                    != PackageManager.PERMISSION_GRANTED) {
//                ActivityCompat.requestPermissions(this,
//                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
//                return;
//            }
//        }

//        @Override
//        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//            // Check which request we're responding to
//            super.onActivityResult(requestCode, resultCode, data);
//            if (requestCode == PICK_IMAGE_REQUEST) {
//                // Make sure the request was successful
//                if (resultCode == RESULT_OK) {
//                    // The user picked a image.
//                    // The Intent's data Uri identifies which item was selected.
//                    if (data != null) {
//
//                        // This is the key line item, URI specifies the name of the data
//                        mImageUri = data.getData();
//
//                        // Saves image URI as string to Default Shared Preferences
//                        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
//                        SharedPreferences.Editor editor = preferences.edit();
//                        editor.putString("image", String.valueOf(mImageUri));
//                        editor.commit();
//
//                        // Sets the ImageView with the Image URI
//                        mImage.setImageURI(mImageUri);
//                        mImage.invalidate();
//                    }
//                }
//            }
//        }

/**
 * Clear Default Shared Preferences
 */
//        public void clearData() {
//            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
//            SharedPreferences.Editor editor = preferences.edit();
//            editor.clear();
//            editor.commit();
//            finish();
//            startActivity(getIntent());
//        }

/**
 * to handle the buttons
 */
//        @Override
//        public void onClick(View view) {
//            switch (view.getId()) {
//                case R.id.btnSelectPhoto:
//                    // get image
//                    imageSelect();
//                    break;
//                case R.id.btnResetPhoto:
//                    // increase by 1
//                    clearData();
//                    break;
//                default:
//                    break;
//            }
//        }
// }