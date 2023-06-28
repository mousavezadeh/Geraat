package mousavi.Geraat;


import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

public class GpsActivity extends Activity {

	LocationManager locationManager;
	MyLocationListener mylistener;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gpslayout);
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		Criteria criteria = new Criteria();
		criteria.setAccuracy(Criteria.ACCURACY_COARSE);
		criteria.setCostAllowed(false);
		String provider = locationManager.getBestProvider(criteria, false);
		if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
			// TODO: Consider calling
			//    ActivityCompat#requestPermissions
			// here to request the missing permissions, and then overriding
			//   public void onRequestPermissionsResult(int requestCode, String[] permissions,
			//                                          int[] grantResults)
			// to handle the case where the user grants the permission. See the documentation
			// for ActivityCompat#requestPermissions for more details.
			return;
		}
		Location location = locationManager.getLastKnownLocation(provider);
		mylistener = new MyLocationListener();
	}

	private class MyLocationListener implements LocationListener {





		public void onLocationChanged(Location location)
		{
			// Initialize the location fields
			// latitude.setText("Latitude: "+String.valueOf(location.getLatitude()));
			//longitude.setText("Longitude: "+String.valueOf(location.getLongitude()));
			//provText.setText(provider + " provider has been selected.");

//            Toast.makeText(GpsActivity.this,  "Location changed!"+"\n"+String.valueOf(location.getLatitude())+"\n"+String.valueOf(location.getLongitude()),
//                      Toast.LENGTH_SHORT).show();
			Log.e("locationchange", "change");
			//GpsEnableCount++;
			//if (GpsEnable == false)GpsEnable=true;
			locationManager.removeUpdates(mylistener);
			Toast.makeText(GpsActivity.this,"سیتم مکان یابی فعال شد",Toast.LENGTH_SHORT).show();
			Intent intent = new Intent(getApplicationContext(),GeraatActivity.class);
			startActivity(intent);


		}

		public void onStatusChanged(String provider, int status, Bundle extras)
		{
			//Toast.makeText(GpsActivity.this, provider + "'s status changed to "+status +"!",
			//        Toast.LENGTH_SHORT).show();
			Log.e("status", "status");

		}



		public void onProviderEnabled(String provider) {
			//Toast.makeText(GpsActivity.this, "Provider " + provider + " enabled!",
			Toast.makeText(GpsActivity.this, "مکان یابی گوشی فعال گردید ، لطفا تا برقراری ارتباط کامل با ماهواره جهت تشخیص مکان چند لحظه شکیبا باشید",
					Toast.LENGTH_SHORT).show();
			//GpsEnable=true;

		}

		public void onProviderDisabled(String provider) {
			//Toast.makeText(GpsActivity.this, "Provider " + provider + " disabled!",
			Toast.makeText(GpsActivity.this, "مکان یابی گوشی توسط کاربر غیر فعال گردید",
					Toast.LENGTH_SHORT).show();


		}
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.gps, menu);
		return true;
	}

}
