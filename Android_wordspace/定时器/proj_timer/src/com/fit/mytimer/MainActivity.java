package com.fit.mytimer;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

public class MainActivity extends Activity {
	int i=0;
	int [] pictures =new int[]{R.drawable.a,R.drawable.b,R.drawable.c};
	ImageView image=null;
	

	Timer timer=new Timer();
	Handler handler=new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch(msg.what){
			case 1:
				if(i<pictures.length){
					image.setImageResource(pictures[i]);
				}else{
					i=0;
				}
				i++;
				break;
			}
		};
	};
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
       
        image=(ImageView) findViewById(R.id.image);
        timer.scheduleAtFixedRate(task, new Date(),2000);
    }
	
	TimerTask task=new TimerTask() {
		@Override
		public void run() {
			Message message=new Message();
			message.what=1;
			handler.sendMessage(message);
		}
	};
}