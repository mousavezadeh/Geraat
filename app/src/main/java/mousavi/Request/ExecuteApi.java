package mousavi.Request;

import android.app.DatePickerDialog;
import android.app.DownloadManager;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.core.content.ContextCompat;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.lang.reflect.Constructor;
import java.lang.reflect.Type;

import mousavi.Geraat.GeneralExtentions;
import mousavi.Request.OnPostExecute.GetAllListGheraatByCityOnPostExecute;
import mousavi.Request.OnPostExecute.GetCityByDeviceIdOnPostExecute;
import mousavi.Request.OnPostExecute.GetListMobileOnPostExecute;
import mousavi.Request.OnPostExecute.GetOfficerReportOnPostExecute;
import mousavi.Request.OnPostExecute.SendReadingListItemOnPostExecute;
import mousavi.database.Insert.InsertReadingList;
import mousavi.database.Update.UpdatePhotoSendToServer;
import mousavi.database.databasetest;

public   class  ExecuteApi  extends AsyncTask<SoapObject,String,IResponse> {

    String baseUrl = "https://pay.abfakhz.ir/MobileWebService.asmx";
    String namespace="http://tempuri.org/";
    String classname;
    Context context;
    GetAllListGheraatByCityResponse getAllListGheraatByCityResponse=null;
    MobileRequestAttachmentsResponse mobileRequestAttachmentsResponse=null;
    public ExecuteApi(String classname,Context context){
        this.classname=classname;
        this.context=context;
    }
    public ExecuteApi(String classname,Context context,GetAllListGheraatByCityResponse getAllListGheraatByCityResponse){
        this.classname=classname;
        this.context=context;
        this.getAllListGheraatByCityResponse=getAllListGheraatByCityResponse;
    }
    public ExecuteApi(String classname,Context context,MobileRequestAttachmentsResponse mobileRequestAttachmentsResponse){
        this.classname=classname;
        this.context=context;
        this.mobileRequestAttachmentsResponse=mobileRequestAttachmentsResponse;
    }
    @Override
    protected IResponse doInBackground(SoapObject... soapObjects) {

        try {
            @SuppressWarnings("unused")
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(soapObjects[0]);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(baseUrl);
            androidHttpTransport.call(soapObjects[0].getNamespace()+soapObjects[0].getName(), envelope);
            Object res =  (Object) envelope.getResponse();
            Log.e("response",res.toString());
            Constructor[] constructor
                    = Class.forName(classname).getConstructors();
            IResponse response=(IResponse) constructor[1].newInstance(res);
            return  response;
        }catch (Exception er){
            Log.e("ss",er.toString());
        }
        return null;
    }
    @Override
    protected void onPreExecute() {

    }

    protected void onPostExecute(IResponse iresponse) {
        String m="";
        Integer a=120;

        if (iresponse.getClass().equals(GetAllListGheraatByCityResponse.class)) {
            GetAllListGheraatByCityOnPostExecute getAllListGheraatByCityOnPostExecute = new GetAllListGheraatByCityOnPostExecute(context, iresponse);
            getAllListGheraatByCityOnPostExecute.Execute();
            return;
        }
        if (iresponse.getClass().equals(GetOfficerReportResponse.class)) {
            GetOfficerReportOnPostExecute getOfficerReportOnPostExecute = new GetOfficerReportOnPostExecute(context, iresponse);
            getOfficerReportOnPostExecute.Execute();
            return;
        }
        if (iresponse.getClass().equals(GetCityByDeviceIdResponse.class)) {
            GetCityByDeviceIdOnPostExecute getCityByDeviceIdOnPostExecute = new GetCityByDeviceIdOnPostExecute(context, iresponse);
            getCityByDeviceIdOnPostExecute.Execute();
            return;
        }
        if (iresponse.getClass().equals(GetListMobileResponse.class)) {
            GetListMobileOnPostExecute getListMobileOnPostExecute = new GetListMobileOnPostExecute(context, iresponse,getAllListGheraatByCityResponse);
            getListMobileOnPostExecute.Execute();
            return;
        }
        if (iresponse.getClass().equals(SendReadingListItemResponse.class)) {
            SendReadingListItemOnPostExecute sendReadingListItemOnPostExecute = new SendReadingListItemOnPostExecute(context, iresponse);
            sendReadingListItemOnPostExecute.Execute();
            return;
        }
        if (iresponse.getClass().equals(MobileRequestAttachmentsResponse.class)) {
            UpdatePhotoSendToServer updatePhotoSendToServer=new
                    UpdatePhotoSendToServer(databasetest.db,iresponse.,GeneralExtentions.Extention.GetDateNowWithFormat());
            updatePhotoSendToServer.execute();
            return;
        }
        if (iresponse.getClass().equals(GetVersionResponse.class)) {

            return;
        }

    }
}
