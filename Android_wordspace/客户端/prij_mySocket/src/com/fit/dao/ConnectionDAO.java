package com.fit.dao;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;


public class ConnectionDAO {

	
	public static HttpURLConnection getConnection(String ur){
		HttpURLConnection connection=null;
		try {
			URL url=new URL(ur);
			//建立连接对象
			connection=(HttpURLConnection) url.openConnection();
			//设置提交方式
			connection.setRequestMethod("GET");
			//设置可读可写
			connection.setDoInput(true);
			connection.setDoOutput(true);
			//设置不要缓存
			connection.setUseCaches(false);
			//设置对应编码方式
			connection.setRequestProperty("Charset", "utf_8");
			return connection;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return connection;
	}
}
