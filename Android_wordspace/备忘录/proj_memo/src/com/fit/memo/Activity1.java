package com.fit.memo;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.fit.entity.Memo;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Activity1 extends Activity {
	private static final SimpleDateFormat sdf=new SimpleDateFormat("MM 月 dd 日");
	private SQLiteDatabase db;
	EditText edit;
	String content;
	String memoId;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.write);
        
        db=openOrCreateDatabase(Memo.DBNAME,Context.MODE_PRIVATE,null);
        
        TextView text=(TextView) findViewById(R.id.textLtime);
        text.setText(sdf.format(new Date()));
        
        edit=(EditText) findViewById(R.id.edit);
        //得到从上个页面传来的值
        if(getIntent().getExtras()!=null){
        	String upContent=getIntent().getExtras().getString("upContent");
        	memoId=getIntent().getExtras().getString("memoId");
        	if(upContent!=null){
        		edit.setText(upContent);
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
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		
		int menuId=item.getItemId();
		 content=edit.getText().toString();
		
		switch(menuId){
		case R.id.save:
			if(content.equals("")){
				Toast.makeText(Activity1.this,"无任何内容！！！！",Toast.LENGTH_LONG).show();
			}else{
			db.execSQL("insert into "+Memo.TABLENAME+"("+Memo.LTIME+","+Memo.CONTENT+") values(?,?)",new Object[]{sdf.format(new Date()),content});
			Toast.makeText(Activity1.this,"信息保存成功！！！！",Toast.LENGTH_LONG).show();
			 Activity1.this.finish();
			}
			break;
		case R.id.update:
			String content2=edit.getText().toString();
			if(content2.equals("") || memoId.equals("")){
				Toast.makeText(Activity1.this,"修改失败！！！！",Toast.LENGTH_LONG).show();
			}else{
			ContentValues values=new ContentValues();
			values.put(Memo.CONTENT,content2);
			db.update(Memo.TABLENAME, values, Memo._ID+"=?",new String[]{memoId});
			Toast.makeText(Activity1.this,"信息修改成功！！！！",Toast.LENGTH_LONG).show();
			 Activity1.this.finish();
			}
			break;
		case R.id.cancel:
			Activity1.this.finish();
			break;
		}
		return super.onMenuItemSelected(featureId, item);
	}
}
