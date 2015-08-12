package com.fit.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver {

	private final String TAG=MyReceiver.class.getName();
	@Override
	public void onReceive(Context arg0, Intent arg1) {

		
		Log.i(TAG, "接收到广播："+this.toString());
		
		Intent intent=new Intent(arg0,MainActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		arg0.startActivity(intent);
		
		Log.i(TAG, "完成对广播的响应：："+this.toString());
	}

}
