package mousavi.database.Dto;

import android.database.Cursor;

public class BranchStatusDto implements IDto {
    public Integer id;
    public String title;

    public BranchStatusDto() {
        // TODO Auto-generated constructor stub
    }

    public BranchStatusDto(Cursor cursor)
    {
        id=(Integer.valueOf(cursor.getString(cursor.getColumnIndex("alias"))));
        title=((cursor.getString(cursor.getColumnIndex("title"))));
    }
}
