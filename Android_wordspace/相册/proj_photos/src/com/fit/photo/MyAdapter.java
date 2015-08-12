package com.fit.photo;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {

	private Context mContext;
	private List<Photo> bitlist;
	public MyAdapter(Context mContext,List<Photo> listbit) {
		super();
		this.mContext = mContext;
		this.bitlist=listbit;
	}

	@Override
	public int getCount() {
		return bitlist.size();
	}

	@Override
	public Object getItem(int position) {
		return bitlist.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LinearLayout view=null;
		if(convertView==null){
			view=new LinearLayout(mContext);
			LayoutInflater flater=LayoutInflater.from(mContext);
			flater.inflate(R.layout.list,view);
			ImageView image=(ImageView) view.findViewById(R.id.imageview);
			TextView text=(TextView) view.findViewById(R.id.text);
			 Photo photo=bitlist.get(position);
			image.setImageBitmap((Bitmap) photo.getBmp());
			text.setText((CharSequence)photo.getName());
		}else{
			view=(LinearLayout) convertView;
		}
		return view;
	}

}
