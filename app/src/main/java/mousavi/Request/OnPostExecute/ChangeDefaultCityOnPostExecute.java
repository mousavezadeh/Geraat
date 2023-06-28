package mousavi.Request.OnPostExecute;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import Adapters.Adapter_Citys;
import mousavi.Geraat.GeneralExtentions;
import mousavi.Geraat.R;
import mousavi.Intent.IntenAction;
import mousavi.Intent.IntenEmpty;
import mousavi.Request.Api;
import mousavi.Request.ChangeDefaultCityRequestDto;
import mousavi.Request.ChangeDefaultCityResponse;
import mousavi.Request.GetCityByDeviceIdResponse;
import mousavi.Request.IResponse;

public class ChangeDefaultCityOnPostExecute {
    Dialog dialog;
    Context context;
    IResponse iResponse;
    public ChangeDefaultCityOnPostExecute(Context context, IResponse iResponse){
      this.context=context;
      this.iResponse=iResponse;
    }
    public void Execute(){
        ChangeDefaultCityResponse changeDefaultCityResponse=(ChangeDefaultCityResponse)iResponse;
        //da.Update_OfficerCityName(Name_ChangeCity);
        if (changeDefaultCityResponse.ResponseList==null) {
            Toast.makeText(context, "خطا در انجام عملیات لطفا دوباره تلاش کنید", Toast.LENGTH_SHORT).show();
            return;
        }

        IntenEmpty intenEmpty=new IntenEmpty();
        context.sendBroadcast(intenEmpty.Getintent());
        Toast.makeText(context, "تغییر شهر با موفقیت انجام شد", Toast.LENGTH_SHORT).show();
    }

}
