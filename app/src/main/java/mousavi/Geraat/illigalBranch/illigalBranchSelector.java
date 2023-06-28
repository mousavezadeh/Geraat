package mousavi.Geraat.illigalBranch;

public class illigalBranchSelector extends BaseilligalBranch {
    int illegaltype=0;
    String illegalbranch;
    public BaseilligalBranch baseilligalBranch;
    public illigalBranchSelector(Integer estateid, String nearestserialno, String lat, String lon, String comment,String registerdate,String illegalbranch){
        super(estateid,nearestserialno,lat,lon,comment,registerdate);
        this.illegalbranch=illegalbranch;
    }

    protected   illigalBranchSelector TeeBeforeTheMeter(){
        if (illegalbranch=="1")
        {
            TeeBeforeTheMeter teebeforethemeter=new TeeBeforeTheMeter(estateid,nearestserialno,lat,lon,comment,registerdate);
            this.illegaltype=teebeforethemeter.illegaltype();
            baseilligalBranch=teebeforethemeter;


        }
        return this;
    }
    protected  illigalBranchSelector ReverseTheMeter(){
        if (illegalbranch=="2")
        {
            ReverseTheMeter reverseTheMeter=new ReverseTheMeter(estateid,nearestserialno,lat,lon,comment,registerdate);
            this.illegaltype=reverseTheMeter.illegaltype();
            baseilligalBranch=reverseTheMeter;
        }
        return this;
    }
    protected  illigalBranchSelector RemoveTheMeter(){
        if (illegalbranch=="3")
        {
            RemoveTheMeter removeTheMeter=new RemoveTheMeter(estateid,nearestserialno,lat,lon,comment,registerdate);
            this.illegaltype=removeTheMeter.illegaltype();
            baseilligalBranch=removeTheMeter;
        }
        return this;
    }
    protected  illigalBranchSelector WaterResale(){
        if (illegalbranch=="4")
        {
            WaterResale waterResale=new WaterResale(estateid,nearestserialno,lat,lon,comment,registerdate);
            this.illegaltype= waterResale.illegaltype();
            baseilligalBranch=waterResale;
        }
        return this;
    }
    protected  illigalBranchSelector UnauthorizedBranching(){
        if (illegalbranch=="5")
        {
            UnauthorizedBranching unauthorizedBranching=new UnauthorizedBranching(estateid,nearestserialno,lat,lon,comment,registerdate);
            this.illegaltype= unauthorizedBranching.illegaltype();
            baseilligalBranch=unauthorizedBranching;

        }
        return this;
    }
    protected  illigalBranchSelector BreakingTheMeterSeal(){
        if (illegalbranch=="6")
        {
            BreakingTheMeterSeal breakingTheMeterSeal=new BreakingTheMeterSeal(estateid,nearestserialno,lat,lon,comment,registerdate);
            this.illegaltype= breakingTheMeterSeal.illegaltype();
            baseilligalBranch=breakingTheMeterSeal;
        }
        return this;
    }
    protected  illigalBranchSelector UnderConstruction(){
        if (illegalbranch=="7")
        {
            UnderConstruction underConstruction=new UnderConstruction(estateid,nearestserialno,lat,lon,comment,registerdate);
            this.illegaltype= underConstruction.illegaltype();
            baseilligalBranch=underConstruction;
        }
        return this;
    }
    protected  illigalBranchSelector CompletionOfConstruction(){
        if (illegalbranch=="8")
        {
            CompletionOfConstruction completionOfConstruction=new CompletionOfConstruction(estateid,nearestserialno,lat,lon,comment,registerdate);
            this.illegaltype= completionOfConstruction.illegaltype();
            baseilligalBranch=completionOfConstruction;
        }
        return this;
    }
    protected  illigalBranchSelector UnauthorizedUse(){
        if (illegalbranch=="9")
        {
            UnauthorizedUse unauthorizedUse=new UnauthorizedUse(estateid,nearestserialno,lat,lon,comment,registerdate);
            this.illegaltype= unauthorizedUse.illegaltype();
            baseilligalBranch=unauthorizedUse;
        }
        return this;
    }
    protected  illigalBranchSelector BeginningOfNonPermanentSettlement(){
        if (illegalbranch=="12")
        {
            BeginningOfNonPermanentSettlement beginningOfNonPermanentSettlement=new BeginningOfNonPermanentSettlement(estateid,nearestserialno,lat,lon,comment,registerdate);
            this.illegaltype= beginningOfNonPermanentSettlement.illegaltype();
            baseilligalBranch=beginningOfNonPermanentSettlement;
        }
        return this;
    }
    protected  illigalBranchSelector EndOfPermanentSettlement(){
        if (illegalbranch=="13")
        {
            EndOfPermanentSettlement endOfPermanentSettlement=new EndOfPermanentSettlement(estateid,nearestserialno,lat,lon,comment,registerdate);
            this.illegaltype=endOfPermanentSettlement.illegaltype();
            baseilligalBranch=endOfPermanentSettlement;
        }
        return this;
    }
    @Override
    public int illegaltype() {
        return illegaltype;
    }
}
