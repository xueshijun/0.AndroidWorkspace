package com.fit.photo;


import java.util.List;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
	int i=0;
	ImageView image;
	 ImageView image1,image2;
	FileList fileList=new FileList();
	List<Photo> photosList=null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        
        image=(ImageView)findViewById(R.id.photo);
   
        String path=Environment.getExternalStorageDirectory().getAbsolutePath();
        photosList=fileList.fileList(path);
     
       
    }
    @Override
    protected void onStart() {
    	super.onStart();
    	   
        if(photosList.size()==0){
        	Toast.makeText(MainActivity.this,"Œﬁ»Œ∫Œ’’∆¨£°",Toast.LENGTH_LONG).show();
        	MainActivity.this.finish();
        }
    	
    	 MyAdapter adapter=new MyAdapter(this, photosList);
         ListView view =(ListView) findViewById(R.id.list);
         view.setAdapter(adapter);
         view.setOnItemClickListener(new OnItemClickListener() {

 			@Override
 			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
 					long arg3) {
 				
 				Photo photo1=photosList.get(arg2);
 				Photo photos=(Photo) getApplication();
 				Bitmap photo=(Bitmap)photo1.getBmp();
 				photos.setBmp(photo);
 				
 				Intent intent=new Intent(MainActivity.this,PhotoActivity.class);
 				startActivity(intent);
 			}
 		});
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	MenuInflater flater=getMenuInflater();
    	flater.inflate(R.menu.menu1, menu);
    	return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

    	int menuId=item.getItemId();
    	switch(menuId){
    	case R.id.auto:
    		Intent intent1=new Intent(MainActivity.this,AutoActivity.class);
    		startActivity(intent1);
    		break;
    	case R.id.pull:
    		Intent intent=new Intent(MainActivity.this,GalleryActivity.class);
    		startActivity(intent);
    		break;
    	}
    	return super.onOptionsItemSelected(item);
    }
}