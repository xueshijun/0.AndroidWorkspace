package com.fit.test;

import java.util.Scanner;

public class Test {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Test s = new Test();
		s.suanfa();

	}
	public void suanfa() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("ÇëÊäÈë£º");
		int m = scanner.nextInt();
		int a=1;
		int b=1;
		int c = 1;
		int i=3;
		while(i>2 && i<=m){
			c=a+b;
			a=b;
			b=c;
			i++;
		}
		System.out.println(c);
	}

}
