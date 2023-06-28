package mousavi.database.Update;

import android.database.sqlite.SQLiteDatabase;

import mousavi.database.BaseSql;

public class UpdateOfficerPermission extends BaseSql {
    String officerpermission;
    String date;
    public UpdateOfficerPermission(SQLiteDatabase sqLiteDatabase, String officerpermission, String date) {
        super(sqLiteDatabase);
        this.officerpermission=officerpermission;
        this.date=date;
    }

    @Override
    public String sql() {
        return "update meta set title=?,Created=?  where Node='Officer.Permission'  ";
    }

    @Override
    public Object[] objectparameters() {
        return new Object[]{officerpermission,date};
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
