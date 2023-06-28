package mousavi.database.Update;

import android.database.sqlite.SQLiteDatabase;

import mousavi.database.BaseSql;

public class UpdateEstateCondition extends BaseSql {
    String idlist;
    String serialno;
    int estateconditionid;
    public UpdateEstateCondition(SQLiteDatabase sqLiteDatabase, String idlist, String serialno, int estateconditionid) {
        super(sqLiteDatabase);
        this.idlist=idlist;
        this.serialno=serialno;
        this.estateconditionid=estateconditionid;
    }

    @Override
    public String sql() {
        return "update inp set EstateConditionId=?  where eshterak=? and fkid_list=? ";
    }

    @Override
    public Object[] objectparameters() {
        return new Object[]{estateconditionid,serialno,idlist};
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
