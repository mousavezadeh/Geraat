package mousavi.database.Delete;

import android.database.sqlite.SQLiteDatabase;

import mousavi.database.BaseSql;

public class DeleteSendPhotos extends BaseSql {
    String listofpicid;
    public DeleteSendPhotos(SQLiteDatabase sqLiteDatabase,String listofpicid) {
        super(sqLiteDatabase);
        this.listofpicid=listofpicid;
    }

    @Override
    public String sql() {
        return "delete from pic  where SendToServer=1 and id in ("
                + listofpicid + ")  ";
    }

    @Override
    public Object[] objectparameters() {
        return new Object[]{};
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
