package com.fit.player;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.fit.dao.FileList;

import android.app.ListActivity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends ListActivity {

	private MediaPlayer player;
	private String name;
	private List<Map<String,String>> list;
	//����һ����־
	private int current;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        player=new MediaPlayer();
        
        //׼������
        String path=Environment.getExternalStorageDirectory().getAbsolutePath();
		list=FileList.fileList(path);
		SimpleAdapter adapter=new SimpleAdapter(MainActivity.this,list,R.layout.list,new String[]{"name","fileSize"},new int[]{R.id.name,R.id.fileSize});
		setListAdapter(adapter);
		ListView lv=getListView();
		
		//����¼�
		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
					current = arg2;
					TextView view=(TextView) arg1.findViewById(R.id.name);
					name=view.getText().toString();
					try {
						//����
						player.reset();
						//·��
						player.setDataSource(Environment.getExternalStorageDirectory().getAbsolutePath()+("/"+name));
						player.prepare();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalStateException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					player.start();
				
			}
		});
        
        //��ť����
		ImageButton start=(ImageButton) findViewById(R.id.start);
		ImageButton pause=(ImageButton) findViewById(R.id.pause);
		ImageButton stop=(ImageButton) findViewById(R.id.stop);
		ImageButton pre=(ImageButton) findViewById(R.id.pre);
		ImageButton next=(ImageButton) findViewById(R.id.next);
		//��ʼ
		start.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				player.start();
			}
		});
		//��ͣ
		pause.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				player.pause();
			}
		});
		//ֹͣ
		stop.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				player.seekTo(0);
				player.stop();
				
				try {
					player.prepare();
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		//��һ��
		pre.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(current<0){
					playMusic(current=0);
				}else{
					playMusic(current=current-1);
				}
			}
		});
		//��һ��
		next.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(current==list.size()-1){
					playMusic(current);
				}else if(current<list.size()-1){
					playMusic(current =current+1);
				}
				
			}
		});
    }
	public void playMusic(int postion){
		//����
		if(postion<0){
			player.seekTo(0);
			player.stop();
		}else{
			name = list.get(postion).get("name");
		}
		player.reset();
		//·��
		try {
			player.setDataSource(Environment.getExternalStorageDirectory().getAbsolutePath()+("/"+name));
			player.prepare();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		player.start();
	};
	
}