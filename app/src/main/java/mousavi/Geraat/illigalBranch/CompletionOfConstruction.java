package mousavi.Geraat.illigalBranch;

public class CompletionOfConstruction extends BaseilligalBranch{
    CompletionOfConstruction(Integer estateid, String nearestserialno, String lat, String lon, String comment, String registerdate) {
        super(estateid, nearestserialno, lat, lon, comment, registerdate);
    }

    @Override
    public int illegaltype() {
        return 8;
    }
}
