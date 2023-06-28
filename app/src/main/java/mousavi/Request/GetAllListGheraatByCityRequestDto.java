package mousavi.Request;

public class GetAllListGheraatByCityRequestDto {
    public String Deviceid;
    public int Version;
    public GetAllListGheraatByCityRequestDto(String deviceid,int version){
        Deviceid=deviceid;
        Version = version;
    }
}
