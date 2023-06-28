package mousavi.database.Update;

import android.database.sqlite.SQLiteDatabase;

import mousavi.database.BaseSql;

public class UpdateOfficerCityid extends BaseSql {
    String officercityid;
    String date;
    public UpdateOfficerCityid(SQLiteDatabase sqLiteDatabase, String officercityid, String date) {
        super(sqLiteDatabase);
        this.officercityid=officercityid;
        this.date=date;
    }

    @Override
    public String sql() {
        return "update meta set title=?,Created=?  where Node='Officer.Cityid'  ";
    }

    @Override
    public Object[] objectparameters() {
        return new Object[]{officercityid,date};
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
