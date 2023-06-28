package mousavi.database.Select;

import android.database.sqlite.SQLiteDatabase;

public class SelectillegalBranchByDate extends BaseSqlSelect {

    String date;
    public SelectillegalBranchByDate(SQLiteDatabase sqLiteDatabase,String date) {
        super(sqLiteDatabase);
        this.date=date;
    }

    @Override
    public String sql() {
        return " select count(*) as count from illigalBranch where date(RegisterDate)=? ";
    }

    @Override
    public String[] parameters() {
        return new String[]{date};
    }
}
