package com.fit.binder;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.widget.Toast;

public class MyMessage extends Service {

	private final int MSG=1;
	
	
	
	class IncomingHandler extends Handler{

		@Override
		//����ͻ��˷��͹�������Ϣ
		public void handleMessage(Message msg) {
			
			switch(msg.what){
			case MSG:
				Toast.makeText(getApplicationContext(),"���ã���ӭ�������ʷ���",Toast.LENGTH_LONG).show();
				break;
				default:
			}
			super.handleMessage(msg);
		}
		
		
	}
	
	
	//ͨ��Handler��������һ��Messenger����
	final Messenger mMessenger=new Messenger(new IncomingHandler());
	@Override
	//����һ��IBiner����
	public IBinder onBind(Intent arg0) {
		
		Toast.makeText(getApplicationContext(),"���ã��Ѿ��󶨵�����",Toast.LENGTH_LONG).show();
		return mMessenger.getBinder();
	}

}
