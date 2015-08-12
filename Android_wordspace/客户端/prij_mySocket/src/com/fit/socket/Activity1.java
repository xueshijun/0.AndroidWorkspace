package com.fit.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.fit.dao.ConnectionDAO;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class Activity1 extends ListActivity{
	int j=0;
	private PrintWriter request = null;
	private BufferedReader response = null;
	String id;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main1);
        
        ArrayList<HashMap<String,String>> list1 = new ArrayList<HashMap<String,String>>();
        List<String> list2=getIntent().getExtras().getStringArrayList("list");
    	HashMap<String,String> map=null;
    	//遍历得到的任务集合，并存储到map中
    	for(int i=0;i<list2.size();i+=4){
	    map=new HashMap<String, String>();
		map.put("goodsId", list2.get(i).toString());
		map.put("goodsName", list2.get(i+1).toString());
		map.put("goodsAddress", list2.get(i+2).toString());
		map.put("goodsStatus","未发货");
		list1.add(map);
		}
        
    	// 取得货物所有的ID
    	int idsLength=list1.size();
    	String [] ids=new String[idsLength];
    	for(int m=0;m<list2.size();m+=4){
    	if(j<idsLength){
    		ids[j]=list2.get(m).toString();
    	 	j++;
    	}
    	}
    	 ArrayAdapter<String> adapter1=new ArrayAdapter<String>(Activity1.this,R.layout.list2,ids);
         Spinner spn=(Spinner) findViewById(R.id.spinner);
         spn.setAdapter(adapter1);
         spn.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				TextView view=(TextView) arg1.findViewById(R.id.view);
				id=view.getText().toString();
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				
			}
		});
         //点击发货按钮
         Button btn=(Button) findViewById(R.id.btn);
         btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			       
		        String ur="http://192.168.100.114:8080/proj_myweb/doChange";
		        HttpURLConnection connection=ConnectionDAO.getConnection(ur);
		        try {				
					//发送请求
					request=new PrintWriter(connection.getOutputStream());
					request.print("goodsId="+id);
					request.close();
					
					//接受服务端信息 
					response=new BufferedReader(new InputStreamReader(connection.getInputStream()));
					String info=response.readLine();
					if(info.equals("success")){
						Toast.makeText(Activity1.this,"发货成功,返回重新登录刷新任务！",Toast.LENGTH_LONG).show();
						Activity1.this.finish();
					}

				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
    	if(list1.size()<=0){
    		Toast.makeText(Activity1.this,"暂无任务！",Toast.LENGTH_LONG).show();
    	}else{
    		 SimpleAdapter adapter=new SimpleAdapter(Activity1.this,list1, R.layout.list,new String[]{"goodsId","goodsName","goodsAddress","goodsStatus"}, 
    	        		new int[]{R.id.goodsId,R.id.goodsName,R.id.goodsAddress,R.id.goodsstatus});
    	        setListAdapter(adapter);
    	}
       
	}
}
