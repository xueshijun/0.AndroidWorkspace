package com.yarin.android.MobileMap;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

public class MobileMap extends MapActivity
{
	private MapView			mMapView;
	private MapController	mMapController;
	private Geocoder		mGeocoder;
	private LocationOverlay	mLocationOverlay;
	private LocationManager mlocationManager;
	private Location mLocation;

    private static final int	Search		= Menu.FIRST;
	private static final int	SelectCity	= Menu.FIRST + 1;
	private static final int	ViewMode	= Menu.FIRST + 2;
	private static final int	Exit		= Menu.FIRST + 3;

	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		init();
		
	}


	protected boolean isRouteDisplayed()
	{
		return false;
	}
	
	public void init()
	{
		mMapView=(MapView)findViewById(R.id.MapView01);
        //ȡ��MapControllerʵ�������Ƶ�ͼ
		mMapController=mMapView.getController();
		
		mlocationManager=(LocationManager)getSystemService(Context.LOCATION_SERVICE);
		
		mMapView.setEnabled(true);
		mMapView.setClickable(true);
		//������ʾģʽ
		mMapView.setTraffic(false);
		mMapView.setSatellite(false);
		mMapView.setStreetView(true);
		//��������
		mMapView.setBuiltInZoomControls(true); 
		//���õ�ͼ�ȼ�
		mMapController.setZoom(12);
		
		mLocationOverlay=new LocationOverlay(this);
        List<Overlay> overlays=mMapView.getOverlays();
        overlays.add(mLocationOverlay);
        
        Criteria criteria =new Criteria();
        //����Ҫ��
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setCostAllowed(false);
        criteria.setPowerRequirement(Criteria.POWER_LOW);
        //ȡ��Ч����õ�criteria
        String provider=mlocationManager.getBestProvider(criteria, true);
        //�õ�������ص���Ϣ
        mLocation=mlocationManager.getLastKnownLocation(provider);

        mGeocoder = new Geocoder(this,Locale.getDefault());
        
        updateLocation(mLocation);
        
        mlocationManager.requestLocationUpdates(provider, 3000, 0,mLocationListener);
        
	}
	
	//���¶�λ
	public void updateLocation(Location location) 
	{
		if ( location == null )
		{
			return;
		}
		mLocationOverlay.setLocation(location);
		Double geoLat=location.getLatitude()*1E6;
        Double geoLng=location.getLongitude()*1E6;
        //����ת��Ϊint��
        GeoPoint point=new GeoPoint(geoLat.intValue(),geoLng.intValue());
        //��λ��ָ������
        mMapController.animateTo(point);
		
	}
	public MapController getMapController()
	{
		return mMapController;
	}
	
    private final LocationListener mLocationListener=new LocationListener()
    {
        public void onLocationChanged(Location location)
        {
        	updateLocation(location);
        }
        public void onProviderDisabled(String provider){}
        public void onProviderEnabled(String provider){}
        public void onStatusChanged(String provider,int status,Bundle extras){}
    };
    
    public boolean onCreateOptionsMenu(Menu menu)
	{
		super.onCreateOptionsMenu(menu);
		menu.add(0, Search, Menu.NONE, "�����ص�").setIcon(R.drawable.search);
		menu.add(0, SelectCity, Menu.NONE, "ѡ�����").setIcon(R.drawable.selectcity);
		menu.add(0, ViewMode, Menu.NONE, "��ͼģʽ").setIcon(R.drawable.viewmode);
		menu.add(0, Exit, Menu.NONE, "�˳�").setIcon(R.drawable.exit);
		return true;
	}
    
    public boolean onOptionsItemSelected(MenuItem item)
	{
		super.onOptionsItemSelected(item);
		switch (item.getItemId())
		{
			case Search:
				searchCity();
				return true;
			case SelectCity:
				selectCity();
				return true;
			case ViewMode:
				selectViewMode();
				return true;
			case Exit:
				this.finish();
				return true;
		}
		return true;
	}
    
    //ѡ�����
    public void selectCity()
    {
	    OnClickListener listener = new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which){
				Double geoLat=ConstData.cityCode[which][0]*1E6;
		        Double geoLng=ConstData.cityCode[which][1]*1E6;

		        mLocation.setLatitude(ConstData.cityCode[which][0]);
		        mLocation.setLongitude(ConstData.cityCode[which][1]);
		        mLocationOverlay.setLocation(mLocation);
		        GeoPoint point=new GeoPoint(geoLat.intValue(),geoLng.intValue());
		        //��λ��ָ������
		        mMapController.animateTo(point);
		        
			}
	    };
	    
    	ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ConstData.city);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
	    new AlertDialog.Builder(MobileMap.this)
        .setTitle("��ѡ�����")
        .setAdapter(adapter, listener)
        .show();
    }
    //ѡ���ͼģʽ
    public void selectViewMode()
    {
	    OnClickListener listener = new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which)
			{
				switch ( which )
				{
					case 0:
						mMapView.setTraffic(false);
						mMapView.setSatellite(false);
						mMapView.setStreetView(true);
						break;
					case 1:
						mMapView.setSatellite(false);
						mMapView.setStreetView(false);
						mMapView.setTraffic(true);
						break;
					case 2:
						mMapView.setStreetView(false);
						mMapView.setTraffic(false);
						mMapView.setSatellite(true);
						break;						
				}
		        
			}
	    };
	    
	    String[] menu={"�־�ģʽ","��ͨ����","���ǵ�ͼ"};	
	    new AlertDialog.Builder(MobileMap.this)
        .setTitle("��ѡ���ͼģʽ")
        .setItems(menu, listener)
        .show();
    }
    //��������
    public void searchCity()
    {
		//�Զ���һ��������ĶԻ�����TextView��EditText����
		final LayoutInflater factory = LayoutInflater.from(MobileMap.this);
		final View dialogview = factory.inflate(R.layout.dialog, null);
		//����TextView����ʾ��Ϣ
		((TextView) dialogview.findViewById(R.id.TextView01)).setText("������ͼ");
		//����EditText������ʼֵ
		((EditText) dialogview.findViewById(R.id.EditText01)).setText("������Ҫ���ҵĵ�ַ...");
		
		Builder builder = new Builder(MobileMap.this);
		builder.setTitle("���������");
		builder.setView(dialogview);
		builder.setPositiveButton(android.R.string.ok,
				new AlertDialog.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						//���ȷ��֮��
						String value = ((EditText) dialogview.findViewById(R.id.EditText01)).getText().toString().trim();
						if ( value != "" )
						{
							searchName(value);
						}
					}
				});
		builder.setNegativeButton(android.R.string.cancel,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
					}
				});
		builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
					public void onCancel(DialogInterface dialog) {
						dialog.cancel();
					}
				});
		builder.show();
    }
    
    public void searchName(String nameStr)
	{
		try
		{
			List<Address> addresses = mGeocoder.getFromLocationName(nameStr, 1);
			if (addresses.size() != 0)
			{
				Address address = addresses.get(0);
				GeoPoint geoPoint = new GeoPoint((int) (address.getLatitude() * 1E6), (int) (address.getLongitude() * 1E6));

				mLocation.setLatitude(address.getLatitude());
				mLocation.setLongitude(address.getLongitude());
				mLocationOverlay.setLocation(mLocation);

				mMapController.animateTo(geoPoint);
			}
		}
		catch (IOException e){}
	}
}
