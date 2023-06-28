package mousavi.Geraat;

import static mousavi.Geraat.GeneralExtentions.Extention.ConvertPersianDigitToEnglish;
import static mousavi.Geraat.GeneralExtentions.Extention.GelLocationStatus;
import static mousavi.Geraat.GeneralExtentions.Extention.GetDateNowWithFormat;
import static mousavi.Geraat.GeneralExtentions.Extention.GetFileUri;
import static mousavi.Geraat.GeneralExtentions.Extention.GetImei;
import static mousavi.Geraat.GeneralExtentions.Extention.MakeVibrate;
import static mousavi.Geraat.GeneralExtentions.Extention.compressImage;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;

import android.text.Editable;
import android.text.InputType;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.FileProvider;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.io.File;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import Adapters.Adapter_BranchStatus;
import Adapters.Adapter_CodeMane;
import Adapters.Adapter_EstateCondition;
import Adapters.Adapter_NoeGheraat;
import Adapters.Adapter_Rozkar;
import Adapters.Adapter_SearchItems;
import Adapters.Adapter_illegalmobiletitle;
import mousavi.Dialogs.AddressDialog;
import mousavi.Dialogs.DetectionRowDialog;
import mousavi.Dialogs.IButtonAction;
import mousavi.Dialogs.MeterTrunkPolumpDialog;
import mousavi.Dialogs.PostalCodeDialog;
import mousavi.Geraat.Furtherinformation.AddressFurtherInformation;
import mousavi.Geraat.Furtherinformation.BranchStatus;
import mousavi.Geraat.Furtherinformation.DetectionrowFurtherInformation;
import mousavi.Geraat.Furtherinformation.MetertrunkFurtherInformation;
import mousavi.Geraat.Furtherinformation.MobileFurtherInformation;
import mousavi.Geraat.Furtherinformation.PolompFurtherInformation;
import mousavi.Geraat.Furtherinformation.PostalcodeFurtherInformation;
import mousavi.Geraat.OfficerPermission.ButtonPermision;
import mousavi.Geraat.OfficerPermission.DisplayOldData;
import mousavi.Geraat.OfficerPermission.Pressure;
import mousavi.Geraat.Pic.IllegalBranchPicture;
import mousavi.Geraat.Pic.NormalReadingPicture;
import mousavi.Intent.IntenTakePicture;
import mousavi.database.AddData;
import mousavi.database.Dto.BranchStatusDto;
import mousavi.database.Dto.EstateConditionDto;
import mousavi.database.Dto.OfficerPermissionDto;
import mousavi.database.Dto.ReadingListDto;
import mousavi.database.Dto.ReadingListItemDto;
import mousavi.database.Dto.illegalBranchCountDto;
import mousavi.database.Dto.illegalBranchInsertedIdDto;
import mousavi.database.Insert.InsertIllegalBranch;
import mousavi.database.Insert.InsertPicture;
import mousavi.database.Peymayesh;
import mousavi.database.Select.FetchSelectOperation;
import mousavi.database.Select.SelectBranchStatus;
import mousavi.database.Select.SelectOfficerPermission;
import mousavi.database.Select.SelectillegalBranchByDate;
import mousavi.database.Select.SelectillegalBranchInsertedId;
import mousavi.database.Update.UpdateAddress;
import mousavi.database.Update.UpdateBranchStatus;
import mousavi.database.Update.UpdateEstateCondition;
import mousavi.database.Update.UpdatePeymayesh;
import mousavi.database.Update.UpdatePostalCode;
import mousavi.database.code_mane;
import mousavi.database.databasetest;
import mousavi.database.pdl_input;
import mousavi.date.unit.mydate;
import mousavi.file.operation.file_operation;
import mousavi.message.dialogs;


public class GeraatActivity extends Activity {
	class smsofficeritems {
		private String _Shgh;
		private String _Shpa;
		private String _Mobile;
		private String _Imei;


		public smsofficeritems(String Shgh, String Shpa, String Mobile, String Imei) {
			this._Shgh = Shgh;
			this._Shpa = Shpa;
			this._Mobile = Mobile;
			this._Imei = Imei;
		}

		public smsofficeritems() {
		}

		;

		smsofficeritems Set_Shgh(String Shgh) {
			this._Shgh = Shgh;
			return this;
		}

		smsofficeritems Set_Shpa(String Shpa) {
			this._Shpa = Shpa;
			return this;
		}

		smsofficeritems Set_Mobile(String Mobile) {
			this._Mobile = Mobile;
			return this;
		}

		smsofficeritems Set_Imei(String Imei) {
			this._Imei = Imei;
			return this;
		}
	}

	//region Property
	String SendSmsresult = "";

	ArrayList<pdl_input> arr_temp = new ArrayList<pdl_input>();
	private static final String SOAP_ACTION_SendSms_Officer = "http://tempuri.org/SendSmsOfficer";
	private static final String METHOD_NAME_SendSms_Officer = "SendSmsOfficer";
	private static final String NAMESPACE = "http://tempuri.org/";
	private static final String URL = "https://pay.abfakhz.ir/MobileWebService.asmx";
	smsofficeritems Smsitems;
	Dialog finddialog, searchbynamedialog;
	Boolean ChangeCodeMamor = false;
	String Mobile;
	int recnoforupdate = 0;
	LocationTrack locationTrack;
	final Context context = this;

	static Boolean GpsEnable = false;
	static int GpsEnableCount = 0;
	Intent cameraIntent;

	/** Called when the activity is first created. */
	enum EnumUnderConstruction {None, EndofConstruction, IsUnderConstruction}

	enum EnumTemporalHousing {None, EndofTemporalHousing, IsTemporalHousing}

	enum DatabaseOperationMode {Insert, Update}

	enum Further_information {all, postalcode, mobile, Metertrunk, Meterpolomp, rowno, address, officerdescription}

	;

	enum move_record {first, next, pre, last, recordnumber, recordnumberchangemamor}

	;

	enum Select_Record_FromDatabase {Normal, CodeMamoor, CodeMamoorCodeMane, CodeMane, CodeMamoorRoozkar}

	;

	enum EnumBranchstatus {Notinstalled, Active, TemporaryCut, Collect, stagnant}

	;

	enum EnumEstateCondition {NoData, Normal, Emptyland, RuinedPlace, NoResidence}

	Select_Record_FromDatabase SelectRecordMod;
	Further_information Further_informationstate;
	String[] temp_OnlyCodeMamor;
	String[] temp_OnlyCodeMamorCount;
	dialogs dialogs_Display_List;
	String Code_Mane = "01";
	String Keyboard = "";
	String Gherat = "";
	mydate mydate = new mydate();
	ArrayList<AddData> Peymayesh;
	ArrayList<AddData> GalleryData;
	ArrayList<pdl_input> input = new ArrayList<pdl_input>();
	ArrayList<ReadingListItemDto> readingListItems = new ArrayList<ReadingListItemDto>();
	List<IllegalBranchPicture> illigalBranchPicturelist;
	ArrayList<String> f = new ArrayList<String>();
	ArrayList<code_mane> ArrayListCodeMane = new ArrayList<code_mane>();
	ArrayList<code_mane> ArrayListCodeMane_Text = new ArrayList<code_mane>();
	SQLiteDatabase db;
	file_operation file_opp = new file_operation();
	int recno = -1;
	int reccount = -1;
	int recno_Search = -1;
	int recno_temp = -1;
	Boolean FindStatus = false;
	Spinner SearchType;
	mydate PersianDate = new mydate();
	String SearchTypeText;
	//----------------------
	Uri photoURI;
	private TextView latitude;
	private TextView longitude;
	private LocationManager locationManager;
	private String provider;
	private MyLocationListener mylistener;
	private Criteria criteria;
	private TextView provText;
	Location location;
	private Typeface font, Fontawesome, TitrB;
	int SelectedMamor = 0;
	private String[] mNavigationDrawerItemTitles;
	//private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	EditText txt_search_eshterak;
	private ImageView ReplacementofMeter, UnderCoustrution, IsTemporalHousing;

	final static int TAKE_PHOTO_CODE = 0;
	String illegalmobiletitleid = "";
	String PhotoPath = "";
	File photofile;
	private Uri file;
	databasetest da;
	Boolean Hilowoperationok, Hilowmode;
	OfficerPermissions officerpermission;
	Button noe_gherat;


	enum PropertyConditions {
		//Constants with values
		Normalproperty(1), Emptyproperty(2), Ruinedproperty(3), Noresidenceproperty(5);
		//Instance variable
		private int marks;

		//Constructor to initialize the instance variable
		PropertyConditions(int marks) {
			this.marks = marks;
		}

	}
//endregion

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		try {
			if (requestCode == TAKE_PHOTO_CODE && resultCode == RESULT_OK)
			{
				String filepath = (String) cameraIntent.getExtras().get("fpath");
				String filename=filepath.substring(filepath.lastIndexOf('/'));
				int btnid = (int) cameraIntent.getExtras().get("btnid");
				if (btnid==0) return;
				if (cameraIntent.getExtras().get("pictype").toString().compareTo(NormalReadingPicture.class.getName())==0)
				{
					NormalReadingPicture normalReadingPicture=new NormalReadingPicture(filepath,filename);
					InsertPicture insertPicture=new InsertPicture(db,normalReadingPicture,GetDateNowWithFormat(),readingListItems.get(recno).EstateId);
					insertPicture.execute();

				}else
                {
                    IllegalBranchPicture illegalBranchPicture=new IllegalBranchPicture(filepath,filename);
                    illigalBranchPicturelist.add(illegalBranchPicture);
                }

				compressImage(context,filepath);
				ImageView picicon = null;
				picicon = (ImageView) finddialog.findViewById(btnid);
				picicon.setImageURI(Uri.parse(filepath));

			}
		} catch (Exception ee) {
			Log.e("fucj", ee.toString());
		}
	}




	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);

		// save file url in bundle as it will be null on scren orientation changes
		outState.putParcelable("file_uri", photoURI);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
// get the file url
		photoURI = savedInstanceState.getParcelable("file_uri");
	}


	String Get_RaghamContoroli(String Shenaseh) {
		int len = Shenaseh.length();
		int Zarb = 2;
		int digit = 0;
		int SumZarb = 0;
		while (len > 0) {
			digit = Integer.valueOf(Shenaseh.substring(len - 1));
			SumZarb = SumZarb + (Zarb * digit);
			Zarb = Zarb >= 7 ? 2 : Zarb + 1;
			len = len - 1;
			Shenaseh = Shenaseh.substring(0, len);
		}
		if ((SumZarb % 11 == 0) || (SumZarb % 11 == 0)) {
			return "0";
		} else {
			return String.valueOf(11 - (SumZarb % 11));
		}

	}


//endregion





	@SuppressLint("MissingPermission")
	public void onCreate(Bundle savedInstanceState)
	{
		recnoforupdate = 0;
		Further_informationstate = Further_information.all;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gheraat);
		font = Typeface.createFromAsset(getAssets(), "Fonts/byekan.ttf");
		Fontawesome = Typeface.createFromAsset(getAssets(), "Fonts/fa-solid-900.ttf");
		TitrB = Typeface.createFromAsset(getAssets(), "Fonts/titrb.ttf");
		databasetest._CodeMamor = "";
		da = databasetest.getInstance(getApplicationContext());

		//region  Define TextView Button and .......

		ReplacementofMeter = (ImageView) findViewById(R.id.imageView_replacementofmeter);
		ReplacementofMeter.setVisibility(View.GONE);

		UnderCoustrution = (ImageView) findViewById(R.id.imageView_undercounstruction);
		UnderCoustrution.setVisibility(View.GONE);

		IsTemporalHousing = (ImageView) findViewById(R.id.imageView_istemporalhousing);
		IsTemporalHousing.setVisibility(View.GONE);

		TextView txt_noeenshab_title = (TextView) findViewById(R.id.txt_noee_ensheab);
		TextView txt_postallcode_title = (TextView) findViewById(R.id.TextViewpostallcode);

		TextView txt_name_title = (TextView) findViewById(R.id.textView3);
		TextView txt_radif_title = (TextView) findViewById(R.id.textView03030);
		TextView txt_vazeyat_enshab_title = (TextView) findViewById(R.id.textView0303000);

		TextView txt_eshterak_title = (TextView) findViewById(R.id.TextView001);
		TextView txt_tanehkontor_title = (TextView) findViewById(R.id.TextView00001);
		TextView txt_taneh_polomp_title = (TextView) findViewById(R.id.TextView000001);
		TextView txt_karkardfeli_title = (TextView) findViewById(R.id.TextView07);
		TextView txt_adress_title = (TextView) findViewById(R.id.TextView101);
		TextView txt_polomp_title = (TextView) findViewById(R.id.TextViewplp);
		TextView txt_vazeyatmelk = (TextView) findViewById(R.id.textView030300);

		TextView txt_mobile_edit_icon = (TextView) findViewById(R.id.TextView_editmobile);
		TextView txt_radiftashkhis_edit_icon = (TextView) findViewById(R.id.TextView_editradiftashkhis);
		TextView txt_tanehpolomp_edit_icon = (TextView) findViewById(R.id.TextView_edittanehpolomp);
		TextView txt_address_edit_icon = (TextView) findViewById(R.id.TextView_editaddress);
		TextView txt_postalcode_edit_icon = (TextView) findViewById(R.id.TextView_editpostallcode);
		TextView txt_vazyatmelk_edit_icon = (TextView) findViewById(R.id.TextView_editvazyatmelk);
		TextView txt_description_edit_icon = (TextView) findViewById(R.id.TextView_editdescriptions);
		TextView txt_vazyatenshab_edit_icon = (TextView) findViewById(R.id.TextView_editvazyatensheab);
		TextView txt_postallcode_value = (TextView) findViewById(R.id.txt_postallcode);
		TextView txt_bedehi_value = (TextView) findViewById(R.id.txt_bedehi);
		TextView txt_bedehi_title = (TextView) findViewById(R.id.txt_bedehi_title);
		final TextView txt_vazeyat_melk_value = (TextView) findViewById(R.id.txt_vazyat_melk);
		final TextView txt_vazyat_enshab_value = (TextView) findViewById(R.id.txt_vazyat_enshab);
		TextView txt_readnumold = (TextView) findViewById(R.id.txt_readnumold);
		TextView txt_readnumold_title = (TextView) findViewById(R.id.txt_readnumold_title);
		TextView txt_readcodeold = (TextView) findViewById(R.id.txt_readcodeold);
		TextView txt_readcodeold_title = (TextView) findViewById(R.id.txt_readcodeold_title);
		LinearLayout layout_bedehi = (LinearLayout) findViewById(R.id.layout_bedehi);
		txt_readnumold.setTypeface(font);
		txt_readnumold_title.setTypeface(font);
		txt_readcodeold.setTypeface(font);
		txt_readcodeold_title.setTypeface(font);
		txt_taneh_polomp_title.setTypeface(font);
		txt_noeenshab_title.setTypeface(font);
		txt_vazeyat_enshab_title.setTypeface(font);
		txt_vazeyatmelk.setTypeface(font);
		txt_bedehi_value.setTypeface(font);
		txt_bedehi_title.setTypeface(font);
		txt_postallcode_value.setTypeface(font);
		txt_vazeyat_melk_value.setTypeface(font);
		txt_vazyat_enshab_value.setTypeface(font);


		TextView txt_mobile = (TextView) findViewById(R.id.txt_mobile);

		txt_vazyatenshab_edit_icon.setTypeface(Fontawesome);
		txt_vazyatmelk_edit_icon.setTypeface(Fontawesome);
		txt_postalcode_edit_icon.setTypeface(Fontawesome);
		txt_radiftashkhis_edit_icon.setTypeface(Fontawesome);
		txt_tanehpolomp_edit_icon.setTypeface(Fontawesome);
		txt_mobile_edit_icon.setTypeface(Fontawesome);
		txt_address_edit_icon.setTypeface(Fontawesome);
		txt_description_edit_icon.setTypeface(Fontawesome);
		txt_postallcode_title.setTypeface(font);
		txt_name_title.setTypeface(font);
		txt_radif_title.setTypeface(font);
		txt_eshterak_title.setTypeface(font);
		txt_tanehkontor_title.setTypeface(font);
		txt_karkardfeli_title.setTypeface(font);
		txt_adress_title.setTypeface(font);
		txt_polomp_title.setTypeface(font);
		//txt_kontor_poloomp_value.setTypeface(font);

		txt_mobile.setTypeface(font);
		txt_vazyatenshab_edit_icon.setText(new String(new char[]{0xf044}));
		txt_postalcode_edit_icon.setText(new String(new char[]{0xf044}));
		txt_mobile_edit_icon.setText(new String(new char[]{0xf044}));
		txt_radiftashkhis_edit_icon.setText(new String(new char[]{0xf044}));
		txt_tanehpolomp_edit_icon.setText(new String(new char[]{0xf044}));
		txt_address_edit_icon.setText(new String(new char[]{0xf044}));
		txt_vazyatmelk_edit_icon.setText(new String(new char[]{0xf044}));
		txt_description_edit_icon.setText(new String(new char[]{0xf044}));


		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		criteria = new Criteria();
		criteria.setAccuracy(Criteria.ACCURACY_COARSE);
		criteria.setCostAllowed(false);
		provider = locationManager.getBestProvider(criteria, false);

		location = locationManager.getLastKnownLocation(provider);
		mylistener = new MyLocationListener();
		//MyReceiver receiver = new MyReceiver() ;
		if (location != null) {
			mylistener.onLocationChanged(location);
		} else {
			// leads to the settings because there is no last known location
			Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
			startActivity(intent);
		}
		// location updates: at least 1 meter and 200millsecs change
		locationManager.requestLocationUpdates(provider, 200, 1, mylistener);
		//endregion


		noe_gherat = (Button) findViewById(R.id.noe_gheraat);
		noe_gherat.setTypeface(font);
		Button CodeMamor = (Button) findViewById(R.id.btn_CodeMamoor);
		// CodeMamor.setTypeface(font);

		SpannableStringBuilder SS = new SpannableStringBuilder(new String(new char[]{0xf0c0}) + "ظ…ط§ظ…ظˆط±");
		Log.e("lencodemamor", String.valueOf(CodeMamor.getText().length()));
		Log.e("lenss", String.valueOf(SS.length()));
		SS.setSpan(new CustomTypefaceSpan(font), 1, 6, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
		SS.setSpan(new CustomTypefaceSpan(Fontawesome), 0, 1, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
		CodeMamor.setText(SS);


		//region Define FloatingActionButton

		FloatingActionButton fabButton = new FloatingActionButton.Builder(this)
				.withDrawable(getResources().getDrawable(R.mipmap.find2))

				.withGravity(Gravity.BOTTOM | Gravity.RIGHT)
				.withButtonSize(65)
				.withMargins(0, 0, 0, 0)
				.create();


		FloatingActionButton fabButton1 = new FloatingActionButton.Builder(this)
				.withDrawable(getResources().getDrawable(R.mipmap.officerpng))
				.withGravity(Gravity.BOTTOM | Gravity.RIGHT)
				.withButtonSize(65)
				.withMargins(0, 0, 70, 0)
				.create();


		FloatingActionButton fabButton3 = new FloatingActionButton.Builder(this)
				.withDrawable(getResources().getDrawable(R.mipmap.gheratreportpng))

				.withGravity(Gravity.BOTTOM | Gravity.RIGHT)
				.withButtonSize(65)
				.withMargins(0, 0, 140, 0)
				.create();


		FloatingActionButton Btn_illigalBranch = new FloatingActionButton.Builder(this)
				.withDrawable(getResources().getDrawable(R.mipmap.illegal))

				.withGravity(Gravity.BOTTOM | Gravity.RIGHT)
				.withButtonSize(65)
				.withMargins(0, 0, 210, 0)
				.create();

		FloatingActionButton Btn_pressure = new FloatingActionButton.Builder(this)
				.withDrawable(getResources().getDrawable(R.mipmap.pressure))

				.withGravity(Gravity.BOTTOM | Gravity.RIGHT)
				.withButtonSize(65)
				.withMargins(0, 0, 280, 0)
				.create();
//region Pic

		FloatingActionButton Btn_TakePic = new FloatingActionButton.Builder(this)
				.withDrawable(getResources().getDrawable(R.mipmap.camera72))

				.withGravity(Gravity.BOTTOM | Gravity.RIGHT)
				.withButtonSize(65)
				.withMargins(0, 0, 280, 0)
				.create();

		//Btn_TakePic.setVisibility(View.GONE);

		Btn_TakePic.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String Estateid = String.valueOf(input.get(recno).get_EstateId());

				File dir = new File(Environment.getExternalStorageDirectory() + "/ABFA/Gallery/Pic/" + Estateid + "/" + "Temp1" + file_opp.MakeFileNameWithDateTime());
				try {
					file_opp.MakeDirectory("/ABFA/Gallery/Pic/" + Estateid);
					cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
					Uri photoURI = FileProvider.getUriForFile(GeraatActivity.this,
							getApplicationContext().getPackageName() + ".provider", dir);
					cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
					cameraIntent.putExtra("fpath", dir.getAbsolutePath());
					//cameraIntent.putExtra("btnid", "picicon3");
					cameraIntent.putExtra("pictype", NormalReadingPicture.class.getName());
					startActivityForResult(cameraIntent, TAKE_PHOTO_CODE);

				} catch (Exception error) {
					Log.e("Error", error.getMessage());
				}
			}
		});

//endregion
//endregion


		layout_bedehi.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				finddialog = new Dialog(context);
				finddialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
				finddialog.setContentView(R.layout.dialog_display_shenaseh);
				finddialog.setCancelable(true);
				final TextView txt_shenaseghabz = finddialog.findViewById(R.id.txt_shgh);
				final TextView txt_shenasepardakht = finddialog.findViewById(R.id.txt_shpa);
				TextView txt_shenaseghabz_title = finddialog.findViewById(R.id.txt_shgh_title);
				TextView txt_shenasepardakht_title = finddialog.findViewById(R.id.txt_shpa_title);
				Button btn_send_sms = finddialog.findViewById(R.id.btn_send_sms);
				EditText edittext_ghest = finddialog.findViewById(R.id.edittext_ghest);

				edittext_ghest.setTypeface(font);
				txt_shenaseghabz.setTypeface(font);
				txt_shenasepardakht.setTypeface(font);
				txt_shenaseghabz_title.setTypeface(font);
				txt_shenasepardakht_title.setTypeface(font);
				btn_send_sms.setTypeface(font);
				if (checkConnectivity(getBaseContext())) {

					boolean isConnected = isAbleToConnect("https://www.google.com", 1000);
					if (isConnected) btn_send_sms.setVisibility(View.VISIBLE);
				}
				txt_shenaseghabz.setText(input.get(recno).get_ShenaseGhabz());
				long shenasepardakht = 0;
				try {
					shenasepardakht = Long.parseLong(input.get(recno).get_ShenasePardakht());
				} catch (Exception ex) {
				}
				txt_shenasepardakht.setText(String.valueOf(shenasepardakht));
				if (officerpermission.Get_Debtinstallments() == false)
					edittext_ghest.setVisibility(View.GONE);
				edittext_ghest.addTextChangedListener(new TextWatcher() {
					@Override
					public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

					}

					@Override
					public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
						Log.e("change", "on change");
						try {
							if (charSequence.length() > 0) {
								double Mablagh = Double.parseDouble(charSequence.toString());
								double Shenase = 0;
								String TempShenase = "";
								if ((Mablagh > 500000) && (Mablagh < Double.valueOf(input.get(recno).get_bedehi()))) {
									if (Mablagh % 1000 > 500) {
										Shenase = (Mablagh / 1000) + 1;
										Log.e("change", "on >500");
									} else {
										Shenase = (Mablagh / 1000);
									}
									TempShenase = String.valueOf((int) Shenase) + "120";
									TempShenase = TempShenase + Get_RaghamContoroli(TempShenase);
									TempShenase = TempShenase + Get_RaghamContoroli(input.get(recno).get_ShenaseGhabz() + TempShenase);
									txt_shenasepardakht.setText(TempShenase);
								}
							} else
								txt_shenasepardakht.setText(String.valueOf(Long.parseLong(input.get(recno).get_ShenasePardakht())));
						} catch (Exception ex) {
						}
					}

					@Override
					public void afterTextChanged(Editable s) {
						boolean skipOnChange = false;
						if (skipOnChange)
							return;

						skipOnChange = true;
						try {
							//method
						} catch (NumberFormatException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} finally {
							skipOnChange = false;
						}
					}
				});
				btn_send_sms.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View view) {
						//Smsitems
						String shpa = "";
						Long t1 = 0L, t2 = 0L;
						try {
							t1 = Long.valueOf(input.get(recno).get_ShenasePardakht());
							t2 = Long.valueOf(txt_shenasepardakht.getText().toString());
						} catch (Exception ex) {
						}
						;
						if (t1.compareTo(t2) == 0)
							shpa = "";

						else
							shpa = txt_shenasepardakht.getText().toString();

						Smsitems = new smsofficeritems();
						Smsitems.Set_Shgh(txt_shenaseghabz.getText().toString())
								.Set_Shpa(shpa)
								.Set_Mobile(input.get(recno).get_Mobile())
								.Set_Imei(GetImei(context));
						finddialog.dismiss();
						SendSmsresult = "";
						new DownloadFileAsync_test1().execute("");

					}
				});
				if (Double.valueOf(input.get(recno).get_bedehi()) > 15000)
					finddialog.show();

			}
		});

		//region FloatingActionButton OnClick


		Btn_illigalBranch.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				finddialog = new Dialog(context, R.style.AppTheme);
				finddialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
				finddialog.setContentView(R.layout.dialog_illigalbranch);
				finddialog.setCancelable(false);

				TextView DialogTitle = (TextView) finddialog.findViewById(R.id.textView1);
				final EditText txt_illigalbranch_serialno = (EditText) finddialog.findViewById(R.id.txt_illigalbranch_serialno);
				final EditText txt_illigalbranch_comment = (EditText) finddialog.findViewById(R.id.txt_illigalbranch_comment);
				ImageView picicon = (ImageView) finddialog.findViewById(R.id.textView_pic);
				ImageView picicon2 = (ImageView) finddialog.findViewById(R.id.textView_pic2);
				ImageView picicon3 = (ImageView) finddialog.findViewById(R.id.textView_pic3);


				Spinner sp = (Spinner) finddialog.findViewById(R.id.spinner1);
				ArrayList<String> tempillegalmobiletitle = da.Select_illegalmobiletitle();

				Adapter_illegalmobiletitle dataAdapter = new Adapter_illegalmobiletitle(GeraatActivity.this, tempillegalmobiletitle, input.get(recno).get_IsUnderConstruction(), input.get(recno).get_IsTemporalHousing(), input.get(recno).get_karbari_int());
				sp.setAdapter(dataAdapter);
				sp.setOnItemSelectedListener(new OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
						illegalmobiletitleid = "";
						TextView tc = (TextView) view.findViewById(R.id.txt_roozkar_row_h);
						illegalmobiletitleid = tc.getText().toString();


						if (illegalmobiletitleid.compareTo("5") != 0) {
							txt_illigalbranch_serialno.setText(input.get(recno).get_eshterak_for_display());
							txt_illigalbranch_serialno.setEnabled(false);
						} else {
							txt_illigalbranch_serialno.setEnabled(true);
						}
					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {

					}
				});
				Button btn_ok = (Button) finddialog.findViewById(R.id.btn_illigalbranch_ok);
				Button btn_cancel = (Button) finddialog.findViewById(R.id.btn_illigalbranch_cancel);

				DialogTitle.setTypeface(TitrB);
				btn_ok.setTypeface(font);
				btn_cancel.setTypeface(font);
				txt_illigalbranch_serialno.setTypeface(font);
				txt_illigalbranch_comment.setTypeface(font);

				btn_ok.setOnClickListener(new OnClickListener() {

					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						try {
							GeneralExtentions.Extention.LocationInfo locationInfo=GeneralExtentions.Extention.GelLocationStatus(context);
							if (!locationInfo.status)
                            {
                                    String message = "به دلیل عدم اتصال " + " GPS " + "امکان ثبت انشعاب غیرمجاز وجود ندارد";
                                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                                    return;
                            }

								SelectillegalBranchByDate selectillegalBranchByDate=new SelectillegalBranchByDate(db,GetDateNowWithFormat());
								FetchSelectOperation fetchSelectOperation=new FetchSelectOperation(selectillegalBranchByDate);
								Integer count= fetchSelectOperation.fetch(illegalBranchCountDto.class).get(0).count;
								if (count>20)
								{
									Toast.makeText(getApplicationContext(), "حداکثر تعداد ثبت انشعاب غیرمجاز در روزجاری 20 مورد می باشد", Toast.LENGTH_SHORT).show();
									finddialog.dismiss();
									return;
								}

								InsertIllegalBranch insertIllegalBranch=new InsertIllegalBranch(db,
										txt_illigalbranch_serialno.getText().toString(),
										GetDateNowWithFormat(),
										txt_illigalbranch_comment.getText().toString(),
										locationInfo.latitude,
										locationInfo.longitude,
										Integer.parseInt(illegalmobiletitleid),
										readingListItems.get(recno).EstateId);
								boolean result = insertIllegalBranch.execute();

								SelectillegalBranchInsertedId selectillegalBranchInsertedId=new SelectillegalBranchInsertedId(db);
								Integer insertedid= new FetchSelectOperation(selectillegalBranchInsertedId).fetch(illegalBranchInsertedIdDto.class).get(0).Insertedid;

								for (IllegalBranchPicture item : illigalBranchPicturelist)
								{
									InsertPicture insertpicture=new InsertPicture(db,
                                            insertedid,
                                            item.filepath(),
                                            GetDateNowWithFormat(),
                                            item.filename(),
                                            item.Picturetype(),
                                            readingListItems.get(recno).EstateId
                                            );
                                    insertpicture.execute();
								}

								if (result)
								{
									Toast.makeText(getApplicationContext(), "گزارش با موفقیت ثبت گردید", Toast.LENGTH_SHORT).show();
									finddialog.dismiss();
									return;
								}

								Toast.makeText(getApplicationContext(), "خطا در ثبت انشعاب گزارش", Toast.LENGTH_SHORT).show();
								finddialog.dismiss();


						} catch (Exception error) {
							Toast.makeText(getApplicationContext(), "خطا در ثبت انشعاب غیرمجاز", Toast.LENGTH_SHORT).show();
						}
					}
				});

				btn_cancel.setOnClickListener(new OnClickListener() {

					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						finddialog.dismiss();
					}
				});

//region picicon onclick
				picicon.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View view) {
						String Estateid = String.valueOf(input.get(recno).get_EstateId());
						File dir = new File(Environment.getExternalStorageDirectory() + "/ABFA/Gallery/Pic/" + Estateid + "/" + "Temp1" + file_opp.MakeFileNameWithDateTime());
						try {
							file_opp.MakeDirectory("/ABFA/Gallery/Pic/" + Estateid);
							IntenTakePicture intenTakePicture=new IntenTakePicture(GetFileUri(context,dir),dir.getAbsolutePath(),R.id.textView_pic);
							startActivityForResult(intenTakePicture.Getintent(), TAKE_PHOTO_CODE);
						} catch (Exception error) {
							Log.e("Error", error.getMessage());
						}
					}

				});
				picicon2.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View view) {
						String Estateid = String.valueOf(input.get(recno).get_EstateId());
						File dir = new File(Environment.getExternalStorageDirectory() + "/ABFA/Gallery/Pic/" + Estateid + "/" + "Temp1" + file_opp.MakeFileNameWithDateTime());
						try {
							file_opp.MakeDirectory("/ABFA/Gallery/Pic/" + Estateid);
							IntenTakePicture intenTakePicture=new IntenTakePicture(GetFileUri(context,dir),dir.getAbsolutePath(),R.id.textView_pic2);
							startActivityForResult(intenTakePicture.Getintent(), TAKE_PHOTO_CODE);
						} catch (Exception error) {
							Log.e("Error", error.getMessage());
						}
					}

				});
				picicon3.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View view) {
						String Estateid = String.valueOf(input.get(recno).get_EstateId());
						File dir = new File(Environment.getExternalStorageDirectory() + "/ABFA/Gallery/Pic/" + Estateid + "/" + "Temp1" + file_opp.MakeFileNameWithDateTime());
						try {
								file_opp.MakeDirectory("/ABFA/Gallery/Pic/" + Estateid);
								IntenTakePicture intenTakePicture=new IntenTakePicture(GetFileUri(context,dir),dir.getAbsolutePath(),R.id.textView_pic3);
								startActivityForResult(intenTakePicture.Getintent(), TAKE_PHOTO_CODE);
						} catch (Exception error) {
							Log.e("Error", error.getMessage());
						}
					}

				});
//endregion
				finddialog.show();


			}
		});








		fabButton.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				SearchTypeText = "";
				searchbynamedialog = new Dialog(context);
				searchbynamedialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
				searchbynamedialog.setContentView(R.layout.searchbynamelist);
				searchbynamedialog.setCancelable(false);
				final ListView L_Search = (ListView) searchbynamedialog.findViewById(R.id.list_searchbyname);
				Button btn_searchbynamedialog_cancel = (Button) searchbynamedialog.findViewById(R.id.button_cancel_searchbyname);
				btn_searchbynamedialog_cancel.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						searchbynamedialog.dismiss();
					}
				});
				btn_searchbynamedialog_cancel.setTypeface(font);
				finddialog = new Dialog(context);
				finddialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
				finddialog.setContentView(R.layout.dialog_find);
				finddialog.setCancelable(false);
				TextView DialogTitle = (TextView) finddialog.findViewById(R.id.textView1);
				final EditText finddialog_value = (EditText) finddialog.findViewById(R.id.txt_finddialog_value);
				Button btn_ok = (Button) finddialog.findViewById(R.id.btn_finddialog_ok);
				Button btn_cancel = (Button) finddialog.findViewById(R.id.btn_finddialog_cancel);

				DialogTitle.setTypeface(TitrB);
				btn_ok.setTypeface(font);
				btn_cancel.setTypeface(font);
				finddialog_value.setTypeface(font);

				SearchType = (Spinner) finddialog.findViewById(R.id.spinner1);
				ArrayList<String> SearchList = new ArrayList<String>();
				SearchList.add("شماره اشتراک");
				SearchList.add("شماره تنه کنتور");
				SearchList.add("شماره پلمپ");
				SearchList.add("نام مشترک");
				SearchList.add("کد ملی");
				SearchList.add("موبایل");
				Adapter_SearchItems dataAdapter = new Adapter_SearchItems(GeraatActivity.this, R.layout.rowstyle_codemane, R.id.txt_namelist_row_h, SearchList);
				SearchType.setAdapter(dataAdapter);
				finddialog.getWindow().setSoftInputMode(android.view.WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

				SearchType.setOnItemSelectedListener(new OnItemSelectedListener() {

					public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
						TextView tc = (TextView) view.findViewById(R.id.txt_namelist_row_h);
						String Stemp = tc.getText().toString();
						SearchTypeText = Stemp;
						finddialog_value.setText("");
						finddialog_value.setHint(tc.getText());

						if (Stemp.compareTo("شماره اشتراک") == 0)
							finddialog_value.setInputType(InputType.TYPE_CLASS_NUMBER);
						if (Stemp.compareTo("شماره تنه کنتور") == 0)
							finddialog_value.setInputType(InputType.TYPE_CLASS_NUMBER);
						if (Stemp.compareTo("شماره پلمپ") == 0)
							finddialog_value.setInputType(InputType.TYPE_CLASS_NUMBER);
						if (Stemp.compareTo("نام مشترک") == 0)
							finddialog_value.setInputType(InputType.TYPE_CLASS_TEXT);
						if (Stemp.compareTo("کد ملی") == 0)
							finddialog_value.setInputType(InputType.TYPE_CLASS_NUMBER);
					}

					public void onNothingSelected(AdapterView<?> parent) {
						// TODO Auto-generated method stub

					}
				});

				btn_ok.setOnClickListener(new OnClickListener() {
					public void onClick(View arg0) {
						arr_temp.clear();
						String t = finddialog_value.getText().toString();
						String value = finddialog_value.getText().toString();
						char[] myNameChars = t.toCharArray();

						if (SearchTypeText.compareTo("شماره اشتراک") == 0)
						{
							SearchBySerilano searchbyserilano = new SearchBySerilano(input, value);
							Search(searchbyserilano);
						}
						if (SearchTypeText.compareTo("کد ملی") == 0)
						{
							SearchByNationalCode searchbynationalcode = new SearchByNationalCode(input, value);
							Search(searchbynationalcode);
						}
						if (SearchTypeText.compareTo("شماره تنه کنتور") == 0)
						{
							SearchByMeterTrunk searchbymetertrunk = new SearchByMeterTrunk(input, value);
							Search(searchbymetertrunk);
						}
						if (SearchTypeText.compareTo("شماره پلمپ") == 0)
						{
							SearchByMeterPolump searchbymeterpolump = new SearchByMeterPolump(input, value);
							Search(searchbymeterpolump);
						}
						if (SearchTypeText.compareTo("نام مشترک") == 0)
						{
							SearchByName searchbyname = new SearchByName(input, value);
							Search(searchbyname);
						}
						if (SearchTypeText.compareTo("موبایل") == 0)
						{
							SearchByMobile searchbymobile = new SearchByMobile(input, value);
							Search(searchbymobile);
						}

					}
				});

				btn_cancel.setOnClickListener(new OnClickListener() {

					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						finddialog.dismiss();
					}
				});
				finddialog.show();

			}
		});

		txt_search_eshterak = (EditText) findViewById(R.id.txt_search_box);
		txt_search_eshterak.setTypeface(font);
		txt_search_eshterak.setOnFocusChangeListener(new OnFocusChangeListener() {
			public void onFocusChange(View v, boolean hasFocus) {

			}
		});

		//endregion

		try {

			SelectOfficerPermission selectOfficerPermission=new SelectOfficerPermission(db);
			OfficerPermissionDto officerPermission=selectOfficerPermission.fetch(OfficerPermissionDto.class).get(0);


			//officerpermission = new OfficerPermissions(da.Select_OfficerPermissions(), OfficerPermissions.PermissionActivityName.GeraatActivity);

			DisplayOldData displayOldDatapermission=new DisplayOldData(officerPermission.displayolddata,
					R.id.displayolddata,
					getWindow().getDecorView().getRootView());
			displayOldDatapermission.Handle();

			Pressure pressure=new Pressure(officerPermission.pressure,
					Btn_pressure.getId(),
					getWindow().getDecorView().getRootView());
			pressure.Handle();


			ArrayListCodeMane = da.list_code_mane(true);
			ArrayListCodeMane_Text = da.list_code_mane(false);
			ReadingListDto readingListDto=da.Get_Id_Active_ListGheraat().get(0);


			if (readingListDto!=null) {
				databasetest._FKId_List = readingListDto.Id_List.toString();
				recno = Integer.parseInt(readingListDto.CurrentRecord);

				readingListItems=da.select_data_from_database(readingListDto.Id_List.toString());
				//input = da.select_data_from_database(readingListDto.Id_List.toString());
				if (input.size() == 0) {
					Toast.makeText(getApplicationContext(), "اطلاعاتی جهت قرائت وجود ندارد", Toast.LENGTH_LONG).show();
					finish();
					return;
				}
				noe_gherat.setText(name_of_noee_gheraat(input.get(recno).get_code_mane_feli()));
				reccount = input.size();
			} else {
				Toast.makeText(getApplicationContext(), "اطلاعاتی جهت قرائت بارگذاری نشده است و یا لیست جهت قرائت انتخاب نشده است", Toast.LENGTH_LONG).show();
				finish();
			}
		} catch (Exception e) {
			// TODO: handle exception
			Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
			Toast.makeText(getApplicationContext(), "خطا در دریافت اطلاعات از دیتابیس", Toast.LENGTH_SHORT).show();
		}
		//TextView txt_date=(TextView)findViewById(R.id.txt_display_date);
		final TextView txt_name_moshtarek = (TextView) findViewById(R.id.txt_name_moshtarek);
		final TextView txt_radif_tashkhis = (TextView) findViewById(R.id.txt_radif_tashkhis);
		final TextView txt_eshterak = (TextView) findViewById(R.id.txt_eshterak_number);
		final EditText txt_karkard_feli = (EditText) findViewById(R.id.txt_karkard_feli);
		final TextView txt_address = (TextView) findViewById(R.id.txt_address);
		final TextView txt_meghdare_ghabli = (TextView) findViewById(R.id.txt_meghdare_ghabli);
		final TextView txt_tarikh_ghabli = (TextView) findViewById(R.id.txt_readdateold);
		TextView txt_tarikh_ghabli_title = (TextView) findViewById(R.id.txt_readdateold_title);
		final TextView txt_bedehi = (TextView) findViewById(R.id.txt_bedehi);
		final TextView txt_manee_ghabli = (TextView) findViewById(R.id.txt_manee_ghabli);
		TextView txt_ShomareKontor = (TextView) findViewById(R.id.txt_kontor_number);
		txt_ShomareKontor.setTypeface(font);
		txt_name_moshtarek.setTypeface(font);
		txt_radif_tashkhis.setTypeface(font);
		txt_eshterak.setTypeface(TitrB);
		txt_karkard_feli.setTypeface(font);
		txt_address.setTypeface(font);
		txt_meghdare_ghabli.setTypeface(font);
		txt_tarikh_ghabli_title.setTypeface(font);
		txt_tarikh_ghabli.setTypeface(font);
		txt_bedehi.setTypeface(font);
		txt_manee_ghabli.setTypeface(font);
		//---------------------------------------------------------------------------------------------------

		final Button btn_mobile = (Button) findViewById(R.id.btn_mobile);

		Button btn_find = (Button) findViewById(R.id.btn_find_user);
		final ImageButton btn_save_next = (ImageButton) findViewById(R.id.btn_save_next);
		ImageButton btn_last = (ImageButton) findViewById(R.id.btn_last);
		ImageButton btn_first = (ImageButton) findViewById(R.id.btn_first);
		final ImageButton btn_next = (ImageButton) findViewById(R.id.btn_next);
		ImageButton btn_pre = (ImageButton) findViewById(R.id.btn_pre);
		Button btn_display_report = (Button) findViewById(R.id.btn_report);
		btn_display_report.setTypeface(font);
		//btn_mobile.setTypeface(font);

		btn_find.setTypeface(Fontawesome);
		btn_find.setText(new String(new char[]{0xf002}));


		fabButton1.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//ArrayList<OfficerDto> officerDtoList=da.Select_Code_MamoorOnly_FromList(databasetest._FKId_List);
				String[] temp = da.Select_Code_Mamoor_FromList();



				final Dialog dialog = new Dialog(context);
				dialog.setContentView(R.layout.code_gheraat);

				ListView list_code_mane = (ListView) dialog.findViewById(R.id.listView_code_gherat);
				LinearLayout LayoutTitle = (LinearLayout) dialog.findViewById(R.id.title_list_dialog_line);
				LayoutTitle.setVisibility(View.GONE);

				LinearLayout LayoutTitle1 = (LinearLayout) dialog.findViewById(R.id.title_list_dialog_singlelinelayout);
				LayoutTitle1.setVisibility(View.VISIBLE);

				TextView txttitleoflist = (TextView) dialog.findViewById(R.id.txt_namelist_row_simple);
				txttitleoflist.setText("لیست کدهای مامور");
				txttitleoflist.setTypeface(font);
				//ArrayAdapter<String> ada=new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, temp);
				list_code_mane.setAdapter(new Adapter_NoeGheraat(context, temp));
				dialog.show();

				list_code_mane.setOnItemClickListener(new OnItemClickListener() {

					public void onItemClick(AdapterView<?> arg0, View arg1,
											int arg2, long arg3) {
						// TODO Auto-generated method stub
						TextView txttemp = (TextView) arg1.findViewById(R.id.txt_namelist_row_h);
						if (txttemp.getText().toString().contains("همه مامورها")) {
							databasetest._CodeMamor = "";
						} else {
							databasetest._CodeMamor = temp_OnlyCodeMamor[arg2 - 1];
						}

						recnoforupdate = 0;
						recno = 0;

						input = da.select_data_from_database_by_codemamor();
						ChangeCodeMamor = true;
						SelectRecordMod = Select_Record_FromDatabase.CodeMamoor;
						noe_gherat.setText(name_of_noee_gheraat(input.get(recno).get_code_mane_feli()));
						reccount = input.size();
						recno = getrecnolast("");
						set_data_to_textbox(move_record.recordnumberchangemamor);
						dialog.hide();
						ArrayList<AddData> adddata_temp = da.Get_Roozkar();
						if (adddata_temp.size() > 1) {

							ListView list_code_mane = (ListView) dialog.findViewById(R.id.listView_code_gherat);
							TextView txttitleoflist = (TextView) dialog.findViewById(R.id.txt_namelist_row_simple);
							txttitleoflist.setText("لیست روزکارهای موجود");
							txttitleoflist.setTypeface(font);

							String[] temp = da.Select_Code_Mamoor_FromList();
							list_code_mane.setAdapter(new Adapter_Rozkar(context, da.Get_Roozkar_List(databasetest._FKId_List,databasetest._CodeMamor)));
							dialog.show();
							list_code_mane.setOnItemClickListener(new OnItemClickListener() {

								public void onItemClick(AdapterView<?> arg0, View arg1,
														int arg2, long arg3) {
									SelectRecordMod = Select_Record_FromDatabase.CodeMamoorRoozkar;
									TextView txttemp = (TextView) arg1.findViewById(R.id.txt_namelist_row_h);
									databasetest._roozkar = txttemp.getText().toString();
									recnoforupdate = 0;
									recno = 0;
									readingListItems=da.select_data_from_database_by_codemamorroozkar(databasetest._FKId_List,databasetest._CodeMamor,databasetest._roozkar);
									//input = da.select_data_from_database_by_codemamorroozkar(databasetest._FKId_List,databasetest._CodeMamor,databasetest._roozkar);

									noe_gherat.setText(name_of_noee_gheraat(input.get(recno).get_code_mane_feli()));
									reccount = input.size();
									recno = getrecnolast("");
									set_data_to_textbox(move_record.recordnumberchangemamor);
									dialog.hide();
								}
							});


						}
					}
				});


			}
		});


		fabButton3.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				String[] temp = da.Select_Gozaresh_Gheraat();


				final Dialog dialog = new Dialog(context);
				dialog.setContentView(R.layout.code_gheraat);
				TextView txttitleoflist = (TextView) dialog.findViewById(R.id.txt_namelist_row_simple);

				if (databasetest._CodeMamor.compareTo("") == 0)
					txttitleoflist.setText("خلاصه گزارش قرائت (همه مامورها)");
				else
					txttitleoflist.setText("خلاصه گزارش قرائت کد مامور (" + databasetest._CodeMamor + ")");

				ListView list_code_mane = (ListView) dialog.findViewById(R.id.listView_code_gherat);
				LinearLayout LayoutTitle = (LinearLayout) dialog.findViewById(R.id.title_list_dialog_line);
				LayoutTitle.setVisibility(View.GONE);
				LinearLayout LayoutTitle1 = (LinearLayout) dialog.findViewById(R.id.title_list_dialog_singlelinelayout);
				LayoutTitle1.setVisibility(View.VISIBLE);
				txttitleoflist.setTypeface(font);

				list_code_mane.setAdapter(new Adapter_NoeGheraat(context, temp));
				list_code_mane.setOnItemClickListener(new OnItemClickListener() {

					public void onItemClick(AdapterView<?> arg0, View arg1,
											int arg2, long arg3) {
						TextView t = (TextView) arg1.findViewById(R.id.txt_namelist_row_h);

						String Item[] = t.getText().toString().split(",");
						da._CodeMane = da.Select_Code_Mane_ByName(Item[0]);

						if (databasetest._CodeMamor.compareTo("") == 0) {
							input = da.select_data_from_database_by_CodeMane(da.Select_Code_Mane_ByName(Item[0]));
							SelectRecordMod = Select_Record_FromDatabase.CodeMane;
						} else
							{
							input = da.select_data_from_database_by_codemamorcodemane(da.Select_Code_Mane_ByName(Item[0]));
							SelectRecordMod = Select_Record_FromDatabase.CodeMamoorCodeMane;
						}
						reccount = input.size();
						recno = 0;
						Log.e("Code mane", input.get(recno).get_code_mane_feli());
						noe_gherat.setText(name_of_noee_gheraat(input.get(recno).get_code_mane_feli()));
						set_data_to_textbox(move_record.recordnumberchangemamor);
						dialog.hide();
					}
				});
				dialog.show();
			}
		});
//---------------------------------------------------------------------------------------------------


		//region Further_informationstate
		txt_description_edit_icon.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				Further_informationstate = Further_information.officerdescription;
				btn_mobile.performClick();
			}
		});

		txt_mobile_edit_icon.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				Further_informationstate = Further_information.mobile;
				btn_mobile.performClick();
			}
		});

		txt_radiftashkhis_edit_icon.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {

				final String currentaddress=readingListItems.get(recno).address;
				final DetectionRowDialog detectionrowdialog=new DetectionRowDialog(context,R.layout.dialog_address,R.id.btn_make_out_file,R.id.btn_ret,currentaddress,R.id.txt_address);
				detectionrowdialog.Ok(new IButtonAction() {
					@Override
					public void Action() {
						TextView txtaddress=(TextView) detectionrowdialog.GetDialog().findViewById(R.id.txt_address);
						String newaddress=txtaddress.getText().toString();
						if (Validation.Address(newaddress)==false){
							Toast.makeText(context,Validation.ValidationMessage,Toast.LENGTH_SHORT).show();
							return;
						}

						if (currentaddress.compareTo(newaddress)==0)
							return;

						UpdateAddress updateAddress=
								new UpdateAddress(db,newaddress,readingListItems.get(recno).EstateId,readingListItems.get(recno).FKId_List);
						updateAddress.execute();
						detectionrowdialog.Hide();
					}
				}).Cancle(new IButtonAction() {
					@Override
					public void Action() {
						detectionrowdialog.Hide();
					}
				}).Show();

			}
		});

		txt_tanehpolomp_edit_icon.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				final String currenmetertrunk=readingListItems.get(recno).shomare_kontor;
				final String currenmeterpolump=readingListItems.get(recno).model_kontor;

				final MeterTrunkPolumpDialog metertrunkpolumpdialog=new MeterTrunkPolumpDialog(context,R.layout.dialog_address,R.id.btn_make_out_file,R.id.btn_ret,currentaddress,R.id.txt_address);
				metertrunkpolumpdialog.Ok(new IButtonAction() {
					@Override
					public void Action() {
						TextView txtaddress=(TextView) metertrunkpolumpdialog.GetDialog().findViewById(R.id.txt_address);
						String newaddress=txtaddress.getText().toString();
						if (Validation.Address(newaddress)==false){
							Toast.makeText(context,Validation.ValidationMessage,Toast.LENGTH_SHORT).show();
							return;
						}

						if (currenmetertrunk.compareTo(newaddress)==0)
							return;

						UpdateAddress updateAddress=
								new UpdateAddress(db,newaddress,readingListItems.get(recno).EstateId,readingListItems.get(recno).FKId_List);
						updateAddress.execute();
						metertrunkpolumpdialog.Hide();
					}
				}).Cancle(new IButtonAction() {
					@Override
					public void Action() {
						metertrunkpolumpdialog.Hide();
					}
				}).Show();
			}
		});

		txt_address_edit_icon.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				final String currentaddress=readingListItems.get(recno).address;
				final AddressDialog addressDialog=new AddressDialog(context,R.layout.dialog_address,R.id.btn_make_out_file,R.id.btn_ret,currentaddress,R.id.txt_address);
				addressDialog.Ok(new IButtonAction() {
					@Override
					public void Action() {
						TextView txtaddress=(TextView) addressDialog.GetDialog().findViewById(R.id.txt_address);
						String newaddress=txtaddress.getText().toString();
						if (Validation.Address(newaddress)==false){
							Toast.makeText(context,Validation.ValidationMessage,Toast.LENGTH_SHORT).show();
							return;
						}

						if (currentaddress.compareTo(newaddress)==0)
							return;

						UpdateAddress updateAddress=
								new UpdateAddress(db,newaddress,readingListItems.get(recno).EstateId,readingListItems.get(recno).FKId_List);
						updateAddress.execute();
						addressDialog.Hide();
					}
				}).Cancle(new IButtonAction() {
					@Override
					public void Action() {
						addressDialog.Hide();
					}
				}).Show();
			}
		});

		txt_postalcode_edit_icon.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				final String currentpostalcode=readingListItems.get(recno).code_posti;
				final PostalCodeDialog postalCodeDialog=new
						PostalCodeDialog(context,R.layout.dialog_postalcode,R.id.btn_make_out_file,R.id.btn_ret,currentpostalcode,R.id.txt_postallcode);
				postalCodeDialog.
						Ok(new IButtonAction() {
					@Override
					public void Action() {

											TextView txtpostalcode=(TextView) postalCodeDialog.GetDialog().findViewById(R.id.txt_postalcode);
											String newpostalcode=txtpostalcode.getText().toString();
											if (Validation.Postalcode(newpostalcode)==false){
												Toast.makeText(context,Validation.ValidationMessage,Toast.LENGTH_SHORT).show();
												return;
											}

											if (currentpostalcode.compareTo(newpostalcode)==0)
												return;

											UpdatePostalCode updatePostalCode=
											new UpdatePostalCode(db,
													newpostalcode,
													readingListItems.get(recno).EstateId,
													readingListItems.get(recno).FKId_List);
											updatePostalCode.execute();
											postalCodeDialog.Hide();
					}
				}).
						Cancle(new IButtonAction() {
					@Override
					public void Action() {
											postalCodeDialog.Hide();
					}
				}).Show();


			}
		});

		//--------------------------vazeyat enshab-----------------------------------------------------
		txt_vazyatenshab_edit_icon.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				ArrayList<AddData> ArrayListAddData = new ArrayList<AddData>();
				ArrayListAddData = da.Select_Branchstatus();

				SelectBranchStatus selectBranchStatus=new SelectBranchStatus(db);
				ArrayList<BranchStatusDto> branchStatusDtoList=new FetchSelectOperation(selectBranchStatus).fetch(BranchStatusDto.class);

				final Dialog dialog = new Dialog(context);
				dialog.setContentView(R.layout.code_gheraat_new);
				LinearLayout LayoutTitle1 = (LinearLayout) dialog.findViewById(R.id.title_list_dialog_singlelinelayout_gheraat);
				LayoutTitle1.setVisibility(View.VISIBLE);

				TextView txttitleoflist = (TextView) dialog.findViewById(R.id.txt_namelist_row_simple);
				txttitleoflist.setText("وضعیت انشعاب");
				txttitleoflist.setTypeface(font);


				ListView list_code_mane = (ListView) dialog.findViewById(R.id.listView_code_gherat);
				list_code_mane.setAdapter(new Adapter_BranchStatus(context, branchStatusDtoList));

				list_code_mane.setOnItemClickListener(new OnItemClickListener() {
					public void onItemClick(AdapterView<?> arg0, View arg1,
											int arg2, long arg3) {
						String BranchStatusid = "0", BranchStatustitle = "";
						BranchStatusid = ((TextView) arg1.findViewById(R.id.txt_roozkar_row_h)).getText().toString();
						BranchStatustitle = ((TextView) arg1.findViewById(R.id.txt_namelist_row_h)).getText().toString();

						String strDate =GetDateNowWithFormat();
						BranchStatus branchStatus=new BranchStatus(readingListItems.get(recno).eshterak,
								readingListItems.get(recno).FKId_List,readingListItems.get(recno).EstateId,
								strDate,Integer.valueOf(BranchStatusid));

						Peymayesh peymayesh = new Peymayesh();
						peymayesh.set_Field(8);
						peymayesh.set_NewValue(BranchStatusid);
						peymayesh.set_Serialno(input.get(recno).get_eshterak());
						peymayesh.set_Idlist(Integer.valueOf(input.get(recno).get_FKId_List()));
						peymayesh.set_DateSabt(strDate);



						BranchStatusDto branchStatusDto=da.Peymayesh_GetBranchstatus(readingListItems.get(recno).eshterak);
						if (ExistingPeymayesh.get_Value() == null) {
							da.Insert_Peymayesh(
									new Object[]
											{
													peymayesh.get_Field(),
													peymayesh.get_NewValue(),
													peymayesh.get_Serialno(),
													peymayesh.get_Idlist(),
													peymayesh.get_DateSabt()
											});
						} else {
							ExistingPeymayesh.set_NewValue(BranchStatusid);
							ExistingPeymayesh.set_DateSabt(strDate);

							UpdatePeymayesh updatePeymayesh=new UpdatePeymayesh(db,"","",0);
							updatePeymayesh.execute();

							da.Update_Peymayesh(
									new Object[]{
											ExistingPeymayesh.get_NewValue(),
											ExistingPeymayesh.get_DateSabt(),
											ExistingPeymayesh.get_id()
									});
						}

						input.get(recno).set_Vaziat(Integer.valueOf(BranchStatusid));
						input.get(recno).set_VaziatTitle(BranchStatustitle);


						UpdateBranchStatus updateBranchStatus=new UpdateBranchStatus(db,
								readingListItems.get(recno).FKId_List.toString(),
								readingListItems.get(recno).eshterak,
								Integer.parseInt(BranchStatusid));
						updateBranchStatus.execute();


						txt_vazyat_enshab_value.setText(BranchStatustitle);
						if (Integer.valueOf(BranchStatusid) > 1)
						{
								txt_karkard_feli.setEnabled(false);
								btn_save_next.setEnabled(false);
								btn_save_next.setImageResource(R.mipmap.illegal);
						}
						else
						{
								txt_karkard_feli.setEnabled(true);
								btn_save_next.setEnabled(true);
								btn_save_next.setImageResource(R.mipmap.saveandnext);
						}

						dialog.dismiss();
					}
				});
				dialog.show();
			}
		});

		//-------------------------------------------------------------------------------

		txt_vazyatmelk_edit_icon.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				ArrayList<EstateConditionDto> estateConditionList = da.Select_EstateCondition();

				final Dialog dialog = new Dialog(context);
				dialog.setContentView(R.layout.code_gheraat_new);
				LinearLayout LayoutTitle1 = (LinearLayout) dialog.findViewById(R.id.title_list_dialog_singlelinelayout_gheraat);
				LayoutTitle1.setVisibility(View.VISIBLE);

				TextView txttitleoflist = (TextView) dialog.findViewById(R.id.txt_namelist_row_simple);
				txttitleoflist.setText("وضعیت ملک");
				txttitleoflist.setTypeface(font);


				ListView list_code_mane = (ListView) dialog.findViewById(R.id.listView_code_gherat);
				list_code_mane.setAdapter(new Adapter_EstateCondition(context, estateConditionList));

				list_code_mane.setOnItemClickListener(new OnItemClickListener() {
					public void onItemClick(AdapterView<?> arg0, View arg1,
											int arg2, long arg3) {
						String EstateConditionId = "0", EstateConditionTitle = "";
						EstateConditionId = ((TextView) arg1.findViewById(R.id.txt_roozkar_row_h)).getText().toString();
						EstateConditionTitle = ((TextView) arg1.findViewById(R.id.txt_namelist_row_h)).getText().toString();

						String strDate = GetDateNowWithFormat();

						Peymayesh peymayesh = new Peymayesh();
						peymayesh.set_Field(7);
						peymayesh.set_NewValue(EstateConditionId);
						peymayesh.set_Serialno(input.get(recno).get_eshterak());
						peymayesh.set_Idlist(Integer.valueOf(input.get(recno).get_FKId_List()));
						peymayesh.set_DateSabt(strDate);


						if (EstateConditionId.compareTo("5") == 0) noe_gherat.setEnabled(false);
						//Peymayesh ExistingPeymayesh = da.Peymayesh_GetEstateCondition(peymayesh);
						EstateConditionDto estateConditionDto=da.Peymayesh_GetEstateCondition(readingListItems.get(recno).eshterak);
						if (ExistingPeymayesh.get_Value() == null) {
							da.Insert_Peymayesh(
									new Object[]
											{
													peymayesh.get_Field(),
													peymayesh.get_NewValue(),
													peymayesh.get_Serialno(),
													peymayesh.get_Idlist(),
													peymayesh.get_DateSabt()
											});
						} else {

								ExistingPeymayesh.set_NewValue(EstateConditionId);
								ExistingPeymayesh.set_DateSabt(strDate);

								da.Update_Peymayesh(
									new Object[]{
											ExistingPeymayesh.get_NewValue(),
											ExistingPeymayesh.get_DateSabt(),
											ExistingPeymayesh.get_id()
									});
						}

						input.get(recno).set_EstateConditionid(Integer.parseInt(EstateConditionId));
						input.get(recno).set_EstateConditionTitle(EstateConditionTitle);

						UpdateEstateCondition updateEstateCondition=new UpdateEstateCondition(db,readingListItems.get(recno).FKId_List.toString(),readingListItems.get(recno).eshterak,Integer.valueOf(EstateConditionId));
						updateEstateCondition.execute();

						txt_vazeyat_melk_value.setText(EstateConditionTitle);
						if (Integer.valueOf(EstateConditionId) > 1)
						{
							noe_gherat.setEnabled(false);
							txt_karkard_feli.setEnabled(false);
							btn_save_next.setEnabled(false);
							btn_save_next.setImageResource(R.mipmap.illegal);
						} else
							{
							txt_karkard_feli.setEnabled(true);
							btn_save_next.setEnabled(true);
							btn_save_next.setImageResource(R.mipmap.saveandnext);
							noe_gherat.setEnabled(true);
						}

						dialog.dismiss();
					}
				});
				dialog.show();
			}
		});

		// endregion


		btn_mobile.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				Peymayesh = da.Peymayesh_GetMobile(input.get(recno).get_eshterak(), da.Select_AddData());

				final Dialog dialog = new Dialog(context);
				dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
				dialog.setContentView(R.layout.dialog_out);
				Display_Further_information(Further_informationstate, dialog);

				dialog.getWindow().setSoftInputMode(android.view.WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
				dialog.setTitle("ورود اطلاعات تکمیلی");
				EditText txt_mobile = (EditText) dialog.findViewById(R.id.txt_out_file_name);
				EditText txt_polomp = (EditText) dialog.findViewById(R.id.txt_polomp_no);
				EditText txt_radif = (EditText) dialog.findViewById(R.id.txt_radiftashkhis);

				EditText txt_postalcode = (EditText) dialog.findViewById(R.id.txt_postalcode);
				EditText txt_address = (EditText) dialog.findViewById(R.id.txt_address);
				EditText txt_tane = (EditText) dialog.findViewById(R.id.txt_taneh_no);

				EditText txt_officerdiscription = (EditText) dialog.findViewById(R.id.txt_officerdiscription);

				TextView txttitle = (TextView) dialog.findViewById(R.id.textView1);


				TextView lbl_mobile = (TextView) dialog.findViewById(R.id.lbl_mobile);
				TextView lbl_polomp = (TextView) dialog.findViewById(R.id.lbl_polomp);
				TextView lbl_radif = (TextView) dialog.findViewById(R.id.lbl_radif);

				TextView lbl_taneh = (TextView) dialog.findViewById(R.id.lbl_tanehkontor);
				TextView lbl_addresss = (TextView) dialog.findViewById(R.id.lbl_address);
				TextView lbl_postal = (TextView) dialog.findViewById(R.id.lbl_postalcode);

				TextView lbl_officerdiscription = (TextView) dialog.findViewById(R.id.lbl_officerdiscription);


				lbl_mobile.setTypeface(font);
				lbl_polomp.setTypeface(font);
				lbl_radif.setTypeface(font);
				lbl_taneh.setTypeface(font);
				lbl_addresss.setTypeface(font);
				lbl_postal.setTypeface(font);
				lbl_officerdiscription.setTypeface(font);

				txt_officerdiscription.setTypeface(font);
				txttitle.setTypeface(font);
				txt_polomp.setTypeface(font);
				txt_radif.setTypeface(font);
				txt_mobile.setTypeface(font);

				txt_postalcode.setTypeface(font);
				txt_tane.setTypeface(font);
				txt_address.setTypeface(font);

				String TypeOperation = "";
				switch (Further_informationstate) {
					case mobile:
						TypeOperation = "1";
						break;
					case Meterpolomp:
						TypeOperation = "2";
						break;
					case rowno:
						TypeOperation = "3";
						break;
					case Metertrunk:
						TypeOperation = "4";
						break;
					case address:
						TypeOperation = "5";
						break;
					case postalcode:
						TypeOperation = "6";
						break;
					case officerdescription:
						TypeOperation = "-1";
						break;
					default:
						break;
				}

				if (Further_informationstate != Further_information.officerdescription) {
					for (AddData temp : Peymayesh) {
						if (Further_informationstate != Further_information.all) {
							Boolean ContinueOperation = false;
							if ((Further_informationstate == Further_information.Meterpolomp)
									||
									(Further_informationstate == Further_information.Metertrunk))

								if ((temp.get_Created().toString().compareTo("2") == 0)
										||
										(temp.get_Created().toString().compareTo("4") == 0))
									ContinueOperation = true;

							if (ContinueOperation == false)
								if (temp.get_Created().toString().compareTo(TypeOperation) != 0)
									continue;
						}
						int key = temp.get_id();

						if (temp.get_Value().compareTo("NotFound") == 0) {
							switch (key) {
								case 1:
									txt_mobile.setText("");
									break;
								case 2:
									txt_polomp.setText("");
									break;
								case 3:
									txt_radif.setText("");
									break;
								case 4:
									txt_tane.setText("");
									break;
								case 5:
									txt_address.setText("");
									break;
								case 6:
									txt_postalcode.setText("");
									break;
								default:
									break;
							}
						} else {
							switch (key) {
								case 1:
									txt_mobile.setText(temp.get_Value());
									break;
								case 2:
									txt_polomp.setText(temp.get_Value());
									break;
								case 3:
									txt_radif.setText(temp.get_Value());
									break;
								case 4:
									txt_tane.setText(temp.get_Value());
									break;
								case 5:
									txt_address.setText(temp.get_Value());
									break;
								case 6:
									txt_postalcode.setText(temp.get_Value());
									break;
								default:
									break;
							}
						}
					}
				} else {
					txt_officerdiscription.setText(input.get(recno).get_OfficerDescription());

				}


				txt_mobile.setSelection(txt_mobile.getText().length());
				txt_polomp.setSelection(txt_polomp.getText().length());
				txt_radif.setSelection(txt_radif.getText().length());
				txt_tane.setSelection(txt_tane.getText().length());
				txt_address.setSelection(txt_address.getText().length());
				txt_postalcode.setSelection(txt_postalcode.getText().length());
				txt_officerdiscription.setSelection(txt_officerdiscription.getText().length());


				dialog.show();
				Button btn_ok = (Button) dialog.findViewById(R.id.btn_make_out_file);
				Button btn_cancel = (Button) dialog.findViewById(R.id.btn_ret);
				btn_ok.setTypeface(font);
				btn_cancel.setTypeface(font);
				btn_ok.setOnClickListener(new OnClickListener() {

					public void onClick(View arg0) {


						String strDate = GetDateNowWithFormat();

						EditText txt_mobile = (EditText) dialog.findViewById(R.id.txt_out_file_name);
						EditText txt_polomp = (EditText) dialog.findViewById(R.id.txt_polomp_no);
						EditText txt_radif = (EditText) dialog.findViewById(R.id.txt_radiftashkhis);

						EditText txt_codepostii = (EditText) dialog.findViewById(R.id.txt_postalcode);
						EditText txt_tanehkoontor = (EditText) dialog.findViewById(R.id.txt_taneh_no);
						EditText txt_addreess = (EditText) dialog.findViewById(R.id.txt_address);
						EditText txt_officerdiscription = (EditText) dialog.findViewById(R.id.txt_officerdiscription);

						txt_mobile.setTypeface(font);
						txt_polomp.setTypeface(font);
						txt_radif.setTypeface(font);
						txt_officerdiscription.setTypeface(font);


						txt_codepostii.setTypeface(font);
						txt_tanehkoontor.setTypeface(font);
						txt_addreess.setTypeface(font);

						//--------------------------------------------------------- validation

						switch (Further_informationstate) {
							case officerdescription:
								if (txt_officerdiscription.getText().length() <= 5) {
									txt_officerdiscription.setError("توضیحات حداقل شامل 5 حرف یا عدد می باشد");
									return;
								}
								break;
							case postalcode:
								if (txt_codepostii.getText().length() == 0) {
									txt_codepostii.setError("لطفا کد پستی را وارد کنید");
									return;
								}

								if (txt_codepostii.getText().length() >= 2) {
									if ((txt_codepostii.getText().toString().substring(0, 2).compareTo("63") != 0)
											&&
											(txt_codepostii.getText().toString().substring(0, 2).compareTo("64") != 0)) {
										txt_codepostii.setError("کد پستی استان خوزستان با 63,64آغاز می شود");
										return;
									}


								}

								if ((txt_codepostii.getText().length() < 10)) {
									txt_codepostii.setError("تعداد ارقام کد پستی 10 رقم می باشد");
									return;
								}

								break;
							case mobile:
								if (txt_mobile.getText().length() == 0) {
									txt_mobile.setError("لطفا شماره موبایل را وارد کنید");
									return;
								}
								if ((txt_mobile.getText().length() < 11) && (txt_mobile.getText().length() > 0)) {
									txt_mobile.setError("شماره موبایل 11 رقم می باشد");
									return;
								}
								if ((txt_mobile.getText().length() == 11)) {
									if (!txt_mobile.getText().toString().startsWith("09")) {
										txt_mobile.setError("شماره موبایل با 09 آغاز می شود");
										return;
									}
								}
								break;
							case Metertrunk:
							case Meterpolomp:

								Boolean Tane = false, Polomp = false;
								if (txt_tanehkoontor.getText().length() > 0) Tane = true;
								if (txt_polomp.getText().length() > 0) Polomp = true;

								if (Tane && Polomp) {
									if ((txt_tanehkoontor.getText().length() > 10) || (txt_tanehkoontor.getText().length() < 4)) {
										txt_tanehkoontor.setError("شماره تنه حداقل 4و حداکثر 10 رقم می باشد");
										return;
									}

									if ((txt_polomp.getText().length() > 10) || (txt_polomp.getText().length() < 4)) {
										txt_polomp.setError("شماره پلمپ حداقل 4و حداکثر 10 رقم می باشد");
										return;
									}


								}

								if ((Tane == true) && (Polomp == false)) {
									if ((txt_tanehkoontor.getText().length() > 10) || (txt_tanehkoontor.getText().length() < 4)) {
										txt_tanehkoontor.setError("شماره تنه حداقل 4و حداکثر 10 رقم می باشد");
										return;
									}
								}

								if ((Tane == false) && (Polomp == true)) {
									if ((txt_polomp.getText().length() > 10) || (txt_polomp.getText().length() < 4)) {
										txt_polomp.setError("شماره پلمپ حداقل 4و حداکثر 10 رقم می باشد");
										return;
									}
								}

								if ((Tane == false) && (Polomp == false)) {
									txt_tanehkoontor.setError("لطفا شماره تنه یا پلمپ کنتور را وارد کنید");
									return;
								}

								break;
							case rowno:
								if (txt_radif.getText().length() == 0) {
									txt_radif.setError("لطفا ردیف تشخیص را وارد کنید");
									return;
								}

								if ((txt_radif.getText().length() > 10) || (txt_radif.getText().length() < 1)) {
									txt_radif.setError("ردیف تشخیص حداقل 1و حداکثر 10 رقم می باشد");
									return;
								}
								break;
							case address:
								if (txt_addreess.getText().length() == 0) {
									txt_addreess.setError("لطفا آدرس را وارد کنید");
									return;
								}

								if ((txt_addreess.getText().length() > 300) || (txt_addreess.getText().length() < 5)) {
									txt_addreess.setError("آدرس مشترک حداقل 5و حداکثر 300 حرف یا رقم می باشد");
									return;
								}
								break;

							default:
								break;
						}


						//---------------------------------------------------------

						if (Further_informationstate != Further_information.officerdescription) {
							//region Further_informationstate!=Further_information.officerdescription
							Peymayesh peymayesh = null;
							ArrayList<Peymayesh> peymayeshlist = new ArrayList<Peymayesh>();



							PolompFurtherInformation polompFurtherInformation=
									new PolompFurtherInformation(input.get(recno).get_eshterak(),
											Integer.valueOf(input.get(recno).get_FKId_List()),
											input.get(recno).get_EstateId(),
											strDate,
											txt_polomp.getText().toString());

							DetectionrowFurtherInformation detectionrowFurtherInformation=
									new DetectionrowFurtherInformation(input.get(recno).get_eshterak(),
											Integer.valueOf(input.get(recno).get_FKId_List()),
											input.get(recno).get_EstateId(),
											strDate,
											txt_radif.getText().toString());

							PostalcodeFurtherInformation postalcodeFurtherInformation=
									new PostalcodeFurtherInformation(input.get(recno).get_eshterak(),
											Integer.valueOf(input.get(recno).get_FKId_List()),
											input.get(recno).get_EstateId(),
											strDate,
											txt_codepostii.getText().toString());

							MetertrunkFurtherInformation metertrunkFurtherInformation=
									new MetertrunkFurtherInformation(input.get(recno).get_eshterak(),
											Integer.valueOf(input.get(recno).get_FKId_List()),
											input.get(recno).get_EstateId(),
											strDate,
											txt_tanehkoontor.getText().toString());

							AddressFurtherInformation addressFurtherInformation=
									new AddressFurtherInformation(input.get(recno).get_eshterak(),
											Integer.valueOf(input.get(recno).get_FKId_List()),
											input.get(recno).get_EstateId(),
											strDate,
											txt_addreess.getText().toString());


							if (txt_mobile.getText().length() == 11) {
								//Field 1= Mobile

								MobileFurtherInformation mobileFurtherInformation=
										new MobileFurtherInformation(input.get(recno).get_eshterak(),
												Integer.valueOf(input.get(recno).get_FKId_List()),
												input.get(recno).get_EstateId(),
												strDate,
												txt_mobile.getText().toString());

								peymayesh = new Peymayesh();
								peymayesh.set_Field(1);
								peymayesh.set_NewValue(txt_mobile.getText().toString());
								peymayesh.set_Serialno(input.get(recno).get_eshterak());
								peymayesh.set_Idlist(Integer.valueOf(input.get(recno).get_FKId_List()));
								peymayesh.set_DateSabt(strDate);
								peymayesh.set_EstateId(input.get(recno).get_EstateId());
								peymayeshlist.add(peymayesh);
								da.update_Mobile(peymayesh);
								input.get(recno).set_Mobile(txt_mobile.getText().toString());

							}

							if (txt_polomp.getText().length() >= 4) {
								//Field 1= Mobile
								peymayesh = new Peymayesh();
								peymayesh.set_Field(2);
								peymayesh.set_NewValue(txt_polomp.getText().toString());
								peymayesh.set_Serialno(input.get(recno).get_eshterak());
								peymayesh.set_Idlist(Integer.valueOf(input.get(recno).get_FKId_List()));
								peymayesh.set_DateSabt(strDate);
								peymayeshlist.add(peymayesh);
							}

							if (txt_radif.getText().length() > 1) {
								//Field 1= Mobile
								peymayesh = new Peymayesh();
								peymayesh.set_Field(3);
								peymayesh.set_NewValue(txt_radif.getText().toString());
								peymayesh.set_Serialno(input.get(recno).get_eshterak());
								peymayesh.set_Idlist(Integer.valueOf(input.get(recno).get_FKId_List()));
								peymayesh.set_DateSabt(strDate);
								peymayeshlist.add(peymayesh);
							}


							if (txt_codepostii.getText().length() == 10) {
								//Field 4= Codeposti
								peymayesh = new Peymayesh();
								peymayesh.set_Field(6);
								peymayesh.set_NewValue(txt_codepostii.getText().toString());
								peymayesh.set_Serialno(input.get(recno).get_eshterak());
								peymayesh.set_Idlist(Integer.valueOf(input.get(recno).get_FKId_List()));
								peymayesh.set_DateSabt(strDate);
								peymayeshlist.add(peymayesh);
							}

							if (txt_tanehkoontor.getText().length() >= 4) {
								//Field 5= TanehNo
								peymayesh = new Peymayesh();
								peymayesh.set_Field(4);
								peymayesh.set_NewValue(txt_tanehkoontor.getText().toString());
								peymayesh.set_Serialno(input.get(recno).get_eshterak());
								peymayesh.set_Idlist(Integer.valueOf(input.get(recno).get_FKId_List()));
								peymayesh.set_DateSabt(strDate);
								peymayeshlist.add(peymayesh);
							}

							if (txt_addreess.getText().length() >= 5) {
								//Field 4= Codeposti
								peymayesh = new Peymayesh();
								peymayesh.set_Field(5);
								peymayesh.set_NewValue(txt_addreess.getText().toString());
								peymayesh.set_Serialno(input.get(recno).get_eshterak());
								peymayesh.set_Idlist(Integer.valueOf(input.get(recno).get_FKId_List()));
								peymayesh.set_DateSabt(strDate);
								peymayeshlist.add(peymayesh);
							}

							for (Peymayesh Peymayesh_Temp : peymayeshlist) {

								for (AddData AddData_Temp : Peymayesh) {
									if (AddData_Temp.get_id() == Peymayesh_Temp.get_Field()) {
										Peymayesh_Temp.set_Value(AddData_Temp.get_Value());
										Peymayesh_Temp.set_id(AddData_Temp.get_idpeymayesh());
									}
								}

								if (Peymayesh_Temp.get_Value().compareTo("NotFound") == 0) {
									da.Insert_Peymayesh(
											new Object[]{
													Peymayesh_Temp.get_Field(),
													Peymayesh_Temp.get_NewValue(),
													Peymayesh_Temp.get_Serialno(),
													Peymayesh_Temp.get_Idlist(),
													Peymayesh_Temp.get_DateSabt()
											});

								} else {
									da.Update_Peymayesh(
											new Object[]{
													Peymayesh_Temp.get_NewValue(),
													Peymayesh_Temp.get_DateSabt(),
													Peymayesh_Temp.get_id()
											});
								}
							}

							Peymayesh = da.Peymayesh_GetMobile(input.get(recno).get_eshterak(), da.Select_AddData());
							//endregion
						} else {
							input.get(recno).set_OfficerDescription(txt_officerdiscription.getText().toString());
							da.update_OfficerDiscription(input.get(recno));
						}
						Further_informationstate = Further_information.all;
						dialog.dismiss();
					}
				});

				btn_cancel.setOnClickListener(new OnClickListener() {

					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						Further_informationstate = Further_information.all;
						dialog.dismiss();
					}
				});
			}

		});


		CodeMamor.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				temp_OnlyCodeMamorCount = da.Select_Code_MamoorOnlyCount_FromList();
				temp_OnlyCodeMamor = da.Select_Code_MamoorOnly_FromList();
				String[] temp = da.Select_Code_Mamoor_FromList();



				final Dialog dialog = new Dialog(context);
				dialog.setContentView(R.layout.code_gheraat);
				dialog.setTitle("لیست کدهای مامور");
				ListView list_code_mane = (ListView) dialog.findViewById(R.id.listView_code_gherat);
				LinearLayout LayoutTitle = (LinearLayout) dialog.findViewById(R.id.title_list_dialog_line);
				LayoutTitle.setVisibility(View.GONE);
				ArrayAdapter<String> ada = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, temp);
				list_code_mane.setAdapter(ada);
				dialog.show();

				list_code_mane.setOnItemClickListener(new OnItemClickListener() {

					public void onItemClick(AdapterView<?> arg0, View arg1,
											int arg2, long arg3) {
						// TODO Auto-generated method stub
						recnoforupdate = 0;
						for (int i = 0; i < arg2; i++) {
							recnoforupdate += Integer.valueOf(temp_OnlyCodeMamorCount[i]);
						}
						recno = 0;
						databasetest._CodeMamor = temp_OnlyCodeMamor[arg2];
						input = da.select_data_from_database_by_codemamor();
						ChangeCodeMamor = true;
						SelectRecordMod = Select_Record_FromDatabase.CodeMamoor;
						//databasetest._CodeMamor="";
						Log.d("sdsa", input.get(0).get_eshterak());
						Toast.makeText(getApplicationContext(), "item" + " " + input.get(0).get_eshterak(), Toast.LENGTH_LONG).show();
						noe_gherat.setText(name_of_noee_gheraat(input.get(recno).get_code_mane_feli()));
						reccount = input.size();
						recno = getrecnolast("");
						set_data_to_textbox(move_record.recordnumberchangemamor);
						dialog.hide();
					}
				});


			}
		});

		btn_display_report.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String[] temp = da.Select_Gozaresh_Gheraat();
				final Dialog dialog = new Dialog(context);
				dialog.setContentView(R.layout.code_gheraat);
				dialog.setTitle("خلاصه گزارش قرائت");
				ListView list_code_mane = (ListView) dialog.findViewById(R.id.listView_code_gherat);
				LinearLayout LayoutTitle = (LinearLayout) dialog.findViewById(R.id.title_list_dialog_line);
				LayoutTitle.setVisibility(View.GONE);
				final ArrayAdapter<String> ada = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, temp);
				list_code_mane.setAdapter(ada);
				list_code_mane.setOnItemClickListener(new OnItemClickListener() {

					public void onItemClick(AdapterView<?> arg0, View arg1,
											int arg2, long arg3) {
						String Item[] = ada.getItem(arg2).toString().split(",");
						if (databasetest._CodeMamor.compareTo("") == 0) {
							input = da.select_data_from_database_by_CodeMane(da.Select_Code_Mane_ByName(Item[0]));
							SelectRecordMod = Select_Record_FromDatabase.CodeMane;
						} else {
							input = da.select_data_from_database_by_codemamorcodemane(da.Select_Code_Mane_ByName(Item[0]));
							SelectRecordMod = Select_Record_FromDatabase.CodeMamoorCodeMane;
						}

						reccount = input.size();
						recno = 0;
						noe_gherat.setText(name_of_noee_gheraat(input.get(recno).get_code_mane_feli()));
						set_data_to_textbox(move_record.recordnumberchangemamor);
						dialog.hide();
					}
				});
				dialog.show();
			}
		});

		btn_find.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				recno_Search = 0;
				EditText txt_search_eshterak = (EditText) findViewById(R.id.txt_search_box);
				String t = txt_search_eshterak.getText().toString();

				t=ConvertPersianDigitToEnglish(txt_search_eshterak.getText().toString());
				Toast.makeText(getApplicationContext(), t, Toast.LENGTH_SHORT).show();
				String Stemp = SearchType.getSelectedItem().toString();

				Toast.makeText(getApplicationContext(), Stemp, Toast.LENGTH_SHORT).show();

				if (Stemp.compareTo("شماره اشتراک") == 0)
					search(txt_search_eshterak.getText().toString(), "Eshterak");
				if (Stemp.compareTo("شماره تنه کنتور") == 0)
					search(txt_search_eshterak.getText().toString(), "Taneh");
				if (Stemp.compareTo("شماره پلمپ") == 0)
					search(txt_search_eshterak.getText().toString(), "Polomp");
				if (Stemp.compareTo("نام مشترک") == 0)
					search(txt_search_eshterak.getText().toString(), "Name");
				Keyboard = "";

				if (recno_Search >= 0) {
					String temp_karkard = input.get(recno_Search).get_karkard_feli();
					if (temp_karkard == null)
						temp_karkard = "0";

					Integer temp_int_karkard = 0, temp = 0;
					temp_int_karkard = Integer.getInteger(temp_karkard, 0);
					temp = Integer.getInteger(txt_karkard_feli.getText().toString(), 0);
					noe_gherat.setText(set_data_to_textbox_search());
				}
			}
		});
		btn_first.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					noe_gherat.setText(set_data_to_textbox(move_record.first));
					Keyboard = "";
				} catch (Exception Error)
				{
					Toast.makeText(getApplicationContext(), "خطا در پیمایش اطلاعات اشتراک لطفا لیست دریافتی را بررسی کنید ", Toast.LENGTH_LONG).show();
				}
			}
		});
		//-------------------------------btn last
		btn_last.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				try {
					// find usernew String[]{"%"+master_group_name+"%"}new String[]{"%"+master_group_name+"%"}
					noe_gherat.setText(set_data_to_textbox(move_record.last));
					Keyboard = "";
				} catch (Exception Error) {
					Toast.makeText(getApplicationContext(), "خطا در پیمایش اطلاعات اشتراک لطفا لیست دریافتی را بررسی کنید ", Toast.LENGTH_SHORT).show();
				}
			}
		});

		noe_gherat.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				final Dialog dialog = new Dialog(context);
				dialog.setContentView(R.layout.code_gheraat_new);
				LinearLayout LayoutTitle1 = (LinearLayout) dialog.findViewById(R.id.title_list_dialog_singlelinelayout_gheraat);
				LayoutTitle1.setVisibility(View.VISIBLE);

				TextView txttitleoflist = (TextView) dialog.findViewById(R.id.txt_namelist_row_simple);
				txttitleoflist.setText("لیست کدهای قرائت");
				txttitleoflist.setTypeface(font);
				int meterstatus=input.get(recno).get_MeterStatus();
				int estateconditionid=input.get(recno).get_EstateConditionid();
				ArrayList<code_mane> listacceptedreadcode=input.get(recno).ListAcceptedReadCode;


				ListView list_code_mane = (ListView) dialog.findViewById(R.id.listView_code_gherat);
				list_code_mane.setAdapter(new Adapter_CodeMane(context,
						listacceptedreadcode, estateconditionid));

				list_code_mane.setOnItemClickListener(new OnItemClickListener() {
					public void onItemClick(AdapterView<?> arg0, View arg1,
											int arg2, long arg3) {
						String code_manefeli = "0";
						code_manefeli = ((TextView) arg1.findViewById(R.id.txt_roozkar_row_h)).getText().toString();

						String temp = code_manefeli;
						try {

							GeneralExtentions.Extention.LocationInfo locationInfo=GelLocationStatus(context);
							if (locationInfo.status) {
								if (input.get(recno).get_EstateConditionid() > 1) {
									String EstateConditionId = "1";

									String strDate = GetDateNowWithFormat();

									Peymayesh peymayesh = new Peymayesh();
									peymayesh.set_Field(7);
									peymayesh.set_NewValue(EstateConditionId);
									peymayesh.set_Serialno(input.get(recno).get_eshterak());
									peymayesh.set_Idlist(Integer.valueOf(input.get(recno).get_FKId_List()));
									peymayesh.set_DateSabt(strDate);
									//Peymayesh ExistingPeymayesh = da.Peymayesh_GetEstateCondition(peymayesh);
									EstateConditionDto estateConditionDto=da.Peymayesh_GetEstateCondition(readingListItems.get(recno).eshterak);

									if (ExistingPeymayesh.get_Value() == null) {
										da.Insert_Peymayesh(
												new Object[]
														{
																peymayesh.get_Field(),
																peymayesh.get_NewValue(),
																peymayesh.get_Serialno(),
																peymayesh.get_Idlist(),
																peymayesh.get_DateSabt()
														});
									} else {
										ExistingPeymayesh.set_NewValue(EstateConditionId);
										ExistingPeymayesh.set_DateSabt(strDate);

										da.Update_Peymayesh(
												new Object[]{
														ExistingPeymayesh.get_NewValue(),
														ExistingPeymayesh.get_DateSabt(),
														ExistingPeymayesh.get_id()
												});
									}

									input.get(recno).set_EstateConditionid(Integer.valueOf(EstateConditionId));
									input.get(recno).set_EstateConditionTitle("عادی");
									da.update_gheraat(
											input.get(recno).get_eshterak(),
											mydate.current_date_to_fasridate(),
											String.valueOf(Integer.parseInt(input.get(recno).get_meghdare_ghabli())),
											temp,
											String.valueOf(locationInfo.longitude),
											String.valueOf(locationInfo.latitude), 1);
								} else
									da.update_gheraat(
											input.get(recno).get_eshterak(),
											mydate.current_date_to_fasridate(),
											String.valueOf(input.get(recno).get_meghdare_ghabli_int()),
											temp, String.valueOf(locationInfo.longitude),
											String.valueOf(locationInfo.latitude));
								da.Update_CurrentRecord(String.valueOf(recno + recnoforupdate));
								set_data_to_textbox(move_record.next);

								if ((databasetest._CodeMamor.compareTo("") == 0) && (databasetest._roozkar.compareTo("") == 0))
									readingListItems = da.select_data_from_database(databasetest._FKId_List);
								//input = da.select_data_from_database(databasetest._FKId_List);
								else if ((databasetest._CodeMamor.compareTo("") != 0) && (databasetest._roozkar.compareTo("") == 0))
									input = da.select_data_from_database_by_codemamor();
								else if ((databasetest._CodeMamor.compareTo("") != 0) && (databasetest._roozkar.compareTo("") != 0)) {
									readingListItems=da.select_data_from_database_by_codemamorroozkar(databasetest._FKId_List,databasetest._CodeMamor,databasetest._roozkar);
									//input = da.select_data_from_database_by_codemamorroozkar();
								}
								Keyboard = "";
							} else {
								String message = "به دلیل عدم اتصال " + " GPS " + "امکان قرائت اشتراک وجود ندارد";
								Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
							}
						} catch (Exception e) {
							Log.e("location", e.toString());
						}

						dialog.dismiss();
					}
				});
				dialog.show();
			}
		});


		btn_next.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					Keyboard = "";
					noe_gherat.setText(set_data_to_textbox(move_record.next));
				} catch (Exception Error) {
					Toast.makeText(getApplicationContext(), "خطا در پیمایش اطلاعات اشتراک لطفا لیست دریافتی را بررسی کنید ", Toast.LENGTH_LONG).show();
				}
			}
		});

		btn_pre.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					Keyboard = "";
					noe_gherat.setText(set_data_to_textbox(move_record.pre));
				} catch (Exception Error) {
					Toast.makeText(getApplicationContext(), "خطا در پیمایش اطلاعات اشتراک لطفا لیست دریافتی را بررسی کنید ", Toast.LENGTH_LONG).show();
				}
				//input=databasetest.select_data_from_database();
			}
		});

		btn_save_next.setOnClickListener(new OnClickListener() {
			int temp = 0;

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				try {
					Hilowmode = false;
					Hilowoperationok = false;
					int Code_ManeGhable = Integer.valueOf(input.get(recno).get_mane_ghabli());
					int Raghame_Ghable = Integer.valueOf(input.get(recno).get_meghdare_ghabli());

					String txtt = txt_karkard_feli.getText().toString();
					if (txtt.compareTo("") == 0) {
						Toast.makeText(getApplicationContext(), "رقم کنتور را وارد کنید", Toast.LENGTH_SHORT).show();
						return;
					}

					final GeneralExtentions.Extention.LocationInfo locationInfo= GelLocationStatus(context);
					if (locationInfo.status) {

						String SendToServerTemp = input.get(recno).get_SendToServer();
						String Code_ManeTemp = input.get(recno).get_code_mane_feli();
						if (SendToServerTemp == null) SendToServerTemp = "0";


						String temp_karkard = input.get(recno).get_karkard_feli();
						if (temp_karkard == null)
							temp_karkard = "0";
						Integer temp_int_karkard = 0;

						temp_int_karkard = Integer.getInteger(temp_karkard, 0);
						temp = Integer.valueOf(txt_karkard_feli.getText().toString());
						if ((Integer.valueOf(SendToServerTemp) == 1) && (Integer.valueOf(Code_ManeTemp) == 2)) {
							Code_Mane = "1";
							input.get(recno).set_SendToServer("0");
							da.update_gheraat_And_Update_SendToServer(input.get(recno).get_eshterak(), mydate.current_date_to_fasridate(), String.valueOf(temp), Code_Mane, String.valueOf(locationInfo.longitude), String.valueOf(locationInfo.latitude));
						} else {

							if ((Code_ManeGhable == 1) && (Integer.valueOf(Code_Mane) == 1)) {
								if (Raghame_Ghable < temp) {
									int masraf = temp - Raghame_Ghable;
									if ((masraf >= 0) && (masraf <= 5)) {
										Hilowmode = true;
										finddialog = new Dialog(context);
										finddialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
										finddialog.setContentView(R.layout.hilow_message);
										finddialog.setCancelable(false);

										TextView txt_message_hilow = finddialog.findViewById(R.id.txt_message_hilow);

										Button btn_ok_hilow = finddialog.findViewById(R.id.btn_ok_hilow);
										Button btn_cancel_hilow = finddialog.findViewById(R.id.btn_cancel_hilow);

										txt_message_hilow.setTypeface(font);
										btn_ok_hilow.setTypeface(font);
										btn_cancel_hilow.setTypeface(font);

										btn_ok_hilow.setOnClickListener(new OnClickListener() {
											@Override
											public void onClick(View view) {
												Hilowoperationok = true;
												if (input.get(recno).get_EstateConditionid() > 1) {
													String EstateConditionId = "1";

													String strDate = GetDateNowWithFormat();

													Peymayesh peymayesh = new Peymayesh();
													peymayesh.set_Field(7);
													peymayesh.set_NewValue(EstateConditionId);
													peymayesh.set_Serialno(input.get(recno).get_eshterak());
													peymayesh.set_Idlist(Integer.valueOf(input.get(recno).get_FKId_List()));
													peymayesh.set_DateSabt(strDate);
													EstateConditionDto estateConditionDto=da.Peymayesh_GetEstateCondition(readingListItems.get(recno).eshterak);
													//Peymayesh ExistingPeymayesh = da.Peymayesh_GetEstateCondition(peymayesh);



													if (ExistingPeymayesh.get_Value() == null) {
														da.Insert_Peymayesh(
																new Object[]
																		{
																				peymayesh.get_Field(),
																				peymayesh.get_NewValue(),
																				peymayesh.get_Serialno(),
																				peymayesh.get_Idlist(),
																				peymayesh.get_DateSabt()
																		});
													} else {
														ExistingPeymayesh.set_NewValue(EstateConditionId);
														ExistingPeymayesh.set_DateSabt(strDate);

														da.Update_Peymayesh(
																new Object[]{
																		ExistingPeymayesh.get_NewValue(),
																		ExistingPeymayesh.get_DateSabt(),
																		ExistingPeymayesh.get_id()
																});
													}

													input.get(recno).set_EstateConditionid(Integer.valueOf(EstateConditionId));
													input.get(recno).set_EstateConditionTitle("عادی");
													da.update_gheraat(input.get(recno).get_eshterak(), mydate.current_date_to_fasridate(), String.valueOf(temp), Code_Mane, String.valueOf(locationInfo.longitude), String.valueOf(locationInfo.latitude), 1);
												} else
													da.update_gheraat(input.get(recno).get_eshterak(), mydate.current_date_to_fasridate(), String.valueOf(temp), Code_Mane, String.valueOf(locationInfo.longitude), String.valueOf(locationInfo.latitude));
												da.Update_CurrentRecord(String.valueOf(input.get(recno).get_RecNo()));

												if (SelectRecordMod == Select_Record_FromDatabase.CodeMamoorRoozkar) {
													readingListItems=da.select_data_from_database_by_codemamorroozkar(databasetest._FKId_List,databasetest._CodeMamor,databasetest._roozkar);
													//input = da.select_data_from_database_by_codemamorroozkar();
												} else if (SelectRecordMod == Select_Record_FromDatabase.CodeMamoor) {
													input = da.select_data_from_database_by_codemamor();
												} else if (SelectRecordMod == Select_Record_FromDatabase.CodeMane) {
													input = da.select_data_from_database_by_CodeMane(da._CodeMane);
												} else if (SelectRecordMod == Select_Record_FromDatabase.CodeMamoorCodeMane) {
													input = da.select_data_from_database_by_codemamorcodemane(da._CodeMane);
												} else
													readingListItems = da.select_data_from_database(databasetest._FKId_List);
												//input = da.select_data_from_database(databasetest._FKId_List);

												reccount = input.size();
												noe_gherat.setText(set_data_to_textbox(move_record.next));

												Keyboard = "";
												Code_Mane = "1";
												finddialog.dismiss();
											}
										});
										btn_cancel_hilow.setOnClickListener(new OnClickListener() {
											@Override
											public void onClick(View view) {
												finddialog.dismiss();
											}
										});
										finddialog.show();

//                                        // if dialog.btnok==true {} else {dismiss}
//
									}
								}
							}

							if ((Hilowmode == true) && (Hilowoperationok == true)) {
								if (input.get(recno).get_EstateConditionid() > 1) {
									String EstateConditionId = "1";

									String strDate = GetDateNowWithFormat();

									Peymayesh peymayesh = new Peymayesh();
									peymayesh.set_Field(7);
									peymayesh.set_NewValue(EstateConditionId);
									peymayesh.set_Serialno(input.get(recno).get_eshterak());
									peymayesh.set_Idlist(Integer.valueOf(input.get(recno).get_FKId_List()));
									peymayesh.set_DateSabt(strDate);
									//Peymayesh ExistingPeymayesh = da.Peymayesh_GetEstateCondition(peymayesh);

									EstateConditionDto estateConditionDto=da.Peymayesh_GetEstateCondition(readingListItems.get(recno).eshterak);
									if (ExistingPeymayesh.get_Value() == null) {
										da.Insert_Peymayesh(
												new Object[]
														{
																peymayesh.get_Field(),
																peymayesh.get_NewValue(),
																peymayesh.get_Serialno(),
																peymayesh.get_Idlist(),
																peymayesh.get_DateSabt()
														});
									} else {
										ExistingPeymayesh.set_NewValue(EstateConditionId);
										ExistingPeymayesh.set_DateSabt(strDate);

										da.Update_Peymayesh(
												new Object[]{
														ExistingPeymayesh.get_NewValue(),
														ExistingPeymayesh.get_DateSabt(),
														ExistingPeymayesh.get_id()
												});
									}

									input.get(recno).set_EstateConditionid(Integer.valueOf(EstateConditionId));
									input.get(recno).set_EstateConditionTitle("عادی");
									da.update_gheraat(input.get(recno).get_eshterak(), mydate.current_date_to_fasridate(), String.valueOf(temp), Code_Mane, String.valueOf(locationInfo.longitude), String.valueOf(locationInfo.latitude), 1);
								} else
									da.update_gheraat(input.get(recno).get_eshterak(), mydate.current_date_to_fasridate(), String.valueOf(temp), Code_Mane, String.valueOf(locationInfo.longitude), String.valueOf(locationInfo.latitude));
							}
							if ((Hilowmode == false)) {
								if (input.get(recno).get_EstateConditionid() > 1) {
									String EstateConditionId = "1";

									String strDate = GetDateNowWithFormat();

									Peymayesh peymayesh = new Peymayesh();
									peymayesh.set_Field(7);
									peymayesh.set_NewValue(EstateConditionId);
									peymayesh.set_Serialno(input.get(recno).get_eshterak());
									peymayesh.set_Idlist(Integer.valueOf(input.get(recno).get_FKId_List()));
									peymayesh.set_DateSabt(strDate);

									//Peymayesh ExistingPeymayesh = da.Peymayesh_GetEstateCondition(peymayesh);

									EstateConditionDto estateConditionDto=da.Peymayesh_GetEstateCondition(readingListItems.get(recno).eshterak);
									if (ExistingPeymayesh.get_Value() == null) {
										da.Insert_Peymayesh(
												new Object[]
														{
																peymayesh.get_Field(),
																peymayesh.get_NewValue(),
																peymayesh.get_Serialno(),
																peymayesh.get_Idlist(),
																peymayesh.get_DateSabt()
														});
									} else {
										ExistingPeymayesh.set_NewValue(EstateConditionId);
										ExistingPeymayesh.set_DateSabt(strDate);

										da.Update_Peymayesh(
												new Object[]{
														ExistingPeymayesh.get_NewValue(),
														ExistingPeymayesh.get_DateSabt(),
														ExistingPeymayesh.get_id()
												});
									}

									input.get(recno).set_EstateConditionid(Integer.valueOf(EstateConditionId));
									input.get(recno).set_EstateConditionTitle("عادی");
									da.update_gheraat(input.get(recno).get_eshterak(), mydate.current_date_to_fasridate(), String.valueOf(temp), Code_Mane, String.valueOf(locationInfo.longitude), String.valueOf(locationInfo.latitude), 1);
								} else
									da.update_gheraat(input.get(recno).get_eshterak(), mydate.current_date_to_fasridate(), String.valueOf(temp), Code_Mane, String.valueOf(locationInfo.longitude), String.valueOf(locationInfo.latitude));
							}
						}

						if ((Hilowmode == true) && (Hilowoperationok == true)) {
							da.Update_CurrentRecord(String.valueOf(input.get(recno).get_RecNo()));
							if (SelectRecordMod == Select_Record_FromDatabase.CodeMamoorRoozkar) {
								readingListItems=da.select_data_from_database_by_codemamorroozkar(databasetest._FKId_List,databasetest._CodeMamor,databasetest._roozkar);
								//input = da.select_data_from_database_by_codemamorroozkar();
							} else if (SelectRecordMod == Select_Record_FromDatabase.CodeMamoor) {
								input = da.select_data_from_database_by_codemamor();
							} else if (SelectRecordMod == Select_Record_FromDatabase.CodeMane) {
								input = da.select_data_from_database_by_CodeMane(da._CodeMane);
							} else if (SelectRecordMod == Select_Record_FromDatabase.CodeMamoorCodeMane) {
								input = da.select_data_from_database_by_codemamorcodemane(da._CodeMane);
							} else
								readingListItems = da.select_data_from_database(databasetest._FKId_List);
							//input = da.select_data_from_database(databasetest._FKId_List);
							reccount = input.size();
							noe_gherat.setText(set_data_to_textbox(move_record.next));
							Keyboard = "";
							Code_Mane = "1";
						}
						if (Hilowmode == false) {
							da.Update_CurrentRecord(String.valueOf(input.get(recno).get_RecNo()));

							if (SelectRecordMod == Select_Record_FromDatabase.CodeMamoorRoozkar) {
								readingListItems=da.select_data_from_database_by_codemamorroozkar(databasetest._FKId_List,databasetest._CodeMamor,databasetest._roozkar);
								//input = da.select_data_from_database_by_codemamorroozkar();
							} else if (SelectRecordMod == Select_Record_FromDatabase.CodeMamoor) {
								readingListItems=da.select_data_from_database_by_codemamor(databasetest._FKId_List,databasetest._CodeMamor);
								//input = da.select_data_from_database_by_codemamor();
							} else if (SelectRecordMod == Select_Record_FromDatabase.CodeMane) {
								input = da.select_data_from_database_by_CodeMane(da._CodeMane);
							} else if (SelectRecordMod == Select_Record_FromDatabase.CodeMamoorCodeMane) {
								input = da.select_data_from_database_by_codemamorcodemane(da._CodeMane);
							} else
								readingListItems = da.select_data_from_database(databasetest._FKId_List);
								//input=da.select_data_from_database();
							reccount = input.size();
							noe_gherat.setText(set_data_to_textbox(move_record.next));
							Keyboard = "";
							Code_Mane = "1";
						}

					} else {
						String message = "به دلیل عدم اتصال " + " GPS " + "امکان ثبت اشتراک وجود ندارد";
						Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
					}
				} catch (Exception e) {
					Log.e("location", e.toString());
				}
			}
		});


		try {
			if (databasetest._FKId_List.compareTo("0") != 0) {
				set_data_to_textbox(move_record.recordnumber);
			}
			Keyboard = "";
		} catch (Exception e) {
			// TODO: handle exception
			Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
			Toast.makeText(getApplicationContext(), "خطا در مقدار دهی ", Toast.LENGTH_SHORT).show();
		}

	}

	//region Other Function

	private int getrecnolast(String CodeMamor) {

		int Rec = 0;
		Boolean Find = false;
		pdl_input temp = da.select_data_from_database1();
		if (temp == null) return 0;

		int rec = 0;
		for (pdl_input inp1 : input) {
			if (inp1.get_eshterak().compareTo(temp.get_eshterak()) == 0) {
				Find = true;
				break;
			}
			rec++;
		}
		if (Find == false) rec = 0;
		return rec;

	}

	private class MyLocationListener implements LocationListener {


		public void onLocationChanged(Location location)
		{

		}

		public void onStatusChanged(String provider, int status, Bundle extras) {
			//Toast.makeText(GeraatActivity.this, provider + "'s status changed to "+status +"!",
			//        Toast.LENGTH_SHORT).show();
			Log.e("status", "status");

		}


		public void onProviderEnabled(String provider) {
			//Toast.makeText(GeraatActivity.this, "Provider " + provider + " enabled!",
			Toast.makeText(GeraatActivity.this, "مکان یابی گوشی فعال گردید ، لطفا تا برقراری ارتباط کامل با ماهواره جهت تشخیص مکان چند لحظه شکیبا باشید",
					Toast.LENGTH_SHORT).show();
			//GpsEnable=true;

		}

		public void onProviderDisabled(String provider) {
			//Toast.makeText(GeraatActivity.this, "Provider " + provider + " disabled!",
			Toast.makeText(GeraatActivity.this, "مکان یابی گوشی توسط کاربر غیر فعال گردید",
					Toast.LENGTH_SHORT).show();


		}
	}



	private void Display_Further_information(Further_information State, Dialog dialog) {
		LinearLayout postalcode = (LinearLayout) dialog.findViewById(R.id.row_postalcode);
		LinearLayout mobilenumber = (LinearLayout) dialog.findViewById(R.id.row_mobilenumber);
		LinearLayout Metertrunk = (LinearLayout) dialog.findViewById(R.id.row_tanehkontor);
		LinearLayout Meterpolomp = (LinearLayout) dialog.findViewById(R.id.row_polompkontor);
		LinearLayout rowno = (LinearLayout) dialog.findViewById(R.id.row_radiftashkhis);
		LinearLayout address = (LinearLayout) dialog.findViewById(R.id.row_address);
		LinearLayout officerdiscription = (LinearLayout) dialog.findViewById(R.id.row_officerdiscription);

		switch (State) {
			case officerdescription:
				postalcode.setVisibility(View.GONE);
				mobilenumber.setVisibility(View.GONE);
				Metertrunk.setVisibility(View.GONE);
				Meterpolomp.setVisibility(View.GONE);
				rowno.setVisibility(View.GONE);
				address.setVisibility(View.GONE);
				officerdiscription.setVisibility(View.VISIBLE);
				break;
			case postalcode:
				postalcode.setVisibility(View.VISIBLE);
				mobilenumber.setVisibility(View.GONE);
				Metertrunk.setVisibility(View.GONE);
				Meterpolomp.setVisibility(View.GONE);
				rowno.setVisibility(View.GONE);
				address.setVisibility(View.GONE);
				officerdiscription.setVisibility(View.GONE);
				break;

			case mobile:
				postalcode.setVisibility(View.GONE);
				mobilenumber.setVisibility(View.VISIBLE);
				Metertrunk.setVisibility(View.GONE);
				Meterpolomp.setVisibility(View.GONE);
				rowno.setVisibility(View.GONE);
				address.setVisibility(View.GONE);
				officerdiscription.setVisibility(View.GONE);
				break;

			case Metertrunk:
				postalcode.setVisibility(View.GONE);
				mobilenumber.setVisibility(View.GONE);
				Metertrunk.setVisibility(View.VISIBLE);
				Meterpolomp.setVisibility(View.VISIBLE);
				rowno.setVisibility(View.GONE);
				address.setVisibility(View.GONE);
				officerdiscription.setVisibility(View.GONE);
				break;

			case Meterpolomp:
				postalcode.setVisibility(View.GONE);
				mobilenumber.setVisibility(View.GONE);
				Metertrunk.setVisibility(View.VISIBLE);
				Meterpolomp.setVisibility(View.VISIBLE);
				rowno.setVisibility(View.GONE);
				address.setVisibility(View.GONE);
				officerdiscription.setVisibility(View.GONE);
				break;

			case rowno:
				postalcode.setVisibility(View.GONE);
				mobilenumber.setVisibility(View.GONE);
				Metertrunk.setVisibility(View.GONE);
				Meterpolomp.setVisibility(View.GONE);
				rowno.setVisibility(View.VISIBLE);
				address.setVisibility(View.GONE);
				officerdiscription.setVisibility(View.GONE);
				break;

			case address:
				postalcode.setVisibility(View.GONE);
				mobilenumber.setVisibility(View.GONE);
				Metertrunk.setVisibility(View.GONE);
				Meterpolomp.setVisibility(View.GONE);
				rowno.setVisibility(View.GONE);
				address.setVisibility(View.VISIBLE);
				officerdiscription.setVisibility(View.GONE);
				break;
			case all:
				postalcode.setVisibility(View.VISIBLE);
				mobilenumber.setVisibility(View.VISIBLE);
				Metertrunk.setVisibility(View.VISIBLE);
				Meterpolomp.setVisibility(View.VISIBLE);
				rowno.setVisibility(View.VISIBLE);
				address.setVisibility(View.VISIBLE);
				break;
			default:
				break;
		}

	}


	private String set_data_to_textbox(move_record moving_status) {
		//region moving_status

		switch (moving_status) {
			case first:
				recno = 0;
				break;

			case next:

				recno++;

				if (recno > input.size() - 1) recno = input.size() - 1;
				break;

			case pre:
				recno--;
				if (recno <= 0) recno = 0;
				break;

			case last:
				//------------- -1 becuse start az 0
				recno = input.size() - 1;
				break;

			case recordnumber:

				break;

			case recordnumberchangemamor:
				recno = getrecnolast("");
				break;
			default:
				break;
		}
//endregion

		//region DefineControls

		TextView TextView_Officerdescription = (TextView) findViewById(R.id.TextView_editdescriptions);
		TextView TextView_editvazyatmelk = (TextView) findViewById(R.id.TextView_editvazyatmelk);
		LinearLayout LinearLayoutpropertycondition = (LinearLayout) findViewById(R.id.ll_propertycondition);
		Button btn_noe_gheraat = (Button) findViewById(R.id.noe_gheraat);
		ImageButton btn_save = (ImageButton) findViewById(R.id.btn_save_next);
		TextView txt_name_moshtarek = (TextView) findViewById(R.id.txt_name_moshtarek);
		TextView txt_radif_tashkhis = (TextView) findViewById(R.id.txt_radif_tashkhis);
		TextView txt_eshterak = (TextView) findViewById(R.id.txt_eshterak_number);
		EditText txt_karkard_feli = (EditText) findViewById(R.id.txt_karkard_feli);
		//txt_karkard_feli.setInputType(InputType.TYPE_NULL);
		TextView txt_address = (TextView) findViewById(R.id.txt_address);
		TextView txt_meghdare_ghabli = (TextView) findViewById(R.id.txt_meghdare_ghabli);
		TextView txt_tarikh_ghabli = (TextView) findViewById(R.id.txt_readdateold);
		TextView txt_bedehi = (TextView) findViewById(R.id.txt_bedehi);
		TextView txt_mane_ghabli = (TextView) findViewById(R.id.txt_manee_ghabli);
		TextView txt_ShomareKontor = (TextView) findViewById(R.id.txt_kontor_number);
		TextView txt_mobile = (TextView) findViewById(R.id.txt_mobile);
		TextView txt_postallcode = (TextView) findViewById(R.id.txt_postallcode);
		TextView txt_vazeyat_melk = (TextView) findViewById(R.id.txt_vazyat_melk);

		TextView txt_vazeyat_enshab = (TextView) findViewById(R.id.txt_vazyat_enshab);
		TextView txt_noe_enshab = (TextView) findViewById(R.id.txt_noee_ensheab);

		TextView txt_readnumold = (TextView) findViewById(R.id.txt_readnumold);
		TextView txt_readcodeold = (TextView) findViewById(R.id.txt_readcodeold);
		TextView txt_vazyatmelk_edit_icon = (TextView) findViewById(R.id.TextView_editvazyatmelk);

		TextView txt_vazyatenshab_edit_icon = (TextView) findViewById(R.id.TextView_editvazyatensheab);

		//endregion


		try {
			txt_readnumold.setText(input.get(recno).get_meghdare_ghabli());
		} catch (Exception name) {
			txt_readnumold.setText("");
		}
		try {
			txt_readcodeold.setText(name_of_noee_gheraat(input.get(recno).get_mane_ghabli()));
		} catch (Exception name) {
			txt_readcodeold.setText("");
		}
		try {
			if (input.get(recno).get_TavizKontor().toString().compareTo("1") == 0) {
				ReplacementofMeter.setVisibility(View.VISIBLE);
				MakeVibrate(context);
			} else ReplacementofMeter.setVisibility(View.GONE);
		} catch (Exception er) {
		}

		try {
			if (input.get(recno).get_IsUnderConstruction() == EnumUnderConstruction.IsUnderConstruction.ordinal()) {
				UnderCoustrution.setVisibility(View.VISIBLE);
				MakeVibrate(context);
			} else UnderCoustrution.setVisibility(View.GONE);
		} catch (Exception er) {
		}
		try {
			if (input.get(recno).get_IsTemporalHousing() == EnumTemporalHousing.IsTemporalHousing.ordinal()) {
				IsTemporalHousing.setVisibility(View.VISIBLE);
				MakeVibrate(context);
			} else IsTemporalHousing.setVisibility(View.GONE);
		} catch (Exception er) {
		}
		try {
			txt_vazeyat_melk.setText(input.get(recno).get_EstateConditionTitle());
		} catch (Exception name) {
			txt_vazeyat_melk.setText("عادی");
		}
		try {
			String temp = "";
			temp = input.get(recno).get_IsUnderConstructionTitle() + input.get(recno).get_IsTemporalHousingTitle();
			if (temp.length() > 0) temp = " - " + temp;
			txt_noe_enshab.setText(input.get(recno).get_KindBranchTitle() + temp);
		} catch (Exception name) {
			txt_noe_enshab.setText("آب و فاضلاب");
		}
		try {
			txt_vazeyat_enshab.setText(input.get(recno).get_VaziatTitle());
		} catch (Exception name) {
			txt_vazeyat_enshab.setText("فعال");
		}


		try {
			txt_postallcode.setText(input.get(recno).get_code_posti());
		} catch (Exception name) {
		}
		try {
			txt_name_moshtarek.setText(input.get(recno).get_name_moshtarek());
		} catch (Exception name) {
		}
		try {
			txt_radif_tashkhis.setText(input.get(recno).get_radif_tashkhis() + " - " + input.get(recno).get_karbarititle());
		} catch (Exception name) {
		}
		//-----------
		String temp_Eshterak = "";
		try {
			temp_Eshterak = input.get(recno).get_eshterak();
			txt_eshterak.setText(Integer.valueOf(temp_Eshterak.substring(temp_Eshterak.length() - 6, temp_Eshterak.length())).toString());
		} catch (Exception name) {
		}
		//---------------------------

		try {
			txt_address.setText(input.get(recno).get_address_moshtarek());
		} catch (Exception name) {
		}
		try {
			txt_meghdare_ghabli.setText(String.valueOf(Integer.parseInt(input.get(recno).get_meghdare_ghabli())));
		} catch (Exception name) {
		}

		try {
			txt_tarikh_ghabli.setText(input.get(recno).get_tarikh_ghabli());
		} catch (Exception name) {
		}
		try {
			txt_mane_ghabli.setText(name_of_noee_gheraat(input.get(recno).get_mane_ghabli()));
		} catch (Exception name) {
		}
		try {
			txt_mobile.setText((input.get(recno).get_Mobile()));
		} catch (Exception name) {
		}
		try {
			txt_ShomareKontor.setText(Long.valueOf((input.get(recno).get_Polomp_Kontor())).toString() + " - " + Long.valueOf((input.get(recno).get_shomare_kontor())).toString());
		} catch (Exception name) {
		}

		try {
			txt_bedehi.setText(String.format("%,.0f", Float.parseFloat(input.get(recno).get_bedehi())) + " ریال ");
		} catch (Exception name) {
			Log.e("catch", name.toString());
		}

		int CodeManeTemp = 0;
		// try
		//  {
		CodeManeTemp = Integer.valueOf((input.get(recno).get_code_mane_feli()));


		switch (CodeManeTemp) {
			case 0:
				txt_eshterak.setBackgroundColor(Color.parseColor("#e6e6e6"));
				btn_noe_gheraat.setBackgroundColor(Color.parseColor("#e6e6e6"));
				break;
			case 1:
				txt_eshterak.setBackgroundColor(Color.parseColor("#75ab40"));
				btn_noe_gheraat.setBackgroundColor(Color.parseColor("#75ab40"));
				break;
			case 2:
				txt_eshterak.setBackgroundColor(Color.parseColor("#7f837b"));
				btn_noe_gheraat.setBackgroundColor(Color.parseColor("#7f837b"));
				break;
			case 3:
				txt_eshterak.setBackgroundColor(Color.parseColor("#7f837b"));
				btn_noe_gheraat.setBackgroundColor(Color.parseColor("#7f837b"));
				break;
			case 4:
				txt_eshterak.setBackgroundColor(Color.parseColor("#fecc66"));
				btn_noe_gheraat.setBackgroundColor(Color.parseColor("#fecc66"));
				break;
			case 6:
				txt_eshterak.setBackgroundColor(Color.parseColor("#d75446"));
				btn_noe_gheraat.setBackgroundColor(Color.parseColor("#d75446"));
				break;
			default:
				break;
		}

		// }catch(Exception name){}

		try {

			if (CodeManeTemp == 1)
				txt_karkard_feli.setText(input.get(recno).get_karkard_feli());
			else
				txt_karkard_feli.setText("");

			txt_karkard_feli.setSelection(txt_karkard_feli.getText().length());
		} catch (Exception name) {
		}


		TextView txt_rec_no = (TextView) findViewById(R.id.txt_record_number);
		txt_rec_no.setTypeface(font);
		txt_vazeyat_melk.setTypeface(font);

		try {
			String strtempcodemamor = "(" + ((databasetest._CodeMamor.compareTo("") == 0) ? "همه" : databasetest._CodeMamor) + ")";
			txt_rec_no.setText(" رکورد " + String.valueOf(recno) + " از " + String.valueOf(reccount) + " - " + "کد مامور" + strtempcodemamor + " - " + "روزکار" + " " + databasetest._roozkar);
		} catch (Exception name) {
		}

		if (input.get(recno).OfficerDescription)
			TextView_Officerdescription.setTextColor(Color.RED);
		else
			TextView_Officerdescription.setTextColor(Color.BLACK);


//اگر وضعیت انشعاب فعال بود
		if (EnumBranchstatus.Active.ordinal() == input.get(recno).get_Vaziat()) {

			if (EnumEstateCondition.Normal.ordinal() == input.get(recno).get_EstateConditionid()) {
				btn_save.setImageResource(R.mipmap.saveandnext);
				btn_save.setEnabled(true);
				txt_karkard_feli.setEnabled(true);
				btn_noe_gheraat.setEnabled(true);
				//region Count_PropertyConditions
				if (da.Get_Count_PropertyConditions(input.get(recno).get_eshterak())) {
					btn_noe_gheraat.setEnabled(false);
					btn_save.setEnabled(false);
					LinearLayoutpropertycondition.setEnabled(false);
					TextView_editvazyatmelk.setEnabled(false);
					TextView_editvazyatmelk.setTextColor(Color.RED);

				} else {
					btn_noe_gheraat.setEnabled(true);
					btn_save.setEnabled(true);
					LinearLayoutpropertycondition.setEnabled(true);
					TextView_editvazyatmelk.setEnabled(true);
					TextView_editvazyatmelk.setTextColor(Color.BLACK);
				}
				//endregion

				//region CheckMeterState
				try {
					final int Temp_ManeGhabli = Integer.valueOf(input.get(recno).get_mane_ghabli());
					int Temp_ManeFeli = Integer.valueOf(input.get(recno).get_code_mane_feli());
					if (input.get(recno).MeterChange == true) {
						final Dialog dialog1 = new Dialog(context);
						dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
						dialog1.setContentView(R.layout.dialog_codemaneghable_exception);
						dialog1.setCancelable(false);
						Button Btn_Read = (Button) dialog1.findViewById(R.id.btn_illigalbranch_ok);
						Button Btn_Skip = (Button) dialog1.findViewById(R.id.btn_illigalbranch_cancel);
						Btn_Skip.setEnabled(false);
						Btn_Read.setText("متوجه شدم");
						TextView txt_message = (TextView) dialog1.findViewById(R.id.txt_illigalbranch_message);
						txt_message.setText("تعویض کنتور در دوره جاری");
						Btn_Read.setOnClickListener(new OnClickListener() {
							@Override
							public void onClick(View v) {
								dialog1.dismiss();
							}
						});
						dialog1.show();
					} else if ((Temp_ManeGhabli == 4 || Temp_ManeGhabli == 6) && (Temp_ManeFeli == 0)) {

						final Dialog dialog1 = new Dialog(context);
						dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
						dialog1.setContentView(R.layout.dialog_codemaneghable_exception);
						dialog1.setCancelable(false);

						Button Btn_Read = (Button) dialog1.findViewById(R.id.btn_illigalbranch_ok);
						Button Btn_Skip = (Button) dialog1.findViewById(R.id.btn_illigalbranch_cancel);
						TextView txt_message = (TextView) dialog1.findViewById(R.id.txt_illigalbranch_message);
						txt_message.setText("قرائت قبلی " + name_of_noee_gheraat(input.get(recno).get_mane_ghabli()) + " " + "بوده است");
						Btn_Read.setOnClickListener(new OnClickListener() {
							@Override
							public void onClick(View v) {
								dialog1.dismiss();
							}
						});

						Btn_Skip.setOnClickListener(new OnClickListener() {
							@Override
							public void onClick(View v) {
								dialog1.dismiss();
								//set_data_to_textbox(move_record.next);
								try {


								GeneralExtentions.Extention.LocationInfo locationInfo=GelLocationStatus(context);
									if (locationInfo.status) {
										da.update_gheraat(
												input.get(recno).get_eshterak(),
												mydate.current_date_to_fasridate(),
												String.valueOf(Integer.parseInt(input.get(recno).get_meghdare_ghabli())),
												String.valueOf(Temp_ManeGhabli),
												String.valueOf(locationInfo.longitude),
												String.valueOf(locationInfo.latitude)
										);
										da.Update_CurrentRecord(String.valueOf(recno + recnoforupdate));

										if (SelectRecordMod == Select_Record_FromDatabase.CodeMamoorRoozkar) {
											input = da.select_data_from_database_by_codemamorroozkar();
										} else if (SelectRecordMod == Select_Record_FromDatabase.CodeMamoor) {
											input = da.select_data_from_database_by_codemamor();
										} else if (SelectRecordMod == Select_Record_FromDatabase.CodeMane) {
											input = da.select_data_from_database_by_CodeMane(da._CodeMane);
										} else if (SelectRecordMod == Select_Record_FromDatabase.CodeMamoorCodeMane) {
											input = da.select_data_from_database_by_codemamorcodemane(da._CodeMane);
										} else
											input = da.select_data_from_database();
										reccount = input.size();
										Keyboard = "";
										set_data_to_textbox(move_record.next);
									} else {
										String message = "به دلیل عدم اتصال " + " GPS " + "امکان قرائت اشتراک وجود ندارد";
										Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
									}
								} catch (Exception e) {
									Log.e("location", e.toString());
								}
							}
						});

						if ((Temp_ManeGhabli == 4 || Temp_ManeGhabli == 6) && (input.get(recno).MeterChange == false))
							dialog1.show();


					}
				} catch (Exception name) {
					Log.e("ff", name.getMessage());
				}
				//endregion


			} else {
				btn_noe_gheraat.setEnabled(false);


				txt_karkard_feli.setEnabled(false);
				btn_save.setEnabled(false);
				btn_save.setImageResource(R.mipmap.illegal);

				final Dialog dialog = new Dialog(context);
				dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
				dialog.setContentView(R.layout.dialog_propertycondition);
				dialog.setCancelable(false);

				Button Btn_Read = (Button) dialog.findViewById(R.id.btn_illigalbranch_ok);
				Button Btn_Skip = (Button) dialog.findViewById(R.id.btn_illigalbranch_cancel);
				TextView txt_message = (TextView) dialog.findViewById(R.id.txt_illigalbranch_message);
				txt_message.setText(input.get(recno).get_EstateConditionTitle());
				Btn_Read.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						dialog.dismiss();
					}
				});

				Btn_Skip.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						dialog.dismiss();
						set_data_to_textbox(move_record.next);
					}
				});
				dialog.show();
			}

		} else {
			btn_save.setImageResource(R.mipmap.illegal);
			btn_save.setEnabled(false);
			txt_karkard_feli.setEnabled(false);
			btn_noe_gheraat.setEnabled(false);
		}

		//if (officerpermission.Get_EstateCondition()) txt_vazyatmelk_edit_icon.setVisibility(View.VISIBLE);else txt_vazyatmelk_edit_icon.setVisibility(View.GONE);
		//if (officerpermission.Get_BranchCondition()) txt_vazyatenshab_edit_icon.setVisibility(View.VISIBLE);else txt_vazyatenshab_edit_icon.setVisibility(View.GONE);
		return name_of_noee_gheraat(input.get(recno).get_code_mane_feli());

	}



	private String set_data_to_textbox_search() {
		// TODO Auto-generated method stub
//region define variable
		TextView TextView_Officerdescription = (TextView) findViewById(R.id.TextView_editdescriptions);
		TextView TextView_editvazyatmelk = (TextView) findViewById(R.id.TextView_editvazyatmelk);
		ImageButton btn_save = (ImageButton) findViewById(R.id.btn_save_next);
		TextView txt_vazeyat_melk = (TextView) findViewById(R.id.txt_vazyat_melk);
		LinearLayout LinearLayoutpropertycondition = (LinearLayout) findViewById(R.id.ll_propertycondition);
		Button btn_noe_gheraat = (Button) findViewById(R.id.noe_gheraat);
		TextView txt_shomarekontor = (TextView) findViewById(R.id.txt_kontor_number);
		TextView txt_name_moshtarek = (TextView) findViewById(R.id.txt_name_moshtarek);
		TextView txt_radif_tashkhis = (TextView) findViewById(R.id.txt_radif_tashkhis);
		TextView txt_eshterak = (TextView) findViewById(R.id.txt_eshterak_number);
		EditText txt_karkard_feli = (EditText) findViewById(R.id.txt_karkard_feli);
		//txt_karkard_feli.setInputType(InputType.TYPE_NULL);
		TextView txt_address = (TextView) findViewById(R.id.txt_address);
		TextView txt_meghdare_ghabli = (TextView) findViewById(R.id.txt_meghdare_ghabli);
		TextView txt_tarikh_ghabli = (TextView) findViewById(R.id.txt_readdateold);
		TextView txt_bedehi = (TextView) findViewById(R.id.txt_bedehi);
		TextView txt_mane_ghabli = (TextView) findViewById(R.id.txt_manee_ghabli);
		TextView txt_mobile = (TextView) findViewById(R.id.txt_mobile);
		TextView txt_postallcode = (TextView) findViewById(R.id.txt_postallcode);
		TextView txt_vazeyat_enshab = (TextView) findViewById(R.id.txt_vazyat_enshab);
		TextView txt_noe_enshab = (TextView) findViewById(R.id.txt_noee_ensheab);
		TextView txt_readnumold = (TextView) findViewById(R.id.txt_readnumold);
		TextView txt_readcodeold = (TextView) findViewById(R.id.txt_readcodeold);
//endregion vava
		try {
			txt_readnumold.setText(input.get(recno_Search).get_meghdare_ghabli());
		} catch (Exception name) {
			txt_readnumold.setText("");
		}
		try {
			txt_readcodeold.setText(name_of_noee_gheraat(input.get(recno_Search).get_mane_ghabli()));
		} catch (Exception name) {
			txt_readcodeold.setText("");
		}
		try {
			txt_postallcode.setText(input.get(recno_Search).get_code_posti());
		} catch (Exception name) {
		}
		try {
			txt_shomarekontor.setText(Long.valueOf((input.get(recno_Search).get_Polomp_Kontor())).toString() + " - " + Long.valueOf((input.get(recno_Search).get_shomare_kontor())).toString());
		} catch (Exception name) {
		}
		//try{txt_shomarekontor.setText(Long.valueOf(input.get(recno_Search).get_shomare_kontor()).toString());}catch(Exception Error){}
		//-----------------------------------------------------------------
		try {
			txt_name_moshtarek.setText(input.get(recno_Search).get_name_moshtarek());
		} catch (Exception Error) {
		}
		try {
			txt_radif_tashkhis.setText(input.get(recno_Search).get_radif_tashkhis() + " - " + input.get(recno_Search).get_karbarititle());
		} catch (Exception name) {
		}
//            try{txt_radif_tashkhis.setText(input.get(recno_Search).get_radif_tashkhis());}catch(Exception Error){}
		try {
			txt_mobile.setText((input.get(recno_Search).get_Mobile()));
		} catch (Exception name) {
		}
		//-----------
		try {
			String temp_Eshterak = input.get(recno_Search).get_eshterak();
			txt_eshterak.setText(Integer.valueOf(temp_Eshterak.substring(temp_Eshterak.length() - 6, temp_Eshterak.length())).toString());
		} catch (Exception Error) {
		}
		//txt_eshterak.setText(temp_Eshterak.substring(temp_Eshterak.length()-6, temp_Eshterak.length()));
		//---------------------------

		try {
			txt_address.setText(input.get(recno_Search).get_address_moshtarek());
		} catch (Exception Error) {
		}
		try {
			txt_meghdare_ghabli.setText(String.valueOf(Integer.parseInt(input.get(recno_Search).get_meghdare_ghabli())));
		} catch (Exception Error) {
		}
		try {
			txt_tarikh_ghabli.setText(input.get(recno_Search).get_tarikh_ghabli());
		} catch (Exception Error) {
		}
		try {
			txt_mane_ghabli.setText(name_of_noee_gheraat(input.get(recno_Search).get_mane_ghabli()));
		} catch (Exception Error) {
		}

		try {
			txt_bedehi.setText(String.format("%,.0f", Float.parseFloat(input.get(recno_Search).get_bedehi())) + " ریال ");
		} catch (Exception name) {
			Log.e("catch", name.toString());
		}


		try {
			if (input.get(recno_Search).get_code_mane_feli().compareTo("01") == 0)
				txt_karkard_feli.setText(input.get(recno_Search).get_karkard_feli());
		} catch (Exception Error) {
			txt_karkard_feli.setText("");
		}

		try {
			if (input.get(recno_Search).get_TavizKontor().toString().compareTo("1") == 0)
				ReplacementofMeter.setVisibility(View.VISIBLE);
			else
				ReplacementofMeter.setVisibility(View.GONE);
		} catch (Exception error) {
		}
		;
		try {
			if (input.get(recno_Search).get_IsUnderConstruction() == EnumUnderConstruction.IsUnderConstruction.ordinal()) {
				UnderCoustrution.setVisibility(View.VISIBLE);
				MakeVibrate(context);
			} else UnderCoustrution.setVisibility(View.GONE);
		} catch (Exception er) {
		}
		try {
			if (input.get(recno_Search).get_IsTemporalHousing() == EnumTemporalHousing.IsTemporalHousing.ordinal()) {
				IsTemporalHousing.setVisibility(View.VISIBLE);
				MakeVibrate(context);
			} else IsTemporalHousing.setVisibility(View.GONE);
		} catch (Exception er) {
		}
		txt_karkard_feli.setSelection(txt_karkard_feli.getText().length());


		try {
			txt_vazeyat_melk.setText(input.get(recno_Search).get_EstateConditionTitle());
		} catch (Exception name) {
			txt_vazeyat_melk.setText("عادی");
		}

		try {
			String temp = "";
			temp = input.get(recno_Search).get_IsUnderConstructionTitle() + input.get(recno_Search).get_IsTemporalHousingTitle();
			if (temp.length() > 0) temp = " - " + temp;
			txt_noe_enshab.setText(input.get(recno_Search).get_KindBranchTitle() + temp);
		} catch (Exception name) {
			txt_noe_enshab.setText("آب و فاضلاب");
		}
		try {
			txt_vazeyat_enshab.setText(input.get(recno_Search).get_VaziatTitle());
		} catch (Exception name) {
			txt_vazeyat_enshab.setText("فعال");
		}
		int CodeManeTemp = 0;
		// try
		//  {
		CodeManeTemp = Integer.valueOf((input.get(recno_Search).get_code_mane_feli()));


		switch (CodeManeTemp) {
			case 0:
				txt_eshterak.setBackgroundColor(Color.parseColor("#e6e6e6"));
				btn_noe_gheraat.setBackgroundColor(Color.parseColor("#e6e6e6"));
				break;
			case 1:
				txt_eshterak.setBackgroundColor(Color.parseColor("#75ab40"));
				btn_noe_gheraat.setBackgroundColor(Color.parseColor("#75ab40"));
				break;
			case 2:
				txt_eshterak.setBackgroundColor(Color.parseColor("#7f837b"));
				btn_noe_gheraat.setBackgroundColor(Color.parseColor("#7f837b"));
				break;
			case 3:
				txt_eshterak.setBackgroundColor(Color.parseColor("#7f837b"));
				btn_noe_gheraat.setBackgroundColor(Color.parseColor("#7f837b"));
				break;
			case 4:
				txt_eshterak.setBackgroundColor(Color.parseColor("#fecc66"));
				btn_noe_gheraat.setBackgroundColor(Color.parseColor("#fecc66"));
				break;
			case 6:
				txt_eshterak.setBackgroundColor(Color.parseColor("#d75446"));
				btn_noe_gheraat.setBackgroundColor(Color.parseColor("#d75446"));
				break;
			default:
				break;
		}

		if (EnumBranchstatus.Active.ordinal() == input.get(recno_Search).get_Vaziat()) {

			if (EnumEstateCondition.Normal.ordinal() == input.get(recno_Search).get_EstateConditionid()) {
				btn_save.setImageResource(R.mipmap.saveandnext);
				btn_save.setEnabled(true);
				txt_karkard_feli.setEnabled(true);
				btn_noe_gheraat.setEnabled(true);
				//region Count_PropertyConditions
				if (da.Get_Count_PropertyConditions(input.get(recno_Search).get_eshterak())) {
					btn_noe_gheraat.setEnabled(false);
					btn_save.setEnabled(false);
					LinearLayoutpropertycondition.setEnabled(false);
					TextView_editvazyatmelk.setEnabled(false);
					TextView_editvazyatmelk.setTextColor(Color.RED);

				} else {
					btn_noe_gheraat.setEnabled(true);
					btn_save.setEnabled(true);
					LinearLayoutpropertycondition.setEnabled(true);
					TextView_editvazyatmelk.setEnabled(true);
					TextView_editvazyatmelk.setTextColor(Color.BLACK);
				}
				//endregion

				//region CheckMeterState
				try {
					final int Temp_ManeGhabli = Integer.valueOf(input.get(recno_Search).get_mane_ghabli());
					int Temp_ManeFeli = Integer.valueOf(input.get(recno_Search).get_code_mane_feli());
					if ((Temp_ManeGhabli == 4 || Temp_ManeGhabli == 6) && (Temp_ManeFeli == 0)) {

						final Dialog dialog = new Dialog(context);
						dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
						dialog.setContentView(R.layout.dialog_codemaneghable_exception);
						dialog.setCancelable(false);

						Button Btn_Read = (Button) dialog.findViewById(R.id.btn_illigalbranch_ok);
						Button Btn_Skip = (Button) dialog.findViewById(R.id.btn_illigalbranch_cancel);
						TextView txt_message = (TextView) dialog.findViewById(R.id.txt_illigalbranch_message);
						txt_message.setText("قرائت قبلی " + name_of_noee_gheraat(input.get(recno_Search).get_mane_ghabli()) + " " + "بوده است");
						Btn_Read.setOnClickListener(new OnClickListener() {
							@Override
							public void onClick(View v) {
								dialog.dismiss();
							}
						});

						Btn_Skip.setOnClickListener(new OnClickListener() {
							@Override
							public void onClick(View v) {
								dialog.dismiss();
								//set_data_to_textbox(move_record.next);
								try {
									GeneralExtentions.Extention.LocationInfo locationInfo=GeneralExtentions.Extention.GelLocationStatus(context);
                                    if (locationInfo.status) {

                                        da.update_gheraat(
                                                input.get(recno_Search).get_eshterak(),
                                                mydate.current_date_to_fasridate(),
                                                String.valueOf(Integer.parseInt(input.get(recno_Search).get_meghdare_ghabli())),
                                                String.valueOf(Temp_ManeGhabli),
                                                String.valueOf(locationInfo.longitude),
                                                String.valueOf(locationInfo.latitude)
                                        );
                                        da.Update_CurrentRecord(String.valueOf(recno_Search + recnoforupdate));

                                        if ((databasetest._CodeMamor.compareTo("") == 0) && (databasetest._roozkar.compareTo("")==0))
                                            input = da.select_data_from_database();
                                        else if ((databasetest._CodeMamor.compareTo("") != 0)  && (databasetest._roozkar.compareTo("")==0))
                                            input = da.select_data_from_database_by_codemamor();
                                        else if ((databasetest._CodeMamor.compareTo("") != 0)  && (databasetest._roozkar.compareTo("")!=0))
											input = da.select_data_from_database_by_codemamorroozkar();


                                        Keyboard = "";
                                        set_data_to_textbox(move_record.next);
                                    } else {
                                        String message = "به دلیل عدم اتصال " + " GPS " + "امکان قرائت اشتراک وجود ندارد";
                                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                                    }
                                } catch (Exception e) {
                                    Log.e("location", e.toString());
                                }
                            }
                        });

                        if ((Temp_ManeGhabli != 4)&&(input.get(recno_Search).MeterChange==false))
                            dialog.show();

                    }
                } catch (Exception name) {
                    Log.e("ff", name.getMessage());
                }
                //endregion
            }
            else
            {
                btn_noe_gheraat.setEnabled(false);


                txt_karkard_feli.setEnabled(false);
                btn_save.setEnabled(false);
                btn_save.setImageResource(R.mipmap.illegal);

                final Dialog dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_propertycondition);
                dialog.setCancelable(false);

                Button Btn_Read = (Button) dialog.findViewById(R.id.btn_illigalbranch_ok);
                Button Btn_Skip = (Button) dialog.findViewById(R.id.btn_illigalbranch_cancel);
                TextView txt_message = (TextView) dialog.findViewById(R.id.txt_illigalbranch_message);
                txt_message.setText(input.get(recno).get_EstateConditionTitle());
                Btn_Read.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                Btn_Skip.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        set_data_to_textbox(move_record.next);
                    }
                });
                dialog.show();
            }

        }else
        {
            btn_save.setImageResource(R.mipmap.illegal);
            btn_save.setEnabled(false);
            txt_karkard_feli.setEnabled(false);
            btn_noe_gheraat.setEnabled(false);

        }

          return name_of_noee_gheraat(input.get(recno_Search).get_code_mane_feli());

  	}

    private String name_of_noee_gheraat(String noee_gherrat) {
		// TODO Auto-generated method stub
    	String title_gherat="";

    	try
    	{
    	for (code_mane g : ArrayListCodeMane_Text)
    	{
			if (Integer.valueOf(g.get_code_mane())==Integer.valueOf(noee_gherrat))
				title_gherat=g.get_title_mane();
		}
    	}catch(Exception error){title_gherat="خطا در کد مانع";}

    	return title_gherat;

	}



    private void search(String txt,String SearchType)
    {
    	FindStatus=false;
    	recno_Search=-1;
    	String temp_Eshterak="";

		if (SearchType.compareTo("NationalCode")==0)
		{

		}
    	 if (SearchType.compareTo("Eshterak")==0)
    	 {
    		 if (txt.length()<5)
        	 {
        		 String temp="";
        		 	for(int i=1;i<=(5-txt.length());i++)
        		 	{
        		 		temp=temp+"0";
        		 	}
        		 temp=temp+txt;
        		 txt=temp;
        	 }
        	 if (txt.length()==5)
        	 {
        		 String temp="";
        		 temp="0"+txt;
        		 txt=temp;
        	 }
    	 }

    	 if (SearchType.compareTo("Taneh")==0)
    	 {//12 ragham
    		// Log.e("taneh","Start<12" );
    		 if (txt.length()<12)
        	 {
        		 String temp="";
        		 	for(int i=1;i<=(12-txt.length());i++)
        		 	{
        		 		temp=temp+"0";
        		 	}
        		 temp=temp+txt;
        		 txt=temp;
        		 Log.e("taneh",temp.toString() );
        	 }
    	 }
    	 if (SearchType.compareTo("Polomp")==0)
    	 {//12 ragham
    		// Log.e("taneh","Start<12" );
    		 if (txt.length()<15)
        	 {
        		 String temp="";
        		 	for(int i=1;i<=(15-txt.length());i++)
        		 	{
        		 		temp=temp+"0";
        		 	}
        		 temp=temp+txt;
        		 txt=temp;
        		 Log.e("taneh",temp.toString() );
        	 }
    	 }

    	 if (SearchType.compareTo("Name")==0)
    	 {

    	 }


    	for (pdl_input test1 : input)
    	{

    		recno_Search++;

    		if (SearchType.compareTo("Eshterak")==0)
    		{
	    		 temp_Eshterak=test1.get_eshterak();
	             temp_Eshterak=temp_Eshterak.substring(temp_Eshterak.length()-6, temp_Eshterak.length());
	             if (temp_Eshterak.equals(txt)==true)
	    		{
	    			FindStatus=true;
	    			break;
	    		}
    		}
    		if (SearchType.compareTo("Taneh")==0)
    		{
	    		 temp_Eshterak=test1.get_shomare_kontor();
	             if (temp_Eshterak.equals(txt)==true)
	    		{
	    			FindStatus=true;
	    			break;
	    		}

    		}
			if (SearchType.compareTo("NationalCode")==0)
			{
				temp_Eshterak=test1.get_code_meli();
				if (temp_Eshterak.equals(txt)==true)
				{
					FindStatus=true;
					break;
				}

			}

    		if (SearchType.compareTo("Polomp")==0)
    		{
	    		 temp_Eshterak=test1.get_Polomp_Kontor();
	             if (temp_Eshterak.equals(txt)==true)
	    		{
	    			FindStatus=true;
	    			break;
	    		}

    		}
    		 if (SearchType.compareTo("Name")==0)
        	{
        	 	Log.e("name","findname");
				if (test1.get_name_moshtarek().contains(txt))
				{
					Log.e("ok","ok");
					arr_temp.add(test1);
					FindStatus=true;
				}
//---------------------------------------------------------------------
//    			 temp_Eshterak=test1.get_name_moshtarek();
//	             if (temp_Eshterak.contains(txt))
//	    		{
//	    			FindStatus=true;
//	    			break;
//	    		}

        	 }

		}


    	if (FindStatus)
    	{


    		Toast.makeText(getApplicationContext(), "مشترک پیدا شد", Toast.LENGTH_SHORT).show();

			if (SearchType.compareTo("Name")!=0)
			{
				recno = recno_Search;
				noe_gherat.setText(set_data_to_textbox_search());
				finddialog.dismiss();
			}

    	}

    	else
    	{
    			recno_Search=-1;
    			Toast.makeText(getApplicationContext(), " اشتراک در سیستم موجود نمی باشد", Toast.LENGTH_SHORT).show();

    	}


    }






   public interface ISearch{
	   ArrayList<Integer>  Find();

	}

	class SearchByNationalCode implements ISearch
	{
		ArrayList<pdl_input> Source;
		String NationalCode;

		SearchByNationalCode(ArrayList<pdl_input> source,String nationalCode)
		{
			Source=source;
			NationalCode=nationalCode;
		}

		@Override
		public ArrayList<Integer> Find() {
			int index=-1;
			ArrayList<Integer> res =new ArrayList<Integer>();

			for (pdl_input items : Source)
			{
				index++;
				if(items.get_code_meli().equals(NationalCode))
				{

					res.add(index);
					break;
				}
			}
			return res;
		}
	}


	class SearchBySerilano implements ISearch
	{
		ArrayList<pdl_input> Source;
		String Serialno;

		SearchBySerilano(ArrayList<pdl_input> source,String serialno)
		{
			Source=source;
			Serialno=InitSerialno(serialno);
		}

		String InitSerialno(String serial)
		{
			if (serial.length()<5)
			{
				String temp="";
				for(int i=1;i<=(5-serial.length());i++)
				{
					temp=temp+"0";
				}
				return temp+serial;

			}
			if (serial.length()==5)
			{
				String temp="";
				temp="0"+serial;
				return   temp;
			}
			return serial;
		}

		@Override
		public ArrayList<Integer> Find() {
			int index=-1;
			ArrayList<Integer> res =new ArrayList<Integer>();
			for (pdl_input items : Source)
			{
				index++;
				String temp=items.get_eshterak();
				temp=temp.substring(temp.length()-6, temp.length());
				if(temp.equals(Serialno))
				{
					res.add(index);
					break;
				}
			}
			return res;
		}
	}

	class SearchByMeterTrunk implements ISearch
	{
		ArrayList<pdl_input> Source;
		String MeterTrunk;

		SearchByMeterTrunk(ArrayList<pdl_input> source,String meterTrunk)
		{
			Source=source;
			MeterTrunk=InitTrunk(meterTrunk);
		}

		String InitTrunk(String meter)
		{
			if (meter.length() < 12)
			{
				String temp="";
				for (int i = 1; i <= (12 - meter.length()); i++)
					temp = temp + "0";

				return temp + meter;
			}
			return meter;
		}

		@Override
		public ArrayList<Integer> Find() {
			int index=-1;
			ArrayList<Integer> res =new ArrayList<Integer>();
			for (pdl_input items : Source)
			{
				index++;

				if(items.get_shomare_kontor().equals(MeterTrunk))
				{
					res.add(index);
					break;
				}
			}
			return res;
		}
	}
	class SearchByMeterPolump implements ISearch
	{
		ArrayList<pdl_input> Source;
		String MeterPolump;

		SearchByMeterPolump(ArrayList<pdl_input> source,String meterPolump)
		{
			Source=source;
			MeterPolump=InitPolump(meterPolump);
		}

		String InitPolump(String polump)
		{
			if (polump.length() < 15)
			{
				String temp="";
				for (int i = 1; i <= (15 - polump.length()); i++)
					temp = temp + "0";

				return temp + polump;
			}
			return polump;
		}

		@Override
		public ArrayList<Integer> Find() {
			int index=-1;
			ArrayList<Integer> res =new ArrayList<Integer>();
			for (pdl_input items : Source)
			{
				index++;

				if(items.get_Polomp_Kontor().equals(MeterPolump))
				{
					res.add(index);
					break;
				}
			}
			return res;
		}
	}

	class SearchByName implements ISearch
	{
		ArrayList<pdl_input> Source;
		String Name;

		SearchByName(ArrayList<pdl_input> source,String name)
		{
			Source=source;
			Name=name;
		}



		@Override
		public ArrayList<Integer> Find() {
			int index=-1;
			ArrayList<Integer> res =new ArrayList<Integer>();
			for (pdl_input items : Source)
			{
				int findcounter=0;
				index++;
				if(items.get_name_moshtarek().contains(Name))
				{
					findcounter++;
					res.add(index);
					if (findcounter>=5) break;
				}
			}
			return res;
		}
	}

	class SearchByMobile implements ISearch
	{
		ArrayList<pdl_input> Source;
		String Mobile;

		SearchByMobile(ArrayList<pdl_input> source,String mobile)
		{
			Source=source;
			Mobile=mobile;
		}



		@Override
		public ArrayList<Integer> Find() {
			int index=-1;
			ArrayList<Integer> res =new ArrayList<Integer>();
			for (pdl_input items : Source)
			{
				index++;
				if(items.get_Mobile().equals(Mobile))
					res.add(index);
			}
			return res;
		}
	}

	void  Search(ISearch search){
		ArrayList<Integer> RecNoList=search.Find();
		recno_Search=-1;
		if (RecNoList.size()>0)
		{
					Toast.makeText(getApplicationContext(), "مشترک پیدا شد", Toast.LENGTH_SHORT).show();
					recno_Search = RecNoList.get(0);
					noe_gherat.setText(set_data_to_textbox_search());
					finddialog.dismiss();
		}
		else
			Toast.makeText(getApplicationContext(), " اشتراک در سیستم موجود نمی باشد", Toast.LENGTH_SHORT).show();


	}

    class DownloadFileAsync_test extends AsyncTask<String, String, String> {

	       @Override
	       protected void onPreExecute() {
	           super.onPreExecute();
	       }

	       protected String doInBackground(String... aurl) {
	    	   input=da.select_data_from_database();

	           return null;

	       }
	       protected void onProgressUpdate(String... progress) {

	       }

	       protected void onPostExecute(String unused) {




	    	          }
	   }





    //endregion



    //region Async

    public void call1()
    {
        try {

            @SuppressWarnings("unused")

            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME_SendSms_Officer);
            request.addProperty("Shgh", Smsitems._Shgh);
            request.addProperty("Shpa", Smsitems._Shpa);
            request.addProperty("Mobile", Smsitems._Mobile);
            request.addProperty("Imei", Smsitems._Imei);
            Log.e("shgh",Smsitems._Shgh);
            Log.e("shpa",Smsitems._Shpa);
            Log.e("Mobile",Smsitems._Mobile);
            Log.e("imel",Smsitems._Imei);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                    SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            androidHttpTransport.call(SOAP_ACTION_SendSms_Officer, envelope);
            Object result = (Object) envelope.getResponse();
            SendSmsresult=result.toString();
            Log.e("result",result.toString());
        } catch (Exception e) {
            Log.e("err",e.getMessage());
        }

    }
    class DownloadFileAsync_test1 extends AsyncTask<String, String, String> {
        boolean isConnected = false;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            checkConnectivity(getBaseContext());

        }


        protected String doInBackground(String... aurl)
        {


            if (checkConnectivity(getBaseContext())) {

                               isConnected = isAbleToConnect("https://www.google.com", 1000);
                if (isConnected)
                {
                    call1();

                }else
                    SendSmsresult="ErrorNetwork";

            }


            return null;

        }



        protected void onPostExecute(String unused)
        {
            //InsertRecordSuccessfully!273
            if (SendSmsresult.compareTo("ok")==0)
            {
                Toast.makeText(getApplicationContext(),"پیامک قبض با موفقیت ارسال گردید",Toast.LENGTH_SHORT).show();
            }else if (SendSmsresult.compareTo("ErrorNetwork")==0)
                Toast.makeText(getApplicationContext(),"خطا در اتصال به اینترنت",Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isAbleToConnect(String url, int timeout) {
        try {
            java.net.URL myUrl = new URL(url);
            URLConnection connection = myUrl.openConnection();
            connection.setConnectTimeout(timeout);
            connection.connect();
            return true;
        } catch (Exception e) {
            Log.i("exception", "" + e.getMessage());
            return false;
        }
    }
    private boolean isNetworkInterfaceAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }
    private Boolean checkConnectivity(final Context context) {
        if (!isNetworkInterfaceAvailable(context))
            return false;
        else
            return true;
    }
    //endregion
}
