package mousavi.database.Update;

import android.database.sqlite.SQLiteDatabase;

import mousavi.database.BaseSql;

public class UpdatePeymayeshSendToServer extends BaseSql {
   String serials;
   String datesendtoserver;
    public UpdatePeymayeshSendToServer(SQLiteDatabase sqLiteDatabase, String serials, String datesendtoserver) {
        super(sqLiteDatabase);
        this.serials=serials;
        this.datesendtoserver=datesendtoserver;
    }

    @Override
    public String sql() {
        return "update peymayesh set SendToServer=1 ,DateSendToServer=? where SendToServer=0 and id in ("
                + serials + ")  ";
    }

    @Override
    public Object[] objectparameters() {
        return new Object[]{datesendtoserver};
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
