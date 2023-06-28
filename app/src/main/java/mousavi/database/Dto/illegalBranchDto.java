package mousavi.database.Dto;

import android.database.Cursor;

public class illegalBranchDto implements IDto {
    public String comment;
    public String dateSendToServer;
    public String nearestSerialNo;
    public String registerDate;
    public Integer sendToServer = 0;
    public String id;
    public String lat;
    public String lon;
    public String type;
    public String estateid;

    public illegalBranchDto() {
    }
    public illegalBranchDto(Cursor cursor) {
        id=cursor.getString(cursor.getColumnIndex("id"));
        nearestSerialNo=cursor.getString(cursor.getColumnIndex("NearestSerialNo"));
        registerDate=cursor.getString(cursor.getColumnIndex("RegisterDate"));
        comment=cursor.getString(cursor.getColumnIndex("Comment"));
        lat=cursor.getString(cursor.getColumnIndex("lat"));
        lon=cursor.getString(cursor.getColumnIndex("lon"));
        type=cursor.getString(cursor.getColumnIndex("illegalmobiletitleid"));
        estateid=cursor.getString(cursor.getColumnIndex("EstateId"));
    }
}
