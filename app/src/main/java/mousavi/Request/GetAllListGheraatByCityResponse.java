package mousavi.Request;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import mousavi.database.ListGheraat;

public class GetAllListGheraatByCityResponse implements IResponse {

    public String id;
    public String name_list;
    public String day_;
    public String Comment;

    public ArrayList<GetAllListGheraatByCityResponse> ResponseList;

    public GetAllListGheraatByCityResponse(Object res){
        try {
            Gson gson=new Gson();
            Type listType = new TypeToken<List<GetAllListGheraatByCityResponse>>() {}.getType();
            ResponseList = gson.fromJson(res.toString(),listType);
        } catch (Exception e) {

        }
    }
    public GetAllListGheraatByCityResponse(){};

}


