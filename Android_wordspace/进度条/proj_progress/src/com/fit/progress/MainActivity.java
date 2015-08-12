package com.fit.progress;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	private ProgressDialog progress;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button button=(Button) findViewById(R.id.button);
        button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//��ʾԲȦ����
				//final ProgressDialog progress=ProgressDialog.show(MainActivity.this,"���ڵ�¼", "loading......");
				//��ʾ������
//				new Thread(){
//				public void run(){
//					try {
//						
//							Thread.sleep(10000);
//						
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//					progress.dismiss();
//				}
//			}.start();
				progress=new ProgressDialog(MainActivity.this);
				progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
				progress.setMax(2000);
				progress.setMessage("���ڵ�¼��Loading...");
				progress.setCancelable(false);
				progress.show();
				new Thread(){
					public void run(){
						progress();
						progress.dismiss();
					}
				}.start();
			}
		});
     }
    private void progress(){
    	try {
    	for(int i=0;i<progress.getMax();i+=100){
    		progress.setProgress(i);
    		Thread.sleep(200);
    	}				
		} catch (InterruptedException e) {
				e.printStackTrace();
			}
    	
    }
}