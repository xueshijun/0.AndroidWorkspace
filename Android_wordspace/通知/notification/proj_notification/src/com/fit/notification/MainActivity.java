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
				//��ȡ֪ͨ�����������ڷ���֪ͨ
				NotificationManager manager=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
				//ʵ����Notification�������������Ϣ����
				Notification notice=new Notification(R.drawable.icon,"����һ��֪ͨ��Ϣ",System.currentTimeMillis());
//				//�����չ����Ϣ��������� 
//				String noticeTitle="֪ͨ�ı���";
//				String noticeContent="֪ͨ��չ������";
//				//ͨ����ϢҪ�򿪵�Ӧ�ó���
//				Intent intent=new Intent("com.fit.demo.notice");
//				//�ȴ��û���������Ϣ
//				PendingIntent pending=PendingIntent.getActivity(MainActivity.this,0, intent, 0);
//				//�����е���Ϣ���ݷŵ�Notification��
//				notice.setLatestEventInfo(getApplicationContext(), noticeTitle, noticeContent, pending);
//				//��Ӧ�ó������Ϣͼ����ʧ
//				notice.flags|=Notification.FLAG_AUTO_CANCEL;
//				//ͨ����Ϣ������������Ϣ
//				//manager.notify(1,notice);
//				
//				//���ڲ��Ϸ�����Ϣ�����Ḳ����ǰ����Ϣ
//				manager.notify(index,notice);
				
				
				
				//�Զ���֪ͨ
				RemoteViews view=new RemoteViews(getPackageName(),R.layout.notice);
				view.setTextViewText(R.id.title,"�Զ���֪ͨ����");
				view.setTextViewText(R.id.content,"֪ͨ������");
				view.setTextColor(R.id.content,Color.BLUE);
				
				notice.contentView=view;
				Intent intent=new Intent("com.fit.demo.notice");
				PendingIntent pending=PendingIntent.getActivity(MainActivity.this,0, intent, 0);
				notice.contentIntent=pending;
				
				//��Ӧ�ó������Ϣͼ����ʧ
				notice.flags|=Notification.FLAG_AUTO_CANCEL;
				//ͨ����Ϣ������������Ϣ
				manager.notify(1,notice);
			}
		});
    }
}