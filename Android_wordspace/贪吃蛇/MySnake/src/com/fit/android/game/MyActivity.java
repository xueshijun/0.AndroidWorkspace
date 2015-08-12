package com.fit.android.game;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MyActivity extends Activity {
	Snake snake;
	GameView view;
	TextView tx;
	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			int score = msg.what;
			tx.setText("您得到的总分数为:" + score);
			view.handler.removeCallbacks(view.updateThead);
			view.snake = new Snake(320, 320);
			view.random = view.getRandom();
			view.f = 0;
			
			snake = view.snake;
		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		view = new GameView(this, handler);
		snake = view.snake;
		LinearLayout lean = new LinearLayout(this);
		tx = new TextView(this);

		lean.setOrientation(LinearLayout.VERTICAL);
		view.setLayoutParams(new LayoutParams(320, 320));

		lean.addView(view);
		LinearLayout lean1 = new LinearLayout(this);
		lean1.setOrientation(LinearLayout.HORIZONTAL);
		Button btn1 = new Button(this);
		btn1.setText("开始");
		btn1.setTextSize(24);
		Button btn2 = new Button(this);
		btn2.setText("暂停");
		btn2.setTextSize(24);
		Button btn3 = new Button(this);
		btn3.setText("退出游戏");
		btn3.setTextSize(24);
		lean1.addView(btn1);
		lean1.addView(btn2);
		lean1.addView(btn3);
		lean.addView(lean1);
		lean.addView(tx);
		setContentView(lean);

		btn1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				tx.setText("");
				view.handler.post(view.updateThead);
			}
		});
		btn2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				view.handler.removeCallbacks(view.updateThead);
			}
		});
		btn3.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				tx.setText("您的总分是：" + view.f);
				view.handler.removeCallbacks(view.updateThead);
				view.snake = new Snake(320, 320);
				view.random = view.getRandom();
				view.f = 0;
				snake = view.snake;
			}
		});

	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_DPAD_UP:
			snake.changeDirect(Snake.UP);
			break;
		case KeyEvent.KEYCODE_DPAD_RIGHT:
			snake.changeDirect(Snake.RIGHT);
			break;
		case KeyEvent.KEYCODE_DPAD_DOWN:
			snake.changeDirect(Snake.DOWN);
			break;
		case KeyEvent.KEYCODE_DPAD_LEFT:
			snake.changeDirect(Snake.LEFT);
			break;
		}
		return super.onKeyDown(keyCode, event);
	}
}
