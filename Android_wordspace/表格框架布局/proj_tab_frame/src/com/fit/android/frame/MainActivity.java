package com.fit.android.frame;


import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

public class MainActivity extends TabActivity{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Resources resources=getResources();
        
       TabHost tabHost=getTabHost();
       
       TabHost.TabSpec 	spec;
       
       Intent intent;
       
       intent=new Intent(this,Activity1.class);
       spec=tabHost.newTabSpec("ssss").setIndicator("��һҳ",resources.getDrawable(R.drawable.btn_star_big_on)).setContent(intent);
       tabHost.addTab(spec);
       
       intent=new Intent(this,Activity2.class);
       spec=tabHost.newTabSpec("dadd").setIndicator("�ڶ�ҳ",resources.getDrawable(R.drawable.btn_star_big_on)).setContent(intent);
       tabHost.addTab(spec);
       
       intent=new Intent(this,Activity3.class);
       spec=tabHost.newTabSpec("sdsd").setIndicator("����ҳ",resources.getDrawable(R.drawable.btn_star_big_on)).setContent(intent);
       tabHost.addTab(spec);
       
       tabHost.setCurrentTab(1);
    }
}