package com.test;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Activity1 extends Activity {

	/* ����������� */
	private NotificationManager myNotiManager;
	private Spinner mySpinner;
	private ArrayAdapter<String> myAdapter;
	private static final String[] status = { "����", "�뿪", "æµ��", "���ϻ���", "����" };

	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);

		setContentView(R.layout.main);

		/* ��ʼ������ */
		myNotiManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		
		mySpinner = (Spinner) findViewById(R.id.mySpinner);
		myAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, status);
		/* Ӧ��myspinner_dropdown�Զ��������˵�ģʽ */
		myAdapter.setDropDownViewResource(R.layout.myspinner_dropdown);
		/* ��ArrayAdapter���Spinner������ */
		mySpinner.setAdapter(myAdapter);

		/* ��mySpinner���OnItemSelectedListener */
		mySpinner
				.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {

					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						/* ����ѡ���item���ж�Ҫ����һ��notification */
						if (status[arg2].equals("����")) {
							setNotiType(R.drawable.msn, "����");
						} else if (status[arg2].equals("�뿪")) {
							setNotiType(R.drawable.away, "�뿪");
						} else if (status[arg2].equals("æµ��")) {
							setNotiType(R.drawable.busy, "æµ��");
						} else if (status[arg2].equals("���ϻ���")) {
							setNotiType(R.drawable.min, "���ϻ���");
						} else {
							setNotiType(R.drawable.offine, "����");
						}
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
					}
				});
	}

	/* ����Notification��method */
	private void setNotiType(int iconId, String text) {
		/*
		 * �����µ�Intent����Ϊ���Notification������ʱ�� �����е�Activity
		 */
		Intent notifyIntent = new Intent(this, Activity2.class);
		notifyIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		/* ����PendingIntent��Ϊ���õ������е�Activity */
		PendingIntent appIntent = PendingIntent.getActivity(Activity1.this, 0,
				notifyIntent, 0);

		/* ����Notication����������ز��� */
		Notification myNoti = new Notification();
		/* ����statusbar��ʾ��icon */
		myNoti.icon = iconId;
		/* ����statusbar��ʾ��������Ϣ */
		myNoti.tickerText = text;
		/* ����notification����ʱͬʱ����Ĭ������ */
		myNoti.defaults = Notification.DEFAULT_SOUND;
		/* ����Notification�������Ĳ��� */
		myNoti.setLatestEventInfo(Activity1.this, "MSN��¼״̬", text, appIntent);
		/* �ͳ�Notification */
		myNotiManager.notify(0, myNoti);
	}
}