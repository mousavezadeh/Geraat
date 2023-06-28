package mousavi.Request;

import mousavi.Geraat.GeneralExtentions;

public class MobileRequestAttachmentsRequestDto {
    public String illegalbranchid;
    public String imagefile;
    public String imei;
    public String id;
    public String pictype;
    public String estateid;
    public MobileRequestAttachmentsRequestDto(String illegalbranchid,String imagefile,String imei,String id,String pictype, String estateid){
        this.illegalbranchid=illegalbranchid;
        this.imagefile=imagefile;
        this.imei=imei;
        this.id=id;
        this.pictype=pictype;
        this.estateid=estateid;
    }
}
