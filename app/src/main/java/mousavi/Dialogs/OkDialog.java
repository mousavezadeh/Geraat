package mousavi.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class OkDialog {

    private Context ctx;
    private Dialog dialog=null;
    List<ActionTypeButtonActionDto> actiontypebuttonactionlist=null;
    private String _title = "";
    private int _layoutId;

    public OkDialog(Context context, int layoutId, int btnOk){
        ctx = context;
        _layoutId = layoutId;
        actiontypebuttonactionlist = new ArrayList<>();
        actiontypebuttonactionlist.add(new ActionTypeButtonActionDto(btnOk,null));

    }
    public OkDialog Ok(IButtonAction action){
        actiontypebuttonactionlist.get(0).buttonAction =action;
        return this;
    }
    public OkDialog SetTitle(String title){
        _title = title;
        return this;
    }

    public void Build(){
        Dialog dialog = new Dialog(ctx);
        dialog.setContentView(_layoutId);
        dialog.setTitle(_title);

        for (final ActionTypeButtonActionDto item :actiontypebuttonactionlist) {
            dialog.findViewById(item.refControl).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    item.buttonAction.Action();
                }
            });
        }
        this.dialog=dialog;
    }
    public void Show(){
        if (this.dialog==null)
            return;
        this.dialog.show();
    }

    public void Hide(){
        if (this.dialog==null)
            return;
        this.dialog.dismiss();
    }
    public Dialog GetDialog(){
        return this.dialog;
    }
}
