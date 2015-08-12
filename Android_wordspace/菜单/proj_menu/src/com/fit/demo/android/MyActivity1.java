package com.fit.demo.android;

import android.app.Activity;


import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MyActivity1 extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit);
        
        //取得上一个Activity的值并显示
        String intent=getIntent().getExtras().getString("edit").toString();
        TextView view=(TextView) findViewById(R.id.view);
        view.setText("标题："+intent);
        
        final Button button=(Button) findViewById(R.id.button);
        registerForContextMenu(button);
//        button.setOnClickListener(new OnClickListener() {
//			
//			public void onClick(View v) {
//				registerForContextMenu(button);
//			}
//		});
    }
    
	@Override
	
	
	//将菜单放到页面中
	public boolean onCreateOptionsMenu(Menu menu) {
		
		MenuInflater flater=getMenuInflater();
		flater.inflate(R.menu.menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	//点击后显示点击的内容
	public boolean onOptionsItemSelected(MenuItem item) {
		
		//Toast.makeText(this, item.getItemId()+":"+item.getTitle(),Toast.LENGTH_LONG).show();
		
		int itemid=item.getItemId();
		switch(itemid){
		case R.id.file:Toast.makeText(this, "选中了文件操作",Toast.LENGTH_LONG).show();break;
		case R.id.edit:Toast.makeText(this, "选中了编辑操作",Toast.LENGTH_LONG).show();break;
		case R.id.format:Toast.makeText(this,"选中了格式操作",Toast.LENGTH_LONG).show();break;
		case R.id.view:Toast.makeText(this, "选中了查看操作",Toast.LENGTH_LONG).show();break;
		case R.id.help:Toast.makeText(this, "选中了帮助操作",Toast.LENGTH_LONG).show();break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		MenuInflater flater=getMenuInflater();
		flater.inflate(R.menu.menu, menu);
		
		super.onCreateContextMenu(menu, v, menuInfo);
	}
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		int itemid=item.getItemId();
		switch(itemid){
		case R.id.file:Toast.makeText(this, "选中了文件操作",Toast.LENGTH_LONG).show();break;
		case R.id.edit:Toast.makeText(this, "选中了编辑操作",Toast.LENGTH_LONG).show();break;
		case R.id.format:Toast.makeText(this,"选中了格式操作",Toast.LENGTH_LONG).show();break;
		case R.id.view:Toast.makeText(this, "选中了查看操作",Toast.LENGTH_LONG).show();break;
		case R.id.help:Toast.makeText(this, "选中了帮助操作",Toast.LENGTH_LONG).show();break;
		}
		return super.onContextItemSelected(item);
	}
}