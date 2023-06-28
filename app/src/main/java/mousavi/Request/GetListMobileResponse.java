package mousavi.Request;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GetListMobileResponse implements IResponse {

    public String   name_moshtarek;
    public String   radif_tashkhis;
    public String   eshterak;
    public String   address;
    public String  meghdar_ghabli;
    public String   SendToServer;
    public Integer   EstateId;
    public String   karbari;
    public String   tarikh_ghabli;
    public String   bedehi;
    public String   code_mane_feli;
    public String   karkard_feli;
    public String   mane_ghabli;
    public String   doreh;
    public String   rozkar;
    public String   code_shahr;
    public String   shenaseh_ghabz;
    public String   shomare_kontor;
    public Integer   FKId_List;
    public Integer   rowno;
    public String   karbarititle;
    public String   mobile;
    public String   code_posti;
    public String   Replacementofmeter;
    public Integer   EstateConditionId;
    public String   EstateConditionTitle;
    public Integer  BranchKindId;
    public String   BranchStatusId;
    public String   Title;
    public String   KindBranchTitle;
    public String   BranchStatusTitle;
    public String   OfficerDescription;
    public String   ShenasePardakht;
    public Integer   IsUnderConstruction;
    public Integer   IsTemporalHousing;
    public Integer   MeterStatus;
    public String   parvandeh;
    public String   code_mamor;
    public String   code_rosta;
    public String   code_mantagheh;
    public String   code_meli;
    public String   ghotr;
    public String   tedad_vahed;
    public String   tedad_khanevar;
    public String   model_kontor;
    public String   tarikh_nasb;
    public String   tarikh_taviz;
    public String   tedad_argham;
    public String   bazresi_ghabli;
    public String   masraf1;
    public String   masraf2;
    public String   replacementofmeter;
    public ArrayList<GetListMobileResponse> ResponseList;

    public GetListMobileResponse(Object res){
        try {
            Gson gson=new Gson();
            Type listType = new TypeToken<List<GetListMobileResponse>>() {}.getType();
            ResponseList = gson.fromJson(res.toString(),listType);

        } catch (Exception e) {

        }
    }
    public GetListMobileResponse(){};

}


