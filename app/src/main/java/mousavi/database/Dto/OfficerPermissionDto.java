package mousavi.database.Dto;

import android.database.Cursor;
import android.util.Base64;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.List;

import mousavi.Request.GetListMobileResponse;

public class OfficerPermissionDto implements IDto {
    public Integer displayolddata;
    public Integer Debtinstallments;
    public Integer pressure;
    public Integer estatecondition;
    public Integer branchcondition;

    private String decodepermission;
    public OfficerPermissionDto() {
        // TODO Auto-generated constructor stub
    }

    public OfficerPermissionDto(Cursor cursor)
    {
        decodepermission= new String(Base64.decode(cursor.getString(cursor.getColumnIndex("permission")),Base64.DEFAULT), StandardCharsets.UTF_8);
        Gson gson=new Gson();
        OfficerPermissionDto temp=null;
        Type listType = new TypeToken<OfficerPermissionDto>() {}.getType();
        temp = gson.fromJson(decodepermission,listType);

        displayolddata=temp.displayolddata;
        Debtinstallments=temp.Debtinstallments;
        pressure=temp.pressure;
        estatecondition=temp.estatecondition;
        branchcondition=temp.branchcondition;

    }
}
