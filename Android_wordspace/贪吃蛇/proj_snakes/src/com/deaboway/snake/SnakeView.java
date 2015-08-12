/**
 * <p>Title: Snake</p>
 * <p>Copyright: (C) 2007 The Android Open Source Project. Licensed under the Apache License, Version 2.0 (the "License")</p>
 * @author Gavin ��ע
 */

package com.deaboway.snake;

import java.util.ArrayList;
import java.util.Random;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

/**
 * SnakeView: implementation of a simple game of Snake
 * 
 * 
 */
public class SnakeView extends TileView {

	private static final String TAG = "Deaboway";

	/**
	 * Current mode of application: READY to run, RUNNING, or you have already
	 * lost. static final ints are used instead of an enum for performance
	 * reasons.
	 */
	// ��Ϸ״̬��Ĭ��ֵ��׼��״̬
	private int mMode = READY;

	// ��Ϸ���ĸ�״̬ ��ͣ ׼�� ���� �� ʧ��
	public static final int PAUSE = 0;
	public static final int READY = 1;
	public static final int RUNNING = 2;
	public static final int LOSE = 3;

	// ��Ϸ���ߵ�ǰ������Ĭ��ֵ����
	private int mDirection = NORTH;
	// ��һ�����ƶ�����Ĭ��ֵ����
	private int mNextDirection = NORTH;

	// ��Ϸ�����趨 �� �� �� ��
	private static final int NORTH = 1;
	private static final int SOUTH = 2;
	private static final int EAST = 3;
	private static final int WEST = 4;

	/**
	 * Labels for the drawables that will be loaded into the TileView class
	 */
	// ������ϷԪ
	private static final int RED_STAR = 1;
	private static final int YELLOW_STAR = 2;
	private static final int GREEN_STAR = 3;

	/**
	 * mScore: used to track the number of apples captured mMoveDelay: number of
	 * milliseconds between snake movements. This will decrease as apples are
	 * captured.
	 */
	// ��Ϸ�÷�
	private long mScore = 0;

	// �ƶ��ӳ�
	private long mMoveDelay = 600;

	/**
	 * mLastMove: tracks the absolute time when the snake last moved, and is
	 * used to determine if a move should be made based on mMoveDelay.
	 */
	// ���һ���ƶ�ʱ�ĺ���ʱ��
	private long mLastMove;

	/**
	 * mStatusText: text shows to the user in some run states
	 */
	// ��ʾ��Ϸ״̬���ı����
	private TextView mStatusText;

	/**
	 * mSnakeTrail: a list of Coordinates that make up the snake's body
	 * mAppleList: the secret location of the juicy apples the snake craves.
	 */
	// ��������(�������������ΪԪ��)
	private ArrayList<Coordinate> mSnakeTrail = new ArrayList<Coordinate>();

	// ƻ������(�������������ΪԪ��)
	private ArrayList<Coordinate> mAppleList = new ArrayList<Coordinate>();

	/**
	 * Everyone needs a little randomness in their life
	 */
	// �����
	private static final Random RNG = new Random();

	/**
	 * Create a simple handler that we can use to cause animation to happen. We
	 * set ourselves as a target and we can use the sleep() function to cause an
	 * update/invalidate to occur at a later date.
	 */
	// ����һ��Refresh Handler������������ ͨ��sleep()��ʵ��
	private RefreshHandler mRedrawHandler = new RefreshHandler();

	// һ��Handler
	class RefreshHandler extends Handler {

		// ������Ϣ����
		@Override
		public void handleMessage(Message msg) {
			// ����View����
			SnakeView.this.update();
			// ǿ���ػ�
			SnakeView.this.invalidate();
		}

		// �ӳٷ�����Ϣ
		public void sleep(long delayMillis) {
			this.removeMessages(0);
			sendMessageDelayed(obtainMessage(0), delayMillis);
		}
	};

	/**
	 * Constructs a SnakeView based on inflation from XML
	 * 
	 * @param context
	 * @param attrs
	 */
	// ���캯��
	public SnakeView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// ����ʱ��ʼ��
		initSnakeView();
	}

	public SnakeView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initSnakeView();
	}

	// ��ʼ��
	private void initSnakeView() {
		// ��ѡ����
		setFocusable(true);

		Resources r = this.getContext().getResources();

		// ������ƬͼƬ����
		resetTiles(4);

		// ������ͼƬ�浽Bitmap��������
		loadTile(RED_STAR, r.getDrawable(R.drawable.redstar));
		loadTile(YELLOW_STAR, r.getDrawable(R.drawable.yellowstar));
		loadTile(GREEN_STAR, r.getDrawable(R.drawable.greenstar));

	}

	// ��ʼ�µ���Ϸ������ʼ��
	private void initNewGame() {
		// ���ArrayList�б�
		mSnakeTrail.clear();
		mAppleList.clear();

		// For now we're just going to load up a short default eastbound snake
		// that's just turned north
		// ��������

		mSnakeTrail.add(new Coordinate(7, 7));
		mSnakeTrail.add(new Coordinate(6, 7));
		mSnakeTrail.add(new Coordinate(5, 7));
		mSnakeTrail.add(new Coordinate(4, 7));
		mSnakeTrail.add(new Coordinate(3, 7));
		mSnakeTrail.add(new Coordinate(2, 7));

		// �µķ��� ������
		mNextDirection = NORTH;

		// 2�����λ�õ�ƻ��
		addRandomApple();
		addRandomApple();

		// �ƶ��ӳ�
		mMoveDelay = 600;
		// ��ʼ�÷�0
		mScore = 0;
	}

	/**
	 * Given a ArrayList of coordinates, we need to flatten them into an array
	 * of ints before we can stuff them into a map for flattening and storage.
	 * 
	 * @param cvec
	 *            : a ArrayList of Coordinate objects
	 * @return : a simple array containing the x/y values of the coordinates as
	 *         [x1,y1,x2,y2,x3,y3...]
	 */
	// ��������ת�������飬��Coordinate�����x y�ŵ�һ��int�����С�����������״̬
	private int[] coordArrayListToArray(ArrayList<Coordinate> cvec) {
		int count = cvec.size();
		int[] rawArray = new int[count * 2];
		for (int index = 0; index < count; index++) {
			Coordinate c = cvec.get(index);
			rawArray[2 * index] = c.x;
			rawArray[2 * index + 1] = c.y;
		}
		return rawArray;
	}

	/**
	 * Save game state so that the user does not lose anything if the game
	 * process is killed while we are in the background.
	 * 
	 * @return a Bundle with this view's state
	 */
	// ����״̬
	public Bundle saveState() {

		Bundle map = new Bundle();

		map.putIntArray("mAppleList", coordArrayListToArray(mAppleList));
		map.putInt("mDirection", Integer.valueOf(mDirection));
		map.putInt("mNextDirection", Integer.valueOf(mNextDirection));
		map.putLong("mMoveDelay", Long.valueOf(mMoveDelay));
		map.putLong("mScore", Long.valueOf(mScore));
		map.putIntArray("mSnakeTrail", coordArrayListToArray(mSnakeTrail));

		return map;
	}

	/**
	 * Given a flattened array of ordinate pairs, we reconstitute them into a
	 * ArrayList of Coordinate objects
	 * 
	 * @param rawArray
	 *            : [x1,y1,x2,y2,...]
	 * @return a ArrayList of Coordinates
	 */
	// ��������ת�������飬��һ��int�����е�x y�ŵ�Coordinate���������С��������ָ�״̬
	private ArrayList<Coordinate> coordArrayToArrayList(int[] rawArray) {
		ArrayList<Coordinate> coordArrayList = new ArrayList<Coordinate>();

		int coordCount = rawArray.length;
		for (int index = 0; index < coordCount; index += 2) {
			Coordinate c = new Coordinate(rawArray[index], rawArray[index + 1]);
			coordArrayList.add(c);
		}
		return coordArrayList;
	}

	/**
	 * Restore game state if our process is being relaunched
	 * 
	 * @param icicle
	 *            a Bundle containing the game state
	 */
	// �ָ�״̬
	public void restoreState(Bundle icicle) {

		setMode(PAUSE);

		mAppleList = coordArrayToArrayList(icicle.getIntArray("mAppleList"));
		mDirection = icicle.getInt("mDirection");
		mNextDirection = icicle.getInt("mNextDirection");
		mMoveDelay = icicle.getLong("mMoveDelay");
		mScore = icicle.getLong("mScore");
		mSnakeTrail = coordArrayToArrayList(icicle.getIntArray("mSnakeTrail"));
	}

	/*
	 * handles key events in the game. Update the direction our snake is
	 * traveling based on the DPAD. Ignore events that would cause the snake to
	 * immediately turn back on itself.
	 * 
	 * (non-Javadoc)
	 * 
	 * @see android.view.View#onKeyDown(int, android.os.KeyEvent)
	 */
	// �����û����̲�������������Щ����
	// �����¼�����ȷ��̰����ֻ��90��ת�򣬶�����180��ת��
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent msg) {

		// ���ϼ�
		if (keyCode == KeyEvent.KEYCODE_DPAD_UP) {
			// ׼��״̬����ʧ��״̬ʱ
			if (mMode == READY | mMode == LOSE) {
				/*
				 * At the beginning of the game, or the end of a previous one,
				 * we should start a new game.
				 */
				// ��ʼ����Ϸ
				initNewGame();
				// ������Ϸ״̬Ϊ����
				setMode(RUNNING);
				// ����
				update();
				// ����
				return (true);
			}

			// ��ͣ״̬ʱ
			if (mMode == PAUSE) {
				/*
				 * If the game is merely paused, we should just continue where
				 * we left off.
				 */
				// ���ó�����״̬
				setMode(RUNNING);
				update();
				// ����
				return (true);
			}

			// ���������״̬ʱ���������ԭ�з��������ϣ���ô����ת��
			if (mDirection != SOUTH) {
				mNextDirection = NORTH;
			}
			return (true);
		}

		// ���¼�
		if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN) {
			// ԭ����������ʱ������ת����
			if (mDirection != NORTH) {
				mNextDirection = SOUTH;
			}
			// ����
			return (true);
		}

		// �����
		if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT) {
			// ԭ����������ʱ������ת����
			if (mDirection != EAST) {
				mNextDirection = WEST;
			}
			// ����
			return (true);
		}

		// ���Ҽ�
		if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
			// ԭ����������ʱ������ת��
			if (mDirection != WEST) {
				mNextDirection = EAST;
			}
			// ����
			return (true);
		}

		// ��������ʱ��ԭ�й��ܷ���
		return super.onKeyDown(keyCode, msg);
	}

	/**
	 * Sets the TextView that will be used to give information (such as "Game
	 * Over" to the user.
	 * 
	 * @param newView
	 */
	// ����״̬��ʾView
	public void setTextView(TextView newView) {
		mStatusText = newView;
	}

	/**
	 * Updates the current mode of the application (RUNNING or PAUSED or the
	 * like) as well as sets the visibility of textview for notification
	 * 
	 * @param newMode
	 */
	// ������Ϸ״̬
	public void setMode(int newMode) {

		// �ѵ�ǰ��Ϸ״̬����oldMode
		int oldMode = mMode;
		// ����Ϸ״̬����Ϊ��״̬
		mMode = newMode;

		// �����״̬������״̬����ԭ��״̬Ϊ�����У���ô�Ϳ�ʼ��Ϸ
		if (newMode == RUNNING & oldMode != RUNNING) {
			// ����mStatusTextView����
			mStatusText.setVisibility(View.INVISIBLE);
			// ����
			update();
			return;
		}

		Resources res = getContext().getResources();
		CharSequence str = "";

		// �����״̬����ͣ״̬����ô�����ı�����Ϊ��ͣ����
		if (newMode == PAUSE) {
			str = res.getText(R.string.mode_pause);
		}

		// �����״̬��׼��״̬����ô�����ı�����Ϊ׼������
		if (newMode == READY) {
			str = res.getText(R.string.mode_ready);
		}

		// �����״̬ʱʧ��״̬����ô�����ı�����Ϊʧ������
		if (newMode == LOSE) {
			// �����ֵĵ÷���ʾ����
			str = res.getString(R.string.mode_lose_prefix) + mScore
					+ res.getString(R.string.mode_lose_suffix);
		}

		// �����ı�
		mStatusText.setText(str);
		// ��ʾ��View
		mStatusText.setVisibility(View.VISIBLE);
	}

	/**
	 * Selects a random location within the garden that is not currently covered
	 * by the snake. Currently _could_ go into an infinite loop if the snake
	 * currently fills the garden, but we'll leave discovery of this prize to a
	 * truly excellent snake-player.
	 * 
	 */
	// ���ƻ��
	private void addRandomApple() {
		// �µ�����
		Coordinate newCoord = null;
		// ��ֹ��ƻ����ϯ��������
		boolean found = false;
		// û���ҵ����ʵ�ƻ��������ѭ������һֱѭ����ֱ���ҵ����ʵ�ƻ��
		while (!found) {
			// Ϊƻ������һ������,�����һ��Xֵ
			int newX = 1 + RNG.nextInt(mXTileCount - 2);
			// �����һ��Yֵ
			int newY = 1 + RNG.nextInt(mYTileCount - 2);
			// ������
			newCoord = new Coordinate(newX, newY);

			// Make sure it's not already under the snake
			// ȷ����ƻ�����������£��ȼ���û�з�����ͻ
			boolean collision = false;

			int snakelength = mSnakeTrail.size();
			// ����ռ�ݵ���������Ƚ�
			for (int index = 0; index < snakelength; index++) {
				// ֻҪ����ռ�ݵ��κ�һ��������ͬ������Ϊ������ͻ��
				if (mSnakeTrail.get(index).equals(newCoord)) {
					collision = true;
				}
			}
			// if we're here and there's been no collision, then we have
			// a good location for an apple. Otherwise, we'll circle back
			// and try again
			// ����г�ͻ�ͼ���ѭ�������û��ͻflag��ֵ����false,��ô��Ȼ���˳�ѭ����������Ҳ�͵�����
			found = !collision;
		}

		if (newCoord == null) {
			Log.e(TAG, "Somehow ended up with a null newCoord!");
		}
		// ����һ����ƻ������ƻ���б��У�����ƻ���п��ܻ��غϡ�����ʱ����Ȼ��������һ��ƻ���������أ���������������������
		mAppleList.add(newCoord);
	}

	/**
	 * Handles the basic update loop, checking to see if we are in the running
	 * state, determining if a move should be made, updating the snake's
	 * location.
	 */
	// ���� ���ֶ������ر��� ̰���� ��λ�ã� ��������ǽ��ƻ���ȵĸ���
	public void update() {
		// ����Ǵ�������״̬
		if (mMode == RUNNING) {

			long now = System.currentTimeMillis();

			// �����ǰʱ��������һ���ƶ���ʱ�䳬�����ӳ�ʱ��
			if (now - mLastMove > mMoveDelay) {
				//
				clearTiles();
				updateWalls();
				updateSnake();
				updateApples();
				mLastMove = now;
			}
			// Handler �Ự����sleepһ���ӳ�ʱ�䵥λ
			mRedrawHandler.sleep(mMoveDelay);
		}

	}

	/**
	 * Draws some walls.
	 * 
	 */
	// ����ǽ
	private void updateWalls() {
		for (int x = 0; x < mXTileCount; x++) {
			// ���ϱ��ߵ�ÿ����Ƭλ������һ����ɫ������ʶ
			setTile(GREEN_STAR, x, 0);
			// ���±��ߵ�ÿ����Ƭλ������һ����ɫ������ʶ
			setTile(GREEN_STAR, x, mYTileCount - 1);
		}
		for (int y = 1; y < mYTileCount - 1; y++) {
			// ������ߵ�ÿ����Ƭλ������һ����ɫ������ʶ
			setTile(GREEN_STAR, 0, y);
			// ���ұ��ߵ�ÿ����Ƭλ������һ����ɫ������ʶ
			setTile(GREEN_STAR, mXTileCount - 1, y);
		}
	}

	/**
	 * Draws some apples.
	 * 
	 */
	// ����ƻ��
	private void updateApples() {
		for (Coordinate c : mAppleList) {
			setTile(YELLOW_STAR, c.x, c.y);
		}
	}

	/**
	 * Figure out which way the snake is going, see if he's run into anything
	 * (the walls, himself, or an apple). If he's not going to die, we then add
	 * to the front and subtract from the rear in order to simulate motion. If
	 * we want to grow him, we don't subtract from the rear.
	 * 
	 */
	// ������
	private void updateSnake() {
		// ������־
		boolean growSnake = false;

		// �õ���ͷ����
		Coordinate head = mSnakeTrail.get(0);
		// ��ʼ��һ���µ���ͷ����
		Coordinate newHead = new Coordinate(1, 1);

		// ��ǰ����ĳ��µķ���
		mDirection = mNextDirection;

		// ���ݷ���ȷ����ͷ������
		switch (mDirection) {
		// ��������򶫣��ң�����ôX��1
		case EAST: {
			newHead = new Coordinate(head.x + 1, head.y);
			break;
		}
			// ��������������󣩣���ôX��1
		case WEST: {
			newHead = new Coordinate(head.x - 1, head.y);
			break;
		}
			// ��������򱱣��ϣ�����ôY��1
		case NORTH: {
			newHead = new Coordinate(head.x, head.y - 1);
			break;
		}
			// ����������ϣ��£�����ôY��1
		case SOUTH: {
			newHead = new Coordinate(head.x, head.y + 1);
			break;
		}
		}

		// Collision detection
		// For now we have a 1-square wall around the entire arena
		// ��ͻ��� ����ͷ�Ƿ�����ǽ�ص�����ô��Ϸ����
		if ((newHead.x < 1) || (newHead.y < 1) || (newHead.x > mXTileCount - 2)
				|| (newHead.y > mYTileCount - 2)) {
			// ������Ϸ״̬ΪLose
			setMode(LOSE);
			// ����
			return;

		}

		// Look for collisions with itself
		// ��ͻ��� ����ͷ�Ƿ�����������ص����ص��Ļ���ϷҲ����
		int snakelength = mSnakeTrail.size();

		for (int snakeindex = 0; snakeindex < snakelength; snakeindex++) {
			Coordinate c = mSnakeTrail.get(snakeindex);
			if (c.equals(newHead)) {
				// ������Ϸ״̬ΪLose
				setMode(LOSE);
				// ����
				return;
			}
		}

		// Look for apples
		// ������ͷ��ƻ�����Ƿ��ص�
		int applecount = mAppleList.size();
		for (int appleindex = 0; appleindex < applecount; appleindex++) {
			Coordinate c = mAppleList.get(appleindex);
			if (c.equals(newHead)) {
				// ����ص���ƻ�������ƻ���б����Ƴ�
				mAppleList.remove(c);
				// ����������һ����ƻ��
				addRandomApple();
				// �÷ּ�һ
				mScore++;
				// �ӳ�����ǰ��90%
				mMoveDelay *= 0.9;
				// ��������־��Ϊ��
				growSnake = true;
			}
		}

		// push a new head onto the ArrayList and pull off the tail
		// ����ͷ��λ������һ��������
		mSnakeTrail.add(0, newHead);
		// except if we want the snake to grow
		// ���û������
		if (!growSnake) {
			// �����ͷû������ɾȥ���һ�����꣬�൱������ǰ����һ��
			mSnakeTrail.remove(mSnakeTrail.size() - 1);
		}

		int index = 0;
		// ��������һ����ɫ����ͷ�ǻ�ɫ�ģ�ͬƻ��һ�����������Ǻ�ɫ��
		for (Coordinate c : mSnakeTrail) {
			if (index == 0) {
				setTile(YELLOW_STAR, c.x, c.y);
			} else {
				setTile(RED_STAR, c.x, c.y);
			}
			index++;
		}

	}

	/**
	 * Simple class containing two integer values and a comparison function.
	 * There's probably something I should use instead, but this was quick and
	 * easy to build.
	 * 
	 */
	// �����ڲ��ࡪ��ԭ����˵������ʱ����
	private class Coordinate {
		public int x;
		public int y;

		// ���캯��
		public Coordinate(int newX, int newY) {
			x = newX;
			y = newY;
		}

		// ��дequals
		public boolean equals(Coordinate other) {
			if (x == other.x && y == other.y) {
				return true;
			}
			return false;
		}

		// ��дtoString
		@Override
		public String toString() {
			return "Coordinate: [" + x + "," + y + "]";
		}
	}

}
