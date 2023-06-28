package mousavi.database.Select;

import android.database.sqlite.SQLiteDatabase;

public class SelectReadingListItemByOfficeridConsumption extends BaseSqlSelect {
    String idlist;
    String officerid;
    Integer startconsumption;
    Integer endconsumption;
    public SelectReadingListItemByOfficeridConsumption(SQLiteDatabase sqLiteDatabase, String idlist, String officerid, Integer startconsumption,Integer endconsumption) {
        super(sqLiteDatabase);
        this.idlist=idlist;
        this.officerid=officerid;
        this.startconsumption=startconsumption;
        this.endconsumption=endconsumption;
    }

    @Override
    public String sql() {
        return "select name_moshtarek,radif_tashkhis,eshterak,address,CAST(meghdar_ghabli as int) meghdar_ghabli,SendToServer,EstateId, "
                + " cast(karbari as int) karbari,tarikh_ghabli,bedehi,code_mane_feli,karkard_feli,mane_ghabli,doreh,rozkar,code_shahr,shenaseh_ghabz," +
                " shomare_kontor,SendToServer,FKId_List, rowno,karbarititle,mobile,code_posti,Replacementofmeter,EstateConditionId," +
                " BranchKindId,BranchStatusId,m1.Title,m2.Title as KindBranchTitle,m3.Title as BranchStatusTitle,OfficerDescription,ShenaseGhabz,ShenasePardakht,IsUnderConstruction,IsTemporalHousing,MeterStatus from inp " +
                " left OUTER  join  Meta as m1 " +
                " on cast(m1.Alias as INT)=ifnull(inp.EstateConditionId,1)  and m1.Node='EstateConditionId' " +
                " left OUTER  join  Meta as m2 "+
                " on cast(m2.Alias as INT)=ifnull(inp.BranchKindId,1) and m2.Node='Branch.Kind' "+
                " left OUTER  join  Meta as m3 "+
                " on cast(m3.Alias as INT)=ifnull(inp.BranchStatusId,1) and m3.Node='Branch.status' "+
                " where FKId_List=? and code_mamor=? and cast(code_mane_feli as int)=1 and (cast(karkard_feli as INT) - cast(meghdar_ghabli as int) BETWEEN ? and ?) order by code_mamor,radif_tashkhis asc ";

    }

    @Override
    public String[] parameters() {
        return new String[]{this.idlist,this.officerid,this.startconsumption.toString(),this.endconsumption.toString()};
    }
}
