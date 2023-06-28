package mousavi.database.Update;

import android.database.sqlite.SQLiteDatabase;

import mousavi.database.BaseSql;

public class UpdateBranchStatus extends BaseSql {
    String idlist;
    String serialno;
    int vaziat;
    public UpdateBranchStatus(SQLiteDatabase sqLiteDatabase, String idlist, String serialno,int vaziat) {
        super(sqLiteDatabase);
        this.idlist=idlist;
        this.serialno=serialno;
        this.vaziat=vaziat;
    }

    @Override
    public String sql() {
        return "update inp set BranchStatusId=?  where eshterak=? and fkid_list=? ";
    }

    @Override
    public Object[] objectparameters() {
        return new Object[]{vaziat,serialno,idlist};
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
