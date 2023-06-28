package mousavi.database.Update;

import android.database.sqlite.SQLiteDatabase;

import mousavi.database.BaseSql;

public class ActiveList extends BaseSql {
   int idlist;
    public ActiveList(SQLiteDatabase sqLiteDatabase, int idlist) {
        super(sqLiteDatabase);
        this.idlist=idlist;
    }

    @Override
    public String sql() {
        return "update List_Gheraat set Active=1 where cast(id_list_server as int)=?  ";
    }

    @Override
    public Object[] objectparameters() {
        return new Object[]{idlist};
    }

    @Override
    public String[] stringparameters() {
        return new String[]{};
    }

    @Override
    public boolean execute() {
        try{
            sqLiteDatabase.execSQL(sql(), objectparameters());
        }catch (Exception error){
            return false;
        }
        return true;
    }
}
