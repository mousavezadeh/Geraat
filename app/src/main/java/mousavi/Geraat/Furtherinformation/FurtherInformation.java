package mousavi.Geraat.Furtherinformation;

public abstract class FurtherInformation {
    protected   String serialno;
    protected   int idlist;
    protected   int estateid;
    protected String registerdate;

    public FurtherInformation(String serialno,int idlist,int estateid,String registerdate){
        this.serialno=serialno;
        this.idlist=idlist;
        this.estateid=estateid;
        this.registerdate=registerdate;
    }
    public abstract boolean Validate();
    public abstract Integer Type();
}
