package mousavi.Dialogs;

import android.content.Context;
import android.widget.TextView;

public class PostalCodeDialog extends OkCancleDialog{
    String postalcode;
    int textviewid;
    public PostalCodeDialog(Context context, int layoutId, int btnOk, int btnCancle,String postalcode,int textviewid) {
        super(context, layoutId, btnOk, btnCancle);
        this.postalcode=postalcode;
        this.textviewid=textviewid;
    }

    @Override
    public void Show() {
        super.Show();
        TextView textviewpostalcode= dialog.findViewById(textviewid);
        textviewpostalcode.setText(postalcode);
    }

}
