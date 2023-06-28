package mousavi.Geraat;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Toast;

public class LocationTrack extends Service implements LocationListener{

	private final Context mcontext;
	boolean checkGPS=false;
	boolean checNetwork=false;
	boolean canGetLocation=false;
	Location loc;
	double lat;
	double lon;
	private static final long MIN_DISTANCE_CHANGE_FOR_UPDATE=10;
	private static final long MIN_TIME_BW_UPDATES = 1000*60*1;
	protected LocationManager locationmanager;
	
	public LocationTrack(Context mContext)
	{
		this.mcontext=mContext;
		
	}
	
	private Location getLocation()
	{
		try
		{
			locationmanager=(LocationManager) mcontext.getSystemService(LOCATION_SERVICE);
			
			checkGPS=locationmanager.isProviderEnabled(LocationManager.GPS_PROVIDER);
			checNetwork=locationmanager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
			
			if(!checkGPS && !checNetwork)
			{
				Toast.makeText(mcontext,"NO SERVICE PROVIDER IS AVAILABLE", Toast.LENGTH_SHORT).show();
			}
			else
			{
				this.canGetLocation=true;
				if (checkGPS)
				{
					locationmanager.requestLocationUpdates(locationmanager.GPS_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATE, this);
					if (locationmanager!=null)
					{
						loc=locationmanager.getLastKnownLocation(locationmanager.GPS_PROVIDER);
						if (loc!=null)
						{
							lat=loc.getLatitude();
							lon=loc.getLongitude();
						}
					}
				}
			}
		}
		catch (Exception e){e.printStackTrace();}
		return loc;
	}
	
	public double getlat()
	{
		if (loc!=null)
		{
			lat=loc.getLatitude();
		}
		return lat;
	}
	
	public double getlont()
	{
		if (loc!=null)
		{
			lon=loc.getLongitude();
		}
		return lon;
	}
	public boolean canGetLocation()
	{
		return this.canGetLocation;
	}
	public void stopListener()
	{
		if (locationmanager!=null) locationmanager.removeUpdates(LocationTrack.this);
	}
	
	public void onLocationChanged(Location arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onProviderEnabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
