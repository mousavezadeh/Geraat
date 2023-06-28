package mousavi.Request.OnPostExecute;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import Adapters.Adapter_Citys;
import mousavi.Geraat.R;
import mousavi.Request.GetAllListGheraatByCityResponse;
import mousavi.Request.GetCityByDeviceIdResponse;
import mousavi.Request.GetListMobileResponse;
import mousavi.Request.IResponse;
import mousavi.database.Dto.ReadingListDto;
import mousavi.database.Insert.InsertReadingList;
import mousavi.database.Insert.InsertReadingListItem;
import mousavi.database.Select.FetchSelectOperation;
import mousavi.database.Select.SelectReadingListById;
import mousavi.database.Update.ActiveList;
import mousavi.database.databasetest;

public class GetListMobileOnPostExecute {
    Context context;
    IResponse iResponse;
    GetAllListGheraatByCityResponse readinglist;
    public GetListMobileOnPostExecute(Context context, IResponse iResponse,GetAllListGheraatByCityResponse readinglist){
      this.context=context;
      this.iResponse=iResponse;
      this.readinglist=readinglist;
    }
    public void Execute(){
        GetListMobileResponse ListMobileResponse=(GetListMobileResponse)iResponse;

        SelectReadingListById selectreadinglistbyid=new SelectReadingListById(databasetest.db,
                Integer.parseInt((readinglist.id)));
        FetchSelectOperation fetchSelectOperation=new FetchSelectOperation(selectreadinglistbyid);
        ReadingListDto readingListDto=fetchSelectOperation.fetch(ReadingListDto.class).get(0);
        if (readingListDto.Id_List==0)
        {
            InsertReadingList insertReadingList=new InsertReadingList(databasetest.db,readinglist);
            if (!insertReadingList.execute())
            {
                Toast.makeText(context,"خطا در انجام عملیات",Toast.LENGTH_SHORT).show();
                return;
            }

            for(GetListMobileResponse item:ListMobileResponse.ResponseList)
            {
                InsertReadingListItem insertReadingListItem=new InsertReadingListItem(databasetest.db,item);
                insertReadingListItem.execute();
            }
            return;
        }
        ActiveList activeList=new ActiveList(databasetest.db,Integer.parseInt(readinglist.id));
        activeList.execute();


    }

}
