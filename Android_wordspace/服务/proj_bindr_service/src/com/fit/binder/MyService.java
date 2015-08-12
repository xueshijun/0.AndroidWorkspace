package com.fit.binder;

import java.util.Random;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class MyService extends Service {
	//ʵ����һ��IBinder���󣬷���
	private final IBinder mBinder=new MyBinder();
	//ʵ����һ�����������
	private final Random mRandom=new Random();
	@Override
	//����һ��IBinder���󣬿��Ե���MyBinder���getService()����
	public IBinder onBind(Intent arg0) {
		return mBinder;
	}
	
	class MyBinder extends Binder{
		//���췽��������һ���������       
		public MyService getService(){
			return MyService.this;
		}
	}
	//��������� 
	public int gerRandomNumber(){
		return mRandom.nextInt(100);
	}
}
