package com.example.testretrofit_recyclerppt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class ShowImage extends AppCompatActivity {

    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_image);

        image=findViewById(R.id.showImage);

        String getImage=getIntent().getStringExtra("imageURL");

        Glide.with(ShowImage.this)
                .load(getImage)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(image);


    }
}