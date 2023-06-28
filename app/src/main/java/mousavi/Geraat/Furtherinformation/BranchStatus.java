package mousavi.Geraat.Furtherinformation;

import mousavi.Geraat.Validation;

public class BranchStatus extends FurtherInformation {
    Integer branchstatus;
    public BranchStatus(String serialno, Integer idlist, Integer estateid, String registerdate, Integer branchstatus){
        super(serialno, idlist, estateid,registerdate);
        this.branchstatus=branchstatus;

    }

    @Override
    public boolean Validate() {
        return false;
    }

    @Override
    public Integer Type() {
        return 8;
    }
}
