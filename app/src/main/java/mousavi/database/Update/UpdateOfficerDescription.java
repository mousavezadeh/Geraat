package mousavi.database.Update;

import android.database.sqlite.SQLiteDatabase;

import mousavi.database.BaseSql;

public class UpdateOfficerDescription extends BaseSql {
    String idlist;
    String serialno;
    String description;
    public UpdateOfficerDescription(SQLiteDatabase sqLiteDatabase,String description ,String idlist, String serialno) {
        super(sqLiteDatabase);
        this.idlist=idlist;
        this.serialno=serialno;
        this.description=description;
    }

    @Override
    public String sql() {
        return "update inp set OfficerDescription=? where eshterak=? and fkid_list=? ";
    }

    @Override
    public Object[] objectparameters() {
        return new Object[]{description,serialno,idlist};
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
