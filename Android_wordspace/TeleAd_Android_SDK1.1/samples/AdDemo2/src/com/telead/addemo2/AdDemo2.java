package com.telead.addemo2;


import com.wooboo.adlib_android.AdListener;
import com.wooboo.adlib_android.WoobooAdView;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;

public class AdDemo2 extends Activity implements AdListener{
	String TAG = "Telead SDK AdDemo2";
	RelativeLayout layout;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		WoobooAdView ad = new WoobooAdView(this, // Context
				"b87f1107d94b4bf2814509a814186f46", // Telead_PID
				 Color.TRANSPARENT, // 广告背景颜色  默认为黑色
				Color.WHITE, // 广告文字颜色 默认为白色
				false, // 广告是否处于测试状态 为保证您的收益，请你在上传或者发布apk之前把该参数设置成false，默认为false
				45); // 广告请求时间间隔(以秒为单位 最短为45秒 最长为10分钟)
		ad.setAdListener(this);
		LayoutParams params = new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.WRAP_CONTENT);
		ad.setLayoutParams(params);
		layout = (RelativeLayout) findViewById(R.id.layout);
		layout.addView(ad);

	}

	@Override
	public void onFailedToReceiveAd(Object arg0) {
		Log.w(TAG, "onFailedToReceiveAd");
		
	}

	@Override
	public void onReceiveAd(Object arg0) {
		Log.i(TAG, "onReceiveAd");
		
	}
}