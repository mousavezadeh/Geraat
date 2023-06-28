package mousavi.database;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Peymayesh
{
	Integer _id;
	Integer _Field;
	String _Value;
	String _Serialno;
	Integer _Idlist;
	String _DateSabt;
	Integer _SendToServer=0;
	String _DateSendToServer;
	String _NewValue;
	int _EstateId;
	
public Peymayesh() 
{
	// TODO Auto-generated constructor stub
}


	public Integer get_EstateId()
	{return _EstateId;}

	public void set_EstateId(Integer EstateId)
	{
		this._EstateId=EstateId;
	}
public String get_DateSendToServer()
{return _DateSendToServer;}

public void set_DateSendToServer(String DateSendToServer)
{
	this._DateSendToServer=DateSendToServer;
}

public Integer get_SendToServer()
{return _SendToServer;}

public void set_SendToServer(Integer SendToServer)
{
	this._SendToServer=SendToServer;
}

public String get_DateSabt()
{return _DateSabt;}


public void set_DateSabt(String DateSabt)
{
	this._DateSabt=DateSabt;
}

	public void set_DateSabtNow()
	{
		Calendar c = Calendar.getInstance(Locale.ENGLISH);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this._DateSabt=sdf.format(c.getTime());
	}


public Integer get_Idlist()
{return _Idlist;}

public void set_Idlist(Integer Idlist)
{
	this._Idlist=Idlist;
}

public String get_Serialno()
{return _Serialno;}

public void set_Serialno(String Serialno)
{
	this._Serialno=Serialno;
}
//----------------------------------------------------------
public Integer get_id()
{return _id;}

public void set_id(Integer id)
{
	this._id=id;
}
public Integer get_Field()
{return _Field;}

public void set_Field(Integer Field)
{
	this._Field=Field;
}

public String get_NewValue()
{return _NewValue;}

public void set_NewValue(String NewValue)
{
	this._NewValue=NewValue;
}
public String get_Value()
{return _Value;}

public void set_Value(String Value)
{
	this._Value=Value;
}
//-----------------------------------------------------------------------------

	public void InsertPeymayeh_PropertyCondition(pdl_input pdl,String NewEstateConditionId)
	{
		Peymayesh peymayesh=new Peymayesh();
		peymayesh.set_Field(7);
		peymayesh.set_NewValue(NewEstateConditionId);
		peymayesh.set_Serialno(pdl.get_eshterak());
		peymayesh.set_Idlist(Integer.valueOf(pdl.get_FKId_List()));
		peymayesh.set_DateSabtNow();

		
	}



}


