package mousavi.database.Select;

import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class SelectOutput extends BaseSqlSelect {

    public SelectOutput(SQLiteDatabase sqLiteDatabase) {
        super(sqLiteDatabase);
    }

    @Override
    public <T> ArrayList<T> fetch(Class<T> clazz) {
        return null;
    }

    @Override
    public String sql() {
        return " select rozkar,eshterak,karkard_feli,code_mane_feli,lat,long,timesabte,List_Gheraat.id_list_server,OfficerDescription  " +
                "from inp " +
                " inner join List_Gheraat " +
                "on inp.FKId_List=List_Gheraat.Id_List where (lat>0 and long>0) and (code_mane_feli <> 0) and (SendToServer=0)    "+
                " and (cast(inp.doreh as int) in ((select cast(doreh as INT) from List_Gheraat order by id_list_server DESC LIMIT 1),"+
                " (select cast(doreh as INT)-1 from List_Gheraat order by id_list_server DESC LIMIT 1)))  ORDER by cast(inp.doreh as INT) DESC LIMIT 1500 ";
    }

    @Override
    public String[] parameters() {
        return new String[]{};
    }
}
