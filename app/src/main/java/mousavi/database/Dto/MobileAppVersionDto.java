package mousavi.database.Dto;

import android.database.Cursor;

public class MobileAppVersionDto implements IDto {
   public String id;
   public String version;
   public String Values;
   public String tozihat;
   public String TableName;
   public String SqlQuery;
   public String NoeUpdateDataBase;
   public String NoeUpdate;
   public String FilePath;
   public String Fields;
   public String DateSabt;
   public String OfficerName;
   public String CityName;
   public String MultiOfficer;
   public String CityId;
   public String ServerDateTime;
   public String Permission;

    public MobileAppVersionDto() {
        // TODO Auto-generated constructor stub
    }

    public MobileAppVersionDto(Cursor cursor)
    {
//        id=(Long.valueOf(cursor.getString(cursor.getColumnIndex("id"))));
//        idillegal=(Long.valueOf(cursor.getString(cursor.getColumnIndex("idillegal"))));
//        filepath=((cursor.getString(cursor.getColumnIndex("filepath"))));
//        estateid=(Long.valueOf(cursor.getString(cursor.getColumnIndex("estateid"))));
//        serverid=(Long.valueOf(cursor.getString(cursor.getColumnIndex("serverid"))));
//        pictype=(Integer.valueOf(cursor.getString(cursor.getColumnIndex("pictype"))));

    }
}
