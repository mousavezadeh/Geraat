package mousavi.Dialogs;

import android.content.Context;
import android.widget.TextView;

public class MobileDialog extends OkCancleDialog{
    String mobile;
    int textviewid;
    public MobileDialog(Context context, int layoutId, int btnOk, int btnCancle, String mobile, int textviewid) {
        super(context, layoutId, btnOk, btnCancle);
        this.mobile=mobile;
        this.textviewid=textviewid;
    }

    @Override
    public void Show() {
        super.Show();
        TextView textviewmobile= dialog.findViewById(textviewid);
        textviewmobile.setText(mobile);
    }

}
