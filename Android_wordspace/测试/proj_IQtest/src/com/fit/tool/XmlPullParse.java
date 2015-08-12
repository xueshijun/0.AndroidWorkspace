package com.fit.tool;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import com.fit.entity.Question;


public class XmlPullParse {
	public List<Question> XmlBook(File file){
		List<Question> books=new LinkedList<Question>();
		XmlPullParserFactory  factory;
		boolean title=false;
		boolean a = false;
		boolean b = false;
		boolean c =  false;
		Question current = null;
		try {
			factory=XmlPullParserFactory.newInstance();
			//ÏÂÈ¥²é
			factory.setNamespaceAware(false);
			XmlPullParser parser = factory.newPullParser();
			parser.setInput(new FileReader(file));
			int eventType = parser.getEventType();
			while(eventType!= XmlPullParser.END_DOCUMENT){
				if(eventType == XmlPullParser.START_TAG){
					String nameTag = parser.getName();
					if(nameTag.equals("problem")){
						current=new Question();
						books.add(current);
					}else if(nameTag.equals("title")){
						title=true;
					}else if(nameTag.equals("a")){
						a=true;
					}else if(nameTag.equals("b")){
						b=true;
					}
					else if(nameTag.equals("c")){
						c=true;
					}
					
				}else if(eventType == XmlPullParser.TEXT){
					if(title){
						current.setTitle(parser.getText());
						title=false;
					}else if(a){
						current.setA(parser.getText());
						a=false;
					}else if(b){
						current.setB(parser.getText().toString());
						b=false;
					}
					else if(c){
						current.setC(parser.getText().toString());
						c=false;
					}
				}
				eventType=parser.next();
			}
			
			
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return books;
	}

}
