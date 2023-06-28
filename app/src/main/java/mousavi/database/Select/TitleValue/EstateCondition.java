package mousavi.database.Select.TitleValue;

import android.database.sqlite.SQLiteDatabase;

import mousavi.database.BaseSql;
import mousavi.database.Select.BaseSqlSelect;

public class EstateCondition extends BaseSqlSelect {
    String serialno;
   public   EstateCondition(SQLiteDatabase sqLiteDatabase,String serialno) {
        super(sqLiteDatabase);
        this.serialno=serialno;
    }

     public @Override
    String sql() {
        return "select id,value,CAST(serialno as int)serialno,datesabt from peymayesh where field=7 "
                + " and serialno=? and sendtoserver=0 order by id desc limit 1 ";
    }

    public @Override
    String[] parameters() {
        return new String[]{serialno};
    }
}
