package mousavi.Request.OnPostExecute;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.ksoap2.serialization.SoapObject;

import java.util.List;

import Adapters.Adapter_Porsesh;
import mousavi.Geraat.GeneralExtentions;
import mousavi.Geraat.R;
import mousavi.Request.Api;
import mousavi.Request.ExecuteApi;
import mousavi.Request.GetAllListGheraatByCityResponse;
import mousavi.Request.GetListMobileRequestDto;
import mousavi.Request.GetListMobileResponse;
import mousavi.Request.IResponse;
import mousavi.Request.MobileRequestAttachmentsRequestDto;
import mousavi.Request.MobileRequestAttachmentsResponse;
import mousavi.database.Dto.PhotoDto;
import mousavi.database.Select.FetchSelectOperation;
import mousavi.database.Select.SelectPhoto;
import mousavi.database.databasetest;

public class SendReadingListItemOnPostExecute {

    Context context;
    IResponse iResponse;
    public SendReadingListItemOnPostExecute(Context context, IResponse iResponse){
      this.context=context;
      this.iResponse=iResponse;
    }
    public void Execute(){

        SelectPhoto selectPhoto=new SelectPhoto(databasetest.db);
        List<PhotoDto> photolist= selectPhoto.fetch(PhotoDto.class);
        for (PhotoDto item : photolist)
        {
            MobileRequestAttachmentsResponse response=(MobileRequestAttachmentsResponse)iResponse;
            Api api = new Api();
            SoapObject res=api.SaveMobileRequestAttachments(new MobileRequestAttachmentsRequestDto(item.idillegal.toString(),
                    item.fileName,
                    GeneralExtentions.Extention.GetImei(context),
                    item.id.toString(),
                    item.pictype.toString(),
                    item.estateid.toString()));
            new ExecuteApi(MobileRequestAttachmentsResponse.class.getName(),context,response).execute(res);
        }
    }

}
