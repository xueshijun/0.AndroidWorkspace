package com.fit.pull;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {

	
	private Context mContext;
	private int[] pictures={R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.e,R.drawable.f,R.drawable.g,R.drawable.h,R.drawable.i,R.drawable.j};
	
	
	public ImageAdapter(Context mContext) {
		super();
		this.mContext = mContext;
	}

	@Override
	public int getCount() {
		return pictures.length;
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView image=new ImageView(mContext);
		image.setImageResource(pictures[position]);
		image.setLayoutParams(new Gallery.LayoutParams(120, 120));
		image.setScaleType(ImageView.ScaleType.FIT_XY);
		
		return image;
	}

}
