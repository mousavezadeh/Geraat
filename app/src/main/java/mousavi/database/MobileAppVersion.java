package mousavi.database;

import android.util.Base64;
import android.util.Log;

import java.nio.charset.StandardCharsets;

public class MobileAppVersion {
	
	//---------------------------------------
	String _id;
	String _version;
	String _Values;
	String _tozihat;
	String _TableName;
	String _SqlQuery;
	String _NoeUpdateDataBase;
	String _NoeUpdate;
	String _FilePath;
	String _Fields;
	String _DateSabt;
	String _OfficerName;
	String _CityName;
	String _MultiOfficer;
	String _CityId;
	String _ServerDateTime;
	String _Permission;
	
	

public MobileAppVersion() {
	// TODO Auto-generated constructor stub
}

	public String get_Permission()
	{return _Permission;}
	public String get_Permissiondecrypt()
	{
		try {
			Log.e("base64--", Base64.encodeToString(_Permission.getBytes("UTF-8"), Base64.NO_WRAP));

			return new String(Base64.decode(_Permission,Base64.DEFAULT), StandardCharsets.UTF_8);
		}catch (Exception Error){
			return "";
		}
		}


	public void set_Permissionencrypt(String Permission)
	{
		Log.e("p",Permission);
		try{this._Permission=Base64.encodeToString(Permission.getBytes("UTF-8"), Base64.NO_WRAP);
			Log.e("pa",this._Permission);
	}
	catch (Exception Error){Log.e("ep",Error.toString()); }}
	public void set_Permission(String Permission)
	{
		this._Permission=Permission.trim().replaceAll("\n","");}
	public String get_ServerDateTime()
	{return _ServerDateTime;}

	public void set_ServerDateTime(String ServerDateTime)
	{this._ServerDateTime=ServerDateTime;}

	public String get_CityId()
	{return _CityId;}

	public void set_CityId(String CityId)
	{this._CityId=CityId;}

	public String get_MultiOfficer()
	{return _MultiOfficer;}

	public void set_MultiOfficer(String MultiOfficer)
	{this._MultiOfficer=MultiOfficer;}


	public String get_CityName()
	{return _CityName;}

	public void set_CityName(String CityName)
	{this._CityName=CityName;}

	public String get_OfficerName()
	{return _OfficerName;}

	public void set_OfficerName(String OfficerName)
{
	this._OfficerName=OfficerName;
}

	public String get_DateSabt()
	{return _DateSabt;}

	public void set_DateSabt(String DateSabt)
{
	this._DateSabt=DateSabt;
}
	public String get_Fields()
	{return _Fields;}

	public void set_Fields(String Fields)
{
	this._Fields=Fields;
}

	public String get_FilePath()
	{return _FilePath;}

public void set_FilePath(String FilePath)
{
	this._FilePath=FilePath;
}


public String get_NoeUpdate()
{return _NoeUpdate;}

public void set_NoeUpdate(String NoeUpdate)
{
	this._NoeUpdate=NoeUpdate;
}

public String get_NoeUpdateDataBase()
{return _NoeUpdateDataBase;}

public void set_NoeUpdateDataBase(String NoeUpdateDataBase)
{
	this._NoeUpdateDataBase=NoeUpdateDataBase;
}

public String get_SqlQuery()
{return _SqlQuery;}

public void set_SqlQuery(String SqlQuery)
{
	this._SqlQuery=SqlQuery;
}

public String get_TableName()
{return _TableName;}

public void set_TableName(String TableName)
{
	this._TableName=TableName;
}

public String get_tozihat()
{return _tozihat;}

public void set_tozihat(String tozihat)
{
	this._tozihat=tozihat;
}
//----------------------------------------------------------
public String get_id()
{return _id;}

public void set_id(String id)
{
	this._id=id;
}
//------------------------------------------------------------------
public String get_version()
{return _version;}

public void set_version(String version)
{
	this._version=version;
}
//------------------------------------------------------------------ Roozkar
public String get_Values()
{return _Values;}

public void set_Values(String Values)
{
	this._Values=Values;
}
//-----------------------------------------------------------------------------
}
