package mousavi.database.Select;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class SelectVersion extends BaseSqlSelect {

    public SelectVersion(SQLiteDatabase sqLiteDatabase) {
        super(sqLiteDatabase);

    }


    @Override
    public String sql() {
        return " select version from meta where node='Version'  ";
    }

    @Override
    public String[] parameters() {
        return new String[] { };
    }




}
