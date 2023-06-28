package mousavi.Request;

public class SendReadingListItemRequestDto {
    public String readinglist;
    public String imei;
    public String peymayeshlist;
    public String illegalbranchlist;
    public String pressurelist;
    public String version;
    public SendReadingListItemRequestDto(String readinglist,String peymayeshlist,String illegalbranchlist,String pressurelist,String imei, String version){
        this.readinglist=readinglist;
        this.peymayeshlist=peymayeshlist;
        this.illegalbranchlist=illegalbranchlist;
        this.pressurelist=pressurelist;
        this.imei=imei;
        this.version=version;
    }
}
