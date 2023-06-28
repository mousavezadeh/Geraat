package mousavi.database.Insert;

import android.database.sqlite.SQLiteDatabase;

import mousavi.Request.GetAllListGheraatByCityResponse;
import mousavi.database.BaseSql;

public class InsertReadingList extends BaseSql {
    String idlist;
    String workday;
    String namelist;

    GetAllListGheraatByCityResponse getAllListGheraatByCityResponse;
    public InsertReadingList(SQLiteDatabase sqLiteDatabase, String idlist, String workday,  String namelist) {
        super(sqLiteDatabase);
        this.idlist=idlist;
        this.workday=workday;
        this.namelist=namelist;

    }

    public InsertReadingList(SQLiteDatabase sqLiteDatabase,GetAllListGheraatByCityResponse readinglist ) {
        super(sqLiteDatabase);
        this.idlist=readinglist.id;
        this.workday=readinglist.day_;
        this.namelist=readinglist.name_list;
    }
    @Override
    public String sql() {
        return "insert into List_Gheraat (id_list_server,Doreh,Roozkar,CodeMamoor,NameList,State,Active,CurrentRecord) " +
                " values (?,?,?,?,?,?,1,0)";
    }

    @Override
    public Object[] objectparameters() {
        return new Object[]{idlist,0,workday,0,namelist,1,1,0};
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
