package mousavi.database.Update;

import android.database.sqlite.SQLiteDatabase;

import mousavi.database.BaseSql;

public class UpdatePeymayesh extends BaseSql {
    String value;
    String datesabt;
    Integer id;
    public UpdatePeymayesh(SQLiteDatabase sqLiteDatabase, String value, String datesabt, Integer id) {
        super(sqLiteDatabase);
        this.value=value;
        this.datesabt=datesabt;
        this.id=id;
    }

    @Override
    public String sql() {
        return "update  Peymayesh set Value=?,DateSabt=?,SendToServer=0 where id=? ";
    }

    @Override
    public Object[] objectparameters() {
        return new Object[]{value,datesabt,id};
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
