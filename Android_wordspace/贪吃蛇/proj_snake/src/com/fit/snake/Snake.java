package com.fit.snake;

import java.util.LinkedList;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Snake {
	private int maxX;
	private int maxY;

	public static final int UP = 1;
	public static final int RIGHT = 2;
	public static final int DOWN = 3;
	public static final int LEFT = 4;

	public static final int MOVE = 1;
	public static final int EAT = 2;
	public static final int BREAK = 3;
	public static final int SWAP = 4;
	// 初始化蛇的方向
	private int direct = RIGHT;

	private LinkedList<Node> self = new LinkedList<Node>();

	public Snake(int mx, int my) {
		self.add(new Node(25, 5));
		self.add(new Node(15, 5));
		self.add(new Node(5, 5));

		maxX = mx;
		maxY = my;
	}


	// 改变蛇的方向
	public void changeDirect(int dr) {
		switch (direct) {
		case UP:
			if (dr == DOWN) 
				return;
			break;
		case RIGHT:
			if (dr == LEFT) 
				return;
			break;
		case DOWN:
			if (dr == UP)
				return;
			break;
		case LEFT:
			if (dr == RIGHT) 
				return;
			break;

		}
		direct = dr;
	}

	// 蛇移动坐标的改变
	public int move(Node bean) {
		Node head = null;
		Node first = self.get(0);
		switch (direct) {
		case UP:
			head = new Node(first.getX(), first.getY() -10);
			break;
		case RIGHT:
			head = new Node(first.getX() + 10, first.getY());
			break;
		case DOWN:
			head = new Node(first.getX(), first.getY() +10);
			break;
		case LEFT:
			head = new Node(first.getX() - 10, first.getY());
			break;
		}
		// 判断是否超出了边界
		if (head.getX() < 0 || head.getX() > maxX || head.getY() < 0
				|| head.getY() > maxY) {
			return BREAK;
		}
		// 判断蛇是否咬到了自己
		for (int i = 0; i < self.size(); i++) {
			if (head.equals(self.get(i))) {
				return SWAP;
			}

		}
		// 移动时头部增加一节
		self.add(0, head);
		if (head.equals(bean)) {
			return EAT;
		}
		// 移动时尾部删除一节
		self.remove(self.size() - 1);
		return MOVE;
	}
	

	public void drawSnake(Canvas c) {
		Paint paint = new Paint();
		paint.setAntiAlias(true);
		// 蛇头
		paint.setColor(Color.RED);
		self.get(0).drawNode(c, paint);
		// 蛇的身子
		paint.setColor(Color.YELLOW);
		for (int i = 1; i < self.size(); i++) {
			self.get(i).drawNode(c, paint);
		}
	}

	// 出现随机点，并判断随机点是否合法
	public boolean checkRandom(int x, int y) {
		for (int i = 0; i < self.size(); i++) {
			if (self.get(i).getX() == x && self.get(i).getY() == y) {
				return false;
			}
		}
		return true;
	}
}
