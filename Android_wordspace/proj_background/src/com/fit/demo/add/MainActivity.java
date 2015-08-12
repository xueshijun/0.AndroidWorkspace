package com.fit.demo.add;



import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.widget.ImageView;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //��ӱ�����ɫ
         getWindow().setBackgroundDrawableResource(R.color.color);
         
         //���һ��ͼƬ
         ImageView image=(ImageView) findViewById(R.id.image);
         Resources r=getResources();
         Drawable dra=r.getDrawable(R.drawable.http_imgload2);
         //��ӱ���ͼƬ
         getWindow().setBackgroundDrawable(dra);
         //���һ��ͼƬ
         image.setImageDrawable(dra);
    }
    
    
}