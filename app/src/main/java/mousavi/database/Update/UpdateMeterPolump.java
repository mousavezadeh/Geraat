package mousavi.database.Update;

import android.database.sqlite.SQLiteDatabase;

import mousavi.database.BaseSql;

public class UpdateMeterPolump extends BaseSql {
    String meterpolump;
    int estateid;
    int idlist;
    public UpdateMeterPolump(SQLiteDatabase sqLiteDatabase, String meterpolump, int estateid, int idlist) {
        super(sqLiteDatabase);
        this.meterpolump=meterpolump;
        this.estateid=estateid;
        this.idlist=idlist;
    }

    @Override
    public String sql() {
        return "update inp set mobile=? where estateid=? and fkid_list=?  ";
    }

    @Override
    public Object[] objectparameters() {
        return new Object[]{meterpolump,estateid,idlist};
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
