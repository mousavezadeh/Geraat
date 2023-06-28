package mousavi.Dialogs;

import android.content.Context;
import android.widget.TextView;

public class MeterTrunkPolumpDialog extends OkCancleDialog{
    String metertrunk;
    String meterpolump;
    int textviewidmetertrunk;
    int textviewidmeterpolump;
    public MeterTrunkPolumpDialog(Context context, int layoutId, int btnOk, int btnCancle, String metertrunk,String meterpolump, int textviewidmetertrunk,int textviewidmeterpolump) {
        super(context, layoutId, btnOk, btnCancle);
        this.metertrunk=metertrunk;
        this.meterpolump=meterpolump;
        this.textviewidmetertrunk=textviewidmetertrunk;
        this.textviewidmeterpolump=textviewidmeterpolump;
    }

    @Override
    public void Show() {
        super.Show();
        TextView textviewidmetertrunk= dialog.findViewById(this.textviewidmetertrunk);
        textviewidmetertrunk.setText(metertrunk);
        TextView textviewidmeterpolump= dialog.findViewById(this.textviewidmeterpolump);
        textviewidmeterpolump.setText(meterpolump);
    }

}
