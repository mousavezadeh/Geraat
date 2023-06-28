package mousavi.database.Select;

import android.database.sqlite.SQLiteDatabase;

import mousavi.database.BaseSql;

public class SelectPeymayesh extends BaseSqlSelect {

    public SelectPeymayesh(SQLiteDatabase sqLiteDatabase) {
        super(sqLiteDatabase);
    }

    @Override
    public String sql() {
        return "select "
                + "Field,Value,cast(Serialno as int) serialno,datesabt,id,idlist "
                + "from  Peymayesh  where  SendToServer=0   ORDER by id DESC  limit 1000";
    }

    @Override
    public String[] parameters() {
        return new String[]{};
    }
}
