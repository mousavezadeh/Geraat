package mousavi.Intent;

import android.content.Intent;

public class IntenEmpty extends BaseIntent{
    private Intent intent ;
    public IntenEmpty(){
        if (intent==null)
            intent=new Intent(intentactionname());
    }
    @Override
    protected String intentactionname() {
        return "mousavi.Geraat";
    }

    @Override
    protected String defaultvariablename() {
        return "Action";
    }

    @Override
    public Intent Getintent() {
        return intent;
    }
}
