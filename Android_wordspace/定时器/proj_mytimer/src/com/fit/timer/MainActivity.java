package com.fit.timer;

import java.util.Date;
import java.util.TimerTask;
import java.util.Timer;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
	int i=0;
	TextView view;
	Timer timer=new Timer();
	
	
	Handler handler=new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch(msg.what){
			case 1:
				i++;
				view.setText(String.valueOf(i));
				break;
			}
		};
	};
	TimerTask task=new TimerTask() {
		
		@Override
		public void run() {
			Message message=new Message();
			message.what=1;
			handler.sendMessage(message);
		}
	};
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        view=(TextView) findViewById(R.id.view);
        timer.scheduleAtFixedRate(task, new Date(), 2000);
    }
}