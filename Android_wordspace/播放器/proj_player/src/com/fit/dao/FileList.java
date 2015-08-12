package com.fit.dao;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FileList {
	
	
	public static List<Map<String,String>> fileList(String filepath){
		List<Map<String,String>> list=new ArrayList<Map<String,String>>();
		File dir=new File(filepath);       
		File[] files=dir.listFiles();
		
		for(int i=0;i<files.length;i++){
			File file=files[i];
			System.out.println(file==null);
		
			if(file.isDirectory() && file.listFiles()!=null){
				
				fileList(file.getAbsolutePath());
			}else if((file.getName()).endsWith(".mp3")){
				Map<String,String> map=new HashMap<String, String>();
				map.put("name",file.getName());
				map.put("fileSize",""+file.length());
				list.add(map);
			}
		}
		return list;
	}

}
