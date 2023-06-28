package mousavi.Request;

import android.os.AsyncTask;
import android.util.Log;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.io.File;

import mousavi.Geraat.GeneralExtentions;

public class Api  {
    String baseUrl = "https://pay.abfakhz.ir/MobileWebService.asmx";
    String namespace="http://tempuri.org/";
    public SoapObject GetAllListGheraatByCity(GetAllListGheraatByCityRequestDto req){
        SoapObject request = new SoapObject(namespace, new Object(){}.getClass().getEnclosingMethod().getName());
        request.addProperty("CityId", req.Deviceid);
        request.addProperty("Version", req.Version);
        return request;
    }
    public SoapObject GetOfficerReport(GetOfficerReportRequestDto req){
        SoapObject request = new SoapObject(namespace, new Object(){}.getClass().getEnclosingMethod().getName());
        request.addProperty("CityId", req.Deviceid);
        return request;
    }
    public SoapObject GetCityByDeviceId(GetCityByDeviceIdDto req){
        SoapObject request = new SoapObject(namespace, new Object(){}.getClass().getEnclosingMethod().getName());
        request.addProperty("Imei", req.Deviceid);
        return request;
    }
    public SoapObject ChangeDefaultCity(ChangeDefaultCityRequestDto req){
        SoapObject request = new SoapObject(namespace, "ChangeDefaultCity");
        request.addProperty("Imei",req.Deviceid);
        request.addProperty("ID", req.NewCityid);
        return request;
    }
    public SoapObject GetListMobile(GetListMobileRequestDto req){
        SoapObject request = new SoapObject(namespace, new Object(){}.getClass().getEnclosingMethod().getName());
        request.addProperty("OutType", req.OutType);
        request.addProperty("IDFile", req.IDFile);
        return request;
    }

    public SoapObject GetVersion(GetVersionRequestDto req){
        SoapObject request = new SoapObject(namespace, new Object(){}.getClass().getEnclosingMethod().getName());
        request.addProperty("imei", req.imei);
        request.addProperty("Version", req.version);
        return request;
    }

    public SoapObject SaveMobileRequestAttachments(MobileRequestAttachmentsRequestDto req){
        SoapObject request = new SoapObject(namespace, new Object(){}.getClass().getEnclosingMethod().getName());
        request.addProperty("ID_IllegalBranch", req.illegalbranchid);
        request.addProperty("imgfiletype", "");
        request.addProperty("imgfilename", "");
        request.addProperty("imgfilesize", 20);
        request.addProperty("Base64imagefile",  req.imagefile);
        request.addProperty("imei", req.imei);
        request.addProperty("Localid", req.id);
        request.addProperty("pictype", req.pictype);
        request.addProperty("estateid", req.estateid);
        return request;
    }


    public SoapObject SendListGheraat(SendReadingListItemRequestDto req){
        SoapObject request = new SoapObject(namespace, new Object(){}.getClass().getEnclosingMethod().getName());
        request.addProperty("JsonArrayList", req.readinglist);
        request.addProperty("id_list", "0");
        request.addProperty("imei", req.imei);
        request.addProperty("JsonArrayListPeymayesh", req.peymayeshlist);
        request.addProperty("JsonArrayListilligalBranch", req.illegalbranchlist);
        request.addProperty("JsonArrayListPressure", req.pressurelist);
        request.addProperty("Version", req.version);
        return request;
    }
}
