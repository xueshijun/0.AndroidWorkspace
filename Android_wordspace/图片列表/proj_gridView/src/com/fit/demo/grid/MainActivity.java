package com.fit.demo.grid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;

public class MainActivity extends Activity implements OnItemSelectedListener ,OnItemClickListener {
    /** Called when the activity is first created. */
	private ImageView imageView;
  
  //获得资源文件
    private int[] photos=new int[]{
    		R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.d,
    		R.drawable.e,R.drawable.f,R.drawable.g,R.drawable.h
    };

	public void onItemSelected(AdapterView<?> arg0, View arg1, int position,
			long arg3) {
		// 对应图片的位置
		imageView.setImageResource(photos[position]);
		
	}

	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		// TODO Auto-generated method stub
		imageView.setImageResource(photos[position]);
		
	}
	  @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.main);
	        
	        GridView gridView=(GridView) findViewById(R.id.gridView);
	        
	        List<Map<String,Object>> pictures=new ArrayList<Map<String,Object>>();
	        for(int i=0;i<photos.length;i++){
	        	Map<String,Object> photo=new HashMap<String, Object>();
	        	photo.put("imageView",photos[i]);
	        	pictures.add(photo);
	        }
	        
	        //适配器
	        SimpleAdapter adapter=new SimpleAdapter(MainActivity.this,pictures,R.layout.main1,
	        		new String[]{"imageView"},new int[]{R.id.view});
	        
	        gridView.setAdapter(adapter);
	        
	        imageView=(ImageView) findViewById(R.id.imageView);
	        gridView.setOnItemClickListener(this);
	        gridView.setOnItemSelectedListener(this);
	        imageView.setImageResource(photos[0]);
	    }
	  
}