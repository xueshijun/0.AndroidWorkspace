package com.fit.photo;

import java.util.List;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.AdapterView.OnItemClickListener;

public class GalleryActivity extends Activity {
	ImageView image=null;
	FileList fileList=new FileList();
	//List<Map<String,Object>> photosList=null;
	List<Photo> photosList=null;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery);
        
        
        String path=Environment.getExternalStorageDirectory().getAbsolutePath();
        photosList=fileList.fileList(path);
        
        image=(ImageView) findViewById(R.id.image);
        System.out.println("====================="+photosList.size());
        image.setImageBitmap((Bitmap) photosList.get(0).getBmp());
        Gallery gallery=(Gallery) findViewById(R.id.gallery);
        gallery.setAdapter(new ImageAdapter(this,photosList));
        
        gallery.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				image.setImageBitmap((Bitmap) photosList.get(arg2).getBmp());
			}
		});
	}
}
