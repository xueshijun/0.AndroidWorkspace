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
		//处理客户端发送过来的信息
		public void handleMessage(Message msg) {
			
			switch(msg.what){
			case MSG:
				Toast.makeText(getApplicationContext(),"您好，欢迎您来访问服务！",Toast.LENGTH_LONG).show();
				break;
				default:
			}
			super.handleMessage(msg);
		}
		
		
	}
	
	
	//通过Handler类来构造一个Messenger对象
	final Messenger mMessenger=new Messenger(new IncomingHandler());
	@Override
	//返回一个IBiner对象
	public IBinder onBind(Intent arg0) {
		
		Toast.makeText(getApplicationContext(),"您好，已经绑定到服务！",Toast.LENGTH_LONG).show();
		return mMessenger.getBinder();
	}

}
