package com.fit.button;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        
        final ImageButton button1=(ImageButton) findViewById(R.id.button1);

        button1.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				
						button1.setImageDrawable(getResources().getDrawable(R.drawable.ic_media_play));
				
			}
		});
  
    }
}