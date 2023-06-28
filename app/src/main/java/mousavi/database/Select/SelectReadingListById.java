package mousavi.database.Select;

import android.database.sqlite.SQLiteDatabase;

public class SelectReadingListById extends BaseSqlSelect {
    Integer id;
    public SelectReadingListById(SQLiteDatabase sqLiteDatabase,Integer id) {
        super(sqLiteDatabase);
        this.id=id;
    }

    @Override
    public String sql() {
        return "select  Id_List,Doreh,Roozkar,CodeMamoor,NameList,State,Active,CurrentRecord,id_list_server  from List_gheraat where cast(id_list_server as int)=?   limit 1  ";
    }

    @Override
    public String[] parameters() {
        return new String[]{id.toString()};
    }
}
