package mousavi.database.Dto;

import android.database.Cursor;

public class EstateConditionDto implements IDto {
    public Integer id;
    public String title;

    public EstateConditionDto() {
        // TODO Auto-generated constructor stub
    }

    public EstateConditionDto(Cursor cursor)
    {
        id=(Integer.valueOf(cursor.getString(cursor.getColumnIndex("alias"))));
        title=((cursor.getString(cursor.getColumnIndex("title"))));
    }
}
