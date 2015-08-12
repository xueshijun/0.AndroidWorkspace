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
	//�������
	MyService myService;
	//��������Ƿ��Ѿ��󶨵ı�־
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
					Toast.makeText(MainActivity.this,"�������ϣ�������ǣ�"+num,Toast.LENGTH_LONG).show();
				}
			}
		});
    }
    
    @Override
    protected void onStart() {
    	super.onStart();
    	//���ʷ������
    	Intent intent=new Intent(MainActivity.this,MyService.class);
    	//�󶨷���
    	this.bindService(intent, connection, Context.BIND_AUTO_CREATE);
    	
    }
    
    @Override
    protected void onStop() {

    	super.onStop();
    	//����Ѿ��󶨣��ͽ����
    	if(mBound){
    		unbindService(connection);
        	mBound=false;
    	}
    	
    }
    //�����ڲ��ڣ������������������
    private ServiceConnection connection=new ServiceConnection() {
		
		@Override
		//û����
		public void onServiceDisconnected(ComponentName name) {
			mBound=false;
		}
		
		@Override
		//�Ѿ�������
		public void onServiceConnected(ComponentName name, IBinder service) {
			//���ܷ��������ص�IBinder����
			MyBinder binder=(MyBinder) service;
			//ͨ�����ص�IBinder�������getService()�������õ��������
			myService=binder.getService();
			
			mBound=true;
		}
	};
}