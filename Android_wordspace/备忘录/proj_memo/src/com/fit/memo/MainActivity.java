package com.fit.memo;

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

import com.fit.entity.Memo;

public class MainActivity extends ListActivity {
	
	private SQLiteDatabase db;
	private String memoId;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    @Override
    protected void onStart() {
    	query();
    	super.onStart();
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

    	MenuInflater flater=getMenuInflater();
    	flater.inflate(R.menu.menu1, menu);
    	return super.onCreateOptionsMenu(menu);
    }
    
    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {

    	int menuId=item.getItemId();
    	switch(menuId){
    	//打开
    	case R.id.news:
    		Intent intent=new Intent(MainActivity.this,Activity1.class);
    		startActivity(intent);
    		break;
    	case R.id.open:
    		if(memoId!=null){
    			Cursor cursor=db.query(Memo.TABLENAME,new String[]{Memo.CONTENT},Memo._ID+"=?",new String[]{memoId},null, null, null);
    			if(cursor.moveToNext()){
    				String upContent=cursor.getString(cursor.getColumnIndex(Memo.CONTENT));
    				Bundle extras=new Bundle();
    				extras.putString("upContent",upContent);
    				extras.putString("memoId", memoId);
    				Intent intent2=new Intent(MainActivity.this,Activity1.class);
    				intent2.putExtras(extras);
    				startActivity(intent2);
    			}
    			}else{
    				Toast.makeText(MainActivity.this, "请选择要打开的记录！！", Toast.LENGTH_LONG).show();
    		}
    		break;
    		//删除
    	case R.id.delete:
    		if(memoId!=null){
    			db.delete(Memo.TABLENAME,Memo._ID+"=?",new String[]{memoId});
        		Toast.makeText(MainActivity.this, "删除成功！！", Toast.LENGTH_LONG).show();
        		query();
    		}else{
    			Toast.makeText(MainActivity.this, "无此记录！！", Toast.LENGTH_LONG).show();
    		}
    		
    		break;
    	
    	}
    	
    	
    	return super.onMenuItemSelected(featureId, item);
    }
    
    public void query(){
    	
    	db=openOrCreateDatabase(Memo.DBNAME,Context.MODE_PRIVATE,null);
    	int version=db.getVersion();
    	if(version<1){
    		db.execSQL("create table "+Memo.TABLENAME+"("+Memo._ID+" integer primary key,"+Memo.LTIME+" text,"+Memo.CONTENT+" text)");
    	db.setVersion(1);
    	Toast.makeText(MainActivity.this,"数据库创建成功！！",Toast.LENGTH_LONG).show();
    	}
    	
    	Cursor cursor=db.query(Memo.TABLENAME,new String[]{Memo._ID,Memo.LTIME}, null,null,null,null,null);
    	ArrayList<HashMap<String,String>> list=new ArrayList<HashMap<String,String>>();
    	
    	while(cursor.moveToNext()){
    		HashMap<String,String> map=new HashMap<String, String>();
    		String memoId=cursor.getString(cursor.getColumnIndex(Memo._ID));
    		String memoLtime=cursor.getString(cursor.getColumnIndex(Memo.LTIME));
    		
    		map.put("memoId",memoId);
    		map.put("memoLtime",memoLtime);
    		list.add(map);
    	}
    	
    	SimpleAdapter adapter=new SimpleAdapter(MainActivity.this,list,R.layout.lsit,new String[]{"memoId","memoLtime"},new int[]{R.id.ids,R.id.ltime});
    	setListAdapter(adapter);
    	
    	ListView lv=getListView();
    	lv.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				TextView textId=(TextView) findViewById(R.id.ids);
				memoId=textId.getText().toString();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				
			}
		});
    	//点击视图事件
    	lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				
				TextView textId=(TextView)arg1.findViewById(R.id.ids);
				memoId=textId.getText().toString();
				Cursor cursor=db.query(Memo.TABLENAME,new String[]{Memo.CONTENT},Memo._ID+"=?",new String[]{memoId},null,null,null);
				if(cursor.moveToNext()){
					String content=cursor.getString(cursor.getColumnIndex(Memo.CONTENT));
					System.out.println("+++++++"+content);
					Bundle extras=new Bundle();
					extras.putString("upContent",content);
					extras.putString("memoId",memoId);
					Intent intent=new Intent(MainActivity.this,Activity1.class);
					intent.putExtras(extras);
					startActivity(intent);
				}
			}
		});
    }
}