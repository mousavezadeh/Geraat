package mousavi.Dialogs;

import android.content.Context;
import android.widget.TextView;

public class AddressDialog extends OkCancleDialog{
    String address;
    int textviewid;
    public AddressDialog(Context context, int layoutId, int btnOk, int btnCancle, String address, int textviewid) {
        super(context, layoutId, btnOk, btnCancle);
        this.address=address;
        this.textviewid=textviewid;
    }

    @Override
    public void Show() {
        super.Show();
        TextView textviewaddress= dialog.findViewById(textviewid);
        textviewaddress.setText(address);
    }

}
