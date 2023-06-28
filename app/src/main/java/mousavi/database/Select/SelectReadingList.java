package mousavi.database.Select;

import android.database.sqlite.SQLiteDatabase;

public class SelectReadingList extends BaseSqlSelect {

    public SelectReadingList(SQLiteDatabase sqLiteDatabase) {
        super(sqLiteDatabase);
    }

    @Override
    public String sql() {
        return "select  Id_List,CurrentRecord,id_list_server  from List_gheraat where Active=1 limit 1  ";
    }

    @Override
    public String[] parameters() {
        return new String[]{};
    }
}
