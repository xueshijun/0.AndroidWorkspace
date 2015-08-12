package com.fit.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.fit.entity.Notes;
import com.fit.entity.Notess;
import com.fit.resolver.Resolver;

import android.app.Activity;
import android.app.ListActivity;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ListActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        
        	ContentResolver resolver=getContentResolver();
        	Cursor cursor=resolver.query(Resolver.NOTES_URI,new String[]{Notes._ID,Notes._CREATTIME,Notes._LASTTIME,Notes._CONTENT},null,null,null);
        	ArrayList<HashMap<String,String>> list=new ArrayList<HashMap<String,String>>();
        	Notess note=null;
        	while(cursor.moveToNext()){
        		note=new Notess();
        		note.setId(cursor.getString(cursor.getColumnIndex(Notes._ID)));
        		note.setCtime(cursor.getString(cursor.getColumnIndex(Notes._CREATTIME)));
        		note.setLtime(cursor.getString(cursor.getColumnIndex(Notes._LASTTIME)));
        		note.setContent(cursor.getString(cursor.getColumnIndex(Notes._CONTENT)));
        		HashMap<String, String> map=new HashMap<String, String>();
        		map.put("ida",note.getId());
        		map.put("ctime", note.getCtime());
        		map.put("ltime",note.getLtime());
        		map.put("content",note.getContent());
        		list.add(map);
        	}
        
//        ArrayList<HashMap<String,String>> list=new ArrayList<HashMap<String,String>>();
//        
//        HashMap<String,String> map1=new HashMap<String, String>();
//        HashMap<String,String> map2=new HashMap<String,String>();
//        
//        map1.put("ctime","aaaaaaaaa");
//        map1.put("ltime","bbbbbbbbbb");
//        map2.put("ctime","cccccccccc");
//        map2.put("ltime","dddddddddd");
//        
//        list.add(map1);
//        list.add(map2);
//        
        SimpleAdapter listAdapter = new SimpleAdapter(this, list,   
	              R.layout.notes, new String[] {"ida", "ctime", "ltime","content"},   
             new int[] {R.id.ids, R.id.ctime, R.id.ltime,R.id.content });
        
        setListAdapter(listAdapter);
		
    }
}