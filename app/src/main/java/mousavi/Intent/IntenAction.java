package mousavi.Intent;

import android.content.Intent;

public class IntenAction extends BaseIntent{
    private Intent intent ;

    public IntenAction(String value){
        if (intent==null)
            intent=new Intent(intentactionname());
        intent.putExtra(defaultvariablename(),value);
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
