package com.fit.notebook;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;

import com.fit.note.Notes;

public class MainActivity extends ListActivity {
	/** Called when the activity is first created. */
	private SQLiteDatabase db;
	private String noteId;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
	}

	@Override
	//调用onStart(),刷新页面
	protected void onStart() {
		query();
		super.onStart();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater flater = getMenuInflater();
		flater.inflate(R.menu.menu1, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		int menuId = item.getItemId();
		switch (menuId) {
		// 新建
		case R.id.news:
			Intent intent= new Intent(MainActivity.this, Activity1.class);
			startActivity(intent);
			break;
		case R.id.open:
			//根据ID查询
			if(noteId!=null){
				Cursor cursor=db.query(Notes.TABLENAME,new String[]{Notes.CONTENT},Notes._ID+"=?",new String[]{noteId},null,null,null);
				if(cursor.moveToNext()){
					String content1=cursor.getString(cursor.getColumnIndex(Notes.CONTENT));
					Bundle extras=new Bundle();
					extras.putString("noteId",noteId);
					extras.putString("content1",content1);
					Intent intent1=new Intent(MainActivity.this,Activity1.class);
					intent1.putExtras(extras);
					startActivity(intent1);
					}
			}else{
				Toast.makeText(MainActivity.this, "无此记录！！", Toast.LENGTH_LONG).show();
			}
			
			break;
		case R.id.delete:
			//删除
			if(noteId!=null){
				db.delete(Notes.TABLENAME,Notes._ID+"=?",new String[]{noteId});
				Toast.makeText(MainActivity.this, "删除成功！！", Toast.LENGTH_LONG).show();
				query();
			}else{
				Toast.makeText(MainActivity.this, "无记录！！", Toast.LENGTH_LONG).show();
			}
		
			break;
		}
		return super.onMenuItemSelected(featureId, item);
	}
	
	public  void query(){
		// 创建数据库
		db = openOrCreateDatabase(Notes.DBNAME, Context.MODE_PRIVATE, null);
		int version = db.getVersion();
		if (version < 1) {
			db.execSQL("create table " + Notes.TABLENAME + "(" + Notes._ID
					+ " integer primary key," + Notes.TITLE + " text,"
					+ Notes.CONTENT + " text," + Notes.LTIME + " text)");
			db.setVersion(1);
			Toast.makeText(MainActivity.this, "数据库创建成功！！", Toast.LENGTH_LONG)
					.show();
		}

		// 查询表信息
		Cursor cursor = db.query(Notes.TABLENAME, new String[] { Notes._ID,
				Notes.TITLE }, null, null, null, null, null);
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		// 提供数据
		if (cursor != null) {
			while (cursor.moveToNext()) {
				HashMap<String, String> map = new HashMap<String, String>();
				String noteId = cursor.getString(cursor
						.getColumnIndex(Notes._ID));
				String ltime = cursor.getString(cursor
						.getColumnIndex(Notes.TITLE));
				map.put("noteId", noteId);
				map.put("ltime", ltime);
				list.add(map);
			}

			// 适配器
			SimpleAdapter adapter = new SimpleAdapter(MainActivity.this, list,
					R.layout.list, new String[] { "noteId", "ltime" },
					new int[] { R.id.ids, R.id.ltime });
			setListAdapter(adapter);
		} else {
			Toast.makeText(MainActivity.this, "暂无记录！！", Toast.LENGTH_LONG)
					.show();
		}

		ListView lv = getListView();
		
		//选择监听器
		lv.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				TextView txtId = (TextView) arg1.findViewById(R.id.ids);
				noteId=txtId.getText().toString();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		//点击监听器
		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				TextView txtId = (TextView) arg1.findViewById(R.id.ids);
				
				noteId=txtId.getText().toString();
				Cursor cursor=db.query(Notes.TABLENAME,new String[]{Notes.CONTENT},Notes._ID+"=?",new String[]{noteId},null,null,null);
				if(cursor.moveToNext()){
					String content1=cursor.getString(cursor.getColumnIndex(Notes.CONTENT));
					Bundle extras=new Bundle();
					extras.putString("content1",content1);
					extras.putString("noteId",noteId);
					Intent intent1=new Intent(MainActivity.this,Activity1.class);
					intent1.putExtras(extras);
					startActivity(intent1);
					}
				
			}
		});
	}
}