package com.fit.shap;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

public class MyShap extends View {

	private int flag=-1;
	private Thread auto=null;
	private boolean autoFlag=false;
	
	public MyShap(Context context) {
		super(context);
	}
	
	
	public void setFlag(int f){
		this.flag=f;
	}

	public void runDraw(){
		autoFlag=true;
		auto=new Thread(){

			@Override
			public void run() {
				int f=0;
				while(autoFlag){
					f++;
					if(f>3){
						f=1;
					}
					setFlag(f);
					postInvalidate();
					
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			
		};
		auto.start();
	}
	
	public void stopDraw(){
		autoFlag=false;
	}
	@Override
	protected void onDraw(Canvas canvas) {
		//定义一个画笔
		Paint paint=new Paint();
		paint.setAntiAlias(true);
		//画笔颜色
		paint.setColor(Color.GRAY);
		//画板
		 canvas.drawRect(new Rect(0,0,320,320), paint);
		 
		 switch(flag){
		 case 1:
			 paint.setColor(Color.YELLOW);
			 canvas.drawLine(20, 20, 240,250, paint);
			 break;
		 case 2:
			 paint.setColor(Color.GREEN);
			 canvas.drawRect(new Rect(30,30, 240,240), paint);
			 break;
		 case 3:
			 paint.setColor(Color.BLUE);
			 canvas.drawText("这是我的文本！！",20,200, paint);
			 break;
		 }
		
	}
}
