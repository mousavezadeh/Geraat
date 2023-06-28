package mousavi.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class OkCancleDialog implements IDialog {

    private Context ctx;
    protected Dialog dialog=null;
    List<ActionTypeButtonActionDto> actiontypebuttonactionlist=null;
    private String _title = "";
    private int _layoutId;

    public OkCancleDialog(Context context,int layoutId,int btnOk,int btnCancle){
        ctx = context;
        _layoutId = layoutId;
        actiontypebuttonactionlist = new ArrayList<>();
        actiontypebuttonactionlist.add(new ActionTypeButtonActionDto(btnOk,null));
        actiontypebuttonactionlist.add(new ActionTypeButtonActionDto(btnCancle,null));
    }
    public OkCancleDialog Ok(IButtonAction action){
        actiontypebuttonactionlist.get(0).buttonAction =action;
        return this;
    }

    public OkCancleDialog Cancle(IButtonAction action){
        actiontypebuttonactionlist.get(1).buttonAction =action;
        return this;
    }

    public OkCancleDialog SetTitle(String title){
        _title = title;
        return this;
    }

    public OkCancleDialog AddAction(int refBtn, IButtonAction buttonAction) {
        actiontypebuttonactionlist.add(new ActionTypeButtonActionDto(refBtn,buttonAction));
        return this;
    }


    public void Show(){
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
        dialog.show();
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
