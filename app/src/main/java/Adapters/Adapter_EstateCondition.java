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
import mousavi.database.Dto.EstateConditionDto;

/**
 * Created by mehdi on 08/09/2018.
 */

public class Adapter_EstateCondition extends ArrayAdapter {
    private static Context context;
    private Typeface font;
     class ViewHolder
    {
        private TextView txt_namelist;
        private TextView txt_id;
        private LinearLayout row_layout;
    }
    private ArrayList<EstateConditionDto> estateconditionlist=null;


    public Adapter_EstateCondition(Context context, ArrayList<EstateConditionDto> estateconditionlist) {
        super(context, layout.rowstyle_codemane);
        this.context = context;
        this.estateconditionlist = estateconditionlist;
        font = Typeface.createFromAsset(context.getAssets(), "Fonts/byekan.ttf");

    }
    @Override

    public View getView(final int position, View convertView, ViewGroup parent)
    {

        EstateConditionDto estateConditionDto=new EstateConditionDto();
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

        estateConditionDto=estateconditionlist.get(position);
        holder.txt_namelist.setTypeface(font);
        
        holder.txt_namelist.setText(estateConditionDto.title);
        holder.txt_id.setText(estateConditionDto.id.toString());

        holder.txt_namelist.setTextColor(Color.BLACK);
        holder.txt_namelist.setBackgroundColor(Color.parseColor("#e6e6e6"));
        
//        if (permissions==false)
//        {
//            if (adaptertype!=null) {
//                //region EstateCondition
//                if (adaptertype.compareTo(AdapterType.EstateCondition) == 0) {
//                    if (temp.get_id() == 1)
//                        holder.row_layout.setVisibility(View.VISIBLE);
//                    else
//                        holder.row_layout.setVisibility(View.GONE);
//                }
//                //endregion
//                //region BranchCondition
//                if (adaptertype.compareTo(AdapterType.BranchCondition) == 0) {
//                    if (temp.get_id() == 1)
//                        holder.row_layout.setVisibility(View.VISIBLE);
//                    else
//                        holder.row_layout.setVisibility(View.GONE);
//                }
//                //endregion}}
//            }
//        }

        return gridView;
    }


    public int getCount() {
        return estateconditionlist.size();
    }

    public String getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

}
