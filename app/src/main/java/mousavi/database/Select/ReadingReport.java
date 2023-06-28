package mousavi.database.Select;

import android.database.sqlite.SQLiteDatabase;

import mousavi.database.databasetest;

public class ReadingReport extends BaseSqlSelect {
    String officerid;
    String idlist;
    public ReadingReport(SQLiteDatabase sqLiteDatabase) {
        super(sqLiteDatabase);

    }
    public ReadingReport(SQLiteDatabase sqLiteDatabase,String idlist,String officerid) {
        super(sqLiteDatabase);
        this.officerid=officerid;
        this.idlist=idlist;
    }

    @Override
    public String sql() {
        return  "select itable.code_mane_feli,mtable.title_gherat,count(*) as tedad from inp as itable,code_mane as mtable"
                + " where cast(itable.code_mane_feli as int)=cast(mtable.code_gherat as int)"
                + " and itable.fkid_list=? and cast(itable.code_mamor as int)=?"
                + " group by itable.code_mane_feli";

    }

    @Override
    public String[] parameters() {
        return new String[] { this.idlist,this.officerid };
    }
}
