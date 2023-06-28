package mousavi.Intent;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;

import mousavi.database.Pic;

public class IntenTakePicture extends BaseIntent{
    private Intent intent ;
    private Uri uri;
    String filepath;
    int btnid;

    public IntenTakePicture(Uri uri,String filepath,int btnid){
        if (intent==null)
            intent=new Intent(intentactionname());
        this.uri=uri;
        this.filepath=filepath;
        this.btnid=btnid;
        intent.putExtra(defaultvariablename(),uri);
        intent.putExtra("fpath", filepath);
        intent.putExtra("btnid", btnid);
        intent.putExtra("pictype", 0);
    }

    @Override
    protected String intentactionname() {
        return MediaStore.ACTION_IMAGE_CAPTURE;
    }

    @Override
    protected String defaultvariablename() {
        return MediaStore.EXTRA_OUTPUT;
    }

    @Override
    public Intent Getintent() {
        return intent;
    }
}
