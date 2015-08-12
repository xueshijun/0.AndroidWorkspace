package com.telead.addemo3;

import com.wooboo.adlib_android.AdListener;
import com.wooboo.adlib_android.ImpressionAdView;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.LinearLayout;

public class AdDemo3 extends Activity  implements AdListener{
	LinearLayout adlayout;
	String TAG = "Telead SDK AdDemo3";
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		adlayout = (LinearLayout) findViewById(R.id.adlayout);
		DisplayMetrics dm = new DisplayMetrics();
		dm = this.getResources().getDisplayMetrics();
		double density = dm.density;
		int adWidth = (int) (density * 320); // 广告宽度
		int adHeight = (int) (density * 48); // 广告高度
		int x = (dm.widthPixels - adWidth) >> 1;
		int y = dm.heightPixels - adHeight;

		ImpressionAdView adView = new ImpressionAdView(this, // Context
				adlayout, // View 及View的子类 即可
				x,  // 广告显示左上角 x 位置
				y, // 广告显示左上角 y 位置
				Color.WHITE, // 广告文字颜色
				false); //广告是否处于测试状态 为保证您的收益，请你在上传或者发布apk之前把该参数设置成false，默认为false
		
//		adView.show(); // 广告请求并展示 (广告只请求一次)
		adView.show(45); // 广告请求广告时间间隔 (以秒为单位 最短为45秒 最长为10分钟)
		adView.setAdListener(this);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// 关闭 渐入式 广告
		ImpressionAdView.close();
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