package mousavi.Geraat.OfficerPermission;

import android.view.View;
import android.widget.Button;

import mousavi.Geraat.FloatingActionButton;

public class FloatingActionButtonPermision<T extends FloatingActionButton>{
    int permission;
    int btnid;
    View view;
    public FloatingActionButtonPermision(int permission, int btnid, View view){
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
