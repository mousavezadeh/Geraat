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
import mousavi.database.Dto.RozkarDto;

/**
 * Created by mehdi on 08/09/2018.
 */

public class Adapter_Rozkar extends ArrayAdapter {

    private static Context context;
    private Typeface font;
     class ViewHolder
    {
        private TextView txt_namelist;
    }
    private final RozkarDto rozkarDto;

    public Adapter_Rozkar(Context context, RozkarDto rozkarDto) {
        super(context, layout.rowstyle_codemane);
        this.context = context;
        this.rozkarDto = rozkarDto;
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
            RozkarDto rozkarDto=new RozkarDto();


        }
        else
        {
            gridView = convertView;
        }

         ViewHolder holder = (ViewHolder) gridView.getTag();
         String temp;
        RozkarDto rozkarDto=new RozkarDto();

        
        holder.txt_namelist.setTypeface(font);
        
        holder.txt_namelist.setText(rozkarDto.rozkar);
        holder.txt_namelist.setTextColor(Color.BLACK);

        holder.txt_namelist.setBackgroundColor(Color.parseColor("#e6e6e6"));

        return gridView;
    }


    public int getCount() {
        return 1;
    }

    public String getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

}
