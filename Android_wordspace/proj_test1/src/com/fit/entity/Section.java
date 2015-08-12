package com.fit.entity;

public class Section {

	private String title;
	private String A;
	private String B;
	private String C;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getA() {
		return A;
	}
	public void setA(String a) {
		A = a;
	}
	public String getB() {
		return B;
	}
	public void setB(String b) {
		B = b;
	}
	public String getC() {
		return C;
	}
	public void setC(String c) {
		C = c;
	}
	@Override
	public String toString() {
		return "Section [A=" + A + ", B=" + B + ", C=" + C + ", title=" + title
				+ "]";
	}
	
	
}
