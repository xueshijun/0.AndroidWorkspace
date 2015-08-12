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
        //添加背景颜色
         getWindow().setBackgroundDrawableResource(R.color.color);
         
         //添加一个图片
         ImageView image=(ImageView) findViewById(R.id.image);
         Resources r=getResources();
         Drawable dra=r.getDrawable(R.drawable.http_imgload2);
         //添加背景图片
         getWindow().setBackgroundDrawable(dra);
         //添加一个图片
         image.setImageDrawable(dra);
    }
    
    
}