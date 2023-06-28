package mousavi.database.Select;

import android.database.sqlite.SQLiteDatabase;

public class SelectBranchStatusBySerialno extends BaseSqlSelect {
    String serialno;
    public SelectBranchStatusBySerialno(SQLiteDatabase sqLiteDatabase,String serialno) {
           super(sqLiteDatabase);
           this.serialno=serialno;
    }

    @Override
    public String sql() {

        return " select id as title ,value as alias from peymayesh where field=8 " +
               " and serialno=? and sendtoserver=0 order by id desc limit 1";
    }

    @Override
    public String[] parameters() {
        return new String[] {this.serialno };
    }
}
