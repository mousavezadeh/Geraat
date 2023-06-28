package mousavi.Geraat.OfficerPermission;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class LinearLayoutPermision<T extends LinearLayout>{
    int permission;
    int btnid;
    View view;
    public LinearLayoutPermision(int permission, int btnid, View view){
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
