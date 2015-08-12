package com.fit.file;


import java.io.FileNotFoundException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;



import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        
        Button save=(Button) findViewById(R.id.save);
        save.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				saveFile();
				Toast.makeText(MainActivity.this,"��Ϣ����ɹ�����",Toast.LENGTH_LONG).show();
			}
		});
        
        Button show=(Button) findViewById(R.id.show);
        show.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				show();
			}
		});
    }
    //��������Ϣ���ļ�
    public void saveFile(){
    	OutputStreamWriter out=null;
    	//PrintWriter out=null;
    	//���ļ�userinfo.txt���浽sdcard��PICTURES��Ŀ¼��
    	//File file=new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES),"userInfo.txt");
    	
    	//���ļ����浽sdcard�ĸ�Ŀ¼��
    	File file = new File(Environment.getExternalStorageDirectory(),"content.dat");
    	try {
			//out=new OutputStreamWriter(MainActivity.this.openFileOutput("userInfo.txt",MODE_PRIVATE));
    		
    		out=new OutputStreamWriter(new FileOutputStream(file));
    		//out=new PrintWriter(new FileOutputStream(file));
    		
			EditText text=(EditText) findViewById(R.id.text);
		
			try {
				out.write(text.getText().toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}finally{
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			out=null;
		}
    }
    //�õ��ļ���Ϣ����ʾ
    public void show(){
    	BufferedReader reader=null;
    	StringBuffer buffer=new StringBuffer();
    	EditText text=(EditText) findViewById(R.id.text);
    	try {
			reader= new BufferedReader(new InputStreamReader(openFileInput("userInfo.txt")));
			String temp=null;
	    	while((temp=reader.readLine())!=null){
	    		buffer.append(temp+"\r\n");
	    		
	    	}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			buffer.delete(0, buffer.length());
			Toast.makeText(this,"��ȡ��Ϣʧ�ܣ��� ", Toast.LENGTH_LONG)
			.show();
		}finally{
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			reader=null;
		}
		text.setText(buffer.toString());
    }
}