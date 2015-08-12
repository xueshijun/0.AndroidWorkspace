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

import com.fit.entity.Section;

public class XmlParser {

	
	 public static  List<Section> xmlParser(File file){
		
		List<Section> list=new ArrayList<Section>();
		
		
		XmlPullParserFactory factory=null;
		try {
			factory=XmlPullParserFactory.newInstance();
			factory.setNamespaceAware(true);
			XmlPullParser parser=factory.newPullParser();
			parser.setInput(new FileReader(file));
			int eventType=parser.getEventType();
			
			Section section=null;
			boolean isTitle=false;
			boolean isA=false;
			boolean isB=false;
			boolean isC=false;
			while(eventType!=XmlPullParser.END_DOCUMENT){
				//判断是否为标签
				if(eventType==XmlPullParser.START_TAG){
					String tagName=parser.getName();
					if("question".equals(tagName)){
						section=new Section();
						list.add(section);
					}else if("title".equals(tagName)){
						isTitle=true;
					}else if("A".equals(tagName)){
						isA=true;
					}else if("B".equals(tagName)){
						isB=true;
					}else if("C".equals(tagName)){
						isC=true;
					}
					
					//判断是否为文本节点
				}else if(eventType==XmlPullParser.TEXT){
					if(isTitle){
						section.setTitle(parser.getText());
						isTitle=false;
					}else if(isA){
						section.setA(parser.getText());
						isA=false;
					}else if(isB){
						section.setB(parser.getText());
						isB=false;
					}else if(isC){
						section.setC(parser.getText());
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
