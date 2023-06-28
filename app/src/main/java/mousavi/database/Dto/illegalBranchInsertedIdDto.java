package mousavi.database.Dto;

import android.database.Cursor;

public class illegalBranchInsertedIdDto implements IDto {

    public Integer Insertedid = 0;


    public illegalBranchInsertedIdDto() {
    }
    public illegalBranchInsertedIdDto(Cursor cursor) {
        Insertedid=cursor.getInt(cursor.getColumnIndex("id"));
    }
}
