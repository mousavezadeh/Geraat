package mousavi.Request.OnPostExecute;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import Adapters.Adapter_Porsesh;
import Adapters.Adapter_Report;
import mousavi.Geraat.R;
import mousavi.Request.GetAllListGheraatByCityResponse;
import mousavi.Request.GetOfficerReportResponse;
import mousavi.Request.IResponse;

public class GetOfficerReportOnPostExecute {
    Dialog dialog;
    Context context;
    IResponse iResponse;
    public GetOfficerReportOnPostExecute(Context context, IResponse iResponse){
      this.context=context;
      this.iResponse=iResponse;
    }
    public void Execute(){
        Dialog dialog = new Dialog(context,R.style.AppTheme);
        dialog.setContentView(R.layout.code_gheraat);
        dialog.setTitle("لیست های ارسال شده به واحد مشترکین");
        ListView list_code_mane = (ListView) dialog
                .findViewById(R.id.listView_code_gherat);

        LinearLayout title=(LinearLayout) dialog
                .findViewById(R.id.title_list_dialog_line);
        title.setVisibility(View.GONE);
        list_code_mane.setAdapter(new Adapter_Report(context,
                iResponse));
        dialog.show();

    }

}
