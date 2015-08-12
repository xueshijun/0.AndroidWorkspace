package com.fit.entity;

public class Question {

	private String title;
	private String a;
	private String b;
	private String c;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getA() {
		return a;
	}
	public void setA(String a) {
		this.a = a;
	}
	public String getB() {
		return b;
	}
	public void setB(String b) {
		this.b = b;
	}
	public String getC() {
		return c;
	}
	public void setC(String c) {
		this.c = c;
	}
	@Override
	public String toString() {
		return "Question [a=" + a + ", b=" + b + ", c=" + c + ", title="
				+ title + "]";
	}

	
}
