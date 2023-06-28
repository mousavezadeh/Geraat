package mousavi.database;

import android.database.sqlite.SQLiteDatabase;

public abstract class BaseSql {
    public abstract String sql();
    public abstract Object[] objectparameters();
    public abstract String[] stringparameters();
    public SQLiteDatabase sqLiteDatabase;
    public BaseSql(SQLiteDatabase sqLiteDatabase){
        this.sqLiteDatabase=sqLiteDatabase;
    };
    public abstract boolean execute();
}
