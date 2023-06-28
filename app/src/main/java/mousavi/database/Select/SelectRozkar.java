package mousavi.database.Select;

import android.database.sqlite.SQLiteDatabase;

import mousavi.database.databasetest;

public class SelectRozkar extends BaseSqlSelect {
    String idlist;
    String officerid;
    public SelectRozkar(SQLiteDatabase sqLiteDatabase,String idlist,String officerid) {
        super(sqLiteDatabase);
        this.idlist=idlist;
        this.officerid=officerid;
    }

    @Override
    public String sql() {
        return " SELECT cast(rozkar as int) rozkar,FKId_List from inp WHERE FKId_List = ?  and  CAST(code_mamor as int)=? group by cast(rozkar as int) ";
    }

    @Override
    public String[] parameters() {
        return new String[] { this.idlist,this.officerid};
    }
}
