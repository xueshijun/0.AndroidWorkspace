package com.fit.android.download;

import java.io.File;
import java.net.URL;
import java.net.URLConnection;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity {
	private EditText downloadUrl;
	private EditText downloadFileName;
	private EditText downloadThreadNum;
	private Button downloadBt;
	private ProgressBar downloadProgressBar;
	private TextView progressMessage;
	private int downloadedSize = 0;
	private int fileSize = 0;
 
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
 
		downloadUrl = (EditText) findViewById(R.id.downloadUrl);
		downloadFileName = (EditText) findViewById(R.id.downloadFileName);
		downloadThreadNum = (EditText) findViewById(R.id.downloadThreadNum);
		progressMessage = (TextView) findViewById(R.id.progressMessage);
		downloadBt = (Button) findViewById(R.id.downloadBt);
		downloadProgressBar = (ProgressBar) findViewById(R.id.downloadProgressBar);
		downloadProgressBar.setVisibility(View.VISIBLE);
		downloadProgressBar.setMax(100);
		downloadProgressBar.setProgress(0);
		downloadBt.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				
//				ProgressDialog progress=new ProgressDialog(MainActivity.this);
//				progress.setMax(100);
//				progress.setProgress(0);
//				progress.setTitle("�������ء���������");
//				progress.setMessage(progressMessage.getText().toString());
//				progress.show();
				download();
			}
		});
	}
 
	
	private void download() {
		// ��ȡSD��Ŀ¼
		String dowloadDir = Environment.getExternalStorageDirectory()
				+ "/ideasdownload/";
		File file = new File(dowloadDir);
		//��������Ŀ¼
		if (!file.exists()) {
			file.mkdirs();
		}
 
		//��ȡ�����߳��������Ϊ�գ����߳�����
		int downloadTN = Integer.valueOf("".equals(downloadThreadNum.getText().toString()) ? "1" : downloadThreadNum.getText().toString());
		//��������ļ���Ϊ�����ȡUrlβΪ�ļ���
		int fileNameStart = downloadUrl.getText().toString().lastIndexOf("/");
		String fileName = "".equals(downloadFileName.getText().toString()) ? downloadUrl.getText().toString().substring(fileNameStart): downloadFileName.getText().toString();
		//��ʼ����ǰ�����ذ�ť����Ϊ������
		downloadBt.setClickable(false);
		//��������Ϊ0
		downloadProgressBar.setProgress(0);
		//�����ļ������߳�
		new downloadTask(downloadUrl.getText().toString(), Integer
				.valueOf(downloadTN), dowloadDir + fileName).start();
	}
 
	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			//���յ�������ͼ��Ϣʱ��������������ذٷֱȣ�ͬʱ���½�������Ϣ
			int progress = (Double.valueOf((downloadedSize * 1.0 / fileSize * 100))).intValue();
			if (progress == 100) {
				downloadBt.setClickable(true);
				progressMessage.setText("������ɣ�");
			} else {
				progressMessage.setText("��ǰ����:" + progress + "%");
			}
			downloadProgressBar.setProgress(progress);
		}
 
	};
 	public class downloadTask extends Thread {
		private int blockSize, downloadSizeMore;
		private int threadNum = 5;
		String urlStr, threadNo, fileName;
 
		public downloadTask(String urlStr, int threadNum, String fileName) {
			this.urlStr = urlStr;
			this.threadNum = threadNum;
			this.fileName = fileName;
		}
 
		@Override
		public void run() {
			FileDownloadThread[] fds = new FileDownloadThread[threadNum];
			try {
				URL url = new URL(urlStr);
				URLConnection conn = url.openConnection();
				//��ȡ�����ļ����ܴ�С
				fileSize = conn.getContentLength();
				//����ÿ���߳�Ҫ���ص�������
				blockSize = fileSize / threadNum;
				// ���������ٷֱȼ������
				downloadSizeMore = (fileSize % threadNum);
				File file = new File(fileName);
				for (int i = 0; i < threadNum; i++) {
					//�����̣߳��ֱ������Լ���Ҫ���صĲ���
					FileDownloadThread fdt = new FileDownloadThread(url, file,
							i * blockSize, (i + 1) * blockSize - 1);
					fdt.setName("Thread" + i);
					fdt.start();
					fds[i] = fdt;
				}
				boolean finished = false;
				while (!finished) {
					// �Ȱ������������㶨
					downloadedSize = downloadSizeMore;
					finished = true;
					for (int i = 0; i < fds.length; i++) {
						downloadedSize += fds[i].getDownloadSize();
						if (!fds[i].isFinished()) {
							finished = false;
						}
					}
					//֪ͨhandlerȥ������ͼ���
					handler.sendEmptyMessage(0);
					//��Ϣ1����ٶ�ȡ���ؽ���
					sleep(1000);
				}
			} catch (Exception e) {
 
			}
 
		}
	}
}
