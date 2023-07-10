package com.example.project.Utilities;

import android.content.Context;
import android.widget.ImageView;

import androidx.appcompat.widget.AppCompatImageView;

import com.bumptech.glide.Glide;


public class ImageLoader {

    private static ImageLoader instance;


    private static Context appContext;

    private static String commonImage;

    private ImageLoader(Context context){
        this.appContext = context;
    }

    public static ImageLoader getInstance() {
        return instance;
    }

    public static ImageLoader initImageLoader (Context context, String url){
        if (instance == null)
            instance = new ImageLoader(context);

        commonImage = url;

        return instance;
    }

    public void loadImage(AppCompatImageView imageView){
        Glide
                .with(this.appContext)
                .load(commonImage)
                .centerCrop()
                .into(imageView);
    }

    public void loadImage(int imageResource, ImageView imageView){
        Glide
                .with(this.appContext)
                .load(imageResource)
                .into(imageView);
    }


}
