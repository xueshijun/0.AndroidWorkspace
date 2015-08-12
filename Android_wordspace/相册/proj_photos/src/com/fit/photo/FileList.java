package com.fit.photo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


public class FileList {
	
	public  List<Photo> fileList(String filepath){
		FileInputStream in=null;
		List<Photo> photoList=new ArrayList<Photo>();
		Photo photo=null;
		//List<Map<String,Object>> photosList=new ArrayList<Map<String,Object>>();
		
		Bitmap bitmap=null;
		File dir=new File(filepath);       
		File[] files=dir.listFiles();
		
		for(int i=0;i<files.length;i++){
			File file=files[i];
		
			if(file.isDirectory() && file.listFiles()!=null){
				
				fileList(file.getAbsolutePath());
			}else if((file.getName()).endsWith(".jpg")){
			try {
				//Map<String,Object> map=new HashMap<String, Object>();
				photo=new Photo();
				in=new FileInputStream(file);
				BitmapFactory.Options options=new BitmapFactory.Options();
				options.inSampleSize=2;
				bitmap=BitmapFactory.decodeStream(in,null,options);
//				map.put("name",file.getName());
//				map.put("photo", bitmap);
//				photosList.add(map);
				photo.setBmp(bitmap);
				photo.setName(file.getName());
				photoList.add(photo);
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}finally{
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			}
		}
		return photoList;
	}

}
