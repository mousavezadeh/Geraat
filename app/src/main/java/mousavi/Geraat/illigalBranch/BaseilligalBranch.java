package mousavi.Geraat.illigalBranch;

public abstract class BaseilligalBranch {
    public abstract int illegaltype() ;
    public String comment;
    public String datesendtoserver;
    public String nearestserialno;
    public String registerdate;
    public Integer sendtoserver = 0;
    public String id;
    public String lat;
    public String lon;
    public Integer  estateid;
     BaseilligalBranch(Integer estateid,String nearestserialno,String lat,String lon,String comment,String registerdate){
      this.estateid=estateid;
      this.nearestserialno=nearestserialno;
      this.lat=lat;
      this.lon=lon;
      this.comment=comment;
      this.registerdate=registerdate;
     }
}
