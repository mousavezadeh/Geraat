package mousavi.Geraat;

import android.Manifest;
import android.app.DownloadManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.os.IBinder;

import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import org.json.JSONArray;
import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.io.File;
import java.util.ArrayList;

import mousavi.Intent.IntenAction;
import mousavi.Intent.IntenEmpty;
import mousavi.Notification.DeviceNotRegisterNotification;
import mousavi.Notification.EndofSendingInformationNotification;
import mousavi.Notification.ErrorDateTimeNotification;
import mousavi.Notification.ErrorDuringOperationNotification;
import mousavi.Notification.NewVersionAvailabelNotification;
import mousavi.Notification.NoConnectiontotheServerNotification;
import mousavi.Notification.SendDataToServerNotification;
import mousavi.Notification.SoftwareUpdateNotification;
import mousavi.Request.Api;
import mousavi.Request.ExecuteApi;
import mousavi.Request.GetOfficerReportRequestDto;
import mousavi.Request.GetOfficerReportResponse;
import mousavi.Request.GetVersionRequestDto;
import mousavi.Request.GetVersionResponse;
import mousavi.database.AddData;
import mousavi.database.Dto.PhotoDto;
import mousavi.database.Dto.VersionDto;
import mousavi.database.ExecuteSql;
import mousavi.database.MobileAppVersion;
import mousavi.database.Pic;
import mousavi.database.Select.FetchSelectOperation;
import mousavi.database.Select.SelectVersion;
import mousavi.database.SendListGheratReturn;
import mousavi.database.Update.UpdateIllegalBranchSendToServer;
import mousavi.database.Update.UpdateIllegalBranchServerId;
import mousavi.database.Update.UpdatePeymayesh;
import mousavi.database.Update.UpdatePeymayeshSendToServer;
import mousavi.database.Update.UpdatePhotoSendToServer;
import mousavi.database.Update.UpdateReadingListItemSendToServer;
import mousavi.database.databasetest;
import mousavi.file.operation.file_operation;
import retrofit2.Retrofit;

public class MyService extends Service {

	int versionCode;
	PackageInfo pInfo=null;
	databasetest da;
	SQLiteDatabase db;
	ArrayList<AddData> Version = new ArrayList<AddData>();
	ArrayList<MobileAppVersion> ArrayListMobileAppVersion = new ArrayList<MobileAppVersion>();
	MobileAppVersion TempVersion = new MobileAppVersion();
	Retrofit retrofit;
	private static final String SOAP_ACTION_SendListGheraat = "http://tempuri.org/SendListGheraat";
	private static final String METHOD_NAME_SendListGheraat = "SendListGheraat";
	private static final String SOAP_ACTION = "http://tempuri.org/GetVersion";
	private static final String METHOD_NAME = "GetVersion";
    private static final String SOAP_ACTION_SaveMobileRequestAttachments = "http://tempuri.org/SaveMobileRequestAttachments";
    private static final String METHOD_NAME_SaveMobileRequestAttachments = "SaveMobileRequestAttachments";
	private static final String NAMESPACE = "http://tempuri.org/";
	private static final String URL = "https://pay.abfakhz.ir/MobileWebService.asmx";
	String js = "", SendListToServer = "",SendList_Result="";
	Float DataBaseVersionNo = (float) 1.1;
	Float WebServiceVersionNo = (float) 1.1;
	TextView t1;
	private long DownloadID;
	static int Downloadapk = 0;
	boolean DateState=false;
	private BroadcastReceiver onDownloadComplete = new BroadcastReceiver() {

		@Override
		public void onReceive(Context arg0, Intent arg1) {
			Toast.makeText(getApplicationContext(), "Download Complete", Toast.LENGTH_LONG).show();
			// TODO Auto-generated method stub
			Downloadapk = 0;
			long id = arg1.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
		}
	};

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {

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

		//StringtoJsonnew(getString(R.string.jsonvalue));
		int Permission_Read = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE);
		int Permission_Write = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
		if (Permission_Read == PackageManager.PERMISSION_GRANTED && Permission_Write == PackageManager.PERMISSION_GRANTED)
		{
			Log.e("service", "fuck service onStartCommand");
			new DownloadFileAsync_test().execute("");
			SelectVersion selectversion=new SelectVersion(db);
			if (selectversion.fetch(VersionDto.class).size()==1)
			{
				//test case
			}
			Api api=new Api();
			SoapObject res=api.GetVersion(new GetVersionRequestDto(GeneralExtentions.Extention.GetImei(getApplicationContext()),""));
			new ExecuteApi(GetVersionResponse.class.getName(),getApplicationContext()).execute(res);

//			new SendPicAsync().execute("");
		}


		return START_STICKY;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		//Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
	}

	@Override
	public void onCreate() {
		super.onCreate();
		// here you can add whatever you want this service to do
		Log.e("service", "fuck service credate");
	}


	void StringtoJson(String Str) {
		Log.e("StringtoJsonStart", Str);
		try {
			JSONArray postalCodesItems = new JSONArray(Str);
			for (int i = 0; i < postalCodesItems.length(); i++)
			{
				JSONObject postalCodesItem = postalCodesItems.getJSONObject(i);
				MobileAppVersion agahi = new MobileAppVersion();

				agahi.set_id(postalCodesItem.getString("id"));
				agahi.set_version(postalCodesItem.getString("version"));
				agahi.set_Values(postalCodesItem.getString("Values"));
				agahi.set_tozihat(postalCodesItem.getString("tozihat"));
				agahi.set_TableName(postalCodesItem.getString("TableName"));
				agahi.set_SqlQuery(postalCodesItem.getString("SqlQuery"));
				agahi.set_NoeUpdateDataBase(postalCodesItem.getString("NoeUpdateDataBase"));
				agahi.set_NoeUpdate(postalCodesItem.getString("NoeUpdate"));
				agahi.set_FilePath(postalCodesItem.getString("FilePath"));
				agahi.set_Fields(postalCodesItem.getString("Fields"));
				agahi.set_DateSabt(postalCodesItem.getString("DateSabt"));
				agahi.set_OfficerName(postalCodesItem.getString("OfficerName"));
				agahi.set_CityName(postalCodesItem.getString("CityName"));
				agahi.set_MultiOfficer(postalCodesItem.getString("MultiOfficer"));
				agahi.set_CityId(postalCodesItem.getString("CityId"));
				agahi.set_ServerDateTime(postalCodesItem.getString("SystemDate"));
				agahi.set_Permissionencrypt(postalCodesItem.getString("permission"));

				ArrayListMobileAppVersion.add(agahi);
				Log.e("end string to json", "end");
			}

		} catch (Exception e) {
			Log.e("error in json object", e.toString());
		}
	}

	void StringtoJsonnew(String Str) {
		try {
				SendListGheratReturn sendlistgheratreturn=new SendListGheratReturn(Str);
				String date=GeneralExtentions.Extention.GetDateNowWithFormat();

				UpdateReadingListItemSendToServer updateReadingListItemSendToServer=new UpdateReadingListItemSendToServer(db,"",date);
				UpdatePeymayeshSendToServer updatePeymayeshSendToServer=new UpdatePeymayeshSendToServer(db,"",date);
				UpdateIllegalBranchSendToServer updateIllegalBranchSendToServer=new UpdateIllegalBranchSendToServer(db,"",date);

				//UpdateIllegalBranchServerId updateIllegalBranchServerId=new UpdateIllegalBranchServerId(db,"","");

				try { da.CheckMark_SendToServerSerialno(sendlistgheratreturn,"");}catch (Exception e){}
				try { da.CheckMark_SendToServerPeymayesh(sendlistgheratreturn);}catch (Exception e){}
				try { da.CheckMark_SendToServerIlligalBranch(sendlistgheratreturn);}catch (Exception e){}
				try { da.Update_IllegalBranch_Serverid(sendlistgheratreturn);}catch (Exception e){}


			}
		 catch (Exception e) {
			Log.e("error in json object", e.toString());

		}

	}

	public void ExecuteOperations()
	{

		int NotificationId = 0;
		Log.e("size", Integer.toString(ArrayListMobileAppVersion.size()));
		for (MobileAppVersion Versions : ArrayListMobileAppVersion)
		{
			if (Versions.get_NoeUpdateDataBase().compareTo("Apk") == 0 && Downloadapk == 0)
			{
				Downloadapk = 1;
				 GeneralExtentions.Extention.apkinformation apkinfo= GeneralExtentions.Extention.CheckNewVersionWithinfo(getApplicationContext());
				 if (apkinfo==null) return;


					SoftwareUpdateNotification softwareUpdateNotification=
							new SoftwareUpdateNotification(Versions.get_tozihat(),NotificationId,Versions.get_SqlQuery());
					GeneralExtentions.Extention.SendNotification(softwareUpdateNotification,getApplicationContext());
					NotificationId++;

					File temp1 = new File(apkinfo.uri.getPath());
                    DownloadManager.Request re = new DownloadManager.Request(Uri.parse(Versions.get_FilePath()))
                            .setDestinationUri(Uri.fromFile(temp1))
                            .setVisibleInDownloadsUi(true)
                            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)
                            .setDescription("درحال دریافت نسخه جدید نرم افزار قرائت کنتور");

                    TempVersion = Versions;
					try {
						da.Select_OfficerData(ArrayListMobileAppVersion.get(0));

						if (!temp1.exists())
						{
							//fileopp.DeleteAllFileinFolder("ABFA/Update");
							DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
							DownloadID = manager.enqueue(re);
						}

					} catch (Exception e) {
						Log.e("apk", e.toString());
						temp1.delete();
						e.printStackTrace();
					}

			}

			if (GeneralExtentions.Extention.CheckDateTime(Versions.get_ServerDateTime()))
			{
				IntenAction intenAction=new IntenAction("");
				sendBroadcast(intenAction.Getintent());

				if (Versions.get_NoeUpdateDataBase().compareTo("Insert") == 0) {
					da.UdateDatabaseVersion_Insert_Record
							(
									Versions.get_TableName(),
									Versions.get_Fields(),
									Versions.get_Values()
							);

					da.UpdatePRAGMA(Versions.get_version());

					da.Update_Version(Versions.get_version());
					SoftwareUpdateNotification softwareUpdateNotification=
							new SoftwareUpdateNotification(Versions.get_tozihat(),NotificationId,Versions.get_SqlQuery());
					GeneralExtentions.Extention.SendNotification(softwareUpdateNotification,getApplicationContext());
					NotificationId++;
				}
				if (Versions.get_NoeUpdateDataBase().compareTo("InsertColumn") == 0) {
					da.CheckColumnExist
							(
									Versions.get_TableName(),
									Versions.get_Fields(),
									Versions.get_Values(),
									Versions.get_SqlQuery()

							);
					Log.e("v u", Versions.get_version());
					da.UpdatePRAGMA(Versions.get_version());
					da.Update_Version(Versions.get_version());

					SoftwareUpdateNotification softwareUpdateNotification=
							new SoftwareUpdateNotification(Versions.get_tozihat(),NotificationId,Versions.get_SqlQuery());
					GeneralExtentions.Extention.SendNotification(softwareUpdateNotification,getApplicationContext());
					NotificationId++;
				}
				if (Versions.get_NoeUpdateDataBase().compareTo("Update") == 0) {
					Log.e("sqlquey", Versions.get_SqlQuery());
					da.UdateDatabaseTable
							(
									Versions.get_TableName(),
									Versions.get_Fields(),
									Versions.get_Values(),
									Versions.get_SqlQuery()
							);
					Log.e("v u", Versions.get_version());
					da.UpdatePRAGMA(Versions.get_version());
					da.Update_Version(Versions.get_version());

					SoftwareUpdateNotification softwareUpdateNotification=
							new SoftwareUpdateNotification(Versions.get_tozihat(),NotificationId,Versions.get_SqlQuery());
					GeneralExtentions.Extention.SendNotification(softwareUpdateNotification,getApplicationContext());
							NotificationId++;
				}
				if (Versions.get_NoeUpdateDataBase().compareTo("ExecQuery") == 0) {

					databasetest.db.execSQL(Versions.get_SqlQuery(),null);
					Log.e("v u", Versions.get_version());
					da.UpdatePRAGMA(Versions.get_version());
					da.Update_Version(Versions.get_version());

					SoftwareUpdateNotification softwareUpdateNotification=
							new SoftwareUpdateNotification(Versions.get_tozihat(),NotificationId,Versions.get_SqlQuery());
					GeneralExtentions.Extention.SendNotification(softwareUpdateNotification,getApplicationContext());
					NotificationId++;
				}
			}

			ErrorDateTimeNotification errorDateTimeNotification=new ErrorDateTimeNotification();
			GeneralExtentions.Extention.SendNotification(errorDateTimeNotification,getApplicationContext());

			IntenAction intenAction=new IntenAction("ErrorDateTime");
			sendBroadcast(intenAction.Getintent());
			return;
		}

		if (!Version.isEmpty()) {
			da.Select_OfficerData(ArrayListMobileAppVersion.get(ArrayListMobileAppVersion.size() - 1));
			IntenEmpty intenEmpty=new IntenEmpty();
			sendBroadcast(intenEmpty.Getintent());
		}
	}
	public MobileAppVersion SetOfficerData(String StrData) {
		String[] OfficerData = StrData.split("#");
		String[] OfficerRecord = OfficerData[1].split(",");
		MobileAppVersion TempMobileAppVersion = new MobileAppVersion();

		try{TempMobileAppVersion.set_OfficerName(OfficerRecord[0]);}catch (Exception Error){TempMobileAppVersion.set_OfficerName("");}
		try{TempMobileAppVersion.set_CityName(OfficerRecord[1]);}catch (Exception Error){TempMobileAppVersion.set_CityName("");}
		try{TempMobileAppVersion.set_MultiOfficer(OfficerRecord[2]);}catch (Exception Error){TempMobileAppVersion.set_MultiOfficer("");}
		try{TempMobileAppVersion.set_CityId(OfficerRecord[3]);}catch (Exception Error){TempMobileAppVersion.set_CityId("");}
		try{TempMobileAppVersion.set_ServerDateTime(OfficerRecord[4]);}catch (Exception Error){TempMobileAppVersion.set_ServerDateTime("");}
		try{TempMobileAppVersion.set_Permissionencrypt(OfficerData[1].substring(OfficerData[1].indexOf("["),OfficerData[1].indexOf("]")+1));}catch (Exception Error){TempMobileAppVersion.set_Permissionencrypt("");}

		if (GeneralExtentions.Extention.CheckDateTime(TempMobileAppVersion.get_ServerDateTime())==false)
		{
			ErrorDateTimeNotification errorDateTimeNotification=new ErrorDateTimeNotification();
			GeneralExtentions.Extention.SendNotification(errorDateTimeNotification,getApplicationContext());
			IntenAction intenAction=new IntenAction("ErrorDateTime");
			sendBroadcast(intenAction.Getintent());
		}

		return TempMobileAppVersion;
	}




	public void SendListToServer(String Normalreading, String Peymayesh, String IllegalBranch,String Pressure) {


		if ((Normalreading.compareTo("RecordNotFoudForSendToServer") != 0)
				||
				(Peymayesh.compareTo("RecordNotFoudForSendToServer") != 0)
				||
				(IllegalBranch.compareTo("RecordNotFoudForSendToServer") != 0)
				||
				(Pressure.compareTo("RecordNotFoudForSendToServer") != 0)) {
			try {

					SendDataToServerNotification sendDataToServerNotification=new SendDataToServerNotification();
					GeneralExtentions.Extention.SendNotification(sendDataToServerNotification,getApplicationContext());
				Log.e("Sen","sen");
				ArrayListMobileAppVersion.clear();
				@SuppressWarnings("unused")
				SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME_SendListGheraat);
				request.addProperty("JsonArrayList", Normalreading);
				request.addProperty("id_list", "0");
				request.addProperty("imei", GeneralExtentions.Extention.GetImei(getApplicationContext()));
				request.addProperty("JsonArrayListPeymayesh", Peymayesh);
				request.addProperty("JsonArrayListilligalBranch", IllegalBranch);
				request.addProperty("JsonArrayListPressure", Pressure);
				request.addProperty("Version", versionCode);

				SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
				envelope.dotNet = true;
				envelope.setOutputSoapObject(request);
				HttpTransportSE androidHttpTransport = new HttpTransportSE(URL, 200000);
				androidHttpTransport.call(SOAP_ACTION_SendListGheraat, envelope);
				Object result = (Object) envelope.getResponse();
				SendList_Result = result.toString();
				SendListToServer = SendList_Result;
				Log.e("List out",SendListToServer);
			} catch (Exception e) {
				js = e.toString();
			}
		}
	}
	public void call1()
	{
		try {
			file_operation f=new file_operation();
			PackageInfo CurrentPackageInfo;
			CurrentPackageInfo = f.CurrentPackageInfo(getApplicationContext());
			ArrayListMobileAppVersion.clear();
			Log.e("Start", "call");
			@SuppressWarnings("unused")
			SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
			Log.e("imei", GeneralExtentions.Extention.GetImei(getApplicationContext()));

			request.addProperty("imei", GeneralExtentions.Extention.GetImei(getApplicationContext()));
			Log.e("imei", GeneralExtentions.Extention.GetImei(getApplicationContext()));
			Log.e("Version Database", DataBaseVersionNo.toString()+"#"+String.valueOf(CurrentPackageInfo.versionCode));
			request.addProperty("Version", DataBaseVersionNo.toString()+"#"+String.valueOf(CurrentPackageInfo.versionCode));
            Log.e("ver", DataBaseVersionNo.toString()+"#"+String.valueOf(CurrentPackageInfo.versionCode));
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
					SoapEnvelope.VER11);
			envelope.dotNet = true;
			envelope.setOutputSoapObject(request);

			HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
			androidHttpTransport.call(SOAP_ACTION, envelope);
			Object result = (Object) envelope.getResponse();
			js = result.toString();
			Log.e("Start officer", "--------------------------"+js);

			if ((js.compareTo("ListNotFound") == 0) || (js.compareTo("DeviceNotRegister") == 0)
					|| (js.compareTo("ErrorInFunction") == 0) || (js.startsWith("ListNotFound#")))
			{

				if (js.compareTo("DeviceNotRegister") == 0)
				{
					DeviceNotRegisterNotification deviceNotRegisterNotification=new DeviceNotRegisterNotification();
					GeneralExtentions.Extention.SendNotification(deviceNotRegisterNotification,getApplicationContext());
					IntenAction intenAction=new IntenAction("ErrorImei");
					sendBroadcast(intenAction.Getintent());
					return;
				}
				if (js.contains("ErrorInFunction") )
				{
					ErrorDuringOperationNotification errorDuringOperationNotification=new ErrorDuringOperationNotification();
					GeneralExtentions.Extention.SendNotification(errorDuringOperationNotification,getApplicationContext());

					return;
				}
				da.Select_OfficerData(SetOfficerData(js));
				return;
			}

			//StringtoJson(js);
			ExecuteOperations();

		} catch (Exception e) {
			js = e.toString();
			if (e.getMessage().contains("Failed to connect to"))
			{

				NoConnectiontotheServerNotification noConnectiontotheServerNotification=new NoConnectiontotheServerNotification();
				GeneralExtentions.Extention.SendNotification(noConnectiontotheServerNotification,getApplicationContext());
			}

			ErrorDuringOperationNotification errorDuringOperation=new ErrorDuringOperationNotification(e.getMessage());
			GeneralExtentions.Extention.SendNotification(errorDuringOperation,getApplicationContext());

		}

	}
    public Pic Sendpic(Pic pic )
	{
		String js="";Pic p=null;
		try {
			Log.e("Start", "call");
			@SuppressWarnings("unused")
			SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME_SaveMobileRequestAttachments);
			request.addProperty("ID_IllegalBranch", pic.getServerid());
			request.addProperty("imgfiletype", "");
			request.addProperty("imgfilename", "");
			request.addProperty("imgfilesize", 20);
			request.addProperty("Base64imagefile",  GeneralExtentions.Extention.ImageToBase64(new File(pic.getfilepath())));
			request.addProperty("imei", GeneralExtentions.Extention.GetImei(getApplicationContext()));
			request.addProperty("Localid", pic.getid());
			request.addProperty("pictype", pic.get_pictype().ordinal());
			request.addProperty("estateid", pic.getestateid());


			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
					SoapEnvelope.VER11);
			envelope.dotNet = true;
			envelope.setOutputSoapObject(request);

			HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
			androidHttpTransport.call(SOAP_ACTION_SaveMobileRequestAttachments, envelope);
			Object result = (Object) envelope.getResponse();
			js = result.toString();
			Log.e("pic",js.toString());
			p=new Pic(js);
			if ((js.compareTo("ListNotFound") == 0) || (js.compareTo("DeviceNotRegister") == 0)
					|| (js.compareTo("ErrorInFunction") == 0) || (js.startsWith("ListNotFound#"))) {
				if (js.compareTo("DeviceNotRegister") == 0) {
					DeviceNotRegisterNotification deviceNotRegisterNotification=new DeviceNotRegisterNotification();
					GeneralExtentions.Extention.SendNotification(deviceNotRegisterNotification,getApplicationContext());

					IntenAction intenAction=new IntenAction("ErrorImei");
					sendBroadcast(intenAction.intent);
					return new Pic();
				}
				if (js.contains("ErrorInFunction")) {
					ErrorDuringOperationNotification errorDuringOperationNotification=new ErrorDuringOperationNotification();
					GeneralExtentions.Extention.SendNotification(errorDuringOperationNotification,getApplicationContext());

					return new Pic();
				}
				return p;
			}
			Log.e("js", js.toString());

		} catch (Exception e) {
			js = e.toString();
			if (e.getMessage().contains("Failed to connect to")) {
				NoConnectiontotheServerNotification noConnectiontotheServerNotification=new NoConnectiontotheServerNotification();
				GeneralExtentions.Extention.SendNotification(noConnectiontotheServerNotification,getApplicationContext());

			}
			Log.e("catch call1", e.getMessage());

			ErrorDuringOperationNotification errorDuringOperationNotification=new ErrorDuringOperationNotification(e.getMessage());
			GeneralExtentions.Extention.SendNotification(errorDuringOperationNotification,getApplicationContext());
		}
		return  p;
	}

    //region Async
        //region SendGheraat
	        class DownloadFileAsync_test extends AsyncTask<String, String, String> {

		Boolean IsVersionRecord = false;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();


		}


		protected String doInBackground(String... aurl)
		{
			da = databasetest.getInstance(getApplicationContext());
			//databasetest.db.beginTransaction();
			Version = da.Select_Version();
			if (Version.size() == 1) {
				//DataBaseVersionNo=Float.valueOf("3");
				DataBaseVersionNo = Float.valueOf(Version.get(0).get_Value());
			}

			if (GeneralExtentions.Extention.checkConnectivity(getBaseContext()))
			{
				String SendListToServer = da.select_data_for_output_pdl_device_string_Json("");
				String SendListToServer_Peymayesh = da.select_data_for_peymayesh_device_string_Json("");
				String SendListToServer_IlligalBranch = da.select_data_for_illigalbranch_device_string_Json();
				String SendListToServer_Pressure = "RecordNotFoudForSendToServer";


				if (GeneralExtentions.Extention.PayUrlConnectionState())
				{
					//check imei
					call1();
					if (DateState) SendListToServer(SendListToServer, SendListToServer_Peymayesh, SendListToServer_IlligalBranch,SendListToServer_Pressure);
				}
			}
			return null;

		}



		protected void onPostExecute(String unused)
		{
			Log.e("onPostExecute","ha jha");
			try {
				if (SendListToServer.startsWith("Error-Version"))
				{
					NewVersionAvailabelNotification newVersionAvailabelNotification=new NewVersionAvailabelNotification();
					GeneralExtentions.Extention.SendNotification(newVersionAvailabelNotification,getApplicationContext());

				}else
				{
					if (!SendListToServer.isEmpty()) {
						StringtoJsonnew(SendListToServer);

						EndofSendingInformationNotification endofSendingInformationNotification=new EndofSendingInformationNotification();
						GeneralExtentions.Extention.SendNotification(endofSendingInformationNotification,getApplicationContext());
					}
				}
				new SendPicAsync().execute("");
			}catch (Exception error){Log.e("onPostExecute",error.getMessage());}
			finally {
				SendList_Result="";SendListToServer="";
			}
		}
	}
	//endregion
    //region SendPic
    class SendPicAsync extends AsyncTask<String, String, String> {

        Boolean IsVersionRecord = false;
        @Override
        protected void onPreExecute()
		{
            super.onPreExecute();
            //checkConnectivity(getBaseContext());
        }
        protected String doInBackground(String... aurl)
        {
            da = databasetest.getInstance(getApplicationContext());
            if (GeneralExtentions.Extention.checkConnectivity(getBaseContext())) {
               ArrayList<PhotoDto> photoDtos=da.select_data_for_pic_device();


                if (GeneralExtentions.Extention.PayUrlConnectionState())
                {
                    for (PhotoDto item : photoDtos)
                    {
                    	if (GeneralExtentions.Extention.FileExist(item.filepath)) {
							UpdatePhotoSendToServer updatePhotoSendToServer=new
									UpdatePhotoSendToServer(db,item.id,GeneralExtentions.Extention.GetDateNowWithFormat());
							new ExecuteSql(updatePhotoSendToServer).Exec();
							//da.CheckMark_SendToServerPic(Sendpic(pic));
						}

                    }
                }
            }
            return null;
        }
        protected void onPostExecute(String unused)
        {

            try {
                if (SendListToServer.startsWith("InsertRecordSuccessfully"))
                {
                    String[]  temp_Count = new String[]{};
                    SendList_Result = SendList_Result.replace("InsertRecordSuccessfully", "");
                    String txt_message = "";

					EndofSendingInformationNotification endofSendingInformationNotification =new EndofSendingInformationNotification();
					GeneralExtentions.Extention.SendNotification(endofSendingInformationNotification,getApplicationContext());
                }
                else if (SendListToServer.startsWith("Error-Version"))
                {
					NewVersionAvailabelNotification newVersionAvailabelNotification=new NewVersionAvailabelNotification();
					GeneralExtentions.Extention.SendNotification(newVersionAvailabelNotification,getApplicationContext());
                }


            }catch (Exception error){Log.e("onPostExecute",error.getMessage());}
            finally {

            }
        }
    }
    //endregion
	//endregion


}
	   

