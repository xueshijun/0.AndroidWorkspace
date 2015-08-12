package com.fit.widgets;

import java.util.Date;
import java.util.Timer;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;

public class MyWidgets extends AppWidgetProvider {

	private Timer timer;
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		if(timer==null){
			timer=new Timer();
			timer.scheduleAtFixedRate(new MyTimer(context, appWidgetManager), new Date(),1000);
		}
	}
	
	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		if(timer!=null){
			timer.cancel();
		}
	}
}
