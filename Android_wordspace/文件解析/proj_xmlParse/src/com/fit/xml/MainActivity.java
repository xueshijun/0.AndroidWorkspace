package com.fit.xml;

import java.io.File;
import java.util.List;

import com.fit.dao.XmlTool;
import com.fit.entity.Book;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.widget.TextView;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        
        TextView view=(TextView) findViewById(R.id.view);
        File file=new File(Environment.getExternalStorageDirectory(),"books.xml");
        List<Book> booklist=XmlTool.xmlTool(file);
        
        	for (Book book : booklist) {
			view.append("\r\n"+book.toString());
		}
    }
}