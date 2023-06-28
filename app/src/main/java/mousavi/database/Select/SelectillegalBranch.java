package mousavi.database.Select;

import android.database.sqlite.SQLiteDatabase;

import mousavi.database.BaseSql;

public class SelectillegalBranch extends BaseSqlSelect {

    public SelectillegalBranch(SQLiteDatabase sqLiteDatabase) {
        super(sqLiteDatabase);
    }

    @Override
    public String sql() {
        return "select "
                + "id,NearestSerialNo,RegisterDate,Comment,lat,lon,illegalmobiletitleid,ifnull(EstateId,0) EstateId "
                + " from  illigalBranch  where  SendToServer=0 and id>0  ORDER by id DESC limit 500 ";
    }

    @Override
    public String[] parameters() {
        return new String[]{};
    }
}
