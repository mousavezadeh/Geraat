package mousavi.database.Insert;

import android.database.sqlite.SQLiteDatabase;

import mousavi.Geraat.Pic.BasePicture;
import mousavi.database.BaseSql;

public class InsertPicture extends BaseSql {
    Integer idillegal;
    String  filepath;
    String  registerdate;
    String  filename;
    Integer pictype;
    Integer estateid;
    BasePicture basepicture;
    public InsertPicture(SQLiteDatabase sqLiteDatabase,Integer idillegal,String filepath, String registerdate,String filename , Integer pictype, Integer estateid) {
        super(sqLiteDatabase);
        this.idillegal=idillegal;
        this.filepath=filepath;
        this.registerdate=registerdate;
        this.filename=filename;
        this.pictype=pictype;
        this.estateid=estateid;
    }

    public InsertPicture(SQLiteDatabase sqLiteDatabase, BasePicture basePicture, String registerdate, Integer estateid) {
        super(sqLiteDatabase);
        this.basepicture=basePicture;
        this.idillegal=0;
        this.filepath=basePicture.filepath();
        this.registerdate=registerdate;
        this.filename=basePicture.filename();
        this.pictype=basePicture.Picturetype();
        this.estateid=estateid;

    }
    @Override
    public String sql() {
        return "insert into pic (idillegal," +
                "filepath," +
                "RegisterDate," +
                "FileName, " +
                "estateid, " +
                "pictype )  "
                + "values (?,?,?,?,?,?)";
    }

    @Override
    public Object[] objectparameters() {
        return new Object[]{idillegal,filepath,registerdate,filename,estateid,pictype};
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
