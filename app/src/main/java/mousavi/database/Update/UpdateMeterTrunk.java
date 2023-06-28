package mousavi.database.Update;

import android.database.sqlite.SQLiteDatabase;

import mousavi.database.BaseSql;

public class UpdateMeterTrunk extends BaseSql {
    String metertrunk;
    int estateid;
    int idlist;
    public UpdateMeterTrunk(SQLiteDatabase sqLiteDatabase, String metertrunk, int estateid, int idlist) {
        super(sqLiteDatabase);
        this.metertrunk=metertrunk;
        this.estateid=estateid;
        this.idlist=idlist;
    }

    @Override
    public String sql() {
        return "update inp set mobile=? where estateid=? and fkid_list=?  ";
    }

    @Override
    public Object[] objectparameters() {
        return new Object[]{metertrunk,estateid,idlist};
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
