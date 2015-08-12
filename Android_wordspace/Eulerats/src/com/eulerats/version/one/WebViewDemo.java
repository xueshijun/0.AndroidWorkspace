package com.eulerats.version.one;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class WebViewDemo extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview); 
        String url="http://www.cs.bu.edu/~hwxi/.xshare/EULERATS/WEBDEV/YiHaoDian/search.html";
//	    String url="http://www.baidu.com";
	    WebView webView=(WebView)findViewById(R.id.webView1);
	    webView.setWebViewClient(new WebViewClient()); 
	    webView.loadUrl(url); 
    }
}
