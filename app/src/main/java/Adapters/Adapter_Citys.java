package Adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import mousavi.Geraat.R.id;
import mousavi.Geraat.R.layout;
import mousavi.Request.GetCityByDeviceIdResponse;
import mousavi.Request.GetOfficerReportResponse;
import mousavi.Request.IResponse;
import mousavi.database.Citys;

/**
 * Created by mehdi on 08/09/2018.
 */

public class Adapter_Citys extends ArrayAdapter {
    GetCityByDeviceIdResponse getCityByDeviceIdResponse;
    IResponse response;
    private static Context context;
    private Typeface font;
    class ViewHolder
    {
        private TextView txt_namelist;
    }


    public Adapter_Citys(Context context, IResponse response) {
        super(context, layout.rowstyle_mainmenu);
        this.context = context;
        this.response =response;
        font = Typeface.createFromAsset(context.getAssets(), "Fonts/byekan.ttf");
        this.getCityByDeviceIdResponse=(GetCityByDeviceIdResponse) response;
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
            gridView = inflater.inflate(layout.rowstyle_codemane,parent,false);
            ViewHolder viewHolder = new ViewHolder();

            viewHolder.txt_namelist = (TextView) gridView.findViewById(id.txt_namelist_row_h);

            gridView.setTag(viewHolder);
            GetCityByDeviceIdResponse citys=new GetCityByDeviceIdResponse().ResponseList.get(position);

        }
        else
        {
            gridView = convertView;
        }

        ViewHolder holder = (ViewHolder) gridView.getTag();
        GetCityByDeviceIdResponse citys=new GetCityByDeviceIdResponse().ResponseList.get(position);
        holder.txt_namelist.setTextColor(Color.BLACK);

        if (citys.defaultcityid.compareTo("0")==0)
            holder.txt_namelist.setBackgroundColor(Color.parseColor("#e6e6e6"));
        else
            holder.txt_namelist.setBackgroundColor(Color.parseColor("#7f7f7f"));

        holder.txt_namelist.setTypeface(font);
        holder.txt_namelist.setText(citys.cityname);
        return gridView;
    }


    public int getCount() {
        return getCityByDeviceIdResponse.ResponseList.size();
    }

    public String getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

}
