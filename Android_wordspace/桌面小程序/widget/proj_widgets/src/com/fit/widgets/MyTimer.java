package com.fit.widgets;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class MyTimer extends TimerTask {

	private RemoteViews view;
	private AppWidgetManager wManager;
	private ComponentName provider;
	private static final SimpleDateFormat df=new SimpleDateFormat("hh :mm :ss");
	
	
	public MyTimer(Context context,AppWidgetManager wManager) {
		super();
		this.wManager = wManager;
		this.view=new RemoteViews(context.getPackageName(),R.layout.main);
		this.provider=new ComponentName(context,MyWidgets.class);
		
		Intent intent=new Intent("com.fit.add");
		PendingIntent pending=PendingIntent.getActivity(context,0, intent,0);
		this.view.setOnClickPendingIntent(R.id.view, pending);
	}

	@Override
	public void run() {
		
		view.setTextViewText(R.id.view,df.format(new Date()));
		wManager.updateAppWidget(provider, view);	
	}

}
