package mousavi.Request;

public interface IRequest {
  public String  URL = "https://pay.abfakhz.ir/MobileWebService.asmx";
  public String Namespace="http://tempuri.org/";
  public String SoapAction();
  public String MethodName();
  public void  onPreExecute();
  public void onPostExecute();
  public  IResponse doInBackground();
}
