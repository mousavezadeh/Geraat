package mousavi.database.Dto;

import android.database.Cursor;

public class RozkarDto implements IDto {
    public Integer rozkar;
    public Integer idlist;

    public RozkarDto() {
        // TODO Auto-generated constructor stub
    }

    public RozkarDto(Cursor cursor)
    {
        rozkar=(Integer.valueOf(cursor.getString(cursor.getColumnIndex("rozkar"))));
        idlist=(Integer.valueOf(cursor.getString(cursor.getColumnIndex("FKId_List"))));


    }
}
