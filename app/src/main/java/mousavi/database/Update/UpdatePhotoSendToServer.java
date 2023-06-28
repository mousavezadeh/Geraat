package mousavi.database.Update;

import android.database.sqlite.SQLiteDatabase;

import mousavi.database.BaseSql;

public class UpdatePhotoSendToServer extends BaseSql {
   long photoid;
   String datesendtoserver;
    public UpdatePhotoSendToServer(SQLiteDatabase sqLiteDatabase, long photoid,String datesendtoserver) {
        super(sqLiteDatabase);
        this.photoid=photoid;
        this.datesendtoserver=datesendtoserver;
    }

    @Override
    public String sql() {
        return "update pic set SendToServer=1 ,DateSendToServer=? where SendToServer=0  and id=?  ";
    }

    @Override
    public Object[] objectparameters() {
        return new Object[]{datesendtoserver,photoid};
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
