package mousavi.Geraat.Furtherinformation;

import mousavi.Geraat.Validation;

public class MetertrunkFurtherInformation extends FurtherInformation {
    String metertrunk;
    public MetertrunkFurtherInformation(String serialno, int idlist, int estateid, String registerdate, String metertrunk){
        super(serialno, idlist, estateid,registerdate);
        this.metertrunk=metertrunk;
    }

    @Override
    public boolean Validate() {
        boolean result=false;
        result=Validation.Metertrunk(metertrunk);
        return false;
    }

    @Override
    public Integer Type() {
        return 4;
    }
}
