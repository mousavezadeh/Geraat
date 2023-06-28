package mousavi.database.Dto;

import android.database.Cursor;

public class OutputDto implements IDto {
      public String code_control;
      public String code_mane;
      public String eshterak;
      public String id_list_server;
      public String latitude;
      public String longitude;
      public String meghdare_feli;
      public String roz_kar;
      public String tarikh_feli;
      public String tarikh_ghabli;
      public String timesabte;
      public String officerDescription;
    public OutputDto() {
        // TODO Auto-generated constructor stub
    }

    public OutputDto(Cursor cursor)
    {
        roz_kar=(cursor.getString(cursor.getColumnIndex("rozkar")));
        eshterak=(cursor.getString(cursor.getColumnIndex("eshterak")));
        tarikh_feli="000000";
        latitude=(cursor.getString(cursor.getColumnIndex("lat")));
        longitude=(cursor.getString(cursor.getColumnIndex("long")));
        timesabte=(cursor.getString(cursor.getColumnIndex("timesabte")));
        id_list_server=(cursor.getString(cursor.getColumnIndex("id_list_server")));
        officerDescription=(cursor.getString(cursor.getColumnIndex("OfficerDescription")));
        meghdare_feli=cursor.getString(cursor.getColumnIndex("karkard_feli"));
        code_mane=cursor.getString(cursor.getColumnIndex("code_mane_feli"));
    }
}
