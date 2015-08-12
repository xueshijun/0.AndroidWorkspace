package com.fit.android.game;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Node {
	private int x;
	private int y;

	public Node(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	public void draw(Canvas c, Paint p) {
		c.drawCircle(x, y, 5, p);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
}
