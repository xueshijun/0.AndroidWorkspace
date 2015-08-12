package com.fit.notebook;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.fit.note.Notes;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class Activity1 extends Activity {
	private static final SimpleDateFormat sdf = new SimpleDateFormat(
	"yyyy-MM-dd hh:mm:ss");
	private SQLiteDatabase db;
	String noteId;
	EditText edit;
	  @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.edit);
	        
	        db=openOrCreateDatabase(Notes.DBNAME,Context.MODE_PRIVATE,null);
	        edit=(EditText) findViewById(R.id.edit1);
	        
	        //得到上文中出入的数据
	        if(getIntent().getExtras()!=null){
	        	String content1=getIntent().getExtras().getString("content1").toString();
	        	noteId=getIntent().getExtras().getString("noteId").toString();
		        if(content1!=null){
		        	edit.setText(content1);
		        }
	        }
	        
	    }
	  
	  @Override
	public boolean onCreateOptionsMenu(Menu menu) {

		  MenuInflater flater=getMenuInflater();
		  flater.inflate(R.menu.menu2, menu);
		  return super.onCreateOptionsMenu(menu);
	}
	  
	  @Override
	public boolean onOptionsItemSelected(MenuItem item) {
		  
		  
		  String content=edit.getText().toString();
		  
		  int itemId=item.getItemId();
		  switch(itemId){
		  //保存
		  case R.id.save:
			  if(!content.equals("")){
				  db.execSQL("insert into "+Notes.TABLENAME+"("+Notes.TITLE+","+Notes.CONTENT+","+Notes.LTIME+") values(?,?,?)",
						  	new Object[]{sdf.format(new Date()),content,sdf.format(new Date())});
				  Toast.makeText(Activity1.this,"信息保存成功！！！！",Toast.LENGTH_LONG).show();
				  Activity1.this.finish(); 
			  }else{
				  Toast.makeText(Activity1.this,"请输入信息！！！！",Toast.LENGTH_LONG).show();
			  }
			 
			  break;
		  case R.id.update:
			  //根据id修改信息
			  if(noteId!=null){
				  String content2=edit.getText().toString();
				  ContentValues values=new ContentValues();
				  values.put("content",content2);
				  db.update(Notes.TABLENAME,values,Notes._ID+"=?",new String[]{noteId});
				  Toast.makeText(Activity1.this,"信息修改成功！！！！",Toast.LENGTH_LONG).show();
				  Activity1.this.finish(); 
			  }else{
				  Toast.makeText(Activity1.this,"无内容修改！！！！",Toast.LENGTH_LONG).show();
			  }
			
		  	break;
		  	//取消
		  case R.id.cancel:
			  Activity1.this.finish();
			  break;
		  }
		  return super.onOptionsItemSelected(item);
	}
}
