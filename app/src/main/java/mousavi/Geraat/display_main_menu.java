package mousavi.Geraat;


import static mousavi.Geraat.GeneralExtentions.Extention.GelLocationStatus;

import android.Manifest;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.Settings;

//import android.support.v4.app.ActivityCompat;
//import android.support.v4.app.NotificationCompat;
//import android.support.v4.content.FileProvider;

import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import org.json.JSONArray;
import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import Adapters.Adapter_Citys;
import Adapters.Adapter_Porsesh;


import mousavi.Dialogs.IButtonAction;
import mousavi.Dialogs.OkDialog;
import mousavi.Intent.IntenInstallPackage;
import mousavi.Notification.ErrorDuringOperationNotification;
import mousavi.Notification.NewVersionAvailabelNotification;
import mousavi.Notification.ReadingListNotFoundNotification;
import mousavi.Notification.RequestPermissionNotification;
import mousavi.Request.Api;
import mousavi.Request.ExecuteApi;
import mousavi.Request.GetAllListGheraatByCityRequestDto;
import mousavi.Request.GetAllListGheraatByCityResponse;
import mousavi.Request.GetCityByDeviceIdDto;
import mousavi.Request.GetCityByDeviceIdResponse;
import mousavi.Request.GetOfficerReportRequestDto;
import mousavi.Request.GetOfficerReportResponse;
import mousavi.Request.IResponse;
import mousavi.database.AddData;
import mousavi.database.Citys;
import mousavi.database.Dto.ReadingListDto;
import mousavi.database.Dto.ReadingListItemDto;
import mousavi.database.Insert.InsertReadingList;
import mousavi.database.Insert.InsertReadingListItem;
import mousavi.database.ListGheraat;
import mousavi.database.MobileAppVersion;
import mousavi.database.Select.FetchSelectOperation;
import mousavi.database.Select.SelectReadingList;
import mousavi.database.Select.SelectReadingListById;
import mousavi.database.Select.TitleValue.TitleValueDto;
import mousavi.database.databasetest;
import mousavi.database.pdl_input;
import mousavi.file.operation.file_operation;
import mousavi.message.dialogs;

public class display_main_menu extends Activity {
	pdl_input pdl_input_temp_closedoor;
	int versionCode;
	PackageInfo pInfo=null;
	int onresumcounter=0;
	MobileAppVersion OfficerData;
	String NameListForOutFile = "", SendListToServer = "", idlist = "", TextMessage = "", id_server_list = "";
	String ID_ChangeCity = "", Name_ChangeCity = "";
	ArrayList<ListGheraat> ArrayListListGheraat = new ArrayList<ListGheraat>();

	private static final String SOAP_ACTION2 = "http://tempuri.org/SendListGheraat";
	private static final String METHOD_NAME2 = "SendListGheraat";

	private static final String SOAP_ACTION1 = "http://tempuri.org/GetAllListGheraatByCity";
	private static final String METHOD_NAME1 = "GetAllListGheraatByCity";
	JSONArray JsonListSendToServer;
	private static final String SOAP_ACTION = "http://tempuri.org/GetListMobile";
	private static final String METHOD_NAME = "GetListMobile";

	private static final String SOAP_ACTION_GetCityByDeviceId = "http://tempuri.org/GetCityByDeviceId";
	private static final String METHOD_NAME_GetCityByDeviceId = "GetCityByDeviceId";

	private static final String SOAP_ACTION_ChangeDefaultCity = "http://tempuri.org/ChangeDefaultCity";
	private static final String METHOD_NAME_ChangeDefaultCity = "ChangeDefaultCity";

	private static final String SOAP_ACTION_GetOfficerReport = "http://tempuri.org/GetOfficerReport";
	private static final String METHOD_NAME_GetOfficerReport = "GetOfficerReport";

	private static final String NAMESPACE = "http://tempuri.org/";
	private static final String URL = "https://pay.abfakhz.ir/MobileWebService.asmx";
	private String js = "", AsyncTaskType = "";
	String IDFile = "", NameList = "";
	dialogs dialogs;
	public static final int DIALOG_DOWNLOAD_PROGRESS = 0;
	private ProgressDialog mProgressDialog;
	final Context context = this;
	String DataOfActiveList[];
	//private io.socket.client.Socket  socket;
	private Typeface font, Fontawesome, TitrB;
	private IntentFilter myFilter = new IntentFilter("VersionInfo");
	private boolean receiversRegistered;
	MyReceiver myreciver;
	BroadcastReceiver DownloadReciver;
	databasetest da;
	boolean DateState=false;









	private BroadcastReceiver broadcastReceiver = new BroadcastReceiver()
	{
		@Override
		public void onReceive(Context context, Intent intent)
		{
		    String Action="";
            Log.e("onReceive----", "onReceive");
			da = databasetest.getInstance(context);
			Log.e("Fucking onReceive", "onReceive broadcastReceiver Update UI");
			try {
				Bundle extras = intent.getExtras();
				if (extras!=null) {
                     Action = extras.getString("Action");
                }

				Log.e("Action", Action);
				//LinearLayout Versionlay = (LinearLayout) findViewById(R.id.layout_version);

				TextView txtMessage = (TextView) findViewById(R.id.textView8);
				OfficerData = da.Get_OfficerData();
				LinearLayout Gherat=(LinearLayout) findViewById(R.id.l_gherratt);
				LinearLayout Gherat_Darbasteh=(LinearLayout) findViewById(R.id.l_gherrattdarbasteh);
				LinearLayout Choice_City=(LinearLayout) findViewById(R.id.layout_version);
				LinearLayout Get_List=(LinearLayout) findViewById(R.id.l_GetListOnline);
                if (Action.compareTo("ErrorDateTime")==0)
                {
                    Get_List.setVisibility(View.GONE);
                    Choice_City.setVisibility(View.GONE);
                    Gherat_Darbasteh.setVisibility(View.GONE);
                    Gherat.setVisibility(View.GONE);
                }else  if (Action.compareTo("ErrorImei")==0)
                {
					Get_List.setVisibility(View.GONE);
					Choice_City.setVisibility(View.GONE);
					Gherat_Darbasteh.setVisibility(View.GONE);
					Gherat.setVisibility(View.GONE);

                }
                else
				{ Get_List.setVisibility(View.VISIBLE);
					Gherat_Darbasteh.setVisibility(View.VISIBLE);
					Gherat.setVisibility(View.VISIBLE);
					if (OfficerData.get_MultiOfficer().compareTo("1") == 0)
						Choice_City.setVisibility(View.VISIBLE);
				}


                file_operation fileopp=new file_operation();
				PackageInfo CurrentPackageInfo;
				CurrentPackageInfo = fileopp.CurrentPackageInfo(getApplicationContext());
				txtMessage.setText
						(
								"شناسه دستگاه" + "  " + da._Imei.toUpperCase() +
										"\n" + "نسخه " + " " +CurrentPackageInfo.versionName +
										"\n" + "شهر: " + OfficerData.get_CityName() +
										"\n" + "مامور: " + OfficerData.get_OfficerName());

			} catch (Exception error) {
				Log.e("Error in Update UI", error.toString());
			}
		}
	};

	public void registerReceivers() {
		// Only register if not already registered
		if (!receiversRegistered)
		{
			IntentFilter intentFilter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
			this.registerReceiver(myreciver, intentFilter);
			IntentFilter ir = new IntentFilter("mousavi.Geraat");
			registerReceiver(broadcastReceiver, ir);
			receiversRegistered = true;
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.e("onDestroy", "onDestroy");
		//da.database_close1();
		if (receiversRegistered) {
		    if (myreciver!=null)
			unregisterReceiver(myreciver);
			unregisterReceiver(broadcastReceiver);
		}

	}



	private boolean isMyServiceRunning(Class<?> serviceClass) {
		ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
		for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
			if (serviceClass.getName().equals(service.service.getClassName())) {
				return true;
			}
		}
		return false;
	}
	@Override
	protected void onResume() {
		super.onResume();
		 onresumcounter++;
		Log.e("onResume", "onResume"+"-"+String.valueOf(onresumcounter));
		registerReceivers();

		if (onresumcounter==10001)
		{
			Intent serviceIntent = new Intent(context, MyService.class);
			//if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
			//	context.startForegroundService(serviceIntent);
			//}else
				context.startService(serviceIntent);
			//context.startService(serviceIntent);
		}
//        if (receiversRegistered)
//        {
//            Intent serviceIntent = new Intent(context, MyService.class);
//            context.startService(serviceIntent);
//        }
		//da=databasetest.getInstance();
		//super.onResume();

	}

	@Override
	protected void onRestart() {
		Log.e("onRestart", "onRestart");
		registerReceivers();
		super.onRestart();
	}
	@Override
	protected void onPause() {

		super.onPause();
		Log.e("onPause", "onPause-Broadcast");
	}

	@Override
	protected void onStop()
    {
		onresumcounter=10000;
		super.onStop();
		Log.e("onStop", "onStop-Broadcast");
//		if (receiversRegistered)
//		{
//			unregisterReceiver(myreciver);
//			unregisterReceiver(broadcastReceiver);
//			receiversRegistered = false;
//		}
	}

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions,
                                           int[] grantResults) {

	    if (CheckPerm()) {registerReceivers();
	    }

    }


    public  boolean CheckPerm()
    {

        if ((ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
        &&
        (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
        &&
        (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        &&
        (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED)
        &&
         (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED))

        {
            return  false;
        }
        else return true;
    }

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainmenu);
		StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
		StrictMode.setVmPolicy(builder.build());
		Log.e("onCreate", "onCreate");
		Log.e("Envi", Environment.getExternalStorageDirectory().getPath());



        //region requestPermissions

		if (android.os.Build.VERSION.SDK_INT >= 23)
		{
			ActivityCompat.requestPermissions(display_main_menu.this,
					new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
							Manifest.permission.WRITE_EXTERNAL_STORAGE,
							Manifest.permission.ACCESS_FINE_LOCATION,
							Manifest.permission.READ_PHONE_STATE,
							Manifest.permission.CAMERA,
							Manifest.permission.REQUEST_INSTALL_PACKAGES
					},
					1);
		}
//endregion


		try {
			pInfo = getApplicationContext().getPackageManager().getPackageInfo(getApplicationContext().getPackageName(), 0);
		} catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
		}

		if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
			versionCode = (int) pInfo.getLongVersionCode(); // avoid huge version numbers and you will be ok
		} else {
			//noinspection deprecation
			versionCode = pInfo.versionCode;
		}

        //region DEfine Variable
		font = Typeface.createFromAsset(getAssets(), "Fonts/byekan.ttf");
		Fontawesome = Typeface.createFromAsset(getAssets(), "Fonts/fa-solid-900.ttf");

		TextView txtMessage = (TextView) findViewById(R.id.textView8);
		TextView txtbargozari = (TextView) findViewById(R.id.textView2);
		TextView txt_daryaft_list_gheraat = (TextView) findViewById(R.id.textView6);
		TextView txt_gheraat = (TextView) findViewById(R.id.textView3);
		TextView txt_ersal_file_gheraat = (TextView) findViewById(R.id.textView5);
		TextView txt_khoroj = (TextView) findViewById(R.id.textView41);
		TextView txt_khoroj1 = (TextView) findViewById(R.id.textView4);
		TextView txt_sabte_darbasteh = (TextView) findViewById(R.id.textView1330);
        TextView txt_Officer_report = (TextView) findViewById(R.id.textView5020);


		TextView icon_ersalonline = (TextView) findViewById(R.id.textView550);
		TextView icon_daryaft_list_gheraat = (TextView) findViewById(R.id.textView660);
		TextView icon_gheraat = (TextView) findViewById(R.id.textView330);
		TextView icon_khoroj = (TextView) findViewById(R.id.textView440);
		TextView icon_history = (TextView) findViewById(R.id.textView441);
		TextView icon_bargozari = (TextView) findViewById(R.id.textView220);
		TextView icon_gheraatedarbasteh = (TextView) findViewById(R.id.textView3340);
        TextView icon_officer_report = (TextView) findViewById(R.id.textView5501);

		icon_gheraatedarbasteh.setTypeface(Fontawesome);
		icon_ersalonline.setTypeface(Fontawesome);
		icon_daryaft_list_gheraat.setTypeface(Fontawesome);
		icon_gheraat.setTypeface(Fontawesome);
		icon_khoroj.setTypeface(Fontawesome);
		icon_history.setTypeface(Fontawesome);
		icon_bargozari.setTypeface(Fontawesome);
		icon_officer_report.setTypeface(Fontawesome);
		txt_sabte_darbasteh.setTypeface(font);
		txtMessage.setTypeface(font);
		txtbargozari.setTypeface(font);
		txt_daryaft_list_gheraat.setTypeface(font);
		txt_gheraat.setTypeface(font);
		txt_ersal_file_gheraat.setTypeface(font);
		txt_khoroj.setTypeface(font);
		txt_khoroj1.setTypeface(font);
		txt_Officer_report.setTypeface(font);

		icon_ersalonline.setText(new String(new char[]{0xf093}));
		icon_daryaft_list_gheraat.setText(new String(new char[]{0xf019}));
		icon_gheraatedarbasteh.setText(new String(new char[]{0xf573}));
		icon_gheraat.setText(new String(new char[]{0xf573}));
		icon_khoroj.setText(new String(new char[]{0xf410}));
		icon_history.setText(new String(new char[]{0xf46d}));
		icon_bargozari.setText(new String(new char[]{0xf381}));
		icon_officer_report.setText(new String(new char[]{0xf573}));

		//endregion
        PackageInfo CurrentPackageInfo;

        file_operation file_opp = new file_operation();
        CurrentPackageInfo = file_opp.CurrentPackageInfo(getApplicationContext());

        if (CheckPerm()) {

                    //region imei
            try {
                TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {

                    return;
                }
                String tempimei = telephonyManager.getDeviceId();
                if (tempimei != null) {
                    da._Imei = telephonyManager.getDeviceId();
                } else {
                    da._Imei = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
                }

            } catch (Exception err) {
                da._Imei = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
            } finally {

            }
//endregion


            file_opp.MakeDirectory("/ABFA/Gallery/Pic");
            file_opp.MakeDirectory("/ABFA/Gallery/Video");
            file_opp.MakeDirectory("/ABFA/Out");
            file_opp.MakeDirectory("/ABFA/List");
            file_opp.MakeDirectory("/ABFA/DataBase");
            file_opp.MakeDirectory("/ABFA/Update");
            file_opp.copy_database_file(Environment.getExternalStorageDirectory() + "/ABFA/DataBase/abfapdl_database", getApplicationContext());
            ArrayList<AddData> Version = new ArrayList<AddData>();

            da = databasetest.getInstance(this.getApplicationContext());
            da.SetupDataBase();
            Version = da.Select_Version();
            if (Version.size() > 0) {
                if (CurrentPackageInfo.versionCode > Float.valueOf(Version.get(0).get_Value())) {
                    da.Update_Version(String.valueOf(CurrentPackageInfo.versionCode));
                    Version = da.Select_Version();
                }

                OfficerData = da.Get_OfficerData();
                txtMessage.setText(
                        "شناسه دستگاه" + "  " + da._Imei.toUpperCase() +
                                "\n" + "نسخه " + " " + CurrentPackageInfo.versionName+
                                "\n" + "شهر: " + OfficerData.get_CityName() +
                                "\n" + "مامور: " + OfficerData.get_OfficerName());
            }
            myreciver = new MyReceiver();
        }
        else
        {
			RequestPermissionNotification requestPermissionNotification=new RequestPermissionNotification();
			GeneralExtentions.Extention.SendNotification(requestPermissionNotification,context);

        }




		//region MainMenu

		LinearLayout L_Gherrat = (LinearLayout) findViewById(R.id.l_gherratt);
		L_Gherrat.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				final GeneralExtentions.Extention.apkinformation apkinformation=GeneralExtentions.Extention.CheckNewVersionWithinfo(context);
				if (apkinformation!=null)
				{
					IntenInstallPackage intenInstallPackage=new IntenInstallPackage(apkinformation.uri);
					startActivityForResult(intenInstallPackage.Getintent(), 12);
					return;
				}

						GeneralExtentions.Extention.LocationInfo locationInfo=GelLocationStatus(context);
						if (!locationInfo.status)
						{
							Toast.makeText(getApplicationContext(), "خطا در ارتباط با جی پی اس", Toast.LENGTH_SHORT).show();
							return;
						}

						if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
								ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
							return;
						}

							Intent intent = new Intent(getApplicationContext(),GeraatActivity.class);
							startActivity(intent);

					return;


			//new version available


			}
		});
		//-----------------------------------------------------------------------------
		LinearLayout L_GherratDarbasteh=(LinearLayout)findViewById(R.id.l_gherrattdarbasteh);
		L_GherratDarbasteh.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				pdl_input_temp_closedoor=null;
				TitrB=Typeface.createFromAsset(getAssets(), "Fonts/titrb.ttf");
				final Dialog  finddialog = new Dialog(context);
				finddialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
				finddialog.setContentView(R.layout.dialog_closedoor);
				finddialog.setCancelable(false);
				TextView DialogTitle=(TextView)finddialog.findViewById(R.id.textView1);
				final EditText txt_closedoor_serialno=(EditText)finddialog.findViewById(R.id.txt_closedoor_serialno);
				final EditText txt_closedoor_readnumnow=(EditText)finddialog.findViewById(R.id.txt_illigalbranch_comment);
				final TextView txt_closedoor_name_title=(TextView)finddialog.findViewById(R.id.closedoor_txt_name_title);
                final TextView txt_closedoor_name=(TextView)finddialog.findViewById(R.id.closedoor_txt_name);
                final TextView txt_closedoor_oldnum=(TextView)finddialog.findViewById(R.id.closedoor_txt_oldnum);
				final TextView txt_closedoor_oldnum_title=(TextView)finddialog.findViewById(R.id.closedoor_txt_oldnum_title);
				final TextView txt_closedoor_newnum_title=(TextView)finddialog.findViewById(R.id.closedoor_txt_new_num_title);
				final TextView txt_closedoor_serialno_title=(TextView)finddialog.findViewById(R.id.closedoor_txt_serialno_title);
                final LinearLayout layout_closedoor_name=(LinearLayout)finddialog.findViewById(R.id.closedoor_ln_name);
                final LinearLayout layout_closedoor_oldnum=(LinearLayout)finddialog.findViewById(R.id.closedoor_ln_oldnum);
				Button btn_ok  =(Button)finddialog.findViewById(R.id.btn_illigalbranch_ok);
				final Button btn_save  =(Button)finddialog.findViewById(R.id.btn_illigalbranch_save);
				Button btn_cancel  =(Button)finddialog.findViewById(R.id.btn_illigalbranch_cancel);



				DialogTitle.setTypeface(TitrB);
				btn_ok.setTypeface(font);
				btn_save.setTypeface(font);
				btn_cancel.setTypeface(font);
				txt_closedoor_serialno_title.setTypeface(font);
				txt_closedoor_newnum_title.setTypeface(font);
				txt_closedoor_name.setTypeface(font);
				txt_closedoor_name_title.setTypeface(font);
				txt_closedoor_oldnum_title.setTypeface(font);
				txt_closedoor_oldnum.setTypeface(font);
				txt_closedoor_serialno.setTypeface(font);
				txt_closedoor_readnumnow.setTypeface(font);


				btn_ok.setOnClickListener(new OnClickListener()
				{
					@Override
					public void onClick(View v) {
						try {
							 pdl_input_temp_closedoor = da.select_data_from_database_closedoor(OfficerData.get_CityId(),txt_closedoor_serialno.getText().toString());
							if (pdl_input_temp_closedoor != null )
							{
                                layout_closedoor_name.setVisibility(View.VISIBLE);
                                layout_closedoor_oldnum.setVisibility(View.VISIBLE);
                                txt_closedoor_name.setText(pdl_input_temp_closedoor.get_name_moshtarek());
                                txt_closedoor_oldnum.setText( String.valueOf(pdl_input_temp_closedoor.get_meghdare_ghabli_int()));
								btn_save.setEnabled(true);
								InputMethodManager imm=(InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
								imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY,0);
//								temp.set_karkard_feli(txt_closedoor_readnumnow.getText().toString());
//								da.update_gheraat_And_Update_SendToServer_CloseDoor(temp);
//								txt_closedoor_serialno.setText("");
//								txt_closedoor_readnumnow.setText("");
//								txt_closedoor_serialno.requestFocus();


							}
							else
							{
								btn_save.setEnabled(false);
								Toast.makeText(getApplicationContext(), "اشتراک مورد نظر یافت نشد", Toast.LENGTH_SHORT).show();
							}

						}catch (Exception error){
							Toast.makeText(getApplicationContext(), "خطا در انجام عملیات لطفا مجددا تلاش کنید", Toast.LENGTH_SHORT).show();
							Log.e("closedoor",error.toString());
						}
					}
				});

			btn_save.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View view) {
					try {


						int newnum = 0;
						newnum = Integer.valueOf(txt_closedoor_readnumnow.getText().toString());
						if (newnum > 0) {
							pdl_input_temp_closedoor.set_karkard_feli(txt_closedoor_readnumnow.getText().toString());
							da.update_gheraat_And_Update_SendToServer_CloseDoor(pdl_input_temp_closedoor);
							txt_closedoor_serialno.setText("");
							txt_closedoor_readnumnow.setText("");
							layout_closedoor_name.setVisibility(View.GONE);
							layout_closedoor_oldnum.setVisibility(View.GONE);
							txt_closedoor_serialno.requestFocus();
							btn_save.setEnabled(false);
							Toast.makeText(getApplicationContext(), "ثبت با موفقیت انجام شد", Toast.LENGTH_SHORT).show();
						} else
							Toast.makeText(getApplicationContext(), "خطا در ثبت اطلاعات", Toast.LENGTH_SHORT).show();
					}catch (Exception Err){Toast.makeText(getApplicationContext(), "خطا در انجام عملیات", Toast.LENGTH_SHORT).show();}
				}
			});
				btn_cancel.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						InputMethodManager imm=(InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
						imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY,0);finddialog.dismiss();
					}
				});
				finddialog.show();

				txt_closedoor_serialno.requestFocus();

			}
		});
		// ----------------------------------------------------------------------------
		LinearLayout L_Exit=(LinearLayout)findViewById(R.id.l_exit);
	
		L_Exit.setOnClickListener(new OnClickListener() {

			public void onClick(View v)
			{
				finish();
			}
		});
		



		LinearLayout ReportOnline = (LinearLayout) findViewById(R.id.l_reportOnline);
		ReportOnline.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view)
			{

//				ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//				if (cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnected() || cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnected())
//				{
//					//connected
//					AsyncTaskType = "ReportOnline";
//					new DownloadFileAsync_test().execute("");
//				} else {
//					//not connected
//					Toast.makeText(context,"خطا در اتصال به شبکه ،لطفا وضعیت ارتباط با شبکه را بررسی کنید", Toast.LENGTH_LONG).show();
//				}

				final GeneralExtentions.Extention.apkinformation apkinformation=GeneralExtentions.Extention.CheckNewVersionWithinfo(context);
				if (apkinformation==null)
				{
					Api api = new Api();
					SoapObject res=api.GetOfficerReport(new GetOfficerReportRequestDto(da._Imei));
					new ExecuteApi(GetOfficerReportResponse.class.getName(),display_main_menu.this).execute(res);
					return;
				}


				final OkDialog okDialog=new OkDialog(context,R.layout.hilo_message,R.id.btn_ok);
				okDialog.Ok(new IButtonAction() {
					@Override
					public void Action() {
						IntenInstallPackage intenInstallPackage=new IntenInstallPackage(apkinformation.uri);
						startActivityForResult(intenInstallPackage.Getintent(), 12);
						okDialog.Hide();
					}
				}).Build();
				okDialog.Show();



			}
		});
		LinearLayout l_GetListOnline=(LinearLayout) findViewById(R.id.l_GetListOnline);
		
		l_GetListOnline.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
					Api api = new Api();
					SoapObject res1=api.GetAllListGheraatByCity(new GetAllListGheraatByCityRequestDto(da._Imei,versionCode));
					new ExecuteApi(GetAllListGheraatByCityResponse.class.getName(),display_main_menu.this).execute(res1);
			}
		});

		ImageButton btn_goz = (ImageButton) findViewById(R.id.btn_gozaresh);
		btn_goz.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent intent = new Intent(getApplicationContext(),
						Tanzimat.class);
				startActivity(intent);

				
			}
		});



		LinearLayout Versionlay = (LinearLayout) findViewById(R.id.layout_version);
		Versionlay.setOnClickListener(new OnClickListener()
		{
			public void onClick(View arg0) 
			{
				final GeneralExtentions.Extention.apkinformation apkinformation=GeneralExtentions.Extention.CheckNewVersionWithinfo(context);
				if (apkinformation==null)
				{
					if (GeneralExtentions.Extention.isNetworkConnected(context))
					{
						Api api = new Api();
						SoapObject res1=api.GetCityByDeviceId(new GetCityByDeviceIdDto(da._Imei));
						new ExecuteApi(GetCityByDeviceIdResponse.class.getName(),display_main_menu.this).execute(res1);
						AsyncTaskType = "GetCityByDeviceId";
						new DownloadFileAsync_test().execute("");
						return;
					}
					Toast.makeText(context,"خطا در اتصال به شبکه ،لطفا وضعیت ارتباط با شبکه را بررسی کنید", Toast.LENGTH_LONG).show();
				}

				final OkDialog okDialog=new OkDialog(context,R.layout.hilo_message,R.id.btn_ok);
				okDialog.Ok(new IButtonAction() {
					@Override
					public void Action() {
						IntenInstallPackage intenInstallPackage=new IntenInstallPackage(apkinformation.uri);
						startActivityForResult(intenInstallPackage.Getintent(), 12);
						okDialog.Hide();
					}
				}).Build();


			}
		});

		//endregion

		try {
			ArrayList<mousavi.database.Select.TitleValue.TitleValueDto> p=da.select_data_for_del_pic_list();
			for(TitleValueDto a:p) file_opp.DeleteFile(a.title);
			da.DeletePic(p);
		}catch (Exception Error){}






	}

	@Override
	public void onActivityResult(int requestCode,int resultCode,Intent data) 
	{
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode==12)
		{
			this.finish();
			if (resultCode==Activity.RESULT_CANCELED)
			{

			}
		}
	}

	  
	  public void startService(View view) {
	      startService(new Intent(getBaseContext(), MyService.class));
	   }
	 
	  public void stopService(View view) {
	      stopService(new Intent(getBaseContext(), MyService.class));
	   }
	









	public void Call_Report()
	{
		try
		{
			Log.e("Start", "Call_Report");
			@SuppressWarnings("unused")
			SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME_GetOfficerReport);
			Log.e("imei", da._Imei);
			request.addProperty("Imei", da._Imei);
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			envelope.dotNet = true;
			envelope.setOutputSoapObject(request);
			HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
			androidHttpTransport.call(SOAP_ACTION_GetOfficerReport, envelope);
			Object result = (Object) envelope.getResponse();
			js = result.toString();
			Log.e("GetOfficerReport", js.toString());
			StringtoJsonReportOnline(js);
		}
		catch (Exception e)
		{ js = e.toString();Log.e("catch", e.toString()); }
	}

	public void call1()
	{
		try
		{
			Log.e("Start", "call");
			@SuppressWarnings("unused")
			SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME1);
			Log.e("imei", da._Imei);
			Log.e("imei",String.valueOf(versionCode));
			request.addProperty("CityId", da._Imei);
			request.addProperty("Version", versionCode);

			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			envelope.dotNet = true;
			envelope.setOutputSoapObject(request);
			HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
			androidHttpTransport.call(SOAP_ACTION1, envelope);
			Object result = (Object) envelope.getResponse();
			js = result.toString();
			Log.e("js", js.toString());
			if (((js.contains("ListOrDeviceNotFound"))||(js.contains("Error"))))
				{ArrayListListGheraat.clear();
					if(js.contains("ListOrDeviceNotFound")) {
						ReadingListNotFoundNotification readingListNotFoundNotification=new ReadingListNotFoundNotification();
						GeneralExtentions.Extention.SendNotification(readingListNotFoundNotification,context);
					}
						else if (js.startsWith("Error-Version")) {
						NewVersionAvailabelNotification newVersionAvailabelNotification=new NewVersionAvailabelNotification();
						GeneralExtentions.Extention.SendNotification(newVersionAvailabelNotification,context);
					}
						else if(js.contains("Error")) {
								ErrorDuringOperationNotification errorDuringOperationNotification=new ErrorDuringOperationNotification();
								GeneralExtentions.Extention.SendNotification(errorDuringOperationNotification,context);
					}
				return;
				}
			StringtoJson(js);
		}
		catch (Exception e)
		{
			ErrorDuringOperationNotification errorDuringOperationNotification=new ErrorDuringOperationNotification(e.getMessage());
			GeneralExtentions.Extention.SendNotification(errorDuringOperationNotification,context);
			js = "Eror";Log.e("catch", e.toString());
		}
	}
	public void Call_GetCityByDeviceId()
	{
		try 
		{
			Log.e("Start", "Call_GetCityByDeviceId");
			@SuppressWarnings("unused")
			SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME_GetCityByDeviceId);
			Log.e("imei", da._Imei);
			request.addProperty("Imei", da._Imei);
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
					SoapEnvelope.VER11);

			envelope.dotNet = true;
			envelope.setOutputSoapObject(request);
			HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
			androidHttpTransport.call(SOAP_ACTION_GetCityByDeviceId, envelope);
			Object result = (Object) envelope.getResponse();
			js = result.toString();
			if (js.contains("Error"))
			{
				ErrorDuringOperationNotification errorDuringOperationNotification=new ErrorDuringOperationNotification(js);
				GeneralExtentions.Extention.SendNotification(errorDuringOperationNotification,context);
			}
			Log.e("js", js.toString());

			//StringtoJson(js);
		}
		catch (Exception e)

		{
			ErrorDuringOperationNotification errorDuringOperationNotification=new ErrorDuringOperationNotification(e.getMessage());
			GeneralExtentions.Extention.SendNotification(errorDuringOperationNotification,context);
			js = e.toString();
			Log.e("catch", e.toString());
		}

	}

	public void Call_ChangeDefaultCity()
	{
		try 
		{
			
			Log.e("Start", "Call_ChangeDefaultCity");
			@SuppressWarnings("unused")
			SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME_ChangeDefaultCity);
			Log.e("imei", da._Imei);
			Log.e("CityID", ID_ChangeCity);
			request.addProperty("Imei", da._Imei);
			request.addProperty("ID", ID_ChangeCity);
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
					SoapEnvelope.VER11);

			envelope.dotNet = true;
			envelope.setOutputSoapObject(request);
			HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
			androidHttpTransport.call(SOAP_ACTION_ChangeDefaultCity, envelope);

			Object result = (Object) envelope.getResponse();
			js = result.toString();
			Log.e("js", js.toString());
			
			//StringtoJson(js);

		} 
		catch (Exception e)

		{
			js = e.toString();
			Log.e("catch", e.toString());
		}

	}

	public void SendList()
	{
		try {
			Log.e("Start", "call");
			@SuppressWarnings("unused")
			SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME2);
			Log.e("Out",da.select_data_for_output_pdl_device_string_Json(idlist));
			request.addProperty("JsonArrayList", da.select_data_for_output_pdl_device_string_Json(idlist));

			request.addProperty("id_list", idlist);
			request.addProperty("imei", da._Imei);
			request.addProperty("JsonArrayListPeymayesh", da.select_data_for_peymayesh_device_string_Json(idlist));
			request.addProperty("JsonArrayListilligalBranch", da.select_data_for_illigalbranch_device_string_Json());

			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
					SoapEnvelope.VER11);

			envelope.dotNet = true;
			envelope.setOutputSoapObject(request);


			HttpTransportSE androidHttpTransport = new HttpTransportSE(URL,60000);
			androidHttpTransport.call(SOAP_ACTION2, envelope);

			Object result = (Object) envelope.getResponse();
			// Log.e("js", "make result start to string");
			js = result.toString();
			SendListToServer = js;
			Log.e("js", js.toString());
			// StringtoJson(js);

		} catch (Exception e)

		{
			
			js = e.toString();
			Log.e("catch", e.toString());
		}

	}

	void StringtoJson(String Str)
	{
		Log.e("StringtoJsonStart", "Start String to jsonArray");
		try {
			ArrayListListGheraat.clear();
			// JSONObject jsonObject = new JSONObject(Ob)
			JSONArray postalCodesItems = new JSONArray(Str);
			for (int i = 0; i < postalCodesItems.length(); i++) {
				JSONObject postalCodesItem = postalCodesItems.getJSONObject(i);
				ListGheraat agahi = new ListGheraat();

				agahi.Set_idlistserver(postalCodesItem.getString("id"));
				agahi.Set_NameList(postalCodesItem.getString("name_list"));
				agahi.Set_Roozkar(postalCodesItem.getString("day_"));
				agahi.Set_Comment(postalCodesItem.getString("Comment"));

				if ((postalCodesItem.getString("name_list").compareTo("null") != 0)
						&& (postalCodesItem.getString("day_").compareTo("null") != 0))
					ArrayListListGheraat.add(agahi);
				Log.e("add", "True");
			}
		} catch (Exception e) {
			Log.e("error in json object", e.toString());
		}
	}

	void StringtoJsonReportOnline(String Str)
	{
		Log.e("StringtoJsonStart", "Start String to jsonArray");
		try {
			ArrayListListGheraat.clear();
			JSONArray postalCodesItems = new JSONArray(Str);

			for (int i = 0; i < postalCodesItems.length(); i++)
			{

						JSONObject postalCodesItem = postalCodesItems.getJSONObject(i);
						ListGheraat agahi = new ListGheraat();
						agahi.Set_NameList(postalCodesItem.getString("date_"));
						agahi.Set_Roozkar(postalCodesItem.getString("PeymayeshCount"));
                        agahi.Set_Comment(postalCodesItem.getString("IllegalCount"));
						agahi.Set_Doreh(postalCodesItem.getString("readCount"));
							ArrayListListGheraat.add(agahi);

						}
            Log.e("Report list len",String.valueOf(ArrayListListGheraat.size()));
		} catch (Exception e) {
			Log.e("error in json object", e.toString());
		}
	}
	


	class DownloadFileAsync_test extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			if (AsyncTaskType == "OutPutFile")
				TextMessage="";
			if (AsyncTaskType == "GetListMobile")
				TextMessage="در حال انتقال لیست از سرور به گوشی موبایل......";
			if (AsyncTaskType == "GetListMobile1")
				TextMessage="درحال دریافت اطلاعات لیست های قرائت از سرور......";
				
			if (AsyncTaskType == "SendListMobile") 
				TextMessage="در حال ارسال اطلاعات لیست قرائت به سرور......";
			if (AsyncTaskType == "GetCityByDeviceId") 
				TextMessage="در حال دریافت اطلاعات از سرور......";
			if (AsyncTaskType == "ChangeDefaultCity") 
				TextMessage="در حال ارسال اطلاعات به سرور......";
			if (AsyncTaskType == "ReportOnline")
				TextMessage="در حال دریافت اطلاعات گزارش ارسال از سرور......";
			showDialog(DIALOG_DOWNLOAD_PROGRESS);


		}

		protected String doInBackground(String... aurl) {
			// ***********************toast make error when call in do in
			// background*****************************

			if (AsyncTaskType == "GetListMobile")
				call();
			if (AsyncTaskType == "GetListMobile1")
				call1();
			if (AsyncTaskType == "SendListMobile") 
			{

				ReadingListDto readingListDto=da.Get_Id_Active_ListGheraat().get(0);

				SendListToServer = da
						.select_data_for_output_pdl_device_string_Json(readingListDto.Id_List.toString());
				String SendListToServer_Peymayesh=da
						.select_data_for_peymayesh_device_string_Json(readingListDto.Id_List.toString());
				String SendListToServer_IlligalBranch=da
						.select_data_for_illigalbranch_device_string_Json();
				
				Log.e("sendtoserver", SendListToServer);
                Log.e("SendListPeymayesh", SendListToServer_Peymayesh);
                Log.e("SendListIlligalBranch", SendListToServer_IlligalBranch);
				Log.e("js", js);
				
				if ((SendListToServer.compareTo("RecordNotFoudForSendToServer") == 0)
					&&
					(SendListToServer_Peymayesh.compareTo("RecordNotFoudForSendToServer") == 0)
					&&
					(SendListToServer_IlligalBranch.compareTo("RecordNotFoudForSendToServer") == 0))
				{
					js = "RecordNotFoudForSendToServer";
				} else {
					Log.e("########", SendListToServer_IlligalBranch);
					SendList();
				}
			}
			if (AsyncTaskType == "GetCityByDeviceId") 
			{
				Call_GetCityByDeviceId();
			}
			if (AsyncTaskType == "ChangeDefaultCity") 
			{
				Call_ChangeDefaultCity(); 
			}

			 if (AsyncTaskType == "ReportOnline")
			 {
			 	Call_Report();
			 }
			return null;

		}

		protected void onProgressUpdate(String... progress) {

		}

		protected void onPostExecute(String unused) 
		{

			dismissDialog(DIALOG_DOWNLOAD_PROGRESS);

			Log.e("AsyncTaskType", AsyncTaskType);
			// try
			// {
			
				
			if (AsyncTaskType == "GetListMobile") 
			{
				//js = "ErrorGetList";GetListSuccessfully
				
				if (js.compareTo("GetListSuccessfully")==0)
				{
					Toast.makeText(
							context,
							"لیست با موفقیت انتخاب گردید ،",
							Toast.LENGTH_LONG).show();
				}
				if (js.compareTo("ErrorGetList")==0)
				{
					Toast.makeText(context,"خطا در دریافت لیست از سرور ،لطفا دوباره تلاش کنید",Toast.LENGTH_SHORT).show();
				}
			}

			if (AsyncTaskType == "ReportOnline") {

//                final Dialog dialog = new Dialog(context,R.style.AppTheme);
//				dialog.setContentView(R.layout.code_gheraat);
//				dialog.setTitle("لیست های ارسال شده به واحد مشترکین");
//
//
//				ListView list_code_mane = (ListView) dialog
//						.findViewById(R.id.listView_code_gherat);
//
//				LinearLayout title=(LinearLayout) dialog
//						.findViewById(R.id.title_list_dialog_line);
//				title.setVisibility(View.GONE);
//				list_code_mane.setAdapter(new Adapter_Report(context,
//						ArrayListListGheraat));
//				dialog.show();
			}
			if (AsyncTaskType == "GetListMobile1") 
			{
				if (ArrayListListGheraat.isEmpty()==false)
				{
					final Dialog dialog = new Dialog(context, R.style.AppTheme);
					dialog.setContentView(R.layout.code_gheraat);
					dialog.setTitle("لیست های قابل انتخاب");
					ListView list_code_mane = (ListView) dialog.findViewById(R.id.listView_code_gherat);
					list_code_mane.setAdapter(new Adapter_Porsesh(context, ArrayListListGheraat));
					dialog.show();
					list_code_mane
							.setOnItemClickListener(new OnItemClickListener() {
								public void onItemClick(AdapterView<?> arg0,
														View arg1, int arg2, long arg3) {
									// TODO Auto-generated method stub
									AsyncTaskType = "GetListMobile";
									IDFile = ArrayListListGheraat.get(arg2).Get_idlistserver();
									NameList = ArrayListListGheraat.get(arg2).Get_NameList();

									new DownloadFileAsync_test().execute("");
								}

							});
				}
			}

			if (AsyncTaskType == "SendListMobile") 
			{
				//Toast.makeText(context, SendListToServer, Toast.LENGTH_SHORT).show();
				//Log.e("fucj1", "fuckyou");
				//Log.e("sendtoserver1", SendListToServer);

				if (SendListToServer.compareTo("InsertRecordSuccessfully")==0)
				{
					Toast.makeText(context,"هیچگونه درج یا ویرایشی در سرور صورت نگرفته است",Toast.LENGTH_SHORT).show();
					return;
				}
                Log.e("[js]", js);
				if (SendListToServer.startsWith("InsertRecordSuccessfully")) 
				{
					//InsertRecordSuccessfully@73000119
					js = js.replace("InsertRecordSuccessfully", "");
					String IlligalBranch="";

					if (SendListToServer.contains("!")) {
						IlligalBranch = js.substring(js.lastIndexOf("!") + 1);
						js = js.substring(0, js.lastIndexOf("!"));
					}

					String Peymayesh="";
					if (SendListToServer.contains("#")) {

					    //---------------------------------------------
						int position=js.contains("!")? js.lastIndexOf("!")-(js.lastIndexOf("#")+1):
                                (js.length())- (js.lastIndexOf("#")+1);
						Peymayesh = js.substring(js.lastIndexOf("#") + 1,js.length());

						js = js.substring(0, js.lastIndexOf("#"));
					}

					if (SendListToServer.contains("@")) {
						String[] temp = js.split("@");
						if (temp.length == 2) {
							Log.e("[2]", temp[1]);
							da.CheckMark_SendToServerSerialno(temp[1],
									idlist);
						}
					}
					Log.e("[p]",Peymayesh );

					if (Peymayesh.length()>0)
					{
						da.CheckMark_SendToServerPeymayesh(Peymayesh);
					}
					if (IlligalBranch.length()>0)
					{
						da.CheckMark_SendToServerIlligalBranch(IlligalBranch);
					}

					Toast.makeText(
							context,
							"اطلاعات با موفقیت ارسال گردید",
							Toast.LENGTH_SHORT).show();
				}
				if (SendListToServer.compareTo("ErrorInsertRecord") == 0) 
				{
					Toast.makeText(
							context,
							"خطا در ارسال اطلاعات لطفا مجددا تلاش کنید",
							Toast.LENGTH_SHORT).show();
				}
				if (SendListToServer.compareTo("RecordNotFoudForSendToServer") == 0) {
					Toast.makeText(
							context,
							"کاربر گرامی ،اطلاعاتی جهت ارسال به سرور وجود ندارد",
							Toast.LENGTH_SHORT).show();

					// Log.e("fucj2", "fuckyou");
				}

				if (SendListToServer.compareTo("ErrorInsertListHasValue") == 0) {
					Toast.makeText(
							context,
							"کاربر گرامی ،اطلاعات ارسالی قبلا بارگذاری شده است ",
							Toast.LENGTH_SHORT).show();

					// Log.e("fucj2", "fuckyou");
				}

			}

			if (AsyncTaskType == "GetCityByDeviceId") {
				//1,14,بهبهان,0#65,60,هفتکل,60
				String[] Record = js.split("#");
				if (Record.length >= 1)
				{
						final ArrayList<Citys> ListOfCitys = new ArrayList<Citys>();
						final Dialog dialog = new Dialog(context);
						dialog.setContentView(R.layout.code_gheraat);
						dialog.setTitle("انتخاب شهر جهت قرائت");

						for (String records : Record) {
							Citys TempCitys = new Citys();
							String[] Fields = records.split(",");
							TempCitys.Set_Id(Fields[0].toString());
							TempCitys.Set_CityId(Fields[1].toString());
							TempCitys.Set_CityName(Fields[2].toString());
							TempCitys.Set_DefaultCityId(Fields[3].toString());
							ListOfCitys.add(TempCitys);
						}
						Log.e("citys", String.valueOf(ListOfCitys.size()));
						ListView list_code_mane = (ListView) dialog
								.findViewById(R.id.listView_code_gherat);
						IResponse m=null;
						list_code_mane.setAdapter(new Adapter_Citys(context, m));
						LinearLayout LayoutTitle = (LinearLayout) dialog.findViewById(R.id.title_list_dialog_line);
						LayoutTitle.setVisibility(View.GONE);
						list_code_mane.setOnItemClickListener(new OnItemClickListener() {

							public void onItemClick(AdapterView<?> arg0, View arg1,
													int arg2, long arg3) {
								dialog.hide();
								ID_ChangeCity = ListOfCitys.get(arg2).Get_Id();
								Name_ChangeCity = ListOfCitys.get(arg2).Get_CityName();
								AsyncTaskType = "ChangeDefaultCity";
								new DownloadFileAsync_test().execute("");
							}
						});
						dialog.show();
			}
			}
		
			if (AsyncTaskType == "ChangeDefaultCity") 
			{
				try
				{
					if (js.compareTo("Ok")==0)
					{
						da.Update_OfficerCityName(Name_ChangeCity);

						String BROADCAST_ACTION = "mousavi.Geraat";
						Intent	intent  = new Intent(BROADCAST_ACTION);
						sendBroadcast(intent);
						Toast.makeText(getApplicationContext(), "تغییر شهر با موفقیت انجام شد", Toast.LENGTH_SHORT).show();
					}
					else
					{
						Toast.makeText(getApplicationContext(), "خطا در انجام عملیات لطفا دوباره تلاش کنید", Toast.LENGTH_SHORT).show();
					}
				}catch(Exception error){Toast.makeText(getApplicationContext(), "خطا در تغییر شهر لطفا دوباره تلاش کنید", Toast.LENGTH_SHORT).show();}
			}
		}
	}

}
