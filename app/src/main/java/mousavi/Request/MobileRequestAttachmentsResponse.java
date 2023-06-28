package mousavi.Request;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MobileRequestAttachmentsResponse implements IResponse {

    public String illegalbranchid;
    public String Attachmentid;
    public String localid;


    public ArrayList<MobileRequestAttachmentsResponse> ResponseList;

    public MobileRequestAttachmentsResponse(Object res){
        try {
            Gson gson=new Gson();
            Type listType = new TypeToken<List<MobileRequestAttachmentsResponse>>() {}.getType();
            ResponseList = gson.fromJson(res.toString(),listType);

        } catch (Exception e) {

        }
    }
    public MobileRequestAttachmentsResponse(){};

}


