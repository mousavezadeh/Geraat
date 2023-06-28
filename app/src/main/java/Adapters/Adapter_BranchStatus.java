package Adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import mousavi.Geraat.R.id;
import mousavi.Geraat.R.layout;
import mousavi.database.AddData;
import mousavi.database.Dto.BranchStatusDto;

/**
 * Created by mehdi on 08/09/2018.
 */

public class Adapter_BranchStatus extends ArrayAdapter {

    private static Context context;
    private Typeface font;
     class ViewHolder
    {
        private TextView txt_namelist;
        private TextView txt_id;
        private LinearLayout row_layout;
    }
    private final ArrayList<BranchStatusDto> branchStatuslist;
    private  boolean permissions;

    public Adapter_BranchStatus(Context context, ArrayList<BranchStatusDto> branchStatuslist) {
        super(context, layout.rowstyle_codemane);
        this.context = context;
        this.branchStatuslist = branchStatuslist;
        font = Typeface.createFromAsset(context.getAssets(), "Fonts/byekan.ttf");

    }
    @Override

    public View getView(final int position, View convertView, ViewGroup parent)
    {
        BranchStatusDto branchStatusDto;
        View gridView = null;
        if (convertView == null) 
        {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            gridView = new View(context);
            gridView = inflater.inflate(layout.rowstyle_codemane,parent,false);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.txt_namelist = (TextView) gridView.findViewById(id.txt_namelist_row_h);
            viewHolder.txt_id=(TextView) gridView.findViewById(id.txt_roozkar_row_h);
            viewHolder.row_layout=(LinearLayout) gridView.findViewById(id.lrow);
            gridView.setTag(viewHolder);

        }
        else
        {
            gridView = convertView;
        }

         ViewHolder holder = (ViewHolder) gridView.getTag();
         branchStatusDto=branchStatuslist.get(position);

        holder.txt_namelist.setTypeface(font);
        
        holder.txt_namelist.setText(branchStatusDto.title);
        holder.txt_id.setText(branchStatusDto.id.toString());
        holder.txt_namelist.setTextColor(Color.BLACK);

        holder.txt_namelist.setBackgroundColor(Color.parseColor("#e6e6e6"));

        return gridView;
    }


    public int getCount() {
        return branchStatuslist.size();
    }

    public String getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

}
