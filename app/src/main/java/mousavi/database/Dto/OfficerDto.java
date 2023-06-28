package mousavi.database.Dto;

import android.database.Cursor;

public class OfficerDto implements IDto {
    public Integer officerid;
    public Integer countofserialno;

    public OfficerDto() {
        // TODO Auto-generated constructor stub
    }

    public OfficerDto(Cursor cursor)
    {
        officerid=(Integer.valueOf(cursor.getString(cursor.getColumnIndex("code_mamor"))));
        countofserialno=(Integer.valueOf(cursor.getString(cursor.getColumnIndex("tedad"))));


    }
}
