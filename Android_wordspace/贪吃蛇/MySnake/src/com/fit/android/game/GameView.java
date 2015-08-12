package com.fit.android.game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.view.View;

public class GameView extends View {
	public int f = 0;
	Snake snake = new Snake(320, 320);
	Node random;
	boolean runFlag = false;
	Handler handler = new Handler();
	Handler panelHandler;
	int s=300;
	Runnable updateThead = null;

	public GameView(Context context, Handler pHandler) {
		super(context);
		panelHandler = pHandler;

		random = getRandom();
		updateThead = new Runnable() {
			public void run() {
				runFlag = true;
				int result = snake.move(random);

				if (result == 3 || result == 4) {
					panelHandler.sendEmptyMessage(f);
					handler.removeCallbacks(updateThead);
				}

				switch (result) {
				case Snake.MOVE:
					break;
				case Snake.EAT:
					f = f + 10;
					if(s>100){
						s = s - 10;
					}
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
				if (runFlag) {
					handler.postDelayed(this, s);
				}
			}
		};
	}

	@Override
	protected void onDraw(Canvas canvas) {
		Paint p = new Paint();
		p.setAntiAlias(true);

		p.setColor(Color.GRAY);
		canvas.drawRect(0, 0, 320, 320, p);

		p.setColor(Color.GREEN);
		random.draw(canvas, p);

		snake.show(canvas);

	}

	public int getThreads() {

		return 1;
	}

	public Node getRandom() {
		int x = ((int) (Math.random() * 32)) * 10 + 5;
		int y = ((int) (Math.random() * 32)) * 10 + 5;

		while (!snake.checkRandom(x, y)) {
			x = ((int) (Math.random() * 32)) * 10 + 5;
			y = ((int) (Math.random() * 32)) * 10 + 5;
		}

		return new Node(x, y);
	}

}
