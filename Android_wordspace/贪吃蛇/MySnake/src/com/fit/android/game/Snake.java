package com.fit.android.game;

import java.util.LinkedList;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Snake {
	public static final int UP = 1;
	public static final int RIGHT = 2;
	public static final int DOWN = 3;
	public static final int LEFT = 4;

	public static final int MOVE = 1;
	public static final int EAT = 2;
	public static final int BREAK = 3;
	public static final int SWAP = 4;

	private LinkedList<Node> self = new LinkedList<Node>();
	private int direct = RIGHT;
	private int widthX;
	private int widthY;

	public Snake(int wx, int wy) {
		self.add(new Node(25, 5));
		self.add(new Node(15, 5));
		self.add(new Node(5, 5));
		widthX = wx;
		widthY = wy;
	}
	public void changeDirect(int d) {
		switch (direct) {
		case UP:
			if (d == DOWN)
				return;
			break;
		case RIGHT:
			if (d == LEFT)
				return;
			break;
		case DOWN:
			if (d == UP)
				return;
			break;
		case LEFT:
			if (d == RIGHT)
				return;
			break;
		}
		direct = d;
	}


	public int move(Node bean) {
		Node head = null;
		Node first = self.get(0);
		switch (direct) {
		case UP:
			head = new Node(first.getX(), first.getY() - 10);
			break;
		case RIGHT:
			head = new Node(first.getX() + 10, first.getY());
			break;
		case DOWN:
			head = new Node(first.getX(), first.getY() + 10);
			break;
		case LEFT:
			head = new Node(first.getX() - 10, first.getY());
			break;
		}
		if (head.getX() < 0 || head.getX() > widthX || head.getY() < 0
				|| head.getY() > widthY) {
			return BREAK;
		}
		for (int i = 0; i < self.size(); i++) {
			if (head.equals(self.get(i))) {
				return SWAP;
			}
		}
		self.add(0, head);

		if (head.equals(bean)) {
			return EAT;
		}
		self.remove(self.size() - 1);
		return MOVE;
	}
	public void show(Canvas c) {
		Paint paint = new Paint();
		paint.setAntiAlias(true);
		
		
		paint.setColor(Color.RED);
		self.get(0).draw(c, paint);

		paint.setColor(Color.YELLOW);
		for (int i = 1; i < self.size(); i++) {
			self.get(i).draw(c, paint);
		}
	}

	public boolean checkRandom(int x, int y) {
		for (int i = 0; i < self.size(); i++) {
			if (self.get(i).getX() == x && self.get(i).getY() == y) {
				return false;
			}
		}
		return true;
	}
}
