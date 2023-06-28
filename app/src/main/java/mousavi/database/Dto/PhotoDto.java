package mousavi.database.Dto;

import android.database.Cursor;

public class PhotoDto implements IDto {
    public Integer pictype;
    public Long id;
    public Long idillegal;
    public Long estateid;
    public String filepath;
    public String registerDate;
    public String dateSendToServer;
    public int sendToServer=0;
    public String fileName;
    public Long serverid;

    public PhotoDto() {
        // TODO Auto-generated constructor stub
    }

    public PhotoDto(Cursor cursor)
    {
        id=(Long.valueOf(cursor.getString(cursor.getColumnIndex("id"))));
        idillegal=(Long.valueOf(cursor.getString(cursor.getColumnIndex("idillegal"))));
        filepath=((cursor.getString(cursor.getColumnIndex("filepath"))));
        estateid=(Long.valueOf(cursor.getString(cursor.getColumnIndex("estateid"))));
        serverid=(Long.valueOf(cursor.getString(cursor.getColumnIndex("serverid"))));
        pictype=(Integer.valueOf(cursor.getString(cursor.getColumnIndex("pictype"))));

    }
}
