package mousavi.database.Select;

import android.database.Cursor;

import com.google.gson.internal.reflect.ReflectionHelper;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import mousavi.database.Extentions;
import mousavi.database.Select.TitleValue.TitleValueDto;

public class FetchSelectOperation {
    BaseSqlSelect baseSqlSelect;
    public FetchSelectOperation(BaseSqlSelect baseSqlSelect){
        this.baseSqlSelect=baseSqlSelect;
    }
    public <T> ArrayList<T> fetch(Class<T> clazz){
        T newInstance = null;
        ArrayList<T> dtolist=new ArrayList<T>();
        Cursor cursor= baseSqlSelect.sqLiteDatabase.rawQuery(baseSqlSelect.sql(), baseSqlSelect.parameters());
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
    }
}
