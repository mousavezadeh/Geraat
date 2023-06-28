package mousavi.database.Select;

import android.database.sqlite.SQLiteDatabase;

public class SelectEstateConditionBySerilano extends BaseSqlSelect {
    String serilno;
    public SelectEstateConditionBySerilano(SQLiteDatabase sqLiteDatabase,String serilno) {
        super(sqLiteDatabase);
        this.serilno=serilno;
    }


    @Override
    public String sql() {
        return "  select id as title ,value as alias from peymayesh where field=7 " +
                " and serialno=? and sendtoserver=0 order by id desc limit 1 ";
    }

    @Override
    public String[] parameters() {
        return new String[] {serilno };
    }
}
