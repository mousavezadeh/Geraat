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


import mousavi.database.pdl_input;

/**
 * Created by mehdi on 08/09/2018.
 */

public class Adapter_Searchbyname extends ArrayAdapter {

    private static Context context;
    private Typeface font;
     class ViewHolder
    {
        private  TextView txt_namefamily_title;
        private  TextView txt_serialno_title ;
        private  TextView txt_tanehpolomp_title;
        private  TextView txt_address_title;

        private  TextView txt_namefamily;
        private  TextView txt_serialno ;
        private  TextView txt_tanehpolomp;
        private  TextView txt_address;

    }
    private final ArrayList<pdl_input> mobileValues;

    public Adapter_Searchbyname(Context context, ArrayList<pdl_input> mobileValues) {
        super(context, layout.findbynameitems);
        this.context = context;
        this.mobileValues = mobileValues;
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
            gridView = inflater.inflate(layout.findbynameitems,parent,false);
            ViewHolder viewHolder = new ViewHolder();

            viewHolder.txt_namefamily = (TextView) gridView.findViewById(id.txt_findbyname_name_value);
            viewHolder.txt_serialno = (TextView) gridView.findViewById(id.txt_findbyname_serialno_value);
            viewHolder.txt_tanehpolomp = (TextView) gridView.findViewById(id.txt_findbyname_tanehpolomp_value);
            viewHolder.txt_address = (TextView) gridView.findViewById(id.txt_findbyname_address_value);

            viewHolder.txt_namefamily_title = (TextView) gridView.findViewById(id.txt_findbyname_name_title);
            viewHolder.txt_serialno_title = (TextView) gridView.findViewById(id.txt_findbyname_serialno_title);
            viewHolder.txt_tanehpolomp_title = (TextView) gridView.findViewById(id.txt_findbyname_tanehpolomp_title);
            viewHolder.txt_address_title = (TextView) gridView.findViewById(id.txt_findbyname_address_title);


            gridView.setTag(viewHolder);
            pdl_input temp=new pdl_input();
                    temp=mobileValues.get(position);


        }
        else
        {
            gridView = convertView;
        }

         ViewHolder holder = (ViewHolder) gridView.getTag();
        pdl_input temp=new pdl_input();
        temp=mobileValues.get(position);
        //region Set Font
        holder.txt_namefamily.setTypeface(font);
        holder.txt_serialno.setTypeface(font);
        holder.txt_tanehpolomp.setTypeface(font);
        holder.txt_address.setTypeface(font);
        holder.txt_namefamily_title.setTypeface(font);
        holder.txt_serialno_title.setTypeface(font);
        holder.txt_tanehpolomp_title.setTypeface(font);
        holder.txt_address_title.setTypeface(font);

//endregion
        //region Set Data
        holder.txt_namefamily.setText(temp.get_name_moshtarek());
        holder.txt_serialno.setText(temp.get_eshterak_for_display());


        String tempp="";
        tempp=Long.valueOf((temp.get_Polomp_Kontor())).toString()+" - "+Long.valueOf((temp.get_shomare_kontor())).toString();


        holder.txt_tanehpolomp.setText(tempp);
        holder.txt_address.setText(temp.get_address_moshtarek());

//endregion


        if (position % 2 == 1) {
            gridView.setBackgroundColor(Color.parseColor("#e6e6e6"));
        } else {
            gridView.setBackgroundColor(Color.parseColor("#bfbfbf"));
        }
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
