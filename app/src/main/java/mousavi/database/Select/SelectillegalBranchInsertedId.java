package mousavi.database.Select;

import android.database.sqlite.SQLiteDatabase;

public class SelectillegalBranchInsertedId extends BaseSqlSelect {

    public SelectillegalBranchInsertedId(SQLiteDatabase sqLiteDatabase) {
        super(sqLiteDatabase);
    }

    @Override
    public String sql() {
        return " SELECT id from illigalBranch order by id desc limit 1 ";
    }

    @Override
    public String[] parameters() {
        return new String[]{};
    }
}
