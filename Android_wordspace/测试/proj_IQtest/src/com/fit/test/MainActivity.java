package com.fit.test;

import java.io.File;
import java.util.List;

import com.fit.entity.Question;
import com.fit.tool.XmlPullParse;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class MainActivity extends Activity {
	private int i=-1;
	private TextView title;
	private RadioButton btn_a;
	private RadioButton btn_b;
	private RadioButton btn_c;
	private List<Question> questions;
	private int countA;
	private int countB;
	private int countC;
	private int score;
	private RadioGroup group;
	Button btn;
	Button pre;
	File file;
	char[] COUNT;
	boolean flag ;
	int questCount;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        btn = (Button) findViewById(R.id.sure);
		btn_a = (RadioButton) findViewById(R.id.RadioButton01);
		btn_b = (RadioButton) findViewById(R.id.RadioButton02);
		btn_c = (RadioButton) findViewById(R.id.RadioButton03);
		title = (TextView) findViewById(R.id.title);
		group = (RadioGroup) findViewById(R.id.RadioGroup01);
		file = new File(Environment.getExternalStorageDirectory(), "text.xml");
	
		questions = new XmlPullParse().XmlBook(file);
		questCount = questions.size();
  
		COUNT=new char [questCount];
		pre = (Button) findViewById(R.id.shang);
		flag = true;
		final TextView select = (TextView) findViewById(R.id.daan);
		final TextView scoreText = (TextView) findViewById(R.id.fenshu);
		nextQuestion();
		
		btn.setOnClickListener(new OnClickListener() {
			String show ="";
			@Override
			public void onClick(View v) {
				if (i < questCount-1) {
					nextQuestion();
				} else if(flag) {
					for (int j = 0; j < COUNT.length; j++) {
						
						if(COUNT[j]=='a'){
							show+=COUNT[j];
							countA++;
						}else if(COUNT[j]=='b'){
							show+=COUNT[j];
							countB++;
						}
						else if(COUNT[j]=='c'){
							show+=COUNT[j];
							countC++;
						}
					}
					score = 1 * countA + 2 * countB + 3 * countC;
					select.setText("您选的答案是："+show);
					scoreText.setText("总分为："+score + "分");
					flag = false;
				}
			}
		});
		
		pre.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(i > 0 && i < questCount-1){
					previousQuestion();
				} else {
					pre.setClickable(false);
				}
			}
		});
	}

	public void nextQuestion() {
		i++;
		title.setText(questions.get(i).getTitle());
		btn_a.setText(questions.get(i).getA());
		btn_b.setText(questions.get(i).getB());
		btn_c.setText(questions.get(i).getC());
		
		group.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if (btn_a.isChecked()) {
					COUNT[i]='a';
				} else if (btn_b.isChecked()) {
					COUNT[i]='b';
				} else if (btn_c.isChecked()){
					COUNT[i]='c';
				}
			}
		});
		
	
		group.clearCheck();
		
		
	}

	public void previousQuestion() {
		i--;
		title.setText(questions.get(i).getTitle());
		btn_a.setText(questions.get(i).getA());
		btn_b.setText(questions.get(i).getB());
		btn_c.setText(questions.get(i).getC());
		group.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if (btn_a.isChecked()) {
					COUNT[i] = 'a';
				} else if (btn_b.isChecked()) {
					COUNT[i] = 'b';
				} else if (btn_c.isChecked()) {
					COUNT[i] = 'c';
				}
				
			}
		});
		
		group.clearCheck();
	}

}