package mousavi.database.Select.TitleValue;

import android.database.sqlite.SQLiteDatabase;

import mousavi.database.Select.BaseSqlSelect;

public class PhotosSent extends BaseSqlSelect {

   public PhotosSent(SQLiteDatabase sqLiteDatabase) {
        super(sqLiteDatabase);
    }

    @Override
    public String sql() {
        return "SELECT  filepath,id  from  pic where strftime('%Y-%m-%d', DateSendToServer) < date('now','-1 day')  ";

    }

    @Override
    public String[] parameters() {
        return new String[]{};
    }
}
