package com.deaboway.snake;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

public class TileView extends View {
	/**
	 * Parameters controlling the size of the tiles and their range within view.
	 * Width/Height are in pixels, and Drawables will be scaled to fit to these
	 * dimensions. X/Y Tile Counts are the number of tiles that will be drawn.
	 */

	protected static int mTileSize;

	// X�����Ƭ����
	protected static int mXTileCount;
	// Y�����Ƭ����
	protected static int mYTileCount;

	// Xƫ����
	private static int mXOffset;
	// Yƫ����
	private static int mYOffset;

	/**
	 * A hash that maps integer handles specified by the subclasser to the
	 * drawable that will be used for that reference
	 */
	// ��Ƭͼ���ͼ������
	private Bitmap[] mTileArray;

	/**
	 * A two-dimensional array of integers in which the number represents the
	 * index of the tile that should be drawn at that locations
	 */
	// ����ÿ����Ƭ������������ά����
	private int[][] mTileGrid;

	// Paint���󣨻��ʡ����ϣ�
	private final Paint mPaint = new Paint();

	// ���캯��
	public TileView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

		TypedArray a = context.obtainStyledAttributes(attrs,
				R.styleable.TileView);

		mTileSize = a.getInt(R.styleable.TileView_tileSize, 12);

		a.recycle();
	}

	public TileView(Context context, AttributeSet attrs) {
		super(context, attrs);

		TypedArray a = context.obtainStyledAttributes(attrs,
				R.styleable.TileView);

		mTileSize = a.getInt(R.styleable.TileView_tileSize, 12);

		a.recycle();
	}

	/**
	 * Rests the internal array of Bitmaps used for drawing tiles, and sets the
	 * maximum index of tiles to be inserted
	 * 
	 * @param tilecount
	 */
	// ������ƬͼƬ����
	public void resetTiles(int tilecount) {
		mTileArray = new Bitmap[tilecount];
	}

	// �ص�������View�ĳߴ�ı�ʱ���ã���onDraw()��������֮ǰ�ͻᱻ���ã�������������һЩ�����ĳ�ʼֵ
	// ����ͼ��С�ı��ʱ����ã�����˵�ֻ��ɴ�ֱ��תΪˮƽ
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {

		// ����X����Ƭ����
		mXTileCount = (int) Math.floor(w / mTileSize);
		mYTileCount = (int) Math.floor(h / mTileSize);

		// X��ƫ����
		mXOffset = ((w - (mTileSize * mXTileCount)) / 2);

		// Y��ƫ����
		mYOffset = ((h - (mTileSize * mYTileCount)) / 2);

		// ������Ƭ�Ķ�ά����
		mTileGrid = new int[mXTileCount][mYTileCount];

		// ���������Ƭ
		clearTiles();
	}

	/**
	 * Function to set the specified Drawable as the tile for a particular
	 * integer key.
	 * 
	 * @param key
	 * @param tile
	 */
	// ��mTileArray���BitmapͼƬ��������ֵ
	public void loadTile(int key, Drawable tile) {
		Bitmap bitmap = Bitmap.createBitmap(mTileSize, mTileSize,
				Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap);
		tile.setBounds(0, 0, mTileSize, mTileSize);
		// ��һ��drawableת��һ��Bitmap
		tile.draw(canvas);
		// ������������Bitmap
		mTileArray[key] = bitmap;
	}

	/**
	 * Resets all tiles to 0 (empty)
	 * 
	 */
	// ���������Ƭ
	public void clearTiles() {
		for (int x = 0; x < mXTileCount; x++) {
			for (int y = 0; y < mYTileCount; y++) {
				// ȫ������Ϊ0
				setTile(0, x, y);
			}
		}
	}

	/**
	 * Used to indicate that a particular tile (set with loadTile and referenced
	 * by an integer) should be drawn at the given x/y coordinates during the
	 * next invalidate/draw cycle.
	 * 
	 * @param tileindex
	 * @param x
	 * @param y
	 */
	// ��ĳ����Ƭλ������һ��״̬����
	public void setTile(int tileindex, int x, int y) {
		mTileGrid[x][y] = tileindex;
	}

	// onDraw ����ͼ��Ҫ�ػ���ʱ����ã�����˵ʹ��invalidateˢ�½����ϵ�ĳ����������
	@Override
	public void onDraw(Canvas canvas) {

		super.onDraw(canvas);
		for (int x = 0; x < mXTileCount; x += 1) {
			for (int y = 0; y < mYTileCount; y += 1) {
				// �����������㣬Ҳ���ǲ���ʱ
				if (mTileGrid[x][y] > 0) {
					// mTileGrid�в�Ϊ��ʱ������Ƭ
					canvas.drawBitmap(mTileArray[mTileGrid[x][y]], mXOffset + x
							* mTileSize, mYOffset + y * mTileSize, mPaint);
				}
			}
		}

	}
}