package mousavi.database.Dto;

import android.database.Cursor;

public class VersionDto implements IDto {
    public float version;


    public VersionDto() {
        // TODO Auto-generated constructor stub
    }

    public VersionDto(Cursor cursor)
    {
        version=((cursor.getFloat(cursor.getColumnIndex("version"))));
    }
}
