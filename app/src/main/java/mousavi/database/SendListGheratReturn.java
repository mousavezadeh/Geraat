package mousavi.database;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class SendListGheratReturn {
    public  enum ResponseType {IllegalBranch,Peymayesh,Serialno,Pressure,Pdl_out};
    public  enum ResponseField {estateid,serialno,insertedid,other,localid}
    String _type;
    SendListGheratReturnItems _sendlistgheratreturnitems;
    private  SendListGheratReturn data;
    public ArrayList<SendListGheratReturn> data_list;
    public  SendListGheratReturn() {}
    public  SendListGheratReturn(String jsonstr){
        ArrayList<SendListGheratReturn> ArrayList_SendListGheratReturn=new ArrayList<SendListGheratReturn>();
        try {
                JSONArray jsonarray = new JSONArray(jsonstr);
                for (int i = 0; i < jsonarray.length(); i++) {
                    JSONObject jsonarrayitems = jsonarray.getJSONObject(i);
                    data=new SendListGheratReturn();
                    data.Set_type(jsonarrayitems.optString("name"));
                    _sendlistgheratreturnitems=new SendListGheratReturnItems(jsonarrayitems);
                    data.Set_sendlistgheratreturnitems(_sendlistgheratreturnitems);
                    ArrayList_SendListGheratReturn.add(data); }
        } catch (Exception e) { Log.e("errorSendLisGeratReturn", e.toString()); }
       this.data_list=ArrayList_SendListGheratReturn;
    }
    public  SendListGheratReturnItems Get_sendlistgheratreturnitems() {
        return this._sendlistgheratreturnitems;
    }
    public void Set_sendlistgheratreturnitems(SendListGheratReturnItems type) {
        this._sendlistgheratreturnitems = type;
    }
    public String Get_type() {
        return this._type;
    }
    public void Set_type(String type) {
        this._type = type;
    }
    public  ArrayList<SendListGheratReturnItems>  GetListByType(ResponseType responsetype) {
        ArrayList<SendListGheratReturnItems> temp=new ArrayList<SendListGheratReturnItems>();
              for (SendListGheratReturn i2 : data_list) {
                  if (responsetype.name().compareTo(i2.Get_type()) == 0)
                      temp= i2.Get_sendlistgheratreturnitems().data_list;
              }

return  temp;

    }
    public  String  Itemstolist(ResponseField responsefield,ResponseType responsetype) {
        String temp="";
        for (SendListGheratReturn i2 : data_list)
            if (responsetype.name().compareTo(i2.Get_type())==0)
            for (SendListGheratReturnItems i1 : i2.Get_sendlistgheratreturnitems().data_list)
            {
                try {
                    if (responsefield.name().compareTo(ResponseField.estateid.name())==0)
                        temp += i1.Get_estateid()+  ",";
                    if (responsefield.name().compareTo(ResponseField.insertedid.name())==0)
                        temp += i1.Get_insertedid()+  ",";
                    if (responsefield.name().compareTo(ResponseField.other.name())==0)
                        temp += i1.Get_other()+  ",";
                    if (responsefield.name().compareTo(ResponseField.serialno.name())==0)
                        temp += i1.Get_serialno()+  ",";
                    if (responsefield.name().compareTo(ResponseField.localid.name())==0)
                        temp += i1.Get_localid()+  ",";


                }catch (Exception error){}
                Log.e("estateid", i1.Get_estateid());
                Log.e("insertedid", i1.Get_insertedid());
                Log.e("other", i1.Get_other());
            }

        return  temp.substring(0,temp.length()>0 ? temp.length()-1 : 0);
    }

}
