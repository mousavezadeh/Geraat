package Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import mousavi.Geraat.R.id;
import mousavi.Geraat.R.layout;
import mousavi.Request.GetAllListGheraatByCityResponse;
import mousavi.Request.IResponse;
import mousavi.database.ListGheraat;

/**
 * Created by mehdi on 08/09/2018.
 */

public class Adapter_Porsesh extends ArrayAdapter {
    GetAllListGheraatByCityResponse getAllListGheraatByCityResponse;
    private  Context context;
    private Typeface font;
     class ViewHolder
    {
        private TextView txt_roozkar;
        private TextView txt_namelist;
        private  TextView txt_tozihatelist;
        private LinearLayout layout_tozihat;
    }

    private  ArrayList<ListGheraat> mobileValues=null;
    IResponse response;
    public Adapter_Porsesh(Context context, ArrayList<ListGheraat> mobileValues) {
        super(context, layout.rowstyle_mainmenu);
        this.context = context;
        this.mobileValues = mobileValues;
        font = Typeface.createFromAsset(context.getAssets(), "Fonts/byekan.ttf");
    }

    public Adapter_Porsesh(Context context, IResponse response) {
        super(context, layout.rowstyle_mainmenu);
        this.context = context;
        this.getAllListGheraatByCityResponse=(GetAllListGheraatByCityResponse) response ;
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
            gridView = inflater.inflate(layout.rowstyle_mainmenu,parent,false);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.txt_roozkar = (TextView) gridView.findViewById(id.txt_roozkar_row_h);
            viewHolder.txt_namelist = (TextView) gridView.findViewById(id.txt_namelist_row_h);
            viewHolder.txt_tozihatelist = (TextView) gridView.findViewById(id.txt_tozihatelist_row_h);
            viewHolder.layout_tozihat = (LinearLayout) gridView.findViewById(id.linearlayout_tozihatelist);

            gridView.setTag(viewHolder);

            GetAllListGheraatByCityResponse response=
                    getAllListGheraatByCityResponse.ResponseList.get(position);
//            ListGheraat temp=new ListGheraat();
//                    temp=mobileValues.get(position);
        }
        else
        {
            gridView = convertView;
        }

         ViewHolder holder = (ViewHolder) gridView.getTag();
        GetAllListGheraatByCityResponse response=
                getAllListGheraatByCityResponse.ResponseList.get(position);

        holder.txt_roozkar.setTypeface(font);
        holder.txt_namelist.setTypeface(font);
        holder.txt_tozihatelist.setTypeface(font);

        holder.txt_roozkar.setText(response.day_);
        holder.txt_namelist.setText(response.name_list);
        holder.txt_tozihatelist.setText(response.Comment);

        if(response.Comment!=null)
        if (response.Comment.compareTo("null")==0)
        {holder.layout_tozihat.setVisibility(View.GONE);}
        else
        {holder.layout_tozihat.setVisibility(View.VISIBLE);}
        return gridView;
    }


    public int getCount() {
        return getAllListGheraatByCityResponse.ResponseList.size();
    }

    public String getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

}
