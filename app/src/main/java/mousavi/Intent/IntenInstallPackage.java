package mousavi.Intent;

import android.content.Intent;
import android.net.Uri;

public class IntenInstallPackage extends BaseIntent{
    private Intent intent ;
    private Uri uri;
    public IntenInstallPackage(Uri uri){
        if (intent==null)
            intent=new Intent(intentactionname());
        this.uri=uri;
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.setDataAndType(uri, "application/vnd.android.package-archive");
        intent.putExtra(defaultvariablename(),true);
    }

    @Override
    protected String intentactionname() {
        return Intent.ACTION_VIEW;
    }

    @Override
    protected String defaultvariablename() {
        return Intent.EXTRA_RETURN_RESULT;
    }

    @Override
    public Intent Getintent() {
        return intent;
    }
}
