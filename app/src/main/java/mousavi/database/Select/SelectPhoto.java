package mousavi.database.Select;

import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class SelectPhoto extends BaseSqlSelect {

    public SelectPhoto(SQLiteDatabase sqLiteDatabase) {
        super(sqLiteDatabase);
    }

    @Override
    public <T> ArrayList<T> fetch(Class<T> clazz) {
        return null;
    }

    @Override
    public String sql() {
        return "select "
                + "Pic.id,Pic.idillegal,Pic.filepath,ifnull(Pic.estateid,0) estateid,Pic.pictype ,illigalBranch.serverid "
                + "from  Pic  INNER JOIN  illigalBranch on illigalBranch.id=pic.idillegal where  Pic.SendToServer=0   and  illigalBranch.serverid>0 and illigalBranch.SendToServer=1 ORDER by pic.id DESC limit 10  ";
    }

    @Override
    public String[] parameters() {
        return new String[]{};
    }
}
