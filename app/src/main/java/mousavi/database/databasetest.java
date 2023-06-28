package mousavi.database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import android.util.Base64;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import mousavi.Geraat.illigalBranch.BaseilligalBranch;
import mousavi.database.Delete.DeleteSendPhotos;
import mousavi.database.Dto.BranchStatusDto;
import mousavi.database.Dto.EstateConditionDto;
import mousavi.database.Dto.OfficerDto;
import mousavi.database.Dto.OutputDto;
import mousavi.database.Dto.PeymayeshDto;
import mousavi.database.Dto.PhotoDto;
import mousavi.database.Dto.ReadingListDto;
import mousavi.database.Dto.ReadingListItemDto;
import mousavi.database.Dto.RozkarDto;
import mousavi.database.Dto.illegalBranchDto;
import mousavi.database.Select.FetchSelectOperation;
import mousavi.database.Select.SelectBranchStatusBySerialno;
import mousavi.database.Select.SelectEstateCondition;
import mousavi.database.Select.SelectEstateConditionBySerilano;
import mousavi.database.Select.SelectOfficer;
import mousavi.database.Select.SelectOutput;
import mousavi.database.Select.SelectPeymayesh;
import mousavi.database.Select.SelectPhoto;
import mousavi.database.Select.SelectReadingList;
import mousavi.database.Select.SelectReadingListItem;
import mousavi.database.Select.SelectReadingListItemByOfficerid;
import mousavi.database.Select.SelectReadingListItemByOfficeridRozkar;
import mousavi.database.Select.SelectRozkar;
import mousavi.database.Select.SelectillegalBranch;
import mousavi.database.Select.TitleValue.BranchStatus;
import mousavi.database.Select.TitleValue.EstateCondition;
import mousavi.database.Select.TitleValue.PhotosSent;
import mousavi.database.Select.TitleValue.TitleValueDto;
import mousavi.database.Update.UpdateOfficerCityName;
import mousavi.database.Update.UpdateOfficerCityid;
import mousavi.database.Update.UpdateOfficerMultiCity;
import mousavi.database.Update.UpdateOfficerName;
import mousavi.database.Update.UpdateOfficerPermission;
import mousavi.database.Update.UpdateVersion;
import mousavi.date.unit.mydate;

class FilterByOfficerId extends BaseFilter {
	private final String officerid;


	public FilterByOfficerId(SQLiteDatabase sqLiteDatabase, String idlist, String officerid) {
		super(sqLiteDatabase, idlist);
		this.officerid = officerid;
	}


	@Override
	protected String Sql() {
		return "select name_moshtarek,radif_tashkhis,eshterak,address,cast(meghdar_ghabli as int) meghdar_ghabli,SendToServer,EstateId,"
				+ " tarikh_ghabli,bedehi,code_mane_feli,karkard_feli,mane_ghabli,doreh,rozkar,code_shahr,shenaseh_ghabz," +
				" shomare_kontor,SendToServer,FKId_List, rowno,karbarititle,mobile,code_posti,Replacementofmeter,EstateConditionId," +
				" BranchKindId,BranchStatusId,m1.Title,m2.Title as KindBranchTitle,m3.Title as BranchStatusTitle,OfficerDescription,ShenaseGhabz,ShenasePardakht,IsUnderConstruction,IsTemporalHousing from inp " +
				" left OUTER  join  Meta as m1 " +
				" on cast(m1.Alias as INT)=ifnull(inp.EstateConditionId,1)  and m1.Node='EstateConditionId' " +
				" left OUTER  join  Meta as m2 " +
				" on cast(m2.Alias as INT)=ifnull(inp.BranchKindId,1) and m2.Node='Branch.Kind' " +
				" left OUTER  join  Meta as m3 " +
				" on cast(m3.Alias as INT)=ifnull(inp.BranchStatusId,1) and m3.Node='Branch.status' " +
				" where FKId_List=? and code_mamor=? order by code_mamor,radif_tashkhis asc ";
	}

	@Override
	ArrayList<pdl_input> Run() {
		Cursor cursor = sqLiteDatabase.rawQuery(Sql(), new String[]{idlist, officerid});
		try {
			return new pdl_input().MakeList(cursor);
		} finally {
			if (cursor != null) cursor.close();
		}

	}
}



abstract class BaseFilter{

	protected BaseFilter(SQLiteDatabase sqLiteDatabase,String idlist){
		this.sqLiteDatabase=sqLiteDatabase;
		this.idlist=idlist;
	}
	protected abstract String Sql();
	abstract ArrayList<pdl_input> Run();
	public SQLiteDatabase sqLiteDatabase;
	public String idlist;
}

public class databasetest {

//region t2
	//region property
	public static String DatabasePath="/mnt/sdcard/ABFA/DataBase/abfapdl_database";
	private   Context mycontext;
	public static SQLiteDatabase db;
	public static String _IDServerList;
	public static String _Imei;
	public static String _CodeMamor = "";
	public static String _roozkar = "";
	public static String _CodeMane="";
	static String _id;
	public static String _idListGheraat;
	public static String _CurrentRecord;
	public static String _FKId_List = "0";
	public static Float _APK_Version =(float) 1.2;
	private static databasetest single_instance = null;
	private final int LIMIT_RecordNumber_for_Select_From_DataBase = 800;
	//endregion
	//public  static SQLiteDatabase db=SQLiteDatabase.openDatabase("/mnt/sdcard/ABFA/DataBase/abfapdl_database", null, SQLiteDatabase.OPEN_READWRITE );
	private databasetest(Context ctn )
	{
		Log.e("Environment",Environment.getExternalStorageDirectory().getPath());
		try {
			if (db != null && db.isOpen())
				return;
			db = SQLiteDatabase.openDatabase(Environment.getExternalStorageDirectory() + "/ABFA/DataBase/abfapdl_database", null,SQLiteDatabase.OPEN_READWRITE);
			//if (db != null && db.isOpen() && !db.isReadOnly()) db.enableWriteAheadLogging();
		}
		catch (Exception error){Log.e("databasetest",error.getMessage());
			db=null; }

	Log.e("databasetest","Created -------->");
	}


	public static databasetest getInstance(Context ctn)
	{
		//db.isDbLockedByCurrentThread()
		if (single_instance == null) {
			single_instance = new databasetest(ctn);
			return single_instance;
		}
		if (single_instance!=null && db==null)
			if (1==1)
			{
				Log.e("secend open","open database--------------> Secen");
				single_instance = new databasetest(ctn);
				return single_instance;
			}
		if (single_instance!=null && db!=null)
			if (!db.isOpen())
			{
				Log.e("secend open","open database--------------> Secen");
				single_instance = new databasetest(ctn);
				return single_instance;
			}
		//if (single_instance!=null) {if (db!=null) if (!db.isOpen()) new databasetest();}
		return single_instance;
	}
	public static void database_close1()
	{

	}
	private static void database_close()
	{

	}

	private static void database_open() {


	}

	// ---------------------------------------------------- check name list

    public static void SetupDataBase()
    {
        //region Create Table
            //region illigalBranch

		try {
			String sql = " CREATE TABLE if NOT EXISTS illigalBranch " +
					" ( " +
					"   id	INTEGER PRIMARY KEY AUTOINCREMENT, " +
					"  NearestSerialNo	TEXT, " +
					"  RegisterDate	TEXT, " +
					" Comment	TEXT, " +
					" lat	TEXT, " +
					" lon	TEXT, " +
					" DateSendToServer	TEXT, " +
					" SendToServer	NUMERIC DEFAULT 0, illegalmobiletitleid INTEGER DEFAULT 0, serverid INTEGER DEFAULT 0, EstateId INTEGER DEFAULT 0) ";
			db.execSQL(sql, new Object[]{});
		}catch (Exception error){
			Log.e("sql error",error.getMessage());
		}
            //endregion
			//region Pressure

		try {
			String sql = " CREATE TABLE if NOT EXISTS Pressure " +
					" ( " +
					"   id	INTEGER PRIMARY KEY AUTOINCREMENT, " +
					"  EstateId	INTEGER NOT NULL, " +
					"  Pressure	INTEGER NOT NULL, " +
					" lat	TEXT, " +
					" lon	TEXT, " +
					" registerdate	TEXT, " +
					" DateSendToServer	TEXT, " +
					"SendToServer	NUMERIC DEFAULT 0,  Description TEXT ,Connectionstatus INTEGER DEFAULT 1)";
			db.execSQL(sql, new Object[]{});
		}catch (Exception error){
			Log.e("sql error",error.getMessage());
		}
		//endregion
            //region Pic
        try {
            String sql = " CREATE TABLE if NOT EXISTS Pic " +
                    " ( " +
                    "  id	INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "  idillegal INTEGER NOT NULL, " +
                    "  filepath TEXT NOT NULL, " +
                    "  RegisterDate	TEXT, " +
                    "  DateSendToServer	TEXT, " +
                    "  SendToServer	NUMERIC DEFAULT 0," +
                    "  FileName TEXT NOT NULL,estateid  INTEGER DEFAULT 0, pictype INTEGER DEFAULT 0 )";
            db.execSQL(sql, new Object[]{});
        }catch (Exception error){
            Log.e("sql error",error.getMessage());
        }




        //endregion






        //endregion
        //region InsertColumn
		//region code_mane_table
			CheckColumnExist("code_mane","Visible","ADD COLUMN"," ");
			CheckColumnExist("code_mane","Created","ADD COLUMN"," ");
			CheckColumnExist("code_mane","Discription","ADD COLUMN"," ");
			//endregion

		//region inp_table
			CheckColumnExist("inp","rowno","ADD COLUMN"," ");
			CheckColumnExist("inp","karbarititle","ADD COLUMN"," ");
			CheckColumnExist("inp","EstateConditionId","ADD COLUMN","INTEGER DEFAULT 1");
			CheckColumnExist("inp","BranchStatusId","ADD COLUMN","INTEGER DEFAULT 1");
			CheckColumnExist("inp","BranchKindId","ADD COLUMN","INTEGER DEFAULT 2");
			CheckColumnExist("inp","EstateId","ADD COLUMN","INTEGER DEFAULT 0");
			CheckColumnExist("inp","OfficerDescription","ADD COLUMN","TEXT ");
            CheckColumnExist("inp","ShenaseGhabz","ADD COLUMN","TEXT ");
            CheckColumnExist("inp","ShenasePardakht","ADD COLUMN","TEXT ");
            CheckColumnExist("inp","IsUnderConstruction","ADD COLUMN","INTEGER DEFAULT 0");
			CheckColumnExist("inp","IsTemporalHousing","ADD COLUMN","INTEGER DEFAULT 0");
			CheckColumnExist("inp","MeterStatus","ADD COLUMN","INTEGER DEFAULT 0");

			//endregion
		//region Presure
			CheckColumnExist("Pressure","Connectionstatus","ADD COLUMN","INTEGER DEFAULT 1");
		//endregion
		//region illigalBranch
		CheckColumnExist("illigalBranch","illegalmobiletitleid","ADD COLUMN","INTEGER DEFAULT 0");
		CheckColumnExist("illigalBranch","serverid","ADD COLUMN","INTEGER DEFAULT 0");
		CheckColumnExist("illigalBranch","EstateId","ADD COLUMN","INTEGER DEFAULT 0");
		//endregion
        //region pic
        CheckColumnExist("Pic","estateid","ADD COLUMN","INTEGER DEFAULT 0");
		CheckColumnExist("Pic","pictype","ADD COLUMN","INTEGER DEFAULT 0");

        //endregion
        //endregion
		//region Alter Table

		//endregion
        //region Insert Record
			//region AddData
			InsertRecord("Meta","Node,Alias,Version,Created,Title","'AddData','4',1,'1398/05/16','شماره تنه کنتور'");
			InsertRecord("Meta","Node,Alias,Version,Created,Title","'AddData','5',1,'1398/05/16','آدرس'");
			InsertRecord("Meta","Node,Alias,Version,Created,Title","'AddData','6',1,'1398/05/16','کد پستی'");
			InsertRecord("Meta","Node,Alias,Version,Created,Title","'AddData','7',1,'1398/05/16','وضعیت ملک'");
			InsertRecord("Meta","Node,Alias,Version,Created,Title","'MeterStatus','1',1,'1402/01/16','کنتور دارد سالم'");
			InsertRecord("Meta","Node,Alias,Version,Created,Title","'MeterStatus','2',1,'1402/01/16','کنتور دارد خراب'");
			InsertRecord("Meta","Node,Alias,Version,Created,Title","'MeterStatus','3',1,'1402/01/16','کنتور ندارد آب مستقیم'");
			InsertRecord("Meta","Node,Alias,Version,Created,Title","'MeterStatus','4',1,'1402/01/16','کنتور ندارد با مجوز شرکت'");

			//InsertRecord("Meta","Node,Alias,Version,Created,Title","'AcceptedReadCode','1',1,'1402/01/16','قرائت شده'");
			InsertRecord("Meta","Node,Alias,Version,Created,Title","'AcceptedReadCode','1',2,'1402/01/16','محل دربسته'");
			InsertRecord("Meta","Node,Alias,Version,Created,Title","'AcceptedReadCode','1',3,'1402/01/16','بخارگرفتگی'");
			InsertRecord("Meta","Node,Alias,Version,Created,Title","'AcceptedReadCode','1',4,'1402/01/16','خرابی کنتور'");

			InsertRecord("Meta","Node,Alias,Version,Created,Title","'AcceptedReadCode','1',6,'1402/01/16','آب مستقیم'");

			InsertRecord("Meta","Node,Alias,Version,Created,Title","'AcceptedReadCode','2',4,'1402/01/16','خرابی کنتور'");
			InsertRecord("Meta","Node,Alias,Version,Created,Title","'AcceptedReadCode','2',6,'1402/01/16','آب مستقیم'");

			InsertRecord("Meta","Node,Alias,Version,Created,Title","'AcceptedReadCode','3',6,'1402/01/16','آب مستقیم'");
			InsertRecord("Meta","Node,Alias,Version,Created,Title","'AcceptedReadCode','4',6,'1402/01/16','آب مستقیم'");
			//endregion
			//region EstateConditionId
				InsertRecord("Meta","Node,Alias,Version,Created,Title","'EstateConditionId','1',1,'1398/05/16','عادی'");
				InsertRecord("Meta","Node,Alias,Version,Created,Title","'EstateConditionId','2',1,'1398/05/16','زمین خالی یا تکمیل نشده'");
				InsertRecord("Meta","Node,Alias,Version,Created,Title","'EstateConditionId','3',1,'1398/05/16','مخروبه'");
				InsertRecord("Meta","Node,Alias,Version,Created,Title","'EstateConditionId','5',1,'1398/05/16','فاقد سکونت یا فعالیت'");
			//endregion
			//region illegal.mobiletitle
				InsertRecord("Meta","Node,Alias,Version,Created,Title","'illegal.mobiletitle','1',1,'1398/05/16','سه راهی قبل کنتور'");
				InsertRecord("Meta","Node,Alias,Version,Created,Title","'illegal.mobiletitle','2',1,'1398/05/16','برعکس کردن کنتور'");
				InsertRecord("Meta","Node,Alias,Version,Created,Title","'illegal.mobiletitle','3',1,'1398/05/16','برداشتن کنتور'");
				InsertRecord("Meta","Node,Alias,Version,Created,Title","'illegal.mobiletitle','4',1,'1398/05/16','بازفروش آب'");
				InsertRecord("Meta","Node,Alias,Version,Created,Title","'illegal.mobiletitle','5',1,'1398/05/16','انشعاب غیرمجاز'");
				InsertRecord("Meta","Node,Alias,Version,Created,Title","'illegal.mobiletitle','6',1,'1398/05/16','شکستن پلمپ'");
                InsertRecord("Meta","Node,Alias,Version,Created,Title","'illegal.mobiletitle','7',1,'1400/03/26','در حال ساخت و ساز'");
                InsertRecord("Meta","Node,Alias,Version,Created,Title","'illegal.mobiletitle','8',1,'1400/03/26','اتمام ساخت و ساز'");
				InsertRecord("Meta","Node,Alias,Version,Created,Title","'illegal.mobiletitle','9',1,'1400/04/16','مصرف با کاربری غیرمجاز'");
				InsertRecord("Meta","Node,Alias,Version,Created,Title","'illegal.mobiletitle','12',1,'1400/09/13','شروع سکونتگاه غیردائم'");
				InsertRecord("Meta","Node,Alias,Version,Created,Title","'illegal.mobiletitle','13',1,'1400/09/13','پایان سکونتگاه غیردائم'");
			//endregion
			//region Branch.status
				InsertRecord("Meta","Node,Alias,Version,Created,Title","'Branch.status','1',1,'1399/10/17','فعال'");
				InsertRecord("Meta","Node,Alias,Version,Created,Title","'Branch.status','2',1,'1399/10/17','قطع موقت'");
				InsertRecord("Meta","Node,Alias,Version,Created,Title","'Branch.status','3',1,'1399/10/17','جمع آوری'");
				InsertRecord("Meta","Node,Alias,Version,Created,Title","'Branch.status','4',1,'1399/10/17','راکد'");
				//InsertRecord("Meta","Node,Alias,Version,Created,Title","'Branch.status','5',1,'1399/10/17','حقوقی'");
				//InsertRecord("Meta","Node,Alias,Version,Created,Title","'Branch.status','6',1,'1399/10/17','غیرمجاز'");
				//InsertRecord("Meta","Node,Alias,Version,Created,Title","'Branch.status','7',1,'1399/10/17','انتقال به روستایی'");
				InsertRecord("Meta","Node,Alias,Version,Created,Title","'Branch.status','0',1,'1399/10/17','نصب نشده'");
			//endregion
			//region Branch.Kind
				InsertRecord("Meta","Node,Alias,Version,Created,Title","'Branch.Kind','1',1,'1399/10/17','آب و فاضلاب'");
				InsertRecord("Meta","Node,Alias,Version,Created,Title","'Branch.Kind','2',1,'1399/10/17','آب'");
				InsertRecord("Meta","Node,Alias,Version,Created,Title","'Branch.Kind','3',1,'1399/10/17','فاضلاب'");

			//endregion
            //region Officer
                InsertRecord("Meta","Node,Alias,Version,Created,Title","'Officer.Cityid','4',1,'1399/11/06',''");
				InsertRecord("Meta","Node,Alias,Version,Created,Title","'Officer.Permission','5',1,'1399/11/06',''");
        //endregion
			//region illigalBranch
		InsertRecord("illigalBranch","id,SendToServer,serverid,EstateId","0,1,1,0");

		//endregion
        //endregion
		//region Update Record
			//UpdateRecord("illigalBranch","SendToServer,serverid,EstateId","1,1,0","id=0");
		//endregion
		//region Update Pragma
		if (db.getVersion()<5)
			db.setVersion(5);
		//endregion
    }

	private static void InsertRecord(String Tablename,String Fields,String Values)
	{
//		INSERT INTO Meta(Node,Alias,Title)
//		SELECT 'AddData1', 1 ,'عادی'
//		WHERE NOT EXISTS(SELECT 1 FROM Meta WHERE Node = 'EstateConditionId' AND Alias = 1)
		//region Make Select Fiedl Values
		String[] DatabaseFields,DatabaseValues;
		DatabaseFields=Fields.split(",");
		DatabaseValues=Values.split(",");

		String SelectQueryCirteria="";
		for (int i=0;i<DatabaseFields.length;i++)
		{
			if((DatabaseFields[i].compareTo("Node")==0)||(DatabaseFields[i].compareTo("Version")==0) ||(DatabaseFields[i].compareTo("Alias")==0))
				SelectQueryCirteria+=" "+DatabaseFields[i]+"="+DatabaseValues[i]+" and";

		}
		if (SelectQueryCirteria.length()>2) SelectQueryCirteria=SelectQueryCirteria.substring(0, SelectQueryCirteria.lastIndexOf("and"));
		//endregion

		 if (SelectQueryCirteria.length()==0) SelectQueryCirteria=" id=0 ";
		String sql = "insert into "+Tablename+" ("+Fields+" ) "
				+ " SELECT "+Values+
				" Where NOT EXISTS(SELECT 1 FROM "+Tablename+" WHERE " +SelectQueryCirteria+" )";

		db.execSQL(sql, new Object[]{});
	}

	private static void UpdateRecord(String Tablename,String Fields,String Values,String Where)
	{

		//region Make Select Fiedl Values
		String[] DatabaseFields,DatabaseValues;
		DatabaseFields=Fields.split(",");
		DatabaseValues=Values.split(",");
		String SelectQueryCirteria="";
		for (int i=0;i<DatabaseFields.length;i++)
		{
			SelectQueryCirteria+=" "+DatabaseFields[i]+"="+DatabaseValues[i]+" ,";
		}
		SelectQueryCirteria=SelectQueryCirteria.substring(0, SelectQueryCirteria.lastIndexOf(","));
		SelectQueryCirteria="update "+Tablename+" set "+SelectQueryCirteria;
		if (Where.compareTo("null")!=0)SelectQueryCirteria+=" where "+Where;
		//endregion
		db.execSQL(SelectQueryCirteria, new Object[]{});

	}


	public static Boolean Get_DisplayMenuState() 
	{
		database_open();
		String temp = "0";
		Boolean ret=false;
		Cursor cursor= db
				.rawQuery("select  Title   from meta where node='Officer.MultiCity'  ",null);
		try
		{

		
		if (cursor.moveToFirst()) 
		{
			do {
					temp = (cursor.getString(0));
					break;
				} while (cursor.moveToNext());
		}

		database_close();
		}catch (Exception e) 
		{
			return false;
		}
		finally {
			if (cursor!=null) cursor.close();

		}
		
		if (temp!=null)
		{	
		if (temp.compareTo("0")==0) 
				ret=false; 
		if (temp.compareTo("1")==0)
				ret=true;
		}

		return ret;
	}



	public static ArrayList<ReadingListDto> Get_Id_Active_ListGheraat() {

		SelectReadingList selectReadingList=new SelectReadingList(db);
		FetchSelectOperation fetchSelectOperation=new FetchSelectOperation(selectReadingList);
		return fetchSelectOperation.fetch(ReadingListDto.class);
	}
	// --------------
	public static void Update_IllegalBranch_Serverid(SendListGheratReturn Serials) {
		database_open();
		for (SendListGheratReturnItems i2 : Serials.GetListByType(SendListGheratReturn.ResponseType.IllegalBranch)) {
			if (!db.isReadOnly())
				db.execSQL("UPDATE illigalBranch set serverid=? where id=? and SendToServer=1  ", new String[]{i2.Get_insertedid(),i2.Get_localid()});
		}
		database_close();
	}



	public static void CheckMark_SendToServerPic(Pic p)
	{
		Calendar c = Calendar.getInstance(Locale.ENGLISH);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strDate = sdf.format(c.getTime());
		database_open();
		String Query = "";

		if (!db.isReadOnly())
			db.execSQL(
					"update pic set SendToServer=1 ,DateSendToServer=? where SendToServer=0  and id=?  "
							, new Object[] { strDate,p.getid() });
		database_close();
	}

	public static void CheckMark_SendToServerIlligalBranch(SendListGheratReturn Serials)
	{
		Calendar c = Calendar.getInstance(Locale.ENGLISH);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strDate = sdf.format(c.getTime());
		database_open();
		String Query = "";

		if (!db.isReadOnly())
			db.execSQL(
					"update illigalBranch set SendToServer=1 ,DateSendToServer=? where SendToServer=0 and id in ("
							+ Serials.Itemstolist(SendListGheratReturn.ResponseField.localid, SendListGheratReturn.ResponseType.IllegalBranch) + ")  ", new Object[] { strDate });
		database_close();
	}

	public static void CheckMark_SendToServerIlligalBranch(String Serials) 
	{

		Calendar c = Calendar.getInstance(Locale.ENGLISH);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strDate = sdf.format(c.getTime());
		database_open();
		String Query = "";
		for (int i = 0; i < Serials.split(",").length; i++) {Query += "?,";}
		if (!db.isReadOnly())
		db.execSQL(
				"update illigalBranch set SendToServer=1 ,DateSendToServer=? where SendToServer=0 and id in ("
						+ Serials + ")  ", new Object[] { strDate });
		database_close();
	}

	public static void CheckMark_SendToServerPeymayesh(SendListGheratReturn Serials)
	{

		Calendar c = Calendar.getInstance(Locale.ENGLISH);
		if (!Serials.data_list.isEmpty()) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String strDate = sdf.format(c.getTime());
				database_open();

				if (!db.isReadOnly())
					db.execSQL(
							"update peymayesh set SendToServer=1 ,DateSendToServer=? where SendToServer=0 and id in ("
									+ Serials.Itemstolist(SendListGheratReturn.ResponseField.localid, SendListGheratReturn.ResponseType.Peymayesh) + ")  ", new Object[]{strDate});
				database_close();
		}
	}
	public static void CheckMark_SendToServerPeymayesh(String Serials) 
	{

		Calendar c = Calendar.getInstance(Locale.ENGLISH);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strDate = sdf.format(c.getTime());
		database_open();
		String Query = "";
		for (int i = 0; i < Serials.split(",").length; i++) {
			Query += "?,";
		}
		if (!db.isReadOnly())
		db.execSQL(
				"update peymayesh set SendToServer=1 ,DateSendToServer=? where SendToServer=0 and id in ("
						+ Serials + ")  ", new Object[] { strDate });
		database_close();
	}

	public static void CheckMark_SendToServerSerialno(SendListGheratReturn Serials, String IdList) {
		Calendar c = Calendar.getInstance(Locale.ENGLISH);
		if (!Serials.data_list.isEmpty())
		{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String strDate = sdf.format(c.getTime());
			database_open();
			if (!db.isReadOnly()) {
				db.execSQL(
						"update inp set SendToServer=1 ,DateSendToServer=? where SendToServer=0 and  cast(eshterak as int) in ("
								+ Serials.Itemstolist(SendListGheratReturn.ResponseField.serialno, SendListGheratReturn.ResponseType.Pdl_out) + ")  ", new Object[]{strDate});
			}
			database_close();
		}
	}

	public static void CheckMark_SendToServerSerialno(String Serials, String IdList) {
		Log.e("Serials", Serials);
		Log.e("idlist", IdList);
		Calendar c = Calendar.getInstance(Locale.ENGLISH);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strDate = sdf.format(c.getTime());
		database_open();
		String Query = "";
		if (!db.isReadOnly()) {
			db.execSQL(
					"update inp set SendToServer=1 ,DateSendToServer=? where SendToServer=0 and  cast(eshterak as int) in ("
							+ Serials + ")  ", new Object[]{strDate});
			Log.e("CheckMark->",Serials);
		}
		database_close();
	}

	public static void CheckMark_SendToServerPressure(SendListGheratReturn Estateid) {

		Calendar c = Calendar.getInstance(Locale.ENGLISH);
		if (!Estateid.data_list.isEmpty())
		{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String strDate = sdf.format(c.getTime());
			database_open();
			if (!db.isReadOnly()) {
				db.execSQL(
						"update Pressure set SendToServer=1 ,DateSendToServer=? where SendToServer=0  and  cast(estateid as int) in ("
								+ Estateid.Itemstolist(SendListGheratReturn.ResponseField.estateid, SendListGheratReturn.ResponseType.Pressure) + ")  ", new Object[]{strDate});

			}
			database_close();
		}
	}
    public static void CheckMark_SendToServerPressure(String Estateid) {

        Calendar c = Calendar.getInstance(Locale.ENGLISH);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strDate = sdf.format(c.getTime());
        database_open();
        if (!db.isReadOnly()) {
            db.execSQL(
                    "update Pressure set SendToServer=1 ,DateSendToServer=? where SendToServer=0  and  cast(estateid as int) in ("
                            + Estateid + ")  ", new Object[]{strDate});
            Log.e("CheckMark->",Estateid);
        }
        database_close();
    }
	// ---------------


	public static void Update_ActivListTo_DeActive(String IdList) {
		database_open();
		if (!db.isReadOnly())
		db.execSQL("update List_Gheraat set Active=0 where Active=1 ",
				new Object[] {  });
		database_close();
	}
	public static void Update_ActivListTo_Active(String IdList) {
		database_open();
		if (!db.isReadOnly())
		db.execSQL("update List_Gheraat set Active=1 where cast(id_list_server as int)=? ",
				new Object[] { IdList });
		database_close();
	}
	// ---------------------------------------------------------
    public static void update_Mobile(Peymayesh inp) {
        try {
            database_open();
            if (!db.isReadOnly())
                db.execSQL(
                        "update inp set mobile=? where estateid=? and fkid_list=?",
                        new Object[] { inp.get_NewValue(),inp._EstateId,inp._Idlist});
        } catch (Exception e) {
            Log.e("error update mobile", e.toString());
        } finally {
            database_close();
        }
    }
    public static void update_OfficerDiscription(pdl_input inp) {
        try {
            database_open();
            if (!db.isReadOnly())
                db.execSQL(
                        "update inp set OfficerDescription=? where eshterak=? and fkid_list=?",
                        new Object[] { inp.get_OfficerDescription(),inp.get_eshterak(),inp.get_FKId_List()});
        } catch (Exception e) {
            Log.e("update", e.toString());
        } finally {
            database_close();
        }
    }
	// 1398/02/29---------------------------------------------------------
	public static void update_gheraat_And_Update_SendToServer(String eshterak,
			String tarikh_feli, String karkard_feli, String code_mane_feli,
			String longg, String lat) {
		try {
			Calendar c = Calendar.getInstance(Locale.ENGLISH);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String strDate = sdf.format(c.getTime());
			database_open();
			if (!db.isReadOnly())
			db.execSQL(
					"update inp set karkard_feli=? ,tarikh_feli=?,code_mane_feli=?,long=?,lat=?,timesabte=?,SendToServer=0  where eshterak=? and fkid_list=?",
					new Object[] { karkard_feli, tarikh_feli, code_mane_feli,
							longg, lat, strDate, eshterak,_FKId_List });

			Log.e("updateok", "ok" + lat + "" + longg);
		} catch (Exception e) {
			Log.e("update", e.toString());
		} finally {
			database_close();
		}
	}
//---------------------------------------------------------------
public static void update_gheraat_And_Update_SendToServer_CloseDoor(pdl_input temppdl) {
	try {
		Calendar c = Calendar.getInstance(Locale.ENGLISH);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		mydate mydate=new mydate();
		String strDate = sdf.format(c.getTime());
		database_open();
		if (!db.isReadOnly())
		db.execSQL(
				"update inp set karkard_feli=? ,tarikh_feli=?,code_mane_feli=1,timesabte=?,SendToServer=0 , masraf2=1 where EstateId=? and FKId_List=?  and  EstateId <> 0",
				new Object[] { temppdl.get_karkard_feli(),
								mydate.current_date_to_fasridate(),
								strDate, temppdl.get_EstateId(),temppdl.get_FKId_List()});

		Log.e("updateok", "ok" );
	} catch (Exception e) {
		Log.e("update", e.toString());
	} finally {
		database_close();
	}
}

//--------------------------------------------------------------------

	public static void update_gheraat(String eshterak, String tarikh_feli,
			String karkard_feli, String code_mane_feli, String longg, String lat) {
		try {
			Calendar c = Calendar.getInstance(Locale.ENGLISH);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String strDate = sdf.format(c.getTime());
			database_open();
			if (!db.isReadOnly())
			db.execSQL(
					"update inp set karkard_feli=? ,tarikh_feli=?,code_mane_feli=?,long=?,lat=?,timesabte=?  where eshterak=? and fkid_list=? ",
					new Object[] { karkard_feli, tarikh_feli, code_mane_feli,
							longg, lat, strDate, eshterak,_FKId_List });

			Log.e("updateok", "ok" + lat + "" + longg);
		} catch (Exception e) {
			Log.e("update", e.toString());
		} finally {
			database_close();
		}
	}



	public static void update_gheraat(String eshterak, String tarikh_feli,
									  String karkard_feli, String code_mane_feli, String longg, String lat,int propertycondition) {
		try {
			Calendar c = Calendar.getInstance(Locale.ENGLISH);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String strDate = sdf.format(c.getTime());
			database_open();
			if (!db.isReadOnly())
				db.execSQL(
						"update inp set karkard_feli=? ,tarikh_feli=?,code_mane_feli=?,long=?,lat=?,timesabte=?,EstateConditionId=?  where eshterak=? and fkid_list=? ",
						new Object[] { karkard_feli, tarikh_feli, code_mane_feli,
								longg, lat, strDate, propertycondition,eshterak,_FKId_List });

			Log.e("updateok", "ok" + lat + "" + longg);
		} catch (Exception e) {
			Log.e("update", e.toString());
		} finally {
			database_close();
		}
	}

	public static void Update_CurrentRecord(String RecordNumber) {
		database_open();
		if (!db.isReadOnly())
		db.execSQL("update List_Gheraat set CurrentRecord=? where Id_List=? ",
				new Object[] { RecordNumber, _FKId_List });
		database_close();
	}




	
	public static void CheckColumnExist(String Tablename,String ColumnName,String Operation,String Query )
	{
		Log.e("openCheckColumnExist", "CheckColumnExist");
		database_open();
		Cursor cursor=null;
		try 
		{
			String SelectQueryCirteria="";
				SelectQueryCirteria="select "+ColumnName+" from "+Tablename+" limit 1" ;
				 cursor = db.rawQuery(SelectQueryCirteria,null);


		}catch(SQLException ex)
		{
			String Sql="Alter Table "+Tablename+" "+Operation+" "+ColumnName+" "+Query;

			db.execSQL(Sql);

			Log.e("sqlexptions", ex.getMessage());
		}
		finally {
			database_close();
			if (cursor!=null) cursor.close();
		}
	}
	
	
	public static void UdateDatabaseVersion_Insert_Record(String Tablename,String Fields,String Values ) 
	{
		database_open();
		Cursor cursor=null;
		try {

			String[] DatabaseFields,DatabaseValues;
			DatabaseFields=Fields.split(",");
			DatabaseValues=Values.split(",");
			
			String SelectQueryCirteria="";
			for (int i=0;i<DatabaseFields.length;i++) 
			{
				if((DatabaseFields[i].compareTo("Node")==0)||(DatabaseFields[i].compareTo("Version")==0))
				SelectQueryCirteria+=" "+DatabaseFields[i]+"="+DatabaseValues[i]+" and";
			}
			
			SelectQueryCirteria=SelectQueryCirteria.substring(0, SelectQueryCirteria.lastIndexOf("and"));
			SelectQueryCirteria="select * from "+Tablename+" where "+SelectQueryCirteria;

			 cursor = db.rawQuery(SelectQueryCirteria,null);

			if (cursor.moveToFirst()) 
			{
				do {break;} while (cursor.moveToNext());
			}
			else
			{
				String sql = "insert into "+Tablename+" ("+Fields+" ) "
						+ "values ("+Values+" )";
				db.execSQL(sql, new Object[]{});
			}
			

			
		
		} catch (Exception e) {
			Log.e("insert_test", "UdateDatabaseVersion_Insert_Record"+e.getMessage());
		} finally {

			 if (cursor!=null) cursor.close();
			database_close();
		}
	}
	
	
	public static void UdateDatabaseTable(String Tablename,String Fields,String Values,String Where ) 
	{
		database_open();
		try {
				
			String[] DatabaseFields,DatabaseValues;
			DatabaseFields=Fields.split(",");
			DatabaseValues=Values.split(",");
			
			String SelectQueryCirteria="";
			for (int i=0;i<DatabaseFields.length;i++) 
			{
				SelectQueryCirteria+=" "+DatabaseFields[i]+"="+DatabaseValues[i]+" ,";
			}
			
			SelectQueryCirteria=SelectQueryCirteria.substring(0, SelectQueryCirteria.lastIndexOf(","));
			SelectQueryCirteria="update "+Tablename+" set "+SelectQueryCirteria;
			if (Where.compareTo("null")!=0)SelectQueryCirteria+=" where "+Where;
			if (!db.isReadOnly())
			db.execSQL(SelectQueryCirteria, new Object[]{});

		
		
		} catch (Exception e) {
			Log.e("Update tables", "UdateDatabase Tables");
		} finally {

			database_close();
		}
	}


	public static void Insert_Peymayesh(Object[] param)
	{
		database_open();
		try {

			String sql = "insert into Peymayesh (Field,Value,Serialno,Idlist,DateSabt,SendToServer) "
					+ "values (?    ,?    ,?       ,?     ,?       , 0   )";
			db.execSQL(sql, param);

		} catch (Exception e) {

		} finally {
			database_close();
		}
	}

	public static void Update_Peymayesh(Object[] param) {
		database_open();
		try {

			String sql = "update  Peymayesh set Value=?,DateSabt=?,SendToServer=0 where id=?";
			if (!db.isReadOnly())
			db.execSQL(sql, param);

		} catch (Exception e) {
			Log.e("update_Peymayesh", "UpdateMobileok");
		} finally {
			database_close();
		}
	}

	// --------------------------------------------------------------------------


	// -----------------------------------------------------------------------------------------
	public static String Insert_To_List_Gheraat(Object[] param) {
		database_open();
		Cursor cursor=null;
		String Id_List = "";
		try {
			String sql = " insert into List_Gheraat (id_list_server,Doreh,Roozkar,CodeMamoor,NameList,State,Active,CurrentRecord) "
					+ " values (?,?,?,?,?,?,1,0)";
			db.execSQL(sql, param);
			 cursor = db
					.rawQuery(
							" select Id_List from  List_Gheraat order by Id_list desc  Limit 1 ",
							null);
			if (cursor.moveToFirst()) {
				do {
					Id_List = (cursor.getString(0));
					break;
				} while (cursor.moveToNext());
			}

		} catch (Exception e) {
			// TODO: handle exception
			Log.d("fac", "Listggggggggggggggggggggg");

		} finally {
			if (cursor!=null) cursor.close();
			database_close();
		}
		return Id_List;
	}

	// -----------------------------------------------------------------------------------------



	
	//------------------------------------------Insert meta

	//-------------------------------------------

	public static Boolean Update_Version(String Version) 
	{
		mydate PersianDate=new mydate();
		UpdateVersion updateVersion=new UpdateVersion(db,GetDataBaseVersion(),PersianDate.current_date_to_fasridate());
		return true;
	}
	public static void Update_OfficerName(String OfficerName) 
	{
		mydate PersianDate=new mydate();
		UpdateOfficerName updateOfficerName=new UpdateOfficerName(db,OfficerName,PersianDate.current_date_to_fasridate());
	}
	public static void Update_OfficerCityName(String OfficerCityName) 
	{
		mydate PersianDate=new mydate();
		UpdateOfficerCityName updateOfficerCityName=new UpdateOfficerCityName(db,OfficerCityName,PersianDate.current_date_to_fasridate());
	}
    public static void Update_OfficerCityid(String OfficerCityid)
    {
        mydate PersianDate=new mydate();
		UpdateOfficerCityid updateOfficerCityid=new UpdateOfficerCityid(db,OfficerCityid,PersianDate.current_date_to_fasridate());
    }
	public static void Update_SettingDisplayMenu(String OfficerMultiCity) 
	{
		mydate PersianDate=new mydate();
		UpdateOfficerMultiCity updateOfficerMultiCity=new UpdateOfficerMultiCity(db,OfficerMultiCity,PersianDate.current_date_to_fasridate());
	}
	public static void Update_OfficerPermission(String OfficerPermission)
	{
		try
		{
		mydate PersianDate=new mydate();
		UpdateOfficerPermission updateOfficerPermission=new UpdateOfficerPermission(db,OfficerPermission,PersianDate.current_date_to_fasridate());

		}catch (Exception Error){
			Log.e("OfficerPermission",Error.getMessage());}
	}
	public static MobileAppVersion Get_OfficerData() 
	{
		MobileAppVersion Temp=new MobileAppVersion();
		database_open();
		Cursor cursor;
		cursor = db.rawQuery("select * from Meta where node like 'Officer.%' ",null);
		try {
			if (cursor.moveToFirst()) {
				do {
					if (cursor.getString(1).trim().compareTo("Officer.Name") == 0) {
						Temp.set_OfficerName(cursor.getString(3) != null ? cursor.getString(3) : "");
					}

					if (cursor.getString(1).trim().compareTo("Officer.CityName") == 0) {
						Temp.set_CityName(cursor.getString(3) != null ? cursor.getString(3) : "");
					}
					if (cursor.getString(1).trim().compareTo("Officer.Cityid") == 0) {
						Temp.set_CityId(cursor.getString(3) != null ? cursor.getString(3) : "");
					}
					if (cursor.getString(1).trim().compareTo("Officer.MultiCity") == 0) {
						Temp.set_MultiOfficer(cursor.getString(3) != null ? cursor.getString(3) : "0");
					}


				} while (cursor.moveToNext());

				database_close();
			}
		}finally {
			if (cursor!=null) cursor.close();
		}
		return Temp;
	}


	public static String Select_OfficerPermissions()
	{
		Log.e("OfficerPermissions", "begin");
		database_open();
		String ReturnValue="";
		Cursor cursor;
		cursor = db.rawQuery("select Title as permission from Meta where node = 'Officer.Permission' limit 1 ",null);
		try {
			if (cursor.moveToFirst()) {
				do {


					ReturnValue= cursor.getString(cursor.getColumnIndex("Title"));

				} while (cursor.moveToNext());

				database_close();
			}
		}catch (Exception Error){Log.e("OfficerPermissions",Error.getMessage());ReturnValue="";}
		finally {
			if (cursor!=null) cursor.close();
		}
		return  new String(Base64.decode(ReturnValue,Base64.DEFAULT), StandardCharsets.UTF_8);

	}

	public static void Select_OfficerData(MobileAppVersion Data) 
	{
		Log.e("Select_OfficerData", "begin");
		database_open();
		Cursor cursor;
		cursor = db.rawQuery("select * from Meta where node like 'Officer.%' ",null);
		try {
			if (cursor.moveToFirst()) {
				do {

					Log.e("first", cursor.getString(1).trim());
					if (cursor.getString(1).trim().compareTo("Officer.Name") == 0) {
						Log.e("Officer.Name", cursor.getString(1).trim());
						Log.e("Officer.Name", Data.get_OfficerName());
						Update_OfficerName(Data.get_OfficerName());
					}

					if (cursor.getString(1).trim().compareTo("Officer.CityName") == 0) {
						Log.e("cityname-fuck", Data.get_CityName());
						Update_OfficerCityName(Data.get_CityName());
					}
					if (cursor.getString(1).trim().compareTo("Officer.MultiCity") == 0) {
						Update_SettingDisplayMenu(Data.get_MultiOfficer());
					}
					if (cursor.getString(1).trim().compareTo("Officer.Cityid") == 0) {
						Update_OfficerCityid(Data.get_CityId());
					}
					if (cursor.getString(1).trim().compareTo("Officer.Permission") == 0) {
						Log.e("p--------------------",cursor.getString(1));
						Update_OfficerPermission(Data.get_Permission());
					}
				} while (cursor.moveToNext());

				database_close();
			}
		}catch (Exception Error){Log.e("Select_OfficerData",Error.getMessage());}
		finally {
			if (cursor!=null) cursor.close();
		}
	}


	public static ArrayList<AddData> Select_Version() 
	{
		ArrayList<AddData> adddata = new ArrayList<AddData>();
		UpdateVersions();
		database_open();
		Cursor cursor=null;
		String MobileNo = "";

		try {
			cursor = db.rawQuery(" select id_meta,title,version,created from meta where node='Version'  ", null);

			if (cursor.moveToFirst()) {
				do {
					AddData Temp = new AddData();
					Temp.set_id(Integer.valueOf(cursor.getString(0)));
					Temp.set_Title((cursor.getString(1)));
					Temp.set_Value((cursor.getString(2)));
					Temp.set_Created((cursor.getString(3)));
					adddata.add(Temp);
					break;
				} while (cursor.moveToNext());




			} else
				{
				String Databaseversion=GetDataBaseVersion();
				Calendar c = Calendar.getInstance(Locale.ENGLISH);
				SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
				String strDate = sdf.format(c.getTime());
				mydate PersianDate = new mydate();
				String sql = " INSERT INTO META (node,alias,created,version,title) " +
						"values (?   ,?    ,?    ,?      ,?)  ";
				db.execSQL(sql, new Object[]{"Version",Databaseversion , PersianDate.current_date_to_fasridate() + " " + strDate, "1.1", "ورژن اولیه سیستم"});


				cursor = db.rawQuery(" select id_meta,title,version,created from meta where node='Version'  "
						,
						new String[]{});

				if (cursor.moveToFirst()) {
					do {

						AddData Temp = new AddData();
						Temp.set_id(Integer.valueOf(cursor.getString(0)));
						Temp.set_Title((cursor.getString(1)));
						Temp.set_Value((cursor.getString(2)));
						Temp.set_Created((cursor.getString(3)));
						adddata.add(Temp);

					} while (cursor.moveToNext());





				}
			}
		}catch (Exception Error){Log.e("Select_Version",Error.getMessage());}
		finally {
			if (cursor!=null) cursor.close();
			database_close();
		}
		return adddata;
	}
	
	//--------------------------------------------
	
	public static void UpdatePRAGMA(String Version)
	{
		database_open();
		db.setVersion(Integer.valueOf(Version));
		database_close();
	}
	private static void UpdateVersions()
	{
		try {
			String Databaseversion=GetDataBaseVersion();
			database_open();
			if (!db.isReadOnly())
			db.execSQL("update meta set version=? where node='Version' ", new String[]{Databaseversion});

		}catch (Exception SqlException){}
		finally {
			database_close();
		}
	}
	public static String GetDataBaseVersion() 
	{
		database_open();
		Cursor cursor;
		String User_Version = "0";
		try {
			if (db != null) {
				User_Version=String.valueOf(db.getVersion());
			}
		}catch (Exception Error){Log.e("GetDataBaseVersion","Error in GetDataBaseVersion");}
		finally
		{
			database_close();
		}

		return User_Version;
	}
	//--------------------------------------------
	public static ArrayList<String> Select_illegalmobiletitle()
	{
		ArrayList<String> adddata = new ArrayList<String>();
		database_open();
		Cursor cursor;
		String MobileNo = "";
		cursor = db.rawQuery(" select id_meta,title,alias from meta where node='illegal.mobiletitle' order by id_meta "
				,
				new String[] { });

		if (cursor.moveToFirst())
		{
			do {
				String Temp;
				Temp=cursor.getString(2)+","+cursor.getString(1);
				Temp=Temp.replace("\n","");
				adddata.add(Temp);
			} while (cursor.moveToNext());

			if (cursor!=null) cursor.close();
			database_close();

		}
		return adddata;
	}
	//--------------------------------------------
	public static ArrayList<EstateConditionDto> Select_EstateCondition()
	{
		SelectEstateCondition selectEstateCondition=new SelectEstateCondition(db);
		FetchSelectOperation fetchSelectOperation=new FetchSelectOperation(selectEstateCondition);
		return fetchSelectOperation.fetch(EstateConditionDto.class);
	}

	public static ArrayList<AddData> Select_Branchstatus()
	{
		ArrayList<AddData> adddata = new ArrayList<AddData>();
		database_open();
		Cursor cursor;
		String MobileNo = "";
		cursor = db.rawQuery(" select id_meta,title,alias from meta where node='Branch.status' and alias not in(0,4) order by id_meta "
				,
				new String[] { });

		if (cursor.moveToFirst())
		{
			do {

				AddData Temp=new AddData();
				Temp.set_id(Integer.valueOf(cursor.getString(2))) ;
				Temp.set_Title((cursor.getString(1))) ;
				Temp.set_Value("") ;
				adddata.add(Temp);

			} while (cursor.moveToNext());

			if (cursor!=null) cursor.close();
			database_close();

		}
		return adddata;
	}



	public static ArrayList<AddData> Select_AddData() 
	{
		ArrayList<AddData> adddata = new ArrayList<AddData>();
		database_open();
		Cursor cursor;
		cursor = db.rawQuery(" select id_meta,title,alias from meta where node='AddData' order by id_meta "
				,
				new String[] { });
try {
	if (cursor.moveToFirst())
	{
		do {
			AddData Temp = new AddData();
			Temp.set_id(Integer.valueOf(cursor.getString(cursor.getColumnIndex("alias"))));
			Temp.set_Title((cursor.getString(cursor.getColumnIndex("title"))));
			Temp.set_Value("");
			adddata.add(Temp);
		} while (cursor.moveToNext());
	}
}finally {
		if (cursor!=null) cursor.close();
			database_close();
	}
		return adddata;
	}

	// -------------------------------------------------------------
	public static ArrayList<AddData> Peymayesh_GetMobile(String Serialno,ArrayList<AddData> ListAddData) {
		
		ArrayList<AddData> AddDataArrayList=new ArrayList<AddData>();
		database_open();
		Cursor cursor=null;
		String MobileNo = "";
		try {
			for (AddData addData : ListAddData) {
				cursor = db.rawQuery(" select field,value,id from peymayesh where field=? "
								+ " and serialno='" + Serialno + "' and sendtoserver=0 order by id desc limit 1 ",
						new String[]{addData._id.toString()});

				AddData Temp = addData;
				if (cursor.moveToFirst()) {
					do {
						if (addData._id.toString().compareTo(cursor.getString(0)) == 0)
						{
							addData.set_Created(cursor.getString(0));
							Temp._Created = cursor.getString(0);
							addData.set_Value(cursor.getString(1));
							addData.set_idpeymayesh(Integer.valueOf(cursor.getString(2)));
						}
					} while (cursor.moveToNext());
				} else {
					addData.set_Value("NotFound");
					Temp._Created = addData._id.toString();
				}
				AddDataArrayList.add(Temp);

			}

		}finally {
			if (cursor!=null) cursor.close();
		}
		database_close();
		return AddDataArrayList;
	}
//-------------------------------------------------------------------------------------------

	public static BranchStatusDto Peymayesh_GetBranchstatus(String serilno) {
		SelectBranchStatusBySerialno selectBranchStatusBySerialno=new SelectBranchStatusBySerialno(db,serilno);
		FetchSelectOperation fetchSelectOperation=new FetchSelectOperation(selectBranchStatusBySerialno);
		return fetchSelectOperation.fetch(BranchStatusDto.class).get(0);
	}

	public static ArrayList<mousavi.database.Select.TitleValue.TitleValueDto> Branchstatus(String serialno) {
		BranchStatus branchStatus=new BranchStatus(db,serialno);
		mousavi.database.Select.TitleValue.Fetch f=new mousavi.database.Select.TitleValue.Fetch(branchStatus);
		return f.fetch();
	}

	public static ArrayList<mousavi.database.Select.TitleValue.TitleValueDto> EstateCondition(String serialno) {
		EstateCondition estateCondition=new EstateCondition(db,serialno);
		mousavi.database.Select.TitleValue.Fetch f=new mousavi.database.Select.TitleValue.Fetch(estateCondition);
		return f.fetch();

	}

//-------------------------------------------------------------------------------------------
	public static EstateConditionDto Peymayesh_GetEstateCondition(String serilno) {
		SelectEstateConditionBySerilano selectEstateConditionBySerilano=new SelectEstateConditionBySerilano(db,serilno);
		FetchSelectOperation fetchSelectOperation=new FetchSelectOperation(selectEstateConditionBySerilano);
		return fetchSelectOperation.fetch(EstateConditionDto.class).get(0);
}
//-------------------------------------------------------------------------------------------
	public static String Select_Code_Mane_ByName(String CodeManeName) {
		String temp="";
		if (CodeManeName.compareTo("همه اشتراک ها")==0)  return	"00";
        if (CodeManeName.contains(" کمتر از5 متر"))  return	"0-5";
		database_open();
		ArrayList<code_mane> your_array_list = new ArrayList<code_mane>();
		Cursor cursor;
			cursor = db
					.rawQuery(
							"select cast(code_gherat as int),title_gherat from code_mane where title_gherat=? ",
							new String[]{CodeManeName});
			try {
				if (cursor.moveToFirst()) {
					do {
						temp = cursor.getString(0);
						break;
					} while (cursor.moveToNext());
				}
			}finally {
				if (cursor!=null) cursor.close();
			}

		database_close();
		return temp;
	}
	//---------------------------------
	public static ArrayList<code_mane> list_code_mane(Boolean Filter) {
		database_open();
		ArrayList<code_mane> your_array_list = new ArrayList<code_mane>();
		Cursor cursor=null;
		try {
			if (Filter)
				cursor = db
						.rawQuery(
								"select cast(code_gherat as int) code,title_gherat from code_mane where cast(code_gherat as int)<>1 and visible='1' ",
								null);
			else
				cursor = db
						.rawQuery(
								"select cast(code_gherat as int) code,title_gherat from code_mane  ",
								null);

			if (cursor.moveToFirst()) {
				do {
					code_mane temp = new code_mane();
					temp.set_code_mane(cursor.getString(cursor.getColumnIndex("code")));
					temp.set_title_mane(cursor.getString(cursor.getColumnIndex("title_gherat")));
					your_array_list.add(temp);
				} while (cursor.moveToNext());
			}
		}finally {
			if (cursor!=null) cursor.close();
		}

		database_close();
		return your_array_list;
	}
	// -----------------------------------------------------------------------------list
	// code mane
	public static ArrayList<code_mane> AcceptedReadCodeList(int meterstatus) {
		database_open();
		ArrayList<code_mane> your_array_list = new ArrayList<code_mane>();
		Cursor cursor=null;
		try {
				String Meterstatus=String.valueOf(meterstatus);
				cursor = db
						.rawQuery(
								"select Version as codemane,Title from Meta where Node='AcceptedReadCode' and Alias=? and Title <> 'قرائت شده' ",
								new String[] {Meterstatus});
			if (cursor.moveToFirst()) {
				do {
					code_mane temp = new code_mane();
					temp.set_code_mane(cursor.getString(cursor.getColumnIndex("codemane")));
					temp.set_title_mane(cursor.getString(cursor.getColumnIndex("Title")));
					your_array_list.add(temp);
				} while (cursor.moveToNext());
			}
		}finally {
			if (cursor!=null) cursor.close();
		}
		database_close();
		return your_array_list;
	}


	// search data in database -----------------------------------------


	public static String[] Select_Code_MamoorOnlyCount_FromList_CodeGherat() 
	{
		database_open();
		String your_array_list = "";
		String[] t;
		Cursor cursor = db.rawQuery(
				" select itable.code_mamor,count(*) as tedad  from inp as itable "
						+
						" where  itable.fkid_list=? "
						+ " group by itable.code_mamor",
				new String[] { databasetest._FKId_List });

		int a = 0;
		if (cursor.moveToFirst()) {
			do {
				a = a + 1;
				your_array_list = your_array_list + cursor.getString(1) + "/";

			} while (cursor.moveToNext());
		}
		if (cursor!=null) cursor.close();
		database_close();
		String[] seprate = your_array_list.split("/");
		return seprate;
	}
	
// -----------------------------------------------------------------------

	public static String[] Select_Code_MamoorOnlyCount_FromList() 
	{
		database_open();
		String your_array_list = "";
		String[] t;
		Cursor cursor = db.rawQuery(
				" select itable.code_mamor,count(*) as tedad  from inp as itable "
						+

						" where  itable.fkid_list=? "
						+ " group by itable.code_mamor",
				new String[] { databasetest._FKId_List });


		int a = 0;
		try {
			if (cursor.moveToFirst()) {
				do {
					a = a + 1;
					your_array_list = your_array_list + cursor.getString(1) + "/";

				} while (cursor.moveToNext());
			}
		}finally {
			if (cursor!=null) cursor.close();
		}

		database_close();
		String[] seprate = your_array_list.split("/");
		return seprate;
	}

	//---------------------------------------------------------------------
	public static ArrayList<OfficerDto> Select_Code_MamoorOnly_FromList(String idlist) {
		SelectOfficer selectOfficer=new SelectOfficer(db,idlist);
		FetchSelectOperation fetchSelectOperation=new FetchSelectOperation(selectOfficer);
		return fetchSelectOperation.fetch(OfficerDto.class);

	}

	public static String[] Select_Code_Mamoor_FromList() {
		database_open();
		String your_array_list = "";
		String[] t;
		Cursor cursor = db.rawQuery(
				" select itable.code_mamor,count(*) as tedad  from inp as itable "
						+

						" where  itable.fkid_list=? "
						+ " group by itable.code_mamor",
				new String[] { databasetest._FKId_List });

		Cursor cursorcount = db.rawQuery(
				" select count(c),sum(c) from (select count(code_mamor) c  from inp  "
						+
						" where  fkid_list=? "
						+ " group by code_mamor)",
				new String[] { databasetest._FKId_List });
	String CountofCodemamor="",SumofSerial="";

	try {
		if (cursorcount.moveToFirst()) {
			do {
				CountofCodemamor = cursorcount.getString(0);
				SumofSerial = cursorcount.getString(1);

			} while (cursorcount.moveToNext());
		}


		int a = 0;
		your_array_list = "همه مامورها" + " "
				+ CountofCodemamor + "," + "تعداد اشتراک" + " "
				+ SumofSerial + "/";
		if (cursor.moveToFirst()) {
			do {
				a = a + 1;
				your_array_list = your_array_list + "کد مامور" + " "
						+ cursor.getString(0) + "," + "تعداد اشتراک" + " "
						+ cursor.getString(1) + "/";

			} while (cursor.moveToNext());
		}
	}finally {
		if (cursor!=null) cursor.close();
		cursorcount.close();
	}

		database_close();
		String[] seprate = your_array_list.split("/");
		return seprate;
	}

	public static String[] Select_Gozaresh_Gheraat() {
		database_open();
		String your_array_list = "";
		String[] t;
		Cursor cursor,countcursor;
		
		if (_CodeMamor.compareTo("")==0)
		{
		 cursor = db
				.rawQuery(
						"select itable.code_mane_feli,mtable.title_gherat,count(*) as tedad from inp as itable,code_mane as mtable"
								+ " where cast(itable.code_mane_feli as int)=cast(mtable.code_gherat as int)"
								+ " and itable.fkid_list=? "
								+ " group by cast(itable.code_mane_feli as int)  UNION ALL "
                                + " select '1',' کمتر از5 متر',ifnull(sum(case when cast(itable.karkard_feli as int)-cast(itable.meghdar_ghabli as int) BETWEEN 0 and 5 THEN 1 else 0  END),0) as tedad from inp as itable,code_mane as mtable "
                        + "  where cast(itable.code_mane_feli as int)=cast(mtable.code_gherat as int)  and itable.fkid_list=?   and  cast(itable.code_mane_feli as INT)=1 ",
						new String[] { databasetest._FKId_List,databasetest._FKId_List });
		 countcursor = db
					.rawQuery(
							"select count(*) as tedad from inp "
									+ " where "
									+ " fkid_list=? ",
							new String[] { databasetest._FKId_List });
		}
		else
		{
		 cursor = db
					.rawQuery(
							"select itable.code_mane_feli,mtable.title_gherat,count(*) as tedad from inp as itable,code_mane as mtable"
									+ " where cast(itable.code_mane_feli as int)=cast(mtable.code_gherat as int)"
									+ " and itable.fkid_list=? and cast(itable.code_mamor as int)=?"
									+ " group by itable.code_mane_feli",
							new String[] { databasetest._FKId_List,_CodeMamor });

		 countcursor = db
					.rawQuery(
							"select count(*) as tedad from inp "
									+ " where "
									+ " fkid_list=? and  cast(code_mamor as int)=?",
							new String[] { databasetest._FKId_List ,_CodeMamor });
		}
		
	String CountofSerial="";
		try {
			if (countcursor.moveToFirst()) {
				do {
					CountofSerial = countcursor.getString(0);

				} while (countcursor.moveToNext());
			}
			your_array_list = "همه اشتراک ها" + ","
					+ CountofSerial + "/";
			int a = 0;
			if (cursor.moveToFirst()) {
				do {
					a = a + 1;
					your_array_list = your_array_list + cursor.getString(1) + ","
							+ cursor.getString(2) + "/";
				} while (cursor.moveToNext());
			}
		}finally {
			if (cursor!=null) cursor.close();
			if (countcursor!=null) countcursor.close();
		}

		database_close();
		String[] seprate = your_array_list.split("/");
		return seprate;
	}


//endregion


// -----------------------------------------------------------------------------
	public static ArrayList<ReadingListItemDto> select_data_from_database_by_codemamor(String idlist,String officerid)
	{
		SelectReadingListItemByOfficerid selectReadingListItemByOfficerid=new SelectReadingListItemByOfficerid(db,idlist,officerid);
		FetchSelectOperation fetchSelectOperation=new FetchSelectOperation(selectReadingListItemByOfficerid);
		return fetchSelectOperation.fetch(ReadingListItemDto.class);
	}

	//--------------------------------------------------------------------------------------
	public static ArrayList<ReadingListItemDto> select_data_from_database_by_codemamorroozkar(String idlist,String officerid,String rozkar)
	{
		SelectReadingListItemByOfficeridRozkar selectReadingListItemByOfficeridRozkar=new SelectReadingListItemByOfficeridRozkar(db,idlist,officerid,rozkar);
		FetchSelectOperation fetchSelectOperation=new FetchSelectOperation(selectReadingListItemByOfficeridRozkar);
		return fetchSelectOperation.fetch(ReadingListItemDto.class);
}
//---------------------------------------------------------------------------------
	public static ArrayList<pdl_input> select_data_from_database_by_codemamorcodemane(String codemane) 
	{
		database_open();
		ArrayList<pdl_input> your_array_list = new ArrayList<pdl_input>();
        Cursor cursor;
        if (codemane.compareTo("0-5")==0)
        {
            cursor = db
                    .rawQuery(
                            " select name_moshtarek,radif_tashkhis,eshterak,address,cast(meghdar_ghabli as int) meghdar_ghabli,SendToServer,EstateId,"
                                    + " tarikh_ghabli,bedehi,code_mane_feli,karkard_feli,mane_ghabli,doreh,rozkar,code_shahr,shenaseh_ghabz," +
                                    " shomare_kontor,SendToServer,FKId_List, rowno,karbarititle,mobile,code_posti,Replacementofmeter,EstateConditionId," +
                                    " BranchKindId,BranchStatusId,m1.Title,m2.Title as KindBranchTitle,m3.Title as BranchStatusTitle,OfficerDescription,ShenaseGhabz,ShenasePardakht,IsUnderConstruction,IsTemporalHousing from inp " +
                                    " left OUTER  join  Meta as m1 " +
                                    " on cast(m1.Alias as INT)=ifnull(inp.EstateConditionId,1)  and m1.Node='EstateConditionId' " +
                                    " left OUTER  join  Meta as m2 " +
                                    " on cast(m2.Alias as INT)=ifnull(inp.BranchKindId,1) and m2.Node='Branch.Kind' " +
                                    " left OUTER  join  Meta as m3 " +
                                    " on cast(m3.Alias as INT)=ifnull(inp.BranchStatusId,1) and m3.Node='Branch.status' " +
                                    " where  FKId_List=?  and code_mamor=? and cast(code_mane_feli as int)=1 and (cast(karkard_feli as INT) - cast(meghdar_ghabli as int) BETWEEN 0 and 5) order by code_mamor,radif_tashkhis asc ",
                            new String[]{databasetest._FKId_List, databasetest._CodeMamor, codemane});
        }
        else {
            cursor = db
                    .rawQuery(
                            " select name_moshtarek,radif_tashkhis,eshterak,address,cast(meghdar_ghabli as int) meghdar_ghabli,SendToServer,EstateId,"
                                    + " tarikh_ghabli,bedehi,code_mane_feli,karkard_feli,mane_ghabli,doreh,rozkar,code_shahr,shenaseh_ghabz," +
                                    " shomare_kontor,SendToServer,FKId_List, rowno,karbarititle,mobile,code_posti,Replacementofmeter,EstateConditionId," +
                                    " BranchKindId,BranchStatusId,m1.Title,m2.Title as KindBranchTitle,m3.Title as BranchStatusTitle,OfficerDescription,ShenaseGhabz,ShenasePardakht,IsUnderConstruction,IsTemporalHousing from inp " +
                                    " left OUTER  join  Meta as m1 " +
                                    " on cast(m1.Alias as INT)=ifnull(inp.EstateConditionId,1)  and m1.Node='EstateConditionId' " +
                                    " left OUTER  join  Meta as m2 " +
                                    " on cast(m2.Alias as INT)=ifnull(inp.BranchKindId,1) and m2.Node='Branch.Kind' " +
                                    " left OUTER  join  Meta as m3 " +
                                    " on cast(m3.Alias as INT)=ifnull(inp.BranchStatusId,1) and m3.Node='Branch.status' " +
                                    " where  FKId_List=?  and code_mamor=? and cast(code_mane_feli as int)=? order by code_mamor,radif_tashkhis asc ",
                            new String[]{databasetest._FKId_List, databasetest._CodeMamor, codemane});
        }
try {
	your_array_list = new pdl_input().MakeList(cursor);
}finally {
	if (cursor!=null) cursor.close();
}

		database_close();
		return your_array_list;
	}
//-------------------------------------------------------------------------------
	public static ArrayList<pdl_input> select_data_from_database_by_CodeMane(String CodeMane) {

		ArrayList<pdl_input> your_array_list = new ArrayList<pdl_input>();
		return your_array_list;
	}
//----------------------------------------------------------------------------------


//region t1
	public static ArrayList<AddData> Get_Roozkar() {
	database_open();
	ArrayList<AddData> your_array_list = new ArrayList<AddData>();
	Cursor cursor = db.rawQuery("SELECT rozkar,FKId_List from inp WHERE FKId_List = ? and  CAST(code_mamor as int)=? group by rozkar",
			new String[] { databasetest._FKId_List , databasetest._CodeMamor});
	if (cursor.moveToFirst()) {
		do {
			AddData temp = new AddData();
			temp.set_Value(cursor.getString(cursor.getColumnIndex("rozkar")));
			temp.set_Title(cursor.getString(cursor.getColumnIndex("FKId_List")));
			your_array_list.add(temp);
		} while (cursor.moveToNext());
	}
	if (cursor!=null) cursor.close();
	database_close();
	return your_array_list;
}

	public RozkarDto Get_Roozkar_List(String idlist,String officerid) {

		SelectRozkar selectRozkar=new SelectRozkar(db,idlist,officerid);
		FetchSelectOperation fetchSelectOperation=new FetchSelectOperation(selectRozkar);
		return fetchSelectOperation.fetch(RozkarDto.class).get(0);
	}
	// -----------------------------------------------------------------------------
	public static ArrayList<ReadingListItemDto> select_data_from_database(String idlist) {
		SelectReadingListItem readingListItems=new SelectReadingListItem(db,idlist);
		FetchSelectOperation fetchSelectOperation=new FetchSelectOperation(readingListItems);
		return fetchSelectOperation.fetch(ReadingListItemDto.class);
	}

	// ----------------------------------------------------------------------------

	public static pdl_input select_data_from_database1()
	{
		database_open();
		pdl_input temp=null ;
		Cursor cursor = db
				.rawQuery(
						"select name_moshtarek,radif_tashkhis,eshterak,address,cast(meghdar_ghabli as int) meghdar_ghabli,SendToServer,EstateId,"
								+ " cast(karbari as int) karbari,tarikh_ghabli,bedehi,code_mane_feli,karkard_feli,mane_ghabli,doreh,rozkar,code_shahr,shenaseh_ghabz," +
								" shomare_kontor,SendToServer,FKId_List, rowno,karbarititle,mobile,code_posti,Replacementofmeter,EstateConditionId,Meta.Title from inp " +
								" left OUTER  join  Meta " +
								" on cast(Meta.Alias as INT)=ifnull(inp.EstateConditionId,1)  " +
								" where Meta.Node='EstateConditionId' and code_mamor=? and FKId_List=? " +
								" order by datetime(timesabte) desc limit 1 ",
						new String[] { databasetest._CodeMamor ,databasetest._FKId_List});
		try {
			if (cursor.getCount() > 0)
				temp = new pdl_input().MakeList(cursor).get(0);
		}finally {
			if (cursor!=null) cursor.close();
		}

		database_close();
		return temp;
	}

	// ----------------------------------------------------------------------------

	public static pdl_input select_data_from_database_closedoor(String CityId,String EstateId)
	{//Officer.Cityid
		String SerialNo="";
		int EstateIdLengh=6-EstateId.length();
		if ((EstateIdLengh>0)&&(EstateIdLengh<=5))
		{
			String Temp="";
			for (int i=0;i<EstateIdLengh;i++)
			{
				Temp+="0";
			}
			SerialNo=CityId+Temp+EstateId;
		}else SerialNo=CityId+EstateId;

		Log.e("se--",SerialNo);
		database_open();
		pdl_input temp=null ;
		Cursor cursor = db
				.rawQuery(
						"select name_moshtarek,radif_tashkhis,eshterak,address,meghdar_ghabli,SendToServer,EstateId,"
								+ " cast(karbari as int) karbari,tarikh_ghabli,bedehi,code_mane_feli,karkard_feli,mane_ghabli,doreh,rozkar,code_shahr,shenaseh_ghabz," +
								"shomare_kontor,SendToServer,FKId_List, rowno,karbarititle,mobile,code_posti,Replacementofmeter,EstateConditionId,Meta.Title from inp " +
								" left OUTER  join  Meta " +
								" on cast(Meta.Alias as INT)=ifnull(inp.EstateConditionId,1)  " +
								" where Meta.Node='EstateConditionId' " +
								" and (cast(eshterak as int)=?) " +
								" and (sendtoserver=1 or SendToServer=0)  and (cast(code_mane_feli as int)=2 ) " +
								"order by cast(doreh as int) desc limit 1 ",
						new String[] { SerialNo });


		try {
			if (cursor.getCount() > 0)
				temp = new pdl_input().MakeList(cursor).get(0);
		}finally {
			if (cursor!=null) cursor.close();
		}

		database_close();
		return temp;
	}

	//--------------------------------------------------------------------------------
	public static String select_data_for_illigalbranch_device_string_Json() 
	{
		SelectillegalBranch selectillegalBranch=new SelectillegalBranch(db);
		String res=Extentions.Extention.ToJson(new FetchSelectOperation(selectillegalBranch).fetch(illegalBranchDto.class));
		if (res=="") return "RecordNotFoudForSendToServer";
		return res;
	}

	//--------------------------------------------------------------------------------
	public static ArrayList<PhotoDto> select_data_for_pic_device()
	{
		SelectPhoto selectPhoto=new SelectPhoto(db);
		FetchSelectOperation fetchSelectOperation=new FetchSelectOperation(selectPhoto);
		return fetchSelectOperation.fetch(PhotoDto.class);
	}
	//-------------------------------------------------------------------------------------



	public  void  DeletePic(ArrayList<mousavi.database.Select.TitleValue.TitleValueDto> listofsendphotosidvalue)
	{
		DeleteSendPhotos deleteSendPhotos=new DeleteSendPhotos(db,Extentions.Extention.ToStringList(listofsendphotosidvalue));
		deleteSendPhotos.execute();

	}
	public static ArrayList<mousavi.database.Select.TitleValue.TitleValueDto> select_data_for_del_pic_list()
	{
		PhotosSent photosSent=new PhotosSent(db);
		mousavi.database.Select.TitleValue.Fetch listofpic=new mousavi.database.Select.TitleValue.Fetch(photosSent);
		return listofpic.fetch();
	}


	// ----------------------------------------------------------------------------------
	public static String select_data_for_peymayesh_device_string_Json(
			String idlist) {
		SelectPeymayesh selectPeymayesh=new SelectPeymayesh(db);
		String res=Extentions.Extention.ToJson(new FetchSelectOperation(selectPeymayesh).fetch(PeymayeshDto.class));
		if (res=="") return "RecordNotFoudForSendToServer";
		return res;
	}

	// ----------------------------------------------------------------------------------

	public static Boolean Get_Count_PropertyConditions(String Eshterak,int Estateconditionid)
	{
		database_open();
		Cursor cursor = db
				.rawQuery(
						"select count(*) as Tedad from inp"
								+ " INNER JOIN Peymayesh " +
								" on inp.FKId_List=Peymayesh.Idlist " +
								" and inp.eshterak=Peymayesh.Serialno " +
								" where Peymayesh.Field=7 and Peymayesh.Value>1 and cast(inp.code_mane_feli as int)=1 and eshterak=?",
						new String[] { Eshterak });
		int Count=0;
		if (cursor.moveToFirst())
		{
			do
			{
				Count=Integer.valueOf(cursor.getString(cursor.getColumnIndex("Tedad")));
			}
			while (cursor.moveToNext());
		}
		if (cursor!=null) cursor.close();
		database_close();
		return Count != 0;
	}
	public static Boolean Get_Count_PropertyConditions(String Eshterak)
	{
		database_open();
		Cursor cursor = db
				.rawQuery(
						"select count(*) as Tedad from inp"
								+ " INNER JOIN Peymayesh " +
								" on inp.FKId_List=Peymayesh.Idlist " +
								" and inp.eshterak=Peymayesh.Serialno " +
								" where Peymayesh.Field=7 and Peymayesh.Value=1 and cast(inp.code_mane_feli as int)=1 and eshterak=?",
						new String[] { Eshterak });
		int Count = 0;
		try {

			if (cursor.moveToFirst()) {
				do {
					Count = Integer.valueOf(cursor.getString(cursor.getColumnIndex("Tedad")));
				}
				while (cursor.moveToNext());
			}
		}finally {
			if (cursor!=null) cursor.close();
		}
		database_close();
		return Count != 0;
	}

	//---------------------------------------------------------------------------------------
	public static String select_data_for_output_pdl_device_string_Json(
			String idlist) {
		SelectOutput selectOutput=new SelectOutput(db);
		String res=Extentions.Extention.ToJson(new FetchSelectOperation(selectOutput).fetch(OutputDto.class));
		if (res=="") return "RecordNotFoudForSendToServer";
		return res;
	}


//endregion

}
