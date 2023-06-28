package mousavi.Geraat.Furtherinformation;

import mousavi.Geraat.Validation;

public class PolompFurtherInformation extends FurtherInformation {
    String polomp;
    public PolompFurtherInformation(String serialno, int idlist, int estateid,String registerdate, String polomp){
        super(serialno, idlist, estateid,registerdate);
        this.polomp=polomp;
    }

    @Override
    public boolean Validate() {
        boolean result=false;
        result=Validation.Polomp(polomp);
        return false;
    }

    @Override
    public Integer Type() {
        return 2;
    }
}
