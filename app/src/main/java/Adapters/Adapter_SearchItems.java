package Adapters;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import mousavi.Geraat.R;
import mousavi.Geraat.R.id;
import mousavi.Geraat.R.layout;
import mousavi.database.ListGheraat;
import mousavi.database.code_mane;

/**
 * Created by mehdi on 08/09/2018.
 */


public class Adapter_SearchItems extends BaseAdapter  {

    private static Context context;
    private Typeface font;
    
     class ViewHolder
    {
        private TextView txt_namelist;
    }
    private final List<String> mobileValues;

   
    public Adapter_SearchItems(Context context,int resid,int textid, List<String> mobileValues) 
    {
    	
        //super();
        this.context = context;
        this.mobileValues = mobileValues;
        font = Typeface.createFromAsset(context.getAssets(), "Fonts/byekan.ttf");
    }
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
            String temp;
                    temp=mobileValues.get(position).toString();
        }
        else
        {
            gridView = convertView;
        }

         ViewHolder holder = (ViewHolder) gridView.getTag();
         String temp;
        temp=mobileValues.get(position).toString();
        holder.txt_namelist.setTypeface(font);
        holder.txt_namelist.setText(temp);
        holder.txt_namelist.setTextColor(Color.BLUE);
        return gridView;
    }

    public int getCount() {
        return mobileValues.size();
    }

    public String getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

}
