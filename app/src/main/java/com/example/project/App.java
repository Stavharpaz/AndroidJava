package com.example.project;

import android.app.Application;

import com.example.project.Utilities.ImageLoader;
import com.example.project.Utilities.SignalGenerator;


public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ImageLoader.initImageLoader(this, "https://img.freepik.com/free-vector/blank-camellia-leaves-frame_53876-118806.jpg?size=626&ext=jpg&ga=GA1.2.2030371807.1683731340&semt=ais");
        SignalGenerator.init(this);

    }
}
