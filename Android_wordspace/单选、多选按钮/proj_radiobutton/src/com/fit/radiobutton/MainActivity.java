package com.fit.radiobutton;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        RadioGroup group=(RadioGroup) findViewById(R.id.group);
        final RadioButton radio1=(RadioButton) findViewById(R.id.radio1);
        final RadioButton radio2=(RadioButton) findViewById(R.id.radio2);
        final RadioButton radio3=(RadioButton) findViewById(R.id.radio3);
        
        group.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if(checkedId==radio1.getId()){
					Toast.makeText(MainActivity.this,"您选择了"+radio1.getText().toString(),Toast.LENGTH_LONG).show();
				}
				if(checkedId==radio2.getId()){
					Toast.makeText(MainActivity.this,"您选择了"+radio2.getText().toString(),Toast.LENGTH_LONG).show();
				}
				if(checkedId==radio3.getId()){
					Toast.makeText(MainActivity.this,"您选择了"+radio3.getText().toString(),Toast.LENGTH_LONG).show();
				}
			}
		});
    }
}