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

import com.fit.entity.Problems;

public class XmlParser {

	
	public static List<Problems> xmlParser(File file){
		List<Problems> list=new ArrayList<Problems>();
		
		XmlPullParserFactory factory;
		try {
			factory=XmlPullParserFactory.newInstance();
			factory.setNamespaceAware(true);
			XmlPullParser parser=factory.newPullParser();
			parser.setInput(new FileReader(file));
			
			int eventType=parser.getEventType();
			Problems pb=null;
			
			boolean isTitle=false;
			boolean isA=false;
			boolean isB=false;
			boolean isC=false;
			
			while(eventType!=XmlPullParser.END_DOCUMENT){
				//当遇到标签的时候
				if(eventType==XmlPullParser.START_TAG){
					String tagName=parser.getName();
					if("problem".equals(tagName)){
						pb=new Problems();
						list.add(pb);
					}else if("title".equals(tagName)){
						isTitle=true;
						
					}else if("a".equals(tagName)){
						isA=true;
					}else if("b".equals(tagName)){
						isB=true;
					}else if("c".equals(tagName)){
						isC=true;
					}
				}//当遇到标签文本的时候
				else if(eventType==XmlPullParser.TEXT){
					if(isTitle){
						pb.setTitle(parser.getText());
						isTitle=false;
					}else if(isA){
						pb.setA(parser.getText());
						isA=false;
					}else if(isB){
						pb.setB(parser.getText());
						isB=false;
					}else if(isC){
						pb.setC(parser.getText());
						isC=false;
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
	
	
	
	return list;
}

}
