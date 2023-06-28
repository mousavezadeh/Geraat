package mousavi.database.Select;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class SelectOfficerPermission extends BaseSqlSelect {

    public SelectOfficerPermission(SQLiteDatabase sqLiteDatabase) {
        super(sqLiteDatabase);
    }

    @Override
    public String sql() {
        return "select Title as permission from Meta where node = 'Officer.Permission' limit 1 ";
    }

    @Override
    public String[] parameters() {
        return new String[]{};
    }

    @Override
    public   <T> ArrayList<T> fetch(Class<T> clazz){
        T newInstance = null;
        ArrayList<T> dtolist=new ArrayList<T>();
        Cursor cursor= sqLiteDatabase.rawQuery(sql(), parameters());
        try {
            if (cursor.moveToFirst()) {
                do {
                    newInstance=(T) clazz.getConstructors()[1].newInstance(cursor);
                    dtolist.add(newInstance);
                    break;
                } while (cursor.moveToNext());
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }  catch (InvocationTargetException e) {
            e.printStackTrace();
        } finally {
            if (cursor!=null) cursor.close();
        }
        return dtolist;
    };
}
