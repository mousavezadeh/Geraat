package mousavi.database;

import android.database.Cursor;

public class ListGheraat {
	private String _Id_List;
	private	String _Doreh;
	private String _Roozkar;
	private String _NameList;
	private String _State;
	private String _Active;
	private String _CurrentRecord;
	private String _id_list_server;
	private String _CodeMamoor;
	private String _ActiveListid;
	private String _Comment;
	private boolean _ExistData=false;

public ListGheraat() {
	// TODO Auto-generated constructor stub

}

	public ListGheraat(Cursor DataofList ) {
		if (DataofList.moveToFirst()) {
			do {
				//Id_List,Doreh,roozkar,CodeMamoor,NameList,State,Active,CurrentRecord  from List_gheraat where Active=1 limit 1
				try {_Id_List=DataofList.getString(DataofList.getColumnIndex("Id_List"));} catch (Exception error){ _Id_List= ""; }
				try {_Doreh=DataofList.getString(DataofList.getColumnIndex("Doreh"));} catch (Exception error){ _Doreh= ""; }
				try {_Roozkar=DataofList.getString(DataofList.getColumnIndex("roozkar"));} catch (Exception error){ _Roozkar= ""; }
				try {_CodeMamoor=DataofList.getString(DataofList.getColumnIndex("CodeMamoor"));} catch (Exception error){ _CodeMamoor= ""; }
				try {_NameList=DataofList.getString(DataofList.getColumnIndex("NameList"));} catch (Exception error){ _NameList= ""; }
				try {_State=DataofList.getString(DataofList.getColumnIndex("State"));} catch (Exception error){ _State= ""; }
				try {_Active=DataofList.getString(DataofList.getColumnIndex("Active"));} catch (Exception error){ _Active= ""; }
				try {_CurrentRecord=DataofList.getString(DataofList.getColumnIndex("CurrentRecord"));} catch (Exception error){ _CurrentRecord= ""; }
				try {_id_list_server=DataofList.getString(DataofList.getColumnIndex("id_list_server"));} catch (Exception error){ _id_list_server= ""; }
				break;
			} while (DataofList.moveToNext());
		}
	}

	public ListGheraat(String DataofList ) {
		// TODO Auto-generated constructor stub
		//Id_List,Doreh,roozkar,CodeMamoor,NameList,State,Active,CurrentRecord
		String[] seprate;
		seprate = DataofList.split(",");

	}

	//region Set

public ListGheraat Set_Comment(String Comment)
{
		this._Comment=Comment;
		return this;
	}
public ListGheraat Set_CodeMamoor(String CodeMamoor)
{
		this._CodeMamoor=CodeMamoor;
		return this;
	}
	public ListGheraat Set_idlistserver(String id_list_server)
{
		this._id_list_server=id_list_server;
		return this;
	}
	public ListGheraat Set_CurrentRecord(String CurrentRecord)
{
		this._CurrentRecord=CurrentRecord;
		return this;
	}
	public ListGheraat Set_Active(String Active)
{
		this._Active=Active;
		return this;
	}
	public ListGheraat Set_State(String State)
{
		this._State=State;
		return this;
	}
	public ListGheraat Set_NameList(String NameList)
{
		this._NameList=NameList;
		return this;
	}
	public ListGheraat Set_IdList(String idlist)
{
	this._Id_List=idlist;
	return this;
}
	public ListGheraat Set_Doreh(String Doreh)
{
		this._Doreh=Doreh;
		return this;
	}
	public ListGheraat Set_Roozkar(String Roozkar)
{
		this._Roozkar=Roozkar;
		return this;
	}
//endregion
	//region Get
	public String Get_Comment()
{
	return this._Comment;
}
	public String Get_CodeMamoor()
{
	return this._CodeMamoor;
}
	public  String Get_idlistserver() { return this._id_list_server; }
	public	String Get_CurrentRecord()
	{
		return this._CurrentRecord;
	}
	public	String Get_Active()
	{
		return  this._Active;
	}
	public	String Get_State()
	{
		return this._State;
	}
	public	String Get_NameList()
	{
		return this._NameList;
	}
	public	String Get_IdList()
	{
		return this._Id_List;
	}
	public	String Get_Doreh()
	{
		return this._Doreh;
	}
	public	String Get_Roozkar()
	{
		return this._Roozkar;
	}
//endregion
}
