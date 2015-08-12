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
	//�������ӵ�һ�������ڲ���
	private ServiceConnection connection=new ServiceConnection() {
		
		@Override
		public void onServiceDisconnected(ComponentName name) {
			mService=null;
			mBound=false;
		}
		
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			//���շ�������IBinder���󣬳�ʼ��һ��Messenger���󣬿ͻ����������������������������Ϣ
			mService=new Messenger(service);
			mBound=true;
		}
	};
	
	 public void sayHello(View v) { 
	        if (!mBound) return; 
	        // Create and send a message to the service, using a supported 'what' value 
	        //ȷ��Ҫ���͵���Ϣ
	        Message msg = Message.obtain(null, 1) ;
	        try { 
	        	//�������������Ϣ
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
		//���ʷ����������󶨷�����
		bindService(new Intent("android.intent.action.xie"),connection,Context.BIND_AUTO_CREATE);
		super.onStart();
	}
	@Override
	protected void onStop() {
		//����Ѿ��󶨵����������ͽ����
		if(mBound){
			unbindService(connection);
			mBound=false;
		}
		super.onStop();
	}
}