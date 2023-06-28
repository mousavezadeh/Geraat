package mousavi.Request;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import mousavi.Request.ResponseDto.valueslist;

public class SendReadingListItemResponse implements IResponse {

   public List<mousavi.Request.ResponseDto.valueslist> valueslist;

   public String name;

    public ArrayList<SendReadingListItemResponse> ResponseList;

    public SendReadingListItemResponse(Object res){
        try {
            Gson gson=new Gson();
            Type listType = new TypeToken<List<SendReadingListItemResponse>>() {}.getType();
            ResponseList = gson.fromJson(res.toString(),listType);

        } catch (Exception e) {

        }
    }
    public SendReadingListItemResponse(){};

}


