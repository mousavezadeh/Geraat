package mousavi.Request;

import android.app.Dialog;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import Adapters.Adapter_Citys;
import mousavi.Geraat.R;
import mousavi.Geraat.display_main_menu;


public class GetCityByDeviceIdResponse implements IResponse {

    public String deviceid;
    public String cityid;
    public String cityname;
    public String defaultcityid;

    public ArrayList<GetCityByDeviceIdResponse> ResponseList=null;

    public GetCityByDeviceIdResponse(Object res){
        try {
            String[] listofcity = res.toString().split("#");
            if (listofcity.length==0)
                ResponseList=null;

                for (String item : listofcity)
                {
                    String[] Fields = item.split(",");
                    ResponseList.add(new GetCityByDeviceIdResponse(Fields[0].toString(),Fields[1].toString(),Fields[2].toString(),Fields[3].toString()));
                }

        } catch (Exception e) {

        }
    }
    public GetCityByDeviceIdResponse(){};
    public GetCityByDeviceIdResponse(String deviceid,String cityid,String cityname,String defaultcityid){
        this.deviceid=deviceid;
        this.cityid=cityid;
        this.cityname=cityname;
        this.defaultcityid=defaultcityid;
    };

}


