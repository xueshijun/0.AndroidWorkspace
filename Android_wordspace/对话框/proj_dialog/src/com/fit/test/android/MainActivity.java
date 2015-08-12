package com.fit.test.android;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
	private final int DIALOG=0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button button=(Button) findViewById(R.id.button);
        button.setOnClickListener(new android.view.View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				MainActivity.this.showDialog(DIALOG);
			}
		});
        }
    @Override
    //����һ���Ի���
    protected Dialog onCreateDialog(int id) {
        //�յĶԻ���
    	Dialog dialog=null;
    	//���Ի����������Ӷ���
    	AlertDialog.Builder builder=new AlertDialog.Builder(this);
    	//��Ϣ����
    	builder.setTitle(R.string.title);
    	//builder.setMessage("��ѡ�����");
    	//���һ��ȷ����ť
    	builder.setPositiveButton("ȷ��",new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(MainActivity.this,"��ѡ����ȷ��",Toast.LENGTH_LONG).show();
			}
		});
    	//���һ��ȡ����ť
    	builder.setNegativeButton("ȡ��",new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(MainActivity.this,"��ѡ����ȡ��",Toast.LENGTH_LONG).show();
			}
		});
    	//���һ��������ť
    	builder.setNeutralButton("����",new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(MainActivity.this,"��ѡ��������",Toast.LENGTH_LONG).show();
			}
		});
    	//���һ��ѡ���б�
    	builder.setItems(R.array.arrayList,new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
			Toast.makeText(MainActivity.this,"��ѡ���˵�"+(which+1)+"��",Toast.LENGTH_LONG).show();	
			}
		});
    	
    	dialog=builder.create();
    	return dialog;
    }
 
}