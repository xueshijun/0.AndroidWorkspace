/**
 * <p>Title: Snake</p>
 * <p>Copyright: (C) 2007 The Android Open Source Project. Licensed under the Apache License, Version 2.0 (the "License")</p>
 * @author Gavin ��ע
 */

package com.deaboway.snake;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Snake: a simple game that everyone can enjoy.
 * 
 * This is an implementation of the classic Game "Snake", in which you control a
 * serpent roaming around the garden looking for apples. Be careful, though,
 * because when you catch one, not only will you become longer, but you'll move
 * faster. Running into yourself or the walls will end the game.
 * 
 */
// ̰���ߣ� ������Ϸ����һ����԰����ƻ���ԣ�����ƻ����䳤���ٶȱ�졣�����Լ���ǽ�͹ҵ���
public class Snake extends Activity {

	private SnakeView mSnakeView;

	private static String ICICLE_KEY = "snake-view";

	/**
	 * Called when Activity is first created. Turns off the title bar, sets up
	 * the content views, and fires up the SnakeView.
	 * 
	 */
	// �� activity ��һ�δ���ʱ������
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.snake_layout);

		mSnakeView = (SnakeView) findViewById(R.id.snake);
		mSnakeView.setTextView((TextView) findViewById(R.id.text));

		// ������״̬��ȷ�������¿�ʼ���ǻָ�״̬
		if (savedInstanceState == null) {
			// �洢״̬Ϊ�գ�˵�������������л���׼��״̬
			mSnakeView.setMode(SnakeView.READY);
		} else {
			// �Ѿ����������ô��ȥ�ָ�ԭ��״̬
			Bundle map = savedInstanceState.getBundle(ICICLE_KEY);
			if (map != null) {
				// �ָ�״̬
				mSnakeView.restoreState(map);
			} else {
				// ����״̬Ϊ��ͣ
				mSnakeView.setMode(SnakeView.PAUSE);
			}
		}
	}

	// ��ͣ�¼�������ʱ
	@Override
	protected void onPause() {
		super.onPause();
		// Pause the game along with the activity
		mSnakeView.setMode(SnakeView.PAUSE);
	}

	// ״̬����
	@Override
	public void onSaveInstanceState(Bundle outState) {
		// �洢��Ϸ״̬��View��
		outState.putBundle(ICICLE_KEY, mSnakeView.saveState());
	}

}