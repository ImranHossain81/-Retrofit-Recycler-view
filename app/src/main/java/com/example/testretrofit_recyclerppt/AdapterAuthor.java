package com.example.testretrofit_recyclerppt;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

public class AdapterAuthor extends RecyclerView.Adapter<AdapterAuthor.Holder> {

    ArrayList<ModelData> arrayList;
    Context context;


    public AdapterAuthor(ArrayList<ModelData> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterAuthor.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_data, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterAuthor.Holder holder, @SuppressLint("RecyclerView") int position) {

        holder.name.setText("Author Name: " + arrayList.get(position).getAuthor());
        holder.id.setText("ID : " + arrayList.get(position).getId());
        holder.wdith.setText("Weidth : " + arrayList.get(position).getWidth());
        holder.height.setText("Height : " + arrayList.get(position).getHeight());

        Glide.with(context)
                .load(arrayList.get(position).getDownloadUrl())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imageView);


        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                Button cancel, delete;

                final Dialog dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.item_dialog);
                dialog.show();

                cancel = dialog.findViewById(R.id.cencel);
                delete = dialog.findViewById(R.id.delete);

                delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        arrayList.remove(position);
                        notifyDataSetChanged();
                        dialog.dismiss();


                    }
                });


                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        dialog.dismiss();
                    }
                });


                return false;
            }
        });


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(context, ShowImage.class);
                intent.putExtra("imageURL", arrayList.get(position).getDownloadUrl());
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView name, id, wdith, height;

        public Holder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.item_name);
            id = itemView.findViewById(R.id.item_id);
            imageView = itemView.findViewById(R.id.imageView);
            wdith = itemView.findViewById(R.id.weidth);
            height = itemView.findViewById(R.id.height);
        }
    }
}
