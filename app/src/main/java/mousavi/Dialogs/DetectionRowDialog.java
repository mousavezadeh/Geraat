package mousavi.Dialogs;

import android.content.Context;
import android.widget.TextView;

public class DetectionRowDialog extends OkCancleDialog{
    float detectionrow;
    int textviewid;
    public DetectionRowDialog(Context context, int layoutId, int btnOk, int btnCancle, float detectionrow, int textviewid) {
        super(context, layoutId, btnOk, btnCancle);
        this.detectionrow=detectionrow;
        this.textviewid=textviewid;
    }

    @Override
    public void Show() {
        super.Show();
        TextView textviewdetectionrow= dialog.findViewById(textviewid);
        textviewdetectionrow.setText(String.valueOf(detectionrow));
    }

}
