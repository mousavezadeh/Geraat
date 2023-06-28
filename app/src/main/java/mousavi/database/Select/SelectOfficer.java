package mousavi.database.Select;

import android.database.sqlite.SQLiteDatabase;

public class SelectOfficer extends BaseSqlSelect {
    String idlist;
    public SelectOfficer(SQLiteDatabase sqLiteDatabase,String idlist) {
        super(sqLiteDatabase);
        this.idlist=idlist;
    }

    @Override
    public String sql() {
        return "select cast(itable.code_mamor as INT)code_mamor,count(*) as tedad  from inp as itable "
                + " where  itable.fkid_list=? "
                + " group by itable.code_mamor ";
    }

    @Override
    public String[] parameters() {
        return new String[]{};
    }
}
