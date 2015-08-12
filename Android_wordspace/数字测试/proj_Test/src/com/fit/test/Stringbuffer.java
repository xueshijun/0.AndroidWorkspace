package com.fit.test;


public class Stringbuffer {

		StringBuffer buffer=new StringBuffer();
		
		String m="aa";
		public String buffer(){
			buffer.append(m);
			buffer.append("bb");
			buffer.append("cc");
			return buffer.reverse().toString();
		}
		
	public static void main(String[] args) {

		Stringbuffer sb=new Stringbuffer();
		System.out.println(sb.buffer());
		
	}

}
