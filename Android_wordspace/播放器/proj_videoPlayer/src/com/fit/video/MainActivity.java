package com.fit.video;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends Activity {

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        VideoView video=(VideoView) findViewById(R.id.video);
        video.setVideoPath(Environment.getExternalStorageDirectory()+"/ab.3gp");
        video.setMediaController(new MediaController(this));
        video.start();
    }
}