package mousavi.database;

import android.database.Cursor;

public class ExecuteSql {
    BaseSql baseSql;
    public ExecuteSql(BaseSql baseSql){
        this.baseSql=baseSql;
    }
    public boolean Exec(){
       try {
           baseSql.sqLiteDatabase.execSQL(baseSql.sql(), baseSql.objectparameters());
       }catch (Exception error){
           return false;
       }
       return true;
    }
}
