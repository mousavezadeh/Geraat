package mousavi.Request;

public class GetListMobileRequestDto {
    public String OutType="JsonContent";
    public String IDFile;
    public GetListMobileRequestDto(String idfile){

        this.IDFile=idfile;
    }
}
