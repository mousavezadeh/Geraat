package mousavi.database.Update;

import android.database.sqlite.SQLiteDatabase;
import android.icu.util.EthiopicCalendar;

import mousavi.database.BaseSql;

public class UpdateVersion extends BaseSql {
    String version;
    String date;
    public UpdateVersion(SQLiteDatabase sqLiteDatabase,String version,String date) {
        super(sqLiteDatabase);
        this.version=version;
        this.date=date;
    }

    @Override
    public String sql() {
        return "update meta set version=?,Created=?  where Node='Version'  ";
    }

    @Override
    public Object[] objectparameters() {
        return new Object[]{version,date};
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
