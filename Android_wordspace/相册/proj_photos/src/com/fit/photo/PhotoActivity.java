package com.fit.photo;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class PhotoActivity extends Activity {

	private int displayWidth,displayHeight;
	private float scaleWidth=1,scaleHeight=1;
	private int id=0;
	private LinearLayout layout;
	 ImageView image;
	Bitmap bmp;
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo);
        
        layout=(LinearLayout) findViewById(R.id.layout);
        //取得屏幕分辨率
        DisplayMetrics dm=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        displayWidth=dm.widthPixels;
        displayHeight=dm.heightPixels-80;
        
        image=(ImageView) findViewById(R.id.photo);
        Photo photo=(Photo) getApplication();
        bmp=photo.getBmp();
        image.setImageBitmap(bmp);
       
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater flater=getMenuInflater();
		flater.inflate(R.menu.menu2, menu);
		
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {

		int  menuId=item.getItemId();
		switch(menuId){
		case R.id.big:
			big();
			break;
			
		case R.id.small:
			small();
			break;
		
		}
		
		
		return super.onMenuItemSelected(featureId, item);
	}
	
	private void small(){
		//获得Bitmap的高和宽
		int bmpWidth=bmp.getWidth();
		int bmpHeight=bmp.getHeight();
		//设置缩小比例
		double scale=0.8;
		//计算出所要缩小的比例
		scaleWidth=(float) (scaleWidth*scale);
		scaleHeight=(float) (scaleHeight*scale);
		//产生resize后的Bitmap对象
		 Matrix matrix=new Matrix();
		 matrix.postScale(scaleWidth, scaleHeight);
		 Bitmap resizeBmp=Bitmap.createBitmap(bmp, 0, 0, bmpWidth, bmpHeight, matrix, true);

		 if(id==0){
		layout.removeView(image);
	}else{
		layout.removeView((ImageView)findViewById(id));
	}
		 id++;
		 ImageView  imageview=new ImageView(this);
		 imageview.setId(id);
		 imageview.setImageBitmap(resizeBmp);
		 layout.addView(imageview);
		 setContentView(layout);
	}
	
	private void big(){
	     //获得Bitmap的高和宽
	     int bmpWidth=bmp.getWidth();
	     int bmpHeight=bmp.getHeight();
	     //设置缩小比例
	     double scale=1.25;
	     //计算出这次要缩小的比例
	     scaleWidth=(float)(scaleWidth*scale);
	     scaleHeight=(float)(scaleHeight*scale);
	     //产生resize后的Bitmap对象
	     Matrix matrix=new Matrix();
	     matrix.postScale(scaleWidth, scaleHeight);
	     Bitmap resizeBmp=Bitmap.createBitmap(bmp, 0, 0, bmpWidth, bmpHeight, matrix, true);
	     if(id==0){
	    	 layout.removeView(image);
	     }
	     else{
	    	 layout.removeView((ImageView)findViewById(id));
	      
	     }
	     id++;
	     ImageView imageView=new ImageView(this);
	     imageView.setId(id);
	     imageView.setImageBitmap(resizeBmp);
	     layout.addView(imageView);
	     setContentView(layout);
	     if(scaleWidth*scale*bmpWidth>displayWidth||scaleHeight*scale*scaleHeight>displayHeight){
	     }
	    }

}
