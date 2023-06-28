package mousavi.database.Dto;

import android.database.Cursor;

public class PeymayeshDto implements IDto {
    public Integer id;
    public Integer field;
    public String value;
    public String serialno;
    public Integer idlist;
    public String dateSabt;
    public Integer sendToServer=0;
    public String dateSendToServer;
    public String newValue;
    public int _EstateId;
    public PeymayeshDto() {
        // TODO Auto-generated constructor stub
    }

    public PeymayeshDto(Cursor cursor)
    {
        field=(Integer.valueOf(cursor.getString(cursor.getColumnIndex("Field"))));
        value=((cursor.getString(cursor.getColumnIndex("Value"))));
        serialno=((cursor.getString(cursor.getColumnIndex("serialno"))));
        dateSabt=((cursor.getString(cursor.getColumnIndex("datesabt"))));
        id=(Integer.valueOf(cursor.getString(cursor.getColumnIndex("id"))));
        idlist=(Integer.valueOf(cursor.getString(cursor.getColumnIndex("idlist"))));
    }
}
