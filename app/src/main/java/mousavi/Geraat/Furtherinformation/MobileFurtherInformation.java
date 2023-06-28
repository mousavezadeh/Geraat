package mousavi.Geraat.Furtherinformation;

import mousavi.Geraat.Validation;

public class MobileFurtherInformation extends FurtherInformation {
    String mobile;
    public MobileFurtherInformation(String serialno,int idlist,int estateid,String registerdate,String mobile){
        super(serialno, idlist, estateid,registerdate);
        this.mobile=mobile;
    }

    @Override
    public boolean Validate() {
        boolean result=false;
        result=Validation.Len(mobile,11);
        result=Validation.MobileFormat(mobile);
        return false;
    }

    @Override
    public Integer Type() {
        return 1;
    }
}
