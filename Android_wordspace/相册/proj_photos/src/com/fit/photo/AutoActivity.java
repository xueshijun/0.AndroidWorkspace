package com.fit.photo;

import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

public class AutoActivity extends Activity {
	int i=0;
	ImageView image;
	FileList fileList=new FileList();
	List<Photo> photosList=null;
	Timer timer=new Timer();
    Handler handler=new Handler(){
    	public void handleMessage(android.os.Message msg) {
    		switch(msg.what){
    		case 1:
    			if(i<photosList.size()){
    				Photo photo=photosList.get(i);
    				image.setImageBitmap((Bitmap)photo.getBmp());
    			}else{
    				i=-1;
    			}
    			i++;
    			break;
    		}
    	};
    };
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auto);
        
        
        String path=Environment.getExternalStorageDirectory().getAbsolutePath();
        photosList=fileList.fileList(path);
        
        image=(ImageView)findViewById(R.id.photo);
		timer.scheduleAtFixedRate(task,new Date(),2000);
	}
	 TimerTask task=new TimerTask() {
			
			@Override
			public void run() {
				Message message=new Message();
				message.what=1;
				handler.sendMessage(message);
			}
		};
		
		
		
		protected void onDestroy() {
			super.onDestroy();
			timer.cancel();
		};
}
