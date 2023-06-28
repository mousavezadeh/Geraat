package mousavi.Request;

public class ChangeDefaultCityRequestDto {
    public String Deviceid;
    public String NewCityid;
    public ChangeDefaultCityRequestDto(String deviceid,String newCityid){
        this.Deviceid=deviceid;
        this.NewCityid=newCityid;
    }
}
