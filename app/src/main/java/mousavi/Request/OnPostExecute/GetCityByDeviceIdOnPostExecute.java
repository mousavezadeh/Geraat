package mousavi.Request.OnPostExecute;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import Adapters.Adapter_Citys;
import Adapters.Adapter_Porsesh;
import mousavi.Geraat.GeneralExtentions;
import mousavi.Geraat.R;
import mousavi.Request.Api;
import mousavi.Request.ChangeDefaultCityRequestDto;
import mousavi.Request.GetAllListGheraatByCityResponse;
import mousavi.Request.GetCityByDeviceIdResponse;
import mousavi.Request.IResponse;

public class GetCityByDeviceIdOnPostExecute {
    Dialog dialog;
    Context context;
    IResponse iResponse;
    public GetCityByDeviceIdOnPostExecute(Context context, IResponse iResponse){
      this.context=context;
      this.iResponse=iResponse;
    }
    public void Execute(){
        GetCityByDeviceIdResponse getCityByDeviceIdResponse=(GetCityByDeviceIdResponse)iResponse;
        dialog = new Dialog(context, R.style.AppTheme);
        dialog.setContentView(R.layout.code_gheraat);
        dialog.setTitle("");
        ListView list_code_mane = (ListView) dialog.findViewById(R.id.listView_code_gherat);
        list_code_mane.setAdapter(new Adapter_Citys(context, iResponse));

        dialog.show();
        list_code_mane
                .setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> arg0,
                                            View arg1, int arg2, long arg3) {

                        //Api api=new Api();
                        //api.ChangeDefaultCity(new ChangeDefaultCityRequestDto(GeneralExtentions.Extention.GetImei(context),"12"));
                        //ChangeDefaultCityOnPostExecute changeDefaultCityOnPostExecute=new ChangeDefaultCityOnPostExecute(context,)

                        //IDFile = getAllListGheraatByCityResponse.ResponseList.get(arg2).id;
                       // NameList = ArrayListListGheraat.get(arg2).Get_NameList();
                        //new display_main_menu.DownloadFileAsync_test().execute("");
                    }

                });
    }

}
