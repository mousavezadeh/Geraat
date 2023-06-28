package mousavi.database.Update;

import android.database.sqlite.SQLiteDatabase;

import mousavi.database.BaseSql;

public class UpdateOfficerMultiCity extends BaseSql {
    String officermulticity;
    String date;
    public UpdateOfficerMultiCity(SQLiteDatabase sqLiteDatabase, String officermulticity, String date) {
        super(sqLiteDatabase);
        this.officermulticity=officermulticity;
        this.date=date;
    }

    @Override
    public String sql() {
        return "update meta set title=?,Created=?  where Node='Officer.MultiCity'  ";
    }

    @Override
    public Object[] objectparameters() {
        return new Object[]{officermulticity,date};
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
