package com.fit.demo.android;

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;



public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);    
      
    }
    //将菜单放到页面中
	public boolean onCreateOptionsMenu(Menu menu){
		
		MenuInflater flater=getMenuInflater();
		flater.inflate(R.menu.menu1, menu);
		return super.onCreateOptionsMenu(menu);
	}
	@Override
	//判断选中的菜单选项
	public boolean onOptionsItemSelected(MenuItem item) {
		int open=item.getItemId();
		if(open==R.id.open||open==R.id.newd){
			EditText edit=(EditText) findViewById(R.id.edit);
			//存值并传入到第二个Activity
	    	Bundle extras=new Bundle();
	    	extras.putString("edit",edit.getText().toString());
	    	Intent intent=new Intent(this,MyActivity1.class);
	    	intent.putExtras(extras);
	    	startActivity(intent);
		}
		return super.onOptionsItemSelected(item);
	}
	
}