package mousavi.database.Insert;

import android.database.sqlite.SQLiteDatabase;

import mousavi.database.BaseSql;

public class InsertIllegalBranch extends BaseSql {
    String nearestserialno;
    String registerdate;
    String comment;
    double lat;
    double lon;
    Integer illegaltype;
    Integer estateid;
    public InsertIllegalBranch(SQLiteDatabase sqLiteDatabase,String nearestserialno,String registerdate,String comment,double lat,double lon,Integer illegaltype,Integer estateid) {
        super(sqLiteDatabase);
        this.nearestserialno=nearestserialno;
        this.registerdate=registerdate;
        this.comment=comment;
        this.lat=lat;
        this.lon=lon;
        this.illegaltype=illegaltype;
        this.estateid=estateid;
    }

    @Override
    public String sql() {
        return "insert into illigalBranch (NearestSerialNo," +
                "RegisterDate," +
                "Comment," +
                "lat," +
                "lon,"+
                "illegalmobiletitleid, "+
                "EstateId "+
                " )  "
                + "values (?    ,?    ,?    ,?    ,?   ,?   ,?)";
    }

    @Override
    public Object[] objectparameters() {
        return new Object[]{nearestserialno,registerdate,comment,lat,lon,illegaltype,estateid};
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
