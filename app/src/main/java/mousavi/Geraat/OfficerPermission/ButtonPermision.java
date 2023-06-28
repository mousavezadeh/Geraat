package mousavi.Geraat.OfficerPermission;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import mousavi.Geraat.R;

public class  ButtonPermision <T extends android.widget.Button>{
    int permission;
    int btnid;
    View view;
    public ButtonPermision (int permission,int btnid,View view){
        this.permission=permission;
        this.btnid=btnid;
        this.view=view;
    }
    public void Handle(){
        if (permission==1) {
            AccessAction();
            return;
        }
        NotAccessAction();
    }
    private void AccessAction(){
        T temp=(T) view.findViewById(btnid);
        temp.setVisibility(View.VISIBLE);
    }
    private void NotAccessAction(){
        T temp=(T) view.findViewById(btnid);
        temp.setVisibility(View.GONE);
    }
}
