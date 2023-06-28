package mousavi.Request;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GetVersionResponse implements IResponse {

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
    public String SystemDate;
    public String permission;

    public ArrayList<GetVersionResponse> ResponseList;

    public GetVersionResponse(Object res){
        try {
            Gson gson=new Gson();
            Type listType = new TypeToken<List<GetVersionResponse>>() {}.getType();
            ResponseList = gson.fromJson(res.toString(),listType);

        } catch (Exception e) {

        }
    }
    public GetVersionResponse(){};

}


