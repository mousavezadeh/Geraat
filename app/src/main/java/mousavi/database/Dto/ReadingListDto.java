package mousavi.database.Dto;

import android.database.Cursor;

public class ReadingListDto implements IDto {
    public String id_list_server;
    public Integer Id_List;
    public String   Doreh;
    public String   Roozkar;
    public String   CodeMamoor;
    public String   NameList;
    public String   State;
    public String   Active;
    public String   CurrentRecord;
    public ReadingListDto() {
        // TODO Auto-generated constructor stub
    }

    public ReadingListDto(Cursor cursor)
    {
        id_list_server=((cursor.getString(cursor.getColumnIndex("id_list_server"))));
        Id_List=(Integer.valueOf(cursor.getString(cursor.getColumnIndex("Id_List"))));
        Doreh=((cursor.getString(cursor.getColumnIndex("Doreh"))));
        Roozkar=((cursor.getString(cursor.getColumnIndex("Roozkar"))));
        CodeMamoor=((cursor.getString(cursor.getColumnIndex("CodeMamoor"))));
        NameList=((cursor.getString(cursor.getColumnIndex("NameList"))));
        State=((cursor.getString(cursor.getColumnIndex("State"))));
        Active=((cursor.getString(cursor.getColumnIndex("Active"))));
        CurrentRecord=((cursor.getString(cursor.getColumnIndex("CurrentRecord"))));

    }
}
