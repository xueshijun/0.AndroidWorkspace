package com.fit.binder;

import com.fit.binder.MyService.MyBinder;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	//服务对象
	MyService myService;
	//定义服务是否已经绑定的标志
	boolean mBound=false;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button btn=(Button) findViewById(R.id.btu);
        btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(mBound){
					int num=myService.gerRandomNumber();
					Toast.makeText(MainActivity.this,"已连接上，随机数是："+num,Toast.LENGTH_LONG).show();
				}
			}
		});
    }
    
    @Override
    protected void onStart() {
    	super.onStart();
    	//访问服务对象
    	Intent intent=new Intent(MainActivity.this,MyService.class);
    	//绑定服务
    	this.bindService(intent, connection, Context.BIND_AUTO_CREATE);
    	
    }
    
    @Override
    protected void onStop() {

    	super.onStop();
    	//如果已经绑定，就解除绑定
    	if(mBound){
    		unbindService(connection);
        	mBound=false;
    	}
    	
    }
    //匿名内部内，建立与服务器的连接
    private ServiceConnection connection=new ServiceConnection() {
		
		@Override
		//没连接
		public void onServiceDisconnected(ComponentName name) {
			mBound=false;
		}
		
		@Override
		//已经连接上
		public void onServiceConnected(ComponentName name, IBinder service) {
			//接受服务器返回的IBinder对象
			MyBinder binder=(MyBinder) service;
			//通过返回的IBinder对象调用getService()方法，得到服务对象
			myService=binder.getService();
			
			mBound=true;
		}
	};
}