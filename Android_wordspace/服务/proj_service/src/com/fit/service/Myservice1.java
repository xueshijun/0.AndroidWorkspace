package com.fit.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class Myservice1 extends IntentService {
	private static final String TAG=Myservice1.class.getName();
	public Myservice1() {
		super("我的服务");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		
		
		int target=intent.getIntExtra("cnt",5);
		for(int i=0;i<target;i++){
			Log.i(TAG,target+"正在执行！！");
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
