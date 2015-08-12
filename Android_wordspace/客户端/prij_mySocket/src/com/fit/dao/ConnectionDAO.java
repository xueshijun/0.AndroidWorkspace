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
			//�������Ӷ���
			connection=(HttpURLConnection) url.openConnection();
			//�����ύ��ʽ
			connection.setRequestMethod("GET");
			//���ÿɶ���д
			connection.setDoInput(true);
			connection.setDoOutput(true);
			//���ò�Ҫ����
			connection.setUseCaches(false);
			//���ö�Ӧ���뷽ʽ
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
