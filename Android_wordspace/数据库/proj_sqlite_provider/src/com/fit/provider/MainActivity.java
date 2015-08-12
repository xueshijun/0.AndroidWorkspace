package com.fit.provider;



import java.text.SimpleDateFormat;
import java.util.Date;

import com.fit.entity.Notes;
import com.fit.recieveProvider.NoteProvider;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;

import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	private static final SimpleDateFormat sdf = new SimpleDateFormat(
			"yyyy-MM-dd hh:mm:ss");

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		MenuInflater flater = getMenuInflater();
		flater.inflate(R.menu.menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		ContentResolver resolver=getContentResolver();
		ContentValues values=new ContentValues();
		
		EditText edit1 = (EditText) findViewById(R.id.edit1);
		EditText edit2 = (EditText) findViewById(R.id.edit2);
		
		String id=edit1.getText().toString();
		String content = edit2.getText().toString();
		
		int itemId = item.getItemId();

		switch (itemId) {
	
			//查询信息
		case R.id.query:
			Cursor cursor=resolver.query(NoteProvider.NOTES_URI,new String[]{Notes._CONTENT},Notes._ID+"=?",new String[]{id},null);
			int index=cursor.getColumnIndex(Notes._CONTENT);
			if(cursor.moveToNext()){
				edit2.setText(cursor.getString(index));
			}else {
				Toast.makeText(this, "无此记录！！", Toast.LENGTH_LONG).show();
			}
			break;
		case R.id.save:
			values.put(Notes._CONTENT,content);
			values.put(Notes._CREATTIME,sdf.format(new Date()));
			values.put(Notes._LASTTIME,sdf.format(new Date()));
			resolver.insert(NoteProvider.NOTES_URI, values);
			Toast.makeText(this,"信息保存成功！！",Toast.LENGTH_LONG).show();
			break;
		case R.id.update:
			values.put(Notes._CONTENT,content);
			values.put(Notes._LASTTIME,sdf.format(new Date()));
			resolver.update(NoteProvider.NOTES_URI, values,Notes._ID+"=?",new String[]{id});
			Toast.makeText(this,"信息修改成功！！",Toast.LENGTH_LONG).show();
			break;
		case R.id.delete:
			resolver.delete(NoteProvider.NOTES_URI,Notes._ID+"=?",new String[]{id});
			Toast.makeText(this,"信息删除成功！！",Toast.LENGTH_LONG).show();
			break;
			
		}
		return super.onOptionsItemSelected(item);
	}

}