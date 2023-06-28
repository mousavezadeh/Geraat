package mousavi.Geraat;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver 
{

	
	  @Override
	    public void onReceive(final Context context, final Intent intent) 
	  {
		  Log.e("onReceive","onReceive start");

		  try {
			  Intent serviceIntent = new Intent(context, MyService.class);
			 // if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
				//  context.startForegroundService(serviceIntent);
			 // }else
				  context.startService(serviceIntent);
		  }catch (Exception Error){Log.e("onReceive-Error",Error.getMessage());}
	  }

}
