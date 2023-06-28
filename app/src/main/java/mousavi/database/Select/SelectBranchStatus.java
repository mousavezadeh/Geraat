package mousavi.database.Select;

import android.database.sqlite.SQLiteDatabase;

public class SelectBranchStatus extends BaseSqlSelect {

    public SelectBranchStatus(SQLiteDatabase sqLiteDatabase) {
        super(sqLiteDatabase);
    }

    @Override
    public String sql() {
        return " select title,cast(alias as INT)alias from meta where node='EstateConditionId' order by id_meta   ";
    }

    @Override
    public String[] parameters() {
        return new String[] { };
    }
}
