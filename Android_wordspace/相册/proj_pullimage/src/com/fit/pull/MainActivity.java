package com.fit.pull;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
	ImageView image=null;
	private int[] pictures={R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.e,R.drawable.f,R.drawable.g,R.drawable.h,R.drawable.i,R.drawable.j};
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        getWindow().setBackgroundDrawableResource(R.drawable.abc);
        image=(ImageView) findViewById(R.id.image);
        image.setImageResource(pictures[0]);
        Gallery gallery=(Gallery) findViewById(R.id.gallery);
        gallery.setAdapter(new ImageAdapter(this));
        //gallery.setBackgroundResource(R.drawable.abc);
        gallery.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				image.setImageResource(pictures[arg2]);
			}
		});
        
    }
}