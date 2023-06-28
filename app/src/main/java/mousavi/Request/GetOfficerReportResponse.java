package mousavi.Request;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GetOfficerReportResponse implements IResponse {

    public String date_;
    public String PeymayeshCount;
    public String IllegalCount;
    public String readCount;

    public ArrayList<GetOfficerReportResponse> ResponseList;

    public GetOfficerReportResponse(Object res){
        try {
            Gson gson=new Gson();
            Type listType = new TypeToken<List<GetOfficerReportResponse>>() {}.getType();
            ResponseList = gson.fromJson(res.toString(),listType);

        } catch (Exception e) {

        }
    }
    public GetOfficerReportResponse(){};

}


