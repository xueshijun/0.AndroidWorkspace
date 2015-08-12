package com.fit.demo.dialog;
import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainAvtivity extends Activity {
	private final int DIALOG=0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button button=(Button) findViewById(R.id.button);
        button.setOnClickListener(new android.view.View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				MainAvtivity.this.showDialog(DIALOG);
			}
		});
        
    }
    
    @Override
    protected Dialog onCreateDialog(int id) {

    	Dialog dialog=null;
    	switch(id){
    	case DIALOG:
    		dialog=new Dialog(MainAvtivity.this);
    		dialog.setTitle("自定义对话框");
    		dialog.setContentView(R.layout.main2);
    	}
    	
    	return dialog;
    }
}