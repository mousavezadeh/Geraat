package mousavi.database.Update;

import android.database.sqlite.SQLiteDatabase;

import mousavi.database.BaseSql;

public class UpdateAddress extends BaseSql {
    String address;
    int estateid;
    int idlist;
    public UpdateAddress(SQLiteDatabase sqLiteDatabase, String address, int estateid, int idlist) {
        super(sqLiteDatabase);
        this.address=address;
        this.estateid=estateid;
        this.idlist=idlist;
    }

    @Override
    public String sql() {
        return "update inp set address=? where estateid=? and fkid_list=?  ";
    }

    @Override
    public Object[] objectparameters() {
        return new Object[]{address,estateid,idlist};
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
