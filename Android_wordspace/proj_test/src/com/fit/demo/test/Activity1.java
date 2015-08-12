package com.fit.demo.test;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Activity1 extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main1);
		
		TextView view=(TextView) findViewById(R.id.view);
		
		String name=getIntent().getExtras().getString("name").toString();
		
		view.setText("»¶Ó­Äú£¬"+name);
	}
}
