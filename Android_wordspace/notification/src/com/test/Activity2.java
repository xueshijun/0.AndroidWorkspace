package com.test;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class Activity2 extends Activity{
	
	protected void onCreate(Bundle savedInstanceState) 
	  {  
	    super.onCreate(savedInstanceState); 
	    
	    /* ����Toast */
	    Toast.makeText(Activity2.this,
	                      "ģ��MSN�л���¼״̬�ĳ���",
	                      Toast.LENGTH_SHORT
	                     ).show();
	    finish();
	  } 

}
