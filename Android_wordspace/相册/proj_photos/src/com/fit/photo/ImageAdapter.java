package com.fit.photo;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {

	
	private Context mContext;
	private List<Photo> bitlist=null;
	
	public ImageAdapter(Context mContext, List<Photo> bitlist) {
		super();
		this.mContext = mContext;
		this.bitlist = bitlist;
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
		Photo photo=bitlist.get(position);
		ImageView image=new ImageView(mContext);
		image.setImageBitmap((Bitmap)photo.getBmp());
		image.setLayoutParams(new Gallery.LayoutParams(100, 100));
		image.setScaleType(ImageView.ScaleType.FIT_XY);
		return image;
	}

}
