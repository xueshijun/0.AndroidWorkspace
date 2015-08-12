package com.fit.photo;

import android.app.Application;
import android.graphics.Bitmap;

public class Photo extends Application {

	Bitmap bmp;
	
	String name;
	
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Bitmap getBmp() {
		return bmp;
	}

	public void setBmp(Bitmap bmp) {
		this.bmp = bmp;
	}
	
	
}
