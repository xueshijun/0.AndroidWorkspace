package com.fit.binder;

import java.util.Random;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class MyService extends Service {
	//实例化一个IBinder对象，返回
	private final IBinder mBinder=new MyBinder();
	//实例化一个随机数对象
	private final Random mRandom=new Random();
	@Override
	//返回一个IBinder对象，可以调用MyBinder里的getService()方法
	public IBinder onBind(Intent arg0) {
		return mBinder;
	}
	
	class MyBinder extends Binder{
		//构造方法，返回一个服务对象       
		public MyService getService(){
			return MyService.this;
		}
	}
	//随机数方法 
	public int gerRandomNumber(){
		return mRandom.nextInt(100);
	}
}
