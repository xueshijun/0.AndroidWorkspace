package com.fit.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import com.fit.entity.Book;

public class XmlTool {
		
	
	public static List<Book> xmlTool(File file){
		List<Book> booklist=new ArrayList<Book>();
		
			XmlPullParserFactory factory;
			try {
				factory=XmlPullParserFactory.newInstance();
				factory.setNamespaceAware(true);
				XmlPullParser parser=factory.newPullParser();
				parser.setInput(new FileReader(file));
				
				int eventType=parser.getEventType();
				Book book=null;
				
				boolean isTitle=false;
				boolean isAuthor=false;
				boolean isPrice=false;
				
				while(eventType!=XmlPullParser.END_DOCUMENT){
					//当遇到标签的时候
					if(eventType==XmlPullParser.START_TAG){
						String tagName=parser.getName();
						if("book".equals(tagName)){
							book=new Book();
							booklist.add(book);
						}else if("title".equals(tagName)){
							isTitle=true;
							
						}else if("author".equals(tagName)){
							isAuthor=true;
						}else if("price".equals(tagName)){
							isPrice=true;
						}
					}//当遇到标签文本的时候
					else if(eventType==XmlPullParser.TEXT){
						if(isTitle){
							book.setTitle(parser.getText());
							isTitle=false;
						}else if(isAuthor){
							book.setAuthor(parser.getText());
							isAuthor=false;
						}else if(isPrice){
							book.setPrice(Double.parseDouble(parser.getText()));
							isPrice=false;
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
		
		
		
		return booklist;
	}
	
}
