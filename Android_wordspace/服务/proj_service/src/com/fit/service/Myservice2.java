package com.fit.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class Myservice2 extends Service {

	@Override
	public void onCreate() {
		super.onCreate();
		Log.i("Myservice2","服务对象被创建......");
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.i("Myservice2","服务被用户调用。。。。。");
	
		int data=intent.getIntExtra("cnt",5);
		new Work(startId, data).start();
		return super.onStartCommand(intent, flags, startId);
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		Log.i("Myservice2","服务被用户绑定。。。。");
		return null;
	}
	
	@Override
	public void onDestroy() {
		Log.i("Myservice2","服务即将被销毁。。。。。");
		super.onDestroy();
	}
	
	private class Work extends Thread{
		private final int startId;
		private int data;
		public Work(int startId, int data) {
			super();
			this.startId = startId;
			this.data = data;
		}
		@Override
		public void run() {
			for(int i=0;i<data;i++){
				Log.i(this.toString(),data+"正在执行！！");
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			stopSelf(startId);
		}
		
	}
}
