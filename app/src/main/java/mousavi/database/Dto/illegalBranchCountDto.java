package mousavi.database.Dto;

import android.database.Cursor;

public class illegalBranchCountDto implements IDto {
    public Integer count = 0;

    public illegalBranchCountDto() {
    }
    public illegalBranchCountDto(Cursor cursor) {
        count=cursor.getInt(cursor.getColumnIndex("count"));

    }
}
