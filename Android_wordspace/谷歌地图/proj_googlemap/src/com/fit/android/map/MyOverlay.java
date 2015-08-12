package com.fit.android.map;

import java.util.LinkedList;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

public class MyOverlay extends ItemizedOverlay<OverlayItem> {

	
	private LinkedList<OverlayItem> itemList=new LinkedList<OverlayItem>();
	private Context context;
	
	public MyOverlay(Drawable arg0,Context c) {
		super(boundCenterBottom(arg0));
		context=c;
	}

	@Override
	protected OverlayItem createItem(int i) {
		return itemList.get(i);
	}

	@Override
	public int size() {
		return itemList.size();
	}
	
	//添加一个添加位置的方法
	public void addPosition(int latitude,int longitude,String title,String snippet){
		OverlayItem item=new OverlayItem(new GeoPoint(latitude, longitude), title, snippet);
		itemList.add(item);
		populate();
	}
@Override
protected boolean onTap(int index) {
	OverlayItem item=itemList.get(index);
	AlertDialog.Builder dialog=new AlertDialog.Builder(context);
	dialog.setTitle(item.getTitle());
	dialog.setMessage(item.getSnippet());
	dialog.show();
	return true;
}
}
