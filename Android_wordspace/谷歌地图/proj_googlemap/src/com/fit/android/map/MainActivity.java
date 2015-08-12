package com.fit.android.map;

import java.util.List;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

public class MainActivity extends MapActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        MapView view =(MapView) findViewById(R.id.map);
        Drawable icon=getResources().getDrawable(R.drawable.ic_menu_myplaces);
//        view.getController().setCenter(new GeoPoint(34361009, 109211998));
      
        MyOverlay laylist=new MyOverlay(icon, this);
        laylist.addPosition(34263840, 108955332, "�³ǹ㳡", "ʡ�������ڵ�");
        laylist.addPosition(34253847, 108984246, "���칫԰", "��ӣ��ȥ");
        laylist.addPosition(34217356, 108964462, "������", "��ַ��԰");
        
        List<Overlay> positions=view.getOverlays();
        positions.add(laylist);
        view.setBuiltInZoomControls(true);
    }

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
    
    
}