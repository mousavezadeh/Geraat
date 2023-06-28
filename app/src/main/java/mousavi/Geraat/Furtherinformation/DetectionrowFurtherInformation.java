package mousavi.Geraat.Furtherinformation;

import mousavi.Geraat.Validation;

public class DetectionrowFurtherInformation extends FurtherInformation {
    String detectionrow;
    public DetectionrowFurtherInformation(String serialno, int idlist, int estateid, String registerdate, String detectionrow){
        super(serialno, idlist, estateid,registerdate);
        this.detectionrow=detectionrow;
    }

    @Override
    public boolean Validate() {
        boolean result=false;
        result=Validation.Detectionrow(detectionrow);
        return false;
    }

    @Override
    public Integer Type() {
        return 3;
    }
}
