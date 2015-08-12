package com.fit.notification;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.RemoteViews.RemoteView;

public class MainActivity extends Activity {
	
	
	private int index;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        
        Button btn=(Button) findViewById(R.id.btn);
        btn.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				
//				index++;
//				
				//获取通知管理器，用于发送通知
				NotificationManager manager=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
				//实例化Notification对象，用于添加消息内容
				Notification notice=new Notification(R.drawable.icon,"这是一个通知消息",System.currentTimeMillis());
//				//添加扩展的消息标题和内容 
//				String noticeTitle="通知的标题";
//				String noticeContent="通知扩展的内容";
//				//通过消息要打开的应用程序
//				Intent intent=new Intent("com.fit.demo.notice");
//				//等待用户来处理消息
//				PendingIntent pending=PendingIntent.getActivity(MainActivity.this,0, intent, 0);
//				//把所有的消息内容放到Notification里
//				notice.setLatestEventInfo(getApplicationContext(), noticeTitle, noticeContent, pending);
//				//打开应用程序后消息图标消失
//				notice.flags|=Notification.FLAG_AUTO_CANCEL;
//				//通过消息管理器发送消息
//				//manager.notify(1,notice);
//				
//				//用于不断发送消息而不会覆盖以前的消息
//				manager.notify(index,notice);
				
				
				
				//自定义通知
				RemoteViews view=new RemoteViews(getPackageName(),R.layout.notice);
				view.setTextViewText(R.id.title,"自定义通知标题");
				view.setTextViewText(R.id.content,"通知的内容");
				view.setTextColor(R.id.content,Color.BLUE);
				
				notice.contentView=view;
				Intent intent=new Intent("com.fit.demo.notice");
				PendingIntent pending=PendingIntent.getActivity(MainActivity.this,0, intent, 0);
				notice.contentIntent=pending;
				
				//打开应用程序后消息图标消失
				notice.flags|=Notification.FLAG_AUTO_CANCEL;
				//通过消息管理器发送消息
				manager.notify(1,notice);
			}
		});
    }
}