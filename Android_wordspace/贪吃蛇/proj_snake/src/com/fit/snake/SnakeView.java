package com.fit.snake;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

public class SnakeView extends View {
	
	
	Snake snake=new Snake(320,320);
	//随机出现的点
	Node random;
	boolean runFlag=false;
	
	public SnakeView(Context context) {
		super(context);
		random =getRandom();
		new Thread(){
			public void run() {
				runFlag=true;
				while(runFlag){
					int result=snake.move(random);
					switch (result) {
					case Snake.MOVE:
						break;
					case Snake.EAT:
						random = getRandom();
						break;
					case Snake.BREAK:
						runFlag = false;
						break;
					case Snake.SWAP:
						runFlag = false;
						break;
					}
					postInvalidate();
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
		}.start();
	}
	@Override
	protected void onDraw(Canvas canvas) {

	Paint paint=new Paint();
	paint.setAntiAlias(true);
	//画出背景
	paint.setColor(Color.GRAY);
	canvas.drawRect(new Rect(0,0,320,320), paint);
	
	//画出随机点
	paint.setColor(Color.GREEN);
	random.drawNode(canvas, paint);
	
	snake.drawSnake(canvas);
	}

	private Node getRandom(){
		int x=((int)(Math.random()*32))*10+5;
		int y=((int)(Math.random()*32))*10+5;
		
		while(!snake.checkRandom(x, y)){
		x=((int)(Math.random()*32))*10+5;
		y=((int)(Math.random()*32))*10+5;
		}
		return new Node(x, y);
	}
}
