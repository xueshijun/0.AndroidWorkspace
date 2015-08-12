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
        //设置布局是竖直方向
        root.setOrientation(LinearLayout.VERTICAL);
        //定义一个节点
        LinearLayout line1=new LinearLayout(this);
        //设置第一个节点为水平方向
        line1.setOrientation(LinearLayout.HORIZONTAL);
        root.addView(line1);
        
        TextView text1=new TextView(this);
        text1.setText(R.string.text1);
        
        Button button1=new Button(this);
        button1.setText(R.string.button1);
        
      //将节点放入到上一个节点中
        line1.addView(text1);
        line1.addView(button1);
     
        
        this.setContentView(root);
    }
}