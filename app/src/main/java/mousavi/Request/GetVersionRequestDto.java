package mousavi.Request;

public class GetVersionRequestDto {
    public String imei;
    public String version;
    public GetVersionRequestDto(String imei,String version){
        this.imei=imei;
        this.version=version;
    }
}
