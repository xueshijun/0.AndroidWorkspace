package com.fit.android.game;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;

public class MainActivity extends Activity {
	Snake snake;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		GameView view = new GameView(this, null);
		snake = view.snake;
		setContentView(view);
	}

	@Override
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