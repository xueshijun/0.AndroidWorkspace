package com.fit.spinner;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
	private String[] items;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
       items=getResources().getStringArray(R.array.spinner);
       
       ArrayAdapter<String> adapter=new ArrayAdapter<String>(MainActivity.this,R.layout.list, items);
       Spinner spn=(Spinner) findViewById(R.id.spinner);
       spn.setAdapter(adapter);
       
       spn.setOnItemSelectedListener(new OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			TextView view=(TextView) arg1.findViewById(R.id.view);
			String city=view.getText().toString();
			Toast.makeText(MainActivity.this,"ƒ˙—°‘Ò¡À"+city,Toast.LENGTH_LONG).show();
			
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			
		}
	});
    }
    
}