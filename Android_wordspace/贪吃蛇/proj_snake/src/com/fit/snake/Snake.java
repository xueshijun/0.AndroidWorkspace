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
	// ��ʼ���ߵķ���
	private int direct = RIGHT;

	private LinkedList<Node> self = new LinkedList<Node>();

	public Snake(int mx, int my) {
		self.add(new Node(25, 5));
		self.add(new Node(15, 5));
		self.add(new Node(5, 5));

		maxX = mx;
		maxY = my;
	}


	// �ı��ߵķ���
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

	// ���ƶ�����ĸı�
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
		// �ж��Ƿ񳬳��˱߽�
		if (head.getX() < 0 || head.getX() > maxX || head.getY() < 0
				|| head.getY() > maxY) {
			return BREAK;
		}
		// �ж����Ƿ�ҧ�����Լ�
		for (int i = 0; i < self.size(); i++) {
			if (head.equals(self.get(i))) {
				return SWAP;
			}

		}
		// �ƶ�ʱͷ������һ��
		self.add(0, head);
		if (head.equals(bean)) {
			return EAT;
		}
		// �ƶ�ʱβ��ɾ��һ��
		self.remove(self.size() - 1);
		return MOVE;
	}
	

	public void drawSnake(Canvas c) {
		Paint paint = new Paint();
		paint.setAntiAlias(true);
		// ��ͷ
		paint.setColor(Color.RED);
		self.get(0).drawNode(c, paint);
		// �ߵ�����
		paint.setColor(Color.YELLOW);
		for (int i = 1; i < self.size(); i++) {
			self.get(i).drawNode(c, paint);
		}
	}

	// ��������㣬���ж�������Ƿ�Ϸ�
	public boolean checkRandom(int x, int y) {
		for (int i = 0; i < self.size(); i++) {
			if (self.get(i).getX() == x && self.get(i).getY() == y) {
				return false;
			}
		}
		return true;
	}
}
