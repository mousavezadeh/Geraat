package mousavi.Geraat.Furtherinformation;

import mousavi.Geraat.Validation;

public class PostalcodeFurtherInformation extends FurtherInformation {
    String postalcode;
    public PostalcodeFurtherInformation(String serialno, int idlist, int estateid, String registerdate, String postalcode){
        super(serialno, idlist, estateid,registerdate);
        this.postalcode=postalcode;
    }

    @Override
    public boolean Validate() {
        boolean result=false;
        result=Validation.Postalcode(postalcode);
        return false;
    }

    @Override
    public Integer Type() {
        return 6;
    }
}
