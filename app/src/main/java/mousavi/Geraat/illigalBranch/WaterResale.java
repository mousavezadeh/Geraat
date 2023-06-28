package mousavi.Geraat.illigalBranch;

public class WaterResale extends BaseilligalBranch {

    WaterResale(Integer estateid, String nearestserialno, String lat, String lon, String comment, String registerdate) {
        super(estateid, nearestserialno, lat, lon, comment, registerdate);
    }

    @Override
    public int illegaltype() {
        return 4;
    }
}
