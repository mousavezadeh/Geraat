package mousavi.database.Select;

import android.database.sqlite.SQLiteDatabase;

public class SelectEstateCondition extends BaseSqlSelect {

    public SelectEstateCondition(SQLiteDatabase sqLiteDatabase) {
        super(sqLiteDatabase);
    }


    @Override
    public String sql() {
        return " select title,alias from meta where node='EstateConditionId' order by id_meta ";
    }

    @Override
    public String[] parameters() {
        return new String[] { };
    }
}
