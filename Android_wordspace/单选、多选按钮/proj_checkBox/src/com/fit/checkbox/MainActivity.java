package com.fit.checkbox;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
	CheckBox box1;
	CheckBox box2;
	CheckBox box3;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        
         box1=(CheckBox) findViewById(R.id.box1);
         box2=(CheckBox) findViewById(R.id.box2);
         box3=(CheckBox) findViewById(R.id.box3);
        
       box1.setOnCheckedChangeListener(mm);
       box2.setOnCheckedChangeListener(mm);
       box3.setOnCheckedChangeListener(mm);
    }
     CheckBox.OnCheckedChangeListener mm =new OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			
			if(box1.isChecked()==true & box2.isChecked()==true & box3.isChecked()==true){
				Toast.makeText(MainActivity.this,box1.getText().toString()+box2.getText().toString()+box3.getText().toString(),
						Toast.LENGTH_LONG).show();
			}else if(box1.isChecked()==true & box2.isChecked()==true & box3.isChecked()==false){
				Toast.makeText(MainActivity.this,box1.getText().toString()+box2.getText().toString(),Toast.LENGTH_LONG).show();
			}else if(box1.isChecked()==false & box2.isChecked()==true & box3.isChecked()==true){
				Toast.makeText(MainActivity.this,box2.getText().toString()+box3.getText().toString(),Toast.LENGTH_LONG).show();	
			}else if(box1.isChecked()==true & box2.isChecked()==false & box3.isChecked()==true){
				Toast.makeText(MainActivity.this,box1.getText().toString()+box3.getText().toString(),Toast.LENGTH_LONG).show();
			}else if(box1.isChecked()==false & box2.isChecked()==false & box3.isChecked()==false){
				Toast.makeText(MainActivity.this,"您什么也没选！！",Toast.LENGTH_LONG).show();
			}else if(box1.isChecked()==true || box2.isChecked()==true || box3.isChecked()==false){
				Toast.makeText(MainActivity.this,"至少选择两项！！",Toast.LENGTH_LONG).show();
			}
		}
	};
}