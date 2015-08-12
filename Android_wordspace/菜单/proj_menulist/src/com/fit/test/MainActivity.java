package com.fit.test;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends Activity {
	private boolean six;
	private boolean seven;
	private boolean eight;
	private boolean nine;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	
    	MenuInflater flater=getMenuInflater();
    	flater.inflate(R.menu.menu, menu);
    	return super.onCreateOptionsMenu(menu);
    }
    
   
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

    	int itemId=item.getItemId();
    	item.setChecked(!item.isChecked());
    	switch(itemId){
    	case R.id.item6:six=item.isChecked();break;
    	case R.id.item7:seven=item.isChecked();eight=!seven;nine=!seven;break;
    	case R.id.item8:eight=item.isChecked();seven=!eight;nine=!eight;break;
    	case R.id.item9:nine=item.isChecked();seven=!nine;eight=!nine;break;
    	
    	}
    	return super.onOptionsItemSelected(item);
    }
    @Override
    public void onOptionsMenuClosed(Menu menu) {
    	
    	Toast.makeText(this,six+":"+seven+":"+eight+":"+nine,Toast.LENGTH_LONG).show();
    	super.onOptionsMenuClosed(menu);
    }
}