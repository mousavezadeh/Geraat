package Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import mousavi.Geraat.R.id;
import mousavi.Geraat.R.layout;
import mousavi.Request.GetAllListGheraatByCityResponse;
import mousavi.Request.GetOfficerReportResponse;
import mousavi.Request.IResponse;
import mousavi.database.ListGheraat;

/**
 * Created by mehdi on 08/09/2018.
 */

public class Adapter_Report extends ArrayAdapter {
    GetOfficerReportResponse getOfficerReportResponse;
    private static Context context;
    private Typeface font;
    IResponse response;
     class ViewHolder
    {
        private  TextView txt_report_date;
        private  TextView txt_report_gherat_number;
        private  TextView txt_report_peymayesh_number;
        private  TextView txt_report_illigal_number;
        private  TextView txt_report_gherat_title;
        private  TextView txt_report_peymayesh_title;
        private  TextView txt_report_illigal_title;

    }


    public Adapter_Report(Context context, IResponse response) {
        super(context, layout.rowstyle_mainmenu);
        this.context = context;
        this.response =response;
        font = Typeface.createFromAsset(context.getAssets(), "Fonts/byekan.ttf");
    }



    @Override

    public View getView(final int position, View convertView, ViewGroup parent)
    {

        View gridView = null;
        if (convertView == null) 
        {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            gridView = new View(context);
            gridView = inflater.inflate(layout.reportitems,parent,false);
            ViewHolder viewHolder = new ViewHolder();

            viewHolder.txt_report_date = (TextView) gridView.findViewById(id.txt_report_date);
            viewHolder.txt_report_gherat_number = (TextView) gridView.findViewById(id.txt_report_gherat_number);
            viewHolder.txt_report_peymayesh_number = (TextView) gridView.findViewById(id.txt_report_peymayesh_number);
            viewHolder.txt_report_illigal_number = (TextView) gridView.findViewById(id.txt_report_illigal_number);
            viewHolder.txt_report_gherat_title = (TextView) gridView.findViewById(id.txt_report_gherat_title);
            viewHolder.txt_report_peymayesh_title = (TextView) gridView.findViewById(id.txt_report_peymayesh_title);
            viewHolder.txt_report_illigal_title = (TextView) gridView.findViewById(id.txt_report_illigal_title);
            gridView.setTag(viewHolder);
            GetOfficerReportResponse response=new GetOfficerReportResponse().ResponseList.get(position);
        }
        else
        {
            gridView = convertView;
        }

         ViewHolder holder = (ViewHolder) gridView.getTag();
        GetOfficerReportResponse response=new GetOfficerReportResponse().ResponseList.get(position);

        //region Set Font
        holder.txt_report_date.setTypeface(font);
        holder.txt_report_gherat_number.setTypeface(font);
        holder.txt_report_peymayesh_number.setTypeface(font);
        holder.txt_report_illigal_number.setTypeface(font);
        holder.txt_report_gherat_title.setTypeface(font);
        holder.txt_report_peymayesh_title.setTypeface(font);
        holder.txt_report_illigal_title.setTypeface(font);
//endregion
        //region Set Data
        holder.txt_report_date.setText(response.date_);
        holder.txt_report_gherat_number.setText(response.readCount);
        holder.txt_report_peymayesh_number.setText(response.PeymayeshCount);
        holder.txt_report_illigal_number.setText(response.IllegalCount);

//endregion
        return gridView;
    }


    public int getCount() {
        return getOfficerReportResponse.ResponseList.size();
    }

    public String getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

}
