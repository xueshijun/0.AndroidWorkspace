package com.fit.shap;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
	private boolean autoFlag=false;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        LinearLayout root=(LinearLayout) findViewById(R.id.root);
        final MyShap view=new MyShap(this);
        root.addView(view);
        
        Button line=(Button) findViewById(R.id.line);
        line.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				
				view.setFlag(1);
				view.invalidate();
			}
		});
        
        Button squre=(Button) findViewById(R.id.squre);
        squre.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				
				view.setFlag(2);
				view.invalidate();
			}
		});
        
        Button text=(Button) findViewById(R.id.text);
        text.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				
				view.setFlag(3);
				view.invalidate();
			}
		});
        
        Button auto=(Button) findViewById(R.id.auto);
        auto.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				if(autoFlag){
					view.stopDraw();
				}else{
					view.runDraw();
				}
				autoFlag=!autoFlag;
			}
		});
        
    }
}