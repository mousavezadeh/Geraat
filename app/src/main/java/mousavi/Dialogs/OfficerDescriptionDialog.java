package mousavi.Dialogs;

import android.content.Context;
import android.widget.TextView;

public class OfficerDescriptionDialog extends OkCancleDialog{
    String description;
    int textviewid;
    public OfficerDescriptionDialog(Context context, int layoutId, int btnOk, int btnCancle, String description, int textviewid) {
        super(context, layoutId, btnOk, btnCancle);
        this.description=description;
        this.textviewid=textviewid;
    }

    @Override
    public void Show() {
        super.Show();
        TextView textviewdescription= dialog.findViewById(textviewid);
        textviewdescription.setText(description);
    }

}
