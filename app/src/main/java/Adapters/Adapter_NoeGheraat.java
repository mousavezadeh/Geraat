package Adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import mousavi.Geraat.R.id;
import mousavi.Geraat.R.layout;

/**
 * Created by mehdi on 08/09/2018.
 */

public class Adapter_NoeGheraat extends ArrayAdapter {

    private static Context context;
    private Typeface font;
     class ViewHolder
    {
        private TextView txt_namelist;
    }
    private final String[] mobileValues;

    public Adapter_NoeGheraat(Context context, String[] mobileValues) {
        super(context, layout.rowstyle_codemane);
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
            gridView = inflater.inflate(layout.rowstyle_codemane,parent,false);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.txt_namelist = (TextView) gridView.findViewById(id.txt_namelist_row_h);

            gridView.setTag(viewHolder);

            String temp;
                    temp=mobileValues[position].toString();

        }
        else
        {
            gridView = convertView;
        }

         ViewHolder holder = (ViewHolder) gridView.getTag();
         String temp;
        temp=mobileValues[position].toString();

        
        holder.txt_namelist.setTypeface(font);
        
        holder.txt_namelist.setText(temp);
        holder.txt_namelist.setTextColor(Color.BLACK);

        holder.txt_namelist.setBackgroundColor(Color.parseColor("#e6e6e6"));


        if (temp.contains("همه اشتراک ها")) holder.txt_namelist.setBackgroundColor(Color.parseColor("#e6e6e6"));
        if (temp.contains("قرائت نشده")) holder.txt_namelist.setBackgroundColor(Color.parseColor("#e6e6e6"));
        if (temp.contains("قرائت شده")) holder.txt_namelist.setBackgroundColor(Color.parseColor("#75ab40"));
        if (temp.contains("محل دربسته")) holder.txt_namelist.setBackgroundColor(Color.parseColor("#7f837b"));
        if (temp.contains("بخارگرفتگی")) holder.txt_namelist.setBackgroundColor(Color.parseColor("#7f837b"));
        if (temp.contains("خرابی کنتور")) holder.txt_namelist.setBackgroundColor(Color.parseColor("#fecc66"));
        if (temp.contains("آب مستقیم")) holder.txt_namelist.setBackgroundColor(Color.parseColor("#d75446"));
        
       
       
        return gridView;
    }


    public int getCount() {
        return mobileValues.length;
    }

    public String getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

}
