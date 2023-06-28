package mousavi.Geraat.Furtherinformation;

import mousavi.Geraat.Validation;

public class AddressFurtherInformation extends FurtherInformation {
    String address;
    public AddressFurtherInformation(String serialno, int idlist, int estateid, String registerdate, String address){
        super(serialno, idlist, estateid,registerdate);
        this.address=address;
    }

    @Override
    public boolean Validate() {
        boolean result=false;
        result=Validation.Address(address);
        return false;
    }

    @Override
    public Integer Type() {
        return 5;
    }
}
