package com.fit.store;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button button1=(Button) findViewById(R.id.button1);
        button1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				saveInfo();
				Toast.makeText(MainActivity.this,"��Ϣ����ɹ�����",Toast.LENGTH_LONG).show();
				 showInfo();
			}
		});
        showInfo();
    }
    
    
    public void saveInfo(){
    	EditText text1=(EditText) findViewById(R.id.text1);
    	String name=text1.getText().toString();
    	String pass=((EditText)findViewById(R.id.text2)).getText().toString();
    	//������Ϣ���ⲿ�����Է���
    	//SharedPreferences preferences=getPreferences(MODE_PRIVATE);
    	//������Ϣ���ⲿ���Է���
    	SharedPreferences preferences=getSharedPreferences("userInfo",MODE_PRIVATE);
    	Editor editor=preferences.edit();
    	editor.putString("name",name);
    	editor.putString("pass",pass);
    	editor.commit();
    }
    public void showInfo(){
    	//��ȡ��Ϣ 
    	//SharedPreferences preferences=getPreferences(MODE_PRIVATE);
    	SharedPreferences preferences=getSharedPreferences("userInfo",MODE_PRIVATE);
    	String name=preferences.getString("name",null);
    	TextView view1=(TextView) findViewById(R.id.view1);
    	if(name!=null){
    		view1.setText("��ӭ��"+name);
    	}
    	
    	
    	
    }
}