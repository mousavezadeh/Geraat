package mousavi.database.Update;

import android.database.sqlite.SQLiteDatabase;

import mousavi.database.BaseSql;

public class UpdateOfficerCityName extends BaseSql {
    String officercityname;
    String date;
    public UpdateOfficerCityName(SQLiteDatabase sqLiteDatabase, String officercityname, String date) {
        super(sqLiteDatabase);
        this.officercityname=officercityname;
        this.date=date;
    }

    @Override
    public String sql() {
        return "update meta set title=?,Created=?  where Node='Officer.CityName'  ";
    }

    @Override
    public Object[] objectparameters() {
        return new Object[]{officercityname,date};
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
