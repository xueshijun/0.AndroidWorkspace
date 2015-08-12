package com.fit.web;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.os.Environment;

public class ConnectionDAO {

	
	public static void saveFile(String urlpath){
	
		InputStream inStream=null;
		BufferedOutputStream out=null;
		try {
			out=new BufferedOutputStream(new FileOutputStream(Environment.getExternalStorageDirectory()+"/title.xml"));
			URL url=new URL(urlpath);
			HttpURLConnection conn=(HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			 //定义请求时间，在ANDROID中最好是不好超过10秒。否则将被系统回收。
			conn.setConnectTimeout(6*1000);
			if(conn.getResponseCode()==200){
				inStream=conn.getInputStream();
			}
			
			byte []len=new byte[100];
			while(inStream.read(len)!=-1){
				out.write(len);
			}
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			inStream.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
