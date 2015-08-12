package com.fit.messenget;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.view.View;

public class MainActivity extends Activity {
	Messenger mService=null;
	boolean mBound;
	//建立连接的一个匿名内部内
	private ServiceConnection connection=new ServiceConnection() {
		
		@Override
		public void onServiceDisconnected(ComponentName name) {
			mService=null;
			mBound=false;
		}
		
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			//接收服务器的IBinder对象，初始化一个Messenger对象，客户端用这个对象可以向服务器发送信息
			mService=new Messenger(service);
			mBound=true;
		}
	};
	
	 public void sayHello(View v) { 
	        if (!mBound) return; 
	        // Create and send a message to the service, using a supported 'what' value 
	        //确定要发送的信息
	        Message msg = Message.obtain(null, 1) ;
	        try { 
	        	//向服务器发送信息
	            mService.send(msg); 
	        } catch (RemoteException e) { 
	            e.printStackTrace(); 
	        } 
	    } 
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
    }
	@Override
	protected void onStart() {
		//Intent intent=new Intent("android.intent.action.xie");
		//访问服务器，并绑定服务器
		bindService(new Intent("android.intent.action.xie"),connection,Context.BIND_AUTO_CREATE);
		super.onStart();
	}
	@Override
	protected void onStop() {
		//如果已经绑定到服务器，就解除绑定
		if(mBound){
			unbindService(connection);
			mBound=false;
		}
		super.onStop();
	}
}