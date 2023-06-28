package mousavi.database;

import android.database.Cursor;

public class illigalBranch {

    private String _Comment;
    private String _DateSendToServer;
    private String _NearestSerialNo;
    private String _RegisterDate;
    private Integer _SendToServer = 0;
    private String _id;
    private String _lat;
    private String _lon;
    private String _type;
    private int _estateid;

    public illigalBranch( ){

    }
    public illigalBranch(Cursor cursor){
        set_id((cursor.getString(cursor.getColumnIndex("id"))));
        set_NearestSerialNo(cursor.getString(cursor.getColumnIndex("NearestSerialNo")));
        set_RegisterDate(cursor.getString(cursor.getColumnIndex("RegisterDate")));
        set_Comment(cursor.getString(cursor.getColumnIndex("Comment")));
        set_lat((cursor.getString(cursor.getColumnIndex("lat"))));
        set_lon((cursor.getString(cursor.getColumnIndex("lon"))));
        set_type((cursor.getString(cursor.getColumnIndex("illegalmobiletitleid"))));
        set_estateid_String((cursor.getString(cursor.getColumnIndex("EstateId"))));
    }

    //region set/get

    public int get_estateid() {
        if (this._type.compareTo("5")==0) return 0;
            else
        return this._estateid;
    }

    public void set_estateid_String(String estateid) {
        this._estateid = Integer.parseInt(estateid);
    }

    public void set_estateid(int estateid) {
        this._estateid = estateid;
    }


    public String get_type() {
        return this._type;
    }

    public void set_type(String type) {
        this._type = type;

    }

    public String get_id() {
        return this._id;
    }

    public void set_id(String id) {
        this._id = id;
    }

    public String get_NearestSerialNo() {
        return this._NearestSerialNo;
    }

    public void set_NearestSerialNo(String NearestSerialNo) {
        this._NearestSerialNo = NearestSerialNo;
    }

    public String get_RegisterDate() {
        return this._RegisterDate;
    }

    public void set_RegisterDate(String RegisterDate) {
        this._RegisterDate = RegisterDate;
    }

    public String get_Comment() {
        return this._Comment;
    }

    public void set_Comment(String Comment) {
        this._Comment = Comment;
    }

    public String get_lat() {
        return this._lat;
    }

    public void set_lat(String lat) {
        this._lat = lat;
    }

    public String get_lon() {
        return this._lon;
    }

    public void set_lon(String lon) {
        this._lon = lon;
    }

    public Integer get_SendToServer() {
        return this._SendToServer;
    }

    public void set_SendToServer(Integer SendToServer) {
        this._SendToServer = SendToServer;
    }

    public String get_DateSendToServer() {
        return this._DateSendToServer;
    }

    public void set_DateSendToServer(String DateSendToServer) {
        this._DateSendToServer = DateSendToServer;
    }
    //endregion
}
