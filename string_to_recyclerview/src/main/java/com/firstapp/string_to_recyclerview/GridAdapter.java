package com.firstapp.string_to_recyclerview;


    import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

    import com.bumptech.glide.Glide;

    import java.io.File;
    import java.util.ArrayList;


public class GridAdapter extends RecyclerView.Adapter<GridAdapter.ViewHolder> {

        // creating a variable for array list and context.
        private ArrayList<GridModel> courseModalArrayList;
        private Context context;

    public GridAdapter(ArrayList<GridModel> courseModalArrayList, MainActivity mainActivity) {
        //modellist size =1
        this.courseModalArrayList=courseModalArrayList;

        //context:null
        this.context=mainActivity;
    }


    // creating a constructor for our variables.


//        public GridAdapter(ArrayList<GridModel> courseModalArrayList, Context context) {
//            this.courseModalArrayList = courseModalArrayList;
//            this.context = context;
//        }

        @NonNull
        @Override
        public GridAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            // below line is to inflate our layout.
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull GridAdapter.ViewHolder holder, int position) {
            // setting data to our views of recycler view.
            GridModel modal = courseModalArrayList.get(position);
            //  holder.courseNameTV.setText(modal.getCourseName());
//            holder.courseNameTV.setImageResource(modal.getImage());

            Glide.with(context)
                    .load(new File(String.valueOf(modal.getImage())))
                    .into(holder.courseNameTV);


        }

        @Override
        public int getItemCount() {
            // returning the size of array list.
                return courseModalArrayList.size();
            }



    public class ViewHolder extends RecyclerView.ViewHolder {
            // creating variables for our views.
            //  private TextView courseNameTV;
            private ImageView courseNameTV;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                // initializing our views with their ids.
                courseNameTV = itemView.findViewById(R.id.idTVCourseName);

            }
        }
    }

