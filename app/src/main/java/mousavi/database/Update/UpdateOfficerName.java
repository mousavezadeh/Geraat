package mousavi.database.Update;

import android.database.sqlite.SQLiteDatabase;

import mousavi.database.BaseSql;

public class UpdateOfficerName extends BaseSql {
    String officername;
    String date;
    public UpdateOfficerName(SQLiteDatabase sqLiteDatabase,String officername,String date) {
        super(sqLiteDatabase);
        this.officername=officername;
        this.date=date;
    }

    @Override
    public String sql() {
        return "update meta set title=?,Created=?  where Node='Officer.Name'  ";
    }

    @Override
    public Object[] objectparameters() {
        return new Object[]{officername,date};
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
