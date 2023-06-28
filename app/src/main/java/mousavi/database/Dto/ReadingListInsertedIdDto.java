package mousavi.database.Dto;

import android.database.Cursor;

public class ReadingListInsertedIdDto implements IDto {

    public Integer idlist = 0;
    public Integer idlistserver = 0;

    public ReadingListInsertedIdDto() {
    }
    public ReadingListInsertedIdDto(Cursor cursor) {
        idlist=cursor.getInt(cursor.getColumnIndex("Id_List"));
        idlistserver=cursor.getInt(cursor.getColumnIndex("id_list_server"));
    }
}
