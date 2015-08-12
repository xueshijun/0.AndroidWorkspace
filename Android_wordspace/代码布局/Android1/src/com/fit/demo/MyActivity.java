package com.fit.demo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MyActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
   
        LinearLayout root=new LinearLayout(this);
        //���ò�������ֱ����
        root.setOrientation(LinearLayout.VERTICAL);
        //����һ���ڵ�
        LinearLayout line1=new LinearLayout(this);
        //���õ�һ���ڵ�Ϊˮƽ����
        line1.setOrientation(LinearLayout.HORIZONTAL);
        root.addView(line1);
        
        TextView text1=new TextView(this);
        text1.setText(R.string.text1);
        
        Button button1=new Button(this);
        button1.setText(R.string.button1);
        
      //���ڵ���뵽��һ���ڵ���
        line1.addView(text1);
        line1.addView(button1);
     
        
        this.setContentView(root);
    }
}