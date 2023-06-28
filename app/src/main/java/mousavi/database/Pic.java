package mousavi.database;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;


public class Pic
{
    String filepath;
    String filename;
    //region property

    public  enum  EnumPicType{IllegalBranch,Gheraat};
    private    Boolean _ExistPictureFile ;
    public Boolean ExistPictureFile(){
        return _ExistPictureFile;
    }

    //region pictype
       private EnumPicType _pictype;
       public  EnumPicType get_pictype(){return this._pictype;}
       public void  set_pictype(EnumPicType enumpictype){this._pictype=enumpictype;}
       public void  set_pictype_byint(int enumpictype){
           for (EnumPicType a:EnumPicType.values())
           {
                if (a.ordinal()==enumpictype)
                {
                    this._pictype=a;
                    break;
                }
           }

       }
    //endregion
    //region id
    private long _id;
    public long getid()
    {
        return this._id;
    }
    public void setid(long value)
    {
        this._id = value;
    }
//endregion
    //region idillegal
    private long _idillegal;
    public long getidillegal()
    {
        return this._idillegal;
    }
    public void setidillegal(long value)
    {
        this._idillegal = value;
    }
    //endregion
    //region estateid
    private long _estateid;
    public long getestateid()
    {
        return this._estateid;
    }
    public void setestateid(long value)
    {
        this._estateid = value;
    }
    //endregion
    //region filepath
    private String _filepath;
    public String getfilepath()
    {
        return this._filepath;
    }
    public void setfilepath(String value)
    {
        this._filepath = value;
        try {
            File f = new File(value);
            if (f.exists()) _ExistPictureFile = true;
            else _ExistPictureFile = false;
        }catch (Exception Error){_ExistPictureFile=false;}
    }
    //endregion
    //region RegisterDate
    private String _RegisterDate;
    public String getRegisterDate()
    {
        return this._RegisterDate;
    }
    public void setRegisterDate(String value)
    {
        this._RegisterDate = value;
    }
    //endregion
    //region DateSendToServer
    private String _DateSendToServer;
    public String getDateSendToServer()
    {
        return this._DateSendToServer;
    }
    public void setDateSendToServer(String value)
    {
        this._DateSendToServer = value;
    }
    //endregion
    //region SendToServer
    private long _SendToServer;
    public long getSendToServer()
    {
        return this._SendToServer;
    }
    public void setSendToServer(long value)
    {
        this._SendToServer = value;
    }
    //endregion
    //region FileName
    private String _FileName;
    public String getFileName()
    {
        return this._FileName;
    }
    public void setFileName(String value)
    {
        this._FileName = value;
    }
    //endregion
    //region Serverid
    private long _Serverid;
    public long getServerid()
    {
        return this._Serverid;
    }
    public void setServerid(long value)
    {
        this._Serverid = value;
    }
    //endregion



    //endregion
    //region Constructor
    public Pic()
    {
        // TODO Auto-generated constructor stub
    }
    public Pic()
    {
        // TODO Auto-generated constructor stub
    }
    public Pic(String value)
    {
        // TODO Auto-generated constructor stub
        if ((value !=null) && (value.compareTo("null")>0)) {
            try {
                    JSONObject postalCodesItem = new JSONObject(value);
                    this.setidillegal(Long.valueOf(postalCodesItem.getString("illegalbranchid")));
                    this.setServerid(Long.valueOf(postalCodesItem.getString("Attachmentid")));
                    this.setid(Long.valueOf(postalCodesItem.getString("localid")));
            } catch (Exception e) {
                Log.e("error in json object", e.toString());
            }
        }
    }




//endregion

}