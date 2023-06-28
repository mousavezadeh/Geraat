package mousavi.database.Update;

import android.database.sqlite.SQLiteDatabase;

import mousavi.database.BaseSql;

public class UpdateIllegalBranchServerId extends BaseSql {
   String insertedid;
   String localid;
    public UpdateIllegalBranchServerId(SQLiteDatabase sqLiteDatabase, String insertedid, String localid) {
        super(sqLiteDatabase);
        this.insertedid=insertedid;
        this.localid=localid;
    }

    @Override
    public String sql() {
        return "UPDATE illigalBranch set serverid=? where id=? and SendToServer=1  ";

    }

    @Override
    public Object[] objectparameters() {
        return new Object[]{insertedid,localid};
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
