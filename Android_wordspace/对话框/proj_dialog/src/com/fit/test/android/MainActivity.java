package com.fit.test.android;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
	private final int DIALOG=0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button button=(Button) findViewById(R.id.button);
        button.setOnClickListener(new android.view.View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				MainActivity.this.showDialog(DIALOG);
			}
		});
        }
    @Override
    //创建一个对话框
    protected Dialog onCreateDialog(int id) {
        //空的对话框
    	Dialog dialog=null;
    	//往对话框里可以添加东西
    	AlertDialog.Builder builder=new AlertDialog.Builder(this);
    	//消息标题
    	builder.setTitle(R.string.title);
    	//builder.setMessage("请选择操作");
    	//添加一个确定按钮
    	builder.setPositiveButton("确定",new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(MainActivity.this,"您选择了确定",Toast.LENGTH_LONG).show();
			}
		});
    	//添加一个取消按钮
    	builder.setNegativeButton("取消",new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(MainActivity.this,"您选择了取消",Toast.LENGTH_LONG).show();
			}
		});
    	//添加一个中立按钮
    	builder.setNeutralButton("中立",new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(MainActivity.this,"您选择了中立",Toast.LENGTH_LONG).show();
			}
		});
    	//添加一个选项列表
    	builder.setItems(R.array.arrayList,new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
			Toast.makeText(MainActivity.this,"您选择了第"+(which+1)+"项",Toast.LENGTH_LONG).show();	
			}
		});
    	
    	dialog=builder.create();
    	return dialog;
    }
 
}