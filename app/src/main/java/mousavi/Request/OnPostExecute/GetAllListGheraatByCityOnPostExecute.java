package mousavi.Request.OnPostExecute;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.ksoap2.serialization.SoapObject;

import Adapters.Adapter_Porsesh;
import mousavi.Geraat.R;
import mousavi.Geraat.display_main_menu;
import mousavi.Request.Api;
import mousavi.Request.ExecuteApi;
import mousavi.Request.GetAllListGheraatByCityRequestDto;
import mousavi.Request.GetAllListGheraatByCityResponse;
import mousavi.Request.GetListMobileRequestDto;
import mousavi.Request.GetListMobileResponse;
import mousavi.Request.IResponse;

public class GetAllListGheraatByCityOnPostExecute {
    Dialog dialog;
    Context context;
    IResponse iResponse;
    public GetAllListGheraatByCityOnPostExecute(Context context, IResponse iResponse){
      this.context=context;
      this.iResponse=iResponse;
    }
    public void Execute(){
        final GetAllListGheraatByCityResponse getAllListGheraatByCityResponse=(GetAllListGheraatByCityResponse)iResponse;
        dialog = new Dialog(context, R.style.AppTheme);
        dialog.setContentView(R.layout.code_gheraat);
        dialog.setTitle("لیست های قابل انتخاب");
        ListView list_code_mane = (ListView) dialog.findViewById(R.id.listView_code_gherat);
        list_code_mane.setAdapter(new Adapter_Porsesh(context, iResponse));

        dialog.show();
        list_code_mane
                .setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> arg0,
                                            View arg1, int arg2, long arg3) {

                        String IDFile = getAllListGheraatByCityResponse.ResponseList.get(arg2).id;
                        Api api = new Api();
                        SoapObject res=api.GetListMobile(new GetListMobileRequestDto(IDFile));
                        new ExecuteApi(GetListMobileResponse.class.getName(),context,getAllListGheraatByCityResponse).execute(res);

                    }

                });
    }

}
