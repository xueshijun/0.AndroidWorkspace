package com.fit.android.note;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fit.android.entity.Notes;

public class MainActivity extends Activity {
	private SQLiteDatabase db;
	private static final SimpleDateFormat sdf = new SimpleDateFormat(
			"yyyy-MM-dd hh:mm:ss");

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		db = openOrCreateDatabase(Notes._DBNAME, Context.MODE_PRIVATE, null);

		int version = db.getVersion();
		//创建数据库
		if (version < 1) {
			db.execSQL("create table " + Notes._NOTESTABLE + "(" + Notes._ID
					+ " integer primary key," + Notes._CONTENT + " text,"
					+ Notes._CREATTIME + " text," +Notes._LASTTIME+ " text)");
			db.setVersion(1);
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		MenuInflater flater = getMenuInflater();
		flater.inflate(R.menu.menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		EditText edit1 = (EditText) findViewById(R.id.edit1);
		EditText edit2 = (EditText) findViewById(R.id.edit2);
		
		String id=edit1.getText().toString();
		String content = edit2.getText().toString();
		int itemId = item.getItemId();

		switch (itemId) {
		case R.id.save:
			//保存信息
			
			db.execSQL("insert into " + Notes._NOTESTABLE + "("
					+ Notes._CONTENT + "," + Notes._CREATTIME + ","
					+ Notes._LASTTIME + ") values(?,?,?)", new Object[] {
					content, sdf.format(new Date()), sdf.format(new Date())});
			Toast.makeText(this, "信息保存成功！！", Toast.LENGTH_LONG).show();
			break;
			//查询信息
		case R.id.query:
			Cursor cursor=db.query(Notes._NOTESTABLE,new String[]{Notes._CONTENT},Notes._ID+"=?",new String[]{id},null,null,null);
			int index=cursor.getColumnIndex(Notes._CONTENT);
			if(cursor.moveToNext()){
				edit2.setText(cursor.getString(index));
			}else {
				Toast.makeText(this, "无此记录！！", Toast.LENGTH_LONG).show();
			}
			break;
	
			//修改信息
		case 	R.id.update:
			ContentValues values=new ContentValues();
			values.put(Notes._CONTENT,content);
			values.put(Notes._LASTTIME,sdf.format(new Date()));
			db.update(Notes._NOTESTABLE,values,Notes._ID+"=?",new String[]{id});
			Toast.makeText(this,"修改成功！！",Toast.LENGTH_LONG).show();
			break;
			//删除信息
		case	R.id.delete:
			db.delete(Notes._NOTESTABLE,Notes._ID+"=?",new String[]{id});
			Toast.makeText(this,"删除成功！！",Toast.LENGTH_LONG).show();
			break;
		}
		return super.onOptionsItemSelected(item);
	}

}