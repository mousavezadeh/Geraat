package mousavi.database;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class SendListGheratReturnItems {
	String _estateid;
	String _serialno;
	String _insertedid;
	String _other;
	String _Localid;
	SendListGheratReturnItems data;
	public ArrayList<SendListGheratReturnItems> data_list;

	public SendListGheratReturnItems( ){}


	public SendListGheratReturnItems(JSONObject sendlistgheratreturnitems)
	{
		ArrayList<SendListGheratReturnItems> ArrayList_SendListGheratReturnItems=new ArrayList<SendListGheratReturnItems>();
		try {
			JSONArray jsonarray = new JSONArray(sendlistgheratreturnitems.optString("valueslist"));
			for (int i = 0; i < jsonarray.length(); i++)
			{
				JSONObject jsonarrayitems = jsonarray.getJSONObject(i);
				data=new SendListGheratReturnItems();
				data.Set_estateid(jsonarrayitems.optString("Estateid"));
				data.Set_insertedid(jsonarrayitems.optString("Insertedid"));
				data.Set_other(jsonarrayitems.optString("Otherid"));
				data.Set_serialno(jsonarrayitems.optString("Serialno"));
				data.Set_localid(jsonarrayitems.optString("Localid"));
				ArrayList_SendListGheratReturnItems.add(data);
			}

		} catch (Exception e)
		{
			Log.e("errorSendLisGeratReturn", e.toString());
		}
		this.data_list=ArrayList_SendListGheratReturnItems;
	}

	public SendListGheratReturnItems(SendListGheratReturnItems  sendlistgheratreturnitems){
		this._estateid=sendlistgheratreturnitems._estateid;
		this._insertedid=sendlistgheratreturnitems._insertedid;
		this._serialno=sendlistgheratreturnitems._serialno;
		this._other=sendlistgheratreturnitems._other;
		this._Localid=sendlistgheratreturnitems._Localid;
	}




	//region method
	public String Get_estateid() {
		return this._estateid;
	}

	public void Set_estateid(String type) { this._estateid = type; }

	public String Get_serialno() {
		return this._serialno;
	}

	public void Set_serialno(String serialno) {
		this._serialno = serialno;
	}


	public String Get_insertedid() {
		return this._insertedid;
	}

	public void Set_insertedid(String insertedid) {
		this._insertedid = insertedid;
	}

	public String Get_other() {
		return this._other;
	}

	public void Set_other(String other) {
		this._other = other;
	}

	public  String Get_localid(){return  this._Localid;}
	public  void Set_localid(String localid){ this._Localid=localid;}

//endregion
}
