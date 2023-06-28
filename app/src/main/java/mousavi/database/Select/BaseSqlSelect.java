package mousavi.database.Select;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import mousavi.database.Dto.IDto;

public abstract class BaseSqlSelect {
    public abstract String sql();
    public abstract String[] parameters();

    public SQLiteDatabase sqLiteDatabase;

    public BaseSqlSelect(SQLiteDatabase sqLiteDatabase){
        this.sqLiteDatabase=sqLiteDatabase;

    };

    public   <T> ArrayList<T> fetch(Class<T> clazz){
        T newInstance = null;
        ArrayList<T> dtolist=new ArrayList<T>();
        Cursor cursor= sqLiteDatabase.rawQuery(sql(), parameters());
        try {
            if (cursor.moveToFirst()) {
                do {
                    newInstance=(T) clazz.getConstructors()[1].newInstance(cursor);
                    dtolist.add(newInstance);

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
