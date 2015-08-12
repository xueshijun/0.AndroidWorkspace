package com.fit.memo.widget;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.fit.entity.Memo;
import com.fit.receiver.MyReceiver;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.widget.RemoteViews;

public class MyWidgets extends AppWidgetProvider {
	private ContentResolver resolver;
	private RemoteViews view;
	private AppWidgetManager wManager;
	private ComponentName provider;
	private int count;
	
	private static final SimpleDateFormat sdf=new SimpleDateFormat("MM 月 dd 日");
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		
		//查询数据库
		resolver=context.getContentResolver();
		String ltime=sdf.format(new Date());
		Cursor cursor=resolver.query(MyReceiver.Memo,null,Memo.LTIME+"=?",new String[]{ltime},null);
		//得到任务数
		while(cursor.moveToNext()){
			count=cursor.getCount();
		}
		System.out.println(count);
		
		view=new RemoteViews(context.getPackageName(),R.layout.main);
		provider=new ComponentName(context,MyWidgets.class);
		//点击到应用程序
		Intent intent=new Intent("com.fit.demo.memo");
		PendingIntent pending=PendingIntent.getActivity(context,0, intent,0);
		this.view.setOnClickPendingIntent(R.id.view, pending);
		
		view.setTextViewText(R.id.view,ltime+"\n"+count+"个任务");
		wManager=appWidgetManager;
		wManager.updateAppWidget(provider, view);
		super.onUpdate(context, appWidgetManager, appWidgetIds);
	}
	
	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		super.onDeleted(context, appWidgetIds);
	}
}
