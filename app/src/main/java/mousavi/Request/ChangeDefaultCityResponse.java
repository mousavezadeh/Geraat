package mousavi.Request;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ChangeDefaultCityResponse implements IResponse {

    public String status;

    public ArrayList<ChangeDefaultCityResponse> ResponseList=null;

    public ChangeDefaultCityResponse(Object res){
        try {
            if (res.toString().compareTo("Ok")==0)
                ResponseList.add(new ChangeDefaultCityResponse("Ok"));

            ResponseList=null;
        } catch (Exception e) {

        }
    }
    public ChangeDefaultCityResponse(){};
    public ChangeDefaultCityResponse(String status){
        this.status=status;
    };

}


