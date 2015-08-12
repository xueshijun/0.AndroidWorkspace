package com.fit.receiver.sender;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends Activity {

	private static final SimpleDateFormat sdf=new SimpleDateFormat("hh :mm :ss ");
	private TextView view;
	private MyReceiver receiver;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        view=(TextView) findViewById(R.id.view);
    }
	
	
	@Override
	protected void onResume() {
		super.onResume();
		receiver=new MyReceiver();
		IntentFilter filter=new IntentFilter(Intent.ACTION_TIME_TICK);
		registerReceiver(receiver, filter);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		unregisterReceiver(receiver);
	}
	
	private class MyReceiver extends BroadcastReceiver{
		private final String TAG=MyReceiver.class.getName();
		@Override
		public void onReceive(Context context, Intent intent) {
			
			Log.i(TAG,"接收到时间信号："+this.toString());
			String text=sdf.format(new Date());
			
			view.setText(text);
			
			Log.i(TAG,"接收时间信号完成："+this.toString());
		}
		
	}
}