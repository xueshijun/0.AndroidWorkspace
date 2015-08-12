package com.fit.testsex;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class MyAdapter extends BaseAdapter {
	private Context context;
	private ArrayList<HashMap<String, String>> list;

	public MyAdapter(Context context, ArrayList<HashMap<String, String>> list) {
		super();
		this.context = context;
		this.list = list;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LinearLayout view = null;

		if (convertView == null) {
			view = new LinearLayout(context);
			LayoutInflater inflater = LayoutInflater.from(context);
			inflater.inflate(R.layout.list, view);

			HashMap<String, String> question = list.get(position);
			
			TextView title = (TextView) view.findViewById(R.id.text);
			title.setText(question.get("title"));
			
			RadioButton btnA = (RadioButton) view.findViewById(R.id.A);
			btnA.setText(question.get("A"));
			
			RadioButton btnB = (RadioButton) view.findViewById(R.id.B);
			btnB.setText(question.get("B"));
			
			RadioButton btnC = (RadioButton) view.findViewById(R.id.C);
			btnC.setText(question.get("C"));

			RadioGroup group = (RadioGroup) view.findViewById(R.id.group);
			group.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(RadioGroup group, int checkedId) {
					switch (checkedId) {
					case R.id.A:
						Toast.makeText(
								context,
								"您选择了"
										+ ((RadioButton) group
												.findViewById(R.id.A))
												.getText().toString(),
								Toast.LENGTH_LONG).show();
						break;
					case R.id.B:
						Toast.makeText(
								context,
								"您选择了"
										+ ((RadioButton) group
												.findViewById(R.id.B))
												.getText().toString(),
								Toast.LENGTH_LONG).show();
						break;
					case R.id.C:
						Toast.makeText(
								context,
								"您选择了"
										+ ((RadioButton) group
												.findViewById(R.id.C))
												.getText().toString(),
								Toast.LENGTH_LONG).show();
						break;
					}
				}
			});
		} else {
			view = (LinearLayout) convertView;
		}

		return view;
	}

	public int getScore() {
		return 100;
	}
}
