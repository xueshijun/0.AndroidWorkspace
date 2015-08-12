package com.fit.testsex;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.ListActivity;
import android.os.Bundle;
import android.os.Environment;

import com.fit.dao.XmlParser;
import com.fit.entity.Problems;

public class MainActivity extends ListActivity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// TextView view=(TextView) findViewById(R.id.view);
		File file = new File(Environment.getExternalStorageDirectory(),
				"text.xml");
		// List<Problems> booklist=XmlParser.xmlParser(file);
		//        
		// for (Problems pb : booklist) {
		// view.append("\r\n"+pb.toString());
		//			
		// }

		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		List<Problems> problems = XmlParser.xmlParser(file);

		for (Problems pbl : problems) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("title", pbl.getTitle());
			map.put("A", pbl.getA());
			map.put("B", pbl.getB());
			map.put("C", pbl.getC());

			list.add(map);
		}

		MyAdapter adapter = new MyAdapter(MainActivity.this, list);
		setListAdapter(adapter);
		

		// SimpleAdapter adapter = new SimpleAdapter(MainActivity.this, list,
		// R.layout.list, new String[] { "title", "A", "B", "C" },
		// new int[] { R.id.text, R.id.A, R.id.B, R.id.C });
		// setListAdapter(adapter);
		//
		// ListView lv = getListView();
		// lv.setOnItemClickListener(new OnItemClickListener() {
		//
		// @Override
		// public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
		// long arg3) {
		// RadioGroup group = (RadioGroup) arg1.findViewById(R.id.group);
		// final RadioButton radio1 = (RadioButton) arg1
		// .findViewById(R.id.A);
		// final RadioButton radio2 = (RadioButton) arg1
		// .findViewById(R.id.B);
		// final RadioButton radio3 = (RadioButton) arg1
		// .findViewById(R.id.C);
		// group.setOnCheckedChangeListener(new OnCheckedChangeListener() {
		//
		// @Override
		// public void onCheckedChanged(RadioGroup group, int checkedId) {
		// if (checkedId == radio1.getId()) {
		// Toast.makeText(MainActivity.this,
		// "您选择了" + radio1.getText().toString(),
		// Toast.LENGTH_LONG).show();
		// }
		// if (checkedId == radio2.getId()) {
		// Toast.makeText(MainActivity.this,
		// "您选择了" + radio2.getText().toString(),
		// Toast.LENGTH_LONG).show();
		// }
		// if (checkedId == radio3.getId()) {
		// Toast.makeText(MainActivity.this,
		// "您选择了" + radio3.getText().toString(),
		// Toast.LENGTH_LONG).show();
		// }
		// }
		// });
		// }
		// });
	}
}