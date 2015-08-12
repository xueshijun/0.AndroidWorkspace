package com.fit.demo.test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.fit.dao.XmlParser;
import com.fit.entity.Section;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.widget.RadioButton;
import android.widget.TextView;

public class Activity1 extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main1);
		
		
		TextView view=(TextView) findViewById(R.id.view);
		String name=getIntent().getExtras().getString("name").toString();
		view.setText("»¶Ó­Äú£¬"+name);
		
		RadioButton A=(RadioButton) findViewById(R.id.A);
		RadioButton B=(RadioButton) findViewById(R.id.B);
		RadioButton C=(RadioButton) findViewById(R.id.C);
		File file=new File(Environment.getExternalStorageDirectory(),"testSex.xml");
		TextView view3=(TextView) findViewById(R.id.view3);
		List<Section> list=XmlParser.xmlParser(file);
		
		ArrayList<HashMap<String,String>> list1=new ArrayList<HashMap<String,String>>();
		System.out.println("-------------------"+list.get(0).getTitle().toString());
		for (Section section : list) {
			//view3.append("\r\n"+section.toString());
			view3.setText(section.getTitle().toString());
			A.setText(section.getA().toString());
			B.setText(section.getB().toString());
			C.setText(section.getC().toString());
		}
	}
}
