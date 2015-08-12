package com.fit.count.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class CountMain extends Activity {
	/** Called when the activity is first created. */
	static StringBuffer strBuff = new StringBuffer("");

	// ���ּ���
	public void xianshi(final Button but, final EditText t1) {
		but.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				//�жϿ�ʼ�Ƿ���԰�������
				if (but.getText().toString().equals(".")) {
					if (strBuff.indexOf("+") != -1
							|| strBuff.indexOf("-") != -1
							|| strBuff.indexOf("*") != -1
							|| strBuff.indexOf("/") != -1) {
						strBuff.append(but.getText().toString());
						t1.setText(strBuff);
						//�ж�һ�����������Ƿ���԰������
					} else {
						if (strBuff.indexOf(".") == -1 && strBuff.length() != 0) {
							strBuff.append(but.getText().toString());
							t1.setText(strBuff);
						}
					}
                     //�ж���ʲô����¿��԰������
				} else if (but.getText().toString().equals("0")) {
					if (strBuff.indexOf("0") == 0 && strBuff.indexOf(".") == 1) {
						strBuff.append(but.getText().toString());
						t1.setText(strBuff);
					} else if (strBuff.length() == 0) {
						strBuff.append(but.getText().toString());
						t1.setText(strBuff);
					}
                   //�жϵ�һ�����㣬�����ٰ����������ʱ����Ļ����ʾ
				} else if (strBuff.indexOf("0") == 0
						&& strBuff.indexOf(".") == -1) {
					strBuff.delete(0, strBuff.length());
					strBuff.append(but.getText().toString());
					t1.setText(strBuff);
				} else {
					strBuff.append(but.getText().toString());
					
					t1.setText(strBuff);
				}

			}
		});
	}

	// ���ż���
	public void jisuan(final Button but1, final EditText t1) {
		but1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				//���������ַ�
				String newchar1 = but1.getText().toString();
				//�жϿ�ʼ�Ƿ�Ϊ�Ⱥţ�����һ�����ֺ�������Ⱥ�û�з�ӳ
				String newchar = "";
				if (!(newchar1.equals("="))) {
					newchar = newchar1;
				}
				
				float per1 = 0;//������Ž�ȡ�ַ����ĵ�һ��������
				float per2 = 0;//������Ž�ȡ�ַ����ĵڶ���������
				//�жϰ����ַ��Ƿ�Ϊ�����
				if (newchar.equalsIgnoreCase("c")) {
					strBuff.delete(0, strBuff.length());
					t1.setText("0");
					return;
				}
				//�жϰ��Ĳ�������ʲô�����ַ�
				//�ж��Ƿ�Ϊ�ӷ�
				if (strBuff.indexOf("+") != -1
						&& strBuff.indexOf("+") < strBuff.length() - 1
						&& strBuff.indexOf("+") != 0) {
					int i = strBuff.indexOf("+");
					per1 = Float.parseFloat(strBuff.substring(0, i));
					per2 = Float.parseFloat(strBuff.substring(i + 1, strBuff
							.length()));
					float sum = per1 + per2;
					strBuff.delete(0, strBuff.length());
					strBuff.append(sum);
					strBuff.append(newchar);
					t1.setText(strBuff);
					//�ж��Ƿ�Ϊ����
				} else if (strBuff.indexOf("-") != -1
						&& strBuff.indexOf("-") < strBuff.length() - 1
						&& strBuff.indexOf("-") != 0) {
					int i = strBuff.indexOf("-");
					per1 = Float.parseFloat(strBuff.substring(0, i));
					per2 = Float.parseFloat(strBuff.substring(i + 1, strBuff
							.length()));
					float sum = per1 - per2;
					strBuff.delete(0, strBuff.length());
					strBuff.append(sum);
					strBuff.append(newchar);
					t1.setText(strBuff);
					//�ж��Ƿ�Ϊ�˷�
				} else if (strBuff.indexOf("*") != -1
						&& strBuff.indexOf("*") < strBuff.length() - 1
						&& strBuff.indexOf("*") != 0) {
					int i = strBuff.indexOf("*");
					per1 = Float.parseFloat(strBuff.substring(0, i));
					per2 = Float.parseFloat(strBuff.substring(i + 1, strBuff
							.length()));
					float sum = per1 * per2;
					strBuff.delete(0, strBuff.length());
					strBuff.append(sum);
					strBuff.append(newchar);
					t1.setText(strBuff);
					//�ж��Ƿ�Ϊ����
				} else if (strBuff.indexOf("/") != -1
						&& strBuff.indexOf("/") < strBuff.length() - 1
						&& strBuff.indexOf("/") != 0) {
					int i = strBuff.indexOf("/");
					per1 = Float.parseFloat(strBuff.substring(0, i));
					per2 = Float.parseFloat(strBuff.substring(i + 1, strBuff
							.length()));
					//�жϵڶ����������Ƿ�Ϊ�㣬Ϊ�������ʾ������
					if (per2 != 0) {
						float sum = per1 / per2;
						strBuff.delete(0, strBuff.length());
						strBuff.append(sum);
						strBuff.append(newchar);
						t1.setText(strBuff);
					} else {
						strBuff.delete(0, strBuff.length());
						strBuff.append("0");
						t1.setText(strBuff);
					}
				} else {
					//�жϲ������Ƿ��ܽ������ϵĵ��
					if (strBuff.length() > 0
							&& ('0' <= strBuff.charAt(strBuff.length() - 1))
							&& ('9' >= strBuff.charAt(strBuff.length() - 1))) {
						strBuff.append(newchar);
						t1.setText(strBuff);
					}
				}

			}
		});

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		final EditText t1 = (EditText) findViewById(R.id.txt_input);
		final Button but1 = (Button) findViewById(R.id.but1);
		xianshi(but1, t1);
		final Button but2 = (Button) findViewById(R.id.but2);
		xianshi(but2, t1);
		final Button but3 = (Button) findViewById(R.id.but3);
		xianshi(but3, t1);
		final Button but5 = (Button) findViewById(R.id.but5);
		xianshi(but5, t1);
		final Button but6 = (Button) findViewById(R.id.but6);
		xianshi(but6, t1);
		final Button but7 = (Button) findViewById(R.id.but7);
		xianshi(but7, t1);
		final Button but9 = (Button) findViewById(R.id.but9);
		xianshi(but9, t1);
		final Button but10 = (Button) findViewById(R.id.but10);
		xianshi(but10, t1);
		final Button but11 = (Button) findViewById(R.id.but11);
		xianshi(but11, t1);
		final Button but13 = (Button) findViewById(R.id.but13);
		xianshi(but13, t1);
		final Button but17 = (Button) findViewById(R.id.but17);
		xianshi(but17, t1);

		// ����
		final Button but4 = (Button) findViewById(R.id.but4);
		jisuan(but4, t1);
		final Button but8 = (Button) findViewById(R.id.but8);
		jisuan(but8, t1);
		final Button but12 = (Button) findViewById(R.id.but12);
		jisuan(but12, t1);
		final Button but14 = (Button) findViewById(R.id.but14);
		jisuan(but14, t1);
		final Button but15 = (Button) findViewById(R.id.but15);
		jisuan(but15, t1);
		final Button but16 = (Button) findViewById(R.id.but16);
		jisuan(but16, t1);

	}
}