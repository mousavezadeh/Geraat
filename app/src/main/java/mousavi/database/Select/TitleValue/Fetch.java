package mousavi.database.Select.TitleValue;

import android.database.Cursor;

import java.util.ArrayList;

import mousavi.database.Select.BaseSqlSelect;

public class Fetch {
    BaseSqlSelect baseSqlSelect;
    public Fetch(BaseSqlSelect baseSqlSelect){
        this.baseSqlSelect=baseSqlSelect;
    }
    public ArrayList<TitleValueDto> fetch(){
        ArrayList<TitleValueDto> titleValueDtos=new ArrayList<TitleValueDto>();
        Cursor cursor= baseSqlSelect.sqLiteDatabase.rawQuery(baseSqlSelect.sql(), baseSqlSelect.parameters());
        try {
            if (cursor.moveToFirst()) {
                do {
                    titleValueDtos.add(new TitleValueDto(cursor.getString(0),
                                                         cursor.getString(1)));

                } while (cursor.moveToNext());
            }
        }finally {
            if (cursor!=null) cursor.close();
        }
        return titleValueDtos;
    }


}
