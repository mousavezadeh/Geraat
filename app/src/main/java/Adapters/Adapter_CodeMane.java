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
import mousavi.database.code_mane;

/**
 * Created by mehdi on 08/09/2018.
 */

public class Adapter_CodeMane extends ArrayAdapter {
    private static Context context;
    private Typeface font;
    class ViewHolder
    {
        private TextView txt_roozkar;
        private TextView txt_namelist;

    }
    private int PropertyCondition;
    private int Meterstatus;
    private final ArrayList<code_mane> mobileValues;

    public Adapter_CodeMane(Context context, ArrayList<code_mane> mobileValues) {
        super(context, layout.rowstyle_codemane);
        this.context = context;
        this.mobileValues = mobileValues;
        font = Typeface.createFromAsset(context.getAssets(), "Fonts/byekan.ttf");
    }

    public Adapter_CodeMane(Context context, ArrayList<code_mane> mobileValues,int PropertyCondition) {
        super(context, layout.rowstyle_codemane);
        this.context = context;
        this.mobileValues = mobileValues;
        this.PropertyCondition=PropertyCondition;
        font = Typeface.createFromAsset(context.getAssets(), "Fonts/byekan.ttf");
    }

    public Adapter_CodeMane(Context context, ArrayList<code_mane> mobileValues,int PropertyCondition,int meterStatus) {
        super(context, layout.rowstyle_codemane);
        this.context = context;
        this.mobileValues = mobileValues;
        this.PropertyCondition=PropertyCondition;
        this.Meterstatus=meterStatus;
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
            viewHolder.txt_roozkar = (TextView) gridView.findViewById(id.txt_roozkar_row_h);
            viewHolder.txt_namelist = (TextView) gridView.findViewById(id.txt_namelist_row_h);
            gridView.setTag(viewHolder);
            code_mane temp=new code_mane();
            temp=mobileValues.get(position);

        }
        else
        {
            gridView = convertView;
        }

        ViewHolder holder = (ViewHolder) gridView.getTag();

        code_mane temp=new code_mane();
        temp=mobileValues.get(position);
        holder.txt_roozkar.setTypeface(font);
        holder.txt_namelist.setTypeface(font);

        if ((PropertyCondition>1) &&
                (temp.get_code_mane().compareTo("0")==0 || temp.get_code_mane().compareTo("2")==0 )) {
            holder.txt_namelist.setVisibility(View.GONE);
            holder.txt_roozkar.setVisibility(View.GONE);
        }
        else
        {
            holder.txt_namelist.setVisibility(View.VISIBLE);
            holder.txt_roozkar.setVisibility(View.GONE);
        }



        holder.txt_roozkar.setText(temp.get_code_mane());
        holder.txt_namelist.setText(temp.get_title_mane());



        if (temp.get_title_mane().contains("قرائت نشده")) holder.txt_namelist.setBackgroundColor(Color.parseColor("#e6e6e6"));
        if (temp.get_title_mane().contains("قرائت شده")) holder.txt_namelist.setBackgroundColor(Color.parseColor("#75ab40"));
        if (temp.get_title_mane().contains("محل دربسته")) holder.txt_namelist.setBackgroundColor(Color.parseColor("#7f837b"));
        if (temp.get_title_mane().contains("بخارگرفتگی")) holder.txt_namelist.setBackgroundColor(Color.parseColor("#7f837b"));
        if (temp.get_title_mane().contains("خرابی کنتور")) holder.txt_namelist.setBackgroundColor(Color.parseColor("#fecc66"));
        if (temp.get_title_mane().contains("آب مستقیم")) holder.txt_namelist.setBackgroundColor(Color.parseColor("#d75446"));

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
